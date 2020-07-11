import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ReportGeneratorMarineSuffix } from 'app/shared/model/report-generator-marine-suffix.model';
import { ReportGeneratorMarineSuffixService } from './report-generator-marine-suffix.service';
import { ReportGeneratorMarineSuffixComponent } from './report-generator-marine-suffix.component';
import { ReportGeneratorMarineSuffixDetailComponent } from './report-generator-marine-suffix-detail.component';
import { ReportGeneratorMarineSuffixUpdateComponent } from './report-generator-marine-suffix-update.component';
import { ReportGeneratorMarineSuffixDeletePopupComponent } from './report-generator-marine-suffix-delete-dialog.component';
import { IReportGeneratorMarineSuffix } from 'app/shared/model/report-generator-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ReportGeneratorMarineSuffixResolve implements Resolve<IReportGeneratorMarineSuffix> {
    constructor(private service: ReportGeneratorMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ReportGeneratorMarineSuffix> {
        const id = route.params['guid'] ? route.params['guid'] : null;
        if (id) {
            return this.service
                .findByGuid(id)
                .pipe(
                    filter((response: HttpResponse<ReportGeneratorMarineSuffix>) => response.ok),
                    map((reportGenerator: HttpResponse<ReportGeneratorMarineSuffix>) => reportGenerator.body)
                );
        }
        return of(new ReportGeneratorMarineSuffix());
    }
}

export const reportGeneratorRoute: Routes = [
    {
        path: 'report-generator-marine-suffix',
        component: ReportGeneratorMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.reportGenerator.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'report-generator-marine-suffix/view/:guid',
        component: ReportGeneratorMarineSuffixDetailComponent,
        resolve: {
            reportGenerator: ReportGeneratorMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGenerator.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'report-generator-marine-suffix/new',
        component: ReportGeneratorMarineSuffixUpdateComponent,
        resolve: {
            reportGenerator: ReportGeneratorMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGenerator.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'report-generator-marine-suffix/:guid/edit',
        component: ReportGeneratorMarineSuffixUpdateComponent,
        resolve: {
            reportGenerator: ReportGeneratorMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGenerator.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const reportGeneratorPopupRoute: Routes = [
    {
        path: 'report-generator-marine-suffix/:guid/delete',
        component: ReportGeneratorMarineSuffixDeletePopupComponent,
        resolve: {
            reportGenerator: ReportGeneratorMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGenerator.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
