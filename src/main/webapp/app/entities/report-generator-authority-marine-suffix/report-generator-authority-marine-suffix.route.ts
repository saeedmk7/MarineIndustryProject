import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ReportGeneratorAuthorityMarineSuffix } from 'app/shared/model/report-generator-authority-marine-suffix.model';
import { ReportGeneratorAuthorityMarineSuffixService } from './report-generator-authority-marine-suffix.service';
import { ReportGeneratorAuthorityMarineSuffixComponent } from './report-generator-authority-marine-suffix.component';
import { ReportGeneratorAuthorityMarineSuffixDetailComponent } from './report-generator-authority-marine-suffix-detail.component';
import { ReportGeneratorAuthorityMarineSuffixUpdateComponent } from './report-generator-authority-marine-suffix-update.component';
import { ReportGeneratorAuthorityMarineSuffixDeletePopupComponent } from './report-generator-authority-marine-suffix-delete-dialog.component';
import { IReportGeneratorAuthorityMarineSuffix } from 'app/shared/model/report-generator-authority-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ReportGeneratorAuthorityMarineSuffixResolve implements Resolve<IReportGeneratorAuthorityMarineSuffix> {
    constructor(private service: ReportGeneratorAuthorityMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ReportGeneratorAuthorityMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<ReportGeneratorAuthorityMarineSuffix>) => response.ok),
                    map((reportGeneratorAuthority: HttpResponse<ReportGeneratorAuthorityMarineSuffix>) => reportGeneratorAuthority.body)
                );
        }
        return of(new ReportGeneratorAuthorityMarineSuffix());
    }
}

export const reportGeneratorAuthorityRoute: Routes = [
    {
        path: 'report-generator-authority-marine-suffix',
        component: ReportGeneratorAuthorityMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.reportGeneratorAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'report-generator-authority-marine-suffix/:id/view',
        component: ReportGeneratorAuthorityMarineSuffixDetailComponent,
        resolve: {
            reportGeneratorAuthority: ReportGeneratorAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGeneratorAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'report-generator-authority-marine-suffix/new',
        component: ReportGeneratorAuthorityMarineSuffixUpdateComponent,
        resolve: {
            reportGeneratorAuthority: ReportGeneratorAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGeneratorAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'report-generator-authority-marine-suffix/:id/edit',
        component: ReportGeneratorAuthorityMarineSuffixUpdateComponent,
        resolve: {
            reportGeneratorAuthority: ReportGeneratorAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGeneratorAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const reportGeneratorAuthorityPopupRoute: Routes = [
    {
        path: 'report-generator-authority-marine-suffix/:id/delete',
        component: ReportGeneratorAuthorityMarineSuffixDeletePopupComponent,
        resolve: {
            reportGeneratorAuthority: ReportGeneratorAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.reportGeneratorAuthority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
