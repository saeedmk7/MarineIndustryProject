import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OrganizationChartAuthorityMarineSuffix } from 'app/shared/model/organization-chart-authority-marine-suffix.model';
import { OrganizationChartAuthorityMarineSuffixService } from './organization-chart-authority-marine-suffix.service';
import { OrganizationChartAuthorityMarineSuffixComponent } from './organization-chart-authority-marine-suffix.component';
import { OrganizationChartAuthorityMarineSuffixDetailComponent } from './organization-chart-authority-marine-suffix-detail.component';
import { OrganizationChartAuthorityMarineSuffixUpdateComponent } from './organization-chart-authority-marine-suffix-update.component';
import { OrganizationChartAuthorityMarineSuffixDeletePopupComponent } from './organization-chart-authority-marine-suffix-delete-dialog.component';
import { IOrganizationChartAuthorityMarineSuffix } from 'app/shared/model/organization-chart-authority-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class OrganizationChartAuthorityMarineSuffixResolve implements Resolve<IOrganizationChartAuthorityMarineSuffix> {
    constructor(private service: OrganizationChartAuthorityMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    map(
                        (organizationChartAuthority: HttpResponse<OrganizationChartAuthorityMarineSuffix>) =>
                            organizationChartAuthority.body
                    )
                );
        }
        return of(new OrganizationChartAuthorityMarineSuffix());
    }
}

export const organizationChartAuthorityRoute: Routes = [
    {
        path: 'organization-chart-authority-marine-suffix',
        component: OrganizationChartAuthorityMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.organizationChartAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-chart-authority-marine-suffix/:id/view',
        component: OrganizationChartAuthorityMarineSuffixDetailComponent,
        resolve: {
            organizationChartAuthority: OrganizationChartAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChartAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-chart-authority-marine-suffix/new',
        component: OrganizationChartAuthorityMarineSuffixUpdateComponent,
        resolve: {
            organizationChartAuthority: OrganizationChartAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChartAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-chart-authority-marine-suffix/:id/edit',
        component: OrganizationChartAuthorityMarineSuffixUpdateComponent,
        resolve: {
            organizationChartAuthority: OrganizationChartAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChartAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const organizationChartAuthorityPopupRoute: Routes = [
    {
        path: 'organization-chart-authority-marine-suffix/:id/delete',
        component: OrganizationChartAuthorityMarineSuffixDeletePopupComponent,
        resolve: {
            organizationChartAuthority: OrganizationChartAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChartAuthority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
