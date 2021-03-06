import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { ResourceMarineSuffixService } from './resource-marine-suffix.service';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'mi-resource-marine-suffix',
    templateUrl: './resource-marine-suffix.component.html'
})
export class ResourceMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    resources: IResourceMarineSuffix[];
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
    done: boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;
    isRoleEdit: boolean = false;
    isRoleDelete: boolean = false;

    constructor(
        private resourceService: ResourceMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private jhiTranslate: TranslateService,
        private eventManager: JhiEventManager,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.done = true;
            this.criteria = criteria.content;
            this.loadAll(criteria.content);
        });
    }
    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.resources, 'resources', 'marineindustryprojApp.resource');
    }
    loadAll(criteria?) {
        this.resourceService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IResourceMarineSuffix[]>) => this.paginateResources(res.body, res.headers),
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
        /* this.router.navigate(['/resource-marine-suffix'], {
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
            '/resource-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.searchbarModel = new Array<SearchPanelModel>();
        this.searchbarModel.push(new SearchPanelModel('resource', 'title', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('resource', 'code', 'text', 'contains'));

        /*if(!this.done)
        {
            this.loadAll();
        }*/
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);
        });
        //this.registerChangeInResources();
    }

    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;
        if (account.authorities.find(a => a == 'ROLE_EDIT') !== undefined) this.isRoleEdit = true;
        if (account.authorities.find(a => a == 'ROLE_DELETE') !== undefined) this.isRoleDelete = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isRoleEdit) this.isSuperUsers = true;
        if (this.isModirAmozesh || this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isTopUsers = true;
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IResourceMarineSuffix) {
        return item.id;
    }

    registerChangeInResources() {
        this.eventSubscriber = this.eventManager.subscribe('resourceListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateResources(data: IResourceMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.resources = this.convertObjectDatesService.changeArrayDate(data);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
