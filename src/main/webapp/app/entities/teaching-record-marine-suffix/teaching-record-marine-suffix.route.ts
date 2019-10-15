import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TeachingRecordMarineSuffix } from 'app/shared/model/teaching-record-marine-suffix.model';
import { TeachingRecordMarineSuffixService } from './teaching-record-marine-suffix.service';
import { TeachingRecordMarineSuffixComponent } from './teaching-record-marine-suffix.component';
import { TeachingRecordMarineSuffixDetailComponent } from './teaching-record-marine-suffix-detail.component';
import { TeachingRecordMarineSuffixUpdateComponent } from './teaching-record-marine-suffix-update.component';
import { TeachingRecordMarineSuffixDeletePopupComponent } from './teaching-record-marine-suffix-delete-dialog.component';
import { ITeachingRecordMarineSuffix } from 'app/shared/model/teaching-record-marine-suffix.model';
import {EducationalRecordMarineSuffix} from "app/shared/model/educational-record-marine-suffix.model";

@Injectable({ providedIn: 'root' })
export class TeachingRecordMarineSuffixResolve implements Resolve<ITeachingRecordMarineSuffix> {
    constructor(private service: TeachingRecordMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TeachingRecordMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TeachingRecordMarineSuffix>) => response.ok),
                map((teachingRecord: HttpResponse<TeachingRecordMarineSuffix>) => teachingRecord.body)
            );
        }
        const personGuid = route.params['personGuid'] ? route.params['personGuid'] : null;
        if(personGuid)
        {
            let newObject = new TeachingRecordMarineSuffix();
            newObject.personGuid = personGuid;
            return of(newObject);
        }
        else {
            return of(new TeachingRecordMarineSuffix());
        }

    }
}

export const teachingRecordRoute: Routes = [
    {
        path: 'teaching-record-marine-suffix',
        component: TeachingRecordMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teachingRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teaching-record-marine-suffix/view/:id',
        component: TeachingRecordMarineSuffixDetailComponent,
        resolve: {
            teachingRecord: TeachingRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teaching-record-marine-suffix/new',
        component: TeachingRecordMarineSuffixUpdateComponent,
        resolve: {
            teachingRecord: TeachingRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teaching-record-marine-suffix/new/:personGuid',
        component: TeachingRecordMarineSuffixUpdateComponent,
        resolve: {
            teachingRecord: TeachingRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teaching-record-marine-suffix/edit/:id',
        component: TeachingRecordMarineSuffixUpdateComponent,
        resolve: {
            teachingRecord: TeachingRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teachingRecordPopupRoute: Routes = [
    {
        path: 'teaching-record-marine-suffix/delete/:id',
        component: TeachingRecordMarineSuffixDeletePopupComponent,
        resolve: {
            teachingRecord: TeachingRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachingRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
