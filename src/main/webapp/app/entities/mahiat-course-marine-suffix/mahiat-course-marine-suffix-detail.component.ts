import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';

@Component({
    selector: 'mi-mahiat-course-marine-suffix-detail',
    templateUrl: './mahiat-course-marine-suffix-detail.component.html'
})
export class MahiatCourseMarineSuffixDetailComponent implements OnInit {
    mahiatCourse: IMahiatCourseMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mahiatCourse }) => {
            this.mahiatCourse = mahiatCourse;
        });
    }

    previousState() {
        window.history.back();
    }
}
