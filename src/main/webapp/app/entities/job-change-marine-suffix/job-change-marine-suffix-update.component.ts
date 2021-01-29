import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IJobChangeMarineSuffix } from 'app/shared/model/job-change-marine-suffix.model';
import { JobChangeMarineSuffixService } from './job-change-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from 'app/entities/job-marine-suffix';

@Component({
    selector: 'mi-job-change-marine-suffix-update',
    templateUrl: './job-change-marine-suffix-update.component.html'
})
export class JobChangeMarineSuffixUpdateComponent implements OnInit {
    jobChange: IJobChangeMarineSuffix;
    isSaving: boolean;

    people: IPersonMarineSuffix[];

    jobs: IJobMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected jobChangeService: JobChangeMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected jobService: JobMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jobChange }) => {
            this.jobChange = jobChange;
            this.createDate = this.jobChange.createDate != null ? this.jobChange.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.jobChange.modifyDate != null ? this.jobChange.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.jobChange.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.jobChange.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.jobChange.id !== undefined) {
            this.subscribeToSaveResponse(this.jobChangeService.update(this.jobChange));
        } else {
            this.subscribeToSaveResponse(this.jobChangeService.create(this.jobChange));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobChangeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IJobChangeMarineSuffix>) => this.onSaveSuccess(),
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

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackJobById(index: number, item: IJobMarineSuffix) {
        return item.id;
    }
}
