import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPriorityCriteriaMarineSuffix } from 'app/shared/model/priority-criteria-marine-suffix.model';
import { PriorityCriteriaMarineSuffixService } from './priority-criteria-marine-suffix.service';

@Component({
    selector: 'mi-priority-criteria-marine-suffix-update',
    templateUrl: './priority-criteria-marine-suffix-update.component.html'
})
export class PriorityCriteriaMarineSuffixUpdateComponent implements OnInit {
    priorityCriteria: IPriorityCriteriaMarineSuffix;
    isSaving: boolean;

    constructor(protected priorityCriteriaService: PriorityCriteriaMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ priorityCriteria }) => {
            this.priorityCriteria = priorityCriteria;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.priorityCriteria.id !== undefined) {
            this.subscribeToSaveResponse(this.priorityCriteriaService.update(this.priorityCriteria));
        } else {
            this.subscribeToSaveResponse(this.priorityCriteriaService.create(this.priorityCriteria));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPriorityCriteriaMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPriorityCriteriaMarineSuffix>) => this.onSaveSuccess(),
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
