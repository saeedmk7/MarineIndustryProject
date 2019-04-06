import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';
import { EffectivenessLevelMarineSuffixService } from './effectiveness-level-marine-suffix.service';

@Component({
    selector: 'mi-effectiveness-level-marine-suffix-update',
    templateUrl: './effectiveness-level-marine-suffix-update.component.html'
})
export class EffectivenessLevelMarineSuffixUpdateComponent implements OnInit {
    private _effectivenessLevel: IEffectivenessLevelMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private effectivenessLevelService: EffectivenessLevelMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ effectivenessLevel }) => {
            this.effectivenessLevel = effectivenessLevel;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.effectivenessLevel.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.effectivenessLevel.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.effectivenessLevel.id !== undefined) {
            this.subscribeToSaveResponse(this.effectivenessLevelService.update(this.effectivenessLevel));
        } else {
            this.subscribeToSaveResponse(this.effectivenessLevelService.create(this.effectivenessLevel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEffectivenessLevelMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEffectivenessLevelMarineSuffix>) => this.onSaveSuccess(),
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
    get effectivenessLevel() {
        return this._effectivenessLevel;
    }

    set effectivenessLevel(effectivenessLevel: IEffectivenessLevelMarineSuffix) {
        this._effectivenessLevel = effectivenessLevel;
        this.createDate = moment(effectivenessLevel.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(effectivenessLevel.modifyDate).format(DATE_TIME_FORMAT);
    }
}
