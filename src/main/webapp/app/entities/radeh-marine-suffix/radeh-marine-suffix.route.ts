import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RadehMarineSuffix } from 'app/shared/model/radeh-marine-suffix.model';
import { RadehMarineSuffixService } from './radeh-marine-suffix.service';
import { RadehMarineSuffixComponent } from './radeh-marine-suffix.component';
import { RadehMarineSuffixDetailComponent } from './radeh-marine-suffix-detail.component';
import { RadehMarineSuffixUpdateComponent } from './radeh-marine-suffix-update.component';
import { RadehMarineSuffixDeletePopupComponent } from './radeh-marine-suffix-delete-dialog.component';
import { IRadehMarineSuffix } from 'app/shared/model/radeh-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class RadehMarineSuffixResolve implements Resolve<IRadehMarineSuffix> {
    constructor(private service: RadehMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((radeh: HttpResponse<RadehMarineSuffix>) => radeh.body));
        }
        return of(new RadehMarineSuffix());
    }
}

export const radehRoute: Routes = [
    {
        path: 'radeh-marine-suffix',
        component: RadehMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.radeh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radeh-marine-suffix/:id/view',
        component: RadehMarineSuffixDetailComponent,
        resolve: {
            radeh: RadehMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.radeh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radeh-marine-suffix/new',
        component: RadehMarineSuffixUpdateComponent,
        resolve: {
            radeh: RadehMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.radeh.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'radeh-marine-suffix/:id/edit',
        component: RadehMarineSuffixUpdateComponent,
        resolve: {
            radeh: RadehMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.radeh.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const radehPopupRoute: Routes = [
    {
        path: 'radeh-marine-suffix/:id/delete',
        component: RadehMarineSuffixDeletePopupComponent,
        resolve: {
            radeh: RadehMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.radeh.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
