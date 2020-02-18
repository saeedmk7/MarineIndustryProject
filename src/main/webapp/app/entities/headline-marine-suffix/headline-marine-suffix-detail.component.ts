import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHeadlineMarineSuffix } from 'app/shared/model/headline-marine-suffix.model';

@Component({
    selector: 'mi-headline-marine-suffix-detail',
    templateUrl: './headline-marine-suffix-detail.component.html'
})
export class HeadlineMarineSuffixDetailComponent implements OnInit {
    headline: IHeadlineMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ headline }) => {
            this.headline = headline;
        });
    }

    previousState() {
        window.history.back();
    }
}
