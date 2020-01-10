import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DesignNiazsanjiMarineSuffix } from 'app/shared/model/design-niazsanji-marine-suffix.model';
import { DesignNiazsanjiMarineSuffixService } from './design-niazsanji-marine-suffix.service';
import { DesignNiazsanjiMarineSuffixComponent } from './design-niazsanji-marine-suffix.component';
import { DesignNiazsanjiMarineSuffixDetailComponent } from './design-niazsanji-marine-suffix-detail.component';
import { DesignNiazsanjiMarineSuffixUpdateComponent } from './design-niazsanji-marine-suffix-update.component';
import { DesignNiazsanjiMarineSuffixDeletePopupComponent } from './design-niazsanji-marine-suffix-delete-dialog.component';
import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model/design-niazsanji-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class DesignNiazsanjiMarineSuffixResolve implements Resolve<IDesignNiazsanjiMarineSuffix> {
    constructor(private service: DesignNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DesignNiazsanjiMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DesignNiazsanjiMarineSuffix>) => response.ok),
                map((designNiazsanji: HttpResponse<DesignNiazsanjiMarineSuffix>) => designNiazsanji.body)
            );
        }
        return of(new DesignNiazsanjiMarineSuffix());
    }
}

export const designNiazsanjiRoute: Routes = [
    {
        path: 'design-niazsanji-marine-suffix',
        component: DesignNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.designNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'design-niazsanji-marine-suffix/:id/view',
        component: DesignNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            designNiazsanji: DesignNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'design-niazsanji-marine-suffix/new',
        component: DesignNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            designNiazsanji: DesignNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }/*,
    {
        path: 'design-niazsanji-marine-suffix/:id/edit',
        component: DesignNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            designNiazsanji: DesignNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }*/
];

export const designNiazsanjiPopupRoute: Routes = [
    {
        path: 'design-niazsanji-marine-suffix/:id/delete',
        component: DesignNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            designNiazsanji: DesignNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
