import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { FinalNiazsanjiReportPersonMarineSuffixService } from './final-niazsanji-report-person-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-final-niazsanji-report-person-marine-suffix-update',
    templateUrl: './final-niazsanji-report-person-marine-suffix-update.component.html'
})
export class FinalNiazsanjiReportPersonMarineSuffixUpdateComponent implements OnInit {
    private _finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix;
    isSaving: boolean;

    people: IPersonMarineSuffix[];

    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReportPerson }) => {
            this.finalNiazsanjiReportPerson = finalNiazsanjiReportPerson;
            this.activatedRoute.params.subscribe((params) => {
                this.finalNiazsanjiReportPerson.finalNiazsanjiReportId = params['finalNiazsanjiReportId'];

            });
        });
        if(this.personService.people){
            this.people = this.convertObjectDatesService.goClone(this.personService.people);
        }
        else {
            this.personService.query().subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.finalNiazsanjiReportService.query().subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {
                this.finalniazsanjireports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

        this.finalNiazsanjiReportPerson.status = 0;
        if(!this.finalNiazsanjiReportPerson.sourceId)
        {
            this.finalNiazsanjiReportPerson.sourceId = 0;
        }
        if (this.finalNiazsanjiReportPerson.id !== undefined) {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportPersonService.update(this.finalNiazsanjiReportPerson));
        } else {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportPersonService.create(this.finalNiazsanjiReportPerson));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>) => this.onSaveSuccess(),
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackFinalNiazsanjiReportById(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
    }
    get finalNiazsanjiReportPerson() {
        return this._finalNiazsanjiReportPerson;
    }

    set finalNiazsanjiReportPerson(finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix) {
        this._finalNiazsanjiReportPerson = finalNiazsanjiReportPerson;
        /*this.createDate = moment(finalNiazsanjiReportPerson.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(finalNiazsanjiReportPerson.modifyDate).format(DATE_TIME_FORMAT);
        this.archivedDate = moment(finalNiazsanjiReportPerson.archivedDate).format(DATE_TIME_FORMAT);*/
    }
}
