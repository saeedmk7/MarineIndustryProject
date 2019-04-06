import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-scientific-work-group-marine-suffix-detail',
    templateUrl: './scientific-work-group-marine-suffix-detail.component.html'
})
export class ScientificWorkGroupMarineSuffixDetailComponent implements OnInit {
    scientificWorkGroup: IScientificWorkGroupMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ scientificWorkGroup }) => {
            this.scientificWorkGroup = this.convertObjectDatesService.changeDate(scientificWorkGroup);
        });
    }

    previousState() {
        window.history.back();
    }
}
