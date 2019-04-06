import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import * as moment from 'moment';
import {DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {IDocumentMarineSuffix} from 'app/shared/model/document-marine-suffix.model';
import {DocumentMarineSuffixService} from './document-marine-suffix.service';
import {IPersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import {PersonMarineSuffixService} from 'app/entities/person-marine-suffix';
import {ITeacherMarineSuffix} from 'app/shared/model/teacher-marine-suffix.model';
import {TeacherMarineSuffixService} from 'app/entities/teacher-marine-suffix';
import {IJobMarineSuffix} from 'app/shared/model/job-marine-suffix.model';
import {JobMarineSuffixService} from 'app/entities/job-marine-suffix';
import {IEducationalModuleMarineSuffix} from 'app/shared/model/educational-module-marine-suffix.model';
import {EducationalModuleMarineSuffixService} from 'app/entities/educational-module-marine-suffix';
import {IEducationalCenterMarineSuffix} from 'app/shared/model/educational-center-marine-suffix.model';
import {EducationalCenterMarineSuffixService} from 'app/entities/educational-center-marine-suffix';
import {IResourceMarineSuffix} from 'app/shared/model/resource-marine-suffix.model';
import {ResourceMarineSuffixService} from 'app/entities/resource-marine-suffix';
import {IRequestOrganizationNiazsanjiMarineSuffix} from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import {RequestOrganizationNiazsanjiMarineSuffixService} from 'app/entities/request-organization-niazsanji-marine-suffix';
import {IFinalOrganizationNiazsanjiMarineSuffix} from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import {FinalOrganizationNiazsanjiMarineSuffixService} from 'app/entities/final-organization-niazsanji-marine-suffix';
import {IFinalNiazsanjiReportMarineSuffix} from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import {FinalNiazsanjiReportMarineSuffixService} from 'app/entities/final-niazsanji-report-marine-suffix';
import {IDesignAndPlanningMarineSuffix} from 'app/shared/model/design-and-planning-marine-suffix.model';
import {DesignAndPlanningMarineSuffixService} from 'app/entities/design-and-planning-marine-suffix';
import {IRunPhaseMarineSuffix} from 'app/shared/model/run-phase-marine-suffix.model';
import {RunPhaseMarineSuffixService} from 'app/entities/run-phase-marine-suffix';

@Component({
    selector: 'mi-document-marine-suffix-update',
    templateUrl: './document-marine-suffix-update.component.html'
})
export class DocumentMarineSuffixUpdateComponent implements OnInit {
    private _document: IDocumentMarineSuffix;
    isSaving: boolean;

    people: IPersonMarineSuffix[];

    teachers: ITeacherMarineSuffix[];

    jobs: IJobMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    educationalcenters: IEducationalCenterMarineSuffix[];

    resources: IResourceMarineSuffix[];

    requestorganizationniazsanjis: IRequestOrganizationNiazsanjiMarineSuffix[];

    finalorganizationniazsanjis: IFinalOrganizationNiazsanjiMarineSuffix[];

    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];

    designandplannings: IDesignAndPlanningMarineSuffix[];

    runphases: IRunPhaseMarineSuffix[];

    createDate: string;
    modifyDate: string;
    progress: { percentage: number } = { percentage: 0 }
    file: File;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private documentService: DocumentMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private teacherService: TeacherMarineSuffixService,
        private jobService: JobMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private educationalCenterService: EducationalCenterMarineSuffixService,
        private resourceService: ResourceMarineSuffixService,
        private requestOrganizationNiazsanjiService: RequestOrganizationNiazsanjiMarineSuffixService,
        private finalOrganizationNiazsanjiService: FinalOrganizationNiazsanjiMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private designAndPlanningService: DesignAndPlanningMarineSuffixService,
        private runPhaseService: RunPhaseMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ document }) => {
            this.activatedRoute.params.subscribe((params) => {
                this.document = document;

                this.document.entityName = params['entityName'];
                this.document.entityId = params['entityId'];
            });

        });

    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {

        this.dataUtils.setFileData(event, entity, field, isImage);
        if (event && event.target.files && event.target.files[0]) {
            this.file = event.target.files[0];
        }
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.progress.percentage = 0;
        this.isSaving = true;

        let formdata: FormData = new FormData();

        formdata.append('file', this.file);
        formdata.append('entityId', this.document.entityId);
        formdata.append('entityName', this.document.entityName);
        formdata.append('title', this.document.title);
        this.documentService.create(formdata).subscribe(event =>{
           if(event.type === HttpEventType.UploadProgress){
               this.progress.percentage = Math.round(100 * event.loaded / event.total);
           }
           else if(event instanceof HttpResponse){
               this.onSaveSuccess();
           }
        },
            () => this.onSaveError());

        /*this.subscribeToSaveResponse();*/
    }

    /*private subscribeToSaveResponse(result: Observable<HttpResponse<IDocumentMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IDocumentMarineSuffix>) => {
                if(res.type === HttpEventType.UploadProgress){

                }
                this.onSaveSuccess()
            },
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }*/

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

    trackTeacherById(index: number, item: ITeacherMarineSuffix) {
        return item.id;
    }

    trackJobById(index: number, item: IJobMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackEducationalCenterById(index: number, item: IEducationalCenterMarineSuffix) {
        return item.id;
    }

    trackResourceById(index: number, item: IResourceMarineSuffix) {
        return item.id;
    }

    trackRequestOrganizationNiazsanjiById(index: number, item: IRequestOrganizationNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackFinalOrganizationNiazsanjiById(index: number, item: IFinalOrganizationNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackFinalNiazsanjiReportById(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
    }

    trackDesignAndPlanningById(index: number, item: IDesignAndPlanningMarineSuffix) {
        return item.id;
    }

    trackRunPhaseById(index: number, item: IRunPhaseMarineSuffix) {
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
    get document() {
        return this._document;
    }

    set document(document: IDocumentMarineSuffix) {

        this._document = document;
        this.createDate = moment(document.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(document.modifyDate).format(DATE_TIME_FORMAT);
       /* this.activatedRoute.params.subscribe((params) => {

            this.document.entityName = params['entityName'];
            this.document.entityId = params['entityId'];
        });*/
    }
}
