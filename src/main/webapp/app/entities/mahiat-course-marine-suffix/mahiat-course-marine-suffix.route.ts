import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';
import { MahiatCourseMarineSuffixService } from './mahiat-course-marine-suffix.service';
import { MahiatCourseMarineSuffixComponent } from './mahiat-course-marine-suffix.component';
import { MahiatCourseMarineSuffixDetailComponent } from './mahiat-course-marine-suffix-detail.component';
import { MahiatCourseMarineSuffixUpdateComponent } from './mahiat-course-marine-suffix-update.component';
import { MahiatCourseMarineSuffixDeletePopupComponent } from './mahiat-course-marine-suffix-delete-dialog.component';
import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MahiatCourseMarineSuffixResolve implements Resolve<IMahiatCourseMarineSuffix> {
    constructor(private service: MahiatCourseMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((mahiatCourse: HttpResponse<MahiatCourseMarineSuffix>) => mahiatCourse.body));
        }
        return of(new MahiatCourseMarineSuffix());
    }
}

export const mahiatCourseRoute: Routes = [
    {
        path: 'mahiat-course-marine-suffix',
        component: MahiatCourseMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.mahiatCourse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mahiat-course-marine-suffix/:id/view',
        component: MahiatCourseMarineSuffixDetailComponent,
        resolve: {
            mahiatCourse: MahiatCourseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mahiatCourse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mahiat-course-marine-suffix/new',
        component: MahiatCourseMarineSuffixUpdateComponent,
        resolve: {
            mahiatCourse: MahiatCourseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mahiatCourse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'mahiat-course-marine-suffix/:id/edit',
        component: MahiatCourseMarineSuffixUpdateComponent,
        resolve: {
            mahiatCourse: MahiatCourseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mahiatCourse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mahiatCoursePopupRoute: Routes = [
    {
        path: 'mahiat-course-marine-suffix/:id/delete',
        component: MahiatCourseMarineSuffixDeletePopupComponent,
        resolve: {
            mahiatCourse: MahiatCourseMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mahiatCourse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
