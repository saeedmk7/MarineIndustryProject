import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PriorityCriteriaMarineSuffix } from 'app/shared/model/priority-criteria-marine-suffix.model';
import { PriorityCriteriaMarineSuffixService } from './priority-criteria-marine-suffix.service';
import { PriorityCriteriaMarineSuffixComponent } from './priority-criteria-marine-suffix.component';
import { PriorityCriteriaMarineSuffixDetailComponent } from './priority-criteria-marine-suffix-detail.component';
import { PriorityCriteriaMarineSuffixUpdateComponent } from './priority-criteria-marine-suffix-update.component';
import { PriorityCriteriaMarineSuffixDeletePopupComponent } from './priority-criteria-marine-suffix-delete-dialog.component';
import { IPriorityCriteriaMarineSuffix } from 'app/shared/model/priority-criteria-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PriorityCriteriaMarineSuffixResolve implements Resolve<IPriorityCriteriaMarineSuffix> {
    constructor(private service: PriorityCriteriaMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PriorityCriteriaMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PriorityCriteriaMarineSuffix>) => response.ok),
                map((priorityCriteria: HttpResponse<PriorityCriteriaMarineSuffix>) => priorityCriteria.body)
            );
        }
        return of(new PriorityCriteriaMarineSuffix());
    }
}

export const priorityCriteriaRoute: Routes = [
    {
        path: 'priority-criteria-marine-suffix',
        component: PriorityCriteriaMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.priorityCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'priority-criteria-marine-suffix/:id/view',
        component: PriorityCriteriaMarineSuffixDetailComponent,
        resolve: {
            priorityCriteria: PriorityCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'priority-criteria-marine-suffix/new',
        component: PriorityCriteriaMarineSuffixUpdateComponent,
        resolve: {
            priorityCriteria: PriorityCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'priority-criteria-marine-suffix/:id/edit',
        component: PriorityCriteriaMarineSuffixUpdateComponent,
        resolve: {
            priorityCriteria: PriorityCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const priorityCriteriaPopupRoute: Routes = [
    {
        path: 'priority-criteria-marine-suffix/:id/delete',
        component: PriorityCriteriaMarineSuffixDeletePopupComponent,
        resolve: {
            priorityCriteria: PriorityCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.priorityCriteria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
