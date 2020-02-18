import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IHomePagePersonEducationalModule} from "../model/custom/home-page-person-educational-module";
import {FinalNiazsanjiReportMarineSuffixService} from "../../entities/final-niazsanji-report-marine-suffix";
import {JhiAlertService} from "ng-jhipster";
import * as $ from 'jquery';

@Component({
    selector: 'education-history-panel',
    templateUrl: './education-history-panel.component.html',
    styleUrls: ['./education-history-panel.component.scss']
})
export class EducationHistoryPanelComponent implements OnInit, OnDestroy {
    @Input('personId') set personId(value: number){
        if(value) {
            this.finalNiazsanjiReportMarineSuffixService.getHomePagePersonEducationalModule(value).subscribe((resp: HttpResponse<IHomePagePersonEducationalModule[]>) => {
                    switch (this.type) {
                        case 2:
                            this.homePagePersonEducationalModules = resp.body.filter(a => a.status > 0).sort((a, b) => (a.runDate > b.runDate) ? 1 : (a.runDate < b.runDate) ? -1 : 0);
                        default:
                            this.homePagePersonEducationalModules = resp.body.sort((a, b) => (a.runDate > b.runDate) ? 1 : (a.runDate < b.runDate) ? -1 : 0);
                    }
                    this.homePagePersonEducationalModules.forEach(a => {
                        a.totalLearningTime = (!a.learningTimePractical ? 0 : a.learningTimePractical) + (!a.learningTimeTheorical ? 0 : a.learningTimeTheorical);
                        switch (a.status) {
                            case 100:
                                a.statusMeaning = "خاتمه دوره";
                                break;
                            case 90:
                                a.statusMeaning = "اجرا شده";
                                break;
                            case 80:
                                a.statusMeaning = "برنامه ریزی شده";
                                break;
                            case 70:
                                a.statusMeaning = "تصویب شوراء";
                                break;
                            case 0:
                                a.statusMeaning = "شناسنامه آموزشی";
                                break;
                        }
                    });
                    //this.makePersonHourPieChart(resp.body);
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
    }
    @Input('type') type: number;
    @Input('title') title: string = "global.sharedTitles.educationHistoryTitle";
    /*
    * Type : 1 ---> Complete
    * Type : 2 ---> Status > 0*/
    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];


    constructor(protected finalNiazsanjiReportMarineSuffixService: FinalNiazsanjiReportMarineSuffixService, protected jhiAlertService: JhiAlertService) {
    }

    deleteElement(i) {
        $('#' + i).remove();
    }

    toggleColappse(i) {
        $('#' + i).collapse('toggle');
    }

    ngOnInit() {
    }

    ngOnDestroy(): void {
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
