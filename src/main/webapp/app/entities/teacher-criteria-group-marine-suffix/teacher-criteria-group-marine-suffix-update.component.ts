import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';
import { TeacherCriteriaGroupMarineSuffixService } from './teacher-criteria-group-marine-suffix.service';

@Component({
    selector: 'mi-teacher-criteria-group-marine-suffix-update',
    templateUrl: './teacher-criteria-group-marine-suffix-update.component.html'
})
export class TeacherCriteriaGroupMarineSuffixUpdateComponent implements OnInit {
    teacherCriteriaGroup: ITeacherCriteriaGroupMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(protected teacherCriteriaGroupService: TeacherCriteriaGroupMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teacherCriteriaGroup }) => {
            this.teacherCriteriaGroup = teacherCriteriaGroup;
            this.createDate =
                this.teacherCriteriaGroup.createDate != null ? this.teacherCriteriaGroup.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.teacherCriteriaGroup.modifyDate != null ? this.teacherCriteriaGroup.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.teacherCriteriaGroup.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.teacherCriteriaGroup.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.teacherCriteriaGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.teacherCriteriaGroupService.update(this.teacherCriteriaGroup));
        } else {
            this.subscribeToSaveResponse(this.teacherCriteriaGroupService.create(this.teacherCriteriaGroup));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeacherCriteriaGroupMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeacherCriteriaGroupMarineSuffix>) => this.onSaveSuccess(),
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
