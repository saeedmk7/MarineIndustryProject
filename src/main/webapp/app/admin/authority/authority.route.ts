import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Authority } from 'app/shared/model/authority.model';
import { AuthorityComponent } from './authority.component';
import {AuthorityDeletePopupComponent, AuthorityUpdateComponent} from 'app/admin/authority';

@Injectable({ providedIn: 'root' })
export class AuthorityResolve implements Resolve<any> {
    constructor() {}

    resolve() {
        return new Authority();
    }
}

export const authorityRoute: Routes = [
    {
        path: 'authority',
        component: AuthorityComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'marineindustryprojApp.authority.home.title'
        }
    },
    {
        path: 'authority/new',
        component: AuthorityUpdateComponent,
        resolve: {
            authority: AuthorityResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'marineindustryprojApp.authority.home.title'
        }
    }
];

export const authorityPopupRoute: Routes = [
    {
        path: 'authority/:name/delete',
        component: AuthorityDeletePopupComponent,
        resolve: {
            authority: AuthorityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.authority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
