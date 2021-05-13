import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { MatchingEducationalRecordMarineSuffixService } from './matching-educational-record-marine-suffix.service';
import { MatchingEducationalRecordMarineSuffixComponent } from './matching-educational-record-marine-suffix.component';
import { MatchingEducationalRecordMarineSuffixDetailComponent } from './matching-educational-record-marine-suffix-detail.component';
import { MatchingEducationalRecordMarineSuffixUpdateComponent } from './matching-educational-record-marine-suffix-update.component';
import { MatchingEducationalRecordMarineSuffixDeletePopupComponent } from './matching-educational-record-marine-suffix-delete-dialog.component';
import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { MatchingEducationalRecordMarineSuffixCommentPopupComponent } from 'app/entities/matching-educational-record-marine-suffix/matching-educational-record-marine-suffix-comment-dialog.component';

@Injectable({ providedIn: 'root' })
export class MatchingEducationalRecordMarineSuffixResolve implements Resolve<IMatchingEducationalRecordMarineSuffix> {
    constructor(private service: MatchingEducationalRecordMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MatchingEducationalRecordMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<MatchingEducationalRecordMarineSuffix>) => response.ok),
                    map((matchingEducationalRecord: HttpResponse<MatchingEducationalRecordMarineSuffix>) => matchingEducationalRecord.body)
                );
        }
        return of(new MatchingEducationalRecordMarineSuffix());
    }
}

export const matchingEducationalRecordRoute: Routes = [
    {
        path: 'matching-educational-record-marine-suffix',
        component: MatchingEducationalRecordMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.matchingEducationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-educational-record-marine-suffix/:id/view',
        component: MatchingEducationalRecordMarineSuffixDetailComponent,
        resolve: {
            matchingEducationalRecord: MatchingEducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingEducationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-educational-record-marine-suffix/new',
        component: MatchingEducationalRecordMarineSuffixUpdateComponent,
        resolve: {
            matchingEducationalRecord: MatchingEducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingEducationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matching-educational-record-marine-suffix/:id/edit',
        component: MatchingEducationalRecordMarineSuffixUpdateComponent,
        resolve: {
            matchingEducationalRecord: MatchingEducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingEducationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const matchingEducationalRecordPopupRoute: Routes = [
    {
        path: 'matching-educational-record-marine-suffix/:id/delete',
        component: MatchingEducationalRecordMarineSuffixDeletePopupComponent,
        resolve: {
            matchingEducationalRecord: MatchingEducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingEducationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'matching-educational-record-marine-suffix/:id/:CommentType/comment',
        component: MatchingEducationalRecordMarineSuffixCommentPopupComponent,
        resolve: {
            matchingEducationalRecord: MatchingEducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.matchingEducationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
