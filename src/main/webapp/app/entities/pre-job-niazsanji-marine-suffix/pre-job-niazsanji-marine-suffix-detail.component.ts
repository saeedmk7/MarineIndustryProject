import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import {ICompetencyMarineSuffix} from "app/shared/model/competency-marine-suffix.model";
import {
    IPreJobNiazsanjiCompetencyMarineSuffix,
    PreJobNiazsanjiCompetencyMarineSuffix
} from "app/shared/model/pre-job-niazsanji-competency-marine-suffix.model";
import {CompetencyMarineSuffixService} from "app/entities/competency-marine-suffix";
import {PreJobNiazsanjiMarineSuffixService} from "app/entities/pre-job-niazsanji-marine-suffix/pre-job-niazsanji-marine-suffix.service";
import {PreJobNiazsanjiCompetencyMarineSuffixService} from "app/entities/pre-job-niazsanji-competency-marine-suffix";
import {HttpResponse} from "@angular/common/http";
import {IJamHelpAuthorityMarineSuffix} from "app/shared/model/jam-help-authority-marine-suffix.model";
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {JobMarineSuffixService} from "app/entities/job-marine-suffix";
import {IJobMarineSuffix, JobMarineSuffix} from "app/shared/model/job-marine-suffix.model";

@Component({
    selector: 'mi-pre-job-niazsanji-marine-suffix-detail',
    templateUrl: './pre-job-niazsanji-marine-suffix-detail.component.html'
})
export class PreJobNiazsanjiMarineSuffixDetailComponent implements OnInit, OnDestroy {
    @Input("preJobNiazsanjiId") preJobNiazsanjiId : number;
    @Output("updatePreJobNiazsanjiStep") updatePreJobNiazsanjiStep = new EventEmitter();
    ngOnDestroy(): void {
    }
    preJobNiazsanji: IPreJobNiazsanjiMarineSuffix;
    competencies: ICompetencyMarineSuffix[] = [];
    preJobNiazsanjiCompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[] = [];
    job: IJobMarineSuffix = new JobMarineSuffix();
    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute,
                protected competencyService: CompetencyMarineSuffixService,
                protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
                protected personService: PersonMarineSuffixService,
                protected jobService: JobMarineSuffixService,
                protected preJobNiazsanjiCompetencyService: PreJobNiazsanjiCompetencyMarineSuffixService,
                private jhiAlertService: JhiAlertService) {

    }
    stepBack(){
        this.changePreJobNiazsanjiStep(1);
    }
    stepForward(){
        this.changePreJobNiazsanjiStep(2);
    }
    changePreJobNiazsanjiStep(step: number){
        this.updatePreJobNiazsanjiStep.emit(step);
    }
    ngOnInit() {

        if(!this.preJobNiazsanjiId) {
            this.activatedRoute.data.subscribe(({preJobNiazsanji}) => {
                this.preJobNiazsanji = preJobNiazsanji;
                this.showData(this.preJobNiazsanji.id);
                this.loadJob(this.preJobNiazsanji.personId);
            });
        }
        else{
            this.loadPreJobNiazsanji(this.preJobNiazsanjiId);
            this.showData(this.preJobNiazsanjiId);
        }

    }
    loadJob(personId: number){
        this.personService.find(personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
            this.jobService.find(resp.body.jobId).subscribe((job: HttpResponse<IJobMarineSuffix>) => {
                this.job = job.body;
            })
        })
    }
    loadPreJobNiazsanji(id){
        this.preJobNiazsanjiService.find(id).subscribe((resp: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => {
                this.preJobNiazsanji = resp.body;
                this.loadJob(this.preJobNiazsanji.personId);
            },
            (error) => this.onError("موردی یافت نشد."));
    }
    showData(id){
        let criteria = [{
            key: 'preJobNiazsanjiId.equals',
            value: id
        }];
        this.preJobNiazsanjiCompetencyService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe((resp: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix[]>) => {
                this.preJobNiazsanjiCompetencies = resp.body;
                this.competencyService.query().subscribe((resp: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix[]>) => {
                        this.competencies = resp.body;
                        this.competencies.forEach(a => {
                            a.selectedItems = this.preJobNiazsanjiCompetencies.filter(w => w.competencyId == a.id).map(e => e.title);
                        })
                    },
                    (error) => this.onError("موردی یافت نشد."));
            },
            (error) => this.onError("موردی یافت نشد."));
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
