import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TeachTypeMarineSuffix } from 'app/shared/model/teach-type-marine-suffix.model';
import { TeachTypeMarineSuffixService } from './teach-type-marine-suffix.service';
import { TeachTypeMarineSuffixComponent } from './teach-type-marine-suffix.component';
import { TeachTypeMarineSuffixDetailComponent } from './teach-type-marine-suffix-detail.component';
import { TeachTypeMarineSuffixUpdateComponent } from './teach-type-marine-suffix-update.component';
import { TeachTypeMarineSuffixDeletePopupComponent } from './teach-type-marine-suffix-delete-dialog.component';
import { ITeachTypeMarineSuffix } from 'app/shared/model/teach-type-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeachTypeMarineSuffixResolve implements Resolve<ITeachTypeMarineSuffix> {
    constructor(private service: TeachTypeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((teachType: HttpResponse<TeachTypeMarineSuffix>) => teachType.body));
        }
        return of(new TeachTypeMarineSuffix());
    }
}

export const teachTypeRoute: Routes = [
    {
        path: 'teach-type-marine-suffix',
        component: TeachTypeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teachType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-type-marine-suffix/:id/view',
        component: TeachTypeMarineSuffixDetailComponent,
        resolve: {
            teachType: TeachTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-type-marine-suffix/new',
        component: TeachTypeMarineSuffixUpdateComponent,
        resolve: {
            teachType: TeachTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-type-marine-suffix/:id/edit',
        component: TeachTypeMarineSuffixUpdateComponent,
        resolve: {
            teachType: TeachTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teachTypePopupRoute: Routes = [
    {
        path: 'teach-type-marine-suffix/:id/delete',
        component: TeachTypeMarineSuffixDeletePopupComponent,
        resolve: {
            teachType: TeachTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
