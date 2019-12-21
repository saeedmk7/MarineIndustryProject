import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MediaAwarenessReportMarineSuffix } from 'app/shared/model/media-awareness-report-marine-suffix.model';
import { MediaAwarenessReportMarineSuffixService } from './media-awareness-report-marine-suffix.service';
import { MediaAwarenessReportMarineSuffixComponent } from './media-awareness-report-marine-suffix.component';
import { MediaAwarenessReportMarineSuffixDetailComponent } from './media-awareness-report-marine-suffix-detail.component';
import { MediaAwarenessReportMarineSuffixUpdateComponent } from './media-awareness-report-marine-suffix-update.component';
import { MediaAwarenessReportMarineSuffixDeletePopupComponent } from './media-awareness-report-marine-suffix-delete-dialog.component';
import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model/media-awareness-report-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class MediaAwarenessReportMarineSuffixResolve implements Resolve<IMediaAwarenessReportMarineSuffix> {
    constructor(private service: MediaAwarenessReportMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MediaAwarenessReportMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MediaAwarenessReportMarineSuffix>) => response.ok),
                map((mediaAwarenessReport: HttpResponse<MediaAwarenessReportMarineSuffix>) => mediaAwarenessReport.body)
            );
        }
        return of(new MediaAwarenessReportMarineSuffix());
    }
}

export const mediaAwarenessReportRoute: Routes = [
    {
        path: 'media-awareness-report-marine-suffix',
        component: MediaAwarenessReportMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.mediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'media-awareness-report-marine-suffix/:id/view',
        component: MediaAwarenessReportMarineSuffixDetailComponent,
        resolve: {
            mediaAwarenessReport: MediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'media-awareness-report-marine-suffix/new',
        component: MediaAwarenessReportMarineSuffixUpdateComponent,
        resolve: {
            mediaAwarenessReport: MediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'media-awareness-report-marine-suffix/:id/edit',
        component: MediaAwarenessReportMarineSuffixUpdateComponent,
        resolve: {
            mediaAwarenessReport: MediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mediaAwarenessReportPopupRoute: Routes = [
    {
        path: 'media-awareness-report-marine-suffix/:id/delete',
        component: MediaAwarenessReportMarineSuffixDeletePopupComponent,
        resolve: {
            mediaAwarenessReport: MediaAwarenessReportMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.mediaAwarenessReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
