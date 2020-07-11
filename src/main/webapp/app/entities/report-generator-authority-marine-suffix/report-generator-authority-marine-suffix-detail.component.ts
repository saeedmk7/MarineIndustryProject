import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReportGeneratorAuthorityMarineSuffix } from 'app/shared/model/report-generator-authority-marine-suffix.model';

@Component({
    selector: 'mi-report-generator-authority-marine-suffix-detail',
    templateUrl: './report-generator-authority-marine-suffix-detail.component.html'
})
export class ReportGeneratorAuthorityMarineSuffixDetailComponent implements OnInit {
    reportGeneratorAuthority: IReportGeneratorAuthorityMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reportGeneratorAuthority }) => {
            this.reportGeneratorAuthority = reportGeneratorAuthority;
        });
    }

    previousState() {
        window.history.back();
    }
}
