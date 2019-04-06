import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';
import { EffectivenessLevelMarineSuffixService } from './effectiveness-level-marine-suffix.service';
import { EffectivenessLevelMarineSuffixComponent } from './effectiveness-level-marine-suffix.component';
import { EffectivenessLevelMarineSuffixDetailComponent } from './effectiveness-level-marine-suffix-detail.component';
import { EffectivenessLevelMarineSuffixUpdateComponent } from './effectiveness-level-marine-suffix-update.component';
import { EffectivenessLevelMarineSuffixDeletePopupComponent } from './effectiveness-level-marine-suffix-delete-dialog.component';
import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EffectivenessLevelMarineSuffixResolve implements Resolve<IEffectivenessLevelMarineSuffix> {
    constructor(private service: EffectivenessLevelMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((effectivenessLevel: HttpResponse<EffectivenessLevelMarineSuffix>) => effectivenessLevel.body));
        }
        return of(new EffectivenessLevelMarineSuffix());
    }
}

export const effectivenessLevelRoute: Routes = [
    {
        path: 'effectiveness-level-marine-suffix',
        component: EffectivenessLevelMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.effectivenessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-level-marine-suffix/:id/view',
        component: EffectivenessLevelMarineSuffixDetailComponent,
        resolve: {
            effectivenessLevel: EffectivenessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-level-marine-suffix/new',
        component: EffectivenessLevelMarineSuffixUpdateComponent,
        resolve: {
            effectivenessLevel: EffectivenessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'effectiveness-level-marine-suffix/:id/edit',
        component: EffectivenessLevelMarineSuffixUpdateComponent,
        resolve: {
            effectivenessLevel: EffectivenessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const effectivenessLevelPopupRoute: Routes = [
    {
        path: 'effectiveness-level-marine-suffix/:id/delete',
        component: EffectivenessLevelMarineSuffixDeletePopupComponent,
        resolve: {
            effectivenessLevel: EffectivenessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.effectivenessLevel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
