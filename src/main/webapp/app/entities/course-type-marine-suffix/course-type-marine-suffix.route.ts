import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from './course-type-marine-suffix.service';
import { CourseTypeMarineSuffixComponent } from './course-type-marine-suffix.component';
import { CourseTypeMarineSuffixDetailComponent } from './course-type-marine-suffix-detail.component';
import { CourseTypeMarineSuffixUpdateComponent } from './course-type-marine-suffix-update.component';
import { CourseTypeMarineSuffixDeletePopupComponent } from './course-type-marine-suffix-delete-dialog.component';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class CourseTypeMarineSuffixResolve implements Resolve<ICourseTypeMarineSuffix> {
    constructor(private service: CourseTypeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((courseType: HttpResponse<CourseTypeMarineSuffix>) => courseType.body));
        }
        return of(new CourseTypeMarineSuffix());
    }
}

export const courseTypeRoute: Routes = [
    {
        path: 'course-type-marine-suffix',
        component: CourseTypeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.courseType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'course-type-marine-suffix/:id/view',
        component: CourseTypeMarineSuffixDetailComponent,
        resolve: {
            courseType: CourseTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'course-type-marine-suffix/new',
        component: CourseTypeMarineSuffixUpdateComponent,
        resolve: {
            courseType: CourseTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'course-type-marine-suffix/:id/edit',
        component: CourseTypeMarineSuffixUpdateComponent,
        resolve: {
            courseType: CourseTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const courseTypePopupRoute: Routes = [
    {
        path: 'course-type-marine-suffix/:id/delete',
        component: CourseTypeMarineSuffixDeletePopupComponent,
        resolve: {
            courseType: CourseTypeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
