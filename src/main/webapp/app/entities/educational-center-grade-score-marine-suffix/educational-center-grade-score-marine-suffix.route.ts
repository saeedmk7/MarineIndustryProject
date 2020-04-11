import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationalCenterGradeScoreMarineSuffix } from 'app/shared/model/educational-center-grade-score-marine-suffix.model';
import { EducationalCenterGradeScoreMarineSuffixService } from './educational-center-grade-score-marine-suffix.service';
import { EducationalCenterGradeScoreMarineSuffixComponent } from './educational-center-grade-score-marine-suffix.component';
import { EducationalCenterGradeScoreMarineSuffixDetailComponent } from './educational-center-grade-score-marine-suffix-detail.component';
import { EducationalCenterGradeScoreMarineSuffixUpdateComponent } from './educational-center-grade-score-marine-suffix-update.component';
import { EducationalCenterGradeScoreMarineSuffixDeletePopupComponent } from './educational-center-grade-score-marine-suffix-delete-dialog.component';
import { IEducationalCenterGradeScoreMarineSuffix } from 'app/shared/model/educational-center-grade-score-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalCenterGradeScoreMarineSuffixResolve implements Resolve<IEducationalCenterGradeScoreMarineSuffix> {
    constructor(private service: EducationalCenterGradeScoreMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EducationalCenterGradeScoreMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EducationalCenterGradeScoreMarineSuffix>) => response.ok),
                map(
                    (educationalCenterGradeScore: HttpResponse<EducationalCenterGradeScoreMarineSuffix>) => educationalCenterGradeScore.body
                )
            );
        }
        return of(new EducationalCenterGradeScoreMarineSuffix());
    }
}

export const educationalCenterGradeScoreRoute: Routes = [
    {
        path: 'educational-center-grade-score-marine-suffix',
        component: EducationalCenterGradeScoreMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalCenterGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-grade-score-marine-suffix/:id/view',
        component: EducationalCenterGradeScoreMarineSuffixDetailComponent,
        resolve: {
            educationalCenterGradeScore: EducationalCenterGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-grade-score-marine-suffix/new',
        component: EducationalCenterGradeScoreMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterGradeScore: EducationalCenterGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-grade-score-marine-suffix/:id/edit',
        component: EducationalCenterGradeScoreMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterGradeScore: EducationalCenterGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalCenterGradeScorePopupRoute: Routes = [
    {
        path: 'educational-center-grade-score-marine-suffix/:id/delete',
        component: EducationalCenterGradeScoreMarineSuffixDeletePopupComponent,
        resolve: {
            educationalCenterGradeScore: EducationalCenterGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
