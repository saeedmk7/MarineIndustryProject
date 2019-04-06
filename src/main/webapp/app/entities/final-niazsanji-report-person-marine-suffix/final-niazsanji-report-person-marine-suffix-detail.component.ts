import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-final-niazsanji-report-person-marine-suffix-detail',
    templateUrl: './final-niazsanji-report-person-marine-suffix-detail.component.html'
})
export class FinalNiazsanjiReportPersonMarineSuffixDetailComponent implements OnInit {
    finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReportPerson }) => {
            this.finalNiazsanjiReportPerson = this.convertObjectDatesService.changeDate(finalNiazsanjiReportPerson);
        });
    }

    previousState() {
        window.history.back();
    }
}
