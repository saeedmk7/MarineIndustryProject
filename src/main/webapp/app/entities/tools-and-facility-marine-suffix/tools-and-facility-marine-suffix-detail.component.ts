import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IToolsAndFacilityMarineSuffix } from 'app/shared/model/tools-and-facility-marine-suffix.model';

@Component({
    selector: 'mi-tools-and-facility-marine-suffix-detail',
    templateUrl: './tools-and-facility-marine-suffix-detail.component.html'
})
export class ToolsAndFacilityMarineSuffixDetailComponent implements OnInit {
    toolsAndFacility: IToolsAndFacilityMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ toolsAndFacility }) => {
            this.toolsAndFacility = toolsAndFacility;
        });
    }

    previousState() {
        window.history.back();
    }
}
