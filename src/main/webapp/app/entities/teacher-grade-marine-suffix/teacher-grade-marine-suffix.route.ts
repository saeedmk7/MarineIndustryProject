import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';
import { TeacherGradeMarineSuffixService } from './teacher-grade-marine-suffix.service';
import { TeacherGradeMarineSuffixComponent } from './teacher-grade-marine-suffix.component';
import { TeacherGradeMarineSuffixDetailComponent } from './teacher-grade-marine-suffix-detail.component';
import { TeacherGradeMarineSuffixUpdateComponent } from './teacher-grade-marine-suffix-update.component';
import { TeacherGradeMarineSuffixDeletePopupComponent } from './teacher-grade-marine-suffix-delete-dialog.component';
import { ITeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeacherGradeMarineSuffixResolve implements Resolve<ITeacherGradeMarineSuffix> {
    constructor(private service: TeacherGradeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TeacherGradeMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TeacherGradeMarineSuffix>) => response.ok),
                map((teacherGrade: HttpResponse<TeacherGradeMarineSuffix>) => teacherGrade.body)
            );
        }
        return of(new TeacherGradeMarineSuffix());
    }
}

export const teacherGradeRoute: Routes = [
    {
        path: 'teacher-grade-marine-suffix',
        component: TeacherGradeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teacherGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-grade-marine-suffix/:id/view',
        component: TeacherGradeMarineSuffixDetailComponent,
        resolve: {
            teacherGrade: TeacherGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-grade-marine-suffix/new',
        component: TeacherGradeMarineSuffixUpdateComponent,
        resolve: {
            teacherGrade: TeacherGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-grade-marine-suffix/:id/edit',
        component: TeacherGradeMarineSuffixUpdateComponent,
        resolve: {
            teacherGrade: TeacherGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teacherGradePopupRoute: Routes = [
    {
        path: 'teacher-grade-marine-suffix/:id/delete',
        component: TeacherGradeMarineSuffixDeletePopupComponent,
        resolve: {
            teacherGrade: TeacherGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGrade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
