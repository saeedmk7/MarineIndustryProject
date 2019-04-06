import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import {
    IRequestEducationalModuleMarineSuffix
} from 'app/shared/model/request-educational-module-marine-suffix.model';
import { Principal } from 'app/core';

import {DATE_TIME_FORMAT, ITEMS_PER_PAGE} from 'app/shared';
import { RequestEducationalModuleMarineSuffixService } from './request-educational-module-marine-suffix.service';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {
    EducationalModuleMarineSuffix,
    IEducationalModuleMarineSuffix
} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import * as moment from "moment";

@Component({
    selector: 'mi-request-educational-module-marine-suffix',
    templateUrl: './request-educational-module-marine-suffix.component.html',
    styleUrls: ['./request-educational-module-marine-suffix.scss']
})
export class RequestEducationalModuleMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    requestEducationalModules: IRequestEducationalModuleMarineSuffix[];
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
    isAdmin: boolean;

    constructor(
        private requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private convertObjectDatesService : ConvertObjectDatesService,
        private educationalModuleMarineSuffixService: EducationalModuleMarineSuffixService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.requestEducationalModuleService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IRequestEducationalModuleMarineSuffix[]>) =>
                    this.paginateRequestEducationalModules(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    reject(mymodel: IRequestEducationalModuleMarineSuffix)
    {
        if(confirm("آیا برای رد کردن کامل درخواست موافقید؟")) {

            this.requestEducationalModuleService.find(mymodel.id).subscribe((resp) => {
                let model = resp.body;
                model.requestStatus = RequestStatus.IGNORE;
                model.changeStatusUserLogin = this.currentAccount.login;
                this.requestEducationalModuleService.update(model).subscribe(
                    (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccessIgnore(),
                    (res: HttpErrorResponse) => this.onSaveError()
                );
            });
        }
    }
    complete(mymodel: IRequestEducationalModuleMarineSuffix){
        if(confirm("آیا از تایید و نهایی کردن این درخواست مطمئنید.")){

            this.requestEducationalModuleService.find(mymodel.id).subscribe((resp) => {
                let model = resp.body;
                let educationalModule: IEducationalModuleMarineSuffix = new EducationalModuleMarineSuffix();
                educationalModule.teachers = model.teachers;
                educationalModule.code = model.code.toString();
                educationalModule.archived = false;
                educationalModule.documents = model.documents;
                educationalModule.status = 0;
                educationalModule.educationalModuleLevel = model.educationalModuleLevel;
                educationalModule.learningTimePractical = model.learningTimePractical;
                educationalModule.learningTimeTheorical = model.learningTimeTheorical;
                educationalModule.title = model.title;
                /*educationalModule.credit = model.credit;
                educationalModule.timePassed = model.timePassed;*/
                educationalModule.centralizedCode = model.centralizedCode;
                educationalModule.drafters = model.drafters;
                educationalModule.educationalCenters = model.educationalCenters;
                educationalModule.educationalModuleHeadlines = model.educationalModuleHeadlines;
                educationalModule.educationalModuleGroup = model.educationalModuleGroup;
                educationalModule.educationalModuleHour = model.educationalModuleHour;
                educationalModule.evaluationMethodId = model.evaluationMethodId;
                educationalModule.goals = model.goals;
                educationalModule.innerCode = model.innerCode;
                educationalModule.moreDescription = model.moreDescription;
                educationalModule.organizationId = model.organizationId;
                educationalModule.prerequisite = model.prerequisite;
                educationalModule.recommendedBy = model.recommendedBy;
                educationalModule.requestEducationalModuleId = model.id;
                educationalModule.resources = model.resources;
                educationalModule.scientificWorkGroups = model.scientificWorkGroups;
                educationalModule.securityLevelId = model.securityLevelId;
                educationalModule.skillableLevelOfSkillId = model.skillableLevelOfSkillId;
                educationalModule.version = model.version;

                this.educationalModuleMarineSuffixService.create(educationalModule).subscribe(
                    (res: HttpResponse<IEducationalModuleMarineSuffix>) => this.updateRequest(model),
                    (res: HttpErrorResponse) => this.onSaveError()
                );
            });
        }
    }
    private updateRequest(model: IRequestEducationalModuleMarineSuffix){

        //let ss: RequestStatus = RequestStatus.ACCEPT;
        model.requestStatus = RequestStatus.ACCEPT;
        model.changeStatusUserLogin = this.currentAccount.login;
        this.requestEducationalModuleService.update(model).subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    private onSaveSuccess() {

        this.loadAll();
        this.jhiAlertService.success("marineindustryprojApp.requestEducationalModule.completed");
    }
    private onSaveSuccessIgnore() {

        this.loadAll();
        this.jhiAlertService.success("marineindustryprojApp.requestEducationalModule.rejected");
    }
    private onSaveError() {

    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/request-educational-module-marine-suffix'], {
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
            '/request-educational-module-marine-suffix',
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
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
        });
        this.registerChangeInRequestEducationalModules();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IRequestEducationalModuleMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInRequestEducationalModules() {
        this.eventSubscriber = this.eventManager.subscribe('requestEducationalModuleListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateRequestEducationalModules(data: IRequestEducationalModuleMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.requestEducationalModules = this.convertObjectDatesService.changeArrayDate(data);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
