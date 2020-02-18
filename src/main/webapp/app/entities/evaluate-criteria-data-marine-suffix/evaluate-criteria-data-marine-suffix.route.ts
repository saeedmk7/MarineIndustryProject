import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EvaluateCriteriaDataMarineSuffix } from 'app/shared/model/evaluate-criteria-data-marine-suffix.model';
import { EvaluateCriteriaDataMarineSuffixService } from './evaluate-criteria-data-marine-suffix.service';
import { EvaluateCriteriaDataMarineSuffixComponent } from './evaluate-criteria-data-marine-suffix.component';
import { EvaluateCriteriaDataMarineSuffixDetailComponent } from './evaluate-criteria-data-marine-suffix-detail.component';
import { EvaluateCriteriaDataMarineSuffixUpdateComponent } from './evaluate-criteria-data-marine-suffix-update.component';
import { EvaluateCriteriaDataMarineSuffixDeletePopupComponent } from './evaluate-criteria-data-marine-suffix-delete-dialog.component';
import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model/evaluate-criteria-data-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EvaluateCriteriaDataMarineSuffixResolve implements Resolve<IEvaluateCriteriaDataMarineSuffix> {
    constructor(private service: EvaluateCriteriaDataMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EvaluateCriteriaDataMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EvaluateCriteriaDataMarineSuffix>) => response.ok),
                map((evaluateCriteriaData: HttpResponse<EvaluateCriteriaDataMarineSuffix>) => evaluateCriteriaData.body)
            );
        }
        return of(new EvaluateCriteriaDataMarineSuffix());
    }
}

export const evaluateCriteriaDataRoute: Routes = [
    {
        path: 'evaluate-criteria-data-marine-suffix',
        component: EvaluateCriteriaDataMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.evaluateCriteriaData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluate-criteria-data-marine-suffix/:id/view',
        component: EvaluateCriteriaDataMarineSuffixDetailComponent,
        resolve: {
            evaluateCriteriaData: EvaluateCriteriaDataMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluate-criteria-data-marine-suffix/new',
        component: EvaluateCriteriaDataMarineSuffixUpdateComponent,
        resolve: {
            evaluateCriteriaData: EvaluateCriteriaDataMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaData.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluate-criteria-data-marine-suffix/:id/edit',
        component: EvaluateCriteriaDataMarineSuffixUpdateComponent,
        resolve: {
            evaluateCriteriaData: EvaluateCriteriaDataMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaData.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const evaluateCriteriaDataPopupRoute: Routes = [
    {
        path: 'evaluate-criteria-data-marine-suffix/:id/delete',
        component: EvaluateCriteriaDataMarineSuffixDeletePopupComponent,
        resolve: {
            evaluateCriteriaData: EvaluateCriteriaDataMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaData.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
