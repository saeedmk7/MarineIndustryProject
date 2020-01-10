import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';
import { CompetencyMarineSuffixService } from './competency-marine-suffix.service';

@Component({
    selector: 'mi-competency-marine-suffix-update',
    templateUrl: './competency-marine-suffix-update.component.html'
})
export class CompetencyMarineSuffixUpdateComponent implements OnInit {
    competency: ICompetencyMarineSuffix;
    isSaving: boolean;

    constructor(protected competencyService: CompetencyMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ competency }) => {
            this.competency = competency;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.competency.competencyType = 0;
        if (this.competency.id !== undefined) {
            this.subscribeToSaveResponse(this.competencyService.update(this.competency));
        } else {
            this.subscribeToSaveResponse(this.competencyService.create(this.competency));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompetencyMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ICompetencyMarineSuffix>) => this.onSaveSuccess(),
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
