import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';

@Component({
    selector: 'mi-organization-marine-suffix-detail',
    templateUrl: './organization-marine-suffix-detail.component.html'
})
export class OrganizationMarineSuffixDetailComponent implements OnInit {
    organization: IOrganizationMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organization }) => {
            this.organization = organization;
        });
    }

    previousState() {
        window.history.back();
    }
}
