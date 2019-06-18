import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IDesignAndPlanningMarineSuffix } from 'app/shared/model/design-and-planning-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { DesignAndPlanningMarineSuffixService } from './design-and-planning-marine-suffix.service';
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {MONTHS} from "app/shared/constants/months.constants";
import {CourseTypeMarineSuffixService} from "app/entities/course-type-marine-suffix";
import {EffectivenessLevelMarineSuffixService} from "app/entities/effectiveness-level-marine-suffix";
import {ICourseTypeMarineSuffix} from "app/shared/model/course-type-marine-suffix.model";
import {IEffectivenessLevelMarineSuffix} from "app/shared/model/effectiveness-level-marine-suffix.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {EducationalModuleMarineSuffixService} from "app/entities/educational-module-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";

@Component({
    selector: 'mi-design-and-planning-marine-suffix',
    templateUrl: './design-and-planning-marine-suffix.component.html'
})
export class DesignAndPlanningMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    designAndPlannings: IDesignAndPlanningMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    people: IPersonMarineSuffix[];
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

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    constructor(
        private designAndPlanningService: DesignAndPlanningMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private courseTypeService: CourseTypeMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private effectivenessLevelService: EffectivenessLevelMarineSuffixService,
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
            this.done = true;
            this.criteria = criteria.content;
            this.loadAll(criteria.content);
        });
    }

    loadAll(criteria?) {

        if(!criteria){
            criteria = [];
        }
        if(!this.isSuperUsers) {
            criteria.push({
                key: 'personId.equals',
                value: this.currentAccount.personId
            });
        }
        this.designAndPlanningService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IDesignAndPlanningMarineSuffix[]>) => this.paginateDesignAndPlannings(res.body, res.headers),
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
        this.router.navigate(['/design-and-planning-marine-suffix'], {
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
            '/design-and-planning-marine-suffix',
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
        this.setRoles(account);

        this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'runMonth', 'select', 'equals', MONTHS, 'persianMonth'));
        this.prepareSearchEducationalModule();
        if(this.isSuperUsers)
            this.prepareSearchPerson();
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'courseTypeId', 'select', 'equals', res.body));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.effectivenessLevelService.query().subscribe(
            (res: HttpResponse<IEffectivenessLevelMarineSuffix[]>) => {
                this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'effectivenessLevelId', 'select', 'equals', res.body));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        /*this.principal.identity().then(account => {
            this.currentAccount = account;
        });*/

    });
        //this.registerChangeInDesignAndPlannings();
    }
    prepareSearchPerson() {
        if (this.personService.people) {
            this.people = this.personService.people;
            this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'personId', 'select', 'equals', this.people, "fullName"));
        }
        else {
            this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                    this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'personId', 'select', 'equals', this.people, "fullName"));
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    prepareSearchEducationalModule() {
        this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'educationalModuleTitle', 'text', 'contains'));
        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules;
            //this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle'));
            /*if (!this.done) {
                this.loadAll();
            }*/
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    //this.searchbarModel.push(new SearchPanelModel('designAndPlanning', 'educationalModuleId', 'select', 'equals', this.educationalModules, 'fullTitle'));
                    /*if (!this.done) {
                        this.loadAll();
                    }*/
                },
                (res: HttpErrorResponse) => this.onError(res.message))
        }
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
    }
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
        this.eventManager.destroy(this.criteriaSubscriber);
    }

    trackId(index: number, item: IDesignAndPlanningMarineSuffix) {
        return item.id;
    }

    registerChangeInDesignAndPlannings() {
        this.eventSubscriber = this.eventManager.subscribe('designAndPlanningListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateDesignAndPlannings(data: IDesignAndPlanningMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        data.forEach(a => {
            a.runMonthName = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.runMonth);
            let education: IEducationalModuleMarineSuffix = this.educationalModules.find(w => w.id == a.educationalModuleId);
            if(education){
                a.skillLevelOfSkillTitle = education.skillableLevelOfSkillTitle;
                a.totalLearningTime = (education.learningTimePractical ? education.learningTimePractical : 0) + (education.learningTimeTheorical ? education.learningTimeTheorical : 0)
            }
        })
        this.designAndPlannings = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
