import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';

@Component({
    selector: 'mi-organization-chart-marine-suffix-detail',
    templateUrl: './organization-chart-marine-suffix-detail.component.html'
})
export class OrganizationChartMarineSuffixDetailComponent implements OnInit {
    organizationChart: IOrganizationChartMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organizationChart }) => {
            this.organizationChart = organizationChart;
        });
    }

    previousState() {
        window.history.back();
    }
}
