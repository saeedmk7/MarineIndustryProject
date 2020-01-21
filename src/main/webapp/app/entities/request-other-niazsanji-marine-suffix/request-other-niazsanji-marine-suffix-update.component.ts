import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import { RequestOtherNiazsanjiMarineSuffixService } from './request-other-niazsanji-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from 'app/entities/niazsanji-input-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import {
    EducationalModuleMarineSuffix,
    IEducationalModuleMarineSuffix
} from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import {IPersonMarineSuffix, PersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {Principal, UserService} from "app/core";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IEducationalModuleJobMarineSuffix} from "app/shared/model/educational-module-job-marine-suffix.model";
import {EducationalModuleJobMarineSuffixService} from "app/entities/educational-module-job-marine-suffix";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {IRequestNiazsanjiFardiMarineSuffix} from "app/shared/model/request-niazsanji-fardi-marine-suffix.model";

@Component({
    selector: 'mi-request-other-niazsanji-marine-suffix-update',
    templateUrl: './request-other-niazsanji-marine-suffix-update.component.html'
})
export class RequestOtherNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    restrictions: IRestrictionMarineSuffix[];

    niazsanjiinputs: INiazsanjiInputMarineSuffix[];

    coursetypes: ICourseTypeMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    people: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    teachingapproaches: ITeachingApproachMarineSuffix[];

    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgChartDisabled: boolean;

    finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[];
    allPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    currentAccount: any;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    message: string;
    targetPeople: IPersonMarineSuffix[];

    hasNoRow:boolean = false;
    currentUserFullName: string = "";

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected requestOtherNiazsanjiService: RequestOtherNiazsanjiMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected educationalModuleJobService: EducationalModuleJobMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected niazsanjiInputService: NiazsanjiInputMarineSuffixService,
        protected courseTypeService: CourseTypeMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private principal : Principal,
        private treeUtilities: TreeUtilities,
        private userService: UserService,
        private convertObjectDatesService: ConvertObjectDatesService,
        protected router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ requestOtherNiazsanji }) => {
            this.requestOtherNiazsanji = requestOtherNiazsanji;
        });
        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.niazsanjiInputService.query().subscribe(
            (res: HttpResponse<INiazsanjiInputMarineSuffix[]>) => {
                this.niazsanjiinputs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if(this.organizationChartService.organizationchartsAll){
            this.organizationcharts = this.convertObjectDatesService.goClone(this.organizationChartService.organizationchartsAll);

            if(this.requestOtherNiazsanji.organizationChartId){
                let org = this.organizationcharts.find(a => a.id == this.requestOtherNiazsanji.organizationChartId);
                this.onOrganizationChartChange(org);
            }

            this.setPermission();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    if (this.requestOtherNiazsanji.organizationChartId) {
                        let org = this.organizationcharts.find(a => a.id == this.requestOtherNiazsanji.organizationChartId);
                        this.onOrganizationChartChange(org);
                    }

                    this.setPermission();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix[]>) => {
                this.teachingapproaches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    setPermission(){
        this.principal.identity().then(account => {

            this.currentAccount = account;
            this.setRoles(account);

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {

                this.currentPerson = resp.body;
                if(!this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
                    this.requestOtherNiazsanji.organizationChartId = resp.body.organizationChartId;
                    this.requestOtherNiazsanji.personId = resp.body.id;
                    this.people = [];
                    this.people.push(resp.body);

                    this.findTargetPeople(this.currentPerson);
                    this.handleOrgChartView();
                    this.onPersonChange(resp.body);
                    //this.findTargetPeople(resp.body);
                }
                else{
                    this.findTargetPeople(this.currentPerson);
                    this.handleOrgChartView();

                }


                /*    this.findTargetPeople(resp.body);
                else
                    this.handleOrgChartView();*/



            }, (res: HttpErrorResponse) => this.onError(res.message));

        });
    }
    preparePeople() {
        if(this.isSuperUsers) {
            if (this.personService.people) {
                this.allPeople = this.personService.people;
            }
            else {
                this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.allPeople = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message));
            }
        }
        else {
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
    onPersonChange(event){
        debugger;
        if(event.id){
            this.personService.find(event.id).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    debugger;
                    const findPerson = resp.body;
                    if(this.organizationcharts.find(a => a.id == findPerson.organizationChartId))
                    {
                        this.requestOtherNiazsanji.organizationChartId = findPerson.organizationChartId;
                    }
                    else{
                        this.organizationChartService.find(findPerson.organizationChartId).subscribe((org: HttpResponse<IOrganizationChartMarineSuffix>) => {
                                this.organizationcharts.push(org.body);
                                this.requestOtherNiazsanji.organizationChartId = findPerson.organizationChartId;
                            },
                            (res: HttpErrorResponse) => this.onError(res.message));
                    }
                },
                (error) => this.onError("موردی یافت نشد"));
            const criteria = [{
                key:'jobId.equals',
                value: event.jobId
            }];
            this.educationalModuleJobService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            }).subscribe((resp: HttpResponse<IEducationalModuleJobMarineSuffix[]>) => this.showEducationalModules(resp.body),
                (error) => this.onError("موردی یافت نشد"));
        }
    }

    findTargetPeople(person: IPersonMarineSuffix){
        let organization = this.organizationcharts.find(a => a.id == person.organizationChartId);
        if(organization.parentId > 0) {
            let neededOrgId: number = organization.parentId;

            let criteria = [{
                key: 'organizationChartId.equals',
                value: neededOrgId
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    let orgPeople = resp.body;
                    if (orgPeople.length > 0) {
                        this.targetPeople = orgPeople;
                    }
                    else {
                        this.targetPeople = [];
                        this.targetPeople = [new PersonMarineSuffix(0, 'خطا', 'خطا', 'خطا: نفر دریافت کننده ای در چارت سازمانی برای شما تعریف نشده است. لطفا با مدیریت سامانه تماس بگیرید. ')]
                    }
                },
                (error) => this.onError("فردی یافت نشد."));
        }
        else{
            this.targetPeople = [];
            this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'درخواست شما پس از ثبت تایید در لیست اولویت بندی قرار میگیرد. برای ارسال نیازسنجی به کارشناس ارشد آموزش جهت بازبینی از لیست اولویت بندی استفاده نمائید.'));
        }
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
                    if(this.requestOtherNiazsanji.personId){
                        this.onPersonChange(this.people.find(a => a.id == this.requestOtherNiazsanji.personId));
                    }
                },
                (error) => this.onError("فردی یافت نشد."));
        }
    }

    showEducationalModules(resp: IEducationalModuleJobMarineSuffix[]){

        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }

    }
    onEducationalModuleChange($event: IEducationalModuleMarineSuffix){
        this.requestOtherNiazsanji.skillLevelOfSkillTitle = $event.skillableLevelOfSkillTitle;
        this.requestOtherNiazsanji.totalLearningTime = ($event.learningTimeTheorical ? +$event.learningTimeTheorical : 0) + ($event.learningTimePractical ? +$event.learningTimePractical : 0);
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.currentUserFullName = this.currentPerson.fullName;

        if (this.requestOtherNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.requestOtherNiazsanjiService.update(this.requestOtherNiazsanji),true);
        } else {
            if(this.currentPerson.organizationChartId) {

                let org = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                if(org.parentId > 0)
                    this.requestOtherNiazsanji.status = org.parentId;
                else
                    this.requestOtherNiazsanji.status = 0;
                this.requestOtherNiazsanji.requestStatus = RequestStatus.NEW;
                this.requestOtherNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                this.requestOtherNiazsanji.conversation = " درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. "
                if (this.requestOtherNiazsanji.description) {
                    this.requestOtherNiazsanji.conversation += "\n";
                    this.requestOtherNiazsanji.conversation += this.currentUserFullName + ": " + this.requestOtherNiazsanji.description;
                }
                this.subscribeToSaveResponse(this.requestOtherNiazsanjiService.create(this.requestOtherNiazsanji));

            }
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestOtherNiazsanjiMarineSuffix>>, isEdit:boolean = false) {
        result.subscribe(
            (res: HttpResponse<IRequestOtherNiazsanjiMarineSuffix>) => this.onSaveSuccess(res.body, isEdit),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess(res: IRequestOtherNiazsanjiMarineSuffix, isEdit:boolean = false) {
        if(!isEdit) {
            if(res.status == 0){
                res.conversation += "\n ------------------------------------- \n";
                res.conversation += " تایید درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                this.requestOtherNiazsanjiService.finalize(res).subscribe(
                    (res: HttpResponse<IRequestOtherNiazsanjiMarineSuffix>) => res,
                    (res: HttpErrorResponse) => res
                );
            }
        }
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

    trackRestrictionById(index: number, item: IRestrictionMarineSuffix) {
        return item.id;
    }

    trackNiazsanjiInputById(index: number, item: INiazsanjiInputMarineSuffix) {
        return item.id;
    }

    trackCourseTypeById(index: number, item: ICourseTypeMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }

    trackTeachingApproachById(index: number, item: ITeachingApproachMarineSuffix) {
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
    change(i){
        this.router.navigateByUrl(i);
    }
}
