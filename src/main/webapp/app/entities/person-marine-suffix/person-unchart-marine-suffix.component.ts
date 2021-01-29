import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { PersonMarineSuffixService } from './person-marine-suffix.service';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { PlatformLocation } from '@angular/common';
import { TranslateService } from '@ngx-translate/core';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { EmploymentTypeMarineSuffixService } from 'app/entities/employment-type-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { IQualificationMarineSuffix, QualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from 'app/entities/qualification-marine-suffix';

@Component({
    selector: 'mi-person-unchart-marine-suffix',
    templateUrl: './person-unchart-marine-suffix.component.html'
})
export class PersonUnchartMarineSuffixComponent implements OnInit, OnDestroy {
    people: IPersonMarineSuffix[];
    allPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    selectedPeople: IPersonMarineSuffix[] = [];
    orgIds: number[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    organizationcharts: IOrganizationChartMarineSuffix[];
    selectedOrganizationChartId: number;
    allOrganizationcharts: IOrganizationChartMarineSuffix[];

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
    qualifications: IQualificationMarineSuffix[];

    criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[] = [];
    done: boolean = false;
    criteria: any;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isModirAmozesh2: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;
    constructor(
        private personService: PersonMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private employmentTypeService: EmploymentTypeMarineSuffixService,
        private qualificationService: QualificationMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private location: PlatformLocation,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities
    ) {
        //this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', criteria => {
            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);
        });
    }
    change(val, person: IPersonMarineSuffix) {
        if (val.target.checked) {
            this.selectedPeople.push(person);
        } else {
            this.selectedPeople = this.selectedPeople.filter(w => w.id != person.id);
        }
    }
    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(this.people, 'people', 'marineindustryprojApp.person');
    }

    save() {
        if (this.selectedPeople.length > 0 && this.selectedOrganizationChartId) {
            this.isSaving = true;
            this.selectedPeople.forEach(person => {
                person.organizationChartId = this.selectedOrganizationChartId;

                this.personService.find(person.id).subscribe((person: HttpResponse<IPersonMarineSuffix>) => {
                    let newPerson = person.body;
                    newPerson.organizationChartId = this.selectedOrganizationChartId;
                    this.subscribeToSaveResponse(this.personService.update(newPerson));
                });
            });
        }
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IPersonMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPersonMarineSuffix>) => this.onSaveSuccess(res.body),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    isSaving: boolean = false;
    counter: number = 0;
    private onSaveSuccess(res: IPersonMarineSuffix) {
        this.counter++;
        if (this.counter == this.selectedPeople.length) {
            this.counter = 0;
            this.selectedPeople = [];
            this.loadUnChartPeople();
        }
        //this.error = null;
        //this.success += res.name + " " + res.family + " با موفقیت ثبت شد.";
    }

    private onSaveError() {
        this.isSaving = false;
        //this.success = null;
        //this.error = "در هنگام ثبت اطلاعات مشکلی پیشامد کرده لطفا بعدا دوباره امتحان کنید.";
    }
    loadAll(criteria?) {
        if (this.allPeople) {
            this.people = this.allPeople;
            const name = criteria.find(a => a.key == 'name.contains');
            if (name) {
                const nameVal = name.value;
                if (nameVal) this.people = this.people.filter(w => w.name.includes(nameVal));
            }
            const family = criteria.find(a => a.key == 'family.contains');
            if (family) {
                const familyVal = family.value;
                if (familyVal) this.people = this.people.filter(w => w.family.includes(familyVal));
            }
            const nationalId = criteria.find(a => a.key == 'nationalId.contains');
            if (nationalId) {
                const nationalIdVal = nationalId.value;
                if (nationalIdVal) this.people = this.people.filter(w => w.nationalId.includes(nationalIdVal));
            }
            const personelCode = criteria.find(a => a.key == 'personelCode.contains');
            if (personelCode) {
                const personelCodeVal = personelCode.value;
                if (personelCodeVal) this.people = this.people.filter(w => w.personelCode.includes(personelCodeVal));
            }
            const mobile = criteria.find(a => a.key == 'mobile.contains');
            if (mobile) {
                const mobileVal = mobile.value;
                if (mobileVal) this.people = this.people.filter(w => w.mobile.includes(mobileVal));
            }
            const employmentTypeId = criteria.find(a => a.key == 'employmentTypeId.equals');
            if (employmentTypeId) {
                const employmentTypeIdVal = +employmentTypeId.value;
                if (employmentTypeIdVal) this.people = this.people.filter(w => w.employmentTypeId == employmentTypeIdVal);
            }
            const lastQualificationId = criteria.find(a => a.key == 'lastQualificationId.equals');
            if (lastQualificationId) {
                const lastQualificationIdVal = +lastQualificationId.value;
                if (lastQualificationIdVal) this.people = this.people.filter(w => w.lastQualificationId == lastQualificationIdVal);
            }
        }
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/person-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
       this.loadAll(this.criteria);*/
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
    private setRoles(account: any) {
        if (account) {
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH_2') !== undefined) this.isModirAmozesh2 = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
            if (
                this.isKarshenasArshadAmozeshSazman ||
                this.isModirKolAmozesh ||
                this.isAdmin ||
                this.isModirAmozesh ||
                this.isModirAmozesh2
            )
                this.isTopUsers = true;
        }
    }
    loadUnChartPeople() {
        this.personService.getUnChartPeople().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
                this.allPeople = res.body;
                this.isSaving = false;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadUnChartPeople();
        this.searchbarModel.push(new SearchPanelModel('person', 'name', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('person', 'family', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('person', 'nationalId', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('person', 'personelCode', 'text', 'contains'));
        this.searchbarModel.push(new SearchPanelModel('person', 'mobile', 'text', 'contains'));
        this.prepareEmploymentType();
        this.prepareQualification();
        this.principal.identity().then(account => {
            this.setRoles(account);
            this.personService.find(account.personId).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;
                    this.prepareSearchOrgChart();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        /*if(!this.done){
            this.loadAll();
        }*/
        //this.registerChangeInPeople();
    }
    prepareQualification() {
        this.qualificationService.query().subscribe(
            (res: HttpResponse<IQualificationMarineSuffix[]>) => {
                this.employmenttypes = res.body;

                this.searchbarModel.push(new SearchPanelModel('person', 'lastQualificationId', 'select', 'equals', res.body));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareEmploymentType() {
        this.employmentTypeService.query().subscribe(
            (res: HttpResponse<IEmploymentTypeMarineSuffix[]>) => {
                this.qualifications = res.body;

                this.searchbarModel.push(new SearchPanelModel('person', 'employmentTypeId', 'select', 'equals', res.body));
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareSearchOrgChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.allOrganizationcharts = this.organizationChartService.organizationchartsAll;
            this.organizationcharts = this.convertObjectDatesService.goClone(this.allOrganizationcharts);
            this.orgIds = this.treeUtilities.getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId);
            this.organizationcharts = this.organizationcharts.filter(a => this.orgIds.includes(a.id));
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.allOrganizationcharts = res.body;
                    this.organizationcharts = this.convertObjectDatesService.goClone(this.allOrganizationcharts);
                    this.orgIds = this.treeUtilities.getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId);
                    this.organizationcharts = this.organizationcharts.filter(a => this.orgIds.includes(a.id));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
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
