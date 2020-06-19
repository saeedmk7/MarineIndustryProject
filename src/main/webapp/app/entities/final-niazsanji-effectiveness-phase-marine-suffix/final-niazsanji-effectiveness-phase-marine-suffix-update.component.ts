import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { INiazsanjiIntegrationMarineSuffix } from 'app/shared/model/niazsanji-integration-marine-suffix.model';
import { NiazsanjiIntegrationMarineSuffixService } from 'app/entities/niazsanji-integration-marine-suffix';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { TeacherMarineSuffixService } from 'app/entities/teacher-marine-suffix';
import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from 'app/entities/niazsanji-input-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';
import { MahiatCourseMarineSuffixService } from 'app/entities/mahiat-course-marine-suffix';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';

@Component({
    selector: 'mi-final-niazsanji-effectiveness-phase-marine-suffix-update',
    templateUrl: './final-niazsanji-effectiveness-phase-marine-suffix-update.component.html'
})
export class FinalNiazsanjiEffectivenessPhaseMarineSuffixUpdateComponent implements OnInit {
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    restrictions: IRestrictionMarineSuffix[];

    niazsanjiintegrations: INiazsanjiIntegrationMarineSuffix[];

    teachers: ITeacherMarineSuffix[];

    niazsanjiinputs: INiazsanjiInputMarineSuffix[];

    coursetypes: ICourseTypeMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    mahiatcourses: IMahiatCourseMarineSuffix[];

    teachingapproaches: ITeachingApproachMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;
    lastEffectivenessPhaseFinish: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected niazsanjiIntegrationService: NiazsanjiIntegrationMarineSuffixService,
        protected teacherService: TeacherMarineSuffixService,
        protected niazsanjiInputService: NiazsanjiInputMarineSuffixService,
        protected courseTypeService: CourseTypeMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected mahiatCourseService: MahiatCourseMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReport }) => {
            this.finalNiazsanjiReport = finalNiazsanjiReport;
            this.createDate =
                this.finalNiazsanjiReport.createDate != null ? this.finalNiazsanjiReport.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.finalNiazsanjiReport.modifyDate != null ? this.finalNiazsanjiReport.modifyDate.format(DATE_TIME_FORMAT) : null;
            this.archivedDate =
                this.finalNiazsanjiReport.archivedDate != null ? this.finalNiazsanjiReport.archivedDate.format(DATE_TIME_FORMAT) : null;
            this.lastEffectivenessPhaseFinish =
                this.finalNiazsanjiReport.lastEffectivenessPhaseFinish != null
                    ? this.finalNiazsanjiReport.lastEffectivenessPhaseFinish.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.documentService.query().subscribe(
            (res: HttpResponse<IDocumentMarineSuffix[]>) => {
                this.documents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.niazsanjiIntegrationService.query().subscribe(
            (res: HttpResponse<INiazsanjiIntegrationMarineSuffix[]>) => {
                this.niazsanjiintegrations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teacherService.query().subscribe(
            (res: HttpResponse<ITeacherMarineSuffix[]>) => {
                this.teachers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.niazsanjiInputService.query().subscribe(
            (res: HttpResponse<INiazsanjiInputMarineSuffix[]>) => {
                this.niazsanjiinputs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.organizationChartService.query().subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                this.organizationcharts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.mahiatCourseService.query().subscribe(
            (res: HttpResponse<IMahiatCourseMarineSuffix[]>) => {
                this.mahiatcourses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix[]>) => {
                this.teachingapproaches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.finalNiazsanjiReport.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.finalNiazsanjiReport.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        this.finalNiazsanjiReport.archivedDate = this.archivedDate != null ? moment(this.archivedDate, DATE_TIME_FORMAT) : null;
        this.finalNiazsanjiReport.lastEffectivenessPhaseFinish =
            this.lastEffectivenessPhaseFinish != null ? moment(this.lastEffectivenessPhaseFinish, DATE_TIME_FORMAT) : null;
        if (this.finalNiazsanjiReport.id !== undefined) {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportService.update(this.finalNiazsanjiReport));
        } else {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportService.create(this.finalNiazsanjiReport));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinalNiazsanjiReportMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => this.onSaveSuccess(),
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

    trackRestrictionById(index: number, item: IRestrictionMarineSuffix) {
        return item.id;
    }

    trackNiazsanjiIntegrationById(index: number, item: INiazsanjiIntegrationMarineSuffix) {
        return item.id;
    }

    trackTeacherById(index: number, item: ITeacherMarineSuffix) {
        return item.id;
    }

    trackNiazsanjiInputById(index: number, item: INiazsanjiInputMarineSuffix) {
        return item.id;
    }

    trackCourseTypeById(index: number, item: ICourseTypeMarineSuffix) {
        return item.id;
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackMahiatCourseById(index: number, item: IMahiatCourseMarineSuffix) {
        return item.id;
    }

    trackTeachingApproachById(index: number, item: ITeachingApproachMarineSuffix) {
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
