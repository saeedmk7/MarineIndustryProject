import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationalHistoryMarineSuffix } from 'app/shared/model/educational-history-marine-suffix.model';
import { EducationalHistoryMarineSuffixService } from './educational-history-marine-suffix.service';
import { EducationalHistoryMarineSuffixComponent } from './educational-history-marine-suffix.component';
import { EducationalHistoryMarineSuffixDetailComponent } from './educational-history-marine-suffix-detail.component';
import { EducationalHistoryMarineSuffixUpdateComponent } from './educational-history-marine-suffix-update.component';
import { EducationalHistoryMarineSuffixDeletePopupComponent } from './educational-history-marine-suffix-delete-dialog.component';
import { IEducationalHistoryMarineSuffix } from 'app/shared/model/educational-history-marine-suffix.model';
import {
    EducationalHistoryMarineSuffixCommentPopupComponent
} from "app/entities/educational-history-marine-suffix/educational-history-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class EducationalHistoryMarineSuffixResolve implements Resolve<IEducationalHistoryMarineSuffix> {
    constructor(private service: EducationalHistoryMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EducationalHistoryMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EducationalHistoryMarineSuffix>) => response.ok),
                map((educationalHistory: HttpResponse<EducationalHistoryMarineSuffix>) => educationalHistory.body)
            );
        }
        return of(new EducationalHistoryMarineSuffix());
    }
}

export const educationalHistoryRoute: Routes = [
    {
        path: 'educational-history-marine-suffix',
        component: EducationalHistoryMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-history-marine-suffix/:id/view',
        component: EducationalHistoryMarineSuffixDetailComponent,
        resolve: {
            educationalHistory: EducationalHistoryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-history-marine-suffix/new',
        component: EducationalHistoryMarineSuffixUpdateComponent,
        resolve: {
            educationalHistory: EducationalHistoryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-history-marine-suffix/:id/edit',
        component: EducationalHistoryMarineSuffixUpdateComponent,
        resolve: {
            educationalHistory: EducationalHistoryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalHistory.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalHistoryPopupRoute: Routes = [
    {
        path: 'educational-history-marine-suffix/:id/delete',
        component: EducationalHistoryMarineSuffixDeletePopupComponent,
        resolve: {
            educationalHistory: EducationalHistoryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalHistory.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'educational-history-marine-suffix/:id/:CommentType/comment',
        component: EducationalHistoryMarineSuffixCommentPopupComponent,
        resolve: {
            educationalHistory: EducationalHistoryMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalHistory.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
