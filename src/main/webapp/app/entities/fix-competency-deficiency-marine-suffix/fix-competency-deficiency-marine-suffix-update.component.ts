import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFixCompetencyDeficiencyMarineSuffix } from 'app/shared/model/fix-competency-deficiency-marine-suffix.model';
import { FixCompetencyDeficiencyMarineSuffixService } from './fix-competency-deficiency-marine-suffix.service';

@Component({
    selector: 'mi-fix-competency-deficiency-marine-suffix-update',
    templateUrl: './fix-competency-deficiency-marine-suffix-update.component.html'
})
export class FixCompetencyDeficiencyMarineSuffixUpdateComponent implements OnInit {
    fixCompetencyDeficiency: IFixCompetencyDeficiencyMarineSuffix;
    isSaving: boolean;

    constructor(
        protected fixCompetencyDeficiencyService: FixCompetencyDeficiencyMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fixCompetencyDeficiency }) => {
            this.fixCompetencyDeficiency = fixCompetencyDeficiency;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fixCompetencyDeficiency.id !== undefined) {
            this.subscribeToSaveResponse(this.fixCompetencyDeficiencyService.update(this.fixCompetencyDeficiency));
        } else {
            this.subscribeToSaveResponse(this.fixCompetencyDeficiencyService.create(this.fixCompetencyDeficiency));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFixCompetencyDeficiencyMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFixCompetencyDeficiencyMarineSuffix>) => this.onSaveSuccess(),
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
