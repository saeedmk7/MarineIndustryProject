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
import {IDesignAndPlanningMarineSuffix} from "app/shared/model/design-and-planning-marine-suffix.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {Principal} from "app/core";

@Component({
    selector: 'mi-run-phase-marine-suffix-update',
    templateUrl: './run-phase-marine-suffix-update.component.html'
})
export class RunPhaseMarineSuffixUpdateComponent implements OnInit {
    private _runPhase: IRunPhaseMarineSuffix;
    isSaving: boolean;
    educationalModule: IEducationalModuleMarineSuffix = {};
    people: IPersonMarineSuffix[];
    finalniazsanjireport: IFinalNiazsanjiReportMarineSuffix;
    finalniazsanjireportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];

    isAdmin: boolean = false;
    isModirKolAmozesh: boolean;
    isKarshenasArshadAmozesh: boolean;
    currentAccount: any;

    documentUrl: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private runPhaseService: RunPhaseMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ runPhase }) => {
            this.runPhase = runPhase;
            const criteriaRun = [{
                key: 'finalNiazsanjiReportId.equals',
                value: this.runPhase.finalNiazsanjiReportId
            }];
            this.runPhaseService
                .query({
                    page: 0,
                    size: 20000,
                    criteriaRun,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IRunPhaseMarineSuffix[]>) => {

                if (resp.body.length > 0) {
                    this.runPhase =  resp.body[0];
                    debugger;
                    this.documentUrl = 'document-marine-suffix/runphase/' + this.runPhase.id;
                }
            });
            this.finalNiazsanjiReportService.find(this.runPhase.finalNiazsanjiReportId).subscribe(
                (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => {

                    this.finalniazsanjireport = res.body;

                    this.educationalModuleService.find(this.finalniazsanjireport.educationalModuleId).subscribe(
                        (res: HttpResponse<IEducationalModuleMarineSuffix>) => {

                            this.educationalModule = res.body;
                        },
                        (res: HttpErrorResponse) => this.onError(res.message));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );

            const criteria = [{
                key: 'finalNiazsanjiReportId.equals',
                value: this.runPhase.finalNiazsanjiReportId
            }];
            this.finalNiazsanjiReportPersonService.query({
                page: 0,
                size: 20000,
                criteria: criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {
                    this.finalniazsanjireportPeople = resp.body;
                    /*if (resp.body.length > 0) {
                        const personIds = resp.body.map(a => a.personId);
                        const criteria1 = [{
                            key: 'id.in',
                            value: personIds
                        }];
                        this.personService.query({
                            page: 0,
                            size: 20000,
                            criteria: criteria1,
                            sort: ["id", "asc"]
                        }).subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                                this.people = res.body;
                            },
                            (res: HttpErrorResponse) => this.onError(res.message));
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message))

        });

        this.principal.identity().then(account => {
            this.currentAccount = account;
            if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }
        });
        /*this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
        /*this.finalNiazsanjiReportService.query().subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {
                this.finalniazsanjireports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

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
    }
}
