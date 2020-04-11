import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';

@Component({
    selector: 'mi-teacher-criteria-marine-suffix-detail',
    templateUrl: './teacher-criteria-marine-suffix-detail.component.html'
})
export class TeacherCriteriaMarineSuffixDetailComponent implements OnInit {
    teacherCriteria: ITeacherCriteriaMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherCriteria }) => {
            this.teacherCriteria = teacherCriteria;
        });
    }

    previousState() {
        window.history.back();
    }
}
