import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiPaginationUtil, JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {of} from 'rxjs';
import {map} from 'rxjs/operators';
import {DesignAndPlanningMarineSuffix} from 'app/shared/model/design-and-planning-marine-suffix.model';
import {DesignAndPlanningMarineSuffixService} from './design-and-planning-marine-suffix.service';
import {DesignAndPlanningMarineSuffixComponent} from './design-and-planning-marine-suffix.component';
import {DesignAndPlanningMarineSuffixDetailComponent} from './design-and-planning-marine-suffix-detail.component';
import {DesignAndPlanningMarineSuffixUpdateComponent} from './design-and-planning-marine-suffix-update.component';
import {DesignAndPlanningMarineSuffixDeletePopupComponent} from './design-and-planning-marine-suffix-delete-dialog.component';
import {IDesignAndPlanningMarineSuffix} from 'app/shared/model/design-and-planning-marine-suffix.model';
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Injectable({providedIn: 'root'})
export class DesignAndPlanningMarineSuffixResolve implements Resolve<IDesignAndPlanningMarineSuffix> {
    constructor(private service: DesignAndPlanningMarineSuffixService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        debugger;
        const id = route.params['id'] ? route.params['id'] : null;
        /*if (id) {
            let criteria = [{
                key: 'finalNiazsanjiReportId.equals',
                value: id
            }];
            this.service
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IDesignAndPlanningMarineSuffix[]>) => {
                    debugger;
                    if (resp.body.length > 0) {
                        return resp.body[0];
                    }
                    else {
                        let design: IDesignAndPlanningMarineSuffix = new DesignAndPlanningMarineSuffix();
                        design.finalNiazsanjiReportId = id;
                        return design;
                    }
                },
                (error) => {
                    debugger;
                    let design: IDesignAndPlanningMarineSuffix = new DesignAndPlanningMarineSuffix();
                    design.finalNiazsanjiReportId = id;
                    return design;
                });
        }
        else {*/
            debugger;
            let design: IDesignAndPlanningMarineSuffix = new DesignAndPlanningMarineSuffix();
            design.finalNiazsanjiReportId = id;
            return design;
        /*}*/
        //return of(new DesignAndPlanningMarineSuffix());
    }
}

export const designAndPlanningRoute: Routes = [
    {
        path: 'design-and-planning-marine-suffix',
        component: DesignAndPlanningMarineSuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'marineindustryprojApp.designAndPlanning.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'design-and-planning-marine-suffix/:id/view',
        component: DesignAndPlanningMarineSuffixDetailComponent,
        resolve: {
            designAndPlanning: DesignAndPlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designAndPlanning.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'design-and-planning-marine-suffix/new',
        component: DesignAndPlanningMarineSuffixUpdateComponent,
        resolve: {
            designAndPlanning: DesignAndPlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designAndPlanning.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'design-and-planning-marine-suffix/:id/edit',
        component: DesignAndPlanningMarineSuffixUpdateComponent,
        resolve: {
            designAndPlanning: DesignAndPlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designAndPlanning.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const designAndPlanningPopupRoute: Routes = [
    {
        path: 'design-and-planning-marine-suffix/:id/delete',
        component: DesignAndPlanningMarineSuffixDeletePopupComponent,
        resolve: {
            designAndPlanning: DesignAndPlanningMarineSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'marineindustryprojApp.designAndPlanning.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
