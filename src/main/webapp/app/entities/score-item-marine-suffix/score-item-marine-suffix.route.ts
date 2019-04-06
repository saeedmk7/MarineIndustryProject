import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ScoreItemMarineSuffix } from 'app/shared/model/score-item-marine-suffix.model';
import { ScoreItemMarineSuffixService } from './score-item-marine-suffix.service';
import { ScoreItemMarineSuffixComponent } from './score-item-marine-suffix.component';
import { ScoreItemMarineSuffixDetailComponent } from './score-item-marine-suffix-detail.component';
import { ScoreItemMarineSuffixUpdateComponent } from './score-item-marine-suffix-update.component';
import { ScoreItemMarineSuffixDeletePopupComponent } from './score-item-marine-suffix-delete-dialog.component';
import { IScoreItemMarineSuffix } from 'app/shared/model/score-item-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ScoreItemMarineSuffixResolve implements Resolve<IScoreItemMarineSuffix> {
    constructor(private service: ScoreItemMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((scoreItem: HttpResponse<ScoreItemMarineSuffix>) => scoreItem.body));
        }
        return of(new ScoreItemMarineSuffix());
    }
}

export const scoreItemRoute: Routes = [
    {
        path: 'score-item-marine-suffix',
        component: ScoreItemMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.scoreItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'score-item-marine-suffix/:id/view',
        component: ScoreItemMarineSuffixDetailComponent,
        resolve: {
            scoreItem: ScoreItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scoreItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'score-item-marine-suffix/new',
        component: ScoreItemMarineSuffixUpdateComponent,
        resolve: {
            scoreItem: ScoreItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scoreItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'score-item-marine-suffix/:id/edit',
        component: ScoreItemMarineSuffixUpdateComponent,
        resolve: {
            scoreItem: ScoreItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scoreItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const scoreItemPopupRoute: Routes = [
    {
        path: 'score-item-marine-suffix/:id/delete',
        component: ScoreItemMarineSuffixDeletePopupComponent,
        resolve: {
            scoreItem: ScoreItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scoreItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
