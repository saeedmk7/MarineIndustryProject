import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model/niazsanji-person-grade-score-marine-suffix.model';
import { NiazsanjiPersonGradeScoreMarineSuffixService } from './niazsanji-person-grade-score-marine-suffix.service';
import { NiazsanjiPersonGradeScoreMarineSuffixComponent } from './niazsanji-person-grade-score-marine-suffix.component';
import { NiazsanjiPersonGradeScoreMarineSuffixDetailComponent } from './niazsanji-person-grade-score-marine-suffix-detail.component';
import { NiazsanjiPersonGradeScoreMarineSuffixUpdateComponent } from './niazsanji-person-grade-score-marine-suffix-update.component';
import { NiazsanjiPersonGradeScoreMarineSuffixDeletePopupComponent } from './niazsanji-person-grade-score-marine-suffix-delete-dialog.component';
import { INiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model/niazsanji-person-grade-score-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NiazsanjiPersonGradeScoreMarineSuffixResolve implements Resolve<INiazsanjiPersonGradeScoreMarineSuffix> {
    constructor(private service: NiazsanjiPersonGradeScoreMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NiazsanjiPersonGradeScoreMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NiazsanjiPersonGradeScoreMarineSuffix>) => response.ok),
                map((niazsanjiPersonGradeScore: HttpResponse<NiazsanjiPersonGradeScoreMarineSuffix>) => niazsanjiPersonGradeScore.body)
            );
        }
        return of(new NiazsanjiPersonGradeScoreMarineSuffix());
    }
}

export const niazsanjiPersonGradeScoreRoute: Routes = [
    {
        path: 'niazsanji-person-grade-score-marine-suffix',
        component: NiazsanjiPersonGradeScoreMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-grade-score-marine-suffix/:id/view',
        component: NiazsanjiPersonGradeScoreMarineSuffixDetailComponent,
        resolve: {
            niazsanjiPersonGradeScore: NiazsanjiPersonGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-grade-score-marine-suffix/new',
        component: NiazsanjiPersonGradeScoreMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiPersonGradeScore: NiazsanjiPersonGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-grade-score-marine-suffix/:id/edit',
        component: NiazsanjiPersonGradeScoreMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiPersonGradeScore: NiazsanjiPersonGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiPersonGradeScorePopupRoute: Routes = [
    {
        path: 'niazsanji-person-grade-score-marine-suffix/:id/delete',
        component: NiazsanjiPersonGradeScoreMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiPersonGradeScore: NiazsanjiPersonGradeScoreMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGradeScore.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
