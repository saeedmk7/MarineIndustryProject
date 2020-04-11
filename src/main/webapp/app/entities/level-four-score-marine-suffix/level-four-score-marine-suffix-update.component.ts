import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ILevelFourScoreMarineSuffix } from 'app/shared/model/level-four-score-marine-suffix.model';
import { LevelFourScoreMarineSuffixService } from './level-four-score-marine-suffix.service';
import { ILevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';
import { LevelFourCriteriaMarineSuffixService } from 'app/entities/level-four-criteria-marine-suffix';
import { ILevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';
import { LevelFourEffectivenessMarineSuffixService } from 'app/entities/level-four-effectiveness-marine-suffix';

@Component({
    selector: 'mi-level-four-score-marine-suffix-update',
    templateUrl: './level-four-score-marine-suffix-update.component.html'
})
export class LevelFourScoreMarineSuffixUpdateComponent implements OnInit {
    levelFourScore: ILevelFourScoreMarineSuffix;
    isSaving: boolean;

    levelfourcriteria: ILevelFourCriteriaMarineSuffix[];

    levelfoureffectivenesses: ILevelFourEffectivenessMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected levelFourScoreService: LevelFourScoreMarineSuffixService,
        protected levelFourCriteriaService: LevelFourCriteriaMarineSuffixService,
        protected levelFourEffectivenessService: LevelFourEffectivenessMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ levelFourScore }) => {
            this.levelFourScore = levelFourScore;
            this.createDate = this.levelFourScore.createDate != null ? this.levelFourScore.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.levelFourScore.modifyDate != null ? this.levelFourScore.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.levelFourCriteriaService.query().subscribe(
            (res: HttpResponse<ILevelFourCriteriaMarineSuffix[]>) => {
                this.levelfourcriteria = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.levelFourEffectivenessService.query().subscribe(
            (res: HttpResponse<ILevelFourEffectivenessMarineSuffix[]>) => {
                this.levelfoureffectivenesses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.levelFourScore.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.levelFourScore.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.levelFourScore.id !== undefined) {
            this.subscribeToSaveResponse(this.levelFourScoreService.update(this.levelFourScore));
        } else {
            this.subscribeToSaveResponse(this.levelFourScoreService.create(this.levelFourScore));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelFourScoreMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ILevelFourScoreMarineSuffix>) => this.onSaveSuccess(),
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLevelFourCriteriaById(index: number, item: ILevelFourCriteriaMarineSuffix) {
        return item.id;
    }

    trackLevelFourEffectivenessById(index: number, item: ILevelFourEffectivenessMarineSuffix) {
        return item.id;
    }
}
