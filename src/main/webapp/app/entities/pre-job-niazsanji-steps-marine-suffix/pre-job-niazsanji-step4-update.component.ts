import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';
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
import {PreJobNiazsanjiCompetencyMarineSuffixService} from 'app/entities/pre-job-niazsanji-competency-marine-suffix';
import * as $ from 'jquery';

@Component({
    selector: 'mi-pre-job-niazsanji-step4-update',
    templateUrl: './pre-job-niazsanji-step4-update.component.html'
})
export class PreJobNiazsanjiStep4UpdateComponent implements OnInit {
    @Input("preJobNiazsanji") preJobNiazsanji : IPreJobNiazsanjiMarineSuffix;
    @Input("isAdmin") isAdmin : boolean;
    @Input("editable") editable : boolean;
    @Output("updatePreJobNiazsanjiStep") updatePreJobNiazsanjiStep = new EventEmitter();
    preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix;
    isSaving: boolean;
    teachingapproaches: ITeachingApproachMarineSuffix[];
    educationalmodules: IEducationalModuleMarineSuffix[];

    fixcompetencydeficiencies: IFixCompetencyDeficiencyMarineSuffix[];

    competencies: ICompetencyMarineSuffix[];

    preJobNiazsanjiCompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[] = [];
    selectedEducationalModule: IEducationalModuleMarineSuffix;
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

        this.showData(this.preJobNiazsanji.id);
        /*this.fixCompetencyDeficiencyService.query().subscribe(
            (res: HttpResponse<IFixCompetencyDeficiencyMarineSuffix[]>) => {
                this.fixcompetencydeficiencies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
        setTimeout(() => {
            if(!this.editable)
            {
                $("#content :input").attr("disabled", true);
            }
        }, 1000);
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix[]>) => {
                this.teachingapproaches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    deleteTeach(preJobNiazsanjiCompetencyId ,teachId){
        let preJobNiazsanjiCompetency = this.preJobNiazsanjiCompetencies.find(a => a.id == preJobNiazsanjiCompetencyId);
        preJobNiazsanjiCompetency.teachingApproaches = preJobNiazsanjiCompetency.teachingApproaches.filter(w => w.id != teachId);
    }
    showData(id){
        let criteria = [{
            key: 'preJobNiazsanjiId.equals',
            value: id
        },{
            key: 'needToImprove.equals',
            value: 1
        }];
        this.preJobNiazsanjiCompetencyService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe((resp: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix[]>) => {
                this.preJobNiazsanjiCompetencies = resp.body;
            },
            (error) => this.onError("موردی یافت نشد."));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

        this.subscribeToSaveResponse(
            this.preJobNiazsanjiCompetencyService.bulkUpdate(this.preJobNiazsanjiCompetencies));
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.changePreJobNiazsanjiStep(5);
        this.isSaving = false;
        //this.previousState();
    }
    stepBack(){
        this.changePreJobNiazsanjiStep(3);
    }
    stepForward(){
        this.changePreJobNiazsanjiStep(5);
    }
    changePreJobNiazsanjiStep(step: number){
        this.updatePreJobNiazsanjiStep.emit(step);
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
