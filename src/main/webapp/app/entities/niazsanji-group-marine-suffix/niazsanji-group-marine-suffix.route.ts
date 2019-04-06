import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import { NiazsanjiGroupMarineSuffixService } from './niazsanji-group-marine-suffix.service';
import { NiazsanjiGroupMarineSuffixComponent } from './niazsanji-group-marine-suffix.component';
import { NiazsanjiGroupMarineSuffixDetailComponent } from './niazsanji-group-marine-suffix-detail.component';
import { NiazsanjiGroupMarineSuffixUpdateComponent } from './niazsanji-group-marine-suffix-update.component';
import { NiazsanjiGroupMarineSuffixDeletePopupComponent } from './niazsanji-group-marine-suffix-delete-dialog.component';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NiazsanjiGroupMarineSuffixResolve implements Resolve<INiazsanjiGroupMarineSuffix> {
    constructor(private service: NiazsanjiGroupMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((niazsanjiGroup: HttpResponse<NiazsanjiGroupMarineSuffix>) => niazsanjiGroup.body));
        }
        return of(new NiazsanjiGroupMarineSuffix());
    }
}

export const niazsanjiGroupRoute: Routes = [
    {
        path: 'niazsanji-group-marine-suffix',
        component: NiazsanjiGroupMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.niazsanjiGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-group-marine-suffix/:id/view',
        component: NiazsanjiGroupMarineSuffixDetailComponent,
        resolve: {
            niazsanjiGroup: NiazsanjiGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-group-marine-suffix/new',
        component: NiazsanjiGroupMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiGroup: NiazsanjiGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-group-marine-suffix/:id/edit',
        component: NiazsanjiGroupMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiGroup: NiazsanjiGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiGroupPopupRoute: Routes = [
    {
        path: 'niazsanji-group-marine-suffix/:id/delete',
        component: NiazsanjiGroupMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiGroup: NiazsanjiGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
