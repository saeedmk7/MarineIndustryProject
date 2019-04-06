import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { INavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';
import { Principal } from 'app/core';
import { NavBarItemMarineSuffixService } from './nav-bar-item-marine-suffix.service';

@Component({
    selector: 'mi-nav-bar-item-marine-suffix',
    templateUrl: './nav-bar-item-marine-suffix.component.html'
})
export class NavBarItemMarineSuffixComponent implements OnInit, OnDestroy {
    navBarItems: INavBarItemMarineSuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private navBarItemService: NavBarItemMarineSuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.navBarItemService.query().subscribe(
            (res: HttpResponse<INavBarItemMarineSuffix[]>) => {
                this.navBarItems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInNavBarItems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: INavBarItemMarineSuffix) {
        return item.id;
    }

    registerChangeInNavBarItems() {
        this.eventSubscriber = this.eventManager.subscribe('navBarItemListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
