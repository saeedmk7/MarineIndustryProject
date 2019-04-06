import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CriterionMarineSuffix } from 'app/shared/model/criterion-marine-suffix.model';
import { CriterionMarineSuffixService } from './criterion-marine-suffix.service';
import { CriterionMarineSuffixComponent } from './criterion-marine-suffix.component';
import { CriterionMarineSuffixDetailComponent } from './criterion-marine-suffix-detail.component';
import { CriterionMarineSuffixUpdateComponent } from './criterion-marine-suffix-update.component';
import { CriterionMarineSuffixDeletePopupComponent } from './criterion-marine-suffix-delete-dialog.component';
import { ICriterionMarineSuffix } from 'app/shared/model/criterion-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class CriterionMarineSuffixResolve implements Resolve<ICriterionMarineSuffix> {
    constructor(private service: CriterionMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((criterion: HttpResponse<CriterionMarineSuffix>) => criterion.body));
        }
        return of(new CriterionMarineSuffix());
    }
}

export const criterionRoute: Routes = [
    {
        path: 'criterion-marine-suffix',
        component: CriterionMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.criterion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'criterion-marine-suffix/:id/view',
        component: CriterionMarineSuffixDetailComponent,
        resolve: {
            criterion: CriterionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.criterion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'criterion-marine-suffix/new',
        component: CriterionMarineSuffixUpdateComponent,
        resolve: {
            criterion: CriterionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.criterion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'criterion-marine-suffix/:id/edit',
        component: CriterionMarineSuffixUpdateComponent,
        resolve: {
            criterion: CriterionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.criterion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const criterionPopupRoute: Routes = [
    {
        path: 'criterion-marine-suffix/:id/delete',
        component: CriterionMarineSuffixDeletePopupComponent,
        resolve: {
            criterion: CriterionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.criterion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
