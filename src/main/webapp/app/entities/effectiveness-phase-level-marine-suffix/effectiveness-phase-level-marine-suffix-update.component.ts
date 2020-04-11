import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';
import { EffectivenessPhaseLevelMarineSuffixService } from './effectiveness-phase-level-marine-suffix.service';
import {EFFECTIVENESSPHASELEVELS} from "app/shared/constants/effectiveness-phase-levels.constants";

@Component({
    selector: 'mi-effectiveness-phase-level-marine-suffix-update',
    templateUrl: './effectiveness-phase-level-marine-suffix-update.component.html'
})
export class EffectivenessPhaseLevelMarineSuffixUpdateComponent implements OnInit {
    effectivenessPhaseLevel: IEffectivenessPhaseLevelMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    effectivenessPhaseLevels: any[] = EFFECTIVENESSPHASELEVELS;
    constructor(
        protected effectivenessPhaseLevelService: EffectivenessPhaseLevelMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ effectivenessPhaseLevel }) => {
            this.effectivenessPhaseLevel = effectivenessPhaseLevel;
            this.createDate =
                this.effectivenessPhaseLevel.createDate != null ? this.effectivenessPhaseLevel.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.effectivenessPhaseLevel.modifyDate != null ? this.effectivenessPhaseLevel.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.effectivenessPhaseLevel.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.effectivenessPhaseLevel.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.effectivenessPhaseLevel.id !== undefined) {
            this.subscribeToSaveResponse(this.effectivenessPhaseLevelService.update(this.effectivenessPhaseLevel));
        } else {
            this.subscribeToSaveResponse(this.effectivenessPhaseLevelService.create(this.effectivenessPhaseLevel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEffectivenessPhaseLevelMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEffectivenessPhaseLevelMarineSuffix>) => this.onSaveSuccess(),
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
