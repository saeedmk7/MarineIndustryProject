import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IJamHelpAuthorityMarineSuffix } from 'app/shared/model/jam-help-authority-marine-suffix.model';
import { JamHelpAuthorityMarineSuffixService } from './jam-help-authority-marine-suffix.service';
import { IJamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';
import { JamHelpMarineSuffixService } from 'app/entities/jam-help-marine-suffix';

@Component({
    selector: 'mi-jam-help-authority-marine-suffix-update',
    templateUrl: './jam-help-authority-marine-suffix-update.component.html'
})
export class JamHelpAuthorityMarineSuffixUpdateComponent implements OnInit {
    jamHelpAuthority: IJamHelpAuthorityMarineSuffix;
    isSaving: boolean;

    jamhelps: IJamHelpMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected jamHelpAuthorityService: JamHelpAuthorityMarineSuffixService,
        protected jamHelpService: JamHelpMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jamHelpAuthority }) => {
            this.jamHelpAuthority = jamHelpAuthority;
            this.createDate = this.jamHelpAuthority.createDate != null ? this.jamHelpAuthority.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.jamHelpAuthority.modifyDate != null ? this.jamHelpAuthority.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.jamHelpService.query().subscribe(
            (res: HttpResponse<IJamHelpMarineSuffix[]>) => {
                this.jamhelps = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.jamHelpAuthority.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.jamHelpAuthority.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.jamHelpAuthority.id !== undefined) {
            this.subscribeToSaveResponse(this.jamHelpAuthorityService.update(this.jamHelpAuthority));
        } else {
            this.subscribeToSaveResponse(this.jamHelpAuthorityService.create(this.jamHelpAuthority));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IJamHelpAuthorityMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IJamHelpAuthorityMarineSuffix>) => this.onSaveSuccess(),
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

    trackJamHelpById(index: number, item: IJamHelpMarineSuffix) {
        return item.id;
    }
}
