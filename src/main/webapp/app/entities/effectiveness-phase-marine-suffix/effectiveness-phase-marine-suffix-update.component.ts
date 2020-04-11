import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { EffectivenessPhaseMarineSuffixService } from './effectiveness-phase-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { IEffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';
import { EffectivenessPhaseLevelMarineSuffixService } from 'app/entities/effectiveness-phase-level-marine-suffix';

@Component({
    selector: 'mi-effectiveness-phase-marine-suffix-update',
    templateUrl: './effectiveness-phase-marine-suffix-update.component.html'
})
export class EffectivenessPhaseMarineSuffixUpdateComponent implements OnInit {
    effectivenessPhase: IEffectivenessPhaseMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];

    effectivenessphaselevels: IEffectivenessPhaseLevelMarineSuffix[];
    finishPhaseDate: string;
    startPhaseDate: string;
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        protected effectivenessPhaseLevelService: EffectivenessPhaseLevelMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ effectivenessPhase }) => {
            this.effectivenessPhase = effectivenessPhase;
            this.finishPhaseDate =
                this.effectivenessPhase.finishPhaseDate != null ? this.effectivenessPhase.finishPhaseDate.format(DATE_TIME_FORMAT) : null;
            this.startPhaseDate =
                this.effectivenessPhase.startPhaseDate != null ? this.effectivenessPhase.startPhaseDate.format(DATE_TIME_FORMAT) : null;
            this.createDate =
                this.effectivenessPhase.createDate != null ? this.effectivenessPhase.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.effectivenessPhase.modifyDate != null ? this.effectivenessPhase.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.documentService.query().subscribe(
            (res: HttpResponse<IDocumentMarineSuffix[]>) => {
                this.documents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.finalNiazsanjiReportService.query().subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {
                this.finalniazsanjireports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.effectivenessPhaseLevelService.query().subscribe(
            (res: HttpResponse<IEffectivenessPhaseLevelMarineSuffix[]>) => {
                this.effectivenessphaselevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.effectivenessPhase.finishPhaseDate = this.finishPhaseDate != null ? moment(this.finishPhaseDate, DATE_TIME_FORMAT) : null;
        this.effectivenessPhase.startPhaseDate = this.startPhaseDate != null ? moment(this.startPhaseDate, DATE_TIME_FORMAT) : null;
        this.effectivenessPhase.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.effectivenessPhase.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.effectivenessPhase.id !== undefined) {
            this.subscribeToSaveResponse(this.effectivenessPhaseService.update(this.effectivenessPhase));
        } else {
            this.subscribeToSaveResponse(this.effectivenessPhaseService.create(this.effectivenessPhase));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEffectivenessPhaseMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEffectivenessPhaseMarineSuffix>) => this.onSaveSuccess(),
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

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackFinalNiazsanjiReportById(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
    }

    trackEffectivenessPhaseLevelById(index: number, item: IEffectivenessPhaseLevelMarineSuffix) {
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
}
