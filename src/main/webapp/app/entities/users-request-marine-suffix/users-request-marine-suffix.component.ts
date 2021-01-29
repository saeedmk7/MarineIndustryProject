import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiAlertService, JhiDataUtils, JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IUsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { UsersRequestMarineSuffixService } from './users-request-marine-suffix.service';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { IPersonMarineSuffix, PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';

@Component({
    selector: 'mi-users-request-marine-suffix',
    templateUrl: './users-request-marine-suffix.component.html',
    styleUrls: ['./users-request-marine-suffix.scss']
})
export class UsersRequestMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    currentPerson: any;
    usersRequests: IUsersRequestMarineSuffix[];
    referalUsersRequests: IUsersRequestMarineSuffix[];
    people: IPersonMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
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
        private usersRequestService: UsersRequestMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private dataUtils: JhiDataUtils,
        private router: Router,
        private eventManager: JhiEventManager,
        private personService: PersonMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.descending;
            this.predicate = data.pagingParams.predicate;
        });
    }
    changeStatus(usersRequest: IUsersRequestMarineSuffix, newStatus: string) {
        if (confirm('آیا از تغییر وضعیت مطمئنید؟')) {
            this.usersRequestService.find(usersRequest.id).subscribe(resp => {
                let model = resp.body;
                //var requestStatus : RequestStatus = RequestStatus[newStatus];
                //let requestStatus: keyof typeof RequestStatus = newStatus;
                //var requestStatus: RequestStatus = <RequestStatus>RequestStatus[newStatus];
                model.requestStatus = this.convertObjectDatesService.convertString2RequestStatus(newStatus);
                model.changeStatusUserLogin = this.currentAccount.login;
                this.usersRequestService
                    .update(model)
                    .subscribe(
                        (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => this.onSaveSuccess(),
                        (res: HttpErrorResponse) => this.onSaveError()
                    );
            });
        }
    }
    private onSaveSuccess() {
        this.loadAll();
    }

    private onSaveError() {
        this.loadAll();
    }
    loadAll(criteria?) {
        if (!this.isAdmin) {
            if (criteria) {
                criteria.push({ key: 'createUserLogin.equals', value: this.currentAccount.login });
            } else {
                criteria = [{ key: 'createUserLogin.equals', value: this.currentAccount.login }];
            }
        }
        this.usersRequestService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IUsersRequestMarineSuffix[]>) => this.paginateUsersRequests(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );

        if (!this.isAdmin) {
            if (this.currentAccount.personId) {
                this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;
                    criteria = [{ key: 'personId.equals', value: this.currentPerson.id }];
                    this.usersRequestService
                        .query({
                            page: 0,
                            size: 20000,
                            criteria,
                            sort: this.sort()
                        })
                        .subscribe(
                            (res: HttpResponse<IUsersRequestMarineSuffix[]>) => this.paginateReferUsersRequests(res.body, res.headers),
                            (res: HttpErrorResponse) => this.onError(res.message)
                        );
                });
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
        this.router.navigate(['/users-request-marine-suffix'], {
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
            '/users-request-marine-suffix',
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
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            this.loadAll();
            this.registerChangeInUsersRequests();
        });
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUsersRequestMarineSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInUsersRequests() {
        this.eventSubscriber = this.eventManager.subscribe('usersRequestListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateUsersRequests(data: IUsersRequestMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.usersRequests = this.convertObjectDatesService.changeArrayDate(data);
        let personNationalIds = this.usersRequests.map(a => a.createUserLogin);
        this.usersRequests.map(a => a.changeStatusUserLogin).forEach(a => {
            personNationalIds.push(a);
        });
        this.loadPeople(personNationalIds.filter(this.treeUtilities.onlyUnique));
    }
    private paginateReferUsersRequests(data: IUsersRequestMarineSuffix[], headers: HttpHeaders) {
        this.referalUsersRequests = this.convertObjectDatesService.changeArrayDate(data);
        let personNationalIds = this.referalUsersRequests.map(a => a.createUserLogin);
        debugger;
        this.referalUsersRequests.map(a => a.changeStatusUserLogin).forEach(a => {
            personNationalIds.push(a);
        });
        this.loadPeople(personNationalIds.filter(this.treeUtilities.onlyUnique));
    }
    loadPeople(personNationalIds: string[]) {
        if (personNationalIds && personNationalIds.length > 0) {
            debugger;

            let criteria = [
                {
                    key: 'nationalId.in',
                    value: personNationalIds
                }
            ];
            this.personService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (resp: HttpResponse<IPersonMarineSuffix[]>) => {
                        debugger;
                        this.people = resp.body;
                        this.loadChart();
                    },
                    error => this.onError('فردی یافت نشد.')
                );
        }
    }
    loadChart() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.makeData();
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.makeData();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    makeData() {
        this.usersRequests.forEach(a => {
            let person = this.people.find(w => w.nationalId.includes(a.createUserLogin));
            if (person) {
                a.createUserLogin = person.fullName;
                const org = this.organizationcharts.find(w => w.id == person.organizationChartId);
                if (org) a.orgChartRoot = org.fullTitle.split('>')[0];
            }
            person = this.people.find(w => w.nationalId.includes(a.changeStatusUserLogin));
            if (person) {
                a.changeStatusUserLogin = person.fullName;
            }
        });
        this.referalUsersRequests.forEach(a => {
            let person = this.people.find(w => w.nationalId.includes(a.createUserLogin));
            if (person) {
                a.createUserLogin = person.fullName;
                const org = this.organizationcharts.find(w => w.id == person.organizationChartId);
                if (org) a.orgChartRoot = org.fullTitle.split('>')[0];
            }
            person = this.people.find(w => w.nationalId.includes(a.changeStatusUserLogin));
            if (person) {
                a.changeStatusUserLogin = person.fullName;
            }
        });
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
