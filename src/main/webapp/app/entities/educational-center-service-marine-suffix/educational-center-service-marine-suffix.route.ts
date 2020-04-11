import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationalCenterServiceMarineSuffix } from 'app/shared/model/educational-center-service-marine-suffix.model';
import { EducationalCenterServiceMarineSuffixService } from './educational-center-service-marine-suffix.service';
import { EducationalCenterServiceMarineSuffixComponent } from './educational-center-service-marine-suffix.component';
import { EducationalCenterServiceMarineSuffixDetailComponent } from './educational-center-service-marine-suffix-detail.component';
import { EducationalCenterServiceMarineSuffixUpdateComponent } from './educational-center-service-marine-suffix-update.component';
import { EducationalCenterServiceMarineSuffixDeletePopupComponent } from './educational-center-service-marine-suffix-delete-dialog.component';
import { IEducationalCenterServiceMarineSuffix } from 'app/shared/model/educational-center-service-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalCenterServiceMarineSuffixResolve implements Resolve<IEducationalCenterServiceMarineSuffix> {
    constructor(private service: EducationalCenterServiceMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EducationalCenterServiceMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EducationalCenterServiceMarineSuffix>) => response.ok),
                map((educationalCenterService: HttpResponse<EducationalCenterServiceMarineSuffix>) => educationalCenterService.body)
            );
        }
        return of(new EducationalCenterServiceMarineSuffix());
    }
}

export const educationalCenterServiceRoute: Routes = [
    {
        path: 'educational-center-service-marine-suffix',
        component: EducationalCenterServiceMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalCenterService.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-service-marine-suffix/:id/view',
        component: EducationalCenterServiceMarineSuffixDetailComponent,
        resolve: {
            educationalCenterService: EducationalCenterServiceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterService.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-service-marine-suffix/new',
        component: EducationalCenterServiceMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterService: EducationalCenterServiceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterService.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-service-marine-suffix/:id/edit',
        component: EducationalCenterServiceMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterService: EducationalCenterServiceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterService.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalCenterServicePopupRoute: Routes = [
    {
        path: 'educational-center-service-marine-suffix/:id/delete',
        component: EducationalCenterServiceMarineSuffixDeletePopupComponent,
        resolve: {
            educationalCenterService: EducationalCenterServiceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterService.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
