import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResearchRecordMarineSuffix } from 'app/shared/model/research-record-marine-suffix.model';

@Component({
    selector: 'mi-research-record-marine-suffix-detail',
    templateUrl: './research-record-marine-suffix-detail.component.html'
})
export class ResearchRecordMarineSuffixDetailComponent implements OnInit {
    researchRecord: IResearchRecordMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ researchRecord }) => {
            this.researchRecord = researchRecord;
        });
    }

    previousState() {
        window.history.back();
    }
}
