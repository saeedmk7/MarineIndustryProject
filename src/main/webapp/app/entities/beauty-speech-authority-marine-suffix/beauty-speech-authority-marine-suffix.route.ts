import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BeautySpeechAuthorityMarineSuffix } from 'app/shared/model/beauty-speech-authority-marine-suffix.model';
import { BeautySpeechAuthorityMarineSuffixService } from './beauty-speech-authority-marine-suffix.service';
import { BeautySpeechAuthorityMarineSuffixComponent } from './beauty-speech-authority-marine-suffix.component';
import { BeautySpeechAuthorityMarineSuffixDetailComponent } from './beauty-speech-authority-marine-suffix-detail.component';
import { BeautySpeechAuthorityMarineSuffixUpdateComponent } from './beauty-speech-authority-marine-suffix-update.component';
import { BeautySpeechAuthorityMarineSuffixDeletePopupComponent } from './beauty-speech-authority-marine-suffix-delete-dialog.component';
import { IBeautySpeechAuthorityMarineSuffix } from 'app/shared/model/beauty-speech-authority-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class BeautySpeechAuthorityMarineSuffixResolve implements Resolve<IBeautySpeechAuthorityMarineSuffix> {
    constructor(private service: BeautySpeechAuthorityMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BeautySpeechAuthorityMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<BeautySpeechAuthorityMarineSuffix>) => response.ok),
                map((beautySpeechAuthority: HttpResponse<BeautySpeechAuthorityMarineSuffix>) => beautySpeechAuthority.body)
            );
        }
        return of(new BeautySpeechAuthorityMarineSuffix());
    }
}

export const beautySpeechAuthorityRoute: Routes = [
    {
        path: 'beauty-speech-authority-marine-suffix',
        component: BeautySpeechAuthorityMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.beautySpeechAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beauty-speech-authority-marine-suffix/:id/view',
        component: BeautySpeechAuthorityMarineSuffixDetailComponent,
        resolve: {
            beautySpeechAuthority: BeautySpeechAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeechAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beauty-speech-authority-marine-suffix/new',
        component: BeautySpeechAuthorityMarineSuffixUpdateComponent,
        resolve: {
            beautySpeechAuthority: BeautySpeechAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeechAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beauty-speech-authority-marine-suffix/:id/edit',
        component: BeautySpeechAuthorityMarineSuffixUpdateComponent,
        resolve: {
            beautySpeechAuthority: BeautySpeechAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeechAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const beautySpeechAuthorityPopupRoute: Routes = [
    {
        path: 'beauty-speech-authority-marine-suffix/:id/delete',
        component: BeautySpeechAuthorityMarineSuffixDeletePopupComponent,
        resolve: {
            beautySpeechAuthority: BeautySpeechAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeechAuthority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
