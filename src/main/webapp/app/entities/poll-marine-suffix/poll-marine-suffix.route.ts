import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PollMarineSuffix } from 'app/shared/model/poll-marine-suffix.model';
import { PollMarineSuffixService } from './poll-marine-suffix.service';
import { PollMarineSuffixComponent } from './poll-marine-suffix.component';
import { PollMarineSuffixDetailComponent } from './poll-marine-suffix-detail.component';
import { PollMarineSuffixUpdateComponent } from './poll-marine-suffix-update.component';
import { PollMarineSuffixDeletePopupComponent } from './poll-marine-suffix-delete-dialog.component';
import { IPollMarineSuffix } from 'app/shared/model/poll-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PollMarineSuffixResolve implements Resolve<IPollMarineSuffix> {
    constructor(private service: PollMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((poll: HttpResponse<PollMarineSuffix>) => poll.body));
        }
        return of(new PollMarineSuffix());
    }
}

export const pollRoute: Routes = [
    {
        path: 'poll-marine-suffix',
        component: PollMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.poll.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-marine-suffix/:id/view',
        component: PollMarineSuffixDetailComponent,
        resolve: {
            poll: PollMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.poll.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-marine-suffix/new',
        component: PollMarineSuffixUpdateComponent,
        resolve: {
            poll: PollMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.poll.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-marine-suffix/:id/edit',
        component: PollMarineSuffixUpdateComponent,
        resolve: {
            poll: PollMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.poll.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pollPopupRoute: Routes = [
    {
        path: 'poll-marine-suffix/:id/delete',
        component: PollMarineSuffixDeletePopupComponent,
        resolve: {
            poll: PollMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.poll.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
