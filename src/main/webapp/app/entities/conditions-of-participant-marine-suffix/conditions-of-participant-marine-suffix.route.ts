import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ConditionsOfParticipantMarineSuffix } from 'app/shared/model/conditions-of-participant-marine-suffix.model';
import { ConditionsOfParticipantMarineSuffixService } from './conditions-of-participant-marine-suffix.service';
import { ConditionsOfParticipantMarineSuffixComponent } from './conditions-of-participant-marine-suffix.component';
import { ConditionsOfParticipantMarineSuffixDetailComponent } from './conditions-of-participant-marine-suffix-detail.component';
import { ConditionsOfParticipantMarineSuffixUpdateComponent } from './conditions-of-participant-marine-suffix-update.component';
import { ConditionsOfParticipantMarineSuffixDeletePopupComponent } from './conditions-of-participant-marine-suffix-delete-dialog.component';
import { IConditionsOfParticipantMarineSuffix } from 'app/shared/model/conditions-of-participant-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ConditionsOfParticipantMarineSuffixResolve implements Resolve<IConditionsOfParticipantMarineSuffix> {
    constructor(private service: ConditionsOfParticipantMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((conditionsOfParticipant: HttpResponse<ConditionsOfParticipantMarineSuffix>) => conditionsOfParticipant.body));
        }
        return of(new ConditionsOfParticipantMarineSuffix());
    }
}

export const conditionsOfParticipantRoute: Routes = [
    {
        path: 'conditions-of-participant-marine-suffix',
        component: ConditionsOfParticipantMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.conditionsOfParticipant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conditions-of-participant-marine-suffix/:id/view',
        component: ConditionsOfParticipantMarineSuffixDetailComponent,
        resolve: {
            conditionsOfParticipant: ConditionsOfParticipantMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.conditionsOfParticipant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conditions-of-participant-marine-suffix/new',
        component: ConditionsOfParticipantMarineSuffixUpdateComponent,
        resolve: {
            conditionsOfParticipant: ConditionsOfParticipantMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.conditionsOfParticipant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conditions-of-participant-marine-suffix/:id/edit',
        component: ConditionsOfParticipantMarineSuffixUpdateComponent,
        resolve: {
            conditionsOfParticipant: ConditionsOfParticipantMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.conditionsOfParticipant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conditionsOfParticipantPopupRoute: Routes = [
    {
        path: 'conditions-of-participant-marine-suffix/:id/delete',
        component: ConditionsOfParticipantMarineSuffixDeletePopupComponent,
        resolve: {
            conditionsOfParticipant: ConditionsOfParticipantMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.conditionsOfParticipant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
