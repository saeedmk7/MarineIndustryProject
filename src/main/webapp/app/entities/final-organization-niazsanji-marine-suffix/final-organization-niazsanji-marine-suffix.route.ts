import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import { FinalOrganizationNiazsanjiMarineSuffixService } from './final-organization-niazsanji-marine-suffix.service';
import { FinalOrganizationNiazsanjiMarineSuffixComponent } from './final-organization-niazsanji-marine-suffix.component';
import { FinalOrganizationNiazsanjiMarineSuffixDetailComponent } from './final-organization-niazsanji-marine-suffix-detail.component';
import { FinalOrganizationNiazsanjiMarineSuffixUpdateComponent } from './final-organization-niazsanji-marine-suffix-update.component';
import { FinalOrganizationNiazsanjiMarineSuffixDeletePopupComponent } from './final-organization-niazsanji-marine-suffix-delete-dialog.component';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import {FinalOrganizationNiazsanjiMarineSuffixCommentPopupComponent} from "app/entities/final-organization-niazsanji-marine-suffix/final-organization-niazsanji-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class FinalOrganizationNiazsanjiMarineSuffixResolve implements Resolve<IFinalOrganizationNiazsanjiMarineSuffix> {
    constructor(private service: FinalOrganizationNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    map(
                        (finalOrganizationNiazsanji: HttpResponse<FinalOrganizationNiazsanjiMarineSuffix>) =>
                            finalOrganizationNiazsanji.body
                    )
                );
        }
        return of(new FinalOrganizationNiazsanjiMarineSuffix());
    }
}

export const finalOrganizationNiazsanjiRoute: Routes = [
    {
        path: 'final-organization-niazsanji-marine-suffix',
        component: FinalOrganizationNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'modifyDate,desc',
            pageTitle: 'marineindustryprojApp.finalOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-organization-niazsanji-marine-suffix/:id/view',
        component: FinalOrganizationNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            finalOrganizationNiazsanji: FinalOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-organization-niazsanji-marine-suffix/new',
        component: FinalOrganizationNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            finalOrganizationNiazsanji: FinalOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'final-organization-niazsanji-marine-suffix/:id/edit',
        component: FinalOrganizationNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            finalOrganizationNiazsanji: FinalOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const finalOrganizationNiazsanjiPopupRoute: Routes = [
    {
        path: 'final-organization-niazsanji-marine-suffix/:id/delete',
        component: FinalOrganizationNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            finalOrganizationNiazsanji: FinalOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'final-organization-niazsanji-marine-suffix/:id/:CommentType/comment',
        component: FinalOrganizationNiazsanjiMarineSuffixCommentPopupComponent,
        resolve: {
            finalOrganizationNiazsanji: FinalOrganizationNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.finalOrganizationNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
