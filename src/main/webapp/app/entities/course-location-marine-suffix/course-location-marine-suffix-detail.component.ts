import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICourseLocationMarineSuffix } from 'app/shared/model/course-location-marine-suffix.model';

@Component({
    selector: 'mi-course-location-marine-suffix-detail',
    templateUrl: './course-location-marine-suffix-detail.component.html'
})
export class CourseLocationMarineSuffixDetailComponent implements OnInit {
    courseLocation: ICourseLocationMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ courseLocation }) => {
            this.courseLocation = courseLocation;
        });
    }

    previousState() {
        window.history.back();
    }
}
