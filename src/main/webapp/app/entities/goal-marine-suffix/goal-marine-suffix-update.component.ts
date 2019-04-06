import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';
import { GoalMarineSuffixService } from './goal-marine-suffix.service';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';

@Component({
    selector: 'mi-goal-marine-suffix-update',
    templateUrl: './goal-marine-suffix-update.component.html'
})
export class GoalMarineSuffixUpdateComponent implements OnInit {
    private _goal: IGoalMarineSuffix;
    isSaving: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private goalService: GoalMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ goal }) => {
            this.goal = goal;
        });
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.goal.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.goal.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.goal.id !== undefined) {
            this.subscribeToSaveResponse(this.goalService.update(this.goal));
        } else {
            this.subscribeToSaveResponse(this.goalService.create(this.goal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGoalMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IGoalMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get goal() {
        return this._goal;
    }

    set goal(goal: IGoalMarineSuffix) {
        this._goal = goal;
        this.createDate = moment(goal.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(goal.modifyDate).format(DATE_TIME_FORMAT);
    }
}
