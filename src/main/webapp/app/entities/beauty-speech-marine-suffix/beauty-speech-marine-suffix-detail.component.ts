import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-beauty-speech-marine-suffix-detail',
    templateUrl: './beauty-speech-marine-suffix-detail.component.html'
})
export class BeautySpeechMarineSuffixDetailComponent implements OnInit {
    beautySpeech: IBeautySpeechMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ beautySpeech }) => {
            this.beautySpeech = this.convertObjectDatesService.changeDate(beautySpeech);
        });
    }

    previousState() {
        window.history.back();
    }
}
