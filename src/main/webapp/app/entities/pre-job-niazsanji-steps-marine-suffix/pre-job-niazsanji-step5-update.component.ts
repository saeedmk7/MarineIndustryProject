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
import {
    IPriorityCriteriaMarineSuffix,
    PriorityCriteriaMarineSuffix
} from "app/shared/model/priority-criteria-marine-suffix.model";
import {PriorityCriteriaMarineSuffixService} from "app/entities/priority-criteria-marine-suffix";
import {
    IPriorityCriteriaValueMarineSuffix,
    PriorityCriteriaValueMarineSuffix
} from "app/shared/model/priority-criteria-value-marine-suffix.model";
import {PriorityCriteriaValueMarineSuffixService} from "app/entities/priority-criteria-value-marine-suffix";
import {DesignNiazsanjiMarineSuffixService} from "app/entities/design-niazsanji-marine-suffix";
import {IDesignNiazsanjiMarineSuffix} from "app/shared/model/design-niazsanji-marine-suffix.model";
import {IHomePagePersonEducationalModule} from "app/shared/model/custom/home-page-person-educational-module";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";

@Component({
    selector: 'mi-pre-job-niazsanji-step5-update',
    templateUrl: './pre-job-niazsanji-step5-update.component.html',
    styleUrls: ['./pre-job-niazsanji-steps-marine-suffix.scss']
})
export class PreJobNiazsanjiStep5UpdateComponent implements OnInit {
    @Input("preJobNiazsanji") preJobNiazsanji : IPreJobNiazsanjiMarineSuffix;
    @Input("isAdmin") isAdmin : boolean;
    @Input("editable") editable : boolean;
    @Output("updatePreJobNiazsanjiStep") updatePreJobNiazsanjiStep = new EventEmitter();
    preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix;
    isSaving: boolean;
    teachingapproaches: ITeachingApproachMarineSuffix[];
    priorityCriteria: IPriorityCriteriaMarineSuffix[] = [];
    priorityCriteriaValues: IPriorityCriteriaValueMarineSuffix[];
    educationalmodules: IEducationalModuleMarineSuffix[];

    fixcompetencydeficiencies: IFixCompetencyDeficiencyMarineSuffix[];

    competencies: ICompetencyMarineSuffix[];

    preJobNiazsanjiCompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[] = [];
    designNiazsanjies: IDesignNiazsanjiMarineSuffix[] = [];
    selectedEducationalModule: IEducationalModuleMarineSuffix;


    designArray: number[] = [];
    constructor(
        protected jhiAlertService: JhiAlertService,
        protected priorityCriteriaService: PriorityCriteriaMarineSuffixService,
        protected designNiazsanjiService: DesignNiazsanjiMarineSuffixService,
        protected priorityCriteriaValueService: PriorityCriteriaValueMarineSuffixService,
        protected preJobNiazsanjiCompetencyService: PreJobNiazsanjiCompetencyMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected fixCompetencyDeficiencyService: FixCompetencyDeficiencyMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected competencyService: CompetencyMarineSuffixService,
        protected finalNiazsanjiReportMarineSuffixService: FinalNiazsanjiReportMarineSuffixService,
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
        let criteria = [{
            key: 'preJobNiazsanjiId.equals',
            value: this.preJobNiazsanji.id
        }];
        this.designNiazsanjiService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe((resp: HttpResponse<IDesignNiazsanjiMarineSuffix[]>) => {
            this.designNiazsanjies = resp.body;
            if(this.designNiazsanjies && this.designNiazsanjies.length > 0)
            {
                for (let i = 0; i < this.designNiazsanjies.length; i++) {

                    this.designArray.push(this.getRandomNumber());
                }
                this.preJobNiazsanji.designNiazsanjis = this.designNiazsanjies;
            }
            else{
                this.designArray.push(this.getRandomNumber());
            }

        });



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
    getRandomNumber() : number {

        return Math.floor((Math.random() * 10000) + 1);
    }
    get designs(){
        return this.designArray;
    }
    addDesign(){

        this.designArray.push(this.getRandomNumber());
    }
    deleteDesign(event){
        this.designArray = this.designArray.filter(a => a != event);
    }
    /*designCount: number = 0;

    get counter() {
        return new Array(this.designCount);
    }*/
    designNiazsanjiDeleted($event){

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
                this.priorityCriteriaService.query().subscribe(
                    (res: HttpResponse<IPriorityCriteriaMarineSuffix[]>) => {
                        this.priorityCriteria = res.body.sort((a,b) => (a.displayOrder > b.displayOrder) ? 1 : (a.displayOrder < b.displayOrder) ? -1 : 0);
                        let cr = [{
                            key: 'preJobNiazsanjiCompetencyId.in',
                            value: this.preJobNiazsanjiCompetencies.map(r => r.id)
                        }];
                        this.priorityCriteriaValueService.query({
                            page: 0,
                            size: 20000,
                            cr,
                            sort: ["id", "asc"]
                        }).subscribe((response: HttpResponse<IPriorityCriteriaValueMarineSuffix[]>) => {
                                this.priorityCriteriaValues = response.body;
                                this.preJobNiazsanjiCompetencies.forEach(a => {
                                    a.priorityCriteriaValues = [];
                                    this.priorityCriteria.forEach(b => {
                                        let id: number = null;
                                        let score: number = null;
                                        let pcv = this.priorityCriteriaValues.find(t => t.priorityCriteriaId == b.id && t.preJobNiazsanjiCompetencyId == a.id);
                                        if(pcv) {
                                            id = pcv.id;
                                            score = pcv.score;
                                        }
                                        a.priorityCriteriaValues.push({
                                            id: id,
                                            priorityCriteriaId: b.id,
                                            preJobNiazsanjiCompetencyId: a.id,
                                            score: score
                                        });

                                    });

                                });
                            },
                            (res: HttpErrorResponse) => this.onError(res.message))
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            },
            (error) => this.onError("موردی یافت نشد."));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;


        this.preJobNiazsanjiCompetencies.forEach(a => {
            a.sumScore = a.priorityCriteriaValues.map(w => w.score).reduce((sum, current) => sum + current);
        });
        this.subscribeToSaveResponse(
            this.preJobNiazsanjiCompetencyService.bulkUpdate(this.preJobNiazsanjiCompetencies));


        let priorityCriteriaValues : IPriorityCriteriaValueMarineSuffix[] = [];
        this.preJobNiazsanjiCompetencies.forEach(w => {
            w.priorityCriteriaValues.forEach(r => {
                priorityCriteriaValues.push(r);
            });
        });
        this.priorityCriteriaValueService.bulkUpdate(priorityCriteriaValues).subscribe(
            (res: HttpResponse<IPriorityCriteriaValueMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    finishNiazsanji(){
        this.isSaving = true;
        if(confirm("آیا از ثبت نهایی دوره ها مطمئنید؟ در صورت تایید دیگر امکان ویرایش دوره ها وجود ندارد.")){
            this.preJobNiazsanjiService.finalize(this.preJobNiazsanji).subscribe(
                (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.onFinalizeSuccess(),
                (res: HttpErrorResponse) => this.onSaveError()
            );
        }
    }

    /*get sumPriority(i) : number{
        return this.preJobNiazsanjiCompetencies[i].priorityCriteriaValues.map(a => a.score).reduce((sum, current) => sum + current);
    }*/
    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        //this.changePreJobNiazsanjiStep(5);
        this.isSaving = false;
        //this.previousState();
    }
    protected onFinalizeSuccess() {
        this.isSaving = false;
        this.previousState();
    }
    stepBack(){
        this.changePreJobNiazsanjiStep(4);
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
