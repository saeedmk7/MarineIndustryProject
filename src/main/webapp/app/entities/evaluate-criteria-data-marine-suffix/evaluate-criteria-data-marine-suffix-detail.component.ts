import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model/evaluate-criteria-data-marine-suffix.model';

@Component({
    selector: 'mi-evaluate-criteria-data-marine-suffix-detail',
    templateUrl: './evaluate-criteria-data-marine-suffix-detail.component.html'
})
export class EvaluateCriteriaDataMarineSuffixDetailComponent implements OnInit {
    evaluateCriteriaData: IEvaluateCriteriaDataMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluateCriteriaData }) => {
            this.evaluateCriteriaData = evaluateCriteriaData;
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
