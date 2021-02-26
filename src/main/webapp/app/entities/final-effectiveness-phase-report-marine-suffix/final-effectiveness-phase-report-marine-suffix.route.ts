import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { FinalEffectivenessPhaseReportMarineSuffixComponent } from './final-effectiveness-phase-report-marine-suffix.component';
import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { FinalEffectivenessPhaseReportMarineSuffixDetailPopupComponent } from './final-effectiveness-phase-report-marine-suffix-detail-dialog.component';
import {
    FinalEffectivenessPhaseReportModel,
    IFinalEffectivenessPhaseReportModel
} from 'app/shared/model/custom/effectivenessPhaseModels/final-effectiveness-phase-report-model';
import {
    DetailFinalEffectivenessPhaseReportModel,
    IDetailFinalEffectivenessPhaseReportModel
} from 'app/shared/model/custom/effectivenessPhaseModels/detail-final-effectiveness-phase-report-model';
import { SessionStorageService } from 'ngx-webstorage';

@Injectable({ providedIn: 'root' })
export class FinalEffectivenessPhaseReportResolve implements Resolve<IDetailFinalEffectivenessPhaseReportModel[]> {
    constructor(private $sessionStorage: SessionStorageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            const finalEffectivenessPhaseReportDatas: FinalEffectivenessPhaseReportModel[] = this.$sessionStorage.retrieve(
                'finalEffectivenessPhaseReportData'
            );

            const finalEffectivenessPhaseReportData: FinalEffectivenessPhaseReportModel = finalEffectivenessPhaseReportDatas.find(
                w => w.levelId == id
            );

            return finalEffectivenessPhaseReportData.detailFinalEffectivenessPhaseReportModels;
        }
        return null;
    }
}

export const finalEffectivenessPhaseReportRoute: Routes = [
    {
        path: 'final-effectiveness-phase-report-marine-suffix',
        component: FinalEffectivenessPhaseReportMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.finalEffectivenessPhaseReportModel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const finalEffectivenessPhaseReportPopupRoute: Routes = [
    {
        path: 'final-effectiveness-phase-report-marine-suffix/:id/detail',
        component: FinalEffectivenessPhaseReportMarineSuffixDetailPopupComponent,
        resolve: {
            detailFinalEffectivenessPhaseReportModels: FinalEffectivenessPhaseReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.employmentType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
