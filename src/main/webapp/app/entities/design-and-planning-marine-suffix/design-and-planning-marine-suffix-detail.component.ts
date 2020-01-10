import {Component, Input, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDesignAndPlanningMarineSuffix } from 'app/shared/model/design-and-planning-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {HttpResponse} from "@angular/common/http";
import {DesignAndPlanningMarineSuffixService} from "app/entities/design-and-planning-marine-suffix/design-and-planning-marine-suffix.service";
import set = Reflect.set;

@Component({
    selector: 'mi-design-and-planning-marine-suffix-detail',
    templateUrl: './design-and-planning-marine-suffix-detail.component.html'
})
export class DesignAndPlanningMarineSuffixDetailComponent implements OnInit {
    @Input() finalNiazsanjiReportId: number;

    designAndPlanning: IDesignAndPlanningMarineSuffix;
    documentUrl: string;

    constructor(private activatedRoute: ActivatedRoute, private convertObjectDatesService : ConvertObjectDatesService,
                private designAndPlanningService: DesignAndPlanningMarineSuffixService) {}

    ngOnInit() {

        setTimeout(() => {

            if(this.finalNiazsanjiReportId)
            {
                this.showData(this.finalNiazsanjiReportId);
            }
            else {
                this.activatedRoute.data.subscribe(({designAndPlanning}) => {
                    this.showData(designAndPlanning.finalNiazsanjiReportId);
                });
            }
        }, 1000);

    }
    showData(finalNiazsanjiReportId){
        const criteriaDesign = [{
            key: 'finalNiazsanjiReportId.equals',
            value: finalNiazsanjiReportId
        }];
        this.designAndPlanningService
            .query({
                page: 0,
                size: 200,
                criteria: criteriaDesign,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IDesignAndPlanningMarineSuffix[]>) => {


            if (resp.body.length > 0) {
                this.designAndPlanning =  resp.body[0];
                this.designAndPlanning = this.convertObjectDatesService.changeDate(this.designAndPlanning);
                this.designAndPlanning.runMonthName = this.convertObjectDatesService.convertMonthsNumber2MonthName(this.designAndPlanning.runMonth);
                this.documentUrl = 'document-marine-suffix/designandplanning/' + this.designAndPlanning.id;
            }
        });
    }

    previousState() {
        window.history.back();
    }
}
