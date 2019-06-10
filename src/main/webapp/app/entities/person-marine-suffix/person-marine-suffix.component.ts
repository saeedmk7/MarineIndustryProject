import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {IPersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {PersonMarineSuffixService} from './person-marine-suffix.service';
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {PlatformLocation} from "@angular/common";
import {TranslateService} from '@ngx-translate/core';
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IEmploymentTypeMarineSuffix} from "app/shared/model/employment-type-marine-suffix.model";
import {EmploymentTypeMarineSuffixService} from "app/entities/employment-type-marine-suffix";

@Component({
    selector: 'mi-person-marine-suffix',
    templateUrl: './person-marine-suffix.component.html'
})
export class PersonMarineSuffixComponent implements OnInit, OnDestroy {
    people: IPersonMarineSuffix[];
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
    employmenttypes: IEmploymentTypeMarineSuffix[];

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[];
    done:boolean = false;
    criteria: any;
    constructor(
        private personService: PersonMarineSuffixService,
        private employmentTypeService: EmploymentTypeMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private location: PlatformLocation,
        private jhiTranslate: TranslateService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
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

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.people, 'people', 'marineindustryprojApp.person');
    }

    loadAll(criteria?) {

        this.personService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => this.paginatePeople(res.body, res.headers),
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
       /* this.router.navigate(['/person-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });*/
       this.loadAll(this.criteria);
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/person-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.employmentTypeService.query().subscribe(
            (res: HttpResponse<IEmploymentTypeMarineSuffix[]>) => {

                this.employmenttypes = res.body;
                this.searchbarModel = new Array<SearchPanelModel>();
                this.searchbarModel.push(new SearchPanelModel('person','name','text', 'contains'));
                this.searchbarModel.push(new SearchPanelModel('person','family','text','contains'));
                this.searchbarModel.push(new SearchPanelModel('person','nationalId','text','contains'));
                this.searchbarModel.push(new SearchPanelModel('person','personelCode','text','contains'));
                this.searchbarModel.push(new SearchPanelModel('person','employmentTypeId','select','equals', res.body));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        /*if(!this.done){
            this.loadAll();
        }*/
        //this.registerChangeInPeople();
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    registerChangeInPeople(criteria?) {
        this.eventSubscriber = this.eventManager.subscribe('personListModification', response => this.loadAll(criteria));
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginatePeople(data: IPersonMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.people = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
