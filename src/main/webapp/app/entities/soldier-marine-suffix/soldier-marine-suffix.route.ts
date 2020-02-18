import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';
import { SoldierMarineSuffixService } from './soldier-marine-suffix.service';
import { SoldierMarineSuffixComponent } from './soldier-marine-suffix.component';
import { SoldierMarineSuffixDetailComponent } from './soldier-marine-suffix-detail.component';
import { SoldierMarineSuffixUpdateComponent } from './soldier-marine-suffix-update.component';
import { SoldierMarineSuffixDeletePopupComponent } from './soldier-marine-suffix-delete-dialog.component';
import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class SoldierMarineSuffixResolve implements Resolve<ISoldierMarineSuffix> {
    constructor(private service: SoldierMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SoldierMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SoldierMarineSuffix>) => response.ok),
                map((soldier: HttpResponse<SoldierMarineSuffix>) => soldier.body)
            );
        }
        return of(new SoldierMarineSuffix());
    }
}

export const soldierRoute: Routes = [
    {
        path: 'soldier-marine-suffix',
        component: SoldierMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.soldier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-marine-suffix/:id/view',
        component: SoldierMarineSuffixDetailComponent,
        resolve: {
            soldier: SoldierMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-marine-suffix/new',
        component: SoldierMarineSuffixUpdateComponent,
        resolve: {
            soldier: SoldierMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'soldier-marine-suffix/:id/edit',
        component: SoldierMarineSuffixUpdateComponent,
        resolve: {
            soldier: SoldierMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldier.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const soldierPopupRoute: Routes = [
    {
        path: 'soldier-marine-suffix/:id/delete',
        component: SoldierMarineSuffixDeletePopupComponent,
        resolve: {
            soldier: SoldierMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.soldier.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
