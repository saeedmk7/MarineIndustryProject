import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICapitationMarineSuffix } from 'app/shared/model/capitation-marine-suffix.model';

@Component({
    selector: 'mi-capitation-marine-suffix-detail',
    templateUrl: './capitation-marine-suffix-detail.component.html'
})
export class CapitationMarineSuffixDetailComponent implements OnInit {
    capitation: ICapitationMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ capitation }) => {
            this.capitation = capitation;
        });
    }

    previousState() {
        window.history.back();
    }
}
