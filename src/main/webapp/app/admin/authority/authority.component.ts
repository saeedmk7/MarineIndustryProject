import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import {Principal, User} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AuthorityService } from './authority.service';
import {Authority, IAuthority} from "app/shared/model/authority.model";
import {AuthorityDeleteDialogComponent, UserMgmtDeleteDialogComponent} from "app/admin";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
    selector: 'mi-authority',
    templateUrl: './authority.component.html'
})
export class AuthorityComponent implements OnInit, OnDestroy {
    currentAccount: any;
    authorities: IAuthority[];
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

    constructor(
        private authorityService: AuthorityService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private modalService: NgbModal

    ) {
    }

    loadAll() {
        this.authorityService
            .authorities()
            .subscribe(
                (res: HttpResponse<IAuthority[]>) => this.paginateAuthority(res.body),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }


    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAuthority();
    }
    deleteAuthority(authority: Authority) {
        const modalRef = this.modalService.open(AuthorityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.authority = authority;
        modalRef.result.then(
            result => {
                // Left blank intentionally, nothing to do here
            },
            reason => {
                // Left blank intentionally, nothing to do here
            }
        );
    }
    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuthority() {
        this.eventSubscriber = this.eventManager.subscribe('authorityListModification', response => this.loadAll());
    }

    private paginateAuthority(data: IAuthority[]) {
        this.authorities = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
