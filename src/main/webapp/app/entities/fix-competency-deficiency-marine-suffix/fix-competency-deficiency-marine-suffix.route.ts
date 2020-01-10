import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FixCompetencyDeficiencyMarineSuffix } from 'app/shared/model/fix-competency-deficiency-marine-suffix.model';
import { FixCompetencyDeficiencyMarineSuffixService } from './fix-competency-deficiency-marine-suffix.service';
import { FixCompetencyDeficiencyMarineSuffixComponent } from './fix-competency-deficiency-marine-suffix.component';
import { FixCompetencyDeficiencyMarineSuffixDetailComponent } from './fix-competency-deficiency-marine-suffix-detail.component';
import { FixCompetencyDeficiencyMarineSuffixUpdateComponent } from './fix-competency-deficiency-marine-suffix-update.component';
import { FixCompetencyDeficiencyMarineSuffixDeletePopupComponent } from './fix-competency-deficiency-marine-suffix-delete-dialog.component';
import { IFixCompetencyDeficiencyMarineSuffix } from 'app/shared/model/fix-competency-deficiency-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class FixCompetencyDeficiencyMarineSuffixResolve implements Resolve<IFixCompetencyDeficiencyMarineSuffix> {
    constructor(private service: FixCompetencyDeficiencyMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FixCompetencyDeficiencyMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FixCompetencyDeficiencyMarineSuffix>) => response.ok),
                map((fixCompetencyDeficiency: HttpResponse<FixCompetencyDeficiencyMarineSuffix>) => fixCompetencyDeficiency.body)
            );
        }
        return of(new FixCompetencyDeficiencyMarineSuffix());
    }
}

export const fixCompetencyDeficiencyRoute: Routes = [
    {
        path: 'fix-competency-deficiency-marine-suffix',
        component: FixCompetencyDeficiencyMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.fixCompetencyDeficiency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fix-competency-deficiency-marine-suffix/:id/view',
        component: FixCompetencyDeficiencyMarineSuffixDetailComponent,
        resolve: {
            fixCompetencyDeficiency: FixCompetencyDeficiencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fixCompetencyDeficiency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fix-competency-deficiency-marine-suffix/new',
        component: FixCompetencyDeficiencyMarineSuffixUpdateComponent,
        resolve: {
            fixCompetencyDeficiency: FixCompetencyDeficiencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fixCompetencyDeficiency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fix-competency-deficiency-marine-suffix/:id/edit',
        component: FixCompetencyDeficiencyMarineSuffixUpdateComponent,
        resolve: {
            fixCompetencyDeficiency: FixCompetencyDeficiencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fixCompetencyDeficiency.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fixCompetencyDeficiencyPopupRoute: Routes = [
    {
        path: 'fix-competency-deficiency-marine-suffix/:id/delete',
        component: FixCompetencyDeficiencyMarineSuffixDeletePopupComponent,
        resolve: {
            fixCompetencyDeficiency: FixCompetencyDeficiencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fixCompetencyDeficiency.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
