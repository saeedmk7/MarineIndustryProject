import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';
import { AssessmentMethodMarineSuffixService } from './assessment-method-marine-suffix.service';
import { AssessmentMethodMarineSuffixComponent } from './assessment-method-marine-suffix.component';
import { AssessmentMethodMarineSuffixDetailComponent } from './assessment-method-marine-suffix-detail.component';
import { AssessmentMethodMarineSuffixUpdateComponent } from './assessment-method-marine-suffix-update.component';
import { AssessmentMethodMarineSuffixDeletePopupComponent } from './assessment-method-marine-suffix-delete-dialog.component';
import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class AssessmentMethodMarineSuffixResolve implements Resolve<IAssessmentMethodMarineSuffix> {
    constructor(private service: AssessmentMethodMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AssessmentMethodMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AssessmentMethodMarineSuffix>) => response.ok),
                map((assessmentMethod: HttpResponse<AssessmentMethodMarineSuffix>) => assessmentMethod.body)
            );
        }
        return of(new AssessmentMethodMarineSuffix());
    }
}

export const assessmentMethodRoute: Routes = [
    {
        path: 'assessment-method-marine-suffix',
        component: AssessmentMethodMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.assessmentMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'assessment-method-marine-suffix/:id/view',
        component: AssessmentMethodMarineSuffixDetailComponent,
        resolve: {
            assessmentMethod: AssessmentMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.assessmentMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'assessment-method-marine-suffix/new',
        component: AssessmentMethodMarineSuffixUpdateComponent,
        resolve: {
            assessmentMethod: AssessmentMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.assessmentMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'assessment-method-marine-suffix/:id/edit',
        component: AssessmentMethodMarineSuffixUpdateComponent,
        resolve: {
            assessmentMethod: AssessmentMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.assessmentMethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const assessmentMethodPopupRoute: Routes = [
    {
        path: 'assessment-method-marine-suffix/:id/delete',
        component: AssessmentMethodMarineSuffixDeletePopupComponent,
        resolve: {
            assessmentMethod: AssessmentMethodMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.assessmentMethod.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
