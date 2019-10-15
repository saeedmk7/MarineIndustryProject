import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IResearchRecordMarineSuffix } from 'app/shared/model/research-record-marine-suffix.model';
import { ResearchRecordMarineSuffixService } from './research-record-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';

@Component({
    selector: 'mi-research-record-marine-suffix-update',
    templateUrl: './research-record-marine-suffix-update.component.html'
})
export class ResearchRecordMarineSuffixUpdateComponent implements OnInit {
    researchRecord: IResearchRecordMarineSuffix;
    isSaving: boolean;
    person: IPersonMarineSuffix;
    people: IPersonMarineSuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected researchRecordService: ResearchRecordMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ researchRecord }) => {
            this.researchRecord = researchRecord;
            if(!this.researchRecord.personId)
            {
                let criteria = [{
                    key: 'guid.equals',
                    value: this.researchRecord.personGuid
                }];
                this.personService.query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.person = resp.body[0];
                    this.researchRecord.personId = this.person.id;
                });
            }
            else{
                this.personService.find(this.researchRecord.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
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
        /*this.researchRecord.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.researchRecord.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;*/
        if (this.researchRecord.id !== undefined) {
            this.subscribeToSaveResponse(this.researchRecordService.update(this.researchRecord));
        } else {
            this.subscribeToSaveResponse(this.researchRecordService.create(this.researchRecord));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IResearchRecordMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IResearchRecordMarineSuffix>) => this.onSaveSuccess(),
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
