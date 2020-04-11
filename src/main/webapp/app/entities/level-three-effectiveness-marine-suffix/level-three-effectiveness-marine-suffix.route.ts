import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LevelThreeEffectivenessMarineSuffix } from 'app/shared/model/level-three-effectiveness-marine-suffix.model';
import { LevelThreeEffectivenessMarineSuffixService } from './level-three-effectiveness-marine-suffix.service';
import { LevelThreeEffectivenessMarineSuffixDetailComponent } from './level-three-effectiveness-marine-suffix-detail.component';
import { LevelThreeEffectivenessMarineSuffixUpdateComponent } from './level-three-effectiveness-marine-suffix-update.component';
import { LevelThreeEffectivenessMarineSuffixDeletePopupComponent } from './level-three-effectiveness-marine-suffix-delete-dialog.component';
import { ILevelThreeEffectivenessMarineSuffix } from 'app/shared/model/level-three-effectiveness-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class LevelThreeEffectivenessMarineSuffixResolve implements Resolve<ILevelThreeEffectivenessMarineSuffix> {
    constructor(private service: LevelThreeEffectivenessMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LevelThreeEffectivenessMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LevelThreeEffectivenessMarineSuffix>) => response.ok),
                map((levelThreeEffectiveness: HttpResponse<LevelThreeEffectivenessMarineSuffix>) => levelThreeEffectiveness.body)
            );
        }
        const finalNiazsanjiReportPersonId = route.params['finalNiazsanjiReportPersonId'] ? route.params['finalNiazsanjiReportPersonId'] : null;
        if(finalNiazsanjiReportPersonId){
            let levelThreeEffectiveness = new LevelThreeEffectivenessMarineSuffix();
            levelThreeEffectiveness.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
            return of(levelThreeEffectiveness);
        }
        return of(new LevelThreeEffectivenessMarineSuffix());
    }
}

export const levelThreeEffectivenessRoute: Routes = [
    /*{
        path: 'level-three-effectiveness-marine-suffix',
        component: LevelThreeEffectivenessMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.levelThreeEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    },*/
    {
        path: 'level-three-effectiveness-marine-suffix/:id/view',
        component: LevelThreeEffectivenessMarineSuffixDetailComponent,
        resolve: {
            levelThreeEffectiveness: LevelThreeEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-effectiveness-marine-suffix/new/:finalNiazsanjiReportPersonId',
        component: LevelThreeEffectivenessMarineSuffixUpdateComponent,
        resolve: {
            levelThreeEffectiveness: LevelThreeEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-three-effectiveness-marine-suffix/:id/edit',
        component: LevelThreeEffectivenessMarineSuffixUpdateComponent,
        resolve: {
            levelThreeEffectiveness: LevelThreeEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelThreeEffectivenessPopupRoute: Routes = [
    {
        path: 'level-three-effectiveness-marine-suffix/:id/delete',
        component: LevelThreeEffectivenessMarineSuffixDeletePopupComponent,
        resolve: {
            levelThreeEffectiveness: LevelThreeEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelThreeEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
