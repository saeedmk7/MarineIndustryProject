import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';
import { TeacherCriteriaMarineSuffixService } from './teacher-criteria-marine-suffix.service';

@Component({
    selector: 'mi-teacher-criteria-marine-suffix-update',
    templateUrl: './teacher-criteria-marine-suffix-update.component.html'
})
export class TeacherCriteriaMarineSuffixUpdateComponent implements OnInit {
    teacherCriteria: ITeacherCriteriaMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(protected teacherCriteriaService: TeacherCriteriaMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teacherCriteria }) => {
            this.teacherCriteria = teacherCriteria;
            this.createDate = this.teacherCriteria.createDate != null ? this.teacherCriteria.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.teacherCriteria.modifyDate != null ? this.teacherCriteria.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.teacherCriteria.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.teacherCriteria.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.teacherCriteria.id !== undefined) {
            this.subscribeToSaveResponse(this.teacherCriteriaService.update(this.teacherCriteria));
        } else {
            this.subscribeToSaveResponse(this.teacherCriteriaService.create(this.teacherCriteria));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeacherCriteriaMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeacherCriteriaMarineSuffix>) => this.onSaveSuccess(),
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
