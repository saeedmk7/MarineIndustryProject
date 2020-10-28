import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';
import { EducationalCenterGroupMarineSuffixService } from './educational-center-group-marine-suffix.service';
import { EducationalCenterGroupMarineSuffixComponent } from './educational-center-group-marine-suffix.component';
import { EducationalCenterGroupMarineSuffixDetailComponent } from './educational-center-group-marine-suffix-detail.component';
import { EducationalCenterGroupMarineSuffixUpdateComponent } from './educational-center-group-marine-suffix-update.component';
import { EducationalCenterGroupMarineSuffixDeletePopupComponent } from './educational-center-group-marine-suffix-delete-dialog.component';
import { IEducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalCenterGroupMarineSuffixResolve implements Resolve<IEducationalCenterGroupMarineSuffix> {
    constructor(private service: EducationalCenterGroupMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EducationalCenterGroupMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<EducationalCenterGroupMarineSuffix>) => response.ok),
                    map((educationalCenterGroup: HttpResponse<EducationalCenterGroupMarineSuffix>) => educationalCenterGroup.body)
                );
        }
        return of(new EducationalCenterGroupMarineSuffix());
    }
}

export const educationalCenterGroupRoute: Routes = [
    {
        path: 'educational-center-group-marine-suffix',
        component: EducationalCenterGroupMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalCenterGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-group-marine-suffix/:id/view',
        component: EducationalCenterGroupMarineSuffixDetailComponent,
        resolve: {
            educationalCenterGroup: EducationalCenterGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-group-marine-suffix/new',
        component: EducationalCenterGroupMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterGroup: EducationalCenterGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-center-group-marine-suffix/:id/edit',
        component: EducationalCenterGroupMarineSuffixUpdateComponent,
        resolve: {
            educationalCenterGroup: EducationalCenterGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalCenterGroupPopupRoute: Routes = [
    {
        path: 'educational-center-group-marine-suffix/:id/delete',
        component: EducationalCenterGroupMarineSuffixDeletePopupComponent,
        resolve: {
            educationalCenterGroup: EducationalCenterGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalCenterGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
