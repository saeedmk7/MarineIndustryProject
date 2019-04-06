import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {IJobMarineSuffix} from 'app/shared/model/job-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {JobMarineSuffixService} from './job-marine-suffix.service';
import {PlatformLocation} from "@angular/common";
import {TranslateService} from '@ngx-translate/core';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";

@Component({
    selector: 'mi-job-marine-suffix',
    templateUrl: './job-marine-suffix.component.html'
})
export class JobMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    jobs: IJobMarineSuffix[];
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
    searchbarModel: SearchPanelModel[];
    done:boolean = false;
    criteria: any;
    constructor(
        private jobService: JobMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private jhiTranslate: TranslateService,
        private location: PlatformLocation,
        private convertObjectDatesService : ConvertObjectDatesService
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

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.jobs, 'jobs', 'marineindustryprojApp.job');
    }

    loadAll(criteria?) {

        this.jobService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IJobMarineSuffix[]>) => this.paginateJobs(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {

        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {

        /*this.router.navigate(['/job-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
        this.loadAll(this.criteria)
    }
    clear() {

        this.page = 0;
        this.router.navigate([
            '/job-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
    }

    ngOnInit() {
        this.searchbarModel = new Array<SearchPanelModel>();
        this.searchbarModel.push(new SearchPanelModel('job','title','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('job','jobKey','text','contains'));
        this.searchbarModel.push(new SearchPanelModel('job','jobCode','text','contains'));
        this.searchbarModel.push(new SearchPanelModel('job','first3JobCode','text','contains'));

        if(!this.done)
        {
            this.loadAll();
        }
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IJobMarineSuffix) {
        return item.id;
    }

    registerChangeInJobs(criteria?) {
        this.eventSubscriber = this.eventManager.subscribe('jobListModification', response => this.loadAll(criteria));
    }

    sort() {

        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateJobs(data: IJobMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.jobs = this.convertObjectDatesService.changeArrayDate(data);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
