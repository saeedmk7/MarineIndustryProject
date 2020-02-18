import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-soldier-marine-suffix-detail',
    templateUrl: './soldier-marine-suffix-detail.component.html'
})
export class SoldierMarineSuffixDetailComponent implements OnInit {
    soldier: ISoldierMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute, private convertObjectDatesService: ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soldier }) => {
            this.soldier = this.convertObjectDatesService.changeDate(soldier);
        });
    }

    previousState() {
        window.history.back();
    }
}
