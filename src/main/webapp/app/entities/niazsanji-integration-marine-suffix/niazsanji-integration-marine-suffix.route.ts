import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NiazsanjiIntegrationMarineSuffix } from 'app/shared/model/niazsanji-integration-marine-suffix.model';
import { NiazsanjiIntegrationMarineSuffixService } from './niazsanji-integration-marine-suffix.service';
import { NiazsanjiIntegrationMarineSuffixComponent } from './niazsanji-integration-marine-suffix.component';
import { NiazsanjiIntegrationMarineSuffixDetailComponent } from './niazsanji-integration-marine-suffix-detail.component';
import { NiazsanjiIntegrationMarineSuffixUpdateComponent } from './niazsanji-integration-marine-suffix-update.component';
import { NiazsanjiIntegrationMarineSuffixDeletePopupComponent } from './niazsanji-integration-marine-suffix-delete-dialog.component';
import { INiazsanjiIntegrationMarineSuffix } from 'app/shared/model/niazsanji-integration-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NiazsanjiIntegrationMarineSuffixResolve implements Resolve<INiazsanjiIntegrationMarineSuffix> {
    constructor(private service: NiazsanjiIntegrationMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NiazsanjiIntegrationMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NiazsanjiIntegrationMarineSuffix>) => response.ok),
                map((niazsanjiIntegration: HttpResponse<NiazsanjiIntegrationMarineSuffix>) => niazsanjiIntegration.body)
            );
        }
        return of(new NiazsanjiIntegrationMarineSuffix());
    }
}

export const niazsanjiIntegrationRoute: Routes = [
    {
        path: 'niazsanji-integration-marine-suffix',
        component: NiazsanjiIntegrationMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.niazsanjiIntegration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-integration-marine-suffix/:id/view',
        component: NiazsanjiIntegrationMarineSuffixDetailComponent,
        resolve: {
            niazsanjiIntegration: NiazsanjiIntegrationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiIntegration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-integration-marine-suffix/new',
        component: NiazsanjiIntegrationMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiIntegration: NiazsanjiIntegrationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiIntegration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-integration-marine-suffix/:id/edit',
        component: NiazsanjiIntegrationMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiIntegration: NiazsanjiIntegrationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiIntegration.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiIntegrationPopupRoute: Routes = [
    {
        path: 'niazsanji-integration-marine-suffix/:id/delete',
        component: NiazsanjiIntegrationMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiIntegration: NiazsanjiIntegrationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiIntegration.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
