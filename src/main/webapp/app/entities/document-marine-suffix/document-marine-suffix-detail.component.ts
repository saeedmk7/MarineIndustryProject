import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';

@Component({
    selector: 'mi-document-marine-suffix-detail',
    templateUrl: './document-marine-suffix-detail.component.html'
})
export class DocumentMarineSuffixDetailComponent implements OnInit {
    document: IDocumentMarineSuffix;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ document }) => {
            this.document = document;
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
