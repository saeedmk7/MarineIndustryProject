import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IUser, Principal, UserService} from "app/core";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IHomePagePersonEducationalModule} from "app/shared/model/custom/home-page-person-educational-module";
import {IEducationalModuleJobMarineSuffix} from "app/shared/model/educational-module-job-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {EducationalModuleJobMarineSuffixService} from "app/entities/educational-module-job-marine-suffix";
import {
    EducationalModuleMarineSuffix,
    IEducationalModuleMarineSuffix
} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {JobMarineSuffixService} from "app/entities/job-marine-suffix";
import {ICompetencyMarineSuffix} from "app/shared/model/competency-marine-suffix.model";
import {CompetencyMarineSuffixService} from 'app/entities/competency-marine-suffix';
import {IPreJobNiazsanjiCompetencyMarineSuffix} from "app/shared/model/pre-job-niazsanji-competency-marine-suffix.model";
import {IJobMarineSuffix} from "app/shared/model/job-marine-suffix.model";
import {PreJobNiazsanjiMarineSuffixService} from "app/entities/pre-job-niazsanji-marine-suffix";
import {SteppersPanelModel} from "app/shared/model/custom/steppers.model";

@Component({
    selector: 'mi-pre-job-niazsanji-steps-handler-marine-suffix-update',
    templateUrl: './pre-job-niazsanji-steps-handler-marine-suffix-update.component.html'
})
export class PreJobNiazsanjiStepsHandlerMarineSuffixUpdateComponent implements OnInit {
    preJobNiazsanji: IPreJobNiazsanjiMarineSuffix;
    isSaving: boolean;
    editable: boolean = true;
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];
    orgChartDisabled: boolean;
    job: IJobMarineSuffix = {};

    documents: IDocumentMarineSuffix[];

    people: IPersonMarineSuffix[];
    agents: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    allPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    currentAccount: any;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isTopUsers: boolean = false;
    isSuperUsers: boolean = false;



    competencies: ICompetencyMarineSuffix[];
    preJobNiazsanjiCompetencies: IPreJobNiazsanjiCompetencyMarineSuffix[] = [];

    stepsList: SteppersPanelModel[] = [];
    activeTab: number;
    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected jobService: JobMarineSuffixService,
        protected competencyService: CompetencyMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected finalNiazsanjiReportMarineSuffixService: FinalNiazsanjiReportMarineSuffixService,
        protected educationalModuleJobService: EducationalModuleJobMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private userService: UserService,
        private principal: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ preJobNiazsanji, editable }) => {

            this.editable = editable;
            this.preJobNiazsanji = preJobNiazsanji;
            this.reloadStepsList(this.preJobNiazsanji.step);
        });
        this.principal.identity().then(account => {

            this.currentAccount = account;
            this.setRoles(account);
        });
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

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
            this.isTopUsers = true;
    }
    reloadStepsList(step: number){
        this.stepsList = [];
        this.activeTab = step;
        this.stepsList.push({
                stepTitle: "گام اول",
                stepIndex: 1,
                activeStep: step,
                description: "تعیین استاندارد شایستگی های شغل"
            },
            {
                stepTitle: "گام دوم",
                stepIndex: 2,
                activeStep: step,
                description: "بررسی شایستگی ها"
            },{
                stepTitle: "گام سوم",
                stepIndex: 3,
                activeStep: step,
                description: "شناسایی راه حل ها برای رفع کمبودها"
            },{
                stepTitle: "گام چهارم",
                stepIndex: 4,
                activeStep: step,
                description: "ارتباط بین راه حل و دوره های آموزشی"
            },{
                stepTitle: "گام پنجم",
                stepIndex: 5,
                activeStep: step,
                description: "اولویت بندی و طراحی دوره"
            });
    }
    activeTabChanged(activeTab) {
        console.log(activeTab);
        this.activeTab = activeTab;
        this.updatePreJobNiazsanjiStep(this.activeTab);
    }
    updatePreJobNiazsanjiStep($event){

        this.preJobNiazsanji.step = $event;
        this.preJobNiazsanjiService.update(this.preJobNiazsanji).subscribe((res: HttpResponse<IPreJobNiazsanjiCompetencyMarineSuffix>) => this.onSaveSuccess(res),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    protected onSaveSuccess(res) {
        this.preJobNiazsanji = res.body;
        this.reloadStepsList(this.preJobNiazsanji.step);
    }
    protected onSaveError() {
    }
}
