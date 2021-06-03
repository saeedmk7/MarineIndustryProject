import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CapitationMarineSuffix } from 'app/shared/model/capitation-marine-suffix.model';
import { CapitationMarineSuffixService } from './capitation-marine-suffix.service';
import { CapitationMarineSuffixComponent } from './capitation-marine-suffix.component';
import { CapitationMarineSuffixDetailComponent } from './capitation-marine-suffix-detail.component';
import { CapitationMarineSuffixUpdateComponent } from './capitation-marine-suffix-update.component';
import { CapitationMarineSuffixDeletePopupComponent } from './capitation-marine-suffix-delete-dialog.component';
import { ICapitationMarineSuffix } from 'app/shared/model/capitation-marine-suffix.model';
import { CapitationReportMarineSuffixComponent } from 'app/entities/capitation-marine-suffix/capitation-report-marine-suffix.component';

@Injectable({ providedIn: 'root' })
export class CapitationMarineSuffixResolve implements Resolve<ICapitationMarineSuffix> {
    constructor(private service: CapitationMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CapitationMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<CapitationMarineSuffix>) => response.ok),
                    map((capitation: HttpResponse<CapitationMarineSuffix>) => capitation.body)
                );
        }
        return of(new CapitationMarineSuffix());
    }
}

export const capitationRoute: Routes = [
    {
        path: 'capitation-report-marine-suffix',
        component: CapitationReportMarineSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.capitation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'capitation-marine-suffix',
        component: CapitationMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.capitation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'capitation-marine-suffix/:id/view',
        component: CapitationMarineSuffixDetailComponent,
        resolve: {
            capitation: CapitationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.capitation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'capitation-marine-suffix/new',
        component: CapitationMarineSuffixUpdateComponent,
        resolve: {
            capitation: CapitationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.capitation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'capitation-marine-suffix/:id/edit',
        component: CapitationMarineSuffixUpdateComponent,
        resolve: {
            capitation: CapitationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.capitation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const capitationPopupRoute: Routes = [
    {
        path: 'capitation-marine-suffix/:id/delete',
        component: CapitationMarineSuffixDeletePopupComponent,
        resolve: {
            capitation: CapitationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.capitation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
