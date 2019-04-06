import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';
import { AcademicRankMarineSuffixService } from './academic-rank-marine-suffix.service';
import { AcademicRankMarineSuffixComponent } from './academic-rank-marine-suffix.component';
import { AcademicRankMarineSuffixDetailComponent } from './academic-rank-marine-suffix-detail.component';
import { AcademicRankMarineSuffixUpdateComponent } from './academic-rank-marine-suffix-update.component';
import { AcademicRankMarineSuffixDeletePopupComponent } from './academic-rank-marine-suffix-delete-dialog.component';
import { IAcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class AcademicRankMarineSuffixResolve implements Resolve<IAcademicRankMarineSuffix> {
    constructor(private service: AcademicRankMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((academicRank: HttpResponse<AcademicRankMarineSuffix>) => academicRank.body));
        }
        return of(new AcademicRankMarineSuffix());
    }
}

export const academicRankRoute: Routes = [
    {
        path: 'academic-rank-marine-suffix',
        component: AcademicRankMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.academicRank.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'academic-rank-marine-suffix/:id/view',
        component: AcademicRankMarineSuffixDetailComponent,
        resolve: {
            academicRank: AcademicRankMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.academicRank.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'academic-rank-marine-suffix/new',
        component: AcademicRankMarineSuffixUpdateComponent,
        resolve: {
            academicRank: AcademicRankMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.academicRank.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'academic-rank-marine-suffix/:id/edit',
        component: AcademicRankMarineSuffixUpdateComponent,
        resolve: {
            academicRank: AcademicRankMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.academicRank.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const academicRankPopupRoute: Routes = [
    {
        path: 'academic-rank-marine-suffix/:id/delete',
        component: AcademicRankMarineSuffixDeletePopupComponent,
        resolve: {
            academicRank: AcademicRankMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.academicRank.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
