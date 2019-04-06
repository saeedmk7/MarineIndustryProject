import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {FormUploadComponent, UploadFileService} from "app/plugin/upload-file";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {HttpResponse} from "@angular/common/http";

@Component({
    selector: 'mi-person-marine-suffix-detail',
    templateUrl: './person-marine-suffix-detail.component.html'
})
export class PersonMarineSuffixDetailComponent implements OnInit {
    person: IPersonMarineSuffix;
    constructor(private activatedRoute: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService,
        private organizationChartService: OrganizationChartMarineSuffixService) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ person }) => {

            this.person =  this.convertObjectDatesService.changeDate(person);
            if(this.person.organizationChartId)
            {
                if(this.organizationChartService.organizationchartsAll)
                {
                    this.person.organizationChartTitle = this.organizationChartService.organizationchartsAll.find(a => a.id == this.person.organizationChartId).fullTitle;
                }
                else {
                    this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                        this.person.organizationChartTitle = resp.body.find(a => a.id == this.person.organizationChartId).fullTitle;
                    });
                }
            }
            //this.person = person;
        });
    }

    previousState() {
        window.history.back();
    }
}
