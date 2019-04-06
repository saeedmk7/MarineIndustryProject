import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';
import { NavBarItemMarineSuffixService } from './nav-bar-item-marine-suffix.service';
import { NavBarItemMarineSuffixComponent } from './nav-bar-item-marine-suffix.component';
import { NavBarItemMarineSuffixDetailComponent } from './nav-bar-item-marine-suffix-detail.component';
import { NavBarItemMarineSuffixUpdateComponent } from './nav-bar-item-marine-suffix-update.component';
import { NavBarItemMarineSuffixDeletePopupComponent } from './nav-bar-item-marine-suffix-delete-dialog.component';
import { INavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NavBarItemMarineSuffixResolve implements Resolve<INavBarItemMarineSuffix> {
    constructor(private service: NavBarItemMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((navBarItem: HttpResponse<NavBarItemMarineSuffix>) => navBarItem.body));
        }
        return of(new NavBarItemMarineSuffix());
    }
}

export const navBarItemRoute: Routes = [
    {
        path: 'nav-bar-item-marine-suffix',
        component: NavBarItemMarineSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nav-bar-item-marine-suffix/:id/view',
        component: NavBarItemMarineSuffixDetailComponent,
        resolve: {
            navBarItem: NavBarItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nav-bar-item-marine-suffix/new',
        component: NavBarItemMarineSuffixUpdateComponent,
        resolve: {
            navBarItem: NavBarItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nav-bar-item-marine-suffix/:id/edit',
        component: NavBarItemMarineSuffixUpdateComponent,
        resolve: {
            navBarItem: NavBarItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const navBarItemPopupRoute: Routes = [
    {
        path: 'nav-bar-item-marine-suffix/:id/delete',
        component: NavBarItemMarineSuffixDeletePopupComponent,
        resolve: {
            navBarItem: NavBarItemMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
