import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {HttpResponse, HttpErrorResponse, HttpEventType} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';
import { SoldierTrainingReportMarineSuffixService } from './soldier-training-report-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';
import { SoldierMarineSuffixService } from 'app/entities/soldier-marine-suffix';
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {Principal} from "app/core";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {MONTHS} from "app/shared/constants/months.constants";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";

@Component({
    selector: 'mi-soldier-training-report-marine-suffix-update',
    templateUrl: './soldier-training-report-marine-suffix-update.component.html'
})
export class SoldierTrainingReportMarineSuffixUpdateComponent implements OnInit {
    soldierTrainingReport: ISoldierTrainingReportMarineSuffix;
    organizationCharts: IOrganizationChartMarineSuffix[];
    isSaving: boolean;
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

    soldiers: ISoldierMarineSuffix[];

    runMonths: any = MONTHS.sort(function(a,b)
    {
        return (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0);
    });
    years: any = GREGORIAN_START_END_DATE.map(a => a.year);

    uploadingFile: boolean = false;
    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected soldierTrainingReportService: SoldierTrainingReportMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected soldierService: SoldierMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private principal : Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ soldierTrainingReport }) => {
            this.soldierTrainingReport = soldierTrainingReport;
        });
        this.principal.identity().then(account => {

            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
        });
        this.soldierService.query().subscribe(
            (res: HttpResponse<ISoldierMarineSuffix[]>) => {
                this.soldiers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    /*setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }*/

    previousState() {
        window.history.back();
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
    uploadFile() {

        this.uploadingFile = true;
        let formdata: FormData = new FormData();

        formdata.append('file', this.file);
        this.soldierTrainingReportService.uploadFile(formdata).subscribe(event => {
                if (event instanceof HttpResponse) {
                    if (event.body) {
                        debugger;
                        this.soldierTrainingReport.fileDoc = event.body.toString();
                        this.fileMessage = "آپلود فایل با موفقیت انجام شد.";
                    }
                }
            },
            () => {
                this.uploadingFile = false;
                this.fileMessage = "آپلود فایل با خطا روبرو شد. لطفا عملیات را بعدا دوباره تکرار کنید.";
                this.fileHasError = true;
                this.onSaveError()
            });
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
        /*this.soldierTrainingReport.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.soldierTrainingReport.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;*/
        this.soldierTrainingReport.reportTime = 0;
        if (this.soldierTrainingReport.id !== undefined) {
            this.subscribeToSaveResponse(this.soldierTrainingReportService.update(this.soldierTrainingReport));
        } else {
            if(this.file) {
                let formdata: FormData = new FormData();

                formdata.append('file', this.file);
                this.soldierTrainingReportService.uploadFile(formdata).subscribe(event => {
                        if (event.type === HttpEventType.UploadProgress) {
                            this.progress.percentage = Math.round(100 * event.loaded / event.total);
                        }
                        else if (event instanceof HttpResponse) {
                            if (event.body) {
                                this.soldierTrainingReport.fileDoc = event.body;
                                this.subscribeToSaveResponse(this.soldierTrainingReportService.create(this.soldierTrainingReport));
                            }
                        }
                    },
                    () => this.onSaveError());
            }
            else {
                this.subscribeToSaveResponse(this.soldierTrainingReportService.create(this.soldierTrainingReport));
            }
            //this.subscribeToSaveResponse(this.soldierTrainingReportService.create(this.soldierTrainingReport));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISoldierTrainingReportMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ISoldierTrainingReportMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
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

    trackSoldierById(index: number, item: ISoldierMarineSuffix) {
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
