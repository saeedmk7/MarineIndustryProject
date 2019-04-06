import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { WorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';
import { WorkGroupMarineSuffixService } from './work-group-marine-suffix.service';
import { WorkGroupMarineSuffixComponent } from './work-group-marine-suffix.component';
import { WorkGroupMarineSuffixDetailComponent } from './work-group-marine-suffix-detail.component';
import { WorkGroupMarineSuffixUpdateComponent } from './work-group-marine-suffix-update.component';
import { WorkGroupMarineSuffixDeletePopupComponent } from './work-group-marine-suffix-delete-dialog.component';
import { IWorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class WorkGroupMarineSuffixResolve implements Resolve<IWorkGroupMarineSuffix> {
    constructor(private service: WorkGroupMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((workGroup: HttpResponse<WorkGroupMarineSuffix>) => workGroup.body));
        }
        return of(new WorkGroupMarineSuffix());
    }
}

export const workGroupRoute: Routes = [
    {
        path: 'work-group-marine-suffix',
        component: WorkGroupMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.workGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-group-marine-suffix/:id/view',
        component: WorkGroupMarineSuffixDetailComponent,
        resolve: {
            workGroup: WorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-group-marine-suffix/new',
        component: WorkGroupMarineSuffixUpdateComponent,
        resolve: {
            workGroup: WorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-group-marine-suffix/:id/edit',
        component: WorkGroupMarineSuffixUpdateComponent,
        resolve: {
            workGroup: WorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const workGroupPopupRoute: Routes = [
    {
        path: 'work-group-marine-suffix/:id/delete',
        component: WorkGroupMarineSuffixDeletePopupComponent,
        resolve: {
            workGroup: WorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
