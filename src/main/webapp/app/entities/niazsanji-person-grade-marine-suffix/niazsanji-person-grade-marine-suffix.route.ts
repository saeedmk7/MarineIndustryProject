import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';
import { NiazsanjiPersonGradeMarineSuffixService } from './niazsanji-person-grade-marine-suffix.service';
import { NiazsanjiPersonGradeMarineSuffixDetailComponent } from './niazsanji-person-grade-marine-suffix-detail.component';
import { NiazsanjiPersonGradeMarineSuffixUpdateComponent } from './niazsanji-person-grade-marine-suffix-update.component';
import { NiazsanjiPersonGradeMarineSuffixDeletePopupComponent } from './niazsanji-person-grade-marine-suffix-delete-dialog.component';
import { INiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class NiazsanjiPersonGradeMarineSuffixResolve implements Resolve<INiazsanjiPersonGradeMarineSuffix> {
    constructor(private service: NiazsanjiPersonGradeMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NiazsanjiPersonGradeMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NiazsanjiPersonGradeMarineSuffix>) => response.ok),
                map((niazsanjiPersonGrade: HttpResponse<NiazsanjiPersonGradeMarineSuffix>) => niazsanjiPersonGrade.body)
            );
        }
        const finalNiazsanjiReportPersonId = route.params['finalNiazsanjiReportPersonId'] ? route.params['finalNiazsanjiReportPersonId'] : null;
        if(finalNiazsanjiReportPersonId){
            let niazsanjiPersonGrade = new NiazsanjiPersonGradeMarineSuffix();
            niazsanjiPersonGrade.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
            return of(niazsanjiPersonGrade);
        }
        return of(new NiazsanjiPersonGradeMarineSuffix());
    }
}

export const niazsanjiPersonGradeRoute: Routes = [
    /*{
        path: 'niazsanji-person-grade-marine-suffix',
        component: NiazsanjiPersonGradeMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },*/
    {
        path: 'niazsanji-person-grade-marine-suffix/:id/view',
        component: NiazsanjiPersonGradeMarineSuffixDetailComponent,
        resolve: {
            niazsanjiPersonGrade: NiazsanjiPersonGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-grade-marine-suffix/new/:finalNiazsanjiReportPersonId',
        component: NiazsanjiPersonGradeMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiPersonGrade: NiazsanjiPersonGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'niazsanji-person-grade-marine-suffix/:id/edit',
        component: NiazsanjiPersonGradeMarineSuffixUpdateComponent,
        resolve: {
            niazsanjiPersonGrade: NiazsanjiPersonGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGrade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const niazsanjiPersonGradePopupRoute: Routes = [
    {
        path: 'niazsanji-person-grade-marine-suffix/:id/delete',
        component: NiazsanjiPersonGradeMarineSuffixDeletePopupComponent,
        resolve: {
            niazsanjiPersonGrade: NiazsanjiPersonGradeMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.niazsanjiPersonGrade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
