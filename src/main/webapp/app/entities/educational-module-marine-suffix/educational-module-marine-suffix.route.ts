import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from './educational-module-marine-suffix.service';
import { EducationalModuleMarineSuffixComponent } from './educational-module-marine-suffix.component';
import { EducationalModuleMarineSuffixDetailComponent } from './educational-module-marine-suffix-detail.component';
import { EducationalModuleMarineSuffixUpdateComponent } from './educational-module-marine-suffix-update.component';
import { EducationalModuleMarineSuffixDeletePopupComponent } from './educational-module-marine-suffix-delete-dialog.component';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class EducationalModuleMarineSuffixResolve implements Resolve<IEducationalModuleMarineSuffix> {
    constructor(private service: EducationalModuleMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((educationalModule: HttpResponse<EducationalModuleMarineSuffix>) => educationalModule.body));
        }
        return of(new EducationalModuleMarineSuffix());
    }
}

export const educationalModuleRoute: Routes = [
    {
        path: 'educational-module-marine-suffix',
        component: EducationalModuleMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-module-marine-suffix/:id/view',
        component: EducationalModuleMarineSuffixDetailComponent,
        resolve: {
            educationalModule: EducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-module-marine-suffix/new',
        component: EducationalModuleMarineSuffixUpdateComponent,
        resolve: {
            educationalModule: EducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-module-marine-suffix/:id/edit',
        component: EducationalModuleMarineSuffixUpdateComponent,
        resolve: {
            educationalModule: EducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalModulePopupRoute: Routes = [
    {
        path: 'educational-module-marine-suffix/:id/delete',
        component: EducationalModuleMarineSuffixDeletePopupComponent,
        resolve: {
            educationalModule: EducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalModule.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
