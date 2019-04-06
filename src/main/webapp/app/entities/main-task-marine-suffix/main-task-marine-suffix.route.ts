import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';
import { MainTaskMarineSuffixService } from './main-task-marine-suffix.service';
import { MainTaskMarineSuffixComponent } from './main-task-marine-suffix.component';
import { MainTaskMarineSuffixDetailComponent } from './main-task-marine-suffix-detail.component';
import { MainTaskMarineSuffixUpdateComponent } from './main-task-marine-suffix-update.component';
import { MainTaskMarineSuffixDeletePopupComponent } from './main-task-marine-suffix-delete-dialog.component';
import { IMainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MainTaskMarineSuffixResolve implements Resolve<IMainTaskMarineSuffix> {
    constructor(private service: MainTaskMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((mainTask: HttpResponse<MainTaskMarineSuffix>) => mainTask.body));
        }
        return of(new MainTaskMarineSuffix());
    }
}

export const mainTaskRoute: Routes = [
    {
        path: 'main-task-marine-suffix',
        component: MainTaskMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.mainTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'main-task-marine-suffix/:id/view',
        component: MainTaskMarineSuffixDetailComponent,
        resolve: {
            mainTask: MainTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mainTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'main-task-marine-suffix/new',
        component: MainTaskMarineSuffixUpdateComponent,
        resolve: {
            mainTask: MainTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mainTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'main-task-marine-suffix/:id/edit',
        component: MainTaskMarineSuffixUpdateComponent,
        resolve: {
            mainTask: MainTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mainTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mainTaskPopupRoute: Routes = [
    {
        path: 'main-task-marine-suffix/:id/delete',
        component: MainTaskMarineSuffixDeletePopupComponent,
        resolve: {
            mainTask: MainTaskMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mainTask.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
