import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { TeacherMarineSuffixService } from './teacher-marine-suffix.service';
import { TeacherMarineSuffixComponent } from './teacher-marine-suffix.component';
import { TeacherMarineSuffixDetailComponent } from './teacher-marine-suffix-detail.component';
import { TeacherMarineSuffixUpdateComponent } from './teacher-marine-suffix-update.component';
import { TeacherMarineSuffixDeletePopupComponent } from './teacher-marine-suffix-delete-dialog.component';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeacherMarineSuffixResolve implements Resolve<ITeacherMarineSuffix> {
    constructor(private service: TeacherMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((teacher: HttpResponse<TeacherMarineSuffix>) => teacher.body));
        }
        return of(new TeacherMarineSuffix());
    }
}

export const teacherRoute: Routes = [
    {
        path: 'teacher-marine-suffix',
        component: TeacherMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teacher.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-marine-suffix/:id/view',
        component: TeacherMarineSuffixDetailComponent,
        resolve: {
            teacher: TeacherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacher.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-marine-suffix/new',
        component: TeacherMarineSuffixUpdateComponent,
        resolve: {
            teacher: TeacherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacher.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-marine-suffix/:id/edit',
        component: TeacherMarineSuffixUpdateComponent,
        resolve: {
            teacher: TeacherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacher.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teacherPopupRoute: Routes = [
    {
        path: 'teacher-marine-suffix/:id/delete',
        component: TeacherMarineSuffixDeletePopupComponent,
        resolve: {
            teacher: TeacherMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacher.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
