import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { LevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';
import { LevelFourEffectivenessMarineSuffixService } from './level-four-effectiveness-marine-suffix.service';
import { LevelFourEffectivenessMarineSuffixComponent } from './level-four-effectiveness-marine-suffix.component';
import { LevelFourEffectivenessMarineSuffixDetailComponent } from './level-four-effectiveness-marine-suffix-detail.component';
import { LevelFourEffectivenessMarineSuffixUpdateComponent } from './level-four-effectiveness-marine-suffix-update.component';
import { LevelFourEffectivenessMarineSuffixDeletePopupComponent } from './level-four-effectiveness-marine-suffix-delete-dialog.component';
import { ILevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';

@Injectable({ providedIn: 'root' })
export class LevelFourEffectivenessMarineSuffixResolve implements Resolve<ILevelFourEffectivenessMarineSuffix> {
    constructor(private service: LevelFourEffectivenessMarineSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<LevelFourEffectivenessMarineSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LevelFourEffectivenessMarineSuffix>) => response.ok),
                map((levelFourEffectiveness: HttpResponse<LevelFourEffectivenessMarineSuffix>) => levelFourEffectiveness.body)
            );
        }
        const finalNiazsanjiReportPersonId = route.params['finalNiazsanjiReportPersonId'] ? route.params['finalNiazsanjiReportPersonId'] : null;
        if(finalNiazsanjiReportPersonId){
            let levelFourEffectiveness = new LevelFourEffectivenessMarineSuffix();
            levelFourEffectiveness.finalNiazsanjiReportPersonId = finalNiazsanjiReportPersonId;
            return of(levelFourEffectiveness);
        }
        return of(new LevelFourEffectivenessMarineSuffix());
    }
}

export const levelFourEffectivenessRoute: Routes = [
    /*{
        path: 'level-four-effectiveness-marine-suffix',
        component: LevelFourEffectivenessMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.levelFourEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    },*/
    {
        path: 'level-four-effectiveness-marine-suffix/:id/view',
        component: LevelFourEffectivenessMarineSuffixDetailComponent,
        resolve: {
            levelFourEffectiveness: LevelFourEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-effectiveness-marine-suffix/new/:finalNiazsanjiReportPersonId',
        component: LevelFourEffectivenessMarineSuffixUpdateComponent,
        resolve: {
            levelFourEffectiveness: LevelFourEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'level-four-effectiveness-marine-suffix/:id/edit',
        component: LevelFourEffectivenessMarineSuffixUpdateComponent,
        resolve: {
            levelFourEffectiveness: LevelFourEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const levelFourEffectivenessPopupRoute: Routes = [
    {
        path: 'level-four-effectiveness-marine-suffix/:id/delete',
        component: LevelFourEffectivenessMarineSuffixDeletePopupComponent,
        resolve: {
            levelFourEffectiveness: LevelFourEffectivenessMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.levelFourEffectiveness.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
