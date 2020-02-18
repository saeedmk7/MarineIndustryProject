import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';
import { PeopleUnderTrainingMarineSuffixService } from './people-under-training-marine-suffix.service';
import { PeopleUnderTrainingMarineSuffixComponent } from './people-under-training-marine-suffix.component';
import { PeopleUnderTrainingMarineSuffixDetailComponent } from './people-under-training-marine-suffix-detail.component';
import { PeopleUnderTrainingMarineSuffixUpdateComponent } from './people-under-training-marine-suffix-update.component';
import { PeopleUnderTrainingMarineSuffixDeletePopupComponent } from './people-under-training-marine-suffix-delete-dialog.component';
import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PeopleUnderTrainingMarineSuffixResolve implements Resolve<IPeopleUnderTrainingMarineSuffix> {
    constructor(private service: PeopleUnderTrainingMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PeopleUnderTrainingMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PeopleUnderTrainingMarineSuffix>) => response.ok),
                map((peopleUnderTraining: HttpResponse<PeopleUnderTrainingMarineSuffix>) => peopleUnderTraining.body)
            );
        }
        return of(new PeopleUnderTrainingMarineSuffix());
    }
}

export const peopleUnderTrainingRoute: Routes = [
    {
        path: 'people-under-training-marine-suffix',
        component: PeopleUnderTrainingMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.peopleUnderTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'people-under-training-marine-suffix/:id/view',
        component: PeopleUnderTrainingMarineSuffixDetailComponent,
        resolve: {
            peopleUnderTraining: PeopleUnderTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.peopleUnderTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'people-under-training-marine-suffix/new',
        component: PeopleUnderTrainingMarineSuffixUpdateComponent,
        resolve: {
            peopleUnderTraining: PeopleUnderTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.peopleUnderTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'people-under-training-marine-suffix/:id/edit',
        component: PeopleUnderTrainingMarineSuffixUpdateComponent,
        resolve: {
            peopleUnderTraining: PeopleUnderTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.peopleUnderTraining.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const peopleUnderTrainingPopupRoute: Routes = [
    {
        path: 'people-under-training-marine-suffix/:id/delete',
        component: PeopleUnderTrainingMarineSuffixDeletePopupComponent,
        resolve: {
            peopleUnderTraining: PeopleUnderTrainingMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.peopleUnderTraining.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
