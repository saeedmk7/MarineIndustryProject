import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { WorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';
import { WorkIndustryMarineSuffixService } from './work-industry-marine-suffix.service';
import { WorkIndustryMarineSuffixComponent } from './work-industry-marine-suffix.component';
import { WorkIndustryMarineSuffixDetailComponent } from './work-industry-marine-suffix-detail.component';
import { WorkIndustryMarineSuffixUpdateComponent } from './work-industry-marine-suffix-update.component';
import { WorkIndustryMarineSuffixDeletePopupComponent } from './work-industry-marine-suffix-delete-dialog.component';
import { IWorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class WorkIndustryMarineSuffixResolve implements Resolve<IWorkIndustryMarineSuffix> {
    constructor(private service: WorkIndustryMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((workIndustry: HttpResponse<WorkIndustryMarineSuffix>) => workIndustry.body));
        }
        return of(new WorkIndustryMarineSuffix());
    }
}

export const workIndustryRoute: Routes = [
    {
        path: 'work-industry-marine-suffix',
        component: WorkIndustryMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.workIndustry.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-industry-marine-suffix/:id/view',
        component: WorkIndustryMarineSuffixDetailComponent,
        resolve: {
            workIndustry: WorkIndustryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workIndustry.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-industry-marine-suffix/new',
        component: WorkIndustryMarineSuffixUpdateComponent,
        resolve: {
            workIndustry: WorkIndustryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workIndustry.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'work-industry-marine-suffix/:id/edit',
        component: WorkIndustryMarineSuffixUpdateComponent,
        resolve: {
            workIndustry: WorkIndustryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workIndustry.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const workIndustryPopupRoute: Routes = [
    {
        path: 'work-industry-marine-suffix/:id/delete',
        component: WorkIndustryMarineSuffixDeletePopupComponent,
        resolve: {
            workIndustry: WorkIndustryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.workIndustry.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
