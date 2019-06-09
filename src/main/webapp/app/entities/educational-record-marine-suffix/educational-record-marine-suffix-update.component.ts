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

@Component({
    selector: 'mi-educational-record-marine-suffix-update',
    templateUrl: './educational-record-marine-suffix-update.component.html'
})
export class EducationalRecordMarineSuffixUpdateComponent implements OnInit {
    educationalRecord: IEducationalRecordMarineSuffix;
    isSaving: boolean;

    qualifications: IQualificationMarineSuffix[];

    fieldofstudies: IFieldOfStudyMarineSuffix[];

    people: IPersonMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected educationalRecordService: EducationalRecordMarineSuffixService,
        protected qualificationService: QualificationMarineSuffixService,
        protected fieldOfStudyService: FieldOfStudyMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalRecord }) => {
            this.educationalRecord = educationalRecord;
            this.createDate = this.educationalRecord.createDate != null ? this.educationalRecord.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.educationalRecord.modifyDate != null ? this.educationalRecord.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualificationMarineSuffix[]>) => {
                this.qualifications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.fieldOfStudyService.query().subscribe(
            (res: HttpResponse<IFieldOfStudyMarineSuffix[]>) => {
                this.fieldofstudies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.educationalRecord.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.educationalRecord.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
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
