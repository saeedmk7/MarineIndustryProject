import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from './final-niazsanji-report-marine-suffix.service';
import { FinalNiazsanjiReportMarineSuffixComponent } from './final-niazsanji-report-marine-suffix.component';
import { FinalNiazsanjiReportMarineSuffixDetailComponent } from './final-niazsanji-report-marine-suffix-detail.component';
import { FinalNiazsanjiReportMarineSuffixUpdateComponent } from './final-niazsanji-report-marine-suffix-update.component';
import { FinalNiazsanjiReportMarineSuffixDeletePopupComponent } from './final-niazsanji-report-marine-suffix-delete-dialog.component';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class FinalNiazsanjiReportMarineSuffixResolve implements Resolve<IFinalNiazsanjiReportMarineSuffix> {
    constructor(private service: FinalNiazsanjiReportMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((finalNiazsanjiReport: HttpResponse<FinalNiazsanjiReportMarineSuffix>) => finalNiazsanjiReport.body));
        }
        return of(new FinalNiazsanjiReportMarineSuffix());
    }
}

export const finalNiazsanjiReportRoute: Routes = [
    {
        path: 'final-niazsanji-report-marine-suffix',
        component: FinalNiazsanjiReportMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-marine-suffix/:id/view',
        component: FinalNiazsanjiReportMarineSuffixDetailComponent,
        resolve: {
            finalNiazsanjiReport: FinalNiazsanjiReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-marine-suffix/new',
        component: FinalNiazsanjiReportMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReport: FinalNiazsanjiReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-marine-suffix/:id/edit',
        component: FinalNiazsanjiReportMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReport: FinalNiazsanjiReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const finalNiazsanjiReportPopupRoute: Routes = [
    {
        path: 'final-niazsanji-report-marine-suffix/:id/delete',
        component: FinalNiazsanjiReportMarineSuffixDeletePopupComponent,
        resolve: {
            finalNiazsanjiReport: FinalNiazsanjiReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
