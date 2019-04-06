import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';
import { SecurityLevelMarineSuffixService } from './security-level-marine-suffix.service';
import { SecurityLevelMarineSuffixComponent } from './security-level-marine-suffix.component';
import { SecurityLevelMarineSuffixDetailComponent } from './security-level-marine-suffix-detail.component';
import { SecurityLevelMarineSuffixUpdateComponent } from './security-level-marine-suffix-update.component';
import { SecurityLevelMarineSuffixDeletePopupComponent } from './security-level-marine-suffix-delete-dialog.component';
import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class SecurityLevelMarineSuffixResolve implements Resolve<ISecurityLevelMarineSuffix> {
    constructor(private service: SecurityLevelMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((securityLevel: HttpResponse<SecurityLevelMarineSuffix>) => securityLevel.body));
        }
        return of(new SecurityLevelMarineSuffix());
    }
}

export const securityLevelRoute: Routes = [
    {
        path: 'security-level-marine-suffix',
        component: SecurityLevelMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.securityLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'security-level-marine-suffix/:id/view',
        component: SecurityLevelMarineSuffixDetailComponent,
        resolve: {
            securityLevel: SecurityLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.securityLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'security-level-marine-suffix/new',
        component: SecurityLevelMarineSuffixUpdateComponent,
        resolve: {
            securityLevel: SecurityLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.securityLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'security-level-marine-suffix/:id/edit',
        component: SecurityLevelMarineSuffixUpdateComponent,
        resolve: {
            securityLevel: SecurityLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.securityLevel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const securityLevelPopupRoute: Routes = [
    {
        path: 'security-level-marine-suffix/:id/delete',
        component: SecurityLevelMarineSuffixDeletePopupComponent,
        resolve: {
            securityLevel: SecurityLevelMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.securityLevel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
