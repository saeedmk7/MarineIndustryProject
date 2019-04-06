import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ToolsAndFacilityMarineSuffix } from 'app/shared/model/tools-and-facility-marine-suffix.model';
import { ToolsAndFacilityMarineSuffixService } from './tools-and-facility-marine-suffix.service';
import { ToolsAndFacilityMarineSuffixComponent } from './tools-and-facility-marine-suffix.component';
import { ToolsAndFacilityMarineSuffixDetailComponent } from './tools-and-facility-marine-suffix-detail.component';
import { ToolsAndFacilityMarineSuffixUpdateComponent } from './tools-and-facility-marine-suffix-update.component';
import { ToolsAndFacilityMarineSuffixDeletePopupComponent } from './tools-and-facility-marine-suffix-delete-dialog.component';
import { IToolsAndFacilityMarineSuffix } from 'app/shared/model/tools-and-facility-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class ToolsAndFacilityMarineSuffixResolve implements Resolve<IToolsAndFacilityMarineSuffix> {
    constructor(private service: ToolsAndFacilityMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((toolsAndFacility: HttpResponse<ToolsAndFacilityMarineSuffix>) => toolsAndFacility.body));
        }
        return of(new ToolsAndFacilityMarineSuffix());
    }
}

export const toolsAndFacilityRoute: Routes = [
    {
        path: 'tools-and-facility-marine-suffix',
        component: ToolsAndFacilityMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.toolsAndFacility.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tools-and-facility-marine-suffix/:id/view',
        component: ToolsAndFacilityMarineSuffixDetailComponent,
        resolve: {
            toolsAndFacility: ToolsAndFacilityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.toolsAndFacility.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tools-and-facility-marine-suffix/new',
        component: ToolsAndFacilityMarineSuffixUpdateComponent,
        resolve: {
            toolsAndFacility: ToolsAndFacilityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.toolsAndFacility.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tools-and-facility-marine-suffix/:id/edit',
        component: ToolsAndFacilityMarineSuffixUpdateComponent,
        resolve: {
            toolsAndFacility: ToolsAndFacilityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.toolsAndFacility.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const toolsAndFacilityPopupRoute: Routes = [
    {
        path: 'tools-and-facility-marine-suffix/:id/delete',
        component: ToolsAndFacilityMarineSuffixDeletePopupComponent,
        resolve: {
            toolsAndFacility: ToolsAndFacilityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.toolsAndFacility.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
