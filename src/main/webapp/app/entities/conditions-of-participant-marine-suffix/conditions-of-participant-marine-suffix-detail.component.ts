import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConditionsOfParticipantMarineSuffix } from 'app/shared/model/conditions-of-participant-marine-suffix.model';

@Component({
    selector: 'mi-conditions-of-participant-marine-suffix-detail',
    templateUrl: './conditions-of-participant-marine-suffix-detail.component.html'
})
export class ConditionsOfParticipantMarineSuffixDetailComponent implements OnInit {
    conditionsOfParticipant: IConditionsOfParticipantMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conditionsOfParticipant }) => {
            this.conditionsOfParticipant = conditionsOfParticipant;
        });
    }

    previousState() {
        window.history.back();
    }
}
