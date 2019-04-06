import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IConditionsOfParticipantMarineSuffix } from 'app/shared/model/conditions-of-participant-marine-suffix.model';
import { ConditionsOfParticipantMarineSuffixService } from './conditions-of-participant-marine-suffix.service';

@Component({
    selector: 'mi-conditions-of-participant-marine-suffix-update',
    templateUrl: './conditions-of-participant-marine-suffix-update.component.html'
})
export class ConditionsOfParticipantMarineSuffixUpdateComponent implements OnInit {
    private _conditionsOfParticipant: IConditionsOfParticipantMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        private conditionsOfParticipantService: ConditionsOfParticipantMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ conditionsOfParticipant }) => {
            this.conditionsOfParticipant = conditionsOfParticipant;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.conditionsOfParticipant.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.conditionsOfParticipant.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.conditionsOfParticipant.id !== undefined) {
            this.subscribeToSaveResponse(this.conditionsOfParticipantService.update(this.conditionsOfParticipant));
        } else {
            this.subscribeToSaveResponse(this.conditionsOfParticipantService.create(this.conditionsOfParticipant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConditionsOfParticipantMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IConditionsOfParticipantMarineSuffix>) => this.onSaveSuccess(),
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
    get conditionsOfParticipant() {
        return this._conditionsOfParticipant;
    }

    set conditionsOfParticipant(conditionsOfParticipant: IConditionsOfParticipantMarineSuffix) {
        this._conditionsOfParticipant = conditionsOfParticipant;
        this.createDate = moment(conditionsOfParticipant.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(conditionsOfParticipant.modifyDate).format(DATE_TIME_FORMAT);
    }
}
