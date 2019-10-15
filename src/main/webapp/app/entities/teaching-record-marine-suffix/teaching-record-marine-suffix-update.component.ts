import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITeachingRecordMarineSuffix } from 'app/shared/model/teaching-record-marine-suffix.model';
import { TeachingRecordMarineSuffixService } from './teaching-record-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';

@Component({
    selector: 'mi-teaching-record-marine-suffix-update',
    templateUrl: './teaching-record-marine-suffix-update.component.html'
})
export class TeachingRecordMarineSuffixUpdateComponent implements OnInit {
    teachingRecord: ITeachingRecordMarineSuffix;
    isSaving: boolean;
    person: IPersonMarineSuffix;
    people: IPersonMarineSuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected teachingRecordService: TeachingRecordMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teachingRecord }) => {
            this.teachingRecord = teachingRecord;
            if(!this.teachingRecord.personId)
            {
                let criteria = [{
                    key: 'guid.equals',
                    value: this.teachingRecord.personGuid
                }];
                this.personService.query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.person = resp.body[0];
                    this.teachingRecord.personId = this.person.id;
                });
            }
            else{
                this.personService.find(this.teachingRecord.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.teachingRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.teachingRecordService.update(this.teachingRecord));
        } else {
            this.subscribeToSaveResponse(this.teachingRecordService.create(this.teachingRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeachingRecordMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeachingRecordMarineSuffix>) => this.onSaveSuccess(),
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
