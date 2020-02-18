import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { PreJobNiazsanjiMarineSuffixService } from './pre-job-niazsanji-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import {IPersonMarineSuffix, PersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IUser, Principal, UserService} from "app/core";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IHomePagePersonEducationalModule} from "app/shared/model/custom/home-page-person-educational-module";
import {IEducationalModuleJobMarineSuffix} from "app/shared/model/educational-module-job-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {EducationalModuleJobMarineSuffixService} from "app/entities/educational-module-job-marine-suffix";
import {
    EducationalModuleMarineSuffix,
    IEducationalModuleMarineSuffix
} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {JobMarineSuffixService} from "app/entities/job-marine-suffix";
import {ICompetencyMarineSuffix} from "app/shared/model/competency-marine-suffix.model";
import {CompetencyMarineSuffixService} from 'app/entities/competency-marine-suffix';
import {IPreJobNiazsanjiCompetencyMarineSuffix} from "app/shared/model/pre-job-niazsanji-competency-marine-suffix.model";
import {IJobMarineSuffix} from "app/shared/model/job-marine-suffix.model";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";

@Component({
    selector: 'mi-pre-job-niazsanji-marine-suffix-update',
    templateUrl: './pre-job-niazsanji-marine-suffix-update.component.html',
    styleUrls: ['./pre-job-niazsanji-marine-suffix.scss']
})
export class PreJobNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    preJobNiazsanji: IPreJobNiazsanjiMarineSuffix;
    isSaving: boolean;
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];
    orgChartDisabled: boolean;
    job: IJobMarineSuffix = {};
    selectedPerson: IPersonMarineSuffix = new PersonMarineSuffix();

    documents: IDocumentMarineSuffix[];

    people: IPersonMarineSuffix[];
    agents: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    allPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    currentAccount: any;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;


    errorMessage: string;
    competencies: ICompetencyMarineSuffix[];
    preJobNiazsanjiCompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[] = [];
    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected jobService: JobMarineSuffixService,
        protected competencyService: CompetencyMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected finalNiazsanjiReportMarineSuffixService: FinalNiazsanjiReportMarineSuffixService,
        protected educationalModuleJobService: EducationalModuleJobMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private userService: UserService,
        private principal: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ preJobNiazsanji }) => {
            this.preJobNiazsanji = preJobNiazsanji;
            if(this.organizationChartService.organizationchartsAll){
                this.organizationcharts = this.convertObjectDatesService.goClone(this.organizationChartService.organizationchartsAll);

                if(this.preJobNiazsanji.organizationChartId){
                    let org = this.organizationcharts.find(a => a.id == this.preJobNiazsanji.organizationChartId);
                    this.onOrganizationChartChange(org);
                }

                this.setPermission();
            }
            else {
                this.organizationChartService.query().subscribe(
                    (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                        this.organizationcharts = res.body;
                        if (this.preJobNiazsanji.organizationChartId) {
                            let org = this.organizationcharts.find(a => a.id == this.preJobNiazsanji.organizationChartId);
                            this.onOrganizationChartChange(org);
                        }

                        this.setPermission();
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
        });
        this.competencyService.query().subscribe(
            (res: HttpResponse<ICompetencyMarineSuffix[]>) => {
                this.competencies = res.body.sort((a,b) => (a.displayOrder > b.displayOrder) ? 1 : (a.displayOrder < b.displayOrder) ? -1 : 0);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    jobNotFoundError: string;
    setPermission(){
        this.principal.identity().then(account => {

            this.currentAccount = account;
            this.setRoles(account);

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {

                this.currentPerson = resp.body;
                this.onPersonChange(this.currentPerson);
                if(!this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
                    this.people = [];
                    this.people.push(resp.body);
                    this.preJobNiazsanji.organizationChartId = resp.body.organizationChartId;
                    this.preJobNiazsanji.personId = resp.body.id;
                    this.handleOrgChartView();
                }
                else{
                    //this.findTargetPeople(this.currentPerson);
                    this.handleOrgChartView();
                }
                /*this.userService.findAllByAuthorityIs('ROLE_KARSHENAS_AMOZESH').subscribe((resp: HttpResponse<IUser[]>) => {


                    let orgIds = this.treeUtilities.getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId);

                    let users = resp.body;

                    let criteria = [{
                        key:'id.in',
                        value: users.map(a => a.personId)
                    }, {
                        key: 'organizationChartId.in',
                        value: orgIds
                    }];
                    this.personService.query({
                        page: 0,
                        size: 20000,
                        criteria,
                        sort: ["id","asc"]
                    }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                            this.agents = resp.body;
                        },
                        (res: HttpErrorResponse) => this.onError(res.message));
                    //this.agents = this.allPeople.filter(a => personIds.includes(a.id));
                }, (res: HttpErrorResponse) => this.onError(res.message));*/

            }, (res: HttpErrorResponse) => this.onError(res.message));

        });
    }
    handleOrgChartView(){
        if(this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId))
        {
            let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
            this.orgChartDisabled = false;
        }
        else{
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
            this.orgChartDisabled = true;
        }
        this.preparePeople();
    }
    preparePeople() {
        if(this.isSuperUsers) {
            if (this.personService.people) {
                this.allPeople = this.convertObjectDatesService.goClone(this.personService.people);
            }
            else {
                this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.allPeople = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message));
            }
            return
        }
        let criteria = [{
            key:'organizationChartId.in',
            value: this.recommenedOrgCharts.map(a => a.id)
        }];
        this.personService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id","asc"]
        }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                this.allPeople = resp.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }
    setRoles(account: any){

        if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
            this.isAdmin = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
            this.isModirAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
            this.isModirKolAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isSuperUsers = true;
    }
    onOrganizationChartChange(event){

        if(event.id){
            let criteria = [{
                key:'organizationChartId.equals',
                value: event.id
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            }).subscribe((resp) => {
                    this.people = resp.body;
                },
                (error) => this.onError("فردی یافت نشد."));
        }
    }

    onPersonChange(event: IPersonMarineSuffix) {
        if(event.id) {
            this.personService.find(event.id).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.selectedPerson = resp.body;
                    if (this.organizationcharts.find(a => a.id == this.selectedPerson.organizationChartId)) {
                        this.preJobNiazsanji.organizationChartId = this.selectedPerson.organizationChartId;
                    }
                    else {
                        this.organizationChartService.find(this.selectedPerson.organizationChartId).subscribe((org: HttpResponse<IOrganizationChartMarineSuffix>) => {
                                this.organizationcharts.push(org.body);
                                this.preJobNiazsanji.organizationChartId = this.selectedPerson.organizationChartId;
                            },
                            (res: HttpErrorResponse) => this.onError(res.message));
                    }
                    if (this.selectedPerson.jobId) {
                        this.jobNotFoundError = "";
                        this.jobService.find(this.selectedPerson.jobId).subscribe((resp: HttpResponse<IJobMarineSuffix>) => {
                                this.job = resp.body;
                                this.preJobNiazsanji.reviewDate = this.job.reviewDate;
                            },
                            (res: HttpErrorResponse) => this.onError(res.message));
                    }
                    else
                    {
                        this.jobNotFoundError = "برای " + this.currentPerson.fullName + " شغل سازمانی تعیین نشده است.";
                    }
                },
                (error) => this.onError("موردی یافت نشد"));
        }

    }
    previousState() {
        window.history.back();
    }
    currentUserFullName: string;
    save() {
        if(confirm("آیا برای ذخیره نهایی اطلاعات مطمئنید؟ (در صورت تایید امکان ویرایش اطلاعات وجود ندارد.)")) {
            this.isSaving = true;
            if(!this.preJobNiazsanji.title)
                this.preJobNiazsanji.title = "نیازسنجی شغلی";
            this.currentUserFullName = this.currentPerson.fullName;
            if (this.preJobNiazsanji.id !== undefined) {

            }
            else{
                if(this.currentPerson.organizationChartId) {
                    if(!this.preJobNiazsanji.organizationChartId)
                    {
                        this.errorMessage = "فرد مورد نظر شما در چارت سازمانی تعریف نشده است لطفا ابتدا آن را در چارت سازمانی تعریف سپس اقدام به ثبت نیازسنجی آن فرد نمائید.";
                        this.isSaving = false;
                        return;
                    }

                    let org = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                    if (org.parentId > 0)
                        this.preJobNiazsanji.status = org.parentId;
                    else
                        this.preJobNiazsanji.status = org.id;
                    this.preJobNiazsanji.step = 2;
                    this.preJobNiazsanji.archived = false;
                    this.preJobNiazsanji.preJobNiazsanjiCompetencies = [];
                    this.competencies.forEach(a => {
                        if (a.selectedItems) {
                            a.selectedItems.forEach(w => {
                                let newPreJob: IPreJobNiazsanjiCompetencyMarineSuffix = {
                                    title: w,
                                    competencyId: a.id,
                                };
                                this.preJobNiazsanji.preJobNiazsanjiCompetencies.push(newPreJob);
                            })
                        }
                    });
                    if(this.preJobNiazsanji.preJobNiazsanjiCompetencies.length == 0)
                    {
                        this.errorMessage = "لطفا حداقل یک مورد از شاخص های مورد انتظار خود را در باکس های بالا وارد نمائید.";
                        this.isSaving = false;
                        return;
                    }
                    this.preJobNiazsanji.requestStatus = RequestStatus.NEW;
                    this.preJobNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                    this.preJobNiazsanji.conversation = " درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. "
                    if (this.preJobNiazsanji.description) {
                        this.preJobNiazsanji.conversation += "\n";
                        this.preJobNiazsanji.conversation += this.currentUserFullName + ": " + this.preJobNiazsanji.description;
                    }
                    this.subscribeToSaveResponse(this.preJobNiazsanjiService.create(this.preJobNiazsanji));
                }
            }
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPreJobNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(res.body),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess(res: IPreJobNiazsanjiMarineSuffix) {
        this.router.navigateByUrl("pre-job-niazsanji-steps-marine-suffix/" + res.id + "/edit");
        this.isSaving = false;

    }
    protected onSaveSuccessCompetency(res: IPreJobNiazsanjiMarineSuffix) {

        this.isSaving = false;
        this.previousState();
    }
    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
