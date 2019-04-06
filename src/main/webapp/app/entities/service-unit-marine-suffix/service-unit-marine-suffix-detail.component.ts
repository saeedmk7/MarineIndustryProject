import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';

@Component({
    selector: 'mi-service-unit-marine-suffix-detail',
    templateUrl: './service-unit-marine-suffix-detail.component.html'
})
export class ServiceUnitMarineSuffixDetailComponent implements OnInit {
    serviceUnit: IServiceUnitMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ serviceUnit }) => {
            this.serviceUnit = serviceUnit;
        });
    }

    previousState() {
        window.history.back();
    }
}
