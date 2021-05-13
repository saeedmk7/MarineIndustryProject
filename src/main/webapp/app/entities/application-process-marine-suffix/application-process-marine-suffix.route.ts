import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApplicationProcessMarineSuffix } from 'app/shared/model/application-process-marine-suffix.model';
import { ApplicationProcessMarineSuffixService } from './application-process-marine-suffix.service';
import { ApplicationProcessMarineSuffixComponent } from './application-process-marine-suffix.component';
import { ApplicationProcessMarineSuffixDetailComponent } from './application-process-marine-suffix-detail.component';
import { ApplicationProcessMarineSuffixUpdateComponent } from './application-process-marine-suffix-update.component';
import { ApplicationProcessMarineSuffixDeletePopupComponent } from './application-process-marine-suffix-delete-dialog.component';
import { IApplicationProcessMarineSuffix } from 'app/shared/model/application-process-marine-suffix.model';
import { ApplicationProcessMarineSuffixCommentPopupComponent } from 'app/entities/application-process-marine-suffix/application-process-marine-suffix-comment-dialog.component';

@Injectable({ providedIn: 'root' })
export class ApplicationProcessMarineSuffixResolve implements Resolve<IApplicationProcessMarineSuffix> {
    constructor(private service: ApplicationProcessMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApplicationProcessMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<ApplicationProcessMarineSuffix>) => response.ok),
                    map((applicationProcess: HttpResponse<ApplicationProcessMarineSuffix>) => applicationProcess.body)
                );
        }
        return of(new ApplicationProcessMarineSuffix());
    }
}

export const applicationProcessRoute: Routes = [
    {
        path: 'application-process-marine-suffix',
        component: ApplicationProcessMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.applicationProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-marine-suffix/:id/view',
        component: ApplicationProcessMarineSuffixDetailComponent,
        resolve: {
            applicationProcess: ApplicationProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-marine-suffix/new',
        component: ApplicationProcessMarineSuffixUpdateComponent,
        resolve: {
            applicationProcess: ApplicationProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'application-process-marine-suffix/:id/edit',
        component: ApplicationProcessMarineSuffixUpdateComponent,
        resolve: {
            applicationProcess: ApplicationProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const applicationProcessPopupRoute: Routes = [
    {
        path: 'application-process-marine-suffix/:id/delete',
        component: ApplicationProcessMarineSuffixDeletePopupComponent,
        resolve: {
            applicationProcess: ApplicationProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'application-process-marine-suffix/:id/:CommentType/comment',
        component: ApplicationProcessMarineSuffixCommentPopupComponent,
        resolve: {
            applicationProcess: ApplicationProcessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.applicationProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
