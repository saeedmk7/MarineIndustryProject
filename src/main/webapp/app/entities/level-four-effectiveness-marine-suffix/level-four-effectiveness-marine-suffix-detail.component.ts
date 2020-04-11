import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ILevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';

@Component({
    selector: 'mi-level-four-effectiveness-marine-suffix-detail',
    templateUrl: './level-four-effectiveness-marine-suffix-detail.component.html'
})
export class LevelFourEffectivenessMarineSuffixDetailComponent implements OnInit {
    levelFourEffectiveness: ILevelFourEffectivenessMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelFourEffectiveness }) => {
            this.levelFourEffectiveness = levelFourEffectiveness;
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
