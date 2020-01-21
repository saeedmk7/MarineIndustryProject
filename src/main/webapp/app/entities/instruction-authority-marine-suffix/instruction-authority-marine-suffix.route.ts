import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { InstructionAuthorityMarineSuffix } from 'app/shared/model/instruction-authority-marine-suffix.model';
import { InstructionAuthorityMarineSuffixService } from './instruction-authority-marine-suffix.service';
import { InstructionAuthorityMarineSuffixComponent } from './instruction-authority-marine-suffix.component';
import { InstructionAuthorityMarineSuffixDetailComponent } from './instruction-authority-marine-suffix-detail.component';
import { InstructionAuthorityMarineSuffixUpdateComponent } from './instruction-authority-marine-suffix-update.component';
import { InstructionAuthorityMarineSuffixDeletePopupComponent } from './instruction-authority-marine-suffix-delete-dialog.component';
import { IInstructionAuthorityMarineSuffix } from 'app/shared/model/instruction-authority-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class InstructionAuthorityMarineSuffixResolve implements Resolve<IInstructionAuthorityMarineSuffix> {
    constructor(private service: InstructionAuthorityMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<InstructionAuthorityMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<InstructionAuthorityMarineSuffix>) => response.ok),
                map((instructionAuthority: HttpResponse<InstructionAuthorityMarineSuffix>) => instructionAuthority.body)
            );
        }
        return of(new InstructionAuthorityMarineSuffix());
    }
}

export const instructionAuthorityRoute: Routes = [
    {
        path: 'instruction-authority-marine-suffix',
        component: InstructionAuthorityMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.instructionAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instruction-authority-marine-suffix/:id/view',
        component: InstructionAuthorityMarineSuffixDetailComponent,
        resolve: {
            instructionAuthority: InstructionAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instructionAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instruction-authority-marine-suffix/new',
        component: InstructionAuthorityMarineSuffixUpdateComponent,
        resolve: {
            instructionAuthority: InstructionAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instructionAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instruction-authority-marine-suffix/:id/edit',
        component: InstructionAuthorityMarineSuffixUpdateComponent,
        resolve: {
            instructionAuthority: InstructionAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instructionAuthority.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const instructionAuthorityPopupRoute: Routes = [
    {
        path: 'instruction-authority-marine-suffix/:id/delete',
        component: InstructionAuthorityMarineSuffixDeletePopupComponent,
        resolve: {
            instructionAuthority: InstructionAuthorityMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.instructionAuthority.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
