import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JobRecordMarineSuffix } from 'app/shared/model/job-record-marine-suffix.model';
import { JobRecordMarineSuffixService } from './job-record-marine-suffix.service';
import { JobRecordMarineSuffixComponent } from './job-record-marine-suffix.component';
import { JobRecordMarineSuffixDetailComponent } from './job-record-marine-suffix-detail.component';
import { JobRecordMarineSuffixUpdateComponent } from './job-record-marine-suffix-update.component';
import { JobRecordMarineSuffixDeletePopupComponent } from './job-record-marine-suffix-delete-dialog.component';
import { IJobRecordMarineSuffix } from 'app/shared/model/job-record-marine-suffix.model';
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Injectable({ providedIn: 'root' })
export class JobRecordMarineSuffixResolve implements Resolve<IJobRecordMarineSuffix> {
    constructor(private service: JobRecordMarineSuffixService,
        private personService: PersonMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JobRecordMarineSuffix> {

        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<JobRecordMarineSuffix>) => response.ok),
                map((jobRecord: HttpResponse<JobRecordMarineSuffix>) => jobRecord.body)
            );
        }
        const personGuid = route.params['personGuid'] ? route.params['personGuid'] : null;
        if(personGuid)
        {
            let newObject = new JobRecordMarineSuffix();
            newObject.personGuid = personGuid;
            return of(newObject);
        }
        else {
            return of(new JobRecordMarineSuffix());
        }
    }
}

export const jobRecordRoute: Routes = [
    {
        path: 'job-record-marine-suffix/list',
        component: JobRecordMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.jobRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-record-marine-suffix/view/:id',
        component: JobRecordMarineSuffixDetailComponent,
        resolve: {
            jobRecord: JobRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-record-marine-suffix/new',
        component: JobRecordMarineSuffixUpdateComponent,
        resolve: {
            jobRecord: JobRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },{
        path: 'job-record-marine-suffix/new/:personGuid',
        component: JobRecordMarineSuffixUpdateComponent,
        resolve: {
            jobRecord: JobRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-record-marine-suffix/edit/:id',
        component: JobRecordMarineSuffixUpdateComponent,
        resolve: {
            jobRecord: JobRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobRecordPopupRoute: Routes = [
    {
        path: 'job-record-marine-suffix/delete/:id',
        component: JobRecordMarineSuffixDeletePopupComponent,
        resolve: {
            jobRecord: JobRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.jobRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
