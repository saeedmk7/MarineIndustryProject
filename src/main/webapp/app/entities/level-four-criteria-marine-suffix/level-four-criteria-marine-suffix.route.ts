import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';
import { LevelFourCriteriaMarineSuffixService } from './level-four-criteria-marine-suffix.service';
import { LevelFourCriteriaMarineSuffixComponent } from './level-four-criteria-marine-suffix.component';
import { LevelFourCriteriaMarineSuffixDetailComponent } from './level-four-criteria-marine-suffix-detail.component';
import { LevelFourCriteriaMarineSuffixUpdateComponent } from './level-four-criteria-marine-suffix-update.component';
import { LevelFourCriteriaMarineSuffixDeletePopupComponent } from './level-four-criteria-marine-suffix-delete-dialog.component';
import { ILevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class LevelFourCriteriaMarineSuffixResolve implements Resolve<ILevelFourCriteriaMarineSuffix> {
    constructor(private service: LevelFourCriteriaMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LevelFourCriteriaMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LevelFourCriteriaMarineSuffix>) => response.ok),
                map((levelFourCriteria: HttpResponse<LevelFourCriteriaMarineSuffix>) => levelFourCriteria.body)
            );
        }
        return of(new LevelFourCriteriaMarineSuffix());
    }
}

export const levelFourCriteriaRoute: Routes = [
    {
        path: 'level-four-criteria-marine-suffix',
        component: LevelFourCriteriaMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.levelFourCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-criteria-marine-suffix/:id/view',
        component: LevelFourCriteriaMarineSuffixDetailComponent,
        resolve: {
            levelFourCriteria: LevelFourCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-criteria-marine-suffix/new',
        component: LevelFourCriteriaMarineSuffixUpdateComponent,
        resolve: {
            levelFourCriteria: LevelFourCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-criteria-marine-suffix/:id/edit',
        component: LevelFourCriteriaMarineSuffixUpdateComponent,
        resolve: {
            levelFourCriteria: LevelFourCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelFourCriteriaPopupRoute: Routes = [
    {
        path: 'level-four-criteria-marine-suffix/:id/delete',
        component: LevelFourCriteriaMarineSuffixDeletePopupComponent,
        resolve: {
            levelFourCriteria: LevelFourCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourCriteria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
