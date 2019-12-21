import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';

@Component({
    selector: 'mi-restriction-marine-suffix-detail',
    templateUrl: './restriction-marine-suffix-detail.component.html'
})
export class RestrictionMarineSuffixDetailComponent implements OnInit {
    restriction: IRestrictionMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ restriction }) => {
            this.restriction = restriction;
        });
    }

    previousState() {
        window.history.back();
    }
}
