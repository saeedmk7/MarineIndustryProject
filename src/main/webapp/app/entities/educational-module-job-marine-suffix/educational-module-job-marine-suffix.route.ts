import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EducationalModuleJobMarineSuffix } from 'app/shared/model/educational-module-job-marine-suffix.model';
import { EducationalModuleJobMarineSuffixService } from './educational-module-job-marine-suffix.service';
import { EducationalModuleJobMarineSuffixComponent } from './educational-module-job-marine-suffix.component';
import { EducationalModuleJobMarineSuffixDetailComponent } from './educational-module-job-marine-suffix-detail.component';
import { EducationalModuleJobMarineSuffixUpdateComponent } from './educational-module-job-marine-suffix-update.component';
import { EducationalModuleJobMarineSuffixDeletePopupComponent } from './educational-module-job-marine-suffix-delete-dialog.component';
import { IEducationalModuleJobMarineSuffix } from 'app/shared/model/educational-module-job-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalModuleJobMarineSuffixResolve implements Resolve<IEducationalModuleJobMarineSuffix> {
    constructor(private service: EducationalModuleJobMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((educationalModuleJob: HttpResponse<EducationalModuleJobMarineSuffix>) => educationalModuleJob.body));
        }
        return of(new EducationalModuleJobMarineSuffix());
    }
}

export const educationalModuleJobRoute: Routes = [
    {
        path: 'educational-module-job-marine-suffix',
        component: EducationalModuleJobMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalModuleJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-module-job-marine-suffix/:id/view',
        component: EducationalModuleJobMarineSuffixDetailComponent,
        resolve: {
            educationalModuleJob: EducationalModuleJobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModuleJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-module-job-marine-suffix/new',
        component: EducationalModuleJobMarineSuffixUpdateComponent,
        resolve: {
            educationalModuleJob: EducationalModuleJobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModuleJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-module-job-marine-suffix/:id/edit',
        component: EducationalModuleJobMarineSuffixUpdateComponent,
        resolve: {
            educationalModuleJob: EducationalModuleJobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModuleJob.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalModuleJobPopupRoute: Routes = [
    {
        path: 'educational-module-job-marine-suffix/:id/delete',
        component: EducationalModuleJobMarineSuffixDeletePopupComponent,
        resolve: {
            educationalModuleJob: EducationalModuleJobMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModuleJob.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
