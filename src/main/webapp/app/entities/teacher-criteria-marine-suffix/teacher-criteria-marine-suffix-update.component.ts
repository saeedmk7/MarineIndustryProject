import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';
import { TeacherCriteriaMarineSuffixService } from './teacher-criteria-marine-suffix.service';
import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';
import { TeacherCriteriaGroupMarineSuffixService } from 'app/entities/teacher-criteria-group-marine-suffix';

@Component({
    selector: 'mi-teacher-criteria-marine-suffix-update',
    templateUrl: './teacher-criteria-marine-suffix-update.component.html'
})
export class TeacherCriteriaMarineSuffixUpdateComponent implements OnInit {
    teacherCriteria: ITeacherCriteriaMarineSuffix;
    isSaving: boolean;

    teachercriteriagroups: ITeacherCriteriaGroupMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected teacherCriteriaGroupService: TeacherCriteriaGroupMarineSuffixService,
        public router: Router,
        protected jhiAlertService: JhiAlertService,
        protected teacherCriteriaService: TeacherCriteriaMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teacherCriteria }) => {
            this.teacherCriteria = teacherCriteria;
            this.createDate = this.teacherCriteria.createDate != null ? this.teacherCriteria.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.teacherCriteria.modifyDate != null ? this.teacherCriteria.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.teacherCriteriaGroupService.query().subscribe(
            (res: HttpResponse<ITeacherCriteriaGroupMarineSuffix[]>) => {
                this.teachercriteriagroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    change(i) {
        this.router.navigateByUrl(i);
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTeacherCriteriaGroupById(index: number, item: ITeacherCriteriaGroupMarineSuffix) {
        return item.id;
    }
}
