import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model/evaluate-criteria-training-marine-suffix.model';
import { EvaluateCriteriaTrainingMarineSuffixService } from './evaluate-criteria-training-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {IMediaProductTypeMarineSuffix} from "app/shared/model/media-product-type-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {Principal} from "app/core";
import {MediaProductTypeMarineSuffixService} from "app/entities/media-product-type-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import * as persianMoment from 'jalali-moment';
import {MONTHS} from "app/shared/constants/months.constants";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";

@Component({
    selector: 'mi-evaluate-criteria-training-marine-suffix-update',
    templateUrl: './evaluate-criteria-training-marine-suffix-update.component.html'
})
export class EvaluateCriteriaTrainingMarineSuffixUpdateComponent implements OnInit {
    evaluateCriteriaTraining: IEvaluateCriteriaTrainingMarineSuffix;
    isSaving: boolean;
    currentPerson: IPersonMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];

    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    groups: IOrganizationChartMarineSuffix[];

    finishDateValidation: number;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    currentAccount: any;

    runMonths: any = MONTHS.sort(function(a,b)
    {
        return (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0);
    });
    years: any = GREGORIAN_START_END_DATE.map(a => a.year);

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected evaluateCriteriaTrainingService: EvaluateCriteriaTrainingMarineSuffixService,
        private personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        private principal : Principal,
        protected mediaProductTypeService: MediaProductTypeMarineSuffixService,
        private treeUtilities: TreeUtilities,
        protected activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ evaluateCriteriaTraining }) => {
            this.evaluateCriteriaTraining = evaluateCriteriaTraining;
        });
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                    this.currentPerson = resp.body;
                    this.loadOrgCharts();
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        });
    }
    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.handleOrgChartView();
            this.groups = this.recommenedOrgCharts.filter(a => a.parentId == undefined);

        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.handleOrgChartView();
                    this.groups = this.recommenedOrgCharts.filter(a => a.parentId == undefined);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    checkDateValidation(event) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.finishDateValidation = 1;
            }
            else {
                this.finishDateValidation = 2;
            }
        }
        catch (e) {
            this.finishDateValidation = 2;
        }
    }
    change(i){
        //this.router.navigateByUrl(i);
        this.router.navigateByUrl(i);
    }
    handleOrgChartView(){
        if(this.isSuperUsers) {
            this.recommenedOrgCharts = this.organizationcharts;
            return;
        }
        if(this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId))
        {
            let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
        }
        else{
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
        }
    }
    setRoles(account: any){

        if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
            this.isAdmin = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
            this.isModirAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
            this.isModirKolAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isSuperUsers = true;
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.evaluateCriteriaTraining.reportTime = 0;
        if (this.evaluateCriteriaTraining.id !== undefined) {
            this.subscribeToSaveResponse(this.evaluateCriteriaTrainingService.update(this.evaluateCriteriaTraining));
        } else {
            this.subscribeToSaveResponse(this.evaluateCriteriaTrainingService.create(this.evaluateCriteriaTraining));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluateCriteriaTrainingMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEvaluateCriteriaTrainingMarineSuffix>) => this.onSaveSuccess(),
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

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
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

    calculateMeasure(){
        const measureCalc = (this.evaluateCriteriaTraining.firstNumber ? this.evaluateCriteriaTraining.firstNumber : 0) / (this.evaluateCriteriaTraining.secondNumber ? this.evaluateCriteriaTraining.secondNumber : 0);
        if(measureCalc) {
            this.evaluateCriteriaTraining.measureCalc = measureCalc;

            const result = measureCalc * (this.evaluateCriteriaTraining.criteriaWeight ? this.evaluateCriteriaTraining.criteriaWeight : 0);
            if(result)
            {
                this.evaluateCriteriaTraining.result = result;

                this.evaluateCriteriaTraining.measureCalcPercent = (measureCalc / result) * 100;
            }

        }
    }

}
