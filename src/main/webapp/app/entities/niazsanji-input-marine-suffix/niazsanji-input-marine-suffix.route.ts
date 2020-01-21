import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from './niazsanji-input-marine-suffix.service';
import { NiazsanjiInputMarineSuffixComponent } from './niazsanji-input-marine-suffix.component';
import { NiazsanjiInputMarineSuffixDetailComponent } from './niazsanji-input-marine-suffix-detail.component';
import { NiazsanjiInputMarineSuffixUpdateComponent } from './niazsanji-input-marine-suffix-update.component';
import { NiazsanjiInputMarineSuffixDeletePopupComponent } from './niazsanji-input-marine-suffix-delete-dialog.component';
import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NiazsanjiInputMarineSuffixResolve implements Resolve<INiazsanjiInputMarineSuffix> {
    constructor(private service: NiazsanjiInputMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NiazsanjiInputMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NiazsanjiInputMarineSuffix>) => response.ok),
                map((niazsanjiInput: HttpResponse<NiazsanjiInputMarineSuffix>) => niazsanjiInput.body)
            );
        }
        return of(new NiazsanjiInputMarineSuffix());
    }
}

export const niazsanjiInputRoute: Routes = [
    {
        path: 'niazsanji-input-marine-suffix',
        component: NiazsanjiInputMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.niazsanjiInput.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-input-marine-suffix/:id/view',
        component: NiazsanjiInputMarineSuffixDetailComponent,
        resolve: {
            niazsanjiInput: NiazsanjiInputMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiInput.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-input-marine-suffix/new',
        component: NiazsanjiInputMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiInput: NiazsanjiInputMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiInput.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-input-marine-suffix/:id/edit',
        component: NiazsanjiInputMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiInput: NiazsanjiInputMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiInput.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiInputPopupRoute: Routes = [
    {
        path: 'niazsanji-input-marine-suffix/:id/delete',
        component: NiazsanjiInputMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiInput: NiazsanjiInputMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiInput.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
