import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PollItemMarineSuffix } from 'app/shared/model/poll-item-marine-suffix.model';
import { PollItemMarineSuffixService } from './poll-item-marine-suffix.service';
import { PollItemMarineSuffixComponent } from './poll-item-marine-suffix.component';
import { PollItemMarineSuffixDetailComponent } from './poll-item-marine-suffix-detail.component';
import { PollItemMarineSuffixUpdateComponent } from './poll-item-marine-suffix-update.component';
import { PollItemMarineSuffixDeletePopupComponent } from './poll-item-marine-suffix-delete-dialog.component';
import { IPollItemMarineSuffix } from 'app/shared/model/poll-item-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PollItemMarineSuffixResolve implements Resolve<IPollItemMarineSuffix> {
    constructor(private service: PollItemMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pollItem: HttpResponse<PollItemMarineSuffix>) => pollItem.body));
        }
        return of(new PollItemMarineSuffix());
    }
}

export const pollItemRoute: Routes = [
    {
        path: 'poll-item-marine-suffix',
        component: PollItemMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.pollItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-item-marine-suffix/:id/view',
        component: PollItemMarineSuffixDetailComponent,
        resolve: {
            pollItem: PollItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-item-marine-suffix/new',
        component: PollItemMarineSuffixUpdateComponent,
        resolve: {
            pollItem: PollItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-item-marine-suffix/:id/edit',
        component: PollItemMarineSuffixUpdateComponent,
        resolve: {
            pollItem: PollItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pollItemPopupRoute: Routes = [
    {
        path: 'poll-item-marine-suffix/:id/delete',
        component: PollItemMarineSuffixDeletePopupComponent,
        resolve: {
            pollItem: PollItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
