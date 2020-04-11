import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';

@Component({
    selector: 'mi-educational-center-grade-marine-suffix-detail',
    templateUrl: './educational-center-grade-marine-suffix-detail.component.html'
})
export class EducationalCenterGradeMarineSuffixDetailComponent implements OnInit {
    educationalCenterGrade: IEducationalCenterGradeMarineSuffix;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterGrade }) => {
            this.educationalCenterGrade = educationalCenterGrade;
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
