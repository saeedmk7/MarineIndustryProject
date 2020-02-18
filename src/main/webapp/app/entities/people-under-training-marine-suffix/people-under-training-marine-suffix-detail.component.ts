import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';

@Component({
    selector: 'mi-people-under-training-marine-suffix-detail',
    templateUrl: './people-under-training-marine-suffix-detail.component.html'
})
export class PeopleUnderTrainingMarineSuffixDetailComponent implements OnInit {
    peopleUnderTraining: IPeopleUnderTrainingMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ peopleUnderTraining }) => {
            this.peopleUnderTraining = peopleUnderTraining;
        });
    }

    previousState() {
        window.history.back();
    }
}
