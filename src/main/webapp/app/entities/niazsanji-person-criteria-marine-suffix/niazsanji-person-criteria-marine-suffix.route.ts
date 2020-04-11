import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NiazsanjiPersonCriteriaMarineSuffix } from 'app/shared/model/niazsanji-person-criteria-marine-suffix.model';
import { NiazsanjiPersonCriteriaMarineSuffixService } from './niazsanji-person-criteria-marine-suffix.service';
import { NiazsanjiPersonCriteriaMarineSuffixComponent } from './niazsanji-person-criteria-marine-suffix.component';
import { NiazsanjiPersonCriteriaMarineSuffixDetailComponent } from './niazsanji-person-criteria-marine-suffix-detail.component';
import { NiazsanjiPersonCriteriaMarineSuffixUpdateComponent } from './niazsanji-person-criteria-marine-suffix-update.component';
import { NiazsanjiPersonCriteriaMarineSuffixDeletePopupComponent } from './niazsanji-person-criteria-marine-suffix-delete-dialog.component';
import { INiazsanjiPersonCriteriaMarineSuffix } from 'app/shared/model/niazsanji-person-criteria-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NiazsanjiPersonCriteriaMarineSuffixResolve implements Resolve<INiazsanjiPersonCriteriaMarineSuffix> {
    constructor(private service: NiazsanjiPersonCriteriaMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NiazsanjiPersonCriteriaMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NiazsanjiPersonCriteriaMarineSuffix>) => response.ok),
                map((niazsanjiPersonCriteria: HttpResponse<NiazsanjiPersonCriteriaMarineSuffix>) => niazsanjiPersonCriteria.body)
            );
        }
        return of(new NiazsanjiPersonCriteriaMarineSuffix());
    }
}

export const niazsanjiPersonCriteriaRoute: Routes = [
    {
        path: 'niazsanji-person-criteria-marine-suffix',
        component: NiazsanjiPersonCriteriaMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.niazsanjiPersonCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-criteria-marine-suffix/:id/view',
        component: NiazsanjiPersonCriteriaMarineSuffixDetailComponent,
        resolve: {
            niazsanjiPersonCriteria: NiazsanjiPersonCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-criteria-marine-suffix/new',
        component: NiazsanjiPersonCriteriaMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiPersonCriteria: NiazsanjiPersonCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-criteria-marine-suffix/:id/edit',
        component: NiazsanjiPersonCriteriaMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiPersonCriteria: NiazsanjiPersonCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiPersonCriteriaPopupRoute: Routes = [
    {
        path: 'niazsanji-person-criteria-marine-suffix/:id/delete',
        component: NiazsanjiPersonCriteriaMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiPersonCriteria: NiazsanjiPersonCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonCriteria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
