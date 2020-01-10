import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';
import { CompetencyMarineSuffixService } from './competency-marine-suffix.service';
import { CompetencyMarineSuffixComponent } from './competency-marine-suffix.component';
import { CompetencyMarineSuffixDetailComponent } from './competency-marine-suffix-detail.component';
import { CompetencyMarineSuffixUpdateComponent } from './competency-marine-suffix-update.component';
import { CompetencyMarineSuffixDeletePopupComponent } from './competency-marine-suffix-delete-dialog.component';
import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class CompetencyMarineSuffixResolve implements Resolve<ICompetencyMarineSuffix> {
    constructor(private service: CompetencyMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CompetencyMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CompetencyMarineSuffix>) => response.ok),
                map((competency: HttpResponse<CompetencyMarineSuffix>) => competency.body)
            );
        }
        return of(new CompetencyMarineSuffix());
    }
}

export const competencyRoute: Routes = [
    {
        path: 'competency-marine-suffix',
        component: CompetencyMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.competency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'competency-marine-suffix/:id/view',
        component: CompetencyMarineSuffixDetailComponent,
        resolve: {
            competency: CompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.competency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'competency-marine-suffix/new',
        component: CompetencyMarineSuffixUpdateComponent,
        resolve: {
            competency: CompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.competency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'competency-marine-suffix/:id/edit',
        component: CompetencyMarineSuffixUpdateComponent,
        resolve: {
            competency: CompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.competency.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const competencyPopupRoute: Routes = [
    {
        path: 'competency-marine-suffix/:id/delete',
        component: CompetencyMarineSuffixDeletePopupComponent,
        resolve: {
            competency: CompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.competency.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
