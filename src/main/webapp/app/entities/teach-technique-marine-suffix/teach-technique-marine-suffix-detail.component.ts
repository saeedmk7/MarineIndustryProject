import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeachTechniqueMarineSuffix } from 'app/shared/model/teach-technique-marine-suffix.model';

@Component({
    selector: 'mi-teach-technique-marine-suffix-detail',
    templateUrl: './teach-technique-marine-suffix-detail.component.html'
})
export class TeachTechniqueMarineSuffixDetailComponent implements OnInit {
    teachTechnique: ITeachTechniqueMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachTechnique }) => {
            this.teachTechnique = teachTechnique;
        });
    }

    previousState() {
        window.history.back();
    }
}
