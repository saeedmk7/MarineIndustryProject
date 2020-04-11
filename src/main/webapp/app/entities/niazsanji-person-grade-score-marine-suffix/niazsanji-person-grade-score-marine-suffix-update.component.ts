import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model/niazsanji-person-grade-score-marine-suffix.model';
import { NiazsanjiPersonGradeScoreMarineSuffixService } from './niazsanji-person-grade-score-marine-suffix.service';
import { INiazsanjiPersonCriteriaMarineSuffix } from 'app/shared/model/niazsanji-person-criteria-marine-suffix.model';
import { NiazsanjiPersonCriteriaMarineSuffixService } from 'app/entities/niazsanji-person-criteria-marine-suffix';
import { INiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';
import { NiazsanjiPersonGradeMarineSuffixService } from 'app/entities/niazsanji-person-grade-marine-suffix';

@Component({
    selector: 'mi-niazsanji-person-grade-score-marine-suffix-update',
    templateUrl: './niazsanji-person-grade-score-marine-suffix-update.component.html'
})
export class NiazsanjiPersonGradeScoreMarineSuffixUpdateComponent implements OnInit {
    niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix;
    isSaving: boolean;

    niazsanjipersoncriteria: INiazsanjiPersonCriteriaMarineSuffix[];

    niazsanjipersongrades: INiazsanjiPersonGradeMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected niazsanjiPersonGradeScoreService: NiazsanjiPersonGradeScoreMarineSuffixService,
        protected niazsanjiPersonCriteriaService: NiazsanjiPersonCriteriaMarineSuffixService,
        protected niazsanjiPersonGradeService: NiazsanjiPersonGradeMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ niazsanjiPersonGradeScore }) => {
            this.niazsanjiPersonGradeScore = niazsanjiPersonGradeScore;
            this.createDate =
                this.niazsanjiPersonGradeScore.createDate != null
                    ? this.niazsanjiPersonGradeScore.createDate.format(DATE_TIME_FORMAT)
                    : null;
            this.modifyDate =
                this.niazsanjiPersonGradeScore.modifyDate != null
                    ? this.niazsanjiPersonGradeScore.modifyDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.niazsanjiPersonCriteriaService.query().subscribe(
            (res: HttpResponse<INiazsanjiPersonCriteriaMarineSuffix[]>) => {
                this.niazsanjipersoncriteria = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.niazsanjiPersonGradeService.query().subscribe(
            (res: HttpResponse<INiazsanjiPersonGradeMarineSuffix[]>) => {
                this.niazsanjipersongrades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.niazsanjiPersonGradeScore.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.niazsanjiPersonGradeScore.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.niazsanjiPersonGradeScore.id !== undefined) {
            this.subscribeToSaveResponse(this.niazsanjiPersonGradeScoreService.update(this.niazsanjiPersonGradeScore));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiPersonGradeScoreService.create(this.niazsanjiPersonGradeScore));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiPersonGradeScoreMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiPersonGradeScoreMarineSuffix>) => this.onSaveSuccess(),
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

    trackNiazsanjiPersonCriteriaById(index: number, item: INiazsanjiPersonCriteriaMarineSuffix) {
        return item.id;
    }

    trackNiazsanjiPersonGradeById(index: number, item: INiazsanjiPersonGradeMarineSuffix) {
        return item.id;
    }
}
