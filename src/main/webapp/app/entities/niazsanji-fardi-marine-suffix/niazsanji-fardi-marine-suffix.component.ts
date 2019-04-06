import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {INiazsanjiFardiMarineSuffix} from 'app/shared/model/niazsanji-fardi-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {NiazsanjiFardiMarineSuffixService} from './niazsanji-fardi-marine-suffix.service';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {IFinalOrganizationNiazsanjiMarineSuffix} from "app/shared/model/final-organization-niazsanji-marine-suffix.model";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import * as $ from 'jquery';
import {
    IRequestNiazsanjiFardiMarineSuffix
} from "app/shared/model/request-niazsanji-fardi-marine-suffix.model";
import {RequestNiazsanjiFardiMarineSuffixService} from "app/entities/request-niazsanji-fardi-marine-suffix/request-niazsanji-fardi-marine-suffix.service";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";

@Component({
    selector: 'mi-niazsanji-fardi-marine-suffix',
    templateUrl: './niazsanji-fardi-marine-suffix.component.html'
})
export class NiazsanjiFardiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    niazsanjiFardis: INiazsanjiFardiMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    people: IPersonMarineSuffix[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    isAdmin: boolean;
    isModirKolAmozesh: boolean;
    isKarshenasArshadAmozesh: boolean;

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done: boolean = false;
    criteria: any;
    years: any[];
    yearsCollections: any[];
    selectedNiazSanji: number[] = [];
    selectedYear: number;
    counter: number = 0;
    constructor(
        protected niazsanjiFardiService: NiazsanjiFardiMarineSuffixService,
        protected requestNiazsanjiFardiService: RequestNiazsanjiFardiMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personService: PersonMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected principal: Principal,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private convertObjectDatesService: ConvertObjectDatesService,
        private jhiTranslate: TranslateService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) => {

            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);

        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
        this.years = this.yearsCollections.map(a => a.year);
    }
    selectYear(){
        if(this.selectedNiazSanji.length > 0){
            if(this.selectedYear) {
                this.selectedNiazSanji.forEach((a) => {
                    this.niazsanjiFardiService.find(a).subscribe((resp: HttpResponse<INiazsanjiFardiMarineSuffix>) => {
                            let niazSanjiFardi = resp.body;
                            niazSanjiFardi.niazsanjiYear = this.selectedYear;
                            this.niazsanjiFardiService.update(niazSanjiFardi).subscribe(
                                (res: HttpResponse<INiazsanjiFardiMarineSuffix>) => this.completeSuccess(),
                                (error1) => this.onError(error1.message));
                        },
                        (error1) => this.onError(error1.message));
                });
            }
            else{
                alert('لطفا سال نیازسنجی را انتخاب نمائید.');
            }
        }
        else{
            alert('لطفا حداقل یک نیازسنجی را انتخاب نمائید.');
        }
    }

    completeSuccess(){
        this.counter++;
        if(this.selectedNiazSanji.length == this.counter){
            this.loadAll(this.criteria);
            this.counter = 0;
            this.selectedNiazSanji = [];
        }
    }
    createCriteria(criteria?): any {

        if (criteria) {
            let val = +criteria.find(a => a.key == 'yearId.equals').value;
            criteria = criteria.filter(a => a.key != 'yearId.equals');
            if (val) {
                let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();

                criteria.push({
                    key: 'createDate.lessOrEqualThan', value: endDate
                });
                criteria.push({
                    key: 'createDate.greaterOrEqualThan', value: beginDate
                });
            }
        }
        else{
            criteria = [];
        }
        if(this.isModirKolAmozesh){
            criteria.push({
                key: 'status.greaterOrEqualThan', value: 10
            });
        }
        return criteria;
    }

    loadAll(criteria?, exportExcel: boolean = false) {
        criteria = this.createCriteria(criteria);
        if(exportExcel){
            this.niazsanjiFardiService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<INiazsanjiFardiMarineSuffix[]>) => this.finalExportToExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else {
            this.niazsanjiFardiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<INiazsanjiFardiMarineSuffix[]>) => this.paginateNiazsanjiFardis(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/niazsanji-fardi-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        this.loadAll(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/niazsanji-fardi-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    onChange(id:number, isChecked: boolean) {
        if(isChecked) {
            if(!this.selectedNiazSanji.includes(id))
                this.selectedNiazSanji.push(id);
        } else {
            this.selectedNiazSanji = this.selectedNiazSanji.filter(a => a != id);
        }
    }
    selectAll(isChecked: boolean){
        if(isChecked){
            $('.mycheckbox').each((a, item) => {
                $(item).prop('checked',true);
                this.onChange(item.id,true)
            });
        }
        else{
            $('.mycheckbox').each((a, item) => {
                $(item).prop('checked',false);
                this.onChange(item.id,false)
            });
        }
    }


    export() {
        this.loadAll(this.criteria, true);
    }
    finalExportToExcel(excelNiazSanjiFardis: INiazsanjiFardiMarineSuffix[]){
        let a = new ExcelService(this.jhiTranslate);
        excelNiazSanjiFardis = this.convertObjectDatesService.changeArrayDate(excelNiazSanjiFardis);
        let report = [];
        let index: number = 0;
        excelNiazSanjiFardis.forEach(a => {
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId).fullTitle;
            let person = this.people.find(w => w.id == a.personId);
            index++;
            let approvedEducationalModule = this.educationalModules.find(w => w.id == a.educationalModuleId);
            let obj: Object;
            obj = {'index': index,
                'organizationChart': org,
                'personName': person.fullName,
                'jobTitle': person.jobTitle,
                'educationalModule': a.educationalModuleTitle,
                'timeEducationalModule': (approvedEducationalModule.learningTimePractical ? approvedEducationalModule.learningTimePractical : 0) + (approvedEducationalModule.learningTimeTheorical ? approvedEducationalModule.learningTimeTheorical : 0),
                'levelEducationalModule': approvedEducationalModule.skillableLevelOfSkillTitle,
                'costEducationalModule': a.priceCost,
                'createDate': a.createDate,
                'niazsanjiYear': a.niazsanjiYear
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'niazsanjiFardis', 'marineindustryprojApp.niazsanjiFardi');
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined) {
                this.isAdmin = true;
            }

            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }

            this.prepareSearchOrgChart();
            this.prepareSearchPerson();
            this.prepareSearchEducationalModule();
            this.prepareSearchDate();
            if (!this.done) {
                this.loadAll();
            }
        });
        //this.registerChangeInNiazsanjiFardis();
    }

    prepareSearchEducationalModule(){
        if(this.educationalModuleService.educationalModules){
            this.educationalModules = this.educationalModuleService.educationalModules
            this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', this.educationalModules, "fullTitle",'half'));
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', res.body, "fullTitle",'half'));

                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
    }
    prepareSearchPerson(){
        if(this.personService.people){
            this.people = this.personService.people;
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    //this.people.forEach(a => a["title"] = a.fullName);
                    this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'personId', 'select', 'equals', this.people, "fullName"));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    prepareSearchDate(){
        let dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates));
    }
    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', this.organizationcharts, 'fullTitle', 'half'));
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    //this.organizationcharts = this.tree
                    this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', this.organizationcharts, 'fullTitle', 'half'));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    complete(mymodel: INiazsanjiFardiMarineSuffix) {
        if(mymodel.niazsanjiYear) {
            this.niazsanjiFardiService.find(mymodel.id).subscribe((resp) => {
                let model = resp.body;

                if (confirm("آیا از اجرا کردن این دوره فردی مورد نظر مطمئنید.")) {

                    this.niazsanjiFardiService.finalize(model).subscribe(
                        (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSuccess("دوره متمرکز مورد نظر شما به مرحله اجرا وارد شد."),
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
                }
            });
        }
        else{
            alert("انتخاب کردن سال نیازسنجی اجباریست.");
        }
    }
    accept(niazsanjiFardi: INiazsanjiFardiMarineSuffix){
        this.niazsanjiFardiService.find(niazsanjiFardi.id).subscribe((resp) => {
            let model = resp.body;

            if (confirm("آیا از اجرا کردن این دوره فردی مورد نظر مطمئنید.")) {

                this.niazsanjiFardiService.finalize(model).subscribe(
                    (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSuccess("دوره متمرکز مورد نظر شما به مرحله اجرا وارد شد."),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
        });
    }
    reject(model: INiazsanjiFardiMarineSuffix){
        let message = prompt("لطفا علت عدم تایید را ثبت نمائید.");
        if(message)
        {

            let currentUserFullName = document.getElementById('currenUserFullNameTopBar').textContent;
            this.niazsanjiFardiService.find(model.id).subscribe((resp: HttpResponse<INiazsanjiFardiMarineSuffix>) =>{

                let niazSanjiFardi = resp.body;
                this.requestNiazsanjiFardiService.find(niazSanjiFardi.requestNiazsanjiFardiId).subscribe((res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => {

                    let requestNiazSanjiFardi = res.body;
                    requestNiazSanjiFardi.status = 21;
                    requestNiazSanjiFardi.conversation += "\n ------------------------------------- \n";
                    requestNiazSanjiFardi.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                    requestNiazSanjiFardi.requestStatus = RequestStatus.IGNORE;
                    requestNiazSanjiFardi.changeStatusUserLogin = this.currentAccount.login;
                    requestNiazSanjiFardi.conversation += "\n";
                    requestNiazSanjiFardi.conversation += currentUserFullName + ": " + message;

                    this.requestNiazsanjiFardiService.update(requestNiazSanjiFardi).subscribe(
                        (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => res,
                        (error: HttpErrorResponse) => this.onError(error.message));

                    niazSanjiFardi.status = 5;
                    niazSanjiFardi.changeStatusUserLogin = this.currentAccount.login;
                    niazSanjiFardi.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                    this.niazsanjiFardiService.update(niazSanjiFardi).subscribe(
                        (res: HttpResponse<INiazsanjiFardiMarineSuffix>) => res,
                        (error: HttpErrorResponse) => this.onError(error.message));
                    //this.niazsanjiFardiService.update()

                });
            });

        }
        else{
            alert("علت عدم تایید اجباریست. ");
        }

    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: INiazsanjiFardiMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInNiazsanjiFardis() {
        this.eventSubscriber = this.eventManager.subscribe('niazsanjiFardiListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateNiazsanjiFardis(data: INiazsanjiFardiMarineSuffix[], headers: HttpHeaders) {

        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.niazsanjiFardis = this.convertObjectDatesService.changeArrayDate(data, true);
    }

    private onSuccess(successMessage: string) {
        this.jhiAlertService.success(successMessage, null, null);
        this.loadAll(this.criteria);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
        this.loadAll(this.criteria);
    }
}
