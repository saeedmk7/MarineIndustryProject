import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { INiazsanjiFardiMarineSuffix } from 'app/shared/model/niazsanji-fardi-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-niazsanji-fardi-marine-suffix-detail',
    templateUrl: './niazsanji-fardi-marine-suffix-detail.component.html'
})
export class NiazsanjiFardiMarineSuffixDetailComponent implements OnInit {
    niazsanjiFardi: INiazsanjiFardiMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute, private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiFardi }) => {
            this.niazsanjiFardi = this.convertObjectDatesService.changeDate(niazsanjiFardi);
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
