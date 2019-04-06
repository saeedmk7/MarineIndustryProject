import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { FieldOfStudyMarineSuffixService } from './field-of-study-marine-suffix.service';
import { FieldOfStudyMarineSuffixComponent } from './field-of-study-marine-suffix.component';
import { FieldOfStudyMarineSuffixDetailComponent } from './field-of-study-marine-suffix-detail.component';
import { FieldOfStudyMarineSuffixUpdateComponent } from './field-of-study-marine-suffix-update.component';
import { FieldOfStudyMarineSuffixDeletePopupComponent } from './field-of-study-marine-suffix-delete-dialog.component';
import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class FieldOfStudyMarineSuffixResolve implements Resolve<IFieldOfStudyMarineSuffix> {
    constructor(private service: FieldOfStudyMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((fieldOfStudy: HttpResponse<FieldOfStudyMarineSuffix>) => fieldOfStudy.body));
        }
        return of(new FieldOfStudyMarineSuffix());
    }
}

export const fieldOfStudyRoute: Routes = [
    {
        path: 'field-of-study-marine-suffix',
        component: FieldOfStudyMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.fieldOfStudy.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'field-of-study-marine-suffix/:id/view',
        component: FieldOfStudyMarineSuffixDetailComponent,
        resolve: {
            fieldOfStudy: FieldOfStudyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fieldOfStudy.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'field-of-study-marine-suffix/new',
        component: FieldOfStudyMarineSuffixUpdateComponent,
        resolve: {
            fieldOfStudy: FieldOfStudyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fieldOfStudy.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'field-of-study-marine-suffix/:id/edit',
        component: FieldOfStudyMarineSuffixUpdateComponent,
        resolve: {
            fieldOfStudy: FieldOfStudyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fieldOfStudy.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fieldOfStudyPopupRoute: Routes = [
    {
        path: 'field-of-study-marine-suffix/:id/delete',
        component: FieldOfStudyMarineSuffixDeletePopupComponent,
        resolve: {
            fieldOfStudy: FieldOfStudyMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.fieldOfStudy.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
