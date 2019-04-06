import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICourseLocationMarineSuffix } from 'app/shared/model/course-location-marine-suffix.model';
import { CourseLocationMarineSuffixService } from './course-location-marine-suffix.service';

@Component({
    selector: 'mi-course-location-marine-suffix-update',
    templateUrl: './course-location-marine-suffix-update.component.html'
})
export class CourseLocationMarineSuffixUpdateComponent implements OnInit {
    private _courseLocation: ICourseLocationMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private courseLocationService: CourseLocationMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ courseLocation }) => {
            this.courseLocation = courseLocation;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.courseLocation.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.courseLocation.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.courseLocation.id !== undefined) {
            this.subscribeToSaveResponse(this.courseLocationService.update(this.courseLocation));
        } else {
            this.subscribeToSaveResponse(this.courseLocationService.create(this.courseLocation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICourseLocationMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ICourseLocationMarineSuffix>) => this.onSaveSuccess(),
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
    get courseLocation() {
        return this._courseLocation;
    }

    set courseLocation(courseLocation: ICourseLocationMarineSuffix) {
        this._courseLocation = courseLocation;
        this.createDate = moment(courseLocation.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(courseLocation.modifyDate).format(DATE_TIME_FORMAT);
    }
}
