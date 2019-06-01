import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';
import { JamHelpMarineSuffixService } from './jam-help-marine-suffix.service';
import { JamHelpMarineSuffixComponent } from './jam-help-marine-suffix.component';
import { JamHelpMarineSuffixDetailComponent } from './jam-help-marine-suffix-detail.component';
import { JamHelpMarineSuffixUpdateComponent } from './jam-help-marine-suffix-update.component';
import { JamHelpMarineSuffixDeletePopupComponent } from './jam-help-marine-suffix-delete-dialog.component';
import { IJamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class JamHelpMarineSuffixResolve implements Resolve<IJamHelpMarineSuffix> {
    constructor(private service: JamHelpMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JamHelpMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<JamHelpMarineSuffix>) => response.ok),
                map((jamHelp: HttpResponse<JamHelpMarineSuffix>) => jamHelp.body)
            );
        }
        return of(new JamHelpMarineSuffix());
    }
}

export const jamHelpRoute: Routes = [
    {
        path: 'jam-help-marine-suffix',
        component: JamHelpMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.jamHelp.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jam-help-marine-suffix/:id/view',
        component: JamHelpMarineSuffixDetailComponent,
        resolve: {
            jamHelp: JamHelpMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelp.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jam-help-marine-suffix/new',
        component: JamHelpMarineSuffixUpdateComponent,
        resolve: {
            jamHelp: JamHelpMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelp.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jam-help-marine-suffix/:id/edit',
        component: JamHelpMarineSuffixUpdateComponent,
        resolve: {
            jamHelp: JamHelpMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelp.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jamHelpPopupRoute: Routes = [
    {
        path: 'jam-help-marine-suffix/:id/delete',
        component: JamHelpMarineSuffixDeletePopupComponent,
        resolve: {
            jamHelp: JamHelpMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelp.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
