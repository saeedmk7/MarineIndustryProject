import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { SkillableLevelOfSkillMarineSuffixService } from './skillable-level-of-skill-marine-suffix.service';
import { SkillableLevelOfSkillMarineSuffixComponent } from './skillable-level-of-skill-marine-suffix.component';
import { SkillableLevelOfSkillMarineSuffixDetailComponent } from './skillable-level-of-skill-marine-suffix-detail.component';
import { SkillableLevelOfSkillMarineSuffixUpdateComponent } from './skillable-level-of-skill-marine-suffix-update.component';
import { SkillableLevelOfSkillMarineSuffixDeletePopupComponent } from './skillable-level-of-skill-marine-suffix-delete-dialog.component';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class SkillableLevelOfSkillMarineSuffixResolve implements Resolve<ISkillableLevelOfSkillMarineSuffix> {
    constructor(private service: SkillableLevelOfSkillMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((skillableLevelOfSkill: HttpResponse<SkillableLevelOfSkillMarineSuffix>) => skillableLevelOfSkill.body));
        }
        return of(new SkillableLevelOfSkillMarineSuffix());
    }
}

export const skillableLevelOfSkillRoute: Routes = [
    {
        path: 'skillable-level-of-skill-marine-suffix',
        component: SkillableLevelOfSkillMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.skillableLevelOfSkill.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'skillable-level-of-skill-marine-suffix/:id/view',
        component: SkillableLevelOfSkillMarineSuffixDetailComponent,
        resolve: {
            skillableLevelOfSkill: SkillableLevelOfSkillMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.skillableLevelOfSkill.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'skillable-level-of-skill-marine-suffix/new',
        component: SkillableLevelOfSkillMarineSuffixUpdateComponent,
        resolve: {
            skillableLevelOfSkill: SkillableLevelOfSkillMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.skillableLevelOfSkill.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'skillable-level-of-skill-marine-suffix/:id/edit',
        component: SkillableLevelOfSkillMarineSuffixUpdateComponent,
        resolve: {
            skillableLevelOfSkill: SkillableLevelOfSkillMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.skillableLevelOfSkill.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const skillableLevelOfSkillPopupRoute: Routes = [
    {
        path: 'skillable-level-of-skill-marine-suffix/:id/delete',
        component: SkillableLevelOfSkillMarineSuffixDeletePopupComponent,
        resolve: {
            skillableLevelOfSkill: SkillableLevelOfSkillMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.skillableLevelOfSkill.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
