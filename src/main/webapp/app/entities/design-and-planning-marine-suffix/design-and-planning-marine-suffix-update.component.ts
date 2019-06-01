import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService} from 'ng-jhipster';

import {IDesignAndPlanningMarineSuffix} from 'app/shared/model/design-and-planning-marine-suffix.model';
import {DesignAndPlanningMarineSuffixService} from './design-and-planning-marine-suffix.service';
import {PersonMarineSuffixService} from 'app/entities/person-marine-suffix';
import {IFinalNiazsanjiReportMarineSuffix} from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import {FinalNiazsanjiReportMarineSuffixService} from 'app/entities/final-niazsanji-report-marine-suffix';
import {IMahiatCourseMarineSuffix} from 'app/shared/model/mahiat-course-marine-suffix.model';
import {MahiatCourseMarineSuffixService} from 'app/entities/mahiat-course-marine-suffix';
import {ICourseTypeMarineSuffix} from 'app/shared/model/course-type-marine-suffix.model';
import {CourseTypeMarineSuffixService} from 'app/entities/course-type-marine-suffix';
import {ITeachTypeMarineSuffix} from 'app/shared/model/teach-type-marine-suffix.model';
import {TeachTypeMarineSuffixService} from 'app/entities/teach-type-marine-suffix';
import {ICourseLocationMarineSuffix} from 'app/shared/model/course-location-marine-suffix.model';
import {CourseLocationMarineSuffixService} from 'app/entities/course-location-marine-suffix';
import {IConditionsOfParticipantMarineSuffix} from 'app/shared/model/conditions-of-participant-marine-suffix.model';
import {ConditionsOfParticipantMarineSuffixService} from 'app/entities/conditions-of-participant-marine-suffix';
import {IEffectivenessLevelMarineSuffix} from 'app/shared/model/effectiveness-level-marine-suffix.model';
import {EffectivenessLevelMarineSuffixService} from 'app/entities/effectiveness-level-marine-suffix';
import {IToolsAndFacilityMarineSuffix} from 'app/shared/model/tools-and-facility-marine-suffix.model';
import {ToolsAndFacilityMarineSuffixService} from 'app/entities/tools-and-facility-marine-suffix';
import {ITeachingApproachMarineSuffix} from 'app/shared/model/teaching-approach-marine-suffix.model';
import {TeachingApproachMarineSuffixService} from 'app/entities/teaching-approach-marine-suffix';
import {ITeachTechniqueMarineSuffix} from 'app/shared/model/teach-technique-marine-suffix.model';
import {TeachTechniqueMarineSuffixService} from 'app/entities/teach-technique-marine-suffix';
import {IEffectivenessIndexMarineSuffix} from 'app/shared/model/effectiveness-index-marine-suffix.model';
import {EffectivenessIndexMarineSuffixService} from 'app/entities/effectiveness-index-marine-suffix';
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {Principal} from "app/core";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {MONTHS} from "app/shared/constants/months.constants";
import * as $ from 'jquery';

@Component({
    selector: 'mi-design-and-planning-marine-suffix-update',
    templateUrl: './design-and-planning-marine-suffix-update.component.html'
})
export class DesignAndPlanningMarineSuffixUpdateComponent implements OnInit {
    isSaving: boolean;
    educationalModule: IEducationalModuleMarineSuffix = {};

    runMonths: any = MONTHS;
    people: IPersonMarineSuffix[];
    finalniazsanjireport: IFinalNiazsanjiReportMarineSuffix;
    finalniazsanjireportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    mahiatcourses: IMahiatCourseMarineSuffix[];
    coursetypes: ICourseTypeMarineSuffix[];
    teachtypes: ITeachTypeMarineSuffix[];
    courselocations: ICourseLocationMarineSuffix[];
    conditionsofparticipants: IConditionsOfParticipantMarineSuffix[];
    effectivenesslevels: IEffectivenessLevelMarineSuffix[];

    toolsandfacilities: IToolsAndFacilityMarineSuffix[];
    teachingapproaches: ITeachingApproachMarineSuffix[];
    teachtechniques: ITeachTechniqueMarineSuffix[];
    effectivenessindices: IEffectivenessIndexMarineSuffix[];

    isAdmin: boolean = false;
    isModirKolAmozesh: boolean;
    isKarshenasArshadAmozesh: boolean;
    currentAccount: any;

    documentUrl: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private designAndPlanningService: DesignAndPlanningMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private mahiatCourseService: MahiatCourseMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private teachTypeService: TeachTypeMarineSuffixService,
        private courseLocationService: CourseLocationMarineSuffixService,
        private conditionsOfParticipantService: ConditionsOfParticipantMarineSuffixService,
        private effectivenessLevelService: EffectivenessLevelMarineSuffixService,
        private toolsAndFacilityService: ToolsAndFacilityMarineSuffixService,
        private teachingApproachService: TeachingApproachMarineSuffixService,
        private teachTechniqueService: TeachTechniqueMarineSuffixService,
        private effectivenessIndexService: EffectivenessIndexMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {
    }

    private _designAndPlanning: IDesignAndPlanningMarineSuffix;

    get designAndPlanning() {
        return this._designAndPlanning;
    }

    set designAndPlanning(designAndPlanning: IDesignAndPlanningMarineSuffix) {
        this._designAndPlanning = designAndPlanning;
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({designAndPlanning}) => {

            this.designAndPlanning = designAndPlanning;
            const criteriaDesign = [{
                key: 'finalNiazsanjiReportId.equals',
                value: this.designAndPlanning.finalNiazsanjiReportId
            }];
            this.designAndPlanningService
                .query({
                    page: 0,
                    size: 200,
                    criteria: criteriaDesign,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IDesignAndPlanningMarineSuffix[]>) => {

                    if (resp.body.length > 0) {
                        this.designAndPlanning =  resp.body[0];

                        this.documentUrl = 'document-marine-suffix/designandplanning/' + this.designAndPlanning.id;
                    }
                });
            this.finalNiazsanjiReportService.find(this.designAndPlanning.finalNiazsanjiReportId).subscribe(
                (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => {

                    this.finalniazsanjireport = res.body;
                    this.designAndPlanning.directCost = this.finalniazsanjireport.priceCost;
                    this.designAndPlanning.courseTypeId = this.finalniazsanjireport.courseTypeId;
                    this.designAndPlanning.educationalModuleId = this.finalniazsanjireport.educationalModuleId;
                    this.designAndPlanning.organizationChartId = this.finalniazsanjireport.organizationChartId;
                    this.educationalModuleService.find(this.finalniazsanjireport.educationalModuleId).subscribe(
                        (res: HttpResponse<IEducationalModuleMarineSuffix>) => {

                            this.educationalModule = res.body;
                        },
                        (res: HttpErrorResponse) => this.onError(res.message));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );

            const criteria = [{
                key: 'finalNiazsanjiReportId.equals',
                value: this.designAndPlanning.finalNiazsanjiReportId
            }];
            this.finalNiazsanjiReportPersonService.query({
                page: 0,
                size: 20000,
                criteria: criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {
                    this.finalniazsanjireportPeople = resp.body;
                    /*if (resp.body.length > 0) {
                        const personIds = resp.body.map(a => a.personId);
                        const criteria1 = [{
                            key: 'id.in',
                            value: personIds
                        }];
                        this.personService.query({
                            page: 0,
                            size: 20000,
                            criteria: criteria1,
                            sort: ["id", "asc"]
                        }).subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                                this.people = res.body;
                            },
                            (res: HttpErrorResponse) => this.onError(res.message));
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message))

        });

        this.principal.identity().then(account => {
            this.currentAccount = account;
            if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }
        });

        /*this.mahiatCourseService.query().subscribe(
            (res: HttpResponse<IMahiatCourseMarineSuffix[]>) => {
                this.mahiatcourses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teachTypeService.query().subscribe(
            (res: HttpResponse<ITeachTypeMarineSuffix[]>) => {
                this.teachtypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.courseLocationService.query().subscribe(
            (res: HttpResponse<ICourseLocationMarineSuffix[]>) => {
                this.courselocations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.conditionsOfParticipantService.query().subscribe(
            (res: HttpResponse<IConditionsOfParticipantMarineSuffix[]>) => {
                this.conditionsofparticipants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.effectivenessLevelService.query().subscribe(
            (res: HttpResponse<IEffectivenessLevelMarineSuffix[]>) => {
                this.effectivenesslevels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.toolsAndFacilityService.query().subscribe(
            (res: HttpResponse<IToolsAndFacilityMarineSuffix[]>) => {
                this.toolsandfacilities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix[]>) => {
                this.teachingapproaches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teachTechniqueService.query().subscribe(
            (res: HttpResponse<ITeachTechniqueMarineSuffix[]>) => {
                this.teachtechniques = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.effectivenessIndexService.query().subscribe(
            (res: HttpResponse<IEffectivenessIndexMarineSuffix[]>) => {
                this.effectivenessindices = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    change(i) {

        this.router.navigateByUrl(i);
    }

    save() {


        this.isSaving = true;
        if(!this.designAndPlanning.finished){
            this.designAndPlanning.directCost = this.designAndPlanning.directCost == undefined ? 0 : this.designAndPlanning.directCost;
        }
        this.designAndPlanning.undirectCost = this.designAndPlanning.undirectCost == undefined ? 0 : this.designAndPlanning.undirectCost;
        this.designAndPlanning.status = this.designAndPlanning.status == undefined ? 0 : this.designAndPlanning.status;
        this.designAndPlanning.step = this.designAndPlanning.step == undefined ? 0 : this.designAndPlanning.step;

        if (this.designAndPlanning.id !== undefined) {
            this.subscribeToSaveResponse(this.designAndPlanningService.update(this.designAndPlanning));
        } else {
            this.subscribeToSaveResponse(this.designAndPlanningService.create(this.designAndPlanning));
        }
    }
    finalize(){
        /*if(this.designAndPlanning.runMonth) {*/
            this.designAndPlanning.finished = true;
            $('#save-entity').trigger('click');
        /*}
        else{
            this.onError("برای تایید نهایی ماه اجرا را باید وارد نمائید.")
        }*/
    }
    trackMahiatCourseById(index: number, item: IMahiatCourseMarineSuffix) {
        return item.id;
    }

    trackCourseTypeById(index: number, item: ICourseTypeMarineSuffix) {
        return item.id;
    }

    trackTeachTypeById(index: number, item: ITeachTypeMarineSuffix) {
        return item.id;
    }

    trackCourseLocationById(index: number, item: ICourseLocationMarineSuffix) {
        return item.id;
    }

    trackConditionsOfParticipantById(index: number, item: IConditionsOfParticipantMarineSuffix) {
        return item.id;
    }

    trackEffectivenessLevelById(index: number, item: IEffectivenessLevelMarineSuffix) {
        return item.id;
    }

    trackToolsAndFacilityById(index: number, item: IToolsAndFacilityMarineSuffix) {
        return item.id;
    }

    trackTeachingApproachById(index: number, item: ITeachingApproachMarineSuffix) {
        return item.id;
    }

    trackTeachTechniqueById(index: number, item: ITeachTechniqueMarineSuffix) {
        return item.id;
    }

    trackEffectivenessIndexById(index: number, item: IEffectivenessIndexMarineSuffix) {
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

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDesignAndPlanningMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IDesignAndPlanningMarineSuffix>) => this.onSaveSuccess(res.body),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess(res: IDesignAndPlanningMarineSuffix) {
        this.isSaving = false;
        if(res.finished)
            this.previousState();
        else {
            this.designAndPlanning = res;
            this.documentUrl = 'document-marine-suffix/designandplanning/' + this.designAndPlanning.id;
        }
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
