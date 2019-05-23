import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';
import { InstructionMarineSuffixService } from './instruction-marine-suffix.service';
import { InstructionMarineSuffixComponent } from './instruction-marine-suffix.component';
import { InstructionMarineSuffixDetailComponent } from './instruction-marine-suffix-detail.component';
import { InstructionMarineSuffixUpdateComponent } from './instruction-marine-suffix-update.component';
import { InstructionMarineSuffixDeletePopupComponent } from './instruction-marine-suffix-delete-dialog.component';
import { IInstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class InstructionMarineSuffixResolve implements Resolve<IInstructionMarineSuffix> {
    constructor(private service: InstructionMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<InstructionMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InstructionMarineSuffix>) => response.ok),
                map((instruction: HttpResponse<InstructionMarineSuffix>) => instruction.body)
            );
        }
        return of(new InstructionMarineSuffix());
    }
}

export const instructionRoute: Routes = [
    {
        path: 'instruction-marine-suffix',
        component: InstructionMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.instruction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instruction-marine-suffix/:id/view',
        component: InstructionMarineSuffixDetailComponent,
        resolve: {
            instruction: InstructionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instruction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instruction-marine-suffix/new',
        component: InstructionMarineSuffixUpdateComponent,
        resolve: {
            instruction: InstructionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instruction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instruction-marine-suffix/:id/edit',
        component: InstructionMarineSuffixUpdateComponent,
        resolve: {
            instruction: InstructionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instruction.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const instructionPopupRoute: Routes = [
    {
        path: 'instruction-marine-suffix/:id/delete',
        component: InstructionMarineSuffixDeletePopupComponent,
        resolve: {
            instruction: InstructionMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instruction.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
