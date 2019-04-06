import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';

@Component({
    selector: 'mi-academic-rank-marine-suffix-detail',
    templateUrl: './academic-rank-marine-suffix-detail.component.html'
})
export class AcademicRankMarineSuffixDetailComponent implements OnInit {
    academicRank: IAcademicRankMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ academicRank }) => {
            this.academicRank = academicRank;
        });
    }

    previousState() {
        window.history.back();
    }
}
