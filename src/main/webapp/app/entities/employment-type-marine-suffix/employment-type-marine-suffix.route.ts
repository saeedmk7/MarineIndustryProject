import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { EmploymentTypeMarineSuffixService } from './employment-type-marine-suffix.service';
import { EmploymentTypeMarineSuffixComponent } from './employment-type-marine-suffix.component';
import { EmploymentTypeMarineSuffixDetailComponent } from './employment-type-marine-suffix-detail.component';
import { EmploymentTypeMarineSuffixUpdateComponent } from './employment-type-marine-suffix-update.component';
import { EmploymentTypeMarineSuffixDeletePopupComponent } from './employment-type-marine-suffix-delete-dialog.component';
import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EmploymentTypeMarineSuffixResolve implements Resolve<IEmploymentTypeMarineSuffix> {
    constructor(private service: EmploymentTypeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((employmentType: HttpResponse<EmploymentTypeMarineSuffix>) => employmentType.body));
        }
        return of(new EmploymentTypeMarineSuffix());
    }
}

export const employmentTypeRoute: Routes = [
    {
        path: 'employment-type-marine-suffix',
        component: EmploymentTypeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.employmentType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employment-type-marine-suffix/:id/view',
        component: EmploymentTypeMarineSuffixDetailComponent,
        resolve: {
            employmentType: EmploymentTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.employmentType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employment-type-marine-suffix/new',
        component: EmploymentTypeMarineSuffixUpdateComponent,
        resolve: {
            employmentType: EmploymentTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.employmentType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employment-type-marine-suffix/:id/edit',
        component: EmploymentTypeMarineSuffixUpdateComponent,
        resolve: {
            employmentType: EmploymentTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.employmentType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const employmentTypePopupRoute: Routes = [
    {
        path: 'employment-type-marine-suffix/:id/delete',
        component: EmploymentTypeMarineSuffixDeletePopupComponent,
        resolve: {
            employmentType: EmploymentTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.employmentType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
