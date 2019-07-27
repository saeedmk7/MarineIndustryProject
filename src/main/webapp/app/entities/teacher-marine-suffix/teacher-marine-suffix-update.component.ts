import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import {DATE_FORMAT, DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService, JhiLanguageService} from 'ng-jhipster';

import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { TeacherMarineSuffixService } from './teacher-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from 'app/entities/qualification-marine-suffix';
import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { FieldOfStudyMarineSuffixService } from 'app/entities/field-of-study-marine-suffix';
import { IServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';
import { ServiceUnitMarineSuffixService } from 'app/entities/service-unit-marine-suffix';
import { IAcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';
import { AcademicRankMarineSuffixService } from 'app/entities/academic-rank-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import * as persianMoment from 'jalali-moment';

@Component({
    selector: 'mi-teacher-marine-suffix-update',
    templateUrl: './teacher-marine-suffix-update.component.html'
})
export class TeacherMarineSuffixUpdateComponent implements OnInit {
    private _teacher: ITeacherMarineSuffix;
    isSaving: boolean;

    qualifications: IQualificationMarineSuffix[];

    fieldofstudies: IFieldOfStudyMarineSuffix[];

    serviceunits: IServiceUnitMarineSuffix[];

    academicranks: IAcademicRankMarineSuffix[];

    isfa: boolean;

    issueDate: string;
    expirationDate: string;
    licenseRenewalDate: string;
    createDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private teacherService: TeacherMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private qualificationService: QualificationMarineSuffixService,
        private fieldOfStudyService: FieldOfStudyMarineSuffixService,
        private serviceUnitService: ServiceUnitMarineSuffixService,
        private academicRankService: AcademicRankMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private languageManager: JhiLanguageService,
        private router: Router
    ) {
        this.isfa = languageManager.currentLang == 'fa';
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teacher }) => {
            this.teacher = teacher;
        });
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualificationMarineSuffix[]>) => {
                this.qualifications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if(this.fieldOfStudyService.fieldOfStudies)
        {
            this.fieldofstudies = this.fieldOfStudyService.fieldOfStudies;
        }
        else {
            this.fieldOfStudyService.query().subscribe(
                (res: HttpResponse<IFieldOfStudyMarineSuffix[]>) => {
                    this.fieldofstudies = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.serviceUnitService.query().subscribe(
            (res: HttpResponse<IServiceUnitMarineSuffix[]>) => {
                this.serviceunits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.academicRankService.query().subscribe(
            (res: HttpResponse<IAcademicRankMarineSuffix[]>) => {
                this.academicranks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    change(i){
        this.router.navigateByUrl(i);
    }
    previousState() {
        window.history.back();
    }
    get fullName(){
        return (this.teacher.name ? this.teacher.name : "") + " " + (this.teacher.family ? this.teacher.family : "")
    }
    save() {
        this.isSaving = true;
        this.teacher.status = 0;
        this.teacher.code = +this.teacher.phoneNumber;
        if(this.isfa)
        {
            let issueDatePersian: string = persianMoment.from(this.issueDate, 'fa', 'YYYY-MM-DD').format('YYYY/MM/DD');
            this.teacher.issueDate = moment(issueDatePersian, DATE_TIME_FORMAT);

            let expirationDatePersian: string = persianMoment.from(this.expirationDate, 'fa', 'YYYY-MM-DD').format('YYYY/MM/DD');
            this.teacher.expirationDate = moment(expirationDatePersian, DATE_TIME_FORMAT);

            let licenseRenewalDatePersian: string = persianMoment.from(this.licenseRenewalDate, 'fa', 'YYYY-MM-DD').format('YYYY/MM/DD');
            this.teacher.licenseRenewalDate = moment(licenseRenewalDatePersian, DATE_TIME_FORMAT);

        }
        else {
        this.teacher.issueDate = moment(this.issueDate, DATE_TIME_FORMAT);
        this.teacher.expirationDate = moment(this.expirationDate, DATE_TIME_FORMAT);
        this.teacher.licenseRenewalDate = moment(this.licenseRenewalDate, DATE_TIME_FORMAT);
        }
        if (this.teacher.id !== undefined) {
            this.subscribeToSaveResponse(this.teacherService.update(this.teacher));
        } else {
            this.subscribeToSaveResponse(this.teacherService.create(this.teacher));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITeacherMarineSuffix>>) {
        result.subscribe((res: HttpResponse<ITeacherMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackQualificationById(index: number, item: IQualificationMarineSuffix) {
        return item.id;
    }

    trackFieldOfStudyById(index: number, item: IFieldOfStudyMarineSuffix) {
        return item.id;
    }

    trackServiceUnitById(index: number, item: IServiceUnitMarineSuffix) {
        return item.id;
    }

    trackAcademicRankById(index: number, item: IAcademicRankMarineSuffix) {
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
    get teacher() {
        return this._teacher;
    }

    set teacher(teacher: ITeacherMarineSuffix) {
        this._teacher = teacher;
        if(this.isfa)
        {
            if(teacher.issueDate)
            this.issueDate = moment(teacher.issueDate).format(DATE_FORMAT);
        else
            this.issueDate = moment().format(DATE_FORMAT);


            if(teacher.expirationDate)
                this.expirationDate = moment(teacher.expirationDate).format(DATE_FORMAT);
            else
                this.expirationDate = moment().format(DATE_FORMAT);

            if(teacher.licenseRenewalDate)
                this.licenseRenewalDate = moment(teacher.licenseRenewalDate).format(DATE_FORMAT);
            else
                this.licenseRenewalDate = moment().format(DATE_FORMAT);

        }
        else {
            this.issueDate = moment(teacher.issueDate).format(DATE_TIME_FORMAT);
            this.expirationDate = moment(teacher.expirationDate).format(DATE_TIME_FORMAT);
            this.licenseRenewalDate = moment(teacher.licenseRenewalDate).format(DATE_TIME_FORMAT);
        }
    }
}
