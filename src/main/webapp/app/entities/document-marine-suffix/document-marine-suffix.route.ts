import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from './document-marine-suffix.service';
import { DocumentMarineSuffixComponent } from './document-marine-suffix.component';
import { DocumentMarineSuffixDetailComponent } from './document-marine-suffix-detail.component';
import { DocumentMarineSuffixUpdateComponent } from './document-marine-suffix-update.component';
import { DocumentMarineSuffixDeletePopupComponent } from './document-marine-suffix-delete-dialog.component';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class DocumentMarineSuffixResolve implements Resolve<IDocumentMarineSuffix> {
    constructor(private service: DocumentMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((document: HttpResponse<DocumentMarineSuffix>) => document.body));
        }
        return of(new DocumentMarineSuffix());
    }
}

export const documentRoute: Routes = [
    {
        path: 'document-marine-suffix/:entityName/:entityId',
        component: DocumentMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.document.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'document-marine-suffix/:id/view',
        component: DocumentMarineSuffixDetailComponent,
        resolve: {
            document: DocumentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.document.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'document-marine-suffix/new/:entityName/:entityId',
        component: DocumentMarineSuffixUpdateComponent,
        resolve: {
            document: DocumentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.document.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'document-marine-suffix/:id/edit',
        component: DocumentMarineSuffixUpdateComponent,
        resolve: {
            document: DocumentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.document.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const documentPopupRoute: Routes = [
    {
        path: 'document-marine-suffix/:id/:entityName/delete',
        component: DocumentMarineSuffixDeletePopupComponent,
        resolve: {
            document: DocumentMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.document.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
