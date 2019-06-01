import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IJamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';

@Component({
    selector: 'mi-jam-help-marine-suffix-detail',
    templateUrl: './jam-help-marine-suffix-detail.component.html'
})
export class JamHelpMarineSuffixDetailComponent implements OnInit {
    jamHelp: IJamHelpMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jamHelp }) => {
            this.jamHelp = jamHelp;
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
