import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { FinalNiazsanjiReportPersonMarineSuffixService } from './final-niazsanji-report-person-marine-suffix.service';
import { FinalNiazsanjiReportPersonMarineSuffixComponent } from './final-niazsanji-report-person-marine-suffix.component';
import { FinalNiazsanjiReportPersonMarineSuffixDetailComponent } from './final-niazsanji-report-person-marine-suffix-detail.component';
import { FinalNiazsanjiReportPersonMarineSuffixUpdateComponent } from './final-niazsanji-report-person-marine-suffix-update.component';
import { FinalNiazsanjiReportPersonMarineSuffixDeletePopupComponent } from './final-niazsanji-report-person-marine-suffix-delete-dialog.component';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class FinalNiazsanjiReportPersonMarineSuffixResolve implements Resolve<IFinalNiazsanjiReportPersonMarineSuffix> {
    constructor(private service: FinalNiazsanjiReportPersonMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    map(
                        (finalNiazsanjiReportPerson: HttpResponse<FinalNiazsanjiReportPersonMarineSuffix>) =>
                            finalNiazsanjiReportPerson.body
                    )
                );
        }
        return of(new FinalNiazsanjiReportPersonMarineSuffix());
    }
}

export const finalNiazsanjiReportPersonRoute: Routes = [
    {
        path: 'final-niazsanji-report-person-marine-suffix',
        component: FinalNiazsanjiReportPersonMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReportPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-person-marine-suffix/:id/view',
        component: FinalNiazsanjiReportPersonMarineSuffixDetailComponent,
        resolve: {
            finalNiazsanjiReportPerson: FinalNiazsanjiReportPersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReportPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-person-marine-suffix/new',
        component: FinalNiazsanjiReportPersonMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReportPerson: FinalNiazsanjiReportPersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReportPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-niazsanji-report-person-marine-suffix/:id/edit',
        component: FinalNiazsanjiReportPersonMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReportPerson: FinalNiazsanjiReportPersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReportPerson.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const finalNiazsanjiReportPersonPopupRoute: Routes = [
    {
        path: 'final-niazsanji-report-person-marine-suffix/:id/delete',
        component: FinalNiazsanjiReportPersonMarineSuffixDeletePopupComponent,
        resolve: {
            finalNiazsanjiReportPerson: FinalNiazsanjiReportPersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReportPerson.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
