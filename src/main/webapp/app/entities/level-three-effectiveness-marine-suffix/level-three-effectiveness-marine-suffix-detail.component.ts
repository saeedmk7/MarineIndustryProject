import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ILevelThreeEffectivenessMarineSuffix } from 'app/shared/model/level-three-effectiveness-marine-suffix.model';

@Component({
    selector: 'mi-level-three-effectiveness-marine-suffix-detail',
    templateUrl: './level-three-effectiveness-marine-suffix-detail.component.html'
})
export class LevelThreeEffectivenessMarineSuffixDetailComponent implements OnInit {
    levelThreeEffectiveness: ILevelThreeEffectivenessMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeEffectiveness }) => {
            this.levelThreeEffectiveness = levelThreeEffectiveness;
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
