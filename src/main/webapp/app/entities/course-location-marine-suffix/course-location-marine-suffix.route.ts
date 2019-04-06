import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CourseLocationMarineSuffix } from 'app/shared/model/course-location-marine-suffix.model';
import { CourseLocationMarineSuffixService } from './course-location-marine-suffix.service';
import { CourseLocationMarineSuffixComponent } from './course-location-marine-suffix.component';
import { CourseLocationMarineSuffixDetailComponent } from './course-location-marine-suffix-detail.component';
import { CourseLocationMarineSuffixUpdateComponent } from './course-location-marine-suffix-update.component';
import { CourseLocationMarineSuffixDeletePopupComponent } from './course-location-marine-suffix-delete-dialog.component';
import { ICourseLocationMarineSuffix } from 'app/shared/model/course-location-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class CourseLocationMarineSuffixResolve implements Resolve<ICourseLocationMarineSuffix> {
    constructor(private service: CourseLocationMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((courseLocation: HttpResponse<CourseLocationMarineSuffix>) => courseLocation.body));
        }
        return of(new CourseLocationMarineSuffix());
    }
}

export const courseLocationRoute: Routes = [
    {
        path: 'course-location-marine-suffix',
        component: CourseLocationMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.courseLocation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'course-location-marine-suffix/:id/view',
        component: CourseLocationMarineSuffixDetailComponent,
        resolve: {
            courseLocation: CourseLocationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseLocation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'course-location-marine-suffix/new',
        component: CourseLocationMarineSuffixUpdateComponent,
        resolve: {
            courseLocation: CourseLocationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseLocation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'course-location-marine-suffix/:id/edit',
        component: CourseLocationMarineSuffixUpdateComponent,
        resolve: {
            courseLocation: CourseLocationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseLocation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const courseLocationPopupRoute: Routes = [
    {
        path: 'course-location-marine-suffix/:id/delete',
        component: CourseLocationMarineSuffixDeletePopupComponent,
        resolve: {
            courseLocation: CourseLocationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.courseLocation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
