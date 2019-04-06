import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';
import { GoalMarineSuffixService } from './goal-marine-suffix.service';
import { GoalMarineSuffixComponent } from './goal-marine-suffix.component';
import { GoalMarineSuffixDetailComponent } from './goal-marine-suffix-detail.component';
import { GoalMarineSuffixUpdateComponent } from './goal-marine-suffix-update.component';
import { GoalMarineSuffixDeletePopupComponent } from './goal-marine-suffix-delete-dialog.component';
import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class GoalMarineSuffixResolve implements Resolve<IGoalMarineSuffix> {
    constructor(private service: GoalMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((goal: HttpResponse<GoalMarineSuffix>) => goal.body));
        }
        return of(new GoalMarineSuffix());
    }
}

export const goalRoute: Routes = [
    {
        path: 'goal-marine-suffix',
        component: GoalMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.goal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goal-marine-suffix/:id/view',
        component: GoalMarineSuffixDetailComponent,
        resolve: {
            goal: GoalMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.goal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goal-marine-suffix/new',
        component: GoalMarineSuffixUpdateComponent,
        resolve: {
            goal: GoalMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.goal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'goal-marine-suffix/:id/edit',
        component: GoalMarineSuffixUpdateComponent,
        resolve: {
            goal: GoalMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.goal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const goalPopupRoute: Routes = [
    {
        path: 'goal-marine-suffix/:id/delete',
        component: GoalMarineSuffixDeletePopupComponent,
        resolve: {
            goal: GoalMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.goal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
