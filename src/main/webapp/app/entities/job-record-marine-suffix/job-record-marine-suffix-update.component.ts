import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IJobRecordMarineSuffix } from 'app/shared/model/job-record-marine-suffix.model';
import { JobRecordMarineSuffixService } from './job-record-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import * as persianMoment from 'jalali-moment';

@Component({
    selector: 'mi-job-record-marine-suffix-update',
    templateUrl: './job-record-marine-suffix-update.component.html'
})
export class JobRecordMarineSuffixUpdateComponent implements OnInit {
    jobRecord: IJobRecordMarineSuffix;
    isSaving: boolean;

    people: IPersonMarineSuffix[];
    person: IPersonMarineSuffix;
    dateStartDateValid: number;
    dateEndDateValid: number;
    /*createDate: string;
    modifyDate: string;*/

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected jobRecordService: JobRecordMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jobRecord }) => {

            this.jobRecord = jobRecord;
            if(!this.jobRecord.personId)
            {
                let criteria = [{
                    key: 'guid.equals',
                    value: this.jobRecord.personGuid
                }];
                this.personService.query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.person = resp.body[0];
                    this.jobRecord.personId = this.person.id;
                });
            }
            else{
                this.personService.find(this.jobRecord.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                   this.person = resp.body;
                });
            }
        });
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    checkDateValidation(event, dateType){

        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                if(dateType == 1)
                    this.dateStartDateValid = 1;
                else
                    this.dateEndDateValid = 1;
            }
            else {
                if(dateType == 1)
                    this.dateStartDateValid = 2;
                else
                    this.dateEndDateValid = 2;
            }
        }
        catch (e) {
            if(dateType == 1)
                this.dateStartDateValid = 2;
            else
                this.dateEndDateValid = 2;
        }
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

        /*this.jobRecord.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.jobRecord.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;*/
        if (this.jobRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.jobRecordService.update(this.jobRecord));
        } else {
            this.subscribeToSaveResponse(this.jobRecordService.create(this.jobRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobRecordMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IJobRecordMarineSuffix>) => this.onSaveSuccess(),
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
}
