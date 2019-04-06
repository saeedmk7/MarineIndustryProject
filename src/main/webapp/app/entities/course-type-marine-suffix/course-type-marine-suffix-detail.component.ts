import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';

@Component({
    selector: 'mi-course-type-marine-suffix-detail',
    templateUrl: './course-type-marine-suffix-detail.component.html'
})
export class CourseTypeMarineSuffixDetailComponent implements OnInit {
    courseType: ICourseTypeMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ courseType }) => {
            this.courseType = courseType;
        });
    }

    previousState() {
        window.history.back();
    }
}
