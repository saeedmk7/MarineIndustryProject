import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';
import { EducationalCenterCriteriaMarineSuffixService } from './educational-center-criteria-marine-suffix.service';
import { EducationalCenterCriteriaMarineSuffixComponent } from './educational-center-criteria-marine-suffix.component';
import { EducationalCenterCriteriaMarineSuffixDetailComponent } from './educational-center-criteria-marine-suffix-detail.component';
import { EducationalCenterCriteriaMarineSuffixUpdateComponent } from './educational-center-criteria-marine-suffix-update.component';
import { EducationalCenterCriteriaMarineSuffixDeletePopupComponent } from './educational-center-criteria-marine-suffix-delete-dialog.component';
import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalCenterCriteriaMarineSuffixResolve implements Resolve<IEducationalCenterCriteriaMarineSuffix> {
    constructor(private service: EducationalCenterCriteriaMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EducationalCenterCriteriaMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EducationalCenterCriteriaMarineSuffix>) => response.ok),
                map((educationalCenterCriteria: HttpResponse<EducationalCenterCriteriaMarineSuffix>) => educationalCenterCriteria.body)
            );
        }
        return of(new EducationalCenterCriteriaMarineSuffix());
    }
}

export const educationalCenterCriteriaRoute: Routes = [
    {
        path: 'educational-center-criteria-marine-suffix',
        component: EducationalCenterCriteriaMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalCenterCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-criteria-marine-suffix/:id/view',
        component: EducationalCenterCriteriaMarineSuffixDetailComponent,
        resolve: {
            educationalCenterCriteria: EducationalCenterCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-criteria-marine-suffix/new',
        component: EducationalCenterCriteriaMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterCriteria: EducationalCenterCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-criteria-marine-suffix/:id/edit',
        component: EducationalCenterCriteriaMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterCriteria: EducationalCenterCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalCenterCriteriaPopupRoute: Routes = [
    {
        path: 'educational-center-criteria-marine-suffix/:id/delete',
        component: EducationalCenterCriteriaMarineSuffixDeletePopupComponent,
        resolve: {
            educationalCenterCriteria: EducationalCenterCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterCriteria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
