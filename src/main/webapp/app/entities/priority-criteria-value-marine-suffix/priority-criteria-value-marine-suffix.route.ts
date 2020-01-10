import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PriorityCriteriaValueMarineSuffix } from 'app/shared/model/priority-criteria-value-marine-suffix.model';
import { PriorityCriteriaValueMarineSuffixService } from './priority-criteria-value-marine-suffix.service';
import { PriorityCriteriaValueMarineSuffixComponent } from './priority-criteria-value-marine-suffix.component';
import { PriorityCriteriaValueMarineSuffixDetailComponent } from './priority-criteria-value-marine-suffix-detail.component';
import { PriorityCriteriaValueMarineSuffixUpdateComponent } from './priority-criteria-value-marine-suffix-update.component';
import { PriorityCriteriaValueMarineSuffixDeletePopupComponent } from './priority-criteria-value-marine-suffix-delete-dialog.component';
import { IPriorityCriteriaValueMarineSuffix } from 'app/shared/model/priority-criteria-value-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PriorityCriteriaValueMarineSuffixResolve implements Resolve<IPriorityCriteriaValueMarineSuffix> {
    constructor(private service: PriorityCriteriaValueMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PriorityCriteriaValueMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PriorityCriteriaValueMarineSuffix>) => response.ok),
                map((priorityCriteriaValue: HttpResponse<PriorityCriteriaValueMarineSuffix>) => priorityCriteriaValue.body)
            );
        }
        return of(new PriorityCriteriaValueMarineSuffix());
    }
}

export const priorityCriteriaValueRoute: Routes = [
    {
        path: 'priority-criteria-value-marine-suffix',
        component: PriorityCriteriaValueMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.priorityCriteriaValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'priority-criteria-value-marine-suffix/:id/view',
        component: PriorityCriteriaValueMarineSuffixDetailComponent,
        resolve: {
            priorityCriteriaValue: PriorityCriteriaValueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteriaValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'priority-criteria-value-marine-suffix/new',
        component: PriorityCriteriaValueMarineSuffixUpdateComponent,
        resolve: {
            priorityCriteriaValue: PriorityCriteriaValueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteriaValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'priority-criteria-value-marine-suffix/:id/edit',
        component: PriorityCriteriaValueMarineSuffixUpdateComponent,
        resolve: {
            priorityCriteriaValue: PriorityCriteriaValueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteriaValue.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const priorityCriteriaValuePopupRoute: Routes = [
    {
        path: 'priority-criteria-value-marine-suffix/:id/delete',
        component: PriorityCriteriaValueMarineSuffixDeletePopupComponent,
        resolve: {
            priorityCriteriaValue: PriorityCriteriaValueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteriaValue.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
