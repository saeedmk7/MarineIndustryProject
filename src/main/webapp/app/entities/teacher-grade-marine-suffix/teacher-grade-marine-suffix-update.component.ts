import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ITeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';
import { TeacherGradeMarineSuffixService } from './teacher-grade-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { TeacherMarineSuffixService } from 'app/entities/teacher-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';
import { ITeacherGradeScoreMarineSuffix, TeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';
import { TeacherCriteriaMarineSuffixService } from 'app/entities/teacher-criteria-marine-suffix';
import { TeacherGradeScoreMarineSuffixService } from 'app/entities/teacher-grade-score-marine-suffix';
import { Principal } from 'app/core';
import { Grade } from 'app/shared/model/enums/Grade';
import * as persianMoment from 'jalali-moment';
import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';
import { TeacherCriteriaGroupMarineSuffixService } from 'app/entities/teacher-criteria-group-marine-suffix';

@Component({
    selector: 'mi-teacher-grade-marine-suffix-update',
    templateUrl: './teacher-grade-marine-suffix-update.component.html',
    styleUrls: ['./teacher-grade-marine-suffix.scss']
})
export class TeacherGradeMarineSuffixUpdateComponent implements OnInit {
    teacherGrade: ITeacherGradeMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    teachers: ITeacherMarineSuffix[];

    teacherCriterias: ITeacherCriteriaMarineSuffix[];

    teacherGradeScores: ITeacherGradeScoreMarineSuffix[];

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

    teachercriteriagroups: ITeacherCriteriaGroupMarineSuffix[];
    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected teacherGradeService: TeacherGradeMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected teacherService: TeacherMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected teacherCriteriaService: TeacherCriteriaMarineSuffixService,
        protected teacherGradeScoreService: TeacherGradeScoreMarineSuffixService,
        protected teacherCriteriaGroupService: TeacherCriteriaGroupMarineSuffixService,
        private principal: Principal,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
        });

        this.activatedRoute.data.subscribe(({ teacherGrade }) => {
            this.teacherGrade = teacherGrade;

            if (this.teacherGrade.id !== undefined) {
                const criteria = [
                    {
                        key: 'teacherGradeId.equals',
                        value: this.teacherGrade.id
                    }
                ];
                this.teacherGradeScoreService
                    .query({
                        page: 0,
                        size: 20000,
                        criteria,
                        sort: ['id', 'asc']
                    })
                    .subscribe(
                        (res: HttpResponse<ITeacherGradeScoreMarineSuffix[]>) => {
                            this.teacherGradeScores = res.body;
                            this.buildTeacherGradeScores(
                                this.teacherGradeScores,
                                this.teacherGrade.id,
                                this.teacherGrade.teacherCriteriaGroupId
                            );
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
            }
            /*else{
                this.buildTeacherGradeScores([], this.teacherGrade.id);
            }*/
        });
        this.teacherCriteriaGroupService.query().subscribe(
            (res: HttpResponse<ITeacherCriteriaGroupMarineSuffix[]>) => {
                this.teachercriteriagroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teacherService.query().subscribe(
            (res: HttpResponse<ITeacherMarineSuffix[]>) => {
                this.teachers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadCriterias(teacherGradeId, teacherCriteriaGroupId) {
        this.buildTeacherGradeScores([], teacherGradeId, teacherCriteriaGroupId);
    }
    buildTeacherGradeScores(teacherGradeScores: ITeacherGradeScoreMarineSuffix[], gradeId, teacherCriteriaGroupId) {
        const criteria = [
            {
                key: 'teacherCriteriaGroupId.equals',
                value: teacherCriteriaGroupId
            }
        ];
        this.teacherCriteriaService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['id', 'asc']
            })
            .subscribe(
                (res: HttpResponse<ITeacherCriteriaMarineSuffix[]>) => {
                    this.teacherCriterias = res.body.sort(
                        (a, b) => (a.displayOrder > b.displayOrder ? 1 : a.displayOrder < b.displayOrder ? -1 : 0)
                    );
                    this.teacherGrade.teacherGradeScores = [];
                    this.teacherCriterias.forEach(w => {
                        const teacherGradeScore = teacherGradeScores.find(a => a.teacherCriteriaId == w.id);
                        if (teacherGradeScore) {
                            teacherGradeScore.teacherCriteriaDescription = w.description;
                            teacherGradeScore.teacherCriteriaWeight = w.weight;
                            this.teacherGrade.teacherGradeScores.push(teacherGradeScore);
                        } else {
                            let teacherGradeScore: ITeacherGradeScoreMarineSuffix = new TeacherGradeScoreMarineSuffix();
                            teacherGradeScore.isNew = true;
                            teacherGradeScore.id = Math.floor(Math.random() * 100000000000); //This must change to guid but for now is good //this.commonUtilitiesService.uuidv4();
                            teacherGradeScore.teacherGradeId = gradeId;
                            teacherGradeScore.teacherCriteriaId = w.id;
                            teacherGradeScore.teacherCriteriaTitle = w.title;
                            teacherGradeScore.teacherCriteriaDescription = w.description;
                            teacherGradeScore.teacherCriteriaWeight = w.weight;
                            teacherGradeScore.score = 5;
                            this.teacherGrade.teacherGradeScores.push(teacherGradeScore);
                        }
                    });
                    this.sumScores();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    checkValue(id: number, value: number) {
        let a = this.teacherGrade.teacherGradeScores.find(a => a.id == id);
        if (a) {
            a.score = value;
        }

        this.sumScores();
    }
    sumScores() {
        const totalScore = this.teacherGrade.teacherGradeScores
            .map(a => a.score * a.teacherCriteriaWeight)
            .reduce((sum, current) => sum + current);
        this.teacherGrade.totalScore = totalScore;
        const totalWeight = this.teacherGrade.teacherGradeScores.map(a => a.teacherCriteriaWeight).reduce((sum, current) => sum + current);
        const generalTotalScores = 5 * totalWeight;
        const totalScorePercent = Math.round(totalScore / generalTotalScores * 100);
        this.teacherGrade.totalScorePercent = totalScorePercent;

        switch (true) {
            case totalScorePercent <= 50:
                this.teacherGrade.grade = Grade.D;
                break;
            case totalScorePercent <= 60:
                this.teacherGrade.grade = Grade.C;
                break;
            case totalScorePercent <= 75:
                this.teacherGrade.grade = Grade.B;
                break;
            case totalScorePercent <= 100:
                this.teacherGrade.grade = Grade.A;
                break;
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

        this.teacherGrade.teacherGradeScores.forEach(a => {
            if (a.isNew) {
                a.id = undefined;
            }
        });

        if (this.teacherGrade.id !== undefined) {
            this.subscribeToSaveResponse(this.teacherGradeService.update(this.teacherGrade));
        } else {
            this.subscribeToSaveResponse(this.teacherGradeService.create(this.teacherGrade));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITeacherGradeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeacherGradeMarineSuffix>) => this.onSaveSuccess(),
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

    trackTeacherById(index: number, item: ITeacherMarineSuffix) {
        return item.id;
    }

    trackTeacherCriteriaGroupById(index: number, item: ITeacherCriteriaGroupMarineSuffix) {
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
