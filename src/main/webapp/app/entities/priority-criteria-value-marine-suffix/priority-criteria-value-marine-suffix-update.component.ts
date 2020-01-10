import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPriorityCriteriaValueMarineSuffix } from 'app/shared/model/priority-criteria-value-marine-suffix.model';
import { PriorityCriteriaValueMarineSuffixService } from './priority-criteria-value-marine-suffix.service';
import { IPriorityCriteriaMarineSuffix } from 'app/shared/model/priority-criteria-marine-suffix.model';
import { PriorityCriteriaMarineSuffixService } from 'app/entities/priority-criteria-marine-suffix';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';
import { PreJobNiazsanjiCompetencyMarineSuffixService } from 'app/entities/pre-job-niazsanji-competency-marine-suffix';

@Component({
    selector: 'mi-priority-criteria-value-marine-suffix-update',
    templateUrl: './priority-criteria-value-marine-suffix-update.component.html'
})
export class PriorityCriteriaValueMarineSuffixUpdateComponent implements OnInit {
    priorityCriteriaValue: IPriorityCriteriaValueMarineSuffix;
    isSaving: boolean;

    prioritycriteria: IPriorityCriteriaMarineSuffix[];

    prejobniazsanjicompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected priorityCriteriaValueService: PriorityCriteriaValueMarineSuffixService,
        protected priorityCriteriaService: PriorityCriteriaMarineSuffixService,
        protected preJobNiazsanjiCompetencyService: PreJobNiazsanjiCompetencyMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ priorityCriteriaValue }) => {
            this.priorityCriteriaValue = priorityCriteriaValue;
            this.createDate =
                this.priorityCriteriaValue.createDate != null ? this.priorityCriteriaValue.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.priorityCriteriaValue.modifyDate != null ? this.priorityCriteriaValue.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.priorityCriteriaService.query().subscribe(
            (res: HttpResponse<IPriorityCriteriaMarineSuffix[]>) => {
                this.prioritycriteria = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.preJobNiazsanjiCompetencyService.query().subscribe(
            (res: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix[]>) => {
                this.prejobniazsanjicompetencies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.priorityCriteriaValue.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.priorityCriteriaValue.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.priorityCriteriaValue.id !== undefined) {
            this.subscribeToSaveResponse(this.priorityCriteriaValueService.update(this.priorityCriteriaValue));
        } else {
            this.subscribeToSaveResponse(this.priorityCriteriaValueService.create(this.priorityCriteriaValue));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPriorityCriteriaValueMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPriorityCriteriaValueMarineSuffix>) => this.onSaveSuccess(),
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

    trackPriorityCriteriaById(index: number, item: IPriorityCriteriaMarineSuffix) {
        return item.id;
    }

    trackPreJobNiazsanjiCompetencyById(index: number, item: IPreJobNiazsanjiCompetencyMarineSuffix) {
        return item.id;
    }
}
