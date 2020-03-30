import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IInstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';
import { Principal} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { InstructionMarineSuffixService } from './instruction-marine-suffix.service';
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';

@Component({
    selector: 'mi-instruction-marine-suffix',
    templateUrl: './instruction-marine-suffix.component.html'
})
export class InstructionMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    instructions: IInstructionMarineSuffix[];
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

    constructor(
        protected instructionService: InstructionMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected principal: Principal,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService : ConvertObjectDatesService
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
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
        a.exportAsExcelFile(this.instructions, 'instructions', 'marineindustryprojApp.instruction');
    }
    loadAll(criteria?) {
        this.instructionService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IInstructionMarineSuffix[]>) => this.paginateInstructions(res.body, res.headers),
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
       /* this.router.navigate(['/instruction-marine-suffix'], {
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
            '/instruction-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.searchbarModel = new Array<SearchPanelModel>();
        this.searchbarModel.push(new SearchPanelModel('instruction','title','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('instruction','code','text','contains'));
        /*if(!this.done)
        {
            this.loadAll();
        }*/
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        //this.registerChangeInInstructions();
    }

    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IInstructionMarineSuffix) {
        return item.id;
    }

    registerChangeInInstructions() {
        this.eventSubscriber = this.eventManager.subscribe('instructionListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateInstructions(data: IInstructionMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.instructions = this.convertObjectDatesService.changeArrayDate(data);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
