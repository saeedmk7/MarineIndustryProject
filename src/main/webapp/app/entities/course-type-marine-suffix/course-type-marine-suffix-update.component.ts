import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from './course-type-marine-suffix.service';

@Component({
    selector: 'mi-course-type-marine-suffix-update',
    templateUrl: './course-type-marine-suffix-update.component.html'
})
export class CourseTypeMarineSuffixUpdateComponent implements OnInit {
    private _courseType: ICourseTypeMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private courseTypeService: CourseTypeMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ courseType }) => {
            this.courseType = courseType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.courseType.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.courseType.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.courseType.id !== undefined) {
            this.subscribeToSaveResponse(this.courseTypeService.update(this.courseType));
        } else {
            this.subscribeToSaveResponse(this.courseTypeService.create(this.courseType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICourseTypeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get courseType() {
        return this._courseType;
    }

    set courseType(courseType: ICourseTypeMarineSuffix) {
        this._courseType = courseType;
        this.createDate = moment(courseType.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(courseType.modifyDate).format(DATE_TIME_FORMAT);
    }
}
