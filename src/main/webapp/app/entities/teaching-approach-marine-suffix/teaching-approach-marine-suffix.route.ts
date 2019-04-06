import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from './teaching-approach-marine-suffix.service';
import { TeachingApproachMarineSuffixComponent } from './teaching-approach-marine-suffix.component';
import { TeachingApproachMarineSuffixDetailComponent } from './teaching-approach-marine-suffix-detail.component';
import { TeachingApproachMarineSuffixUpdateComponent } from './teaching-approach-marine-suffix-update.component';
import { TeachingApproachMarineSuffixDeletePopupComponent } from './teaching-approach-marine-suffix-delete-dialog.component';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeachingApproachMarineSuffixResolve implements Resolve<ITeachingApproachMarineSuffix> {
    constructor(private service: TeachingApproachMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((teachingApproach: HttpResponse<TeachingApproachMarineSuffix>) => teachingApproach.body));
        }
        return of(new TeachingApproachMarineSuffix());
    }
}

export const teachingApproachRoute: Routes = [
    {
        path: 'teaching-approach-marine-suffix',
        component: TeachingApproachMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teachingApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teaching-approach-marine-suffix/:id/view',
        component: TeachingApproachMarineSuffixDetailComponent,
        resolve: {
            teachingApproach: TeachingApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teaching-approach-marine-suffix/new',
        component: TeachingApproachMarineSuffixUpdateComponent,
        resolve: {
            teachingApproach: TeachingApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teaching-approach-marine-suffix/:id/edit',
        component: TeachingApproachMarineSuffixUpdateComponent,
        resolve: {
            teachingApproach: TeachingApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingApproach.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teachingApproachPopupRoute: Routes = [
    {
        path: 'teaching-approach-marine-suffix/:id/delete',
        component: TeachingApproachMarineSuffixDeletePopupComponent,
        resolve: {
            teachingApproach: TeachingApproachMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingApproach.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
