import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from './organization-chart-marine-suffix.service';
import { OrganizationChartMarineSuffixComponent } from './organization-chart-marine-suffix.component';
import { OrganizationChartMarineSuffixDetailComponent } from './organization-chart-marine-suffix-detail.component';
import { OrganizationChartMarineSuffixUpdateComponent } from './organization-chart-marine-suffix-update.component';
import { OrganizationChartMarineSuffixDeletePopupComponent } from './organization-chart-marine-suffix-delete-dialog.component';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class OrganizationChartMarineSuffixResolve implements Resolve<IOrganizationChartMarineSuffix> {
    constructor(private service: OrganizationChartMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((organizationChart: HttpResponse<OrganizationChartMarineSuffix>) => organizationChart.body));
        }
        return of(new OrganizationChartMarineSuffix());
    }
}

export const organizationChartRoute: Routes = [
    {
        path: 'organization-chart-marine-suffix',
        component: OrganizationChartMarineSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChart.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-chart-marine-suffix/:id/view',
        component: OrganizationChartMarineSuffixDetailComponent,
        resolve: {
            organizationChart: OrganizationChartMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChart.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-chart-marine-suffix/:parentId/new',
        component: OrganizationChartMarineSuffixUpdateComponent,
        resolve: {
            organizationChart: OrganizationChartMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChart.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'organization-chart-marine-suffix/:id/edit',
        component: OrganizationChartMarineSuffixUpdateComponent,
        resolve: {
            organizationChart: OrganizationChartMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChart.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const organizationChartPopupRoute: Routes = [
    {
        path: 'organization-chart-marine-suffix/:id/delete',
        component: OrganizationChartMarineSuffixDeletePopupComponent,
        resolve: {
            organizationChart: OrganizationChartMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.organizationChart.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
