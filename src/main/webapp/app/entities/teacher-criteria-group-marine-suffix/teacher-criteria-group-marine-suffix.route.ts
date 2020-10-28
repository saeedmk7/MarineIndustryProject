import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';
import { TeacherCriteriaGroupMarineSuffixService } from './teacher-criteria-group-marine-suffix.service';
import { TeacherCriteriaGroupMarineSuffixComponent } from './teacher-criteria-group-marine-suffix.component';
import { TeacherCriteriaGroupMarineSuffixDetailComponent } from './teacher-criteria-group-marine-suffix-detail.component';
import { TeacherCriteriaGroupMarineSuffixUpdateComponent } from './teacher-criteria-group-marine-suffix-update.component';
import { TeacherCriteriaGroupMarineSuffixDeletePopupComponent } from './teacher-criteria-group-marine-suffix-delete-dialog.component';
import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeacherCriteriaGroupMarineSuffixResolve implements Resolve<ITeacherCriteriaGroupMarineSuffix> {
    constructor(private service: TeacherCriteriaGroupMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TeacherCriteriaGroupMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<TeacherCriteriaGroupMarineSuffix>) => response.ok),
                    map((teacherCriteriaGroup: HttpResponse<TeacherCriteriaGroupMarineSuffix>) => teacherCriteriaGroup.body)
                );
        }
        return of(new TeacherCriteriaGroupMarineSuffix());
    }
}

export const teacherCriteriaGroupRoute: Routes = [
    {
        path: 'teacher-criteria-group-marine-suffix',
        component: TeacherCriteriaGroupMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teacherCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-criteria-group-marine-suffix/:id/view',
        component: TeacherCriteriaGroupMarineSuffixDetailComponent,
        resolve: {
            teacherCriteriaGroup: TeacherCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-criteria-group-marine-suffix/new',
        component: TeacherCriteriaGroupMarineSuffixUpdateComponent,
        resolve: {
            teacherCriteriaGroup: TeacherCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teacher-criteria-group-marine-suffix/:id/edit',
        component: TeacherCriteriaGroupMarineSuffixUpdateComponent,
        resolve: {
            teacherCriteriaGroup: TeacherCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teacherCriteriaGroupPopupRoute: Routes = [
    {
        path: 'teacher-criteria-group-marine-suffix/:id/delete',
        component: TeacherCriteriaGroupMarineSuffixDeletePopupComponent,
        resolve: {
            teacherCriteriaGroup: TeacherCriteriaGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teacherCriteriaGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
