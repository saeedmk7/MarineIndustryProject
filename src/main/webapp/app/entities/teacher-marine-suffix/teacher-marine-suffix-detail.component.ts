import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-teacher-marine-suffix-detail',
    templateUrl: './teacher-marine-suffix-detail.component.html'
})
export class TeacherMarineSuffixDetailComponent implements OnInit {
    teacher: ITeacherMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacher }) => {
            this.teacher = this.convertObjectDatesService.changeDate(teacher);
        });
    }

    previousState() {
        window.history.back();
    }
}
