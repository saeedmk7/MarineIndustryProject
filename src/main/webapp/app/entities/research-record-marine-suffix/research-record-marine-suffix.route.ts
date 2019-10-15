import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ResearchRecordMarineSuffix } from 'app/shared/model/research-record-marine-suffix.model';
import { ResearchRecordMarineSuffixService } from './research-record-marine-suffix.service';
import { ResearchRecordMarineSuffixComponent } from './research-record-marine-suffix.component';
import { ResearchRecordMarineSuffixDetailComponent } from './research-record-marine-suffix-detail.component';
import { ResearchRecordMarineSuffixUpdateComponent } from './research-record-marine-suffix-update.component';
import { ResearchRecordMarineSuffixDeletePopupComponent } from './research-record-marine-suffix-delete-dialog.component';
import { IResearchRecordMarineSuffix } from 'app/shared/model/research-record-marine-suffix.model';
import {EducationalRecordMarineSuffix} from "app/shared/model/educational-record-marine-suffix.model";

@Injectable({ providedIn: 'root' })
export class ResearchRecordMarineSuffixResolve implements Resolve<IResearchRecordMarineSuffix> {
    constructor(private service: ResearchRecordMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ResearchRecordMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ResearchRecordMarineSuffix>) => response.ok),
                map((researchRecord: HttpResponse<ResearchRecordMarineSuffix>) => researchRecord.body)
            );
        }
        const personGuid = route.params['personGuid'] ? route.params['personGuid'] : null;
        if(personGuid)
        {
            let newObject = new ResearchRecordMarineSuffix();
            newObject.personGuid = personGuid;
            return of(newObject);
        }
        else {
            return of(new ResearchRecordMarineSuffix());
        }

    }
}

export const researchRecordRoute: Routes = [
    {
        path: 'research-record-marine-suffix/list',
        component: ResearchRecordMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.researchRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'research-record-marine-suffix/view/:id',
        component: ResearchRecordMarineSuffixDetailComponent,
        resolve: {
            researchRecord: ResearchRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.researchRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'research-record-marine-suffix/new',
        component: ResearchRecordMarineSuffixUpdateComponent,
        resolve: {
            researchRecord: ResearchRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.researchRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'research-record-marine-suffix/new/:personGuid',
        component: ResearchRecordMarineSuffixUpdateComponent,
        resolve: {
            researchRecord: ResearchRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.researchRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'research-record-marine-suffix/edit/:id',
        component: ResearchRecordMarineSuffixUpdateComponent,
        resolve: {
            researchRecord: ResearchRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.researchRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const researchRecordPopupRoute: Routes = [
    {
        path: 'research-record-marine-suffix/delete/:id',
        component: ResearchRecordMarineSuffixDeletePopupComponent,
        resolve: {
            researchRecord: ResearchRecordMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.researchRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
