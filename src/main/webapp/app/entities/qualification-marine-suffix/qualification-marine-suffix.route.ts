import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { QualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from './qualification-marine-suffix.service';
import { QualificationMarineSuffixComponent } from './qualification-marine-suffix.component';
import { QualificationMarineSuffixDetailComponent } from './qualification-marine-suffix-detail.component';
import { QualificationMarineSuffixUpdateComponent } from './qualification-marine-suffix-update.component';
import { QualificationMarineSuffixDeletePopupComponent } from './qualification-marine-suffix-delete-dialog.component';
import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class QualificationMarineSuffixResolve implements Resolve<IQualificationMarineSuffix> {
    constructor(private service: QualificationMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((qualification: HttpResponse<QualificationMarineSuffix>) => qualification.body));
        }
        return of(new QualificationMarineSuffix());
    }
}

export const qualificationRoute: Routes = [
    {
        path: 'qualification-marine-suffix',
        component: QualificationMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.qualification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'qualification-marine-suffix/:id/view',
        component: QualificationMarineSuffixDetailComponent,
        resolve: {
            qualification: QualificationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.qualification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'qualification-marine-suffix/new',
        component: QualificationMarineSuffixUpdateComponent,
        resolve: {
            qualification: QualificationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.qualification.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'qualification-marine-suffix/:id/edit',
        component: QualificationMarineSuffixUpdateComponent,
        resolve: {
            qualification: QualificationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.qualification.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const qualificationPopupRoute: Routes = [
    {
        path: 'qualification-marine-suffix/:id/delete',
        component: QualificationMarineSuffixDeletePopupComponent,
        resolve: {
            qualification: QualificationMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.qualification.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
