import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SubTaskMarineSuffix } from 'app/shared/model/sub-task-marine-suffix.model';
import { SubTaskMarineSuffixService } from './sub-task-marine-suffix.service';
import { SubTaskMarineSuffixComponent } from './sub-task-marine-suffix.component';
import { SubTaskMarineSuffixDetailComponent } from './sub-task-marine-suffix-detail.component';
import { SubTaskMarineSuffixUpdateComponent } from './sub-task-marine-suffix-update.component';
import { SubTaskMarineSuffixDeletePopupComponent } from './sub-task-marine-suffix-delete-dialog.component';
import { ISubTaskMarineSuffix } from 'app/shared/model/sub-task-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class SubTaskMarineSuffixResolve implements Resolve<ISubTaskMarineSuffix> {
    constructor(private service: SubTaskMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((subTask: HttpResponse<SubTaskMarineSuffix>) => subTask.body));
        }
        return of(new SubTaskMarineSuffix());
    }
}

export const subTaskRoute: Routes = [
    {
        path: 'sub-task-marine-suffix',
        component: SubTaskMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.subTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sub-task-marine-suffix/:id/view',
        component: SubTaskMarineSuffixDetailComponent,
        resolve: {
            subTask: SubTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.subTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sub-task-marine-suffix/new',
        component: SubTaskMarineSuffixUpdateComponent,
        resolve: {
            subTask: SubTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.subTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sub-task-marine-suffix/:id/edit',
        component: SubTaskMarineSuffixUpdateComponent,
        resolve: {
            subTask: SubTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.subTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const subTaskPopupRoute: Routes = [
    {
        path: 'sub-task-marine-suffix/:id/delete',
        component: SubTaskMarineSuffixDeletePopupComponent,
        resolve: {
            subTask: SubTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.subTask.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
