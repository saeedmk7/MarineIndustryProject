import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-soldier-training-report-marine-suffix-detail',
    templateUrl: './soldier-training-report-marine-suffix-detail.component.html'
})
export class SoldierTrainingReportMarineSuffixDetailComponent implements OnInit {
    soldierTrainingReport: ISoldierTrainingReportMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute, private convertObjectDatesService: ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soldierTrainingReport }) => {
            this.soldierTrainingReport = this.convertObjectDatesService.changeDate(soldierTrainingReport);
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
