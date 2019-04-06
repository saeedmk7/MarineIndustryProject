import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';
import { EffectivenessIndexMarineSuffixService } from './effectiveness-index-marine-suffix.service';
import { EffectivenessIndexMarineSuffixComponent } from './effectiveness-index-marine-suffix.component';
import { EffectivenessIndexMarineSuffixDetailComponent } from './effectiveness-index-marine-suffix-detail.component';
import { EffectivenessIndexMarineSuffixUpdateComponent } from './effectiveness-index-marine-suffix-update.component';
import { EffectivenessIndexMarineSuffixDeletePopupComponent } from './effectiveness-index-marine-suffix-delete-dialog.component';
import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EffectivenessIndexMarineSuffixResolve implements Resolve<IEffectivenessIndexMarineSuffix> {
    constructor(private service: EffectivenessIndexMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((effectivenessIndex: HttpResponse<EffectivenessIndexMarineSuffix>) => effectivenessIndex.body));
        }
        return of(new EffectivenessIndexMarineSuffix());
    }
}

export const effectivenessIndexRoute: Routes = [
    {
        path: 'effectiveness-index-marine-suffix',
        component: EffectivenessIndexMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-index-marine-suffix/:id/view',
        component: EffectivenessIndexMarineSuffixDetailComponent,
        resolve: {
            effectivenessIndex: EffectivenessIndexMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-index-marine-suffix/new',
        component: EffectivenessIndexMarineSuffixUpdateComponent,
        resolve: {
            effectivenessIndex: EffectivenessIndexMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-index-marine-suffix/:id/edit',
        component: EffectivenessIndexMarineSuffixUpdateComponent,
        resolve: {
            effectivenessIndex: EffectivenessIndexMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessIndex.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const effectivenessIndexPopupRoute: Routes = [
    {
        path: 'effectiveness-index-marine-suffix/:id/delete',
        component: EffectivenessIndexMarineSuffixDeletePopupComponent,
        resolve: {
            effectivenessIndex: EffectivenessIndexMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessIndex.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
