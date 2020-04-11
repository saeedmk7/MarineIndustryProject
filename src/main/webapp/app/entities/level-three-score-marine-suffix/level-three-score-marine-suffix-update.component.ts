import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ILevelThreeScoreMarineSuffix } from 'app/shared/model/level-three-score-marine-suffix.model';
import { LevelThreeScoreMarineSuffixService } from './level-three-score-marine-suffix.service';
import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';
import { LevelThreeCriteriaMarineSuffixService } from 'app/entities/level-three-criteria-marine-suffix';
import { ILevelThreeEffectivenessMarineSuffix } from 'app/shared/model/level-three-effectiveness-marine-suffix.model';
import { LevelThreeEffectivenessMarineSuffixService } from 'app/entities/level-three-effectiveness-marine-suffix';

@Component({
    selector: 'mi-level-three-score-marine-suffix-update',
    templateUrl: './level-three-score-marine-suffix-update.component.html'
})
export class LevelThreeScoreMarineSuffixUpdateComponent implements OnInit {
    levelThreeScore: ILevelThreeScoreMarineSuffix;
    isSaving: boolean;

    levelthreecriteria: ILevelThreeCriteriaMarineSuffix[];

    levelthreeeffectivenesses: ILevelThreeEffectivenessMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected levelThreeScoreService: LevelThreeScoreMarineSuffixService,
        protected levelThreeCriteriaService: LevelThreeCriteriaMarineSuffixService,
        protected levelThreeEffectivenessService: LevelThreeEffectivenessMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ levelThreeScore }) => {
            this.levelThreeScore = levelThreeScore;
            this.createDate = this.levelThreeScore.createDate != null ? this.levelThreeScore.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.levelThreeScore.modifyDate != null ? this.levelThreeScore.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.levelThreeCriteriaService.query().subscribe(
            (res: HttpResponse<ILevelThreeCriteriaMarineSuffix[]>) => {
                this.levelthreecriteria = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.levelThreeEffectivenessService.query().subscribe(
            (res: HttpResponse<ILevelThreeEffectivenessMarineSuffix[]>) => {
                this.levelthreeeffectivenesses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.levelThreeScore.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.levelThreeScore.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.levelThreeScore.id !== undefined) {
            this.subscribeToSaveResponse(this.levelThreeScoreService.update(this.levelThreeScore));
        } else {
            this.subscribeToSaveResponse(this.levelThreeScoreService.create(this.levelThreeScore));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelThreeScoreMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ILevelThreeScoreMarineSuffix>) => this.onSaveSuccess(),
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

    trackLevelThreeCriteriaById(index: number, item: ILevelThreeCriteriaMarineSuffix) {
        return item.id;
    }

    trackLevelThreeEffectivenessById(index: number, item: ILevelThreeEffectivenessMarineSuffix) {
        return item.id;
    }
}
