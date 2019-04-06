import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { EducationalCenterMarineSuffixService } from './educational-center-marine-suffix.service';
import { EducationalCenterMarineSuffixComponent } from './educational-center-marine-suffix.component';
import { EducationalCenterMarineSuffixDetailComponent } from './educational-center-marine-suffix-detail.component';
import { EducationalCenterMarineSuffixUpdateComponent } from './educational-center-marine-suffix-update.component';
import { EducationalCenterMarineSuffixDeletePopupComponent } from './educational-center-marine-suffix-delete-dialog.component';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalCenterMarineSuffixResolve implements Resolve<IEducationalCenterMarineSuffix> {
    constructor(private service: EducationalCenterMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((educationalCenter: HttpResponse<EducationalCenterMarineSuffix>) => educationalCenter.body));
        }
        return of(new EducationalCenterMarineSuffix());
    }
}

export const educationalCenterRoute: Routes = [
    {
        path: 'educational-center-marine-suffix',
        component: EducationalCenterMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalCenter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-marine-suffix/:id/view',
        component: EducationalCenterMarineSuffixDetailComponent,
        resolve: {
            educationalCenter: EducationalCenterMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-marine-suffix/new',
        component: EducationalCenterMarineSuffixUpdateComponent,
        resolve: {
            educationalCenter: EducationalCenterMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-marine-suffix/:id/edit',
        component: EducationalCenterMarineSuffixUpdateComponent,
        resolve: {
            educationalCenter: EducationalCenterMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalCenterPopupRoute: Routes = [
    {
        path: 'educational-center-marine-suffix/:id/delete',
        component: EducationalCenterMarineSuffixDeletePopupComponent,
        resolve: {
            educationalCenter: EducationalCenterMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
