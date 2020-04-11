import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LevelThreeCriteriaGroupMarineSuffix } from 'app/shared/model/level-three-criteria-group-marine-suffix.model';
import { LevelThreeCriteriaGroupMarineSuffixService } from './level-three-criteria-group-marine-suffix.service';
import { LevelThreeCriteriaGroupMarineSuffixComponent } from './level-three-criteria-group-marine-suffix.component';
import { LevelThreeCriteriaGroupMarineSuffixDetailComponent } from './level-three-criteria-group-marine-suffix-detail.component';
import { LevelThreeCriteriaGroupMarineSuffixUpdateComponent } from './level-three-criteria-group-marine-suffix-update.component';
import { LevelThreeCriteriaGroupMarineSuffixDeletePopupComponent } from './level-three-criteria-group-marine-suffix-delete-dialog.component';
import { ILevelThreeCriteriaGroupMarineSuffix } from 'app/shared/model/level-three-criteria-group-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class LevelThreeCriteriaGroupMarineSuffixResolve implements Resolve<ILevelThreeCriteriaGroupMarineSuffix> {
    constructor(private service: LevelThreeCriteriaGroupMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LevelThreeCriteriaGroupMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LevelThreeCriteriaGroupMarineSuffix>) => response.ok),
                map((levelThreeCriteriaGroup: HttpResponse<LevelThreeCriteriaGroupMarineSuffix>) => levelThreeCriteriaGroup.body)
            );
        }
        return of(new LevelThreeCriteriaGroupMarineSuffix());
    }
}

export const levelThreeCriteriaGroupRoute: Routes = [
    {
        path: 'level-three-criteria-group-marine-suffix',
        component: LevelThreeCriteriaGroupMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.levelThreeCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-criteria-group-marine-suffix/:id/view',
        component: LevelThreeCriteriaGroupMarineSuffixDetailComponent,
        resolve: {
            levelThreeCriteriaGroup: LevelThreeCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-criteria-group-marine-suffix/new',
        component: LevelThreeCriteriaGroupMarineSuffixUpdateComponent,
        resolve: {
            levelThreeCriteriaGroup: LevelThreeCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-criteria-group-marine-suffix/:id/edit',
        component: LevelThreeCriteriaGroupMarineSuffixUpdateComponent,
        resolve: {
            levelThreeCriteriaGroup: LevelThreeCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelThreeCriteriaGroupPopupRoute: Routes = [
    {
        path: 'level-three-criteria-group-marine-suffix/:id/delete',
        component: LevelThreeCriteriaGroupMarineSuffixDeletePopupComponent,
        resolve: {
            levelThreeCriteriaGroup: LevelThreeCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
