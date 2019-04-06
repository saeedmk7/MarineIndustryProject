import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IScoreItemMarineSuffix } from 'app/shared/model/score-item-marine-suffix.model';
import { ScoreItemMarineSuffixService } from './score-item-marine-suffix.service';

@Component({
    selector: 'mi-score-item-marine-suffix-update',
    templateUrl: './score-item-marine-suffix-update.component.html'
})
export class ScoreItemMarineSuffixUpdateComponent implements OnInit {
    private _scoreItem: IScoreItemMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private scoreItemService: ScoreItemMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ scoreItem }) => {
            this.scoreItem = scoreItem;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.scoreItem.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.scoreItem.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.scoreItem.id !== undefined) {
            this.subscribeToSaveResponse(this.scoreItemService.update(this.scoreItem));
        } else {
            this.subscribeToSaveResponse(this.scoreItemService.create(this.scoreItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IScoreItemMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IScoreItemMarineSuffix>) => this.onSaveSuccess(),
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
    get scoreItem() {
        return this._scoreItem;
    }

    set scoreItem(scoreItem: IScoreItemMarineSuffix) {
        this._scoreItem = scoreItem;
        this.createDate = moment(scoreItem.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(scoreItem.modifyDate).format(DATE_TIME_FORMAT);
    }
}
