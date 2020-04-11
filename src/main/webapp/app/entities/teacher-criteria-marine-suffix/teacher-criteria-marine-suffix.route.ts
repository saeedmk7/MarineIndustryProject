import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';
import { TeacherCriteriaMarineSuffixService } from './teacher-criteria-marine-suffix.service';
import { TeacherCriteriaMarineSuffixComponent } from './teacher-criteria-marine-suffix.component';
import { TeacherCriteriaMarineSuffixDetailComponent } from './teacher-criteria-marine-suffix-detail.component';
import { TeacherCriteriaMarineSuffixUpdateComponent } from './teacher-criteria-marine-suffix-update.component';
import { TeacherCriteriaMarineSuffixDeletePopupComponent } from './teacher-criteria-marine-suffix-delete-dialog.component';
import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeacherCriteriaMarineSuffixResolve implements Resolve<ITeacherCriteriaMarineSuffix> {
    constructor(private service: TeacherCriteriaMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TeacherCriteriaMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TeacherCriteriaMarineSuffix>) => response.ok),
                map((teacherCriteria: HttpResponse<TeacherCriteriaMarineSuffix>) => teacherCriteria.body)
            );
        }
        return of(new TeacherCriteriaMarineSuffix());
    }
}

export const teacherCriteriaRoute: Routes = [
    {
        path: 'teacher-criteria-marine-suffix',
        component: TeacherCriteriaMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teacherCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-criteria-marine-suffix/:id/view',
        component: TeacherCriteriaMarineSuffixDetailComponent,
        resolve: {
            teacherCriteria: TeacherCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-criteria-marine-suffix/new',
        component: TeacherCriteriaMarineSuffixUpdateComponent,
        resolve: {
            teacherCriteria: TeacherCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-criteria-marine-suffix/:id/edit',
        component: TeacherCriteriaMarineSuffixUpdateComponent,
        resolve: {
            teacherCriteria: TeacherCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teacherCriteriaPopupRoute: Routes = [
    {
        path: 'teacher-criteria-marine-suffix/:id/delete',
        component: TeacherCriteriaMarineSuffixDeletePopupComponent,
        resolve: {
            teacherCriteria: TeacherCriteriaMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
