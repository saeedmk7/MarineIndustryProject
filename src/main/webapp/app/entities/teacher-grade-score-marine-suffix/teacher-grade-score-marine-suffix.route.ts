import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';
import { TeacherGradeScoreMarineSuffixService } from './teacher-grade-score-marine-suffix.service';
import { TeacherGradeScoreMarineSuffixComponent } from './teacher-grade-score-marine-suffix.component';
import { TeacherGradeScoreMarineSuffixDetailComponent } from './teacher-grade-score-marine-suffix-detail.component';
import { TeacherGradeScoreMarineSuffixUpdateComponent } from './teacher-grade-score-marine-suffix-update.component';
import { TeacherGradeScoreMarineSuffixDeletePopupComponent } from './teacher-grade-score-marine-suffix-delete-dialog.component';
import { ITeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeacherGradeScoreMarineSuffixResolve implements Resolve<ITeacherGradeScoreMarineSuffix> {
    constructor(private service: TeacherGradeScoreMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TeacherGradeScoreMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TeacherGradeScoreMarineSuffix>) => response.ok),
                map((teacherGradeScore: HttpResponse<TeacherGradeScoreMarineSuffix>) => teacherGradeScore.body)
            );
        }
        return of(new TeacherGradeScoreMarineSuffix());
    }
}

export const teacherGradeScoreRoute: Routes = [
    {
        path: 'teacher-grade-score-marine-suffix',
        component: TeacherGradeScoreMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teacherGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-grade-score-marine-suffix/:id/view',
        component: TeacherGradeScoreMarineSuffixDetailComponent,
        resolve: {
            teacherGradeScore: TeacherGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-grade-score-marine-suffix/new',
        component: TeacherGradeScoreMarineSuffixUpdateComponent,
        resolve: {
            teacherGradeScore: TeacherGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-grade-score-marine-suffix/:id/edit',
        component: TeacherGradeScoreMarineSuffixUpdateComponent,
        resolve: {
            teacherGradeScore: TeacherGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teacherGradeScorePopupRoute: Routes = [
    {
        path: 'teacher-grade-score-marine-suffix/:id/delete',
        component: TeacherGradeScoreMarineSuffixDeletePopupComponent,
        resolve: {
            teacherGradeScore: TeacherGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
