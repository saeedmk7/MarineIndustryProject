import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';
import { ServiceUnitMarineSuffixService } from './service-unit-marine-suffix.service';
import { ServiceUnitMarineSuffixComponent } from './service-unit-marine-suffix.component';
import { ServiceUnitMarineSuffixDetailComponent } from './service-unit-marine-suffix-detail.component';
import { ServiceUnitMarineSuffixUpdateComponent } from './service-unit-marine-suffix-update.component';
import { ServiceUnitMarineSuffixDeletePopupComponent } from './service-unit-marine-suffix-delete-dialog.component';
import { IServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ServiceUnitMarineSuffixResolve implements Resolve<IServiceUnitMarineSuffix> {
    constructor(private service: ServiceUnitMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((serviceUnit: HttpResponse<ServiceUnitMarineSuffix>) => serviceUnit.body));
        }
        return of(new ServiceUnitMarineSuffix());
    }
}

export const serviceUnitRoute: Routes = [
    {
        path: 'service-unit-marine-suffix',
        component: ServiceUnitMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.serviceUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-unit-marine-suffix/:id/view',
        component: ServiceUnitMarineSuffixDetailComponent,
        resolve: {
            serviceUnit: ServiceUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.serviceUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-unit-marine-suffix/new',
        component: ServiceUnitMarineSuffixUpdateComponent,
        resolve: {
            serviceUnit: ServiceUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.serviceUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'service-unit-marine-suffix/:id/edit',
        component: ServiceUnitMarineSuffixUpdateComponent,
        resolve: {
            serviceUnit: ServiceUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.serviceUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const serviceUnitPopupRoute: Routes = [
    {
        path: 'service-unit-marine-suffix/:id/delete',
        component: ServiceUnitMarineSuffixDeletePopupComponent,
        resolve: {
            serviceUnit: ServiceUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.serviceUnit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
