import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MediaProductTypeMarineSuffix } from 'app/shared/model/media-product-type-marine-suffix.model';
import { MediaProductTypeMarineSuffixService } from './media-product-type-marine-suffix.service';
import { MediaProductTypeMarineSuffixComponent } from './media-product-type-marine-suffix.component';
import { MediaProductTypeMarineSuffixDetailComponent } from './media-product-type-marine-suffix-detail.component';
import { MediaProductTypeMarineSuffixUpdateComponent } from './media-product-type-marine-suffix-update.component';
import { MediaProductTypeMarineSuffixDeletePopupComponent } from './media-product-type-marine-suffix-delete-dialog.component';
import { IMediaProductTypeMarineSuffix } from 'app/shared/model/media-product-type-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MediaProductTypeMarineSuffixResolve implements Resolve<IMediaProductTypeMarineSuffix> {
    constructor(private service: MediaProductTypeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MediaProductTypeMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MediaProductTypeMarineSuffix>) => response.ok),
                map((mediaProductType: HttpResponse<MediaProductTypeMarineSuffix>) => mediaProductType.body)
            );
        }
        return of(new MediaProductTypeMarineSuffix());
    }
}

export const mediaProductTypeRoute: Routes = [
    {
        path: 'media-product-type-marine-suffix',
        component: MediaProductTypeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.mediaProductType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'media-product-type-marine-suffix/:id/view',
        component: MediaProductTypeMarineSuffixDetailComponent,
        resolve: {
            mediaProductType: MediaProductTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaProductType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'media-product-type-marine-suffix/new',
        component: MediaProductTypeMarineSuffixUpdateComponent,
        resolve: {
            mediaProductType: MediaProductTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaProductType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'media-product-type-marine-suffix/:id/edit',
        component: MediaProductTypeMarineSuffixUpdateComponent,
        resolve: {
            mediaProductType: MediaProductTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaProductType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mediaProductTypePopupRoute: Routes = [
    {
        path: 'media-product-type-marine-suffix/:id/delete',
        component: MediaProductTypeMarineSuffixDeletePopupComponent,
        resolve: {
            mediaProductType: MediaProductTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaProductType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
