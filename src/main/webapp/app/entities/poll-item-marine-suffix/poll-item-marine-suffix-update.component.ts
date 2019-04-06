import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPollItemMarineSuffix } from 'app/shared/model/poll-item-marine-suffix.model';
import { PollItemMarineSuffixService } from './poll-item-marine-suffix.service';
import { ICriterionMarineSuffix } from 'app/shared/model/criterion-marine-suffix.model';
import { CriterionMarineSuffixService } from 'app/entities/criterion-marine-suffix';

@Component({
    selector: 'mi-poll-item-marine-suffix-update',
    templateUrl: './poll-item-marine-suffix-update.component.html'
})
export class PollItemMarineSuffixUpdateComponent implements OnInit {
    private _pollItem: IPollItemMarineSuffix;
    isSaving: boolean;

    criteria: ICriterionMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private pollItemService: PollItemMarineSuffixService,
        private criterionService: CriterionMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pollItem }) => {
            this.pollItem = pollItem;
        });
        this.criterionService.query().subscribe(
            (res: HttpResponse<ICriterionMarineSuffix[]>) => {
                this.criteria = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pollItem.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.pollItem.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.pollItem.id !== undefined) {
            this.subscribeToSaveResponse(this.pollItemService.update(this.pollItem));
        } else {
            this.subscribeToSaveResponse(this.pollItemService.create(this.pollItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPollItemMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPollItemMarineSuffix>) => this.onSaveSuccess(),
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

    trackCriterionById(index: number, item: ICriterionMarineSuffix) {
        return item.id;
    }
    get pollItem() {
        return this._pollItem;
    }

    set pollItem(pollItem: IPollItemMarineSuffix) {
        this._pollItem = pollItem;
        this.createDate = moment(pollItem.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(pollItem.modifyDate).format(DATE_TIME_FORMAT);
    }
}
