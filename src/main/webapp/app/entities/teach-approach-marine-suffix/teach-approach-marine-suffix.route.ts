import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';
import { TeachApproachMarineSuffixService } from './teach-approach-marine-suffix.service';
import { TeachApproachMarineSuffixComponent } from './teach-approach-marine-suffix.component';
import { TeachApproachMarineSuffixDetailComponent } from './teach-approach-marine-suffix-detail.component';
import { TeachApproachMarineSuffixUpdateComponent } from './teach-approach-marine-suffix-update.component';
import { TeachApproachMarineSuffixDeletePopupComponent } from './teach-approach-marine-suffix-delete-dialog.component';
import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeachApproachMarineSuffixResolve implements Resolve<ITeachApproachMarineSuffix> {
    constructor(private service: TeachApproachMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((teachApproach: HttpResponse<TeachApproachMarineSuffix>) => teachApproach.body));
        }
        return of(new TeachApproachMarineSuffix());
    }
}

export const teachApproachRoute: Routes = [
    {
        path: 'teach-approach-marine-suffix',
        component: TeachApproachMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teachApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-approach-marine-suffix/:id/view',
        component: TeachApproachMarineSuffixDetailComponent,
        resolve: {
            teachApproach: TeachApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-approach-marine-suffix/new',
        component: TeachApproachMarineSuffixUpdateComponent,
        resolve: {
            teachApproach: TeachApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-approach-marine-suffix/:id/edit',
        component: TeachApproachMarineSuffixUpdateComponent,
        resolve: {
            teachApproach: TeachApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teachApproachPopupRoute: Routes = [
    {
        path: 'teach-approach-marine-suffix/:id/delete',
        component: TeachApproachMarineSuffixDeletePopupComponent,
        resolve: {
            teachApproach: TeachApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachApproach.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
