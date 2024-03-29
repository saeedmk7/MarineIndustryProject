import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { FinalNiazsanjiReportPersonMarineSuffixService } from 'app/entities/final-niazsanji-report-person-marine-suffix';
import { EffectivenessPhaseMarineSuffixService } from 'app/entities/effectiveness-phase-marine-suffix/effectiveness-phase-marine-suffix.service';
import { NiazsanjiPersonGradeMarineSuffixService } from 'app/entities/niazsanji-person-grade-marine-suffix';
import { Grade } from 'app/shared/model/enums/Grade';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';

@Component({
    selector: 'mi-effectiveness-phase-level-two-marine-suffix',
    templateUrl: './effectiveness-phase-level-two-marine-suffix.component.html',
    styleUrls: ['./effectiveness-phase-marine-suffix.scss']
})
export class EffectivenessPhaseLevelTwoMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    finalNiazsanjiReportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
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

    errorMessage: string = '';

    firstScoreAverage: number = 0;
    secondScoreAverage: number = 0;
    fullAverage: number = 0;
    fullGrade: Grade = Grade.D;

    effectivenessPhase: IEffectivenessPhaseMarineSuffix;
    constructor(
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        private niazsanjiPersonGradeMarineSuffixService: NiazsanjiPersonGradeMarineSuffixService,
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
            this.loadDataByFinalNiazsanjiReportId(this.finalNiazsanjiReportId);

            let criteria = [
                {
                    key: 'finalNiazsanjiReportId.equals',
                    value: this.finalNiazsanjiReportId
                },
                {
                    key: 'effectivenessPhaseLevelEffectivenessLevel.equals',
                    value: 2
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

    loadDataByFinalNiazsanjiReportId(finalNiazsanjiReportId: number) {
        this.finalNiazsanjiReportPersonService
            .getLevelOneDataByFinalNiazsanjiReportId(this.finalNiazsanjiReportId)
            .subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {
                this.finalNiazsanjiReportPeople = resp.body.sort((a, b) => (a.id > b.id ? 1 : a.id < b.id ? -1 : 0));
                this.finalNiazsanjiReportPeople = this.finalNiazsanjiReportPeople.sort(
                    (a, b) => (a.absented > b.absented ? 1 : a.absented < b.absented ? -1 : 0)
                );

                const presentPeople = this.finalNiazsanjiReportPeople.filter(w => !w.absented);
                this.firstScoreAverage = presentPeople.map(w => w.scoreBeforeTest).reduce((sum, current) => sum + current);
                this.secondScoreAverage = presentPeople.map(w => w.scoreAfterTest).reduce((sum, current) => sum + current);

                if (this.firstScoreAverage > 0) this.firstScoreAverage = this.firstScoreAverage / presentPeople.length;

                if (this.secondScoreAverage > 0) this.secondScoreAverage = this.secondScoreAverage / presentPeople.length;

                const goal = this.effectivenessPhase.effectivenessPhaseLevel.goal;

                let minus = this.secondScoreAverage - this.firstScoreAverage;

                if (minus >= goal) this.fullAverage = 100;
                else {
                    this.fullAverage = Number((minus / goal * 100).toFixed(1));
                }

                this.fullGrade = this.convertObjectDatesService.calculateGrade(this.fullAverage);

                if (presentPeople.filter(w => w.averageScore > 0).length == presentPeople.length) {
                    this.canComplete = true;
                }
            });
    }

    completeLevel(finalNiazsanjiReportId: number) {
        if (confirm('آیا اطلاعات وارد شده همگی صحیح هستند؟ و برای تایید نهایی کردن این سطح مطمئنید؟')) {
            this.effectivenessPhaseService.completeLevelTwo(finalNiazsanjiReportId).subscribe(
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

    saveScore(finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix) {
        this.errorMessage = '';
        if (
            !(
                finalNiazsanjiReportPerson.scoreBeforeTest &&
                finalNiazsanjiReportPerson.scoreBeforeTest > 0 &&
                finalNiazsanjiReportPerson.scoreBeforeTest <= 100
            )
        ) {
            this.errorMessage = `لطفا نمره قبل از تست '${finalNiazsanjiReportPerson.personFullName}' را به درستی وارد نمائید.`;
            return;
        }
        if (
            !(
                finalNiazsanjiReportPerson.scoreAfterTest &&
                finalNiazsanjiReportPerson.scoreAfterTest > 0 &&
                finalNiazsanjiReportPerson.scoreAfterTest <= 100
            )
        ) {
            this.errorMessage = `لطفا نمره بعد از تست '${finalNiazsanjiReportPerson.personFullName}' را به درستی وارد نمائید.`;
            return;
        }
        if (finalNiazsanjiReportPerson.scoreAfterTest < finalNiazsanjiReportPerson.scoreBeforeTest) {
            this.errorMessage = `لطفا نمره بعد از تست '${finalNiazsanjiReportPerson.personFullName}' را به درستی وارد نمائید.`;
            return;
        }

        /*let average: number =
            ((finalNiazsanjiReportPerson.scoreBeforeTest ? finalNiazsanjiReportPerson.scoreBeforeTest : 1) +
                (finalNiazsanjiReportPerson.scoreAfterTest ? finalNiazsanjiReportPerson.scoreAfterTest : 1)) /
            2;*/
        let minus =
            (finalNiazsanjiReportPerson.scoreAfterTest ? finalNiazsanjiReportPerson.scoreAfterTest : 1) -
            (finalNiazsanjiReportPerson.scoreBeforeTest ? finalNiazsanjiReportPerson.scoreBeforeTest : 1);

        const goal = this.effectivenessPhase.effectivenessPhaseLevel.goal;
        if (minus >= goal) finalNiazsanjiReportPerson.averageScore = 100;
        else if (minus <= 0) {
            this.errorMessage = `لطفا نمره بعد از تست '${finalNiazsanjiReportPerson.personFullName}' را به درستی وارد نمائید.`;
            return;
        } else {
            finalNiazsanjiReportPerson.averageScore = Number((minus / goal * 100).toFixed(1));
        }

        this.finalNiazsanjiReportPersonService.update(finalNiazsanjiReportPerson).subscribe(
            (resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>) => {
                this.loadDataByFinalNiazsanjiReportId(resp.body.finalNiazsanjiReportId);
            },
            (res: HttpErrorResponse) => this.onSaveError(res)
        );
    }
}
