import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRadehMarineSuffix } from 'app/shared/model/radeh-marine-suffix.model';

@Component({
    selector: 'mi-radeh-marine-suffix-detail',
    templateUrl: './radeh-marine-suffix-detail.component.html'
})
export class RadehMarineSuffixDetailComponent implements OnInit {
    radeh: IRadehMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radeh }) => {
            this.radeh = radeh;
        });
    }

    previousState() {
        window.history.back();
    }
}
