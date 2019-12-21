import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMediaProductTypeMarineSuffix } from 'app/shared/model/media-product-type-marine-suffix.model';

@Component({
    selector: 'mi-media-product-type-marine-suffix-detail',
    templateUrl: './media-product-type-marine-suffix-detail.component.html'
})
export class MediaProductTypeMarineSuffixDetailComponent implements OnInit {
    mediaProductType: IMediaProductTypeMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mediaProductType }) => {
            this.mediaProductType = mediaProductType;
        });
    }

    previousState() {
        window.history.back();
    }
}
