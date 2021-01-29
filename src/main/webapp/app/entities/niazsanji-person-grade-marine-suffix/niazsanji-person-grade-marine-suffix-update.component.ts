import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';
import { NiazsanjiPersonGradeMarineSuffixService } from './niazsanji-person-grade-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import {
    FinalNiazsanjiReportPersonMarineSuffix,
    IFinalNiazsanjiReportPersonMarineSuffix
} from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { FinalNiazsanjiReportPersonMarineSuffixService } from 'app/entities/final-niazsanji-report-person-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { Principal } from 'app/core';
import { INiazsanjiPersonCriteriaMarineSuffix } from 'app/shared/model/niazsanji-person-criteria-marine-suffix.model';
import {
    INiazsanjiPersonGradeScoreMarineSuffix,
    NiazsanjiPersonGradeScoreMarineSuffix
} from 'app/shared/model/niazsanji-person-grade-score-marine-suffix.model';
import { NiazsanjiPersonCriteriaMarineSuffixService } from 'app/entities/niazsanji-person-criteria-marine-suffix';
import { NiazsanjiPersonGradeScoreMarineSuffixService } from 'app/entities/niazsanji-person-grade-score-marine-suffix';
import { Grade } from 'app/shared/model/enums/Grade';
import * as persianMoment from 'jalali-moment';
import {
    FinalNiazsanjiReportMarineSuffix,
    IFinalNiazsanjiReportMarineSuffix
} from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { EffectivenessPhaseMarineSuffixService } from 'app/entities/effectiveness-phase-marine-suffix';

@Component({
    selector: 'mi-niazsanji-person-grade-marine-suffix-update',
    templateUrl: './niazsanji-person-grade-marine-suffix-update.component.html',
    styleUrls: ['./niazsanji-person-grade-marine-suffix.scss']
})
export class NiazsanjiPersonGradeMarineSuffixUpdateComponent implements OnInit {
    niazsanjiPersonGrade: INiazsanjiPersonGradeMarineSuffix;
    isSaving: boolean;

    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix = new FinalNiazsanjiReportMarineSuffix();

    finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix = new FinalNiazsanjiReportPersonMarineSuffix();

    niazsanjiPersonCriterias: INiazsanjiPersonCriteriaMarineSuffix[];

    niazsanjiPersonGradeScores: INiazsanjiPersonGradeScoreMarineSuffix[];

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
        protected niazsanjiPersonGradeService: NiazsanjiPersonGradeMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        protected finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected niazsanjiPersonCriteriaService: NiazsanjiPersonCriteriaMarineSuffixService,
        protected niazsanjiPersonGradeScoreService: NiazsanjiPersonGradeScoreMarineSuffixService,
        protected effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
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

        this.activatedRoute.data.subscribe(({ niazsanjiPersonGrade }) => {
            this.niazsanjiPersonGrade = niazsanjiPersonGrade;

            if (this.niazsanjiPersonGrade.finalNiazsanjiReportPersonId) {
                this.finalNiazsanjiReportPersonService
                    .find(this.niazsanjiPersonGrade.finalNiazsanjiReportPersonId)
                    .subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>) => {
                        this.finalNiazsanjiReportPerson = resp.body;
                        this.finalNiazsanjiReport = this.finalNiazsanjiReportPerson.finalNiazsanjiReport;
                        const personCriteria = [
                            {
                                key: 'finalNiazsanjiReportPersonId.equals',
                                value: this.niazsanjiPersonGrade.finalNiazsanjiReportPersonId
                            }
                        ];
                        this.niazsanjiPersonGradeService
                            .query({
                                page: 0,
                                size: 20000,
                                criteria: personCriteria,
                                sort: ['id', 'asc']
                            })
                            .subscribe(
                                (res: HttpResponse<INiazsanjiPersonGradeMarineSuffix[]>) => {
                                    if (res.body && res.body.length > 0) this.niazsanjiPersonGrade = res.body[0];
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
                                value: 1
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
        if (this.niazsanjiPersonGrade.id !== undefined) {
            const criteria = [
                {
                    key: 'niazsanjiPersonGradeId.equals',
                    value: this.niazsanjiPersonGrade.id
                }
            ];
            this.niazsanjiPersonGradeScoreService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (res: HttpResponse<INiazsanjiPersonGradeScoreMarineSuffix[]>) => {
                        this.niazsanjiPersonGradeScores = res.body;
                        this.buildNiazsanjiPersonGradeScores(this.niazsanjiPersonGradeScores, this.niazsanjiPersonGrade.id);
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.niazsanjiPersonGrade.evaluateDate = this.convertObjectDatesService.getNowShamsiDate();
            this.niazsanjiPersonGrade.month = this.convertObjectDatesService.getNowShamsiMonth();
            this.niazsanjiPersonGrade.year = this.convertObjectDatesService.getNowShamsiYear();
            this.niazsanjiPersonGrade.finalNiazsanjiReportPersonId = this.finalNiazsanjiReportPerson.id;
            this.buildNiazsanjiPersonGradeScores([], this.niazsanjiPersonGrade.id);
        }
    }
    buildNiazsanjiPersonGradeScores(niazsanjiPersonGradeScores: INiazsanjiPersonGradeScoreMarineSuffix[], gradeId) {
        this.niazsanjiPersonCriteriaService.query().subscribe(
            (res: HttpResponse<INiazsanjiPersonCriteriaMarineSuffix[]>) => {
                this.niazsanjiPersonCriterias = res.body.sort(
                    (a, b) => (a.displayOrder > b.displayOrder ? 1 : a.displayOrder < b.displayOrder ? -1 : 0)
                );
                this.niazsanjiPersonGrade.niazsanjiPersonGradeScores = [];
                this.niazsanjiPersonCriterias.forEach(w => {
                    const niazsanjiPersonGradeScore = niazsanjiPersonGradeScores.find(a => a.niazsanjiPersonCriteriaId == w.id);
                    if (niazsanjiPersonGradeScore) {
                        niazsanjiPersonGradeScore.niazsanjiPersonCriteriaDescription = w.description;
                        niazsanjiPersonGradeScore.niazsanjiPersonBackgroundColor = w.backgroundColor;
                        niazsanjiPersonGradeScore.niazsanjiPersonColorText = w.colorText;
                        niazsanjiPersonGradeScore.niazsanjiPersonCriteriaWeight = w.weight;
                        niazsanjiPersonGradeScore.niazsanjiPersonCriterionType = w.criterionType;
                        this.niazsanjiPersonGrade.niazsanjiPersonGradeScores.push(niazsanjiPersonGradeScore);
                    } else {
                        let niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix = new NiazsanjiPersonGradeScoreMarineSuffix();
                        niazsanjiPersonGradeScore.isNew = true;
                        niazsanjiPersonGradeScore.id = Math.floor(Math.random() * 100000000000); //This must change to guid but for now is good //this.commonUtilitiesService.uuidv4();
                        niazsanjiPersonGradeScore.niazsanjiPersonGradeId = gradeId;
                        niazsanjiPersonGradeScore.niazsanjiPersonCriteriaId = w.id;
                        niazsanjiPersonGradeScore.niazsanjiPersonCriteriaTitle = w.title;
                        niazsanjiPersonGradeScore.niazsanjiPersonCriteriaDescription = w.description;
                        niazsanjiPersonGradeScore.niazsanjiPersonBackgroundColor = w.backgroundColor;
                        niazsanjiPersonGradeScore.niazsanjiPersonColorText = w.colorText;
                        niazsanjiPersonGradeScore.niazsanjiPersonCriteriaWeight = w.weight;
                        niazsanjiPersonGradeScore.niazsanjiPersonCriterionType = w.criterionType;
                        niazsanjiPersonGradeScore.score = 5;
                        this.niazsanjiPersonGrade.niazsanjiPersonGradeScores.push(niazsanjiPersonGradeScore);
                    }
                });
                this.sumScores();
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    checkValue(id: number, value: number) {
        let a = this.niazsanjiPersonGrade.niazsanjiPersonGradeScores.find(a => a.id == id);
        if (a) {
            a.score = value;
        }

        this.sumScores();
    }
    sumScores() {
        const totalScore = this.niazsanjiPersonGrade.niazsanjiPersonGradeScores
            .map(a => a.score * a.niazsanjiPersonCriteriaWeight)
            .reduce((sum, current) => sum + current);
        this.niazsanjiPersonGrade.totalScore = totalScore;
        const totalWeight = this.niazsanjiPersonGrade.niazsanjiPersonGradeScores
            .map(a => a.niazsanjiPersonCriteriaWeight)
            .reduce((sum, current) => sum + current);
        const generalTotalScores = 5 * totalWeight;
        const totalScorePercent = Math.round(totalScore / generalTotalScores * 100);
        this.niazsanjiPersonGrade.totalScorePercent = totalScorePercent;

        this.niazsanjiPersonGrade.grade = this.convertObjectDatesService.calculateGrade(totalScorePercent);
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

        this.niazsanjiPersonGrade.createDate = undefined;
        this.niazsanjiPersonGrade.modifyDate = undefined;
        this.niazsanjiPersonGrade.niazsanjiPersonGradeScores.forEach(a => {
            if (a.isNew) {
                a.id = undefined;
            }
        });

        if (this.niazsanjiPersonGrade.id !== undefined) {
            this.subscribeToSaveResponse(this.niazsanjiPersonGradeService.update(this.niazsanjiPersonGrade));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiPersonGradeService.create(this.niazsanjiPersonGrade));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiPersonGradeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiPersonGradeMarineSuffix>) => this.onSaveSuccess(),
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
