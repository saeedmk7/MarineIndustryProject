import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JamHelpAuthorityMarineSuffix } from 'app/shared/model/jam-help-authority-marine-suffix.model';
import { JamHelpAuthorityMarineSuffixService } from './jam-help-authority-marine-suffix.service';
import { JamHelpAuthorityMarineSuffixComponent } from './jam-help-authority-marine-suffix.component';
import { JamHelpAuthorityMarineSuffixDetailComponent } from './jam-help-authority-marine-suffix-detail.component';
import { JamHelpAuthorityMarineSuffixUpdateComponent } from './jam-help-authority-marine-suffix-update.component';
import { JamHelpAuthorityMarineSuffixDeletePopupComponent } from './jam-help-authority-marine-suffix-delete-dialog.component';
import { IJamHelpAuthorityMarineSuffix } from 'app/shared/model/jam-help-authority-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class JamHelpAuthorityMarineSuffixResolve implements Resolve<IJamHelpAuthorityMarineSuffix> {
    constructor(private service: JamHelpAuthorityMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JamHelpAuthorityMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<JamHelpAuthorityMarineSuffix>) => response.ok),
                map((jamHelpAuthority: HttpResponse<JamHelpAuthorityMarineSuffix>) => jamHelpAuthority.body)
            );
        }
        return of(new JamHelpAuthorityMarineSuffix());
    }
}

export const jamHelpAuthorityRoute: Routes = [
    {
        path: 'jam-help-authority-marine-suffix',
        component: JamHelpAuthorityMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.jamHelpAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jam-help-authority-marine-suffix/:id/view',
        component: JamHelpAuthorityMarineSuffixDetailComponent,
        resolve: {
            jamHelpAuthority: JamHelpAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelpAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jam-help-authority-marine-suffix/new',
        component: JamHelpAuthorityMarineSuffixUpdateComponent,
        resolve: {
            jamHelpAuthority: JamHelpAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelpAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jam-help-authority-marine-suffix/:id/edit',
        component: JamHelpAuthorityMarineSuffixUpdateComponent,
        resolve: {
            jamHelpAuthority: JamHelpAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelpAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jamHelpAuthorityPopupRoute: Routes = [
    {
        path: 'jam-help-authority-marine-suffix/:id/delete',
        component: JamHelpAuthorityMarineSuffixDeletePopupComponent,
        resolve: {
            jamHelpAuthority: JamHelpAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jamHelpAuthority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
