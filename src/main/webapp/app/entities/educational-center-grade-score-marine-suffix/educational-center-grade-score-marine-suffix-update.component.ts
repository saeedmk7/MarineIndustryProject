import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEducationalCenterGradeScoreMarineSuffix } from 'app/shared/model/educational-center-grade-score-marine-suffix.model';
import { EducationalCenterGradeScoreMarineSuffixService } from './educational-center-grade-score-marine-suffix.service';
import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';
import { EducationalCenterCriteriaMarineSuffixService } from 'app/entities/educational-center-criteria-marine-suffix';
import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';
import { EducationalCenterGradeMarineSuffixService } from 'app/entities/educational-center-grade-marine-suffix';

@Component({
    selector: 'mi-educational-center-grade-score-marine-suffix-update',
    templateUrl: './educational-center-grade-score-marine-suffix-update.component.html'
})
export class EducationalCenterGradeScoreMarineSuffixUpdateComponent implements OnInit {
    educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix;
    isSaving: boolean;

    educationalcentercriteria: IEducationalCenterCriteriaMarineSuffix[];

    educationalcentergrades: IEducationalCenterGradeMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected educationalCenterGradeScoreService: EducationalCenterGradeScoreMarineSuffixService,
        protected educationalCenterCriteriaService: EducationalCenterCriteriaMarineSuffixService,
        protected educationalCenterGradeService: EducationalCenterGradeMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalCenterGradeScore }) => {
            this.educationalCenterGradeScore = educationalCenterGradeScore;
            this.createDate =
                this.educationalCenterGradeScore.createDate != null
                    ? this.educationalCenterGradeScore.createDate.format(DATE_TIME_FORMAT)
                    : null;
            this.modifyDate =
                this.educationalCenterGradeScore.modifyDate != null
                    ? this.educationalCenterGradeScore.modifyDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.educationalCenterCriteriaService.query().subscribe(
            (res: HttpResponse<IEducationalCenterCriteriaMarineSuffix[]>) => {
                this.educationalcentercriteria = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalCenterGradeService.query().subscribe(
            (res: HttpResponse<IEducationalCenterGradeMarineSuffix[]>) => {
                this.educationalcentergrades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.educationalCenterGradeScore.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.educationalCenterGradeScore.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.educationalCenterGradeScore.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalCenterGradeScoreService.update(this.educationalCenterGradeScore));
        } else {
            this.subscribeToSaveResponse(this.educationalCenterGradeScoreService.create(this.educationalCenterGradeScore));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalCenterGradeScoreMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalCenterGradeScoreMarineSuffix>) => this.onSaveSuccess(),
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

    trackEducationalCenterCriteriaById(index: number, item: IEducationalCenterCriteriaMarineSuffix) {
        return item.id;
    }

    trackEducationalCenterGradeById(index: number, item: IEducationalCenterGradeMarineSuffix) {
        return item.id;
    }
}
