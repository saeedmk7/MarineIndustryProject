import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { BeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';
import { BeautySpeechMarineSuffixService } from './beauty-speech-marine-suffix.service';
import { BeautySpeechMarineSuffixComponent } from './beauty-speech-marine-suffix.component';
import { BeautySpeechMarineSuffixDetailComponent } from './beauty-speech-marine-suffix-detail.component';
import { BeautySpeechMarineSuffixUpdateComponent } from './beauty-speech-marine-suffix-update.component';
import { BeautySpeechMarineSuffixDeletePopupComponent } from './beauty-speech-marine-suffix-delete-dialog.component';
import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class BeautySpeechMarineSuffixResolve implements Resolve<IBeautySpeechMarineSuffix> {
    constructor(private service: BeautySpeechMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((beautySpeech: HttpResponse<BeautySpeechMarineSuffix>) => beautySpeech.body));
        }
        return of(new BeautySpeechMarineSuffix());
    }
}

export const beautySpeechRoute: Routes = [
    {
        path: 'beauty-speech-marine-suffix',
        component: BeautySpeechMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.beautySpeech.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beauty-speech-marine-suffix/:id/view',
        component: BeautySpeechMarineSuffixDetailComponent,
        resolve: {
            beautySpeech: BeautySpeechMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeech.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beauty-speech-marine-suffix/new',
        component: BeautySpeechMarineSuffixUpdateComponent,
        resolve: {
            beautySpeech: BeautySpeechMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeech.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'beauty-speech-marine-suffix/:id/edit',
        component: BeautySpeechMarineSuffixUpdateComponent,
        resolve: {
            beautySpeech: BeautySpeechMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeech.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const beautySpeechPopupRoute: Routes = [
    {
        path: 'beauty-speech-marine-suffix/:id/delete',
        component: BeautySpeechMarineSuffixDeletePopupComponent,
        resolve: {
            beautySpeech: BeautySpeechMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.beautySpeech.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
