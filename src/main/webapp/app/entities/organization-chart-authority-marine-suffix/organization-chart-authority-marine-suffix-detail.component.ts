import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizationChartAuthorityMarineSuffix } from 'app/shared/model/organization-chart-authority-marine-suffix.model';

@Component({
    selector: 'mi-organization-chart-authority-marine-suffix-detail',
    templateUrl: './organization-chart-authority-marine-suffix-detail.component.html'
})
export class OrganizationChartAuthorityMarineSuffixDetailComponent implements OnInit {
    organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organizationChartAuthority }) => {
            this.organizationChartAuthority = organizationChartAuthority;
        });
    }

    previousState() {
        window.history.back();
    }
}
