import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEducationalRecordMarineSuffix } from 'app/shared/model/educational-record-marine-suffix.model';
import { EducationalRecordMarineSuffixService } from './educational-record-marine-suffix.service';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from 'app/entities/qualification-marine-suffix';
import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { FieldOfStudyMarineSuffixService } from 'app/entities/field-of-study-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";

@Component({
    selector: 'mi-educational-record-marine-suffix-update',
    templateUrl: './educational-record-marine-suffix-update.component.html'
})
export class EducationalRecordMarineSuffixUpdateComponent implements OnInit {
    educationalRecord: IEducationalRecordMarineSuffix;
    isSaving: boolean;

    person: IPersonMarineSuffix;
    qualifications: IQualificationMarineSuffix[];

    fieldofstudies: IFieldOfStudyMarineSuffix[];
    years: any[] = [];
    people: IPersonMarineSuffix[];
    valid: number;
    constructor(
        protected jhiAlertService: JhiAlertService,
        protected educationalRecordService: EducationalRecordMarineSuffixService,
        protected qualificationService: QualificationMarineSuffixService,
        protected fieldOfStudyService: FieldOfStudyMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}
    checkValidation(event){

        try {
            if(event.target.value > 20 || event.target.value < 0)
                this.valid = 2;
            else
                this.valid = 1;
        }
        catch (e) {
            this.valid = 2;
        }
    }
    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalRecord }) => {
            this.educationalRecord = educationalRecord;
            if(!this.educationalRecord.personId)
            {
                let criteria = [{
                    key: 'guid.equals',
                    value: this.educationalRecord.personGuid
                }];
                this.personService.query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.person = resp.body[0];
                    this.educationalRecord.personId = this.person.id;
                });
            }
            else{
                this.personService.find(this.educationalRecord.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.person = resp.body;
                });
            }
        });
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualificationMarineSuffix[]>) => {
                this.qualifications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if(this.fieldOfStudyService.fieldOfStudies)
        {
            this.fieldofstudies = this.fieldOfStudyService.fieldOfStudies;
        }
        else {
            this.fieldOfStudyService.query().subscribe(
                (res: HttpResponse<IFieldOfStudyMarineSuffix[]>) => {
                    this.fieldofstudies = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        for (let i = 1300; i < 1500; i++) {
            this.years.push(i);
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

        if(this.educationalRecord.qualificationId)
            this.educationalRecord.qualificationText = this.qualifications.find(a => a.id == this.educationalRecord.qualificationId).title;
        if(this.educationalRecord.fieldOfStudyId)
            this.educationalRecord.fieldOfStudyText = this.fieldofstudies.find(a => a.id == this.educationalRecord.fieldOfStudyId).title;

        if (this.educationalRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalRecordService.update(this.educationalRecord));
        } else {
            this.subscribeToSaveResponse(this.educationalRecordService.create(this.educationalRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalRecordMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalRecordMarineSuffix>) => this.onSaveSuccess(),
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

    trackQualificationById(index: number, item: IQualificationMarineSuffix) {
        return item.id;
    }

    trackFieldOfStudyById(index: number, item: IFieldOfStudyMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }
}
