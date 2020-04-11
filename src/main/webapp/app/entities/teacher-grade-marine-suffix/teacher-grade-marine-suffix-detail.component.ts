import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';

@Component({
    selector: 'mi-teacher-grade-marine-suffix-detail',
    templateUrl: './teacher-grade-marine-suffix-detail.component.html'
})
export class TeacherGradeMarineSuffixDetailComponent implements OnInit {
    teacherGrade: ITeacherGradeMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherGrade }) => {
            this.teacherGrade = teacherGrade;
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
