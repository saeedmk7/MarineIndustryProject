import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';
import { PreJobNiazsanjiCompetencyMarineSuffixService } from './pre-job-niazsanji-competency-marine-suffix.service';
import { PreJobNiazsanjiCompetencyMarineSuffixComponent } from './pre-job-niazsanji-competency-marine-suffix.component';
import { PreJobNiazsanjiCompetencyMarineSuffixDetailComponent } from './pre-job-niazsanji-competency-marine-suffix-detail.component';
import { PreJobNiazsanjiCompetencyMarineSuffixUpdateComponent } from './pre-job-niazsanji-competency-marine-suffix-update.component';
import { PreJobNiazsanjiCompetencyMarineSuffixDeletePopupComponent } from './pre-job-niazsanji-competency-marine-suffix-delete-dialog.component';
import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PreJobNiazsanjiCompetencyMarineSuffixResolve implements Resolve<IPreJobNiazsanjiCompetencyMarineSuffix> {
    constructor(private service: PreJobNiazsanjiCompetencyMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PreJobNiazsanjiCompetencyMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PreJobNiazsanjiCompetencyMarineSuffix>) => response.ok),
                map((preJobNiazsanjiCompetency: HttpResponse<PreJobNiazsanjiCompetencyMarineSuffix>) => preJobNiazsanjiCompetency.body)
            );
        }
        return of(new PreJobNiazsanjiCompetencyMarineSuffix());
    }
}

export const preJobNiazsanjiCompetencyRoute: Routes = [
    {
        path: 'pre-job-niazsanji-competency-marine-suffix',
        component: PreJobNiazsanjiCompetencyMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.preJobNiazsanjiCompetency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pre-job-niazsanji-competency-marine-suffix/:id/view',
        component: PreJobNiazsanjiCompetencyMarineSuffixDetailComponent,
        resolve: {
            preJobNiazsanjiCompetency: PreJobNiazsanjiCompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanjiCompetency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pre-job-niazsanji-competency-marine-suffix/new',
        component: PreJobNiazsanjiCompetencyMarineSuffixUpdateComponent,
        resolve: {
            preJobNiazsanjiCompetency: PreJobNiazsanjiCompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanjiCompetency.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pre-job-niazsanji-competency-marine-suffix/:id/edit',
        component: PreJobNiazsanjiCompetencyMarineSuffixUpdateComponent,
        resolve: {
            preJobNiazsanjiCompetency: PreJobNiazsanjiCompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanjiCompetency.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const preJobNiazsanjiCompetencyPopupRoute: Routes = [
    {
        path: 'pre-job-niazsanji-competency-marine-suffix/:id/delete',
        component: PreJobNiazsanjiCompetencyMarineSuffixDeletePopupComponent,
        resolve: {
            preJobNiazsanjiCompetency: PreJobNiazsanjiCompetencyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanjiCompetency.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
