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
    selector: 'mi-pre-job-niazsanji-step2-update',
    templateUrl: './pre-job-niazsanji-step2-update.component.html'
})
export class PreJobNiazsanjiStep2UpdateComponent implements OnInit {
    @Input("preJobNiazsanji") preJobNiazsanji : IPreJobNiazsanjiMarineSuffix;
    @Input("editable") editable : boolean;
    @Output("updatePreJobNiazsanjiStep") updatePreJobNiazsanjiStep = new EventEmitter();
    preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix;
    isSaving: boolean;
    errorMessage:string;

    competencies: ICompetencyMarineSuffix[];

    preJobNiazsanjiCompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[] = [];
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
        setTimeout(() => {
            if(!this.editable)
            {
                $("#content :input").attr("disabled", true);
            }
        }, 1000);
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
                        this.competencies = resp.body.sort((a,b) => (a.id > b.id) ? 1 : (a.id < b.id) ? -1 : 0);;
                        this.competencies.forEach(a => {
                            a.selectedItems = this.preJobNiazsanjiCompetencies.filter(w => w.competencyId == a.id).map(e =>
                            {
                                return {
                                    id: e.id,
                                    title: e.title,
                                    needToImprove: e.needToImprove,
                                    needToImproveDescription: e.needToImproveDescription
                                };
                            });
                        })
                    },
                    (error) => this.onError("موردی یافت نشد."));
            },
            (error) => this.onError("موردی یافت نشد."));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        let radios = $('input[type="radio"]:checked');
        this.preJobNiazsanjiCompetencies.forEach(a => {

           a.needToImproveDescription = $('#text_' + a.id).val();
           let radioName = 'radio_' + a.id;
           for (let i=0; i < radios.length ;i++){
               if(radios[i].name == radioName)
                   a.needToImprove = radios[i].value;
           }
           /*a.needToImprove = radios.filter(r => r.name == radioName).val(); //$("input[name=`radioName`]:checked").val();*/
        });
        if(this.preJobNiazsanjiCompetencies.filter(w => w.needToImprove == 1).length == 0)
        {
            this.errorMessage = "لطفا حتما حداقل یک مورد نیاز به ارتقاء انتخاب نمائید.";
            this.isSaving = false;
            return;
        }

        this.subscribeToSaveResponse(
            this.preJobNiazsanjiCompetencyService.bulkUpdate(this.preJobNiazsanjiCompetencies));

        /*if (this.preJobNiazsanjiCompetency.id !== undefined) {
            this.subscribeToSaveResponse(this.preJobNiazsanjiCompetencyService.update(this.preJobNiazsanjiCompetency));
        } else {
            this.subscribeToSaveResponse(this.preJobNiazsanjiCompetencyService.create(this.preJobNiazsanjiCompetency));
        }*/
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {

        this.changePreJobNiazsanjiStep(3);
        this.isSaving = false;
    }
    stepBack(){
        this.changePreJobNiazsanjiStep(1);
    }
    stepForward(){
        this.changePreJobNiazsanjiStep(3);
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
