import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISoldierMediaAwarenessReportMarineSuffix } from 'app/shared/model/soldier-media-awareness-report-marine-suffix.model';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';

@Component({
    selector: 'mi-soldier-media-awareness-report-marine-suffix-detail',
    templateUrl: './soldier-media-awareness-report-marine-suffix-detail.component.html'
})
export class SoldierMediaAwarenessReportMarineSuffixDetailComponent implements OnInit {
    soldierMediaAwarenessReport: ISoldierMediaAwarenessReportMarineSuffix;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected activatedRoute: ActivatedRoute,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soldierMediaAwarenessReport }) => {
            this.soldierMediaAwarenessReport = this.convertObjectDatesService.changeDate(soldierMediaAwarenessReport);
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
