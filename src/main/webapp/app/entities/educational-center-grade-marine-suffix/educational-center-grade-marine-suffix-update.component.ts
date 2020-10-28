import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';
import { EducationalCenterGradeMarineSuffixService } from './educational-center-grade-marine-suffix.service';
import { IEvaluatorOpinionMarineSuffix } from 'app/shared/model/evaluator-opinion-marine-suffix.model';
import { EvaluatorOpinionMarineSuffixService } from 'app/entities/evaluator-opinion-marine-suffix';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IEducationalCenterServiceMarineSuffix } from 'app/shared/model/educational-center-service-marine-suffix.model';
import { EducationalCenterServiceMarineSuffixService } from 'app/entities/educational-center-service-marine-suffix';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { EducationalCenterMarineSuffixService } from 'app/entities/educational-center-marine-suffix';
import * as persianMoment from 'jalali-moment';
import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';
import {
    EducationalCenterGradeScoreMarineSuffix,
    IEducationalCenterGradeScoreMarineSuffix
} from 'app/shared/model/educational-center-grade-score-marine-suffix.model';
import { EducationalCenterCriteriaMarineSuffixService } from 'app/entities/educational-center-criteria-marine-suffix';
import { EducationalCenterGradeScoreMarineSuffixService } from 'app/entities/educational-center-grade-score-marine-suffix';
import { CommonUtilitiesService } from 'app/plugin/utilities/common-utilities';
import { Grade } from 'app/shared/model/enums/Grade';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { Principal } from 'app/core';
import { MONTHS } from 'app/shared/constants/months.constants';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { IEducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';
import { EducationalCenterGroupMarineSuffixService } from 'app/entities/educational-center-group-marine-suffix';

@Component({
    selector: 'mi-educational-center-grade-marine-suffix-update',
    templateUrl: './educational-center-grade-marine-suffix-update.component.html',
    styleUrls: ['./educational-center-grade-marine-suffix.scss']
})
export class EducationalCenterGradeMarineSuffixUpdateComponent implements OnInit {
    educationalCenterGrade: IEducationalCenterGradeMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    evaluatoropinions: IEvaluatorOpinionMarineSuffix[];

    educationalcenterservices: IEducationalCenterServiceMarineSuffix[];

    educationalcenters: IEducationalCenterMarineSuffix[];

    educationalCenterCriterias: IEducationalCenterCriteriaMarineSuffix[];

    educationalCenterGradeScores: IEducationalCenterGradeScoreMarineSuffix[];

    finishDateValidation: number;

    scores: number[] = [5, 4, 3, 2, 1];

    educationalcentergroups: IEducationalCenterGroupMarineSuffix[];
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

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected educationalCenterGradeService: EducationalCenterGradeMarineSuffixService,
        protected evaluatorOpinionService: EvaluatorOpinionMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected educationalCenterServiceService: EducationalCenterServiceMarineSuffixService,
        protected educationalCenterService: EducationalCenterMarineSuffixService,
        protected educationalCenterCriteriaService: EducationalCenterCriteriaMarineSuffixService,
        protected educationalCenterGradeScoreService: EducationalCenterGradeScoreMarineSuffixService,
        protected educationalCenterGroupService: EducationalCenterGroupMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private router: Router,
        private principal: Principal,
        private commonUtilitiesService: CommonUtilitiesService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
        });
        this.educationalCenterGroupService.query().subscribe(
            (res: HttpResponse<IEducationalCenterGroupMarineSuffix[]>) => {
                this.educationalcentergroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.activatedRoute.data.subscribe(({ educationalCenterGrade }) => {
            this.educationalCenterGrade = educationalCenterGrade;

            if (this.educationalCenterGrade.id !== undefined) {
                const criteria = [
                    {
                        key: 'educationalCenterGradeId.equals',
                        value: this.educationalCenterGrade.id
                    }
                ];
                this.educationalCenterGradeScoreService
                    .query({
                        page: 0,
                        size: 20000,
                        criteria,
                        sort: ['id', 'asc']
                    })
                    .subscribe(
                        (res: HttpResponse<IEducationalCenterGradeScoreMarineSuffix[]>) => {
                            this.educationalCenterGradeScores = res.body;
                            this.buildEducationalCenterGradeScores(
                                this.educationalCenterGradeScores,
                                this.educationalCenterGrade.id,
                                this.educationalCenterGrade.educationalCenterGroupId
                            );
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
            }
            /*else {
                this.buildEducationalCenterGradeScores([], this.educationalCenterGrade.id);
            }*/
        });
        this.evaluatorOpinionService.query().subscribe(
            (res: HttpResponse<IEvaluatorOpinionMarineSuffix[]>) => {
                this.evaluatoropinions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalCenterServiceService.query().subscribe(
            (res: HttpResponse<IEducationalCenterServiceMarineSuffix[]>) => {
                this.educationalcenterservices = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalCenterService.query().subscribe(
            (res: HttpResponse<IEducationalCenterMarineSuffix[]>) => {
                this.educationalcenters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadCriterias(educationalCenterGradeId, educationalCenterGroupId) {
        this.buildEducationalCenterGradeScores([], educationalCenterGradeId, educationalCenterGroupId);
    }
    buildEducationalCenterGradeScores(
        educationalCenterGradeScores: IEducationalCenterGradeScoreMarineSuffix[],
        gradeId,
        educationalCenterGroupId
    ) {
        const criteria = [
            {
                key: 'educationalCenterGroupId.equals',
                value: educationalCenterGroupId
            }
        ];
        this.educationalCenterCriteriaService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['id', 'asc']
            })
            .subscribe(
                (res: HttpResponse<IEducationalCenterCriteriaMarineSuffix[]>) => {
                    this.educationalCenterCriterias = res.body.sort(
                        (a, b) => (a.displayOrder > b.displayOrder ? 1 : a.displayOrder < b.displayOrder ? -1 : 0)
                    );
                    this.educationalCenterGrade.educationalCenterGradeScores = [];
                    this.educationalCenterCriterias.forEach(w => {
                        const educationalCenterGradeScore = educationalCenterGradeScores.find(a => a.educationalCenterCriteriaId == w.id);
                        if (educationalCenterGradeScore) {
                            educationalCenterGradeScore.educationalCenterCriteriaDescription = w.description;
                            this.educationalCenterGrade.educationalCenterGradeScores.push(educationalCenterGradeScore);
                        } else {
                            let educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix = new EducationalCenterGradeScoreMarineSuffix();
                            educationalCenterGradeScore.isNew = true;
                            educationalCenterGradeScore.id = Math.floor(Math.random() * 100000000000); //This must change to guid but for now is good //this.commonUtilitiesService.uuidv4();
                            educationalCenterGradeScore.educationalCenterGradeId = gradeId;
                            educationalCenterGradeScore.educationalCenterCriteriaId = w.id;
                            educationalCenterGradeScore.educationalCenterCriteriaTitle = w.title;
                            educationalCenterGradeScore.educationalCenterCriteriaDescription = w.description;
                            educationalCenterGradeScore.score = 1;
                            this.educationalCenterGrade.educationalCenterGradeScores.push(educationalCenterGradeScore);
                        }
                    });
                    this.sumScores();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    checkValue(id: number, value: number) {
        let a = this.educationalCenterGrade.educationalCenterGradeScores.find(a => a.id == id);
        if (a) {
            a.score = value;
        }

        this.sumScores();
    }

    sumScores() {
        const totalScore = this.educationalCenterGrade.educationalCenterGradeScores
            .map(a => a.score)
            .reduce((sum, current) => sum + current);
        this.educationalCenterGrade.totalScore = totalScore;
        const generalTotalScores = this.educationalCenterGrade.educationalCenterGradeScores.length * 5;
        const totalScorePercent = Math.round(totalScore / generalTotalScores * 100);
        this.educationalCenterGrade.totalScorePercent = totalScorePercent;

        switch (true) {
            case totalScorePercent <= 50:
                this.educationalCenterGrade.grade = Grade.D;
                break;
            case totalScorePercent <= 60:
                this.educationalCenterGrade.grade = Grade.C;
                break;
            case totalScorePercent <= 75:
                this.educationalCenterGrade.grade = Grade.B;
                break;
            case totalScorePercent <= 100:
                this.educationalCenterGrade.grade = Grade.A;
                break;
        }
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

        this.educationalCenterGrade.educationalCenterGradeScores.forEach(a => {
            if (a.isNew) {
                a.id = undefined;
            }
        });

        if (this.educationalCenterGrade.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalCenterGradeService.update(this.educationalCenterGrade));
        } else {
            this.subscribeToSaveResponse(this.educationalCenterGradeService.create(this.educationalCenterGrade));
        }
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

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalCenterGradeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalCenterGradeMarineSuffix>) => this.onSaveSuccess(),
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

    trackEvaluatorOpinionById(index: number, item: IEvaluatorOpinionMarineSuffix) {
        return item.id;
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackEducationalCenterServiceById(index: number, item: IEducationalCenterServiceMarineSuffix) {
        return item.id;
    }

    trackEducationalCenterById(index: number, item: IEducationalCenterMarineSuffix) {
        return item.id;
    }

    trackEducationalCenterGroupById(index: number, item: IEducationalCenterGroupMarineSuffix) {
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
}
