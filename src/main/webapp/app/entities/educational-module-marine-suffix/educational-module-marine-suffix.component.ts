import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {IEducationalModuleMarineSuffix} from 'app/shared/model/educational-module-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {EducationalModuleMarineSuffixService} from './educational-module-marine-suffix.service';
import {PlatformLocation} from "@angular/common";
import {ExcelService} from "app/plugin/export-excel/excel-service";
import {TranslateService} from '@ngx-translate/core';
import {IScientificWorkGroupMarineSuffix} from "app/shared/model/scientific-work-group-marine-suffix.model";
import {ScientificWorkGroupMarineSuffixService} from "app/entities/scientific-work-group-marine-suffix";
import {ISkillableLevelOfSkillMarineSuffix} from "app/shared/model/skillable-level-of-skill-marine-suffix.model";
import {SkillableLevelOfSkillMarineSuffixService} from "app/entities/skillable-level-of-skill-marine-suffix";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {OrganizationMarineSuffixService} from "app/entities/organization-marine-suffix";
import {IOrganizationMarineSuffix} from "app/shared/model/organization-marine-suffix.model";
import {IRestrictionMarineSuffix} from "app/shared/model/restriction-marine-suffix.model";
import {RestrictionMarineSuffixService} from "app/entities/restriction-marine-suffix";

@Component({
    selector: 'mi-educational-module-marine-suffix',
    templateUrl: './educational-module-marine-suffix.component.html'
})
export class EducationalModuleMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    educationalModules: IEducationalModuleMarineSuffix[];
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
    searchbarModel: SearchPanelModel[] = [];
    done:boolean = false;
    criteria: any;

    searchScientificWorkGroupIds: number[];
    scientificWorkGroups: IScientificWorkGroupMarineSuffix[];
    searchSkillableLevelOfSkillIds: number[];
    skillableLevelOfSkills: ISkillableLevelOfSkillMarineSuffix[];
    organizations: IOrganizationMarineSuffix[];


    constructor(
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private scientificWorkGroupService: ScientificWorkGroupMarineSuffixService,
        private skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        private organizationService: OrganizationMarineSuffixService,
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
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);

        });
    }

    loadAll(criteria?) {

        this.educationalModuleService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => this.paginateEducationalModules(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.educationalModules, 'educationalModules', 'marineindustryprojApp.educationalModule');
    }

    transition() {
        /*this.router.navigate(['/educational-module-marine-suffix'], {
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
            '/educational-module-marine-suffix',
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
        this.searchbarModel.push(new SearchPanelModel('educationalModule','title','text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('educationalModule','code','number', 'equals'));
        this.prepareOrganization();
        this.prepareScientific();
        this.prepareSkillableLevelOfSkill();
        /*if(!this.done)
            this.loadAll();*/
    }
    prepareOrganization(){
        this.organizationService.query().subscribe((res) => {
            this.organizations = res.body;
            this.searchbarModel.push(new SearchPanelModel('educationalModule','organizationId','select','equals', this.organizations));
        }),
            (res: HttpErrorResponse) => this.onError(res.message);
    }
    prepareScientific()
    {
        this.scientificWorkGroupService.query().subscribe(
            (res: HttpResponse<IScientificWorkGroupMarineSuffix[]>) => {
                this.scientificWorkGroups = res.body;
                this.searchbarModel.push(new SearchPanelModel('educationalModule','scientificWorkGroupId','select','equals', this.scientificWorkGroups));
            }),
            (res: HttpErrorResponse) => this.onError(res.message);
    }
    prepareSkillableLevelOfSkill(){
        this.skillableLevelOfSkillService.query().subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {
                this.skillableLevelOfSkills = res.body;
                this.searchbarModel.push(new SearchPanelModel('educationalModule','skillableLevelOfSkillId','select','equals', this.skillableLevelOfSkills));
            }),
            (res: HttpErrorResponse) => this.onError(res.message);
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    registerChangeInEducationalModules() {
        this.eventSubscriber = this.eventManager.subscribe('educationalModuleListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateEducationalModules(data: IEducationalModuleMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.educationalModules = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
