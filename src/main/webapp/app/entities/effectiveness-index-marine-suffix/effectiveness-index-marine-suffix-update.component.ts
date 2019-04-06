import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';
import { EffectivenessIndexMarineSuffixService } from './effectiveness-index-marine-suffix.service';

@Component({
    selector: 'mi-effectiveness-index-marine-suffix-update',
    templateUrl: './effectiveness-index-marine-suffix-update.component.html'
})
export class EffectivenessIndexMarineSuffixUpdateComponent implements OnInit {
    private _effectivenessIndex: IEffectivenessIndexMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private effectivenessIndexService: EffectivenessIndexMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ effectivenessIndex }) => {
            this.effectivenessIndex = effectivenessIndex;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.effectivenessIndex.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.effectivenessIndex.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.effectivenessIndex.id !== undefined) {
            this.subscribeToSaveResponse(this.effectivenessIndexService.update(this.effectivenessIndex));
        } else {
            this.subscribeToSaveResponse(this.effectivenessIndexService.create(this.effectivenessIndex));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEffectivenessIndexMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEffectivenessIndexMarineSuffix>) => this.onSaveSuccess(),
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
    get effectivenessIndex() {
        return this._effectivenessIndex;
    }

    set effectivenessIndex(effectivenessIndex: IEffectivenessIndexMarineSuffix) {
        this._effectivenessIndex = effectivenessIndex;
        this.createDate = moment(effectivenessIndex.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(effectivenessIndex.modifyDate).format(DATE_TIME_FORMAT);
    }
}
