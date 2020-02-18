import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HeadlineMarineSuffix } from 'app/shared/model/headline-marine-suffix.model';
import { HeadlineMarineSuffixService } from './headline-marine-suffix.service';
import { HeadlineMarineSuffixComponent } from './headline-marine-suffix.component';
import { HeadlineMarineSuffixDetailComponent } from './headline-marine-suffix-detail.component';
import { HeadlineMarineSuffixUpdateComponent } from './headline-marine-suffix-update.component';
import { HeadlineMarineSuffixDeletePopupComponent } from './headline-marine-suffix-delete-dialog.component';
import { IHeadlineMarineSuffix } from 'app/shared/model/headline-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class HeadlineMarineSuffixResolve implements Resolve<IHeadlineMarineSuffix> {
    constructor(private service: HeadlineMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<HeadlineMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HeadlineMarineSuffix>) => response.ok),
                map((headline: HttpResponse<HeadlineMarineSuffix>) => headline.body)
            );
        }
        return of(new HeadlineMarineSuffix());
    }
}

export const headlineRoute: Routes = [
    {
        path: 'headline-marine-suffix',
        component: HeadlineMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.headline.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'headline-marine-suffix/:id/view',
        component: HeadlineMarineSuffixDetailComponent,
        resolve: {
            headline: HeadlineMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.headline.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'headline-marine-suffix/new',
        component: HeadlineMarineSuffixUpdateComponent,
        resolve: {
            headline: HeadlineMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.headline.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'headline-marine-suffix/:id/edit',
        component: HeadlineMarineSuffixUpdateComponent,
        resolve: {
            headline: HeadlineMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.headline.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const headlinePopupRoute: Routes = [
    {
        path: 'headline-marine-suffix/:id/delete',
        component: HeadlineMarineSuffixDeletePopupComponent,
        resolve: {
            headline: HeadlineMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.headline.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
