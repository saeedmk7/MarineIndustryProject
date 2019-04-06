import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPollMarineSuffix } from 'app/shared/model/poll-marine-suffix.model';

@Component({
    selector: 'mi-poll-marine-suffix-detail',
    templateUrl: './poll-marine-suffix-detail.component.html'
})
export class PollMarineSuffixDetailComponent implements OnInit {
    poll: IPollMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ poll }) => {
            this.poll = poll;
        });
    }

    previousState() {
        window.history.back();
    }
}
