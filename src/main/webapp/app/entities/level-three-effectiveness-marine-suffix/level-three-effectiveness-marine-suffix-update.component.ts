import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { LevelThreeEffectivenessMarineSuffixService } from './level-three-effectiveness-marine-suffix.service';
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
import { ILevelThreeEffectivenessMarineSuffix } from 'app/shared/model/level-three-effectiveness-marine-suffix.model';
import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';
import { ILevelThreeScoreMarineSuffix, LevelThreeScoreMarineSuffix } from 'app/shared/model/level-three-score-marine-suffix.model';
import { LevelThreeCriteriaMarineSuffixService } from 'app/entities/level-three-criteria-marine-suffix';
import { LevelThreeScoreMarineSuffixService } from 'app/entities/level-three-score-marine-suffix';
import * as persianMoment from 'jalali-moment';
import { EffectivenessPhaseMarineSuffixService } from 'app/entities/effectiveness-phase-marine-suffix';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';

@Component({
    selector: 'mi-level-three-effectiveness-marine-suffix-update',
    templateUrl: './level-three-effectiveness-marine-suffix-update.component.html',
    styleUrls: ['./level-three-effectiveness-marine-suffix.scss']
})
export class LevelThreeEffectivenessMarineSuffixUpdateComponent implements OnInit {
    levelThreeEffectiveness: ILevelThreeEffectivenessMarineSuffix;
    isSaving: boolean;

    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix = new FinalNiazsanjiReportMarineSuffix();

    finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix = new FinalNiazsanjiReportPersonMarineSuffix();

    levelThreeCriterias: ILevelThreeCriteriaMarineSuffix[];

    levelThreeScores: ILevelThreeScoreMarineSuffix[];

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
        protected levelThreeEffectivenessService: LevelThreeEffectivenessMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        protected effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected levelThreeCriteriaService: LevelThreeCriteriaMarineSuffixService,
        protected levelThreeScoreService: LevelThreeScoreMarineSuffixService,
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

        this.activatedRoute.data.subscribe(({ levelThreeEffectiveness }) => {
            this.levelThreeEffectiveness = levelThreeEffectiveness;

            if (this.levelThreeEffectiveness.finalNiazsanjiReportPersonId) {
                this.finalNiazsanjiReportPersonService
                    .find(this.levelThreeEffectiveness.finalNiazsanjiReportPersonId)
                    .subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>) => {
                        this.finalNiazsanjiReportPerson = resp.body;
                        this.finalNiazsanjiReport = this.finalNiazsanjiReportPerson.finalNiazsanjiReport;
                        const personCriteria = [
                            {
                                key: 'finalNiazsanjiReportPersonId.equals',
                                value: this.levelThreeEffectiveness.finalNiazsanjiReportPersonId
                            }
                        ];
                        this.levelThreeEffectivenessService
                            .query({
                                page: 0,
                                size: 20000,
                                criteria: personCriteria,
                                sort: ['id', 'asc']
                            })
                            .subscribe(
                                (res: HttpResponse<ILevelThreeEffectivenessMarineSuffix[]>) => {
                                    if (res.body && res.body.length > 0) this.levelThreeEffectiveness = res.body[0];
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
                                value: 3
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
        if (this.levelThreeEffectiveness.id !== undefined) {
            const criteria = [
                {
                    key: 'levelThreeEffectivenessId.equals',
                    value: this.levelThreeEffectiveness.id
                }
            ];
            this.levelThreeScoreService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (res: HttpResponse<ILevelThreeScoreMarineSuffix[]>) => {
                        this.levelThreeScores = res.body;
                        this.buildLevelThreeEffectivenessScores(this.levelThreeScores, this.levelThreeEffectiveness.id);
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.levelThreeEffectiveness.evaluateDate = this.convertObjectDatesService.getNowShamsiDate();
            this.levelThreeEffectiveness.month = this.convertObjectDatesService.getNowShamsiMonth();
            this.levelThreeEffectiveness.year = this.convertObjectDatesService.getNowShamsiYear();
            this.levelThreeEffectiveness.finalNiazsanjiReportPersonId = this.finalNiazsanjiReportPerson.id;
            this.buildLevelThreeEffectivenessScores([], this.levelThreeEffectiveness.id);
        }
    }
    buildLevelThreeEffectivenessScores(levelThreeScores: ILevelThreeScoreMarineSuffix[], gradeId) {
        const criteria = [
            {
                key: 'mahiatCourseId.equals',
                value: this.finalNiazsanjiReport.mahiatCourseId
            }
        ];
        this.levelThreeCriteriaService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['id', 'asc']
            })
            .subscribe(
                (res: HttpResponse<ILevelThreeCriteriaMarineSuffix[]>) => {
                    this.levelThreeCriterias = res.body.sort(
                        (a, b) => (a.displayOrder > b.displayOrder ? 1 : a.displayOrder < b.displayOrder ? -1 : 0)
                    );
                    this.levelThreeEffectiveness.levelThreeScores = [];
                    this.levelThreeCriterias.forEach(w => {
                        const levelThreeScore = levelThreeScores.find(a => a.levelThreeCriteriaId == w.id);
                        if (levelThreeScore) {
                            levelThreeScore.levelThreeCriteriaDescription = w.description;
                            levelThreeScore.levelThreeBackgroundColor = w.backgroundColor;
                            levelThreeScore.levelThreeColorText = w.colorText;
                            levelThreeScore.levelThreeCriteriaWeight = w.weight;
                            levelThreeScore.levelThreeCriteriaGroupId = w.levelThreeCriteriaGroupId;
                            levelThreeScore.levelThreeCriteriaGroupTitle = w.levelThreeCriteriaGroupTitle;
                            this.levelThreeEffectiveness.levelThreeScores.push(levelThreeScore);
                        } else {
                            let levelThreeScore: ILevelThreeScoreMarineSuffix = new LevelThreeScoreMarineSuffix();
                            levelThreeScore.isNew = true;
                            levelThreeScore.id = Math.floor(Math.random() * 100000000000); //This must change to guid but for now is good //this.commonUtilitiesService.uuidv4();
                            levelThreeScore.levelThreeEffectivenessId = gradeId;
                            levelThreeScore.levelThreeCriteriaId = w.id;
                            levelThreeScore.levelThreeCriteriaTitle = w.title;
                            levelThreeScore.levelThreeCriteriaDescription = w.description;
                            levelThreeScore.levelThreeBackgroundColor = w.backgroundColor;
                            levelThreeScore.levelThreeColorText = w.colorText;
                            levelThreeScore.levelThreeCriteriaWeight = w.weight;
                            levelThreeScore.levelThreeCriteriaGroupId = w.levelThreeCriteriaGroupId;
                            levelThreeScore.levelThreeCriteriaGroupTitle = w.levelThreeCriteriaGroupTitle;
                            levelThreeScore.score = 5;
                            this.levelThreeEffectiveness.levelThreeScores.push(levelThreeScore);
                        }
                    });
                    this.sumScores();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    checkValue(id: number, value: number) {
        let a = this.levelThreeEffectiveness.levelThreeScores.find(a => a.id == id);
        if (a) {
            a.score = value;
        }

        this.sumScores();
    }
    sumScores() {
        const totalScore = this.levelThreeEffectiveness.levelThreeScores
            .map(a => a.score * a.levelThreeCriteriaWeight)
            .reduce((sum, current) => sum + current);
        this.levelThreeEffectiveness.totalScore = totalScore;
        const totalWeight = this.levelThreeEffectiveness.levelThreeScores
            .map(a => a.levelThreeCriteriaWeight)
            .reduce((sum, current) => sum + current);
        const generalTotalScores = 5 * totalWeight;
        const totalScorePercent = Math.round(totalScore / generalTotalScores * 100);
        this.levelThreeEffectiveness.totalScorePercent = totalScorePercent;

        this.levelThreeEffectiveness.grade = this.convertObjectDatesService.calculateGrade(totalScorePercent);
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

        this.levelThreeEffectiveness.createDate = undefined;
        this.levelThreeEffectiveness.modifyDate = undefined;
        this.levelThreeEffectiveness.levelThreeScores.forEach(a => {
            if (a.isNew) {
                a.id = undefined;
            }
        });

        if (this.levelThreeEffectiveness.id !== undefined) {
            this.subscribeToSaveResponse(this.levelThreeEffectivenessService.update(this.levelThreeEffectiveness));
        } else {
            this.subscribeToSaveResponse(this.levelThreeEffectivenessService.create(this.levelThreeEffectiveness));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelThreeEffectivenessMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ILevelThreeEffectivenessMarineSuffix>) => this.onSaveSuccess(),
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
