import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NavBarItemAuthorityMarineSuffix } from 'app/shared/model/nav-bar-item-authority-marine-suffix.model';
import { NavBarItemAuthorityMarineSuffixService } from './nav-bar-item-authority-marine-suffix.service';
import { NavBarItemAuthorityMarineSuffixComponent } from './nav-bar-item-authority-marine-suffix.component';
import { NavBarItemAuthorityMarineSuffixDetailComponent } from './nav-bar-item-authority-marine-suffix-detail.component';
import { NavBarItemAuthorityMarineSuffixUpdateComponent } from './nav-bar-item-authority-marine-suffix-update.component';
import { NavBarItemAuthorityMarineSuffixDeletePopupComponent } from './nav-bar-item-authority-marine-suffix-delete-dialog.component';
import { INavBarItemAuthorityMarineSuffix } from 'app/shared/model/nav-bar-item-authority-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NavBarItemAuthorityMarineSuffixResolve implements Resolve<INavBarItemAuthorityMarineSuffix> {
    constructor(private service: NavBarItemAuthorityMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((navBarItemAuthority: HttpResponse<NavBarItemAuthorityMarineSuffix>) => navBarItemAuthority.body));
        }
        const name = route.params['authorityName'] ? route.params['authorityName'] : null;
        if (name) {
            let navbar = new NavBarItemAuthorityMarineSuffix();
            navbar.authorityName = name;
            return navbar;
        }
        return of(new NavBarItemAuthorityMarineSuffix());
    }
}

export const navBarItemAuthorityRoute: Routes = [
    {
        path: 'nav-bar-item-authority-marine-suffix',
        component: NavBarItemAuthorityMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.navBarItemAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nav-bar-item-authority-marine-suffix/:id/view',
        component: NavBarItemAuthorityMarineSuffixDetailComponent,
        resolve: {
            navBarItemAuthority: NavBarItemAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItemAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nav-bar-item-authority-marine-suffix/new',
        component: NavBarItemAuthorityMarineSuffixUpdateComponent,
        resolve: {
            navBarItemAuthority: NavBarItemAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItemAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nav-bar-item-authority-marine-suffix/:authorityName/new',
        component: NavBarItemAuthorityMarineSuffixUpdateComponent,
        resolve: {
            navBarItemAuthority: NavBarItemAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItemAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nav-bar-item-authority-marine-suffix/:id/edit',
        component: NavBarItemAuthorityMarineSuffixUpdateComponent,
        resolve: {
            navBarItemAuthority: NavBarItemAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItemAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const navBarItemAuthorityPopupRoute: Routes = [
    {
        path: 'nav-bar-item-authority-marine-suffix/:id/delete',
        component: NavBarItemAuthorityMarineSuffixDeletePopupComponent,
        resolve: {
            navBarItemAuthority: NavBarItemAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.navBarItemAuthority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
