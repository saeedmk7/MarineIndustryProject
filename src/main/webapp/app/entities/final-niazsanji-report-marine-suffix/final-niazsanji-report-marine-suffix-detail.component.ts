import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {JhiAlertService} from "ng-jhipster";

@Component({
    selector: 'mi-final-niazsanji-report-marine-suffix-detail',
    templateUrl: './final-niazsanji-report-marine-suffix-detail.component.html'
})
export class FinalNiazsanjiReportMarineSuffixDetailComponent implements OnInit {
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
    finalNiazsanjiReportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private jhiAlertService: JhiAlertService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReport }) => {
            this.finalNiazsanjiReport = this.convertObjectDatesService.changeDate(finalNiazsanjiReport);
            let criteria = [{
                key: "finalNiazsanjiReportId.equals",
                value: this.finalNiazsanjiReport.id
            }];
            this.finalNiazsanjiReportPersonService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id","asc"]
            })
                .subscribe(
                    (res: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => this.finalNiazsanjiReportPeople = res.body,
                    (res: HttpErrorResponse) => this.onError(res.message)
                );

        });
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    previousState() {
        window.history.back();
    }
}
