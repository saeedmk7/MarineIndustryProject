import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse, HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { IMatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';
import { MatchingRunningStepMarineSuffixService } from './matching-running-step-marine-suffix.service';
import { MatchingEducationalRecordMarineSuffixService } from 'app/entities/matching-educational-record-marine-suffix';

@Component({
    selector: 'mi-matching-running-step-marine-suffix-update',
    templateUrl: './matching-running-step-marine-suffix-update.component.html'
})
export class MatchingRunningStepMarineSuffixUpdateComponent implements OnInit {
    matchingRunningStep: IMatchingRunningStepMarineSuffix;
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
        protected matchingRunningStepService: MatchingRunningStepMarineSuffixService,
        protected matchingEducationalRecordService: MatchingEducationalRecordMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ matchingRunningStep }) => {
            this.matchingRunningStep = matchingRunningStep;
            this.createDate =
                this.matchingRunningStep.createDate != null ? this.matchingRunningStep.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.matchingRunningStep.modifyDate != null ? this.matchingRunningStep.modifyDate.format(DATE_TIME_FORMAT) : null;
            this.archivedDate =
                this.matchingRunningStep.archivedDate != null ? this.matchingRunningStep.archivedDate.format(DATE_TIME_FORMAT) : null;
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
        this.matchingEducationalRecordService.uploadFile(formdata).subscribe(
            event => {
                if (event instanceof HttpResponse) {
                    if (event.body) {
                        this.matchingRunningStep.fileDoc = event.body.toString();
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.matchingRunningStep.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.matchingRunningStep.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        this.matchingRunningStep.archivedDate = this.archivedDate != null ? moment(this.archivedDate, DATE_TIME_FORMAT) : null;
        if (this.matchingRunningStep.id !== undefined) {
            this.subscribeToSaveResponse(this.matchingRunningStepService.update(this.matchingRunningStep));
        } else {
            if (this.file) {
                let formdata: FormData = new FormData();

                formdata.append('file', this.file);
                this.matchingEducationalRecordService.uploadFile(formdata).subscribe(
                    event => {
                        if (event.type === HttpEventType.UploadProgress) {
                            this.progress.percentage = Math.round(100 * event.loaded / event.total);
                        } else if (event instanceof HttpResponse) {
                            if (event.body) {
                                this.matchingRunningStep.fileDoc = event.body;
                                this.subscribeToSaveResponse(this.matchingRunningStepService.create(this.matchingRunningStep));
                            }
                        }
                    },
                    () => this.onSaveError()
                );
            } else {
                this.subscribeToSaveResponse(this.matchingRunningStepService.create(this.matchingRunningStep));
            }
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatchingRunningStepMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMatchingRunningStepMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    onSaveError() {
        this.isSaving = false;
    }
}
