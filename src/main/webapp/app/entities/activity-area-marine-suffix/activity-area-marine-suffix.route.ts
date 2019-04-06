import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ActivityAreaMarineSuffix } from 'app/shared/model/activity-area-marine-suffix.model';
import { ActivityAreaMarineSuffixService } from './activity-area-marine-suffix.service';
import { ActivityAreaMarineSuffixComponent } from './activity-area-marine-suffix.component';
import { ActivityAreaMarineSuffixDetailComponent } from './activity-area-marine-suffix-detail.component';
import { ActivityAreaMarineSuffixUpdateComponent } from './activity-area-marine-suffix-update.component';
import { ActivityAreaMarineSuffixDeletePopupComponent } from './activity-area-marine-suffix-delete-dialog.component';
import { IActivityAreaMarineSuffix } from 'app/shared/model/activity-area-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ActivityAreaMarineSuffixResolve implements Resolve<IActivityAreaMarineSuffix> {
    constructor(private service: ActivityAreaMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((activityArea: HttpResponse<ActivityAreaMarineSuffix>) => activityArea.body));
        }
        return of(new ActivityAreaMarineSuffix());
    }
}

export const activityAreaRoute: Routes = [
    {
        path: 'activity-area-marine-suffix',
        component: ActivityAreaMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.activityArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activity-area-marine-suffix/:id/view',
        component: ActivityAreaMarineSuffixDetailComponent,
        resolve: {
            activityArea: ActivityAreaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activityArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activity-area-marine-suffix/new',
        component: ActivityAreaMarineSuffixUpdateComponent,
        resolve: {
            activityArea: ActivityAreaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activityArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activity-area-marine-suffix/:id/edit',
        component: ActivityAreaMarineSuffixUpdateComponent,
        resolve: {
            activityArea: ActivityAreaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activityArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const activityAreaPopupRoute: Routes = [
    {
        path: 'activity-area-marine-suffix/:id/delete',
        component: ActivityAreaMarineSuffixDeletePopupComponent,
        resolve: {
            activityArea: ActivityAreaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activityArea.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
