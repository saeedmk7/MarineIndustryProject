import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { WorkUnitMarineSuffix } from 'app/shared/model/work-unit-marine-suffix.model';
import { WorkUnitMarineSuffixService } from './work-unit-marine-suffix.service';
import { WorkUnitMarineSuffixComponent } from './work-unit-marine-suffix.component';
import { WorkUnitMarineSuffixDetailComponent } from './work-unit-marine-suffix-detail.component';
import { WorkUnitMarineSuffixUpdateComponent } from './work-unit-marine-suffix-update.component';
import { WorkUnitMarineSuffixDeletePopupComponent } from './work-unit-marine-suffix-delete-dialog.component';
import { IWorkUnitMarineSuffix } from 'app/shared/model/work-unit-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class WorkUnitMarineSuffixResolve implements Resolve<IWorkUnitMarineSuffix> {
    constructor(private service: WorkUnitMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((workUnit: HttpResponse<WorkUnitMarineSuffix>) => workUnit.body));
        }
        return of(new WorkUnitMarineSuffix());
    }
}

export const workUnitRoute: Routes = [
    {
        path: 'work-unit-marine-suffix',
        component: WorkUnitMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.workUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-unit-marine-suffix/:id/view',
        component: WorkUnitMarineSuffixDetailComponent,
        resolve: {
            workUnit: WorkUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-unit-marine-suffix/new',
        component: WorkUnitMarineSuffixUpdateComponent,
        resolve: {
            workUnit: WorkUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-unit-marine-suffix/:id/edit',
        component: WorkUnitMarineSuffixUpdateComponent,
        resolve: {
            workUnit: WorkUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workUnit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const workUnitPopupRoute: Routes = [
    {
        path: 'work-unit-marine-suffix/:id/delete',
        component: WorkUnitMarineSuffixDeletePopupComponent,
        resolve: {
            workUnit: WorkUnitMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workUnit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
