import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-final-organization-niazsanji-marine-suffix-detail',
    templateUrl: './final-organization-niazsanji-marine-suffix-detail.component.html'
})
export class FinalOrganizationNiazsanjiMarineSuffixDetailComponent implements OnInit {
    finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalOrganizationNiazsanji }) => {
            this.finalOrganizationNiazsanji = this.convertObjectDatesService.changeDate(finalOrganizationNiazsanji);
        });
    }

    previousState() {
        window.history.back();
    }
}
