import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPollScoreMarineSuffix } from 'app/shared/model/poll-score-marine-suffix.model';
import { PollScoreMarineSuffixService } from './poll-score-marine-suffix.service';
import { IPollItemMarineSuffix } from 'app/shared/model/poll-item-marine-suffix.model';
import { PollItemMarineSuffixService } from 'app/entities/poll-item-marine-suffix';
import { IScoreItemMarineSuffix } from 'app/shared/model/score-item-marine-suffix.model';
import { ScoreItemMarineSuffixService } from 'app/entities/score-item-marine-suffix';
import { IPollMarineSuffix } from 'app/shared/model/poll-marine-suffix.model';
import { PollMarineSuffixService } from 'app/entities/poll-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';

@Component({
    selector: 'mi-poll-score-marine-suffix-update',
    templateUrl: './poll-score-marine-suffix-update.component.html'
})
export class PollScoreMarineSuffixUpdateComponent implements OnInit {
    private _pollScore: IPollScoreMarineSuffix;
    isSaving: boolean;

    pollitems: IPollItemMarineSuffix[];

    scoreitems: IScoreItemMarineSuffix[];

    polls: IPollMarineSuffix[];

    people: IPersonMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private pollScoreService: PollScoreMarineSuffixService,
        private pollItemService: PollItemMarineSuffixService,
        private scoreItemService: ScoreItemMarineSuffixService,
        private pollService: PollMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pollScore }) => {
            this.pollScore = pollScore;
        });
        this.pollItemService.query().subscribe(
            (res: HttpResponse<IPollItemMarineSuffix[]>) => {
                this.pollitems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.scoreItemService.query().subscribe(
            (res: HttpResponse<IScoreItemMarineSuffix[]>) => {
                this.scoreitems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.pollService.query().subscribe(
            (res: HttpResponse<IPollMarineSuffix[]>) => {
                this.polls = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.pollScore.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.pollScore.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.pollScore.id !== undefined) {
            this.subscribeToSaveResponse(this.pollScoreService.update(this.pollScore));
        } else {
            this.subscribeToSaveResponse(this.pollScoreService.create(this.pollScore));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPollScoreMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPollScoreMarineSuffix>) => this.onSaveSuccess(),
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

    trackPollItemById(index: number, item: IPollItemMarineSuffix) {
        return item.id;
    }

    trackScoreItemById(index: number, item: IScoreItemMarineSuffix) {
        return item.id;
    }

    trackPollById(index: number, item: IPollMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }
    get pollScore() {
        return this._pollScore;
    }

    set pollScore(pollScore: IPollScoreMarineSuffix) {
        this._pollScore = pollScore;
        this.createDate = moment(pollScore.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(pollScore.modifyDate).format(DATE_TIME_FORMAT);
    }
}
