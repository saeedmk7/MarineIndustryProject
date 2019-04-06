import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';
import { ResourceMarineSuffixService } from './resource-marine-suffix.service';
import { ResourceMarineSuffixComponent } from './resource-marine-suffix.component';
import { ResourceMarineSuffixDetailComponent } from './resource-marine-suffix-detail.component';
import { ResourceMarineSuffixUpdateComponent } from './resource-marine-suffix-update.component';
import { ResourceMarineSuffixDeletePopupComponent } from './resource-marine-suffix-delete-dialog.component';
import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ResourceMarineSuffixResolve implements Resolve<IResourceMarineSuffix> {
    constructor(private service: ResourceMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((resource: HttpResponse<ResourceMarineSuffix>) => resource.body));
        }
        return of(new ResourceMarineSuffix());
    }
}

export const resourceRoute: Routes = [
    {
        path: 'resource-marine-suffix',
        component: ResourceMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.resource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-marine-suffix/:id/view',
        component: ResourceMarineSuffixDetailComponent,
        resolve: {
            resource: ResourceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.resource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-marine-suffix/new',
        component: ResourceMarineSuffixUpdateComponent,
        resolve: {
            resource: ResourceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.resource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-marine-suffix/:id/edit',
        component: ResourceMarineSuffixUpdateComponent,
        resolve: {
            resource: ResourceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.resource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resourcePopupRoute: Routes = [
    {
        path: 'resource-marine-suffix/:id/delete',
        component: ResourceMarineSuffixDeletePopupComponent,
        resolve: {
            resource: ResourceMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.resource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
