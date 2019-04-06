import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IEducationalModuleJobMarineSuffix } from 'app/shared/model/educational-module-job-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { EducationalModuleJobMarineSuffixService } from './educational-module-job-marine-suffix.service';
import {DataResult, GroupDescriptor, process} from "@progress/kendo-data-query";
import {PlatformLocation} from "@angular/common";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IActivityAreaMarineSuffix} from "app/shared/model/activity-area-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {JobMarineSuffixService} from "app/entities/job-marine-suffix";
import {IJobMarineSuffix} from "app/shared/model/job-marine-suffix.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";

@Component({
    selector: 'mi-educational-module-job-marine-suffix',
    templateUrl: './educational-module-job-marine-suffix.component.html'
})
export class EducationalModuleJobMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    educationalModuleJobs: IEducationalModuleJobMarineSuffix[];
    educationalModuleJobsTemp: IEducationalModuleJobMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
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

    dirty: boolean;
    first3JobCode: string;

    public groups: GroupDescriptor[] = [{ field: 'first3JobCode' }];

    public gridView: DataResult;

    searchbarModel: SearchPanelModel[] = new Array<SearchPanelModel>();
    done:boolean = false;
    criteria: any;

    selectedJob: number;
    selectedEducationalModule: number;
    counter: number = 0;
    allRows: number = 0;
    constructor(
        private educationalModuleJobService: EducationalModuleJobMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private jobService: JobMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private location: PlatformLocation,
        private eventManager: JhiEventManager
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
    /*search(criteria?) {
        
        this.educationalModuleJobs = this.educationalModuleJobsTemp;
        if(this.searchFirst3JobCode)
           this.educationalModuleJobs = this.educationalModuleJobs.filter(a => a.first3JobCode == this.searchFirst3JobCode);
        if(this.searchEducationalModuleTitle)
            this.educationalModuleJobs =  this.educationalModuleJobs.filter(a => a.educationalModuleTitle.includes(this.searchEducationalModuleTitle));
        if(this.searchJobTitle)
            this.educationalModuleJobs = this.educationalModuleJobs.filter(a => a.jobTitle.includes(this.searchJobTitle));

        this.loadEducationalModuleJobs();
    }*/
    loadAll(criteria?) {
        this.educationalModuleJobService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IEducationalModuleJobMarineSuffix[]>) => this.paginateEducationalModuleJobs(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.prepareSearchEducationalModule();
        this.prepareSearchJob();
        /*if(!this.done){
            this.loadAll();
        }*/
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }
    prepareSearchJob(){
        this.jobService.query().subscribe((resp: HttpResponse<IJobMarineSuffix[]>) =>{
                this.jobs = resp.body;
                this.searchbarModel.push(new SearchPanelModel('educationalModuleJob','jobId','select', 'equals', this.jobs, 'fullTitle'));
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }
    prepareSearchEducationalModule(){
        if(this.educationalModuleService.educationalModules){
            this.educationalModules = this.educationalModuleService.educationalModules
            this.searchbarModel.push(new SearchPanelModel('educationalModuleJob', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle'));
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    this.searchbarModel.push(new SearchPanelModel('educationalModuleJob', 'educationalModuleId', 'select', 'equals', res.body, 'fullTitle'));

                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
    }
    delete(){

        let confirmQuestion: string = "";
        if(this.selectedJob && this.selectedEducationalModule)
            confirmQuestion = "آیا از حذف اطلاعات مطمئنید؟";
        else if (this.selectedJob)
            confirmQuestion = "آیا از حذف تمامی پودمان های مربوط به این شغل مطمئنید؟";
        else if (this.selectedEducationalModule)
            confirmQuestion = "آیا از حذف تمامی مشاغل مربوط به این پودمان مطمئنید؟";
        else if (this.first3JobCode)
            confirmQuestion = "آیا از حذف تمامی مواردی که مشاغلشان این 3 رقم اول را دارند، مطمئنید؟";
        else
            return;
        if(confirm(confirmQuestion)){

            let criteria: any[] = [];
            if(this.selectedJob && this.selectedEducationalModule)
            {
                criteria.push({
                    key: "jobId.equals",value: this.selectedJob
                });
                criteria.push({
                    key: "educationalModuleId.equals",value: this.selectedEducationalModule
                });
            }
            else if (this.selectedJob) {
                criteria.push({
                    key: "jobId.equals",value: this.selectedJob
                });
            }
            else if (this.selectedEducationalModule) {
                criteria.push({
                    key: "educationalModuleId.equals",value: this.selectedEducationalModule
                });
            }
            else if (this.first3JobCode){
                let reomvedJobsIds = this.jobs.filter(a => a.jobCode.substring(0,3) == this.first3JobCode).map(a => a.id);
                criteria.push({
                    key: "jobId.in",value: reomvedJobsIds
                });
            }
            this.educationalModuleJobService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: this.sort()
            })
                .subscribe(
                    (res: HttpResponse<IEducationalModuleJobMarineSuffix[]>) => this.deleteAll(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    deleteAll(resp: IEducationalModuleJobMarineSuffix[]){
        this.allRows = resp.length;
        this.counter = 0;

        resp.forEach(a => {

            this.educationalModuleJobService.delete(a.id)
                .subscribe(
                    (res: HttpResponse<IEducationalModuleJobMarineSuffix>) => this.loadAfter(),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        });
    }
    loadAfter(){
        this.counter++;
        if(this.counter == this.allRows){
            this.loadAll(this.criteria);
        }
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IEducationalModuleJobMarineSuffix) {
        return item.id;
    }

    registerChangeInEducationalModuleJobs() {
        this.eventSubscriber = this.eventManager.subscribe('educationalModuleJobListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateEducationalModuleJobs(data: IEducationalModuleJobMarineSuffix[], headers: HttpHeaders) {
        /*this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;*/
        
        this.educationalModuleJobs = data;
        this.educationalModuleJobsTemp = data;
        this.loadEducationalModuleJobs();
        //this.jobIds = this.educationalModuleJobs.map(a => a.jobId);
    }
    public groupChange(groups: GroupDescriptor[]): void {
        this.groups = groups;
        this.loadEducationalModuleJobs();
    }
    private loadEducationalModuleJobs(): void {

        this.gridView = process(this.educationalModuleJobs, { group: this.groups });
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
