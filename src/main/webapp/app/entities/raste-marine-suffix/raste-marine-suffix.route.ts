import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RasteMarineSuffix } from 'app/shared/model/raste-marine-suffix.model';
import { RasteMarineSuffixService } from './raste-marine-suffix.service';
import { RasteMarineSuffixComponent } from './raste-marine-suffix.component';
import { RasteMarineSuffixDetailComponent } from './raste-marine-suffix-detail.component';
import { RasteMarineSuffixUpdateComponent } from './raste-marine-suffix-update.component';
import { RasteMarineSuffixDeletePopupComponent } from './raste-marine-suffix-delete-dialog.component';
import { IRasteMarineSuffix } from 'app/shared/model/raste-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class RasteMarineSuffixResolve implements Resolve<IRasteMarineSuffix> {
    constructor(private service: RasteMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((raste: HttpResponse<RasteMarineSuffix>) => raste.body));
        }
        return of(new RasteMarineSuffix());
    }
}

export const rasteRoute: Routes = [
    {
        path: 'raste-marine-suffix',
        component: RasteMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.raste.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'raste-marine-suffix/:id/view',
        component: RasteMarineSuffixDetailComponent,
        resolve: {
            raste: RasteMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.raste.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'raste-marine-suffix/new',
        component: RasteMarineSuffixUpdateComponent,
        resolve: {
            raste: RasteMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.raste.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'raste-marine-suffix/:id/edit',
        component: RasteMarineSuffixUpdateComponent,
        resolve: {
            raste: RasteMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.raste.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rastePopupRoute: Routes = [
    {
        path: 'raste-marine-suffix/:id/delete',
        component: RasteMarineSuffixDeletePopupComponent,
        resolve: {
            raste: RasteMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.raste.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
