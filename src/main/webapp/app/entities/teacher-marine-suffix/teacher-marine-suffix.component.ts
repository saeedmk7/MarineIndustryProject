import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { TeacherMarineSuffixService } from './teacher-marine-suffix.service';
import { PlatformLocation } from '@angular/common';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { TranslateService } from '@ngx-translate/core';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { QualificationMarineSuffixService } from 'app/entities/qualification-marine-suffix';
import { FieldOfStudyMarineSuffixService } from 'app/entities/field-of-study-marine-suffix';
import { IServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';
import { IAcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';
import { ServiceUnitMarineSuffixService } from 'app/entities/service-unit-marine-suffix';
import { AcademicRankMarineSuffixService } from 'app/entities/academic-rank-marine-suffix';
import { CommonSearchCheckerService } from 'app/plugin/utilities/common-search-checkers';
import { TeacherType } from 'app/shared/model/enums/TeacherType';
import { Grade } from 'app/shared/model/enums/Grade';
import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';

@Component({
    selector: 'mi-teacher-marine-suffix',
    templateUrl: './teacher-marine-suffix.component.html'
})
export class TeacherMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    teachers: ITeacherMarineSuffix[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    criteriaSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    searchbarModel: SearchPanelModel[] = [];
    done: boolean = false;
    criteria: any;
    unchangedCriteria: any;

    qualifications: IQualificationMarineSuffix[];
    fieldofstudies: IFieldOfStudyMarineSuffix[];

    serviceunits: IServiceUnitMarineSuffix[];
    academicranks: IAcademicRankMarineSuffix[];

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;
    isRoleEdit: boolean = false;
    isRoleDelete: boolean = false;

    constructor(
        private teacherService: TeacherMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private location: PlatformLocation,
        private jhiTranslate: TranslateService,
        private qualificationService: QualificationMarineSuffixService,
        private fieldOfStudyService: FieldOfStudyMarineSuffixService,
        private serviceUnitService: ServiceUnitMarineSuffixService,
        private academicRankService: AcademicRankMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.criteria = criteria.content;
            this.unchangedCriteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);
        });
    }

    loadAll(criteria?, exportExcel: boolean = false) {
        if (criteria) {
            const expire = criteria.find(a => a.key == 'expired.equals');
            if (expire) {
                let val = +expire;
                criteria = criteria.filter(a => a.key != 'expired.equals');
                if (val) {
                    let date = new Date().toISOString();
                    if (val == 1) {
                        criteria.push({
                            key: 'expirationDate.lessOrEqualThan',
                            value: date
                        });
                    }
                    if (val == 2) {
                        criteria.push({
                            key: 'expirationDate.greaterOrEqualThan',
                            value: date
                        });
                    }
                }
            }
        }
        if (exportExcel) {
            this.teacherService
                .query({
                    page: this.page - 1,
                    size: 20000,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ITeacherMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.teacherService
                .query({
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    criteria,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ITeacherMarineSuffix[]>) => this.paginateTeachers(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    export() {
        this.loadAll(this.criteria, true);
    }
    prepareForExportExcel(res: ITeacherMarineSuffix[]) {
        let a = new ExcelService(this.jhiTranslate);
        res = this.convertObjectDatesService.changeArrayDate(res);
        let report = [];
        let index: number = 0;
        res.forEach(a => {
            index++;

            let teacherType;
            this.jhiTranslate.get('marineindustryprojApp.TeacherType.' + a.teacherType).subscribe(w => (teacherType = w.toString()));

            let grade;
            this.jhiTranslate.get('marineindustryprojApp.Grade.' + a.grade).subscribe(w => (grade = w.toString()));

            let obj: Object;
            obj = {
                index: index,
                name: a.name,
                family: a.family,
                fatherName: a.fatherName,
                scientificBasis: a.scientificBasis,
                inquiry: a.inquiry,
                schoolConfirmation: a.schoolConfirmation,
                protectiveApproval: a.protectiveApproval,
                teachingSubject: a.teachingSubject,
                issueDate: a.issueDate,
                expirationDate: a.expirationDate,
                licenseNumber: a.licenseNumber,
                sessionNumber: a.sessionNumber,
                phoneNumber: a.phoneNumber,
                licenseRenewalDate: a.licenseRenewalDate,
                lastQualification: a.lastQualificationTitle,
                lastFieldOfStudy: a.lastFieldOfStudyTitle,
                serviceUnitTitle: a.serviceUnitTitle,
                academicRankTitle: a.academicRankTitle,
                teacherType: teacherType,
                grade: grade,
                createDate: a.createDate
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'teachers', 'marineindustryprojApp.teacher');
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/teacher-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        //this.loadAll(this.unchangedCriteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/teacher-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);
        });

        let expiredOptions = [{ id: 0, title: 'هیچکدام' }, { id: 1, title: 'به پایان رسیده' }, { id: 2, title: 'به پایان نرسیده' }];
        this.searchbarModel.push(new SearchPanelModel('teacher', 'name', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('teacher', 'family', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('teacher', 'phoneNumber', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('teacher', 'teachingSubject', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('teacher', 'expired', 'select', 'equals', expiredOptions));
        this.searchbarModel.push(
            new SearchPanelModel(
                'teacher',
                'teacherType',
                'selectWithStringId',
                'equals',
                this.commonSearchCheckerService.convertEnumToSearchArray(TeacherType, 'TeacherType')
            )
        );
        this.searchbarModel.push(
            new SearchPanelModel(
                'teacher',
                'grade',
                'selectWithStringId',
                'equals',
                this.commonSearchCheckerService.convertEnumToSearchArray(Grade, 'Grade')
            )
        );
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualificationMarineSuffix[]>) => {
                this.qualifications = res.body;
                this.searchbarModel.push(new SearchPanelModel('teacher', 'lastQualificationId', 'select', 'equals', this.qualifications));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.fieldOfStudyService.fieldOfStudies) {
            this.fieldofstudies = this.fieldOfStudyService.fieldOfStudies;
            this.searchbarModel.push(new SearchPanelModel('teacher', 'lastFieldOfStudyId', 'select', 'equals', this.fieldofstudies));
        } else {
            this.fieldOfStudyService.query().subscribe(
                (res: HttpResponse<IFieldOfStudyMarineSuffix[]>) => {
                    this.fieldofstudies = res.body;
                    this.searchbarModel.push(
                        new SearchPanelModel('teacher', 'lastFieldOfStudyId', 'select', 'equals', this.fieldofstudies)
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.serviceUnitService.query().subscribe(
            (res: HttpResponse<IServiceUnitMarineSuffix[]>) => {
                this.serviceunits = res.body;
                this.searchbarModel.push(new SearchPanelModel('teacher', 'serviceUnitId', 'select', 'equals', this.serviceunits));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.academicRankService.query().subscribe(
            (res: HttpResponse<IAcademicRankMarineSuffix[]>) => {
                this.academicranks = res.body;
                this.searchbarModel.push(new SearchPanelModel('teacher', 'academicRankId', 'select', 'equals', this.academicranks));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        /*if(!this.done)
        {
            this.loadAll();
        }*/
        //this.registerChangeInTeachers();
    }
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;
        if (account.authorities.find(a => a == 'ROLE_EDIT') !== undefined) this.isRoleEdit = true;
        if (account.authorities.find(a => a == 'ROLE_DELETE') !== undefined) this.isRoleDelete = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
        if (this.isModirAmozesh || this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isTopUsers = true;
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: ITeacherMarineSuffix) {
        return item.id;
    }

    registerChangeInTeachers() {
        this.eventSubscriber = this.eventManager.subscribe('teacherListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateTeachers(data: ITeacherMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;

        this.teachers = this.convertObjectDatesService.changeArrayDate(data);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
