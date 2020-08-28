import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SoldierMediaAwarenessReportMarineSuffix } from 'app/shared/model/soldier-media-awareness-report-marine-suffix.model';
import { SoldierMediaAwarenessReportMarineSuffixService } from './soldier-media-awareness-report-marine-suffix.service';
import { SoldierMediaAwarenessReportMarineSuffixComponent } from './soldier-media-awareness-report-marine-suffix.component';
import { SoldierMediaAwarenessReportMarineSuffixDetailComponent } from './soldier-media-awareness-report-marine-suffix-detail.component';
import { SoldierMediaAwarenessReportMarineSuffixUpdateComponent } from './soldier-media-awareness-report-marine-suffix-update.component';
import { SoldierMediaAwarenessReportMarineSuffixDeletePopupComponent } from './soldier-media-awareness-report-marine-suffix-delete-dialog.component';
import { ISoldierMediaAwarenessReportMarineSuffix } from 'app/shared/model/soldier-media-awareness-report-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class SoldierMediaAwarenessReportMarineSuffixResolve implements Resolve<ISoldierMediaAwarenessReportMarineSuffix> {
    constructor(private service: SoldierMediaAwarenessReportMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SoldierMediaAwarenessReportMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<SoldierMediaAwarenessReportMarineSuffix>) => response.ok),
                    map(
                        (soldierMediaAwarenessReport: HttpResponse<SoldierMediaAwarenessReportMarineSuffix>) =>
                            soldierMediaAwarenessReport.body
                    )
                );
        }
        return of(new SoldierMediaAwarenessReportMarineSuffix());
    }
}

export const soldierMediaAwarenessReportRoute: Routes = [
    {
        path: 'soldier-media-awareness-report-marine-suffix',
        component: SoldierMediaAwarenessReportMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.soldierMediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-media-awareness-report-marine-suffix/:id/view',
        component: SoldierMediaAwarenessReportMarineSuffixDetailComponent,
        resolve: {
            soldierMediaAwarenessReport: SoldierMediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierMediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-media-awareness-report-marine-suffix/new',
        component: SoldierMediaAwarenessReportMarineSuffixUpdateComponent,
        resolve: {
            soldierMediaAwarenessReport: SoldierMediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierMediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-media-awareness-report-marine-suffix/:id/edit',
        component: SoldierMediaAwarenessReportMarineSuffixUpdateComponent,
        resolve: {
            soldierMediaAwarenessReport: SoldierMediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierMediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const soldierMediaAwarenessReportPopupRoute: Routes = [
    {
        path: 'soldier-media-awareness-report-marine-suffix/:id/delete',
        component: SoldierMediaAwarenessReportMarineSuffixDeletePopupComponent,
        resolve: {
            soldierMediaAwarenessReport: SoldierMediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierMediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
