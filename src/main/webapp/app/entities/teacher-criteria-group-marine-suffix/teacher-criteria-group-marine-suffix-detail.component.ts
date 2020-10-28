import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';

@Component({
    selector: 'mi-teacher-criteria-group-marine-suffix-detail',
    templateUrl: './teacher-criteria-group-marine-suffix-detail.component.html'
})
export class TeacherCriteriaGroupMarineSuffixDetailComponent implements OnInit {
    teacherCriteriaGroup: ITeacherCriteriaGroupMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherCriteriaGroup }) => {
            this.teacherCriteriaGroup = teacherCriteriaGroup;
        });
    }

    previousState() {
        window.history.back();
    }
}
