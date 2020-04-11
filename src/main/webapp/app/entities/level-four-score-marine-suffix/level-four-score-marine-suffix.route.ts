import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LevelFourScoreMarineSuffix } from 'app/shared/model/level-four-score-marine-suffix.model';
import { LevelFourScoreMarineSuffixService } from './level-four-score-marine-suffix.service';
import { LevelFourScoreMarineSuffixComponent } from './level-four-score-marine-suffix.component';
import { LevelFourScoreMarineSuffixDetailComponent } from './level-four-score-marine-suffix-detail.component';
import { LevelFourScoreMarineSuffixUpdateComponent } from './level-four-score-marine-suffix-update.component';
import { LevelFourScoreMarineSuffixDeletePopupComponent } from './level-four-score-marine-suffix-delete-dialog.component';
import { ILevelFourScoreMarineSuffix } from 'app/shared/model/level-four-score-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class LevelFourScoreMarineSuffixResolve implements Resolve<ILevelFourScoreMarineSuffix> {
    constructor(private service: LevelFourScoreMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LevelFourScoreMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LevelFourScoreMarineSuffix>) => response.ok),
                map((levelFourScore: HttpResponse<LevelFourScoreMarineSuffix>) => levelFourScore.body)
            );
        }
        return of(new LevelFourScoreMarineSuffix());
    }
}

export const levelFourScoreRoute: Routes = [
    {
        path: 'level-four-score-marine-suffix',
        component: LevelFourScoreMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.levelFourScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-score-marine-suffix/:id/view',
        component: LevelFourScoreMarineSuffixDetailComponent,
        resolve: {
            levelFourScore: LevelFourScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-score-marine-suffix/new',
        component: LevelFourScoreMarineSuffixUpdateComponent,
        resolve: {
            levelFourScore: LevelFourScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-score-marine-suffix/:id/edit',
        component: LevelFourScoreMarineSuffixUpdateComponent,
        resolve: {
            levelFourScore: LevelFourScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelFourScorePopupRoute: Routes = [
    {
        path: 'level-four-score-marine-suffix/:id/delete',
        component: LevelFourScoreMarineSuffixDeletePopupComponent,
        resolve: {
            levelFourScore: LevelFourScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourScore.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
