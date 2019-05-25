import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {JhiAlertService} from "ng-jhipster";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {IFinalNiazsanjiReportPersonMarineSuffix} from "app/shared/model/final-niazsanji-report-person-marine-suffix.model";
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";

@Component({
    selector: 'mi-planning-marine-suffix-detail',
    templateUrl: './planning-marine-suffix-detail.component.html'
})
export class PlanningMarineSuffixDetailComponent implements OnInit {
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
