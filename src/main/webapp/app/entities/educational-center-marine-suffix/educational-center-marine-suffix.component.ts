import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {IEducationalCenterMarineSuffix} from 'app/shared/model/educational-center-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {EducationalCenterMarineSuffixService} from './educational-center-marine-suffix.service';
import {PlatformLocation} from "@angular/common";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {ActivityAreaMarineSuffixService} from "app/entities/activity-area-marine-suffix";
import {IEmploymentTypeMarineSuffix} from "app/shared/model/employment-type-marine-suffix.model";
import {IActivityAreaMarineSuffix} from "app/shared/model/activity-area-marine-suffix.model";
import {Grade} from "app/shared/model/enums/Grade";
import {CommonSearchCheckerService} from "app/plugin/utilities/common-search-checkers";

@Component({
    selector: 'mi-educational-center-marine-suffix',
    templateUrl: './educational-center-marine-suffix.component.html'
})
export class EducationalCenterMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    educationalCenters: IEducationalCenterMarineSuffix[];
    activityAreas: IEducationalCenterMarineSuffix[];
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

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[];
    done:boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;

    constructor(
        private educationalCenterService: EducationalCenterMarineSuffixService,
        private activityAreasService: ActivityAreaMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private location: PlatformLocation,
        private jhiTranslate: TranslateService,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            //this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);

        });
    }

    loadAll(criteria?) {
        this.educationalCenterService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IEducationalCenterMarineSuffix[]>) => this.paginateEducationalCenters(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.educationalCenters, 'educationalCenters', 'marineindustryprojApp.educationalCenter');
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/educational-center-marine-suffix'], {
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
        this.router.navigate([
            '/educational-center-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.searchbarModel = new Array<SearchPanelModel>();
        this.searchbarModel.push(new SearchPanelModel('educationalCenter','name','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('educationalCenter','connectionPerson','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('educationalCenter','telephone','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('educationalCenter','grade','selectWithStringId','equals', this.commonSearchCheckerService.convertEnumToSearchArray(Grade, 'Grade')));
        this.activityAreasService.query().subscribe(
            (res: HttpResponse<IActivityAreaMarineSuffix[]>) => {

                this.activityAreas = res.body;
                this.searchbarModel.push(new SearchPanelModel('educationalCenter','activityAreaId','select', 'equals', res.body));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        /*if(!this.done){
            this.loadAll();
        }*/
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);
        });
        //this.registerChangeInEducationalCenters();
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
        if(this.isModirAmozesh || this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isTopUsers = true;
    }
    ngOnDestroy() {
        /*this.eventManager.destroy(this.eventSubscriber);*/
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IEducationalCenterMarineSuffix) {
        return item.id;
    }

    registerChangeInEducationalCenters() {
        this.eventSubscriber = this.eventManager.subscribe('educationalCenterListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateEducationalCenters(data: IEducationalCenterMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.educationalCenters = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
