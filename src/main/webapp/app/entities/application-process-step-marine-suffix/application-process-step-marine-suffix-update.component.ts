import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse, HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { IApplicationProcessStepMarineSuffix } from 'app/shared/model/application-process-step-marine-suffix.model';
import { ApplicationProcessStepMarineSuffixService } from './application-process-step-marine-suffix.service';
import { ApplicationProcessMarineSuffixService } from 'app/entities/application-process-marine-suffix';

@Component({
    selector: 'mi-application-process-step-marine-suffix-update',
    templateUrl: './application-process-step-marine-suffix-update.component.html'
})
export class ApplicationProcessStepMarineSuffixUpdateComponent implements OnInit {
    applicationProcessStep: IApplicationProcessStepMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    progress: { percentage: number } = { percentage: 0 };
    file: File;

    validFileTypes: string[] = [
        'image/gif',
        'image/jpeg',
        'image/jpg',
        'image/png',
        'image/TIFF',
        'image/bmp',
        'application/pdf',
        'application/x-zip-compressed'
    ];
    fileHasError: boolean = true;
    fileMessage: string;
    message: string;
    uploadingFile: boolean = false;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected applicationProcessStepService: ApplicationProcessStepMarineSuffixService,
        protected applicationProcessService: ApplicationProcessMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ applicationProcessStep }) => {
            this.applicationProcessStep = applicationProcessStep;
            this.createDate =
                this.applicationProcessStep.createDate != null ? this.applicationProcessStep.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.applicationProcessStep.modifyDate != null ? this.applicationProcessStep.modifyDate.format(DATE_TIME_FORMAT) : null;
            this.archivedDate =
                this.applicationProcessStep.archivedDate != null ? this.applicationProcessStep.archivedDate.format(DATE_TIME_FORMAT) : null;
        });
    }
    setFileData(event, entity, field, isImage) {
        this.fileMessage = '';
        this.fileHasError = true;
        //this.dataUtils.setFileData(event, entity, field, isImage);
        if (event && event.target.files && event.target.files[0]) {
            this.file = event.target.files[0];
            this.validateFile(this.file);
        } else {
            this.fileHasError = true;
            this.fileMessage = 'انتخاب فایل اجباری است.';
        }
    }

    uploadFile() {
        this.uploadingFile = true;
        let formdata: FormData = new FormData();

        formdata.append('file', this.file);
        this.applicationProcessService.uploadFile(formdata).subscribe(
            event => {
                if (event instanceof HttpResponse) {
                    if (event.body) {
                        this.applicationProcessStep.fileDoc = event.body.toString();
                        this.fileMessage = 'آپلود فایل با موفقیت انجام شد.';
                    }
                }
            },
            () => {
                this.uploadingFile = false;
                this.fileMessage = 'آپلود فایل با خطا روبرو شد. لطفا عملیات را بعدا دوباره تکرار کنید.';
                this.fileHasError = true;
                this.onSaveError();
            }
        );
    }

    validateFile(file) {
        //file.name.split('.')[file.name.split('.').length-1] == 'rar'
        if (file.size / 1024 / 1024 < 10) {
            this.fileHasError = false;
            this.fileMessage = 'فایل معتبر است.';
        } else {
            this.fileHasError = true;
            this.fileMessage = 'حجم فایل بیش از حد مجاز است.';
        }
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.applicationProcessStep.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.applicationProcessStep.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        this.applicationProcessStep.archivedDate = this.archivedDate != null ? moment(this.archivedDate, DATE_TIME_FORMAT) : null;
        if (this.applicationProcessStep.id !== undefined) {
            this.subscribeToSaveResponse(this.applicationProcessStepService.update(this.applicationProcessStep));
        } else {
            if (this.file) {
                let formdata: FormData = new FormData();

                formdata.append('file', this.file);
                this.applicationProcessService.uploadFile(formdata).subscribe(
                    event => {
                        if (event.type === HttpEventType.UploadProgress) {
                            this.progress.percentage = Math.round(100 * event.loaded / event.total);
                        } else if (event instanceof HttpResponse) {
                            if (event.body) {
                                this.applicationProcessStep.fileDoc = event.body;
                                this.subscribeToSaveResponse(this.applicationProcessStepService.create(this.applicationProcessStep));
                            }
                        }
                    },
                    () => this.onSaveError()
                );
            } else {
                this.subscribeToSaveResponse(this.applicationProcessStepService.create(this.applicationProcessStep));
            }
            this.subscribeToSaveResponse(this.applicationProcessStepService.create(this.applicationProcessStep));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplicationProcessStepMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IApplicationProcessStepMarineSuffix>) => this.onSaveSuccess(),
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
}
