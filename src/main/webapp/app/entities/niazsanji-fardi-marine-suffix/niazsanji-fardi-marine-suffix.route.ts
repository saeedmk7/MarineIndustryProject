import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NiazsanjiFardiMarineSuffix } from 'app/shared/model/niazsanji-fardi-marine-suffix.model';
import { NiazsanjiFardiMarineSuffixService } from './niazsanji-fardi-marine-suffix.service';
import { NiazsanjiFardiMarineSuffixComponent } from './niazsanji-fardi-marine-suffix.component';
import { NiazsanjiFardiMarineSuffixDetailComponent } from './niazsanji-fardi-marine-suffix-detail.component';
import { NiazsanjiFardiMarineSuffixUpdateComponent } from './niazsanji-fardi-marine-suffix-update.component';
import { NiazsanjiFardiMarineSuffixDeletePopupComponent } from './niazsanji-fardi-marine-suffix-delete-dialog.component';
import { INiazsanjiFardiMarineSuffix } from 'app/shared/model/niazsanji-fardi-marine-suffix.model';
import {NiazsanjiFardiMarineSuffixCommentPopupComponent} from "app/entities/niazsanji-fardi-marine-suffix/niazsanji-fardi-marine-suffix-comment-dialog.component";

@Injectable({ providedIn: 'root' })
export class NiazsanjiFardiMarineSuffixResolve implements Resolve<INiazsanjiFardiMarineSuffix> {
    constructor(private service: NiazsanjiFardiMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NiazsanjiFardiMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NiazsanjiFardiMarineSuffix>) => response.ok),
                map((niazsanjiFardi: HttpResponse<NiazsanjiFardiMarineSuffix>) => niazsanjiFardi.body)
            );
        }
        return of(new NiazsanjiFardiMarineSuffix());
    }
}

export const niazsanjiFardiRoute: Routes = [
    {
        path: 'niazsanji-fardi-marine-suffix',
        component: NiazsanjiFardiMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'modifyDate,desc',
            pageTitle: 'marineindustryprojApp.niazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-fardi-marine-suffix/:id/view',
        component: NiazsanjiFardiMarineSuffixDetailComponent,
        resolve: {
            niazsanjiFardi: NiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-fardi-marine-suffix/new',
        component: NiazsanjiFardiMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiFardi: NiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-fardi-marine-suffix/:id/edit',
        component: NiazsanjiFardiMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiFardi: NiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiFardiPopupRoute: Routes = [
    {
        path: 'niazsanji-fardi-marine-suffix/:id/delete',
        component: NiazsanjiFardiMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiFardi: NiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'niazsanji-fardi-marine-suffix/:id/:CommentType/comment',
        component: NiazsanjiFardiMarineSuffixCommentPopupComponent,
        resolve: {
            niazsanjiFardi: NiazsanjiFardiMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiFardi.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
