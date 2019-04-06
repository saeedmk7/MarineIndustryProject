import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { RunPhaseMarineSuffixService } from './run-phase-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';

@Component({
    selector: 'mi-run-phase-marine-suffix-update',
    templateUrl: './run-phase-marine-suffix-update.component.html'
})
export class RunPhaseMarineSuffixUpdateComponent implements OnInit {
    private _runPhase: IRunPhaseMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    people: IPersonMarineSuffix[];

    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];
    doneDate: string;
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private runPhaseService: RunPhaseMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ runPhase }) => {
            this.runPhase = runPhase;
        });
        this.documentService.query().subscribe(
            (res: HttpResponse<IDocumentMarineSuffix[]>) => {
                this.documents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.finalNiazsanjiReportService.query().subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {
                this.finalniazsanjireports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.runPhase.doneDate = moment(this.doneDate, DATE_TIME_FORMAT);
        this.runPhase.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.runPhase.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        this.runPhase.archivedDate = moment(this.archivedDate, DATE_TIME_FORMAT);
        if (this.runPhase.id !== undefined) {
            this.subscribeToSaveResponse(this.runPhaseService.update(this.runPhase));
        } else {
            this.subscribeToSaveResponse(this.runPhaseService.create(this.runPhase));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRunPhaseMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRunPhaseMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackFinalNiazsanjiReportById(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
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
    get runPhase() {
        return this._runPhase;
    }

    set runPhase(runPhase: IRunPhaseMarineSuffix) {
        this._runPhase = runPhase;
        this.doneDate = moment(runPhase.doneDate).format(DATE_TIME_FORMAT);
        this.createDate = moment(runPhase.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(runPhase.modifyDate).format(DATE_TIME_FORMAT);
        this.archivedDate = moment(runPhase.archivedDate).format(DATE_TIME_FORMAT);
    }
}
