import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpEventType, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEducationalHistoryMarineSuffix } from 'app/shared/model/educational-history-marine-suffix.model';
import { EducationalHistoryMarineSuffixService } from './educational-history-marine-suffix.service';
import {IPersonMarineSuffix, PersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {Principal} from "app/core/auth/principal.service";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import * as persianMoment from 'jalali-moment';
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";

@Component({
    selector: 'mi-educational-history-marine-suffix-update',
    templateUrl: './educational-history-marine-suffix-update.component.html'
})
export class EducationalHistoryMarineSuffixUpdateComponent implements OnInit {
    educationalHistory: IEducationalHistoryMarineSuffix;
    organizationCharts: IOrganizationChartMarineSuffix[];
    isSaving: boolean;
    educationalmodules: IEducationalModuleMarineSuffix[];
    people: IPersonMarineSuffix[];
    targetPeople: IPersonMarineSuffix[];
    mustSendOrgChartId: number;
    currentUserFullName: string;
    currentAccount: any;
    currentPerson: IPersonMarineSuffix;
    isAdmin: boolean = false;

    progress: { percentage: number } = { percentage: 0 }
    file: File;

    validFileTypes: string[] = ["image/gif","image/jpeg","image/jpg","image/png","image/TIFF","image/bmp","application/pdf","application/x-zip-compressed"];
    fileHasError: boolean = true;
    fileMessage: string;
    message: string;
    dateValid: number;
    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected educationalHistoryService: EducationalHistoryMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private principal : Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        private educationalModuleService: EducationalModuleMarineSuffixService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalHistory }) => {
            this.educationalHistory = educationalHistory;
        });
        this.principal.identity().then(account => {

            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                    this.currentPerson = resp.body;
                    this.currentUserFullName = this.currentPerson.fullName;
                    this.loadOrgCharts();
                },
                (res: HttpErrorResponse) => this.onError(res.message));


        });
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
    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationCharts = this.organizationChartService.organizationchartsAll;
            this.findTargetPeople();
            this.handlePeopleList();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationCharts = res.body;
                    this.findTargetPeople();
                    this.handlePeopleList();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    handlePeopleList(){
        const rootId = this.treeUtilities.getRootId(this.organizationCharts ,this.currentPerson.organizationChartId);
        const orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationCharts ,rootId).filter(this.treeUtilities.onlyUnique);

        let criteria = [{
            key: 'organizationChartId.in',
            value: orgIds
        }];

        this.personService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
                //this.fullPeople = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    onEducationalModuleChange($event: IEducationalModuleMarineSuffix){

        this.educationalHistory.learningTimeTheorical = $event.learningTimeTheorical;
        this.educationalHistory.learningTimePractical = $event.learningTimePractical;
        this.educationalHistory.totalTime = (this.educationalHistory.learningTimeTheorical ? +this.educationalHistory.learningTimeTheorical : 0) + (this.educationalHistory.learningTimePractical ? +this.educationalHistory.learningTimePractical : 0);
        this.educationalHistory.educationalModuleName = $event.title;

    }
    previousState() {
        window.history.back();
    }
    findTargetPeople(){
        let organization = this.organizationCharts.find(a => a.id == this.currentPerson.organizationChartId);
        if(organization.parentId > 0) {

            this.mustSendOrgChartId = this.treeUtilities.getRootId(this.organizationCharts ,this.currentPerson.organizationChartId); //organization.parentId;

            //this.mustSendChartId = neededOrgId;
            let criteria = [{
                key: 'organizationChartId.equals',
                value: this.mustSendOrgChartId
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
            this.mustSendOrgChartId = 0;
            this.targetPeople = [];
            this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'پرونده/سابقه آموزشی شما شما پس از ثبت برای کارشناس ارشد آموزش سازمان برای بازبینی ارسال می شود.'));
        }
    }
    setFileData(event, entity, field, isImage) {

        this.fileMessage = "";
        this.fileHasError = true;
        //this.dataUtils.setFileData(event, entity, field, isImage);
        if (event && event.target.files && event.target.files[0]) {
            this.file = event.target.files[0];
            this.validateFile(this.file);
        }
        else{
            this.fileHasError = true;
            this.fileMessage = "انتخاب فایل اجباری است.";
        }
    }
    validateFile(file){
        //file.name.split('.')[file.name.split('.').length-1] == 'rar'
        if(this.validFileTypes.includes(file.type)){
            if((file.size / 1024 / 1024) < 10) {
                this.fileHasError = false;
                this.fileMessage = "فایل معتبر است.";
            }
            else{
                this.fileHasError = true;
                this.fileMessage = "حجم فایل بیش از حد مجاز است.";
            }
        }
        else{
            this.fileHasError = true;
            this.fileMessage = "فرمت فایل غیر مجاز است.";
        }
    }

    save() {

        this.isSaving = true;
        this.currentUserFullName = this.currentPerson.fullName;
        this.message = "";

        if(!this.currentPerson.organizationChartId) {
            this.message = "موقعیت در چارت سازمانی برای شما تنظیم نشده است، لطفا مراتب را با مدیریت سامانه در میان بگذارید."
            this.isSaving = false;
            return;
        }

        if (this.educationalHistory.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalHistoryService.update(this.educationalHistory), true);
        } else {
            if(!this.educationalHistory.personId)
                this.educationalHistory.personId = this.currentPerson.id;
            this.educationalHistory.organizationChartId = this.currentPerson.organizationChartId;
            /*let org = this.organizationCharts.find(a => a.id == this.currentPerson.organizationChartId);
            if(org.parentId > 0)
                this.educationalHistory.status = org.parentId;
            else
                this.educationalHistory.status = 0;*/
            this.educationalHistory.status = this.mustSendOrgChartId;
            this.educationalHistory.requestStatus = RequestStatus.NEW;
            this.educationalHistory.changeStatusUserLogin = this.currentAccount.login;
            this.educationalHistory.conversation = " درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. "
            if (this.educationalHistory.description) {
                this.educationalHistory.conversation += "\n";
                this.educationalHistory.conversation += this.currentUserFullName + ": " + this.educationalHistory.description;
            }


            let formdata: FormData = new FormData();

            formdata.append('file', this.file);
            this.educationalHistoryService.uploadFile(formdata).subscribe(event =>{
                    if(event.type === HttpEventType.UploadProgress){
                        this.progress.percentage = Math.round(100 * event.loaded / event.total);
                    }
                    else if(event instanceof HttpResponse){
                        if(event.body){
                            this.educationalHistory.fileDoc = event.body;
                            this.subscribeToSaveResponse(this.educationalHistoryService.create(this.educationalHistory));
                        }
                    }
                },
                () => this.onSaveError());
        }
    }
    updateTotal(){

        this.educationalHistory.totalTime = (this.educationalHistory.learningTimeTheorical ? +this.educationalHistory.learningTimeTheorical : 0) + (this.educationalHistory.learningTimePractical ? +this.educationalHistory.learningTimePractical : 0);
    }
    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalHistoryMarineSuffix>>, isEdit:boolean = false) {
        result.subscribe(
            (res: HttpResponse<IEducationalHistoryMarineSuffix>) => this.onSaveSuccess(res.body, isEdit),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess(res: IEducationalHistoryMarineSuffix, isEdit:boolean = false) {

        if(!isEdit) {
            if(res.status == 0){
                res.conversation += "\n ------------------------------------- \n";
                res.conversation += " تایید درخواست توسط " + this.currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                this.educationalHistoryService.finalize(res).subscribe(
                    (res: HttpResponse<IEducationalHistoryMarineSuffix>) => res,
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

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }
    /*checkNumber(event){

        if(isNaN(event.key)) {
            event.target.value = 0;
        }
    }*/
    validateKeyStrokes(event) {
        let charCode = (event.which) ? event.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    checkDateValidation(event){

        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.dateValid = 1;
            }
            else {
                this.dateValid = 2;
            }
        }
        catch (e) {
            this.dateValid = 2;
        }
    }
}
