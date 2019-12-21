import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from './restriction-marine-suffix.service';
import { RestrictionMarineSuffixComponent } from './restriction-marine-suffix.component';
import { RestrictionMarineSuffixDetailComponent } from './restriction-marine-suffix-detail.component';
import { RestrictionMarineSuffixUpdateComponent } from './restriction-marine-suffix-update.component';
import { RestrictionMarineSuffixDeletePopupComponent } from './restriction-marine-suffix-delete-dialog.component';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class RestrictionMarineSuffixResolve implements Resolve<IRestrictionMarineSuffix> {
    constructor(private service: RestrictionMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RestrictionMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RestrictionMarineSuffix>) => response.ok),
                map((restriction: HttpResponse<RestrictionMarineSuffix>) => restriction.body)
            );
        }
        return of(new RestrictionMarineSuffix());
    }
}

export const restrictionRoute: Routes = [
    {
        path: 'restriction-marine-suffix',
        component: RestrictionMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.restriction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'restriction-marine-suffix/:id/view',
        component: RestrictionMarineSuffixDetailComponent,
        resolve: {
            restriction: RestrictionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.restriction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'restriction-marine-suffix/new',
        component: RestrictionMarineSuffixUpdateComponent,
        resolve: {
            restriction: RestrictionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.restriction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'restriction-marine-suffix/:id/edit',
        component: RestrictionMarineSuffixUpdateComponent,
        resolve: {
            restriction: RestrictionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.restriction.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const restrictionPopupRoute: Routes = [
    {
        path: 'restriction-marine-suffix/:id/delete',
        component: RestrictionMarineSuffixDeletePopupComponent,
        resolve: {
            restriction: RestrictionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.restriction.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
