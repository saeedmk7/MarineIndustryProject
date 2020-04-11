import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EvaluatorOpinionMarineSuffix } from 'app/shared/model/evaluator-opinion-marine-suffix.model';
import { EvaluatorOpinionMarineSuffixService } from './evaluator-opinion-marine-suffix.service';
import { EvaluatorOpinionMarineSuffixComponent } from './evaluator-opinion-marine-suffix.component';
import { EvaluatorOpinionMarineSuffixDetailComponent } from './evaluator-opinion-marine-suffix-detail.component';
import { EvaluatorOpinionMarineSuffixUpdateComponent } from './evaluator-opinion-marine-suffix-update.component';
import { EvaluatorOpinionMarineSuffixDeletePopupComponent } from './evaluator-opinion-marine-suffix-delete-dialog.component';
import { IEvaluatorOpinionMarineSuffix } from 'app/shared/model/evaluator-opinion-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EvaluatorOpinionMarineSuffixResolve implements Resolve<IEvaluatorOpinionMarineSuffix> {
    constructor(private service: EvaluatorOpinionMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EvaluatorOpinionMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EvaluatorOpinionMarineSuffix>) => response.ok),
                map((evaluatorOpinion: HttpResponse<EvaluatorOpinionMarineSuffix>) => evaluatorOpinion.body)
            );
        }
        return of(new EvaluatorOpinionMarineSuffix());
    }
}

export const evaluatorOpinionRoute: Routes = [
    {
        path: 'evaluator-opinion-marine-suffix',
        component: EvaluatorOpinionMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.evaluatorOpinion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluator-opinion-marine-suffix/:id/view',
        component: EvaluatorOpinionMarineSuffixDetailComponent,
        resolve: {
            evaluatorOpinion: EvaluatorOpinionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluatorOpinion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluator-opinion-marine-suffix/new',
        component: EvaluatorOpinionMarineSuffixUpdateComponent,
        resolve: {
            evaluatorOpinion: EvaluatorOpinionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluatorOpinion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'evaluator-opinion-marine-suffix/:id/edit',
        component: EvaluatorOpinionMarineSuffixUpdateComponent,
        resolve: {
            evaluatorOpinion: EvaluatorOpinionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluatorOpinion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const evaluatorOpinionPopupRoute: Routes = [
    {
        path: 'evaluator-opinion-marine-suffix/:id/delete',
        component: EvaluatorOpinionMarineSuffixDeletePopupComponent,
        resolve: {
            evaluatorOpinion: EvaluatorOpinionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.evaluatorOpinion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
