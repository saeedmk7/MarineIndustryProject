import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';
import { MahiatCourseMarineSuffixService } from './mahiat-course-marine-suffix.service';

@Component({
    selector: 'mi-mahiat-course-marine-suffix-update',
    templateUrl: './mahiat-course-marine-suffix-update.component.html'
})
export class MahiatCourseMarineSuffixUpdateComponent implements OnInit {
    private _mahiatCourse: IMahiatCourseMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private mahiatCourseService: MahiatCourseMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mahiatCourse }) => {
            this.mahiatCourse = mahiatCourse;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.mahiatCourse.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.mahiatCourse.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.mahiatCourse.id !== undefined) {
            this.subscribeToSaveResponse(this.mahiatCourseService.update(this.mahiatCourse));
        } else {
            this.subscribeToSaveResponse(this.mahiatCourseService.create(this.mahiatCourse));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMahiatCourseMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMahiatCourseMarineSuffix>) => this.onSaveSuccess(),
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
    get mahiatCourse() {
        return this._mahiatCourse;
    }

    set mahiatCourse(mahiatCourse: IMahiatCourseMarineSuffix) {
        this._mahiatCourse = mahiatCourse;
        this.createDate = moment(mahiatCourse.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(mahiatCourse.modifyDate).format(DATE_TIME_FORMAT);
    }
}
