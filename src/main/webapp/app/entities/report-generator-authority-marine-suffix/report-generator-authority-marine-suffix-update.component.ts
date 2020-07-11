import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IReportGeneratorAuthorityMarineSuffix } from 'app/shared/model/report-generator-authority-marine-suffix.model';
import { ReportGeneratorAuthorityMarineSuffixService } from './report-generator-authority-marine-suffix.service';
import { IReportGeneratorMarineSuffix } from 'app/shared/model/report-generator-marine-suffix.model';
import { ReportGeneratorMarineSuffixService } from 'app/entities/report-generator-marine-suffix';

@Component({
    selector: 'mi-report-generator-authority-marine-suffix-update',
    templateUrl: './report-generator-authority-marine-suffix-update.component.html'
})
export class ReportGeneratorAuthorityMarineSuffixUpdateComponent implements OnInit {
    reportGeneratorAuthority: IReportGeneratorAuthorityMarineSuffix;
    isSaving: boolean;

    reportgenerators: IReportGeneratorMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected reportGeneratorAuthorityService: ReportGeneratorAuthorityMarineSuffixService,
        protected reportGeneratorService: ReportGeneratorMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ reportGeneratorAuthority }) => {
            this.reportGeneratorAuthority = reportGeneratorAuthority;
            this.createDate =
                this.reportGeneratorAuthority.createDate != null ? this.reportGeneratorAuthority.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.reportGeneratorAuthority.modifyDate != null ? this.reportGeneratorAuthority.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.reportGeneratorService.query().subscribe(
            (res: HttpResponse<IReportGeneratorMarineSuffix[]>) => {
                this.reportgenerators = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.reportGeneratorAuthority.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.reportGeneratorAuthority.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.reportGeneratorAuthority.id !== undefined) {
            this.subscribeToSaveResponse(this.reportGeneratorAuthorityService.update(this.reportGeneratorAuthority));
        } else {
            this.subscribeToSaveResponse(this.reportGeneratorAuthorityService.create(this.reportGeneratorAuthority));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportGeneratorAuthorityMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IReportGeneratorAuthorityMarineSuffix>) => this.onSaveSuccess(),
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

    trackReportGeneratorById(index: number, item: IReportGeneratorMarineSuffix) {
        return item.id;
    }
}
