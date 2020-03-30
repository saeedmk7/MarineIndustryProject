import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';

@Component({
    selector: 'mi-final-niazsanji-report-marine-suffix-detail',
    templateUrl: './final-niazsanji-effectiveness-phase-marine-suffix-detail.component.html'
})
export class FinalNiazsanjiReportMarineSuffixDetailComponent implements OnInit {
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReport }) => {
            this.finalNiazsanjiReport = finalNiazsanjiReport;
        });
    }

    previousState() {
        window.history.back();
    }
}
