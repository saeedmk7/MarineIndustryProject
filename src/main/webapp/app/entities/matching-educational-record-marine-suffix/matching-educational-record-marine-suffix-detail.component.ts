import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';

@Component({
    selector: 'mi-matching-educational-record-marine-suffix-detail',
    templateUrl: './matching-educational-record-marine-suffix-detail.component.html'
})
export class MatchingEducationalRecordMarineSuffixDetailComponent implements OnInit {
    matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matchingEducationalRecord }) => {
            this.matchingEducationalRecord = matchingEducationalRecord;
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
