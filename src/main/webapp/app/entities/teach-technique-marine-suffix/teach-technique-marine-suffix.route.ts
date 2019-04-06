import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TeachTechniqueMarineSuffix } from 'app/shared/model/teach-technique-marine-suffix.model';
import { TeachTechniqueMarineSuffixService } from './teach-technique-marine-suffix.service';
import { TeachTechniqueMarineSuffixComponent } from './teach-technique-marine-suffix.component';
import { TeachTechniqueMarineSuffixDetailComponent } from './teach-technique-marine-suffix-detail.component';
import { TeachTechniqueMarineSuffixUpdateComponent } from './teach-technique-marine-suffix-update.component';
import { TeachTechniqueMarineSuffixDeletePopupComponent } from './teach-technique-marine-suffix-delete-dialog.component';
import { ITeachTechniqueMarineSuffix } from 'app/shared/model/teach-technique-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class TeachTechniqueMarineSuffixResolve implements Resolve<ITeachTechniqueMarineSuffix> {
    constructor(private service: TeachTechniqueMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((teachTechnique: HttpResponse<TeachTechniqueMarineSuffix>) => teachTechnique.body));
        }
        return of(new TeachTechniqueMarineSuffix());
    }
}

export const teachTechniqueRoute: Routes = [
    {
        path: 'teach-technique-marine-suffix',
        component: TeachTechniqueMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.teachTechnique.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-technique-marine-suffix/:id/view',
        component: TeachTechniqueMarineSuffixDetailComponent,
        resolve: {
            teachTechnique: TeachTechniqueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachTechnique.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-technique-marine-suffix/new',
        component: TeachTechniqueMarineSuffixUpdateComponent,
        resolve: {
            teachTechnique: TeachTechniqueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachTechnique.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'teach-technique-marine-suffix/:id/edit',
        component: TeachTechniqueMarineSuffixUpdateComponent,
        resolve: {
            teachTechnique: TeachTechniqueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachTechnique.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const teachTechniquePopupRoute: Routes = [
    {
        path: 'teach-technique-marine-suffix/:id/delete',
        component: TeachTechniqueMarineSuffixDeletePopupComponent,
        resolve: {
            teachTechnique: TeachTechniqueMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.teachTechnique.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
