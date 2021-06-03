import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICapitationMarineSuffix } from 'app/shared/model/capitation-marine-suffix.model';
import { CapitationMarineSuffixService } from './capitation-marine-suffix.service';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';

@Component({
    selector: 'mi-capitation-marine-suffix-update',
    templateUrl: './capitation-marine-suffix-update.component.html'
})
export class CapitationMarineSuffixUpdateComponent implements OnInit {
    capitation: ICapitationMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    yearsCollections: any[];
    years: any[];

    constructor(protected capitationService: CapitationMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ capitation }) => {
            this.capitation = capitation;
            this.createDate = this.capitation.createDate != null ? this.capitation.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.capitation.modifyDate != null ? this.capitation.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.yearsCollections = GREGORIAN_START_END_DATE;
        this.years = this.yearsCollections.map(a => a.year);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.capitation.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.capitation.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.capitation.id !== undefined) {
            this.subscribeToSaveResponse(this.capitationService.update(this.capitation));
        } else {
            this.subscribeToSaveResponse(this.capitationService.create(this.capitation));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICapitationMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ICapitationMarineSuffix>) => this.onSaveSuccess(),
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
