import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';
import { MonitorLearningProcessLevelMarineSuffixService } from './monitor-learning-process-level-marine-suffix.service';
import { MonitorLearningProcessLevelMarineSuffixComponent } from './monitor-learning-process-level-marine-suffix.component';
import { MonitorLearningProcessLevelMarineSuffixDetailComponent } from './monitor-learning-process-level-marine-suffix-detail.component';
import { MonitorLearningProcessLevelMarineSuffixUpdateComponent } from './monitor-learning-process-level-marine-suffix-update.component';
import { MonitorLearningProcessLevelMarineSuffixDeletePopupComponent } from './monitor-learning-process-level-marine-suffix-delete-dialog.component';
import { IMonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MonitorLearningProcessLevelMarineSuffixResolve implements Resolve<IMonitorLearningProcessLevelMarineSuffix> {
    constructor(private service: MonitorLearningProcessLevelMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MonitorLearningProcessLevelMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<MonitorLearningProcessLevelMarineSuffix>) => response.ok),
                    map(
                        (monitorLearningProcessLevel: HttpResponse<MonitorLearningProcessLevelMarineSuffix>) =>
                            monitorLearningProcessLevel.body
                    )
                );
        }
        return of(new MonitorLearningProcessLevelMarineSuffix());
    }
}

export const monitorLearningProcessLevelRoute: Routes = [
    {
        path: 'monitor-learning-process-level-marine-suffix',
        component: MonitorLearningProcessLevelMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.monitorLearningProcessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-level-marine-suffix/:id/view',
        component: MonitorLearningProcessLevelMarineSuffixDetailComponent,
        resolve: {
            monitorLearningProcessLevel: MonitorLearningProcessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-level-marine-suffix/new',
        component: MonitorLearningProcessLevelMarineSuffixUpdateComponent,
        resolve: {
            monitorLearningProcessLevel: MonitorLearningProcessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-level-marine-suffix/:id/edit',
        component: MonitorLearningProcessLevelMarineSuffixUpdateComponent,
        resolve: {
            monitorLearningProcessLevel: MonitorLearningProcessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const monitorLearningProcessLevelPopupRoute: Routes = [
    {
        path: 'monitor-learning-process-level-marine-suffix/:id/delete',
        component: MonitorLearningProcessLevelMarineSuffixDeletePopupComponent,
        resolve: {
            monitorLearningProcessLevel: MonitorLearningProcessLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessLevel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
