import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IJobTypeMarineSuffix } from 'app/shared/model/job-type-marine-suffix.model';
import { JobTypeMarineSuffixService } from './job-type-marine-suffix.service';

@Component({
    selector: 'mi-job-type-marine-suffix-update',
    templateUrl: './job-type-marine-suffix-update.component.html'
})
export class JobTypeMarineSuffixUpdateComponent implements OnInit {
    private _jobType: IJobTypeMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private jobTypeService: JobTypeMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jobType }) => {
            this.jobType = jobType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.jobType.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.jobType.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.jobType.id !== undefined) {
            this.subscribeToSaveResponse(this.jobTypeService.update(this.jobType));
        } else {
            this.subscribeToSaveResponse(this.jobTypeService.create(this.jobType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJobTypeMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IJobTypeMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get jobType() {
        return this._jobType;
    }

    set jobType(jobType: IJobTypeMarineSuffix) {
        this._jobType = jobType;
        this.createDate = moment(jobType.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(jobType.modifyDate).format(DATE_TIME_FORMAT);
    }
}
