import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-main-task-marine-suffix-detail',
    templateUrl: './main-task-marine-suffix-detail.component.html'
})
export class MainTaskMarineSuffixDetailComponent implements OnInit {
    mainTask: IMainTaskMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mainTask }) => {
            this.mainTask = this.convertObjectDatesService.changeDate(mainTask);
        });
    }

    previousState() {
        window.history.back();
    }
}
