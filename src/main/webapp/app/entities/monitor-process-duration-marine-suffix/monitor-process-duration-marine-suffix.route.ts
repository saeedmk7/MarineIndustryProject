import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';
import { MonitorProcessDurationMarineSuffixService } from './monitor-process-duration-marine-suffix.service';
import { MonitorProcessDurationMarineSuffixComponent } from './monitor-process-duration-marine-suffix.component';
import { MonitorProcessDurationMarineSuffixDetailComponent } from './monitor-process-duration-marine-suffix-detail.component';
import { MonitorProcessDurationMarineSuffixUpdateComponent } from './monitor-process-duration-marine-suffix-update.component';
import { MonitorProcessDurationMarineSuffixDeletePopupComponent } from './monitor-process-duration-marine-suffix-delete-dialog.component';
import { IMonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MonitorProcessDurationMarineSuffixResolve implements Resolve<IMonitorProcessDurationMarineSuffix> {
    constructor(private service: MonitorProcessDurationMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MonitorProcessDurationMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<MonitorProcessDurationMarineSuffix>) => response.ok),
                    map((monitorProcessDuration: HttpResponse<MonitorProcessDurationMarineSuffix>) => monitorProcessDuration.body)
                );
        }
        return of(new MonitorProcessDurationMarineSuffix());
    }
}

export const monitorProcessDurationRoute: Routes = [
    {
        path: 'monitor-process-duration-marine-suffix',
        component: MonitorProcessDurationMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.monitorProcessDuration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-process-duration-marine-suffix/:id/view',
        component: MonitorProcessDurationMarineSuffixDetailComponent,
        resolve: {
            monitorProcessDuration: MonitorProcessDurationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorProcessDuration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-process-duration-marine-suffix/new',
        component: MonitorProcessDurationMarineSuffixUpdateComponent,
        resolve: {
            monitorProcessDuration: MonitorProcessDurationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorProcessDuration.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-process-duration-marine-suffix/:id/edit',
        component: MonitorProcessDurationMarineSuffixUpdateComponent,
        resolve: {
            monitorProcessDuration: MonitorProcessDurationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorProcessDuration.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const monitorProcessDurationPopupRoute: Routes = [
    {
        path: 'monitor-process-duration-marine-suffix/:id/delete',
        component: MonitorProcessDurationMarineSuffixDeletePopupComponent,
        resolve: {
            monitorProcessDuration: MonitorProcessDurationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorProcessDuration.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
