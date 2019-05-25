import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PlanningMarineSuffixComponent } from './planning-marine-suffix.component';
import { PlanningMarineSuffixDetailComponent } from './planning-marine-suffix-detail.component';
import { PlanningMarineSuffixUpdateComponent } from './planning-marine-suffix-update.component';
import {
    FinalNiazsanjiReportMarineSuffix,
    IFinalNiazsanjiReportMarineSuffix
} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {PlanningMarineSuffixDeletePopupComponent} from "app/entities/planning-marine-suffix/planning-marine-suffix-delete-dialog.component";


@Injectable({ providedIn: 'root' })
export class PlanningMarineSuffixResolve implements Resolve<IFinalNiazsanjiReportMarineSuffix> {
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

export const planningRoute: Routes = [
    {
        path: 'planning-marine-suffix',
        component: PlanningMarineSuffixComponent,
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
        path: 'planning-marine-suffix/:id/view',
        component: PlanningMarineSuffixDetailComponent,
        resolve: {
            finalNiazsanjiReport: PlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planning-marine-suffix/new',
        component: PlanningMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReport: PlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planning-marine-suffix/:id/edit',
        component: PlanningMarineSuffixUpdateComponent,
        resolve: {
            finalNiazsanjiReport: PlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planningPopupRoute: Routes = [
    {
        path: 'planning-marine-suffix/:id/delete',
        component: PlanningMarineSuffixDeletePopupComponent,
        resolve: {
            finalNiazsanjiReport: PlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalNiazsanjiReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
