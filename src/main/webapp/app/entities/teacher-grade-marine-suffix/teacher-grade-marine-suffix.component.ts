import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ITeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';
import { AccountService, Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { TeacherGradeMarineSuffixService } from './teacher-grade-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { Grade } from 'app/shared/model/enums/Grade';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { TeacherMarineSuffixService } from 'app/entities/teacher-marine-suffix';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { CommonSearchCheckerService } from 'app/plugin/utilities/common-search-checkers';
import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';
import { TeacherCriteriaGroupMarineSuffixService } from 'app/entities/teacher-criteria-group-marine-suffix';

@Component({
    selector: 'mi-teacher-grade-marine-suffix',
    templateUrl: './teacher-grade-marine-suffix.component.html',
    styleUrls: ['./teacher-grade-marine-suffix.scss']
})
export class TeacherGradeMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    teacherGrades: ITeacherGradeMarineSuffix[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    teachers: ITeacherMarineSuffix[];
    teacherCriteriaGroups: ITeacherCriteriaGroupMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = [];
    done: boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    grades: string[] = [];

    constructor(
        protected teacherGradeService: TeacherGradeMarineSuffixService,
        protected teacherCriteriaGroupService: TeacherCriteriaGroupMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: Principal,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected teacherService: TeacherMarineSuffixService,
        protected convertObjectDatesService: ConvertObjectDatesService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.done = true;
            this.criteria = criteria.content;
            this.loadAll(criteria.content);
        });
    }

    loadAll(criteria?) {
        if (!criteria) criteria = [];
        this.teacherGradeService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ITeacherGradeMarineSuffix[]>) => this.paginateTeacherGrades(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/teacher-grade-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll(this.criteria);*/
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/teacher-grade-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll(this.criteria);
    }

    ngOnInit() {
        //this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);

            this.grades = Object.keys(Grade);

            this.searchbarModel.push(
                new SearchPanelModel(
                    'teacherGrade',
                    'grade',
                    'selectWithStringId',
                    'equals',
                    this.commonSearchCheckerService.convertEnumToSearchArray(Grade, 'Grade')
                )
            );
            /*this.searchbarModel.push(new SearchPanelModel('mediaAwarenessReport','publishDate','text', 'contains'));*/
            this.teacherService.query().subscribe(
                (res: HttpResponse<ITeacherMarineSuffix[]>) => {
                    this.teachers = res.body;
                    this.searchbarModel.push(
                        new SearchPanelModel('teacherGrade', 'teacherId', 'select', 'equals', this.teachers, 'fullName')
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.teacherCriteriaGroupService.query().subscribe(
                (res: HttpResponse<ITeacherCriteriaGroupMarineSuffix[]>) => {
                    this.teacherCriteriaGroups = res.body;
                    this.searchbarModel.push(
                        new SearchPanelModel('teacherGrade', 'teacherCriteriaGroupId', 'select', 'equals', this.teacherCriteriaGroups)
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.prepareSearchDate();
            this.prepareSearchMonth();
        });
        //this.registerChangeInTeacherGrades();
    }

    prepareSearchDate() {
        const dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('teacherGrade', 'year', 'select', 'equals', dates, 'title'));
    }
    prepareSearchMonth() {
        this.searchbarModel.push(new SearchPanelModel('teacherGrade', 'month', 'select', 'equals', MONTHS, 'persianMonth'));
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: ITeacherGradeMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInTeacherGrades() {
        this.eventSubscriber = this.eventManager.subscribe('teacherGradeListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateTeacherGrades(data: ITeacherGradeMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.teacherGrades = data;
        this.teacherGrades.forEach(w => (w.monthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(w.month)));
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
