import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MonitorLearningProcessGradeMarineSuffix } from 'app/shared/model/monitor-learning-process-grade-marine-suffix.model';
import { MonitorLearningProcessGradeMarineSuffixService } from './monitor-learning-process-grade-marine-suffix.service';
import { MonitorLearningProcessGradeMarineSuffixComponent } from './monitor-learning-process-grade-marine-suffix.component';
import { MonitorLearningProcessGradeMarineSuffixDetailComponent } from './monitor-learning-process-grade-marine-suffix-detail.component';
import { MonitorLearningProcessGradeMarineSuffixUpdateComponent } from './monitor-learning-process-grade-marine-suffix-update.component';
import { MonitorLearningProcessGradeMarineSuffixDeletePopupComponent } from './monitor-learning-process-grade-marine-suffix-delete-dialog.component';
import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model/monitor-learning-process-grade-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MonitorLearningProcessGradeMarineSuffixResolve implements Resolve<IMonitorLearningProcessGradeMarineSuffix> {
    constructor(private service: MonitorLearningProcessGradeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MonitorLearningProcessGradeMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<MonitorLearningProcessGradeMarineSuffix>) => response.ok),
                    map(
                        (monitorLearningProcessGrade: HttpResponse<MonitorLearningProcessGradeMarineSuffix>) =>
                            monitorLearningProcessGrade.body
                    )
                );
        }
        return of(new MonitorLearningProcessGradeMarineSuffix());
    }
}

export const monitorLearningProcessGradeRoute: Routes = [
    {
        path: 'monitor-learning-process-grade-marine-suffix',
        component: MonitorLearningProcessGradeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.monitorLearningProcessGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-grade-marine-suffix/:id/view',
        component: MonitorLearningProcessGradeMarineSuffixDetailComponent,
        resolve: {
            monitorLearningProcessGrade: MonitorLearningProcessGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-grade-marine-suffix/new',
        component: MonitorLearningProcessGradeMarineSuffixUpdateComponent,
        resolve: {
            monitorLearningProcessGrade: MonitorLearningProcessGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'monitor-learning-process-grade-marine-suffix/:id/edit',
        component: MonitorLearningProcessGradeMarineSuffixUpdateComponent,
        resolve: {
            monitorLearningProcessGrade: MonitorLearningProcessGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const monitorLearningProcessGradePopupRoute: Routes = [
    {
        path: 'monitor-learning-process-grade-marine-suffix/:id/delete',
        component: MonitorLearningProcessGradeMarineSuffixDeletePopupComponent,
        resolve: {
            monitorLearningProcessGrade: MonitorLearningProcessGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.monitorLearningProcessGrade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
