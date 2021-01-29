import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { LevelFourEffectivenessMarineSuffixService } from './level-four-effectiveness-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import {
    FinalNiazsanjiReportPersonMarineSuffix,
    IFinalNiazsanjiReportPersonMarineSuffix
} from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { FinalNiazsanjiReportPersonMarineSuffixService } from 'app/entities/final-niazsanji-report-person-marine-suffix';
import {
    FinalNiazsanjiReportMarineSuffix,
    IFinalNiazsanjiReportMarineSuffix
} from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { Principal } from 'app/core';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { ILevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';
import { ILevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';
import { ILevelFourScoreMarineSuffix, LevelFourScoreMarineSuffix } from 'app/shared/model/level-four-score-marine-suffix.model';
import { LevelFourCriteriaMarineSuffixService } from 'app/entities/level-four-criteria-marine-suffix';
import { LevelFourScoreMarineSuffixService } from 'app/entities/level-four-score-marine-suffix';
import * as persianMoment from 'jalali-moment';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { EffectivenessPhaseMarineSuffixService } from 'app/entities/effectiveness-phase-marine-suffix';

@Component({
    selector: 'mi-level-four-effectiveness-marine-suffix-update',
    templateUrl: './level-four-effectiveness-marine-suffix-update.component.html',
    styleUrls: ['./level-four-effectiveness-marine-suffix.scss']
})
export class LevelFourEffectivenessMarineSuffixUpdateComponent implements OnInit {
    levelFourEffectiveness: ILevelFourEffectivenessMarineSuffix;
    isSaving: boolean;
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix = new FinalNiazsanjiReportMarineSuffix();

    finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix = new FinalNiazsanjiReportPersonMarineSuffix();

    levelFourCriterias: ILevelFourCriteriaMarineSuffix[];

    levelFourScores: ILevelFourScoreMarineSuffix[];

    finishDateValidation: number;

    scores: number[] = [5, 4, 3, 2, 1];

    currentAccount: any;
    currentUserFullName: string;
    currentPerson: IPersonMarineSuffix;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;

    runMonths: any = MONTHS.sort(function(a, b) {
        return a.id > b.id ? 1 : b.id > a.id ? -1 : 0;
    });
    years: any = GREGORIAN_START_END_DATE.map(a => a.year);

    effectivenessPhase: IEffectivenessPhaseMarineSuffix;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected levelFourEffectivenessService: LevelFourEffectivenessMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        protected effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected levelFourCriteriaService: LevelFourCriteriaMarineSuffixService,
        protected levelFourScoreService: LevelFourScoreMarineSuffixService,
        private principal: Principal,
        private router: Router,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
        });

        this.activatedRoute.data.subscribe(({ levelFourEffectiveness }) => {
            this.levelFourEffectiveness = levelFourEffectiveness;

            if (this.levelFourEffectiveness.finalNiazsanjiReportPersonId) {
                this.finalNiazsanjiReportPersonService
                    .find(this.levelFourEffectiveness.finalNiazsanjiReportPersonId)
                    .subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>) => {
                        this.finalNiazsanjiReportPerson = resp.body;
                        this.finalNiazsanjiReport = this.finalNiazsanjiReportPerson.finalNiazsanjiReport;
                        const personCriteria = [
                            {
                                key: 'finalNiazsanjiReportPersonId.equals',
                                value: this.levelFourEffectiveness.finalNiazsanjiReportPersonId
                            }
                        ];
                        this.levelFourEffectivenessService
                            .query({
                                page: 0,
                                size: 20000,
                                criteria: personCriteria,
                                sort: ['id', 'asc']
                            })
                            .subscribe(
                                (res: HttpResponse<ILevelFourEffectivenessMarineSuffix[]>) => {
                                    if (res.body && res.body.length > 0) this.levelFourEffectiveness = res.body[0];
                                    this.getScores();
                                },
                                (res: HttpErrorResponse) => this.onError(res.message)
                            );

                        let criteria = [
                            {
                                key: 'finalNiazsanjiReportId.equals',
                                value: this.finalNiazsanjiReport.id
                            },
                            {
                                key: 'effectivenessPhaseLevelEffectivenessLevel.equals',
                                value: 4
                            }
                        ];

                        this.effectivenessPhaseService
                            .query({
                                page: 0,
                                size: 20000,
                                criteria,
                                sort: ['id', 'asc']
                            })
                            .subscribe(
                                (resp: HttpResponse<IEffectivenessPhaseMarineSuffix[]>) => {
                                    if (resp.body && resp.body.length > 0) {
                                        this.effectivenessPhase = resp.body[0];
                                    }
                                },
                                (res: HttpErrorResponse) => this.onError(res.message)
                            );
                    });
            }
        });
    }
    getScores() {
        if (this.levelFourEffectiveness.id !== undefined) {
            const criteria = [
                {
                    key: 'levelFourEffectivenessId.equals',
                    value: this.levelFourEffectiveness.id
                }
            ];
            this.levelFourScoreService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (res: HttpResponse<ILevelFourScoreMarineSuffix[]>) => {
                        this.levelFourScores = res.body;
                        this.buildLevelFourEffectivenessScores(this.levelFourScores, this.levelFourEffectiveness.id);
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.levelFourEffectiveness.evaluateDate = this.convertObjectDatesService.getNowShamsiDate();
            this.levelFourEffectiveness.month = this.convertObjectDatesService.getNowShamsiMonth();
            this.levelFourEffectiveness.year = this.convertObjectDatesService.getNowShamsiYear();
            this.levelFourEffectiveness.finalNiazsanjiReportPersonId = this.finalNiazsanjiReportPerson.id;
            this.buildLevelFourEffectivenessScores([], this.levelFourEffectiveness.id);
        }
    }
    buildLevelFourEffectivenessScores(levelFourScores: ILevelFourScoreMarineSuffix[], gradeId) {
        this.levelFourCriteriaService.query().subscribe(
            (res: HttpResponse<ILevelFourCriteriaMarineSuffix[]>) => {
                this.levelFourCriterias = res.body.sort(
                    (a, b) => (a.displayOrder > b.displayOrder ? 1 : a.displayOrder < b.displayOrder ? -1 : 0)
                );
                this.levelFourEffectiveness.levelFourScores = [];
                this.levelFourCriterias.forEach(w => {
                    const levelFourScore = levelFourScores.find(a => a.levelFourCriteriaId == w.id);
                    if (levelFourScore) {
                        levelFourScore.levelFourCriteriaDescription = w.description;
                        levelFourScore.levelFourBackgroundColor = w.backgroundColor;
                        levelFourScore.levelFourColorText = w.colorText;
                        levelFourScore.levelFourCriteriaWeight = w.weight;
                        this.levelFourEffectiveness.levelFourScores.push(levelFourScore);
                    } else {
                        let levelFourScore: ILevelFourScoreMarineSuffix = new LevelFourScoreMarineSuffix();
                        levelFourScore.isNew = true;
                        levelFourScore.id = Math.floor(Math.random() * 100000000000); //This must change to guid but for now is good //this.commonUtilitiesService.uuidv4();
                        levelFourScore.levelFourEffectivenessId = gradeId;
                        levelFourScore.levelFourCriteriaId = w.id;
                        levelFourScore.levelFourCriteriaTitle = w.title;
                        levelFourScore.levelFourCriteriaDescription = w.description;
                        levelFourScore.levelFourBackgroundColor = w.backgroundColor;
                        levelFourScore.levelFourColorText = w.colorText;
                        levelFourScore.levelFourCriteriaWeight = w.weight;
                        levelFourScore.score = 5;
                        this.levelFourEffectiveness.levelFourScores.push(levelFourScore);
                    }
                });
                this.sumScores();
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    checkValue(id: number, value: number) {
        let a = this.levelFourEffectiveness.levelFourScores.find(a => a.id == id);
        if (a) {
            a.score = value;
        }

        this.sumScores();
    }
    sumScores() {
        const totalScore = this.levelFourEffectiveness.levelFourScores
            .map(a => a.score * a.levelFourCriteriaWeight)
            .reduce((sum, current) => sum + current);
        this.levelFourEffectiveness.totalScore = totalScore;
        const totalWeight = this.levelFourEffectiveness.levelFourScores
            .map(a => a.levelFourCriteriaWeight)
            .reduce((sum, current) => sum + current);
        const generalTotalScores = 5 * totalWeight;
        const totalScorePercent = Math.round(totalScore / generalTotalScores * 100);
        this.levelFourEffectiveness.totalScorePercent = totalScorePercent;

        this.levelFourEffectiveness.grade = this.convertObjectDatesService.calculateGrade(totalScorePercent);
    }

    checkDateValidation(event) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.finishDateValidation = 1;
            } else {
                this.finishDateValidation = 2;
            }
        } catch (e) {
            this.finishDateValidation = 2;
        }
    }
    private setRoles(account: any) {
        if (account) {
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                this.isTopUsers = true;
        }
    }
    change(i) {
        this.router.navigateByUrl(i);
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

        this.levelFourEffectiveness.createDate = undefined;
        this.levelFourEffectiveness.modifyDate = undefined;
        this.levelFourEffectiveness.levelFourScores.forEach(a => {
            if (a.isNew) {
                a.id = undefined;
            }
        });

        if (this.levelFourEffectiveness.id !== undefined) {
            this.subscribeToSaveResponse(this.levelFourEffectivenessService.update(this.levelFourEffectiveness));
        } else {
            this.subscribeToSaveResponse(this.levelFourEffectivenessService.create(this.levelFourEffectiveness));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelFourEffectivenessMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ILevelFourEffectivenessMarineSuffix>) => this.onSaveSuccess(),
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

    trackFinalNiazsanjiReportPersonById(index: number, item: IFinalNiazsanjiReportPersonMarineSuffix) {
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
