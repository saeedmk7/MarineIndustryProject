import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { FinalNiazsanjiReportPersonMarineSuffixService } from 'app/entities/final-niazsanji-report-person-marine-suffix';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { EffectivenessPhaseMarineSuffixService } from 'app/entities/effectiveness-phase-marine-suffix/effectiveness-phase-marine-suffix.service';
import { NiazsanjiPersonGradeMarineSuffixService } from 'app/entities/niazsanji-person-grade-marine-suffix';
import { Grade } from 'app/shared/model/enums/Grade';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { MahiatCourseMarineSuffixService } from 'app/entities/mahiat-course-marine-suffix';
import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { LevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';
import { LevelFourCriteriaMarineSuffixService } from 'app/entities/level-four-criteria-marine-suffix';
import { LevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';
import { LevelFourEffectivenessMarineSuffixService } from 'app/entities/level-four-effectiveness-marine-suffix';
import { Chart } from 'angular-highcharts';
import { IEffectivenessPhasePerCriteriaData } from 'app/shared/model/custom/effectiveness-phase-per-criteria-data';

@Component({
    selector: 'mi-effectiveness-phase-level-four-marine-suffix',
    templateUrl: './effectiveness-phase-level-four-marine-suffix.component.html',
    styleUrls: ['./effectiveness-phase-marine-suffix.scss']
})
export class EffectivenessPhaseLevelFourMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    finalNiazsanjiReportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
    effectivenessPhasePerCriteriaDatas: IEffectivenessPhasePerCriteriaData[];
    effectivenessPhasePerCriteriaDataChart: Chart;
    categories: any[];
    effectivenessPhasePerCriteriaDataSeries: any;
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    finalNiazsanjiReportId: number;
    canComplete: boolean = false;
    fullAverage: number = 0;
    fullGrade: Grade = Grade.D;

    effectivenessPhase: IEffectivenessPhaseMarineSuffix;

    constructor(
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        private levelFourEffectivenessService: LevelFourEffectivenessMarineSuffixService,
        private mahiatCourseService: MahiatCourseMarineSuffixService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.eventSubscriber = this.activatedRoute.params.subscribe(params => {
            this.finalNiazsanjiReportId = params['finalNiazsanjiReportId'];
            this.finalNiazsanjiReportService.find(this.finalNiazsanjiReportId).subscribe(
                (resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => {
                    this.finalNiazsanjiReport = resp.body;
                    this.loadData();
                },
                (res: HttpErrorResponse) => this.onSaveError(res)
            );
            let criteria = [
                {
                    key: 'finalNiazsanjiReportId.equals',
                    value: this.finalNiazsanjiReportId
                },
                {
                    key: 'effectivenessPhaseLevelEffectivenessLevel.equals',
                    value: 4
                }
            ];

            this.effectivenessPhaseService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (resp: HttpResponse<IEffectivenessPhaseMarineSuffix[]>) => {
                        if (resp.body && resp.body.length > 0) {
                            this.effectivenessPhase = resp.body[0];
                        }
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        });
    }

    loadData() {
        this.finalNiazsanjiReportPersonService
            .getLevelFourDataByFinalNiazsanjiReportId(this.finalNiazsanjiReportId)
            .subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {
                this.finalNiazsanjiReportPeople = resp.body.sort(
                    (a, b) => (a.modifyDate > b.modifyDate ? 1 : a.modifyDate < b.modifyDate ? -1 : 0)
                );
                this.finalNiazsanjiReportPeople = this.finalNiazsanjiReportPeople.sort(
                    (a, b) => (a.absented > b.absented ? 1 : a.absented < b.absented ? -1 : 0)
                );

                const presentPeople = this.finalNiazsanjiReportPeople.filter(w => !w.absented);

                this.fullAverage = presentPeople.map(w => w.levelFourScore).reduce((sum, current) => sum + current) / presentPeople.length;
                this.fullGrade = this.convertObjectDatesService.calculateGrade(this.fullAverage);
                if (presentPeople.filter(w => w.levelFourScore > 0).length == presentPeople.length) {
                    this.canComplete = true;
                }
            });
        this.levelFourEffectivenessService.criteriaChart(this.finalNiazsanjiReportId).subscribe(
            (res: HttpResponse<IEffectivenessPhasePerCriteriaData[]>) => {
                this.effectivenessPhasePerCriteriaDatas = res.body;
                this.categories = this.effectivenessPhasePerCriteriaDatas.map(a => a.criteria.title);
                //this.effectivenessPhasePerCriteriaDataSeries = this.effectivenessPhasePerCriteriaDatas.map(a => a.sumValue);
                this.effectivenessPhasePerCriteriaDataSeries = this.effectivenessPhasePerCriteriaDatas.map(w => {
                    return {
                        name: w.criteria.title,
                        data: [w.sumValue],
                        color: w.criteria.backgroundColor,
                        displayOrder: w.criteria.displayOrder
                    };
                });

                this.loadChart();
            },
            (res: HttpErrorResponse) => this.onSaveError(res)
        );
    }
    loadChart() {
        // @ts-ignore
        this.effectivenessPhasePerCriteriaDataChart = new Chart({
            chart: {
                type: 'column',
                style: {
                    fontFamily: 'IranSans, SansSerif, IranYekan, B Nazanin, B Badr, Tahoma, Times New Roman'
                }
            },
            lang: {
                decimalPoint: ',',
                thousandsSep: '.'
            },
            title: {
                text: 'نمودار نتایج به تفکیک معیار ارزشیابی'
            },
            xAxis: {
                categories: this.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'جمع مقادیر'
                },
                opposite: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat:
                    '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="direction: ltr; padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                style: {
                    direction: 'rtl'
                },
                useHTML: true
                /*formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        this.point.y + ' ' + this.point.name.toLowerCase();
                }*/
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series: {
                    cursor: 'pointer'
                }
            },
            series: this.effectivenessPhasePerCriteriaDataSeries.sort(
                (a, b) => (a.displayOrder > b.displayOrder ? -1 : a.displayOrder < b.displayOrder ? 1 : 0)
            ),
            credits: {
                enabled: false
            }
        });
    }
    completeLevel(finalNiazsanjiReportId: number) {
        if (confirm('آیا اطلاعات وارد شده همگی صحیح هستند؟ و برای تایید نهایی کردن این سطح مطمئنید؟')) {
            this.levelFourEffectivenessService.completeLevel(finalNiazsanjiReportId).subscribe(
                (resp: HttpResponse<boolean>) => {
                    if (resp.body) {
                        this.change('effectiveness-phase-marine-suffix/' + finalNiazsanjiReportId);
                    }
                },
                (res: HttpErrorResponse) => this.onSaveError(res)
            );
        }
    }
    protected onSaveError(res) {
        console.error(res);
    }
    change(i) {
        this.router.navigateByUrl(i);
    }
    previousState() {
        window.history.back();
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/final-niazsanji-report-person-marine-suffix'], {
            queryParams: {
                finalNiazsanjiReportId: this.finalNiazsanjiReportId,
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/final-niazsanji-report-person-marine-suffix',
            {
                finalNiazsanjiReportId: this.finalNiazsanjiReportId,
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInFinalNiazsanjiReportPeople();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFinalNiazsanjiReportPersonMarineSuffix) {
        return item.id;
    }

    registerChangeInFinalNiazsanjiReportPeople() {
        this.eventSubscriber = this.eventManager.subscribe('finalNiazsanjiReportPersonListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateFinalNiazsanjiReportPeople(data: IFinalNiazsanjiReportPersonMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.finalNiazsanjiReportPeople = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
