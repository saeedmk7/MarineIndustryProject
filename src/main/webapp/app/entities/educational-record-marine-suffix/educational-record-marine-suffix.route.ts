import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EducationalRecordMarineSuffix } from 'app/shared/model/educational-record-marine-suffix.model';
import { EducationalRecordMarineSuffixService } from './educational-record-marine-suffix.service';
import { EducationalRecordMarineSuffixComponent } from './educational-record-marine-suffix.component';
import { EducationalRecordMarineSuffixDetailComponent } from './educational-record-marine-suffix-detail.component';
import { EducationalRecordMarineSuffixUpdateComponent } from './educational-record-marine-suffix-update.component';
import { EducationalRecordMarineSuffixDeletePopupComponent } from './educational-record-marine-suffix-delete-dialog.component';
import { IEducationalRecordMarineSuffix } from 'app/shared/model/educational-record-marine-suffix.model';
import {JobRecordMarineSuffix} from "app/shared/model/job-record-marine-suffix.model";

@Injectable({ providedIn: 'root' })
export class EducationalRecordMarineSuffixResolve implements Resolve<IEducationalRecordMarineSuffix> {
    constructor(private service: EducationalRecordMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EducationalRecordMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EducationalRecordMarineSuffix>) => response.ok),
                map((educationalRecord: HttpResponse<EducationalRecordMarineSuffix>) => educationalRecord.body)
            );
        }
        const personGuid = route.params['personGuid'] ? route.params['personGuid'] : null;
        if(personGuid)
        {
            let newObject = new EducationalRecordMarineSuffix();
            newObject.personGuid = personGuid;
            return of(newObject);
        }
        else {
            return of(new EducationalRecordMarineSuffix());
        }
    }
}

export const educationalRecordRoute: Routes = [
    {
        path: 'educational-record-marine-suffix/list',
        component: EducationalRecordMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.educationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-record-marine-suffix/view/:id',
        component: EducationalRecordMarineSuffixDetailComponent,
        resolve: {
            educationalRecord: EducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-record-marine-suffix/new',
        component: EducationalRecordMarineSuffixUpdateComponent,
        resolve: {
            educationalRecord: EducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },{
        path: 'educational-record-marine-suffix/new/:personGuid',
        component: EducationalRecordMarineSuffixUpdateComponent,
        resolve: {
            educationalRecord: EducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'educational-record-marine-suffix/edit/:id',
        component: EducationalRecordMarineSuffixUpdateComponent,
        resolve: {
            educationalRecord: EducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const educationalRecordPopupRoute: Routes = [
    {
        path: 'educational-record-marine-suffix/delete/:id',
        component: EducationalRecordMarineSuffixDeletePopupComponent,
        resolve: {
            educationalRecord: EducationalRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.educationalRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
