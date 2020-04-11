import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ActivationNiazsanjiMarineSuffix } from 'app/shared/model/activation-niazsanji-marine-suffix.model';
import { ActivationNiazsanjiMarineSuffixService } from './activation-niazsanji-marine-suffix.service';
import { ActivationNiazsanjiMarineSuffixComponent } from './activation-niazsanji-marine-suffix.component';
import { ActivationNiazsanjiMarineSuffixDetailComponent } from './activation-niazsanji-marine-suffix-detail.component';
import { ActivationNiazsanjiMarineSuffixUpdateComponent } from './activation-niazsanji-marine-suffix-update.component';
import { ActivationNiazsanjiMarineSuffixDeletePopupComponent } from './activation-niazsanji-marine-suffix-delete-dialog.component';
import { IActivationNiazsanjiMarineSuffix } from 'app/shared/model/activation-niazsanji-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ActivationNiazsanjiMarineSuffixResolve implements Resolve<IActivationNiazsanjiMarineSuffix> {
    constructor(private service: ActivationNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ActivationNiazsanjiMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ActivationNiazsanjiMarineSuffix>) => response.ok),
                map((activationNiazsanji: HttpResponse<ActivationNiazsanjiMarineSuffix>) => activationNiazsanji.body)
            );
        }
        return of(new ActivationNiazsanjiMarineSuffix());
    }
}

export const activationNiazsanjiRoute: Routes = [
    {
        path: 'activation-niazsanji-marine-suffix',
        component: ActivationNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.activationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activation-niazsanji-marine-suffix/:id/view',
        component: ActivationNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            activationNiazsanji: ActivationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activation-niazsanji-marine-suffix/new',
        component: ActivationNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            activationNiazsanji: ActivationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'activation-niazsanji-marine-suffix/:id/edit',
        component: ActivationNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            activationNiazsanji: ActivationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const activationNiazsanjiPopupRoute: Routes = [
    {
        path: 'activation-niazsanji-marine-suffix/:id/delete',
        component: ActivationNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            activationNiazsanji: ActivationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.activationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
