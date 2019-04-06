import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubTaskMarineSuffix } from 'app/shared/model/sub-task-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-sub-task-marine-suffix-detail',
    templateUrl: './sub-task-marine-suffix-detail.component.html'
})
export class SubTaskMarineSuffixDetailComponent implements OnInit {
    subTask: ISubTaskMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ subTask }) => {
            this.subTask = this.convertObjectDatesService.changeDate(subTask);
        });
    }

    previousState() {
        window.history.back();
    }
}
