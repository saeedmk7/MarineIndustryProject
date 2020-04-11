import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';
import { LevelFourCriteriaMarineSuffixService } from './level-four-criteria-marine-suffix.service';

@Component({
    selector: 'mi-level-four-criteria-marine-suffix-update',
    templateUrl: './level-four-criteria-marine-suffix-update.component.html'
})
export class LevelFourCriteriaMarineSuffixUpdateComponent implements OnInit {
    levelFourCriteria: ILevelFourCriteriaMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(protected levelFourCriteriaService: LevelFourCriteriaMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ levelFourCriteria }) => {
            this.levelFourCriteria = levelFourCriteria;
            this.createDate = this.levelFourCriteria.createDate != null ? this.levelFourCriteria.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.levelFourCriteria.modifyDate != null ? this.levelFourCriteria.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.levelFourCriteria.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.levelFourCriteria.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.levelFourCriteria.id !== undefined) {
            this.subscribeToSaveResponse(this.levelFourCriteriaService.update(this.levelFourCriteria));
        } else {
            this.subscribeToSaveResponse(this.levelFourCriteriaService.create(this.levelFourCriteria));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelFourCriteriaMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ILevelFourCriteriaMarineSuffix>) => this.onSaveSuccess(),
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
