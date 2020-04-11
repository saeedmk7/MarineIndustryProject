import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { EducationalCenterGradeMarineSuffixService } from './educational-center-grade-marine-suffix.service';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IEvaluatorOpinionMarineSuffix} from "app/shared/model/evaluator-opinion-marine-suffix.model";
import {IEducationalCenterServiceMarineSuffix} from "app/shared/model/educational-center-service-marine-suffix.model";
import {IEducationalCenterMarineSuffix} from "app/shared/model/educational-center-marine-suffix.model";
import {EvaluatorOpinionMarineSuffixService} from "app/entities/evaluator-opinion-marine-suffix";
import {DocumentMarineSuffixService} from "app/entities/document-marine-suffix";
import {EducationalCenterServiceMarineSuffixService} from "app/entities/educational-center-service-marine-suffix";
import {EducationalCenterMarineSuffixService} from "app/entities/educational-center-marine-suffix";
import {IDocumentMarineSuffix} from "app/shared/model/document-marine-suffix.model";
import {MONTHS} from "app/shared/constants/months.constants";
import {GradientStop} from "@progress/kendo-drawing";
import {HeadlineLevel} from "app/shared/model/enums/HeadlineLevel";
import {Grade} from "app/shared/model/enums/Grade";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {CommonSearchCheckerService} from "app/plugin/utilities/common-search-checkers";

@Component({
    selector: 'mi-educational-center-grade-marine-suffix',
    templateUrl: './educational-center-grade-marine-suffix.component.html',
    styleUrls: ['./educational-center-grade-marine-suffix.scss']
})
export class EducationalCenterGradeMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    educationalCenterGrades: IEducationalCenterGradeMarineSuffix[];
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

    evaluatoropinions: IEvaluatorOpinionMarineSuffix[];

    educationalcenterservices: IEducationalCenterServiceMarineSuffix[];

    educationalcenters: IEducationalCenterMarineSuffix[];

    currentPerson: IPersonMarineSuffix;

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = [];
    done:boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    grades: string[] = [];

    constructor(
        protected educationalCenterGradeService: EducationalCenterGradeMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: Principal,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected evaluatorOpinionService: EvaluatorOpinionMarineSuffixService,
        protected educationalCenterServiceService: EducationalCenterServiceMarineSuffixService,
        protected educationalCenterService: EducationalCenterMarineSuffixService,
        protected convertObjectDatesService: ConvertObjectDatesService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.done = true;
            this.criteria = criteria.content;
            this.loadAll(criteria.content);
        });
    }

    loadAll(criteria?) {
        if(!criteria)
            criteria = [];
        this.educationalCenterGradeService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IEducationalCenterGradeMarineSuffix[]>) => this.paginateEducationalCenterGrades(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
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
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/educational-center-grade-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        //this.loadAll(this.criteria);
    }

    clear() {
        this.page = 0;
        /*this.router.navigate([
            '/educational-center-grade-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);*/
        this.loadAll(this.criteria);
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;

            this.setRoles(account);

            this.grades = Object.keys(Grade);

            this.searchbarModel.push(new SearchPanelModel('educationalCenterGrade','grade','selectWithStringId', 'equals', this.commonSearchCheckerService.convertEnumToSearchArray(Grade, 'Grade')));
            /*this.searchbarModel.push(new SearchPanelModel('mediaAwarenessReport','publishDate','text', 'contains'));*/
            this.prepareSearchDate();
            this.prepareSearchMonth();
            /*this.evaluatorOpinionService.query().subscribe(
                (res: HttpResponse<IEvaluatorOpinionMarineSuffix[]>) => {
                    this.evaluatoropinions = res.body;
                    this.searchbarModel.push(new SearchPanelModel('educationalCenterGrade',
                        'subject','text', 'contains'));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );*/
            this.educationalCenterServiceService.query().subscribe(
                (res: HttpResponse<IEducationalCenterServiceMarineSuffix[]>) => {
                    this.educationalcenterservices = res.body;
                    this.searchbarModel.push(new SearchPanelModel('educationalCenterGrade',
                        'educationalCenterServiceId','select', 'equals', this.educationalcenterservices));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.educationalCenterService.query().subscribe(
                (res: HttpResponse<IEducationalCenterMarineSuffix[]>) => {
                    this.educationalcenters = res.body;
                    this.searchbarModel.push(new SearchPanelModel('educationalCenterGrade',
                        'educationalCenterId','select', 'equals', this.educationalcenters, 'name'));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        //this.registerChangeInEducationalCenterGrades();
    }

    prepareSearchDate(){
        const dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('educationalCenterGrade', 'year', 'select', 'equals', dates, 'title'));
    }
    prepareSearchMonth(){
        this.searchbarModel.push(new SearchPanelModel('educationalCenterGrade', 'month', 'select', 'equals', MONTHS, 'persianMonth'));
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IEducationalCenterGradeMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInEducationalCenterGrades() {
        this.eventSubscriber = this.eventManager.subscribe('educationalCenterGradeListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateEducationalCenterGrades(data: IEducationalCenterGradeMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.educationalCenterGrades = data;
        this.educationalCenterGrades.forEach(w => w.monthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(w.month));
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
