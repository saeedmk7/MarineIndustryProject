import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeachTypeMarineSuffix } from 'app/shared/model/teach-type-marine-suffix.model';

@Component({
    selector: 'mi-teach-type-marine-suffix-detail',
    templateUrl: './teach-type-marine-suffix-detail.component.html'
})
export class TeachTypeMarineSuffixDetailComponent implements OnInit {
    teachType: ITeachTypeMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachType }) => {
            this.teachType = teachType;
        });
    }

    previousState() {
        window.history.back();
    }
}
