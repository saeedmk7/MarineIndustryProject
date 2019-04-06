import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from './person-marine-suffix.service';
import { PersonMarineSuffixComponent } from './person-marine-suffix.component';
import { PersonMarineSuffixDetailComponent } from './person-marine-suffix-detail.component';
import { PersonMarineSuffixUpdateComponent } from './person-marine-suffix-update.component';
import { PersonMarineSuffixDeletePopupComponent } from './person-marine-suffix-delete-dialog.component';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class PersonMarineSuffixResolve implements Resolve<IPersonMarineSuffix> {
    constructor(private service: PersonMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((person: HttpResponse<PersonMarineSuffix>) => person.body));
        }
        return of(new PersonMarineSuffix());
    }
}

export const personRoute: Routes = [
    {
        path: 'person-marine-suffix',
        component: PersonMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.person.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'person-marine-suffix/:id/view',
        component: PersonMarineSuffixDetailComponent,
        resolve: {
            person: PersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.person.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'person-marine-suffix/new',
        component: PersonMarineSuffixUpdateComponent,
        resolve: {
            person: PersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.person.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'person-marine-suffix/:id/edit',
        component: PersonMarineSuffixUpdateComponent,
        resolve: {
            person: PersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.person.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const personPopupRoute: Routes = [
    {
        path: 'person-marine-suffix/:id/delete',
        component: PersonMarineSuffixDeletePopupComponent,
        resolve: {
            person: PersonMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.person.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
