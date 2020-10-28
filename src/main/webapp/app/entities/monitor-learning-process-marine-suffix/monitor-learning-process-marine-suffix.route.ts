import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MonitorLearningProcessMarineSuffix } from 'app/shared/model/monitor-learning-process-marine-suffix.model';
import { MonitorLearningProcessMarineSuffixService } from './monitor-learning-process-marine-suffix.service';
import { MonitorLearningProcessMarineSuffixComponent } from './monitor-learning-process-marine-suffix.component';
import { MonitorLearningProcessMarineSuffixDetailComponent } from './monitor-learning-process-marine-suffix-detail.component';
import { MonitorLearningProcessMarineSuffixUpdateComponent } from './monitor-learning-process-marine-suffix-update.component';
import { MonitorLearningProcessMarineSuffixDeletePopupComponent } from './monitor-learning-process-marine-suffix-delete-dialog.component';
import { IMonitorLearningProcessMarineSuffix } from 'app/shared/model/monitor-learning-process-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MonitorLearningProcessMarineSuffixResolve implements Resolve<IMonitorLearningProcessMarineSuffix> {
    constructor(private service: MonitorLearningProcessMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MonitorLearningProcessMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<MonitorLearningProcessMarineSuffix>) => response.ok),
                    map((monitorLearningProcess: HttpResponse<MonitorLearningProcessMarineSuffix>) => monitorLearningProcess.body)
                );
        }
        return of(new MonitorLearningProcessMarineSuffix());
    }
}

export const monitorLearningProcessRoute: Routes = [
    {
        path: 'monitor-learning-process-marine-suffix',
        component: MonitorLearningProcessMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.monitorLearningProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-marine-suffix/:id/view',
        component: MonitorLearningProcessMarineSuffixDetailComponent,
        resolve: {
            monitorLearningProcess: MonitorLearningProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-marine-suffix/new',
        component: MonitorLearningProcessMarineSuffixUpdateComponent,
        resolve: {
            monitorLearningProcess: MonitorLearningProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-marine-suffix/:id/edit',
        component: MonitorLearningProcessMarineSuffixUpdateComponent,
        resolve: {
            monitorLearningProcess: MonitorLearningProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const monitorLearningProcessPopupRoute: Routes = [
    {
        path: 'monitor-learning-process-marine-suffix/:id/delete',
        component: MonitorLearningProcessMarineSuffixDeletePopupComponent,
        resolve: {
            monitorLearningProcess: MonitorLearningProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
