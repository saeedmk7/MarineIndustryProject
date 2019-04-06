import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { ScientificWorkGroupMarineSuffixService } from './scientific-work-group-marine-suffix.service';
import { ScientificWorkGroupMarineSuffixComponent } from './scientific-work-group-marine-suffix.component';
import { ScientificWorkGroupMarineSuffixDetailComponent } from './scientific-work-group-marine-suffix-detail.component';
import { ScientificWorkGroupMarineSuffixUpdateComponent } from './scientific-work-group-marine-suffix-update.component';
import { ScientificWorkGroupMarineSuffixDeletePopupComponent } from './scientific-work-group-marine-suffix-delete-dialog.component';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ScientificWorkGroupMarineSuffixResolve implements Resolve<IScientificWorkGroupMarineSuffix> {
    constructor(private service: ScientificWorkGroupMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((scientificWorkGroup: HttpResponse<ScientificWorkGroupMarineSuffix>) => scientificWorkGroup.body));
        }
        return of(new ScientificWorkGroupMarineSuffix());
    }
}

export const scientificWorkGroupRoute: Routes = [
    {
        path: 'scientific-work-group-marine-suffix',
        component: ScientificWorkGroupMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.scientificWorkGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scientific-work-group-marine-suffix/:id/view',
        component: ScientificWorkGroupMarineSuffixDetailComponent,
        resolve: {
            scientificWorkGroup: ScientificWorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scientificWorkGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scientific-work-group-marine-suffix/new',
        component: ScientificWorkGroupMarineSuffixUpdateComponent,
        resolve: {
            scientificWorkGroup: ScientificWorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scientificWorkGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scientific-work-group-marine-suffix/:id/edit',
        component: ScientificWorkGroupMarineSuffixUpdateComponent,
        resolve: {
            scientificWorkGroup: ScientificWorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scientificWorkGroup.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const scientificWorkGroupPopupRoute: Routes = [
    {
        path: 'scientific-work-group-marine-suffix/:id/delete',
        component: ScientificWorkGroupMarineSuffixDeletePopupComponent,
        resolve: {
            scientificWorkGroup: ScientificWorkGroupMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.scientificWorkGroup.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
