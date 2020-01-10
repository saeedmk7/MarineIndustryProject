import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { PreJobNiazsanjiMarineSuffixService } from './pre-job-niazsanji-marine-suffix.service';
import { PreJobNiazsanjiMarineSuffixComponent } from './pre-job-niazsanji-marine-suffix.component';
import { PreJobNiazsanjiMarineSuffixDetailComponent } from './pre-job-niazsanji-marine-suffix-detail.component';
import { PreJobNiazsanjiMarineSuffixUpdateComponent } from './pre-job-niazsanji-marine-suffix-update.component';
import { PreJobNiazsanjiMarineSuffixDeletePopupComponent } from './pre-job-niazsanji-marine-suffix-delete-dialog.component';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import {PreJobNiazsanjiMarineSuffixCommentPopupComponent} from "app/entities/pre-job-niazsanji-marine-suffix/pre-job-niazsanji-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class PreJobNiazsanjiMarineSuffixResolve implements Resolve<IPreJobNiazsanjiMarineSuffix> {
    constructor(private service: PreJobNiazsanjiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PreJobNiazsanjiMarineSuffix> {

        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PreJobNiazsanjiMarineSuffix>) => response.ok),
                map((preJobNiazsanji: HttpResponse<PreJobNiazsanjiMarineSuffix>) => preJobNiazsanji.body)
            );
        }
        return of(new PreJobNiazsanjiMarineSuffix());
    }
}

export const preJobNiazsanjiRoute: Routes = [
    {
        path: 'pre-job-niazsanji-marine-suffix',
        component: PreJobNiazsanjiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pre-job-niazsanji-marine-suffix/:id/view',
        component: PreJobNiazsanjiMarineSuffixDetailComponent,
        resolve: {
            preJobNiazsanji: PreJobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pre-job-niazsanji-marine-suffix/new',
        component: PreJobNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            preJobNiazsanji: PreJobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'pre-job-niazsanji-marine-suffix/:id/edit',
        component: PreJobNiazsanjiMarineSuffixUpdateComponent,
        resolve: {
            preJobNiazsanji: PreJobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const preJobNiazsanjiPopupRoute: Routes = [
    {
        path: 'pre-job-niazsanji-marine-suffix/:id/delete',
        component: PreJobNiazsanjiMarineSuffixDeletePopupComponent,
        resolve: {
            preJobNiazsanji: PreJobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },{
        path: 'pre-job-niazsanji-marine-suffix/:id/:CommentType/comment',
        component: PreJobNiazsanjiMarineSuffixCommentPopupComponent,
        resolve: {
            preJobNiazsanji: PreJobNiazsanjiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.preJobNiazsanji.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
