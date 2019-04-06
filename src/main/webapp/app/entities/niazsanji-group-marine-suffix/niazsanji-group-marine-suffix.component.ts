import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { NiazsanjiGroupMarineSuffixService } from './niazsanji-group-marine-suffix.service';
import {TranslateService} from '@ngx-translate/core';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IReportMarineSuffix} from "app/shared/model/report-marine-suffix.model";

@Component({
    selector: 'mi-niazsanji-group-marine-suffix',
    templateUrl: './niazsanji-group-marine-suffix.component.html'
})
export class NiazsanjiGroupMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    niazsanjiGroups: INiazsanjiGroupMarineSuffix[];
    _report : IReportMarineSuffix;
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

    constructor(
        private niazsanjiGroupService: NiazsanjiGroupMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private jhiTranslate: TranslateService,
        private dataUtils: JhiDataUtils,
        private convertObjectDatesService : ConvertObjectDatesService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.niazsanjiGroupService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<INiazsanjiGroupMarineSuffix[]>) => this.paginateNiazsanjiGroups(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.niazsanjiGroups, 'niazsanjiGroups', 'marineindustryprojApp.niazsanjiGroup');
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    makeReport(i){

        let id = i;
        this.niazsanjiGroupService.report(id).subscribe((res: HttpResponse<IReportMarineSuffix>)=>{
            this._report = res.body;
        })
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }
    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    transition() {
        this.router.navigate(['/niazsanji-group-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/niazsanji-group-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });

        this._report = new class implements IReportMarineSuffix {
            fileDoc: any;
            fileDocContentType: string;
        };
        this.registerChangeInNiazsanjiGroups();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: INiazsanjiGroupMarineSuffix) {
        return item.id;
    }

    registerChangeInNiazsanjiGroups() {
        this.eventSubscriber = this.eventManager.subscribe('niazsanjiGroupListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateNiazsanjiGroups(data: INiazsanjiGroupMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.niazsanjiGroups = this.convertObjectDatesService.changeArrayDate(data);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
