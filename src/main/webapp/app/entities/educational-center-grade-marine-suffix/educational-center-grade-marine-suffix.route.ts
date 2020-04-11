import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';
import { EducationalCenterGradeMarineSuffixService } from './educational-center-grade-marine-suffix.service';
import { EducationalCenterGradeMarineSuffixComponent } from './educational-center-grade-marine-suffix.component';
import { EducationalCenterGradeMarineSuffixDetailComponent } from './educational-center-grade-marine-suffix-detail.component';
import { EducationalCenterGradeMarineSuffixUpdateComponent } from './educational-center-grade-marine-suffix-update.component';
import { EducationalCenterGradeMarineSuffixDeletePopupComponent } from './educational-center-grade-marine-suffix-delete-dialog.component';
import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalCenterGradeMarineSuffixResolve implements Resolve<IEducationalCenterGradeMarineSuffix> {
    constructor(private service: EducationalCenterGradeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EducationalCenterGradeMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EducationalCenterGradeMarineSuffix>) => response.ok),
                map((educationalCenterGrade: HttpResponse<EducationalCenterGradeMarineSuffix>) => educationalCenterGrade.body)
            );
        }
        return of(new EducationalCenterGradeMarineSuffix());
    }
}

export const educationalCenterGradeRoute: Routes = [
    {
        path: 'educational-center-grade-marine-suffix',
        component: EducationalCenterGradeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalCenterGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-grade-marine-suffix/:id/view',
        component: EducationalCenterGradeMarineSuffixDetailComponent,
        resolve: {
            educationalCenterGrade: EducationalCenterGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-grade-marine-suffix/new',
        component: EducationalCenterGradeMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterGrade: EducationalCenterGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-grade-marine-suffix/:id/edit',
        component: EducationalCenterGradeMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterGrade: EducationalCenterGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalCenterGradePopupRoute: Routes = [
    {
        path: 'educational-center-grade-marine-suffix/:id/delete',
        component: EducationalCenterGradeMarineSuffixDeletePopupComponent,
        resolve: {
            educationalCenterGrade: EducationalCenterGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGrade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
