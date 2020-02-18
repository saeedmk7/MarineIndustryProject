import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';
import { SoldierTrainingReportMarineSuffixService } from './soldier-training-report-marine-suffix.service';
import { SoldierTrainingReportMarineSuffixComponent } from './soldier-training-report-marine-suffix.component';
import { SoldierTrainingReportMarineSuffixDetailComponent } from './soldier-training-report-marine-suffix-detail.component';
import { SoldierTrainingReportMarineSuffixUpdateComponent } from './soldier-training-report-marine-suffix-update.component';
import { SoldierTrainingReportMarineSuffixDeletePopupComponent } from './soldier-training-report-marine-suffix-delete-dialog.component';
import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class SoldierTrainingReportMarineSuffixResolve implements Resolve<ISoldierTrainingReportMarineSuffix> {
    constructor(private service: SoldierTrainingReportMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SoldierTrainingReportMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SoldierTrainingReportMarineSuffix>) => response.ok),
                map((soldierTrainingReport: HttpResponse<SoldierTrainingReportMarineSuffix>) => soldierTrainingReport.body)
            );
        }
        return of(new SoldierTrainingReportMarineSuffix());
    }
}

export const soldierTrainingReportRoute: Routes = [
    {
        path: 'soldier-training-report-marine-suffix',
        component: SoldierTrainingReportMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.soldierTrainingReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-training-report-marine-suffix/:id/view',
        component: SoldierTrainingReportMarineSuffixDetailComponent,
        resolve: {
            soldierTrainingReport: SoldierTrainingReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierTrainingReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-training-report-marine-suffix/new',
        component: SoldierTrainingReportMarineSuffixUpdateComponent,
        resolve: {
            soldierTrainingReport: SoldierTrainingReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierTrainingReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-training-report-marine-suffix/:id/edit',
        component: SoldierTrainingReportMarineSuffixUpdateComponent,
        resolve: {
            soldierTrainingReport: SoldierTrainingReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierTrainingReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const soldierTrainingReportPopupRoute: Routes = [
    {
        path: 'soldier-training-report-marine-suffix/:id/delete',
        component: SoldierTrainingReportMarineSuffixDeletePopupComponent,
        resolve: {
            soldierTrainingReport: SoldierTrainingReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldierTrainingReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
