import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';
import {AccountService, Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { NiazsanjiPersonGradeMarineSuffixService } from './niazsanji-person-grade-marine-suffix.service';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {Grade} from "app/shared/model/enums/Grade";
import {MONTHS} from "app/shared/constants/months.constants";
import {CommonSearchCheckerService} from "app/plugin/utilities/common-search-checkers";

@Component({
    selector: 'mi-niazsanji-person-grade-marine-suffix',
    templateUrl: './niazsanji-person-grade-marine-suffix.component.html',
    styleUrls: ['./niazsanji-person-grade-marine-suffix.scss']
})
export class NiazsanjiPersonGradeMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    niazsanjiPersonGrades: INiazsanjiPersonGradeMarineSuffix[];
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

    finalNiazsanjiReportPersons: IFinalNiazsanjiReportPersonMarineSuffix[];
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
        protected niazsanjiPersonGradeService: NiazsanjiPersonGradeMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: Principal,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        protected convertObjectDatesService: ConvertObjectDatesService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
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
        this.niazsanjiPersonGradeService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<INiazsanjiPersonGradeMarineSuffix[]>) => this.paginateNiazsanjiPersonGrades(res.body, res.headers),
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
        this.router.navigate(['/niazsanji-person-grade-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/niazsanji-person-grade-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll(this.criteria);
    }

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);
            this.grades = Object.keys(Grade);

            this.searchbarModel.push(new SearchPanelModel('niazsanjiPersonGrade','grade','selectWithStringId', 'equals', this.commonSearchCheckerService.convertEnumToSearchArray(Grade, 'Grade')));
            this.prepareSearchDate();
            this.prepareSearchMonth();
        });

        //this.registerChangeInNiazsanjiPersonGrades();
    }

    prepareSearchDate(){
        const dates = this.convertObjectDatesService.getYearsArray();
        this.searchbarModel.push(new SearchPanelModel('niazsanjiPersonGrade', 'year', 'select', 'equals', dates, 'title'));
    }
    prepareSearchMonth(){
        this.searchbarModel.push(new SearchPanelModel('niazsanjiPersonGrade', 'month', 'select', 'equals', MONTHS, 'persianMonth'));
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: INiazsanjiPersonGradeMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInNiazsanjiPersonGrades() {
        this.eventSubscriber = this.eventManager.subscribe('niazsanjiPersonGradeListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateNiazsanjiPersonGrades(data: INiazsanjiPersonGradeMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.niazsanjiPersonGrades = data;
        this.niazsanjiPersonGrades.forEach(w => w.monthPersian = this.convertObjectDatesService.convertMonthsNumber2MonthName(w.month));
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
