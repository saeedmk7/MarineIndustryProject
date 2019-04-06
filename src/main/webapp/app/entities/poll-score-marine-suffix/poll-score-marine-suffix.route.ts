import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PollScoreMarineSuffix } from 'app/shared/model/poll-score-marine-suffix.model';
import { PollScoreMarineSuffixService } from './poll-score-marine-suffix.service';
import { PollScoreMarineSuffixComponent } from './poll-score-marine-suffix.component';
import { PollScoreMarineSuffixDetailComponent } from './poll-score-marine-suffix-detail.component';
import { PollScoreMarineSuffixUpdateComponent } from './poll-score-marine-suffix-update.component';
import { PollScoreMarineSuffixDeletePopupComponent } from './poll-score-marine-suffix-delete-dialog.component';
import { IPollScoreMarineSuffix } from 'app/shared/model/poll-score-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PollScoreMarineSuffixResolve implements Resolve<IPollScoreMarineSuffix> {
    constructor(private service: PollScoreMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((pollScore: HttpResponse<PollScoreMarineSuffix>) => pollScore.body));
        }
        return of(new PollScoreMarineSuffix());
    }
}

export const pollScoreRoute: Routes = [
    {
        path: 'poll-score-marine-suffix',
        component: PollScoreMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.pollScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-score-marine-suffix/:id/view',
        component: PollScoreMarineSuffixDetailComponent,
        resolve: {
            pollScore: PollScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-score-marine-suffix/new',
        component: PollScoreMarineSuffixUpdateComponent,
        resolve: {
            pollScore: PollScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'poll-score-marine-suffix/:id/edit',
        component: PollScoreMarineSuffixUpdateComponent,
        resolve: {
            pollScore: PollScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pollScorePopupRoute: Routes = [
    {
        path: 'poll-score-marine-suffix/:id/delete',
        component: PollScoreMarineSuffixDeletePopupComponent,
        resolve: {
            pollScore: PollScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.pollScore.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
