import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestEducationalModuleMarineSuffixService } from './request-educational-module-marine-suffix.service';
import { RequestEducationalModuleMarineSuffixComponent } from './request-educational-module-marine-suffix.component';
import { RequestEducationalModuleMarineSuffixDetailComponent } from './request-educational-module-marine-suffix-detail.component';
import { RequestEducationalModuleMarineSuffixUpdateComponent } from './request-educational-module-marine-suffix-update.component';
import { RequestEducationalModuleMarineSuffixDeletePopupComponent } from './request-educational-module-marine-suffix-delete-dialog.component';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class RequestEducationalModuleMarineSuffixResolve implements Resolve<IRequestEducationalModuleMarineSuffix> {
    constructor(private service: RequestEducationalModuleMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((requestEducationalModule: HttpResponse<RequestEducationalModuleMarineSuffix>) => requestEducationalModule.body));
        }
        return of(new RequestEducationalModuleMarineSuffix());
    }
}

export const requestEducationalModuleRoute: Routes = [
    {
        path: 'request-educational-module-marine-suffix',
        component: RequestEducationalModuleMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.requestEducationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-educational-module-marine-suffix/:id/view',
        component: RequestEducationalModuleMarineSuffixDetailComponent,
        resolve: {
            requestEducationalModule: RequestEducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestEducationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-educational-module-marine-suffix/new',
        component: RequestEducationalModuleMarineSuffixUpdateComponent,
        resolve: {
            requestEducationalModule: RequestEducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestEducationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'request-educational-module-marine-suffix/:id/edit',
        component: RequestEducationalModuleMarineSuffixUpdateComponent,
        resolve: {
            requestEducationalModule: RequestEducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestEducationalModule.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const requestEducationalModulePopupRoute: Routes = [
    {
        path: 'request-educational-module-marine-suffix/:id/delete',
        component: RequestEducationalModuleMarineSuffixDeletePopupComponent,
        resolve: {
            requestEducationalModule: RequestEducationalModuleMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.requestEducationalModule.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
