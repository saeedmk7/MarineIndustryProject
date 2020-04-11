import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILevelThreeCriteriaGroupMarineSuffix } from 'app/shared/model/level-three-criteria-group-marine-suffix.model';
import { LevelThreeCriteriaGroupMarineSuffixService } from './level-three-criteria-group-marine-suffix.service';

@Component({
    selector: 'mi-level-three-criteria-group-marine-suffix-update',
    templateUrl: './level-three-criteria-group-marine-suffix-update.component.html'
})
export class LevelThreeCriteriaGroupMarineSuffixUpdateComponent implements OnInit {
    levelThreeCriteriaGroup: ILevelThreeCriteriaGroupMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(
        protected levelThreeCriteriaGroupService: LevelThreeCriteriaGroupMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ levelThreeCriteriaGroup }) => {
            this.levelThreeCriteriaGroup = levelThreeCriteriaGroup;
            this.createDate =
                this.levelThreeCriteriaGroup.createDate != null ? this.levelThreeCriteriaGroup.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.levelThreeCriteriaGroup.modifyDate != null ? this.levelThreeCriteriaGroup.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.levelThreeCriteriaGroup.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.levelThreeCriteriaGroup.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.levelThreeCriteriaGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.levelThreeCriteriaGroupService.update(this.levelThreeCriteriaGroup));
        } else {
            this.subscribeToSaveResponse(this.levelThreeCriteriaGroupService.create(this.levelThreeCriteriaGroup));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelThreeCriteriaGroupMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ILevelThreeCriteriaGroupMarineSuffix>) => this.onSaveSuccess(),
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
