import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-niazsanji-group-marine-suffix-detail',
    templateUrl: './niazsanji-group-marine-suffix-detail.component.html',
    styleUrls: ['./niazsanji-group-marine-suffix.scss']
})
export class NiazsanjiGroupMarineSuffixDetailComponent implements OnInit {
    niazsanjiGroup: INiazsanjiGroupMarineSuffix;
    tSum: number = 0;
    pSum: number = 0;
    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiGroup }) => {
            this.niazsanjiGroup = this.convertObjectDatesService.changeDate(niazsanjiGroup);
            this.tSum = 0;
            this.pSum = 0;
            this.niazsanjiGroup.educationalModules.forEach(a => {
               this.tSum += a.learningTimeTheorical;
               this.pSum += a.learningTimePractical;
            });
        });
    }

    previousState() {
        window.history.back();
    }
}
