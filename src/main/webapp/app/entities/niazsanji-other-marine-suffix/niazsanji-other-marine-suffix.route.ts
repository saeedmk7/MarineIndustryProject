import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NiazsanjiOtherMarineSuffix } from 'app/shared/model/niazsanji-other-marine-suffix.model';
import { NiazsanjiOtherMarineSuffixService } from './niazsanji-other-marine-suffix.service';
import { NiazsanjiOtherMarineSuffixComponent } from './niazsanji-other-marine-suffix.component';
import { NiazsanjiOtherMarineSuffixDetailComponent } from './niazsanji-other-marine-suffix-detail.component';
import { NiazsanjiOtherMarineSuffixUpdateComponent } from './niazsanji-other-marine-suffix-update.component';
import { NiazsanjiOtherMarineSuffixDeletePopupComponent } from './niazsanji-other-marine-suffix-delete-dialog.component';
import { INiazsanjiOtherMarineSuffix } from 'app/shared/model/niazsanji-other-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NiazsanjiOtherMarineSuffixResolve implements Resolve<INiazsanjiOtherMarineSuffix> {
    constructor(private service: NiazsanjiOtherMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NiazsanjiOtherMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NiazsanjiOtherMarineSuffix>) => response.ok),
                map((niazsanjiOther: HttpResponse<NiazsanjiOtherMarineSuffix>) => niazsanjiOther.body)
            );
        }
        return of(new NiazsanjiOtherMarineSuffix());
    }
}

export const niazsanjiOtherRoute: Routes = [
    {
        path: 'niazsanji-other-marine-suffix',
        component: NiazsanjiOtherMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.niazsanjiOther.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-other-marine-suffix/:id/view',
        component: NiazsanjiOtherMarineSuffixDetailComponent,
        resolve: {
            niazsanjiOther: NiazsanjiOtherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiOther.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-other-marine-suffix/new',
        component: NiazsanjiOtherMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiOther: NiazsanjiOtherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiOther.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-other-marine-suffix/:id/edit',
        component: NiazsanjiOtherMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiOther: NiazsanjiOtherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiOther.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiOtherPopupRoute: Routes = [
    {
        path: 'niazsanji-other-marine-suffix/:id/delete',
        component: NiazsanjiOtherMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiOther: NiazsanjiOtherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiOther.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
