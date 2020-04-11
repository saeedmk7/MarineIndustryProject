import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';
import { TeacherGradeScoreMarineSuffixService } from './teacher-grade-score-marine-suffix.service';
import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';
import { TeacherCriteriaMarineSuffixService } from 'app/entities/teacher-criteria-marine-suffix';
import { ITeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';
import { TeacherGradeMarineSuffixService } from 'app/entities/teacher-grade-marine-suffix';

@Component({
    selector: 'mi-teacher-grade-score-marine-suffix-update',
    templateUrl: './teacher-grade-score-marine-suffix-update.component.html'
})
export class TeacherGradeScoreMarineSuffixUpdateComponent implements OnInit {
    teacherGradeScore: ITeacherGradeScoreMarineSuffix;
    isSaving: boolean;

    teachercriteria: ITeacherCriteriaMarineSuffix[];

    teachergrades: ITeacherGradeMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected teacherGradeScoreService: TeacherGradeScoreMarineSuffixService,
        protected teacherCriteriaService: TeacherCriteriaMarineSuffixService,
        protected teacherGradeService: TeacherGradeMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teacherGradeScore }) => {
            this.teacherGradeScore = teacherGradeScore;
            this.createDate = this.teacherGradeScore.createDate != null ? this.teacherGradeScore.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.teacherGradeScore.modifyDate != null ? this.teacherGradeScore.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.teacherCriteriaService.query().subscribe(
            (res: HttpResponse<ITeacherCriteriaMarineSuffix[]>) => {
                this.teachercriteria = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teacherGradeService.query().subscribe(
            (res: HttpResponse<ITeacherGradeMarineSuffix[]>) => {
                this.teachergrades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.teacherGradeScore.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.teacherGradeScore.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.teacherGradeScore.id !== undefined) {
            this.subscribeToSaveResponse(this.teacherGradeScoreService.update(this.teacherGradeScore));
        } else {
            this.subscribeToSaveResponse(this.teacherGradeScoreService.create(this.teacherGradeScore));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeacherGradeScoreMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeacherGradeScoreMarineSuffix>) => this.onSaveSuccess(),
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

    trackTeacherCriteriaById(index: number, item: ITeacherCriteriaMarineSuffix) {
        return item.id;
    }

    trackTeacherGradeById(index: number, item: ITeacherGradeMarineSuffix) {
        return item.id;
    }
}
