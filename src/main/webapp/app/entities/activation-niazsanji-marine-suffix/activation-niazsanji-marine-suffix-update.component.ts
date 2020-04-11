import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IActivationNiazsanjiMarineSuffix } from 'app/shared/model/activation-niazsanji-marine-suffix.model';
import { ActivationNiazsanjiMarineSuffixService } from './activation-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-activation-niazsanji-marine-suffix-update',
    templateUrl: './activation-niazsanji-marine-suffix-update.component.html'
})
export class ActivationNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    activationNiazsanji: IActivationNiazsanjiMarineSuffix;
    isSaving: boolean;
    /*startDate: string;
    endDate: string;
    createDate: string;
    modifyDate: string;*/

    constructor(protected activationNiazsanjiService: ActivationNiazsanjiMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ activationNiazsanji }) => {
            this.activationNiazsanji = activationNiazsanji;
            /*this.startDate =
                this.activationNiazsanji.startDate != null ? this.activationNiazsanji.startDate.format(DATE_TIME_FORMAT) : null;
            this.endDate = this.activationNiazsanji.endDate != null ? this.activationNiazsanji.endDate.format(DATE_TIME_FORMAT) : null;
            this.createDate =
                this.activationNiazsanji.createDate != null ? this.activationNiazsanji.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.activationNiazsanji.modifyDate != null ? this.activationNiazsanji.modifyDate.format(DATE_TIME_FORMAT) : null;*/
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        /*this.activationNiazsanji.startDate = this.startDate != null ? moment(this.startDate, DATE_TIME_FORMAT) : null;
        this.activationNiazsanji.endDate = this.endDate != null ? moment(this.endDate, DATE_TIME_FORMAT) : null;
        this.activationNiazsanji.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.activationNiazsanji.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;*/
        if (this.activationNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.activationNiazsanjiService.update(this.activationNiazsanji));
        } else {
            this.subscribeToSaveResponse(this.activationNiazsanjiService.create(this.activationNiazsanji));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IActivationNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IActivationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
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
}
