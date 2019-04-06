import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPollItemMarineSuffix } from 'app/shared/model/poll-item-marine-suffix.model';

@Component({
    selector: 'mi-poll-item-marine-suffix-detail',
    templateUrl: './poll-item-marine-suffix-detail.component.html'
})
export class PollItemMarineSuffixDetailComponent implements OnInit {
    pollItem: IPollItemMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pollItem }) => {
            this.pollItem = pollItem;
        });
    }

    previousState() {
        window.history.back();
    }
}
