import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';
import { PreJobNiazsanjiCompetencyMarineSuffixService } from './pre-job-niazsanji-competency-marine-suffix.service';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import { IFixCompetencyDeficiencyMarineSuffix } from 'app/shared/model/fix-competency-deficiency-marine-suffix.model';
import { FixCompetencyDeficiencyMarineSuffixService } from 'app/entities/fix-competency-deficiency-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { PreJobNiazsanjiMarineSuffixService } from 'app/entities/pre-job-niazsanji-marine-suffix';
import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';
import { CompetencyMarineSuffixService } from 'app/entities/competency-marine-suffix';

@Component({
    selector: 'mi-pre-job-niazsanji-competency-marine-suffix-update',
    templateUrl: './pre-job-niazsanji-competency-marine-suffix-update.component.html'
})
export class PreJobNiazsanjiCompetencyMarineSuffixUpdateComponent implements OnInit {
    preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix;
    isSaving: boolean;

    teachingapproaches: ITeachingApproachMarineSuffix[];

    fixcompetencydeficiencies: IFixCompetencyDeficiencyMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    prejobniazsanjis: IPreJobNiazsanjiMarineSuffix[];

    competencies: ICompetencyMarineSuffix[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected preJobNiazsanjiCompetencyService: PreJobNiazsanjiCompetencyMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected fixCompetencyDeficiencyService: FixCompetencyDeficiencyMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected competencyService: CompetencyMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ preJobNiazsanjiCompetency }) => {
            this.preJobNiazsanjiCompetency = preJobNiazsanjiCompetency;
        });
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix[]>) => {
                this.teachingapproaches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.fixCompetencyDeficiencyService.query().subscribe(
            (res: HttpResponse<IFixCompetencyDeficiencyMarineSuffix[]>) => {
                this.fixcompetencydeficiencies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.preJobNiazsanjiService.query().subscribe(
            (res: HttpResponse<IPreJobNiazsanjiMarineSuffix[]>) => {
                this.prejobniazsanjis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.competencyService.query().subscribe(
            (res: HttpResponse<ICompetencyMarineSuffix[]>) => {
                this.competencies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.preJobNiazsanjiCompetency.id !== undefined) {
            this.subscribeToSaveResponse(this.preJobNiazsanjiCompetencyService.update(this.preJobNiazsanjiCompetency));
        } else {
            this.subscribeToSaveResponse(this.preJobNiazsanjiCompetencyService.create(this.preJobNiazsanjiCompetency));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>) => this.onSaveSuccess(),
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

    trackTeachingApproachById(index: number, item: ITeachingApproachMarineSuffix) {
        return item.id;
    }

    trackFixCompetencyDeficiencyById(index: number, item: IFixCompetencyDeficiencyMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackPreJobNiazsanjiById(index: number, item: IPreJobNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackCompetencyById(index: number, item: ICompetencyMarineSuffix) {
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
