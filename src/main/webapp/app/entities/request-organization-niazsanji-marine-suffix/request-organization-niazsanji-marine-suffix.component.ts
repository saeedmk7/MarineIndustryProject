import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {IRequestOrganizationNiazsanjiMarineSuffix} from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {RequestOrganizationNiazsanjiMarineSuffixService} from './request-organization-niazsanji-marine-suffix.service';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';
import {
    FinalOrganizationNiazsanjiMarineSuffix,
    IFinalOrganizationNiazsanjiMarineSuffix
} from "app/shared/model/final-organization-niazsanji-marine-suffix.model";
import {FinalOrganizationNiazsanjiMarineSuffixService} from "app/entities/final-organization-niazsanji-marine-suffix/final-organization-niazsanji-marine-suffix.service";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix',
    templateUrl: './request-organization-niazsanji-marine-suffix.component.html',
    styleUrls: ['./request-organization-niazsanji-marine-suffix.scss']
})
export class RequestOrganizationNiazsanjiMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    requestOrganizationNiazsanjis: IRequestOrganizationNiazsanjiMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    people: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
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

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[]= new Array<SearchPanelModel>();
    done:boolean = false;
    criteria: any;

    yearsCollections: any[];
    constructor(
        private requestOrganizationNiazsanjiService: RequestOrganizationNiazsanjiMarineSuffixService,
        private finalOrganizationNiazsanjiMarineSuffixService: FinalOrganizationNiazsanjiMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService : ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        private personService: PersonMarineSuffixService,
    ) {

        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {

            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.criteria = criteria.content;
            this.done = true;
            this.makeCriteria(criteria.content);

        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
    }
    makeCriteria(criteria?,excelExport: boolean = false){

        if (criteria) {
            let val = +criteria.find(a => a.key == 'yearId.equals').value;
            //criteria.pop('yearId');
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
        if(this.currentPerson){
            if(this.organizationChartService.organizationchartsAll){
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
                let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                return this.handleAfterChart(wantOrgIds,criteria,excelExport);
            }
            else{
                this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) =>{

                    this.organizationcharts = resp.body;
                    let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                    this.handleAfterChart(wantOrgIds,criteria,excelExport);
                });
            }
        }
        else{
            this.principal.identity().then(account => {

                this.currentAccount = account;
                if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                    this.isAdmin = true;

                this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;

                    if(this.organizationChartService.organizationchartsAll){
                        this.organizationcharts = this.organizationChartService.organizationchartsAll;
                        let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                        this.handleAfterChart(wantOrgIds,criteria,excelExport);
                    }
                    else{
                        this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) =>{

                            this.organizationcharts = resp.body;
                            let wantOrgIds = this.treeUtilities.getAllOfChilderenIdsOfThisIdWithoutItself(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
                            this.handleAfterChart(wantOrgIds,criteria,excelExport);
                        });
                    }
                });
            });
        }

    }
    handleAfterChart(wantOrgIds: number[],criteria,excelExport: boolean = false){
        if(this.isAdmin) {
            this.loadAll(criteria, excelExport);
            return;
        }
        if(wantOrgIds.length > 0) {
            let criteria1 = [{
                key: 'organizationChartId.in',
                value: wantOrgIds
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria: criteria1,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {

                let selectedPeople = resp.body;
                if (selectedPeople.length > 0) {
                    let logins: string[] = selectedPeople.map(a => a.nationalId);
                    logins.push(this.currentPerson.nationalId);
                    criteria.push({
                        key: 'createUserLogin.in',
                        value: logins
                    });
                }
                else {
                    let logins = [this.currentPerson.nationalId];
                    criteria.push({
                        key: 'createUserLogin.in',
                        value: logins
                    });
                }
                this.loadAll(criteria, excelExport);
            });
        }
        else{
            criteria.push({
                key: 'createUserLogin.in',
                value: [this.currentPerson.nationalId]
            });
            this.loadAll(criteria, excelExport);
        }
    }
    loadAll(criteria?, excelExport: boolean = false) {

        if(!this.isAdmin)
        {
            let orgs = this.treeUtilities.getParentIds(this.organizationcharts,this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            if(orgs.length > 0){
                orgs.push(0);
                criteria.push({
                    key: 'status.in',
                    value: orgs
                });
            }
            else{
                orgs = [0];
                criteria.push({
                    key: 'status.equals',
                    value: orgs
                });
            }
        }
        if(excelExport) {
            this.requestOrganizationNiazsanjiService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix[]>) =>
                        this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        else{
            this.requestOrganizationNiazsanjiService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix[]>) =>
                        this.paginateRequestOrganizationNiazsanjis(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    export() {
        this.makeCriteria(this.criteria,true);
        /*let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.requestOrganizationNiazsanjis, 'requestOrganizationNiazsanjis', 'marineindustryprojApp.requestOrganizationNiazsanji');*/
    }
    prepareForExportExcel(res : IRequestOrganizationNiazsanjiMarineSuffix[]){
        res = this.convertObjectDatesService.changeArrayDate(res);
        if(this.personService.people){
            this.people = this.personService.people;
            if(this.educationalModuleService.educationalModules) {
                this.educationalModules = this.educationalModuleService.educationalModules;
                this.exportRequestsFinal(res);
            }
            else{
                this.educationalModuleService.query().subscribe((resp: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                        this.educationalModules = resp.body;
                        this.exportRequestsFinal(res);
                    },
                    (error) => this.onError("پودمانی یافت نشد."));
            }
        }
        else {
            this.personService.query().subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = resp.body;
                    if(this.educationalModuleService.educationalModules) {
                        this.educationalModules = this.educationalModuleService.educationalModules;
                        this.exportRequestsFinal(res);
                    }
                    else{
                        this.educationalModuleService.query().subscribe((resp: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                                this.educationalModules = resp.body;
                                this.exportRequestsFinal(res);
                            },
                            (error) => this.onError("پودمانی یافت نشد."));
                    }
                },
                (error) => this.onError("فردی یافت نشد."));
        }
    }
    exportRequestsFinal(res : IRequestOrganizationNiazsanjiMarineSuffix[]){
        let a = new ExcelService(this.jhiTranslate);
        let report = [];
        let index: number = 0;
        res.forEach(a => {
            let org = this.organizationcharts.find(w => w.id == a.organizationChartId).fullTitle;
            let personIds = a.people.map(a => a.id);
            let filteredPeople = this.people.filter(w => personIds.includes(w.id));
            let peopleNames: string[] = filteredPeople.map(a => a.fullName);

            index++;
            let educationalModule = this.educationalModules.find(w => w.id == a.educationalModuleId);
            let obj: Object;
            obj = {'index': index,
                'organizationChartId': org,
                'personName': peopleNames.join('،'),
                /*'jobTitle': person.jobTitle,*/
                'educationalModule': educationalModule.fullTitle,
                'timeEducationalModule': (educationalModule.learningTimePractical ? educationalModule.learningTimePractical : 0) + (educationalModule.learningTimeTheorical ? educationalModule.learningTimeTheorical : 0),
                'levelEducationalModule': educationalModule.skillableLevelOfSkillTitle,
                'costEducationalModule': a.priceCost,
                'createDate': a.createDate,
                'status': this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus)
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'requestOrganizationNiazsanjis', 'marineindustryprojApp.requestNiazsanjiFardi');
    }
    /*reject(mymodel: IRequestOrganizationNiazsanjiMarineSuffix)
    {
        if(confirm("آیا برای رد کردن کامل درخواست موافقید؟")) {
            this.requestOrganizationNiazsanjiService.find(mymodel.id).subscribe((resp) => {
                let model = resp.body;
                model.requestStatus = RequestStatus.IGNORE;
                model.changeStatusUserLogin = this.currentAccount.login;
                this.requestOrganizationNiazsanjiService.update(model).subscribe(
                    (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccessIgnore(),
                    (res: HttpErrorResponse) => this.onSaveError()
                );
        });
        }
    }
    complete(mymodel: IRequestOrganizationNiazsanjiMarineSuffix){
        this.requestOrganizationNiazsanjiService.find(mymodel.id).subscribe((resp) => {
            let model = resp.body;
            if (model.teacherNotFound) {
                this.onError("marineindustryprojApp.requestOrganizationNiazsanji.teacherNotFoundError");
                return;
            }
            if (confirm("آیا از تایید و نهایی کردن این درخواست مطمئنید.")) {

                let finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix = new FinalOrganizationNiazsanjiMarineSuffix();
                finalOrganizationNiazsanji.organizationChartId = model.organizationChartId;
                finalOrganizationNiazsanji.requestOrganizationNiazsanjiId = model.id;
                finalOrganizationNiazsanji.code = model.code;
                finalOrganizationNiazsanji.requestStatus = RequestStatus.ACCEPT;
                finalOrganizationNiazsanji.archived = false;
                finalOrganizationNiazsanji.description = model.description;
                finalOrganizationNiazsanji.documents = model.documents;
                finalOrganizationNiazsanji.educationalModuleId = model.educationalModuleId;
                finalOrganizationNiazsanji.neededHardware = model.neededHardware;
                finalOrganizationNiazsanji.neededSoftwares = model.neededSoftwares;
                finalOrganizationNiazsanji.people = model.people;
                finalOrganizationNiazsanji.priceCost = model.priceCost;
                finalOrganizationNiazsanji.recommendedByOrgchart = model.recommendedByOrgchart;
                finalOrganizationNiazsanji.studentsType = model.studentsType;
                finalOrganizationNiazsanji.teachApproachId = model.teachApproachId;
                finalOrganizationNiazsanji.teacherMobile = model.teacherMobile;
                finalOrganizationNiazsanji.trainingGoals = model.trainingGoals;
                finalOrganizationNiazsanji.status = 0;
                finalOrganizationNiazsanji.teacherId = model.teacherId;
                this.finalOrganizationNiazsanjiMarineSuffixService.create(finalOrganizationNiazsanji).subscribe(
                    (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.updateRequest(model),
                    (res: HttpErrorResponse) => this.onSaveError()
                );
            }
        });
    }*/
    /*private updateRequest(model: IRequestOrganizationNiazsanjiMarineSuffix){
        //let ss: RequestStatus = RequestStatus.ACCEPT;
        model.requestStatus = RequestStatus.ACCEPT;
        model.changeStatusUserLogin = this.currentAccount.login;
        this.requestOrganizationNiazsanjiService.update(model).subscribe(
            (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }*/
    private onSaveSuccess() {
        this.makeCriteria(this.criteria);
        this.jhiAlertService.success("marineindustryprojApp.requestOrganizationNiazsanji.completed");
    }
    private onSaveSuccessIgnore() {
        this.makeCriteria(this.criteria);
        this.jhiAlertService.success("marineindustryprojApp.requestOrganizationNiazsanji.rejected");
    }
    private onSaveError() {

    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/request-organization-niazsanji-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        this.makeCriteria(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/request-organization-niazsanji-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.makeCriteria();
    }

    ngOnInit() {

        this.principal.identity().then(account => {
            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;

            this.prepareSearchDate();
            this.prepareSearchEducationalModule();
            this.prepareSearchOrgChart();
            if(!this.done){
                this.makeCriteria();
            }
        });
        //this.registerChangeInRequestOrganizationNiazsanjis();
    }
    prepareSearchEducationalModule(){
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalModules = res.body;
                this.searchbarModel.push(new SearchPanelModel('niazsanjiFardi', 'educationalModuleId', 'select', 'equals', res.body));

            },
            (res: HttpErrorResponse) => this.onError(res.message))
    }
    prepareSearchDate(){
        let dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('requestOrganizationNiazsanji', 'yearId', 'select', 'equals', dates));
    }
    prepareSearchOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    this.searchbarModel.push(new SearchPanelModel('requestNiazsanjiFardi', 'organizationChartId', 'select', 'equals', this.organizationcharts));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IRequestOrganizationNiazsanjiMarineSuffix) {
        return item.id;
    }

    registerChangeInRequestOrganizationNiazsanjis() {
        this.eventSubscriber = this.eventManager.subscribe('requestOrganizationNiazsanjiListModification', response => this.loadAll());
    }

    sort() {

        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateRequestOrganizationNiazsanjis(data: IRequestOrganizationNiazsanjiMarineSuffix[], headers: HttpHeaders) {

        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.requestOrganizationNiazsanjis = this.convertObjectDatesService.changeArrayDate(data);
        this.requestOrganizationNiazsanjis.forEach((a: IRequestOrganizationNiazsanjiMarineSuffix) => {
            a.statusMeaning = this.treeUtilities.getStatusMeaning(this.organizationcharts, a.status, a.requestStatus);
        });
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.warning(errorMessage);
    }
    /*createCriteria(criteria?): any{

        if(criteria)
        {
            let val = +criteria.find(a => a.key == 'yearId.equals').value;
            criteria.pop('yearId');
            if(val){
                let yearDetail = this.yearsCollections.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();

                criteria.push({
                    key:'createDate.lessOrEqualThan', value: endDate
                });
                criteria.push({
                    key:'createDate.greaterOrEqualThan', value: beginDate
                });
            }
        }
        if(!this.isAdmin) {
            if (criteria) {
                criteria.push({key: 'createUserLogin.equals', value: this.currentAccount.login})
            }
            else {
                criteria = [
                    {key: 'createUserLogin.equals', value: this.currentAccount.login}
                ];
            }
        }
        return criteria;
    }*/
}
