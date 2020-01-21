import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from './niazsanji-input-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-input-marine-suffix-update',
    templateUrl: './niazsanji-input-marine-suffix-update.component.html'
})
export class NiazsanjiInputMarineSuffixUpdateComponent implements OnInit {
    niazsanjiInput: INiazsanjiInputMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(protected niazsanjiInputService: NiazsanjiInputMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ niazsanjiInput }) => {
            this.niazsanjiInput = niazsanjiInput;
            this.createDate = this.niazsanjiInput.createDate != null ? this.niazsanjiInput.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.niazsanjiInput.modifyDate != null ? this.niazsanjiInput.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.niazsanjiInput.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.niazsanjiInput.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.niazsanjiInput.id !== undefined) {
            this.subscribeToSaveResponse(this.niazsanjiInputService.update(this.niazsanjiInput));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiInputService.create(this.niazsanjiInput));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiInputMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiInputMarineSuffix>) => this.onSaveSuccess(),
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
