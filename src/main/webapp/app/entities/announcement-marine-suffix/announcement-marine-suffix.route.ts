import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AnnouncementMarineSuffix } from 'app/shared/model/announcement-marine-suffix.model';
import { AnnouncementMarineSuffixService } from './announcement-marine-suffix.service';
import { AnnouncementMarineSuffixComponent } from './announcement-marine-suffix.component';
import { AnnouncementMarineSuffixDetailComponent } from './announcement-marine-suffix-detail.component';
import { AnnouncementMarineSuffixUpdateComponent } from './announcement-marine-suffix-update.component';
import { AnnouncementMarineSuffixDeletePopupComponent } from './announcement-marine-suffix-delete-dialog.component';
import { IAnnouncementMarineSuffix } from 'app/shared/model/announcement-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class AnnouncementMarineSuffixResolve implements Resolve<IAnnouncementMarineSuffix> {
    constructor(private service: AnnouncementMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((announcement: HttpResponse<AnnouncementMarineSuffix>) => announcement.body));
        }
        return of(new AnnouncementMarineSuffix());
    }
}

export const announcementRoute: Routes = [
    {
        path: 'announcement-marine-suffix',
        component: AnnouncementMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.announcement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'announcement-marine-suffix/:id/view',
        component: AnnouncementMarineSuffixDetailComponent,
        resolve: {
            announcement: AnnouncementMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.announcement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'announcement-marine-suffix/new',
        component: AnnouncementMarineSuffixUpdateComponent,
        resolve: {
            announcement: AnnouncementMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.announcement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'announcement-marine-suffix/:id/edit',
        component: AnnouncementMarineSuffixUpdateComponent,
        resolve: {
            announcement: AnnouncementMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.announcement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const announcementPopupRoute: Routes = [
    {
        path: 'announcement-marine-suffix/:id/delete',
        component: AnnouncementMarineSuffixDeletePopupComponent,
        resolve: {
            announcement: AnnouncementMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.announcement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
