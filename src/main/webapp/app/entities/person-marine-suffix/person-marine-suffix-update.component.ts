import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from './person-marine-suffix.service';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from 'app/entities/qualification-marine-suffix';
import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { FieldOfStudyMarineSuffixService } from 'app/entities/field-of-study-marine-suffix';
import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { EmploymentTypeMarineSuffixService } from 'app/entities/employment-type-marine-suffix';
import { IWorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';
import { WorkGroupMarineSuffixService } from 'app/entities/work-group-marine-suffix';
import { IWorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';
import { WorkIndustryMarineSuffixService } from 'app/entities/work-industry-marine-suffix';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from 'app/entities/job-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import * as persianMoment from 'jalali-moment';
import { JhiLanguageService } from 'ng-jhipster/src/language/language.service';
import { PERSONELCODE_ALREADY_USED_TYPE } from 'app/shared';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import * as moment from 'moment';

@Component({
    selector: 'mi-person-marine-suffix-update',
    templateUrl: './person-marine-suffix-update.component.html'
})
export class PersonMarineSuffixUpdateComponent implements OnInit {
    private _person: IPersonMarineSuffix;
    isSaving: boolean;

    qualifications: IQualificationMarineSuffix[];

    fieldofstudies: IFieldOfStudyMarineSuffix[];

    employmenttypes: IEmploymentTypeMarineSuffix[];

    workgroups: IWorkGroupMarineSuffix[];

    workindustries: IWorkIndustryMarineSuffix[];

    jobs: IJobMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    isfa: boolean;

    birthDateChange: boolean = false;
    employmentDateChange: boolean = false;

    message: string;
    retiredDate: string;

    dateBirthDateValid: number;
    dateEmploymentDateValid: number;

    constructor(
        private jhiAlertService: JhiAlertService,
        private personService: PersonMarineSuffixService,
        private qualificationService: QualificationMarineSuffixService,
        private fieldOfStudyService: FieldOfStudyMarineSuffixService,
        private employmentTypeService: EmploymentTypeMarineSuffixService,
        private workGroupService: WorkGroupMarineSuffixService,
        private workIndustryService: WorkIndustryMarineSuffixService,
        private jobService: JobMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private activatedRoute: ActivatedRoute,
        private languageManager: JhiLanguageService,
        private router: Router
    ) {
        this.isfa = languageManager.currentLang == 'fa';
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ person }) => {
            this.person = person;
            if (this.person.employmentDate && this.person.employmentDate.isValid()) {
                this.person.yearOfService = this.convertObjectDatesService.getYearsOfService(this.person.employmentDate);
                this.retiredDate = this.convertObjectDatesService.miladi2ShamsiMoment(this.person.employmentDate.add(30, 'years'));
            } else this.retiredDate = '';
        });
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualificationMarineSuffix[]>) => {
                this.qualifications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.fieldOfStudyService.fieldOfStudies) {
            this.fieldofstudies = this.fieldOfStudyService.fieldOfStudies;
        } else {
            this.fieldOfStudyService.query().subscribe(
                (res: HttpResponse<IFieldOfStudyMarineSuffix[]>) => {
                    this.fieldofstudies = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.employmentTypeService.query().subscribe(
            (res: HttpResponse<IEmploymentTypeMarineSuffix[]>) => {
                this.employmenttypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.workGroupService.query().subscribe(
            (res: HttpResponse<IWorkGroupMarineSuffix[]>) => {
                this.workgroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.workIndustryService.query().subscribe(
            (res: HttpResponse<IWorkIndustryMarineSuffix[]>) => {
                this.workindustries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
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
                } else {
                    this.dateEmploymentDateValid = 1;
                }
            } else {
                if (dateType == 1) {
                    this.dateBirthDateValid = 2;
                } else {
                    this.dateEmploymentDateValid = 2;
                }
            }
        } catch (e) {
            if (dateType == 1) {
                this.dateBirthDateValid = 2;
            } else {
                this.dateEmploymentDateValid = 2;
            }
        }
    }

    save() {
        this.isSaving = true;
        this.person.status = 0;

        if (this.person.employmentDatePersian) {
            this.person.employmentDate = this.convertObjectDatesService.shamsi2miladiMoment(this.person.employmentDatePersian);
        }
        if (this.person.birthDatePersian) {
            this.person.birthDate = this.convertObjectDatesService.shamsi2miladiMoment(this.person.birthDatePersian);
        }
        if (this.person.id !== undefined) {
            this.subscribeToSaveResponse(this.personService.update(this.person));
        } else {
            this.subscribeToSaveResponse(this.personService.create(this.person));
        }
    }

    isValidDate(date) {
        return date instanceof Date && !isNaN(date.getDate());
    }

    change(i) {
        this.router.navigateByUrl(i);
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPersonMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPersonMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError(res)
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError(response: HttpErrorResponse) {
        this.isSaving = false;
        if (response.status === 400 && response.error.type === PERSONELCODE_ALREADY_USED_TYPE) {
            this.message = 'این کدملی برای فرد دیگری استفاده شده است لطفا کدی دیگر انتخاب نمائید.';
        } else {
            this.message = 'این کدملی برای فرد دیگری استفاده شده است لطفا کدی دیگر انتخاب نمائید.';
        }
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

    trackEmploymentTypeById(index: number, item: IEmploymentTypeMarineSuffix) {
        return item.id;
    }

    trackWorkGroupById(index: number, item: IWorkGroupMarineSuffix) {
        return item.id;
    }

    trackWorkIndustryById(index: number, item: IWorkIndustryMarineSuffix) {
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

    get person() {
        return this._person;
    }

    get fullName() {
        return (this.person.name ? this.person.name : '') + ' ' + (this.person.family ? this.person.family : '');
    }

    set person(person: IPersonMarineSuffix) {
        if (person.birthDate && person.birthDate.isValid()) {
            person.birthDatePersian = this.convertObjectDatesService.miladi2ShamsiMoment(person.birthDate);
        }

        if (person.employmentDate && person.employmentDate.isValid()) {
            person.employmentDatePersian = this.convertObjectDatesService.miladi2ShamsiMoment(person.employmentDate);
        }

        this._person = person;
    }
    get yearsOfService(): string {
        if (this.person && this.person.employmentDate) {
            const result = this.getAge(new Date(this.person.employmentDate.toDate()), new Date());
            return result;
        }
        return 'تاریخ استخدام شما در سیستم ثبت نشده است.';
    }
    getAge(date_1, date_2) {
        let date2_UTC = new Date(Date.UTC(date_2.getUTCFullYear(), date_2.getUTCMonth(), date_2.getUTCDate()));
        let date1_UTC = new Date(Date.UTC(date_1.getUTCFullYear(), date_1.getUTCMonth(), date_1.getUTCDate()));

        let yAppendix, mAppendix, dAppendix;

        let days = date2_UTC.getDate() - date1_UTC.getDate();
        if (days < 0) {
            date2_UTC.setMonth(date2_UTC.getMonth() - 1);
            days += this.daysInMonth(date2_UTC);
        }

        let months = date2_UTC.getMonth() - date1_UTC.getMonth();
        if (months < 0) {
            date2_UTC.setFullYear(date2_UTC.getFullYear() - 1);
            months += 12;
        }

        let years = date2_UTC.getFullYear() - date1_UTC.getFullYear();

        if (years > 1) yAppendix = ' سال';
        else yAppendix = ' سال';
        if (months > 1) mAppendix = ' ماه';
        else mAppendix = ' ماه';
        if (days > 1) dAppendix = ' روز';
        else dAppendix = ' روز';

        return years + yAppendix + ' و ' + months + mAppendix + ' و ' + days + dAppendix + '';
    }

    daysInMonth(date2_UTC) {
        const monthStart = new Date(date2_UTC.getFullYear(), date2_UTC.getMonth(), 1);
        const monthEnd = new Date(date2_UTC.getFullYear(), date2_UTC.getMonth() + 1, 1);
        const monthLength = (monthEnd.getDate() - monthStart.getDate()) / (1000 * 60 * 60 * 24);
        return monthLength;
    }
}
