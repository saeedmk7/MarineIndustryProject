import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRasteMarineSuffix } from 'app/shared/model/raste-marine-suffix.model';

@Component({
    selector: 'mi-raste-marine-suffix-detail',
    templateUrl: './raste-marine-suffix-detail.component.html'
})
export class RasteMarineSuffixDetailComponent implements OnInit {
    raste: IRasteMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ raste }) => {
            this.raste = raste;
        });
    }

    previousState() {
        window.history.back();
    }
}
