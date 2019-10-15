import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ForceRunningPercentMarineSuffix } from 'app/shared/model/force-running-percent-marine-suffix.model';
import { ForceRunningPercentMarineSuffixService } from './force-running-percent-marine-suffix.service';
import { ForceRunningPercentMarineSuffixComponent } from './force-running-percent-marine-suffix.component';
import { ForceRunningPercentMarineSuffixDetailComponent } from './force-running-percent-marine-suffix-detail.component';
import { ForceRunningPercentMarineSuffixUpdateComponent } from './force-running-percent-marine-suffix-update.component';
import { ForceRunningPercentMarineSuffixDeletePopupComponent } from './force-running-percent-marine-suffix-delete-dialog.component';
import { IForceRunningPercentMarineSuffix } from 'app/shared/model/force-running-percent-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ForceRunningPercentMarineSuffixResolve implements Resolve<IForceRunningPercentMarineSuffix> {
    constructor(private service: ForceRunningPercentMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ForceRunningPercentMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ForceRunningPercentMarineSuffix>) => response.ok),
                map((forceRunningPercent: HttpResponse<ForceRunningPercentMarineSuffix>) => forceRunningPercent.body)
            );
        }
        return of(new ForceRunningPercentMarineSuffix());
    }
}

export const forceRunningPercentRoute: Routes = [
    {
        path: 'force-running-percent-marine-suffix',
        component: ForceRunningPercentMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.forceRunningPercent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'force-running-percent-marine-suffix/:id/view',
        component: ForceRunningPercentMarineSuffixDetailComponent,
        resolve: {
            forceRunningPercent: ForceRunningPercentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.forceRunningPercent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'force-running-percent-marine-suffix/new',
        component: ForceRunningPercentMarineSuffixUpdateComponent,
        resolve: {
            forceRunningPercent: ForceRunningPercentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.forceRunningPercent.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'force-running-percent-marine-suffix/:id/edit',
        component: ForceRunningPercentMarineSuffixUpdateComponent,
        resolve: {
            forceRunningPercent: ForceRunningPercentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.forceRunningPercent.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const forceRunningPercentPopupRoute: Routes = [
    {
        path: 'force-running-percent-marine-suffix/:id/delete',
        component: ForceRunningPercentMarineSuffixDeletePopupComponent,
        resolve: {
            forceRunningPercent: ForceRunningPercentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.forceRunningPercent.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
