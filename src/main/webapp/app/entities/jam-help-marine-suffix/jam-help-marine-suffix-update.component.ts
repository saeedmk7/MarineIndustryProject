import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiDataUtils } from 'ng-jhipster';

import { IJamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';
import { JamHelpMarineSuffixService } from './jam-help-marine-suffix.service';

@Component({
    selector: 'mi-jam-help-marine-suffix-update',
    templateUrl: './jam-help-marine-suffix-update.component.html'
})
export class JamHelpMarineSuffixUpdateComponent implements OnInit {
    jamHelp: IJamHelpMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jamHelpService: JamHelpMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jamHelp }) => {
            this.jamHelp = jamHelp;
            this.createDate = this.jamHelp.createDate != null ? this.jamHelp.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.jamHelp.modifyDate != null ? this.jamHelp.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
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
        this.jamHelp.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.jamHelp.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.jamHelp.id !== undefined) {
            this.subscribeToSaveResponse(this.jamHelpService.update(this.jamHelp));
        } else {
            this.subscribeToSaveResponse(this.jamHelpService.create(this.jamHelp));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IJamHelpMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IJamHelpMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
