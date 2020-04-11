import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { INiazsanjiPersonCriteriaMarineSuffix } from 'app/shared/model/niazsanji-person-criteria-marine-suffix.model';
import { NiazsanjiPersonCriteriaMarineSuffixService } from './niazsanji-person-criteria-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-person-criteria-marine-suffix-update',
    templateUrl: './niazsanji-person-criteria-marine-suffix-update.component.html'
})
export class NiazsanjiPersonCriteriaMarineSuffixUpdateComponent implements OnInit {
    niazsanjiPersonCriteria: INiazsanjiPersonCriteriaMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        protected niazsanjiPersonCriteriaService: NiazsanjiPersonCriteriaMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ niazsanjiPersonCriteria }) => {
            this.niazsanjiPersonCriteria = niazsanjiPersonCriteria;
            this.createDate =
                this.niazsanjiPersonCriteria.createDate != null ? this.niazsanjiPersonCriteria.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.niazsanjiPersonCriteria.modifyDate != null ? this.niazsanjiPersonCriteria.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.niazsanjiPersonCriteria.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.niazsanjiPersonCriteria.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.niazsanjiPersonCriteria.id !== undefined) {
            this.subscribeToSaveResponse(this.niazsanjiPersonCriteriaService.update(this.niazsanjiPersonCriteria));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiPersonCriteriaService.create(this.niazsanjiPersonCriteria));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiPersonCriteriaMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiPersonCriteriaMarineSuffix>) => this.onSaveSuccess(),
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
