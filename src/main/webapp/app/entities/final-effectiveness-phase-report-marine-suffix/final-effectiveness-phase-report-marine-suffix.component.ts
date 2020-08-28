import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { IFinalEffectivenessPhaseReportModel } from 'app/shared/model/custom/effectivenessPhaseModels/final-effectiveness-phase-report-model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { SessionStorageService } from 'ngx-webstorage';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import * as moment from 'jalali-moment';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'mi-final-effectiveness-phase-report-marine-suffix-detail-marine-suffix',
    templateUrl: './final-effectiveness-phase-report-marine-suffix.component.html'
})
export class FinalEffectivenessPhaseReportMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    finalEffectivenessPhaseReportModels: IFinalEffectivenessPhaseReportModel[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    selectedNiazsanjiYear: number;
    years: any[];

    constructor(
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private $sessionStorage: SessionStorageService,
        private parseLinks: JhiParseLinks,
        private jhiTranslate: TranslateService,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute
    ) {
        this.years = GREGORIAN_START_END_DATE.map(a => a.year);
        this.selectedNiazsanjiYear = +moment().format('jYYYY');
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(
            this.finalEffectivenessPhaseReportModels,
            'finalEffectivenessPhaseReportModels',
            'marineindustryprojApp.finalEffectivenessPhaseReportModel'
        );
    }

    loadAll(reportYear: number) {
        this.finalNiazsanjiReportService.getFinalEffectivenessPhaseReport(reportYear).subscribe(
            (resp: HttpResponse<IFinalEffectivenessPhaseReportModel[]>) => {
                this.finalEffectivenessPhaseReportModels = resp.body;
                this.$sessionStorage.store('finalEffectivenessPhaseReportData', this.finalEffectivenessPhaseReportModels);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll(this.selectedNiazsanjiYear);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    ngOnDestroy(): void {}
}
