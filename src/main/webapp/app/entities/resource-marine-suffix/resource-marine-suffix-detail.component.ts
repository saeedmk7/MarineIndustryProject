import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';

@Component({
    selector: 'mi-resource-marine-suffix-detail',
    templateUrl: './resource-marine-suffix-detail.component.html'
})
export class ResourceMarineSuffixDetailComponent implements OnInit {
    resource: IResourceMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resource }) => {
            this.resource = resource;
        });
    }

    previousState() {
        window.history.back();
    }
}
