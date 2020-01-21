import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiIntegrationMarineSuffix } from 'app/shared/model/niazsanji-integration-marine-suffix.model';
import { NiazsanjiIntegrationMarineSuffixService } from './niazsanji-integration-marine-suffix.service';
import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import { PrioritizeRequestNiazsanjiMarineSuffixService } from 'app/entities/prioritize-request-niazsanji-marine-suffix';

@Component({
    selector: 'mi-niazsanji-integration-marine-suffix-update',
    templateUrl: './niazsanji-integration-marine-suffix-update.component.html'
})
export class NiazsanjiIntegrationMarineSuffixUpdateComponent implements OnInit {
    niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix;
    isSaving: boolean;

    prioritizerequestniazsanjis: IPrioritizeRequestNiazsanjiMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected niazsanjiIntegrationService: NiazsanjiIntegrationMarineSuffixService,
        protected prioritizeRequestNiazsanjiService: PrioritizeRequestNiazsanjiMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ niazsanjiIntegration }) => {
            this.niazsanjiIntegration = niazsanjiIntegration;
            this.createDate =
                this.niazsanjiIntegration.createDate != null ? this.niazsanjiIntegration.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.niazsanjiIntegration.modifyDate != null ? this.niazsanjiIntegration.modifyDate.format(DATE_TIME_FORMAT) : null;
            this.archivedDate =
                this.niazsanjiIntegration.archivedDate != null ? this.niazsanjiIntegration.archivedDate.format(DATE_TIME_FORMAT) : null;
        });
        this.prioritizeRequestNiazsanjiService.query().subscribe(
            (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix[]>) => {
                this.prioritizerequestniazsanjis = res.body;
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

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.niazsanjiIntegration.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.niazsanjiIntegration.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        this.niazsanjiIntegration.archivedDate = this.archivedDate != null ? moment(this.archivedDate, DATE_TIME_FORMAT) : null;
        if (this.niazsanjiIntegration.id !== undefined) {
            this.subscribeToSaveResponse(this.niazsanjiIntegrationService.update(this.niazsanjiIntegration));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiIntegrationService.create(this.niazsanjiIntegration));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiIntegrationMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiIntegrationMarineSuffix>) => this.onSaveSuccess(),
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

    trackPrioritizeRequestNiazsanjiById(index: number, item: IPrioritizeRequestNiazsanjiMarineSuffix) {
        return item.id;
    }
}
