import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';

@Component({
    selector: 'mi-teacher-grade-score-marine-suffix-detail',
    templateUrl: './teacher-grade-score-marine-suffix-detail.component.html'
})
export class TeacherGradeScoreMarineSuffixDetailComponent implements OnInit {
    teacherGradeScore: ITeacherGradeScoreMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherGradeScore }) => {
            this.teacherGradeScore = teacherGradeScore;
        });
    }

    previousState() {
        window.history.back();
    }
}
