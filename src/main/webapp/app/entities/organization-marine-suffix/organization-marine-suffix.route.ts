import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';
import { OrganizationMarineSuffixService } from './organization-marine-suffix.service';
import { OrganizationMarineSuffixComponent } from './organization-marine-suffix.component';
import { OrganizationMarineSuffixDetailComponent } from './organization-marine-suffix-detail.component';
import { OrganizationMarineSuffixUpdateComponent } from './organization-marine-suffix-update.component';
import { OrganizationMarineSuffixDeletePopupComponent } from './organization-marine-suffix-delete-dialog.component';
import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class OrganizationMarineSuffixResolve implements Resolve<IOrganizationMarineSuffix> {
    constructor(private service: OrganizationMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((organization: HttpResponse<OrganizationMarineSuffix>) => organization.body));
        }
        return of(new OrganizationMarineSuffix());
    }
}

export const organizationRoute: Routes = [
    {
        path: 'organization-marine-suffix',
        component: OrganizationMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.organization.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-marine-suffix/:id/view',
        component: OrganizationMarineSuffixDetailComponent,
        resolve: {
            organization: OrganizationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organization.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-marine-suffix/new',
        component: OrganizationMarineSuffixUpdateComponent,
        resolve: {
            organization: OrganizationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organization.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-marine-suffix/:id/edit',
        component: OrganizationMarineSuffixUpdateComponent,
        resolve: {
            organization: OrganizationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organization.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const organizationPopupRoute: Routes = [
    {
        path: 'organization-marine-suffix/:id/delete',
        component: OrganizationMarineSuffixDeletePopupComponent,
        resolve: {
            organization: OrganizationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organization.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
