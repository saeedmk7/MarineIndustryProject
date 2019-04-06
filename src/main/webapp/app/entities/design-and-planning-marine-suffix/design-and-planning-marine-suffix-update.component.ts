import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IDesignAndPlanningMarineSuffix } from 'app/shared/model/design-and-planning-marine-suffix.model';
import { DesignAndPlanningMarineSuffixService } from './design-and-planning-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';
import { MahiatCourseMarineSuffixService } from 'app/entities/mahiat-course-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { ITeachTypeMarineSuffix } from 'app/shared/model/teach-type-marine-suffix.model';
import { TeachTypeMarineSuffixService } from 'app/entities/teach-type-marine-suffix';
import { ICourseLocationMarineSuffix } from 'app/shared/model/course-location-marine-suffix.model';
import { CourseLocationMarineSuffixService } from 'app/entities/course-location-marine-suffix';
import { IConditionsOfParticipantMarineSuffix } from 'app/shared/model/conditions-of-participant-marine-suffix.model';
import { ConditionsOfParticipantMarineSuffixService } from 'app/entities/conditions-of-participant-marine-suffix';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';
import { EffectivenessLevelMarineSuffixService } from 'app/entities/effectiveness-level-marine-suffix';
import { IToolsAndFacilityMarineSuffix } from 'app/shared/model/tools-and-facility-marine-suffix.model';
import { ToolsAndFacilityMarineSuffixService } from 'app/entities/tools-and-facility-marine-suffix';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import { ITeachTechniqueMarineSuffix } from 'app/shared/model/teach-technique-marine-suffix.model';
import { TeachTechniqueMarineSuffixService } from 'app/entities/teach-technique-marine-suffix';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';
import { EffectivenessIndexMarineSuffixService } from 'app/entities/effectiveness-index-marine-suffix';

@Component({
    selector: 'mi-design-and-planning-marine-suffix-update',
    templateUrl: './design-and-planning-marine-suffix-update.component.html'
})
export class DesignAndPlanningMarineSuffixUpdateComponent implements OnInit {
    private _designAndPlanning: IDesignAndPlanningMarineSuffix;
    isSaving: boolean;

    people: IPersonMarineSuffix[];

    documents: IDocumentMarineSuffix[];

    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];

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
    finishedDate: string;
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private designAndPlanningService: DesignAndPlanningMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
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
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ designAndPlanning }) => {
            this.designAndPlanning = designAndPlanning;
        });
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.documentService.query().subscribe(
            (res: HttpResponse<IDocumentMarineSuffix[]>) => {
                this.documents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.finalNiazsanjiReportService.query().subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {
                this.finalniazsanjireports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.mahiatCourseService.query().subscribe(
            (res: HttpResponse<IMahiatCourseMarineSuffix[]>) => {
                this.mahiatcourses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    save() {
        this.isSaving = true;
        this.designAndPlanning.finishedDate = moment(this.finishedDate, DATE_TIME_FORMAT);
        this.designAndPlanning.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.designAndPlanning.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        this.designAndPlanning.archivedDate = moment(this.archivedDate, DATE_TIME_FORMAT);
        if (this.designAndPlanning.id !== undefined) {
            this.subscribeToSaveResponse(this.designAndPlanningService.update(this.designAndPlanning));
        } else {
            this.subscribeToSaveResponse(this.designAndPlanningService.create(this.designAndPlanning));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDesignAndPlanningMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IDesignAndPlanningMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackFinalNiazsanjiReportById(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
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
    get designAndPlanning() {
        return this._designAndPlanning;
    }

    set designAndPlanning(designAndPlanning: IDesignAndPlanningMarineSuffix) {
        this._designAndPlanning = designAndPlanning;
        this.finishedDate = moment(designAndPlanning.finishedDate).format(DATE_TIME_FORMAT);
        this.createDate = moment(designAndPlanning.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(designAndPlanning.modifyDate).format(DATE_TIME_FORMAT);
        this.archivedDate = moment(designAndPlanning.archivedDate).format(DATE_TIME_FORMAT);
    }
}
