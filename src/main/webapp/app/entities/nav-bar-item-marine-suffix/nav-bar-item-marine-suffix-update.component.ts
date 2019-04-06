import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';
import { NavBarItemMarineSuffixService } from './nav-bar-item-marine-suffix.service';

@Component({
    selector: 'mi-nav-bar-item-marine-suffix-update',
    templateUrl: './nav-bar-item-marine-suffix-update.component.html'
})
export class NavBarItemMarineSuffixUpdateComponent implements OnInit {
    private _navBarItem: INavBarItemMarineSuffix;
    isSaving: boolean;

    navbaritems: INavBarItemMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private navBarItemService: NavBarItemMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ navBarItem }) => {
            this.navBarItem = navBarItem;
        });
        this.navBarItemService.query().subscribe(
            (res: HttpResponse<INavBarItemMarineSuffix[]>) => {
                this.navbaritems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.navBarItem.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.navBarItem.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.navBarItem.id !== undefined) {
            this.subscribeToSaveResponse(this.navBarItemService.update(this.navBarItem));
        } else {
            this.subscribeToSaveResponse(this.navBarItemService.create(this.navBarItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INavBarItemMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INavBarItemMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackNavBarItemById(index: number, item: INavBarItemMarineSuffix) {
        return item.id;
    }
    get navBarItem() {
        return this._navBarItem;
    }

    set navBarItem(navBarItem: INavBarItemMarineSuffix) {
        this._navBarItem = navBarItem;
        this.createDate = moment(navBarItem.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(navBarItem.modifyDate).format(DATE_TIME_FORMAT);
    }
}
