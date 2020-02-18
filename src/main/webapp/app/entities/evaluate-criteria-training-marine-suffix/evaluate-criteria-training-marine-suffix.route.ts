import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model/evaluate-criteria-training-marine-suffix.model';
import { EvaluateCriteriaTrainingMarineSuffixService } from './evaluate-criteria-training-marine-suffix.service';
import { EvaluateCriteriaTrainingMarineSuffixComponent } from './evaluate-criteria-training-marine-suffix.component';
import { EvaluateCriteriaTrainingMarineSuffixDetailComponent } from './evaluate-criteria-training-marine-suffix-detail.component';
import { EvaluateCriteriaTrainingMarineSuffixUpdateComponent } from './evaluate-criteria-training-marine-suffix-update.component';
import { EvaluateCriteriaTrainingMarineSuffixDeletePopupComponent } from './evaluate-criteria-training-marine-suffix-delete-dialog.component';
import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model/evaluate-criteria-training-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EvaluateCriteriaTrainingMarineSuffixResolve implements Resolve<IEvaluateCriteriaTrainingMarineSuffix> {
    constructor(private service: EvaluateCriteriaTrainingMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EvaluateCriteriaTrainingMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EvaluateCriteriaTrainingMarineSuffix>) => response.ok),
                map((evaluateCriteriaTraining: HttpResponse<EvaluateCriteriaTrainingMarineSuffix>) => evaluateCriteriaTraining.body)
            );
        }
        return of(new EvaluateCriteriaTrainingMarineSuffix());
    }
}

export const evaluateCriteriaTrainingRoute: Routes = [
    {
        path: 'evaluate-criteria-training-marine-suffix',
        component: EvaluateCriteriaTrainingMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.evaluateCriteriaTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluate-criteria-training-marine-suffix/:id/view',
        component: EvaluateCriteriaTrainingMarineSuffixDetailComponent,
        resolve: {
            evaluateCriteriaTraining: EvaluateCriteriaTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluate-criteria-training-marine-suffix/new',
        component: EvaluateCriteriaTrainingMarineSuffixUpdateComponent,
        resolve: {
            evaluateCriteriaTraining: EvaluateCriteriaTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluate-criteria-training-marine-suffix/:id/edit',
        component: EvaluateCriteriaTrainingMarineSuffixUpdateComponent,
        resolve: {
            evaluateCriteriaTraining: EvaluateCriteriaTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const evaluateCriteriaTrainingPopupRoute: Routes = [
    {
        path: 'evaluate-criteria-training-marine-suffix/:id/delete',
        component: EvaluateCriteriaTrainingMarineSuffixDeletePopupComponent,
        resolve: {
            evaluateCriteriaTraining: EvaluateCriteriaTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluateCriteriaTraining.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
