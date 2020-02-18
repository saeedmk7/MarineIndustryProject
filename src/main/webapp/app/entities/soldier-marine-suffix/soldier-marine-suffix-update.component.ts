import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';
import { SoldierMarineSuffixService } from './soldier-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from 'app/entities/qualification-marine-suffix';
import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { FieldOfStudyMarineSuffixService } from 'app/entities/field-of-study-marine-suffix';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from 'app/entities/job-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import * as persianMoment from 'jalali-moment';
import {PERSONELCODE_ALREADY_USED_TYPE} from "app/shared";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Component({
    selector: 'mi-soldier-marine-suffix-update',
    templateUrl: './soldier-marine-suffix-update.component.html'
})
export class SoldierMarineSuffixUpdateComponent implements OnInit {
    private _soldier: ISoldierMarineSuffix;

    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    qualifications: IQualificationMarineSuffix[];

    fieldofstudies: IFieldOfStudyMarineSuffix[];

    jobs: IJobMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];
    groups: IOrganizationChartMarineSuffix[];
    birthDate: string;
    releaseDate: string;
    employmentDate: string;

    message: string;
    dateBirthDateValid: number;
    dateEmploymentDateValid: number;
    dateReleaseDateValid: number;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected soldierService: SoldierMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected qualificationService: QualificationMarineSuffixService,
        protected fieldOfStudyService: FieldOfStudyMarineSuffixService,
        protected jobService: JobMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private personService: PersonMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ soldier }) => {
            this.soldier = soldier;
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
       /* this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.groups = this.organizationcharts.filter(w => !w.parentId);
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.groups = this.organizationcharts.filter(w => !w.parentId);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    previousState() {
        window.history.back();
    }

    checkDateValidation(event, dateType) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                if (dateType == 1) {
                    this.dateBirthDateValid = 1;
                } else if(dateType == 2) {
                    this.dateEmploymentDateValid = 1;
                } else if(dateType == 3) {
                    this.dateReleaseDateValid = 1;
                }

            }
            else {
                if (dateType == 1) {
                    this.dateBirthDateValid = 2;
                } else if(dateType == 2) {
                    this.dateEmploymentDateValid = 2;
                } else if(dateType == 3) {
                    this.dateReleaseDateValid = 2;
                }
            }
        }
        catch (e) {
            if (dateType == 1) {
                this.dateBirthDateValid = 2;
            } else if(dateType == 2) {
                this.dateEmploymentDateValid = 2;
            } else if(dateType == 3) {
                this.dateReleaseDateValid = 2;
            }
        }
    }

    save() {
        this.isSaving = true;
        this.soldier.status = 0;

        if (this.soldier.employmentDatePersian) {
            this.soldier.employmentDate = this.convertObjectDatesService.shamsi2miladiMoment(this.soldier.employmentDatePersian);
        }
        if (this.soldier.birthDatePersian) {
            this.soldier.birthDate = this.convertObjectDatesService.shamsi2miladiMoment(this.soldier.birthDatePersian);
        }
        if (this.soldier.releaseDatePersian) {
            this.soldier.releaseDate = this.convertObjectDatesService.shamsi2miladiMoment(this.soldier.releaseDatePersian);
        }

        if (this.soldier.id !== undefined) {
            this.subscribeToSaveResponse(this.soldierService.update(this.soldier));
        } else {
            this.subscribeToSaveResponse(this.soldierService.create(this.soldier));
        }
    }

    change(i) {
        this.router.navigateByUrl(i);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISoldierMarineSuffix>>) {
        result.subscribe((res: HttpResponse<ISoldierMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError(res));
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError(response: HttpErrorResponse) {
        this.isSaving = false;
        if (response.status === 400 && response.error.type === PERSONELCODE_ALREADY_USED_TYPE) {
            this.message = 'این کدملی برای فرد دیگری استفاده شده است لطفا کدی دیگر انتخاب نمائید.';
        }
        else {
            this.message = 'این کدملی برای فرد دیگری استفاده شده است لطفا کدی دیگر انتخاب نمائید.';
        }
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackQualificationById(index: number, item: IQualificationMarineSuffix) {
        return item.id;
    }

    trackFieldOfStudyById(index: number, item: IFieldOfStudyMarineSuffix) {
        return item.id;
    }

    trackJobById(index: number, item: IJobMarineSuffix) {
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

    get soldier() {
        return this._soldier;
    }
    set soldier(soldier: ISoldierMarineSuffix) {

        if (soldier.birthDate && soldier.birthDate.isValid()) {
            soldier.birthDatePersian = this.convertObjectDatesService.miladi2ShamsiMoment(soldier.birthDate);
        }

        if (soldier.employmentDate && soldier.employmentDate.isValid()) {
            soldier.employmentDatePersian = this.convertObjectDatesService.miladi2ShamsiMoment(soldier.employmentDate);
        }

        if(soldier.releaseDate && soldier.employmentDate.isValid())
        {
            soldier.releaseDatePersian = this.convertObjectDatesService.miladi2ShamsiMoment(soldier.releaseDate);
        }
        this._soldier = soldier;

    }
    get fullName() {
        return (this.soldier.name ? this.soldier.name : "") + " " + (this.soldier.family ? this.soldier.family : "")
    }
}
