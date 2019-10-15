import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InvestToGroupTransactionMarineSuffix } from 'app/shared/model/invest-to-group-transaction-marine-suffix.model';
import { InvestToGroupTransactionMarineSuffixService } from './invest-to-group-transaction-marine-suffix.service';
import { InvestToGroupTransactionMarineSuffixComponent } from './invest-to-group-transaction-marine-suffix.component';
import { InvestToGroupTransactionMarineSuffixDetailComponent } from './invest-to-group-transaction-marine-suffix-detail.component';
import { InvestToGroupTransactionMarineSuffixUpdateComponent } from './invest-to-group-transaction-marine-suffix-update.component';
import { InvestToGroupTransactionMarineSuffixDeletePopupComponent } from './invest-to-group-transaction-marine-suffix-delete-dialog.component';
import { IInvestToGroupTransactionMarineSuffix } from 'app/shared/model/invest-to-group-transaction-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class InvestToGroupTransactionMarineSuffixResolve implements Resolve<IInvestToGroupTransactionMarineSuffix> {
    constructor(private service: InvestToGroupTransactionMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<InvestToGroupTransactionMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InvestToGroupTransactionMarineSuffix>) => response.ok),
                map((investToGroupTransaction: HttpResponse<InvestToGroupTransactionMarineSuffix>) => investToGroupTransaction.body)
            );
        }
        return of(new InvestToGroupTransactionMarineSuffix());
    }
}

export const investToGroupTransactionRoute: Routes = [
    {
        path: 'invest-to-group-transaction-marine-suffix',
        component: InvestToGroupTransactionMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.investToGroupTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invest-to-group-transaction-marine-suffix/:id/view',
        component: InvestToGroupTransactionMarineSuffixDetailComponent,
        resolve: {
            investToGroupTransaction: InvestToGroupTransactionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.investToGroupTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invest-to-group-transaction-marine-suffix/new',
        component: InvestToGroupTransactionMarineSuffixUpdateComponent,
        resolve: {
            investToGroupTransaction: InvestToGroupTransactionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.investToGroupTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'invest-to-group-transaction-marine-suffix/:id/edit',
        component: InvestToGroupTransactionMarineSuffixUpdateComponent,
        resolve: {
            investToGroupTransaction: InvestToGroupTransactionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.investToGroupTransaction.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const investToGroupTransactionPopupRoute: Routes = [
    {
        path: 'invest-to-group-transaction-marine-suffix/:id/delete',
        component: InvestToGroupTransactionMarineSuffixDeletePopupComponent,
        resolve: {
            investToGroupTransaction: InvestToGroupTransactionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.investToGroupTransaction.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
