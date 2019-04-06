import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';

@Component({
    selector: 'mi-skillable-level-of-skill-marine-suffix-detail',
    templateUrl: './skillable-level-of-skill-marine-suffix-detail.component.html'
})
export class SkillableLevelOfSkillMarineSuffixDetailComponent implements OnInit {
    skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ skillableLevelOfSkill }) => {
            this.skillableLevelOfSkill = skillableLevelOfSkill;
        });
    }

    previousState() {
        window.history.back();
    }
}
