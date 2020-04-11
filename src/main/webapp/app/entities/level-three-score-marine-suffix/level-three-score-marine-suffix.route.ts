import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LevelThreeScoreMarineSuffix } from 'app/shared/model/level-three-score-marine-suffix.model';
import { LevelThreeScoreMarineSuffixService } from './level-three-score-marine-suffix.service';
import { LevelThreeScoreMarineSuffixComponent } from './level-three-score-marine-suffix.component';
import { LevelThreeScoreMarineSuffixDetailComponent } from './level-three-score-marine-suffix-detail.component';
import { LevelThreeScoreMarineSuffixUpdateComponent } from './level-three-score-marine-suffix-update.component';
import { LevelThreeScoreMarineSuffixDeletePopupComponent } from './level-three-score-marine-suffix-delete-dialog.component';
import { ILevelThreeScoreMarineSuffix } from 'app/shared/model/level-three-score-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class LevelThreeScoreMarineSuffixResolve implements Resolve<ILevelThreeScoreMarineSuffix> {
    constructor(private service: LevelThreeScoreMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LevelThreeScoreMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LevelThreeScoreMarineSuffix>) => response.ok),
                map((levelThreeScore: HttpResponse<LevelThreeScoreMarineSuffix>) => levelThreeScore.body)
            );
        }
        return of(new LevelThreeScoreMarineSuffix());
    }
}

export const levelThreeScoreRoute: Routes = [
    {
        path: 'level-three-score-marine-suffix',
        component: LevelThreeScoreMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.levelThreeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-score-marine-suffix/:id/view',
        component: LevelThreeScoreMarineSuffixDetailComponent,
        resolve: {
            levelThreeScore: LevelThreeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-score-marine-suffix/new',
        component: LevelThreeScoreMarineSuffixUpdateComponent,
        resolve: {
            levelThreeScore: LevelThreeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-score-marine-suffix/:id/edit',
        component: LevelThreeScoreMarineSuffixUpdateComponent,
        resolve: {
            levelThreeScore: LevelThreeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelThreeScorePopupRoute: Routes = [
    {
        path: 'level-three-score-marine-suffix/:id/delete',
        component: LevelThreeScoreMarineSuffixDeletePopupComponent,
        resolve: {
            levelThreeScore: LevelThreeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeScore.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
