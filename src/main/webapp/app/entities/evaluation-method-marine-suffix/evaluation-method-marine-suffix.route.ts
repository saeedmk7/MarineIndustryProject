import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';
import { EvaluationMethodMarineSuffixService } from './evaluation-method-marine-suffix.service';
import { EvaluationMethodMarineSuffixComponent } from './evaluation-method-marine-suffix.component';
import { EvaluationMethodMarineSuffixDetailComponent } from './evaluation-method-marine-suffix-detail.component';
import { EvaluationMethodMarineSuffixUpdateComponent } from './evaluation-method-marine-suffix-update.component';
import { EvaluationMethodMarineSuffixDeletePopupComponent } from './evaluation-method-marine-suffix-delete-dialog.component';
import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EvaluationMethodMarineSuffixResolve implements Resolve<IEvaluationMethodMarineSuffix> {
    constructor(private service: EvaluationMethodMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((evaluationMethod: HttpResponse<EvaluationMethodMarineSuffix>) => evaluationMethod.body));
        }
        return of(new EvaluationMethodMarineSuffix());
    }
}

export const evaluationMethodRoute: Routes = [
    {
        path: 'evaluation-method-marine-suffix',
        component: EvaluationMethodMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.evaluationMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluation-method-marine-suffix/:id/view',
        component: EvaluationMethodMarineSuffixDetailComponent,
        resolve: {
            evaluationMethod: EvaluationMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluationMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluation-method-marine-suffix/new',
        component: EvaluationMethodMarineSuffixUpdateComponent,
        resolve: {
            evaluationMethod: EvaluationMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluationMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluation-method-marine-suffix/:id/edit',
        component: EvaluationMethodMarineSuffixUpdateComponent,
        resolve: {
            evaluationMethod: EvaluationMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluationMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const evaluationMethodPopupRoute: Routes = [
    {
        path: 'evaluation-method-marine-suffix/:id/delete',
        component: EvaluationMethodMarineSuffixDeletePopupComponent,
        resolve: {
            evaluationMethod: EvaluationMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluationMethod.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
