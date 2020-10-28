import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model/media-awareness-report-marine-suffix.model';
import { MediaAwarenessReportMarineSuffixService } from './media-awareness-report-marine-suffix.service';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { IMediaProductTypeMarineSuffix } from 'app/shared/model/media-product-type-marine-suffix.model';
import { MediaProductTypeMarineSuffixService } from 'app/entities/media-product-type-marine-suffix';
import { Principal } from 'app/core';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import * as persianMoment from 'jalali-moment';

@Component({
    selector: 'mi-media-awareness-report-marine-suffix-update',
    templateUrl: './media-awareness-report-marine-suffix-update.component.html'
})
export class MediaAwarenessReportMarineSuffixUpdateComponent implements OnInit {
    mediaAwarenessReport: IMediaAwarenessReportMarineSuffix;
    isSaving: boolean;
    currentPerson: IPersonMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];

    mediaproducttypes: IMediaProductTypeMarineSuffix[];

    recommenedOrgCharts: IOrganizationChartMarineSuffix[];

    finishDateValidation: number;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    currentAccount: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected mediaAwarenessReportService: MediaAwarenessReportMarineSuffixService,
        private personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        private principal: Principal,
        protected mediaProductTypeService: MediaProductTypeMarineSuffixService,
        private treeUtilities: TreeUtilities,
        protected activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mediaAwarenessReport }) => {
            this.mediaAwarenessReport = mediaAwarenessReport;
        });
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
            this.personService.find(this.currentAccount.personId).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;
                    this.loadOrgCharts();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });

        /* this.organizationChartService.query().subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                this.organizationcharts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
        this.mediaProductTypeService.query().subscribe(
            (res: HttpResponse<IMediaProductTypeMarineSuffix[]>) => {
                this.mediaproducttypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadOrgCharts() {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.handleOrgChartView();
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.handleOrgChartView();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    checkDateValidation(event) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.finishDateValidation = 1;
            } else {
                this.finishDateValidation = 2;
            }
        } catch (e) {
            this.finishDateValidation = 2;
        }
    }
    change(i) {
        //this.router.navigateByUrl(i);
        this.router.navigateByUrl(i);
    }
    handleOrgChartView() {
        if (this.isSuperUsers) {
            this.recommenedOrgCharts = this.organizationcharts;
            return;
        }
        if (this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
            let orgIds = this.treeUtilities
                .getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId)
                .filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
        } else {
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
        }
    }
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.loadPersonHour();
        if (this.mediaAwarenessReport.id !== undefined) {
            this.subscribeToSaveResponse(this.mediaAwarenessReportService.update(this.mediaAwarenessReport));
        } else {
            this.subscribeToSaveResponse(this.mediaAwarenessReportService.create(this.mediaAwarenessReport));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMediaAwarenessReportMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMediaAwarenessReportMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }

    trackMediaProductTypeById(index: number, item: IMediaProductTypeMarineSuffix) {
        return item.id;
    }

    loadPersonHour() {
        this.mediaAwarenessReport.personHour = Number(
            (
                (this.mediaAwarenessReport.reportTime ? this.mediaAwarenessReport.reportTime : 1) *
                (this.mediaAwarenessReport.numberOfViewers ? this.mediaAwarenessReport.numberOfViewers : 1) *
                (this.mediaAwarenessReport.durationOfOperation ? this.mediaAwarenessReport.durationOfOperation : 1) /
                60
            ).toFixed(1)
        );
    }
}
