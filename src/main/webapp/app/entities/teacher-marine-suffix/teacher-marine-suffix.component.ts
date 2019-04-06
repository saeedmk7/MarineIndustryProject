import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {ITeacherMarineSuffix} from 'app/shared/model/teacher-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {TeacherMarineSuffixService} from './teacher-marine-suffix.service';
import {PlatformLocation} from "@angular/common";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";

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

    searchbarModel: SearchPanelModel[];
    done:boolean = false;
    criteria: any;



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
        private convertObjectDatesService : ConvertObjectDatesService
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

    loadAll(criteria?) {

        if(criteria)
        {
            let val = +criteria.find(a => a.key == 'expired.equals').value;
            criteria.pop('expired');

            if(val){
                let date = new Date().toISOString()
                if(val == 1){
                    criteria.push({
                        key: 'expirationDate.lessOrEqualThan',
                        value: date
                    });
                }
                if(val == 2){
                    criteria.push({
                        key: 'expirationDate.greaterOrEqualThan',
                        value: date
                    });
                }
            }


        }
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

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.teachers, 'teachers', 'marineindustryprojApp.teacher');
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
        this.loadAll(this.criteria);
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
        });

        let expiredOptions = [{id:0,title:'هیچکدام'},{id:1,title:'به پایان رسیده'},{id:2,title:'به پایان نرسیده'}];

        this.searchbarModel = new Array<SearchPanelModel>();
        this.searchbarModel.push(new SearchPanelModel('teacher','name','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('teacher','family','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('teacher','teachingSubject','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('teacher','expired','select','equals', expiredOptions))

        if(!this.done)
        {
            this.loadAll();
        }
        //this.registerChangeInTeachers();
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
