import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model/media-awareness-report-marine-suffix.model';

@Component({
    selector: 'mi-media-awareness-report-marine-suffix-detail',
    templateUrl: './media-awareness-report-marine-suffix-detail.component.html'
})
export class MediaAwarenessReportMarineSuffixDetailComponent implements OnInit {
    mediaAwarenessReport: IMediaAwarenessReportMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mediaAwarenessReport }) => {
            this.mediaAwarenessReport = mediaAwarenessReport;
        });
    }

    previousState() {
        window.history.back();
    }
}
