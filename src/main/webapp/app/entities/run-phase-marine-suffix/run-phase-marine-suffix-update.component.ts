import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
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
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {Principal} from "app/core";
import {RunningStepMarineSuffixService} from "app/entities/running-step-marine-suffix";
import {IRunningStepMarineSuffix} from "app/shared/model/running-step-marine-suffix.model";
import {
    IRunPhaseTabModel,
    RunPhaseTabModel
} from "app/entities/run-phase-marine-suffix/run-phase-marine-suffix-tab.model";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {RunPhaseItemModel} from "app/entities/run-phase-marine-suffix/run-phase-marine-suffix-item.model";
import {RunRunningStepMarineSuffixService} from "app/entities/run-running-step-marine-suffix";
import * as $ from 'jquery';
import {
    IRunPhaseSaveDataModel,
    RunPhaseSaveDataModel
} from "app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data.model";
import {
    IRunPhaseSaveDataItemModel,
    RunPhaseSaveDataItemModel
} from "app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data-item.model";
import {IRunRunningStepMarineSuffix} from "app/shared/model/run-running-step-marine-suffix.model";

@Component({
    selector: 'mi-run-phase-marine-suffix-update',
    templateUrl: './run-phase-marine-suffix-update.component.html',
    styleUrls:['./run-phase-marine-suffix.component.scss']
})
export class RunPhaseMarineSuffixUpdateComponent implements OnInit {
    private _runPhase: IRunPhaseMarineSuffix;
    isSaving: boolean;
    educationalModule: IEducationalModuleMarineSuffix = {};
    people: IPersonMarineSuffix[];
    runningSteps: IRunningStepMarineSuffix[];
    finalniazsanjireport: IFinalNiazsanjiReportMarineSuffix;
    finalniazsanjireportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];
    runPhaseTabModel: IRunPhaseTabModel[] = [];
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
        private runningStepService: RunningStepMarineSuffixService,
        private runRunningStepMarineSuffixService: RunRunningStepMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private treeUtilities: TreeUtilities,
        private router: Router
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
                    criteria: criteriaRun,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IRunPhaseMarineSuffix[]>) => {

                if (resp.body.length > 0) {
                    this.runPhase =  resp.body[0];
                    debugger;
                    this.documentUrl = 'document-marine-suffix/runphase/' + this.runPhase.id;
                }


                this.runningStepService.query().subscribe(
                    (resp: HttpResponse<IRunningStepMarineSuffix[]>) => {
                        this.runningSteps = resp.body;
                        debugger;
                        let stepNumbers = resp.body.map(a => a.stepNumber).filter(this.treeUtilities.onlyUnique);
                        stepNumbers.forEach(a => {
                            debugger;
                            let tab: RunPhaseTabModel = new RunPhaseTabModel();
                            tab.id = "tab" + a;
                            tab.title = "گام" + a;
                            tab.href = "#" + tab.id;
                            tab.active = a == 1;
                            tab.runPhaseItems = [];

                            let runningsForThisStep = this.runningSteps.filter(w => w.stepNumber == a);
                            runningsForThisStep.forEach(e => {
                                debugger;
                                let runPhaseItem: RunPhaseItemModel = new RunPhaseItemModel();
                                runPhaseItem.id = e.id;
                                runPhaseItem.title = e.title + (e.stepRequired ? "(اجباریست)" : "");
                                runPhaseItem.description = e.description;
                                runPhaseItem.required = e.stepRequired;
                                runPhaseItem.stepNumber = a;

                                tab.runPhaseItems.push(runPhaseItem);
                            });

                            this.runPhaseTabModel.push(tab);
                        })

                        if(this.runPhase.id != undefined) {
                            const criteria1 = [{
                                key: 'runPhaseId.equals',
                                value: this.runPhase.id
                            }];
                            this.runRunningStepMarineSuffixService.query({
                                page: 0,
                                size: 20000,
                                criteria: criteria1,
                                sort: ["id", "asc"]
                            }).subscribe(
                                (response: HttpResponse<IRunRunningStepMarineSuffix[]>) => {
                                response.body.forEach(x => {
                                    this.runPhaseTabModel.forEach(w => {
                                       w.runPhaseItems.forEach(r => {
                                           if(r.id == x.runningStepId){
                                               r.descMessage = x.description;
                                               r.checked = x.done;
                                           }
                                       })
                                    });
                                })
                            },
                                (res: HttpErrorResponse) => this.onError(res.message));
                            }
                        //this.runPhaseTabModel = resp.body.map(a => new RunPhaseTabModel(a.id))
                    },
                    (res: HttpErrorResponse) => this.onError(res.message));
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
    change(i) {
        debugger;
        this.router.navigateByUrl(i);
    }

    previousState() {
        window.history.back();
    }
    errorMessage: string;
    finalize(){
        debugger;
        let isValid: boolean = true;
        this.errorMessage = "";
        this.runningSteps.forEach(a => {
            if(a.stepRequired)
            {
                if(!($('#chk-' + a.id).is(":checked")))
                {
                    isValid = false;
                    this.errorMessage += "*مورد " + a.title + " در گام " + a.stepNumber + " اجباریست لطفا قبل از تایید نهایی آن را انجام دهید." + "<br />";
                }
            }
        });
        if(isValid){
            this.runPhase.done = true;
            $('#save-entity').trigger('click');
        }

    }

    save() {
        this.isSaving = true;
        debugger;
        let runPhaseSaveData: IRunPhaseSaveDataModel = new RunPhaseSaveDataModel();
        runPhaseSaveData.runPhaseId = this.runPhase.id;
        runPhaseSaveData.finalNiazsanjiReportId = this.runPhase.finalNiazsanjiReportId;
        runPhaseSaveData.description = this.runPhase.description;
        runPhaseSaveData.done = this.runPhase.done;
        runPhaseSaveData.stepNumber = 1;
        runPhaseSaveData.finalizeCost = this.runPhase.finalizeCost;
        runPhaseSaveData.status = this.runPhase.status == undefined ? 0 : this.runPhase.status;
        runPhaseSaveData.runPhaseSaveDataItemModels = [];
        this.runningSteps.forEach(a => {
            let runPhaseSaveDataItemModel: IRunPhaseSaveDataItemModel = new RunPhaseSaveDataItemModel;
            runPhaseSaveDataItemModel.runningStepId = a.id;
            runPhaseSaveDataItemModel.description = $('#txt-' + a.id).val();
            runPhaseSaveDataItemModel.done = $('#chk-' + a.id).is(":checked");
            runPhaseSaveData.runPhaseSaveDataItemModels.push(runPhaseSaveDataItemModel);
        });

        this.subscribeToSaveResponse(this.runPhaseService.saveRunPhaseModel(runPhaseSaveData));

        /*if (this.runPhase.id !== undefined) {
            this.subscribeToSaveResponse(this.runPhaseService.update(this.runPhase));
        } else {
            this.subscribeToSaveResponse(this.runPhaseService.create(this.runPhase));
        }*/
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
