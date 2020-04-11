import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';
import { LevelThreeCriteriaMarineSuffixService } from './level-three-criteria-marine-suffix.service';
import { LevelThreeCriteriaMarineSuffixComponent } from './level-three-criteria-marine-suffix.component';
import { LevelThreeCriteriaMarineSuffixDetailComponent } from './level-three-criteria-marine-suffix-detail.component';
import { LevelThreeCriteriaMarineSuffixUpdateComponent } from './level-three-criteria-marine-suffix-update.component';
import { LevelThreeCriteriaMarineSuffixDeletePopupComponent } from './level-three-criteria-marine-suffix-delete-dialog.component';
import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class LevelThreeCriteriaMarineSuffixResolve implements Resolve<ILevelThreeCriteriaMarineSuffix> {
    constructor(private service: LevelThreeCriteriaMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LevelThreeCriteriaMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LevelThreeCriteriaMarineSuffix>) => response.ok),
                map((levelThreeCriteria: HttpResponse<LevelThreeCriteriaMarineSuffix>) => levelThreeCriteria.body)
            );
        }
        return of(new LevelThreeCriteriaMarineSuffix());
    }
}

export const levelThreeCriteriaRoute: Routes = [
    {
        path: 'level-three-criteria-marine-suffix',
        component: LevelThreeCriteriaMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.levelThreeCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-criteria-marine-suffix/:id/view',
        component: LevelThreeCriteriaMarineSuffixDetailComponent,
        resolve: {
            levelThreeCriteria: LevelThreeCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-criteria-marine-suffix/new',
        component: LevelThreeCriteriaMarineSuffixUpdateComponent,
        resolve: {
            levelThreeCriteria: LevelThreeCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-criteria-marine-suffix/:id/edit',
        component: LevelThreeCriteriaMarineSuffixUpdateComponent,
        resolve: {
            levelThreeCriteria: LevelThreeCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelThreeCriteriaPopupRoute: Routes = [
    {
        path: 'level-three-criteria-marine-suffix/:id/delete',
        component: LevelThreeCriteriaMarineSuffixDeletePopupComponent,
        resolve: {
            levelThreeCriteria: LevelThreeCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
