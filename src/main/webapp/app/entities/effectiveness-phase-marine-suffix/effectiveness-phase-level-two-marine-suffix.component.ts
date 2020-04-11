import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {IFinalNiazsanjiReportPersonMarineSuffix} from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {EffectivenessPhaseMarineSuffixService} from "app/entities/effectiveness-phase-marine-suffix/effectiveness-phase-marine-suffix.service";
import {NiazsanjiPersonGradeMarineSuffixService} from "app/entities/niazsanji-person-grade-marine-suffix";
import {Grade} from "app/shared/model/enums/Grade";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-effectiveness-phase-level-two-marine-suffix',
    templateUrl: './effectiveness-phase-level-two-marine-suffix.component.html'
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

    errorMessage: string = "";

    firstScoreAverage: number = 0;
    secondScoreAverage: number = 0;
    fullAverage: number = 0;
    fullGrade: Grade = Grade.D;

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
        this.eventSubscriber = this.activatedRoute.params.subscribe((params) => {

            this.finalNiazsanjiReportId = params['finalNiazsanjiReportId'];
            this.loadDataByFinalNiazsanjiReportId(this.finalNiazsanjiReportId);
        });
    }

    loadDataByFinalNiazsanjiReportId(finalNiazsanjiReportId: number){
        this.finalNiazsanjiReportPersonService.getLevelOneDataByFinalNiazsanjiReportId(this.finalNiazsanjiReportId)
            .subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {

                this.finalNiazsanjiReportPeople = resp.body.sort((a,b) => (a.id > b.id) ? 1 : (a.id < b.id) ? -1 : 0);

                this.firstScoreAverage = this.finalNiazsanjiReportPeople.map(w => w.scoreBeforeTest).reduce((sum, current) => sum + current) / this.finalNiazsanjiReportPeople.length;
                this.secondScoreAverage = this.finalNiazsanjiReportPeople.map(w => w.scoreAfterTest).reduce((sum, current) => sum + current) / this.finalNiazsanjiReportPeople.length;
                this.fullAverage = this.finalNiazsanjiReportPeople.map(w => w.averageScore).reduce((sum, current) => sum + current) / this.finalNiazsanjiReportPeople.length;
                this.fullGrade = this.convertObjectDatesService.calculateGrade(this.fullAverage);

                if(this.finalNiazsanjiReportPeople.filter(w => w.averageScore > 0).length == this.finalNiazsanjiReportPeople.length){
                    this.canComplete = true;
                }
            });
    }

    completeLevel(finalNiazsanjiReportId: number){
        if(confirm("آیا اطلاعات وارد شده همگی صحیح هستند؟ و برای تایید نهایی کردن این سطح مطمئنید؟")) {
            this.effectivenessPhaseService.completeLevelTwo(finalNiazsanjiReportId).subscribe((resp: HttpResponse<boolean>) => {
                if (resp.body) {
                    this.change('effectiveness-phase-marine-suffix/' + finalNiazsanjiReportId);
                }
            },(res: HttpErrorResponse) => this.onSaveError(res))
        }
    }
    protected onSaveError(res) {
        console.error(res);
    }
    change(i){
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

    saveScore(finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix){
        this.errorMessage = "";
        if(!(finalNiazsanjiReportPerson.scoreBeforeTest && finalNiazsanjiReportPerson.scoreBeforeTest > 0 && finalNiazsanjiReportPerson.scoreBeforeTest <= 100)){
            this.errorMessage = `لطفا نمره قبل از تست '${finalNiazsanjiReportPerson.personFullName}' را به درستی وارد نمائید.`;
            return;
        }
        if(!(finalNiazsanjiReportPerson.scoreAfterTest && finalNiazsanjiReportPerson.scoreAfterTest > 0 && finalNiazsanjiReportPerson.scoreAfterTest <= 100)){
            this.errorMessage = `لطفا نمره بعد از تست '${finalNiazsanjiReportPerson.personFullName}' را به درستی وارد نمائید.`;
            return;
        }

        let average: number = ((finalNiazsanjiReportPerson.scoreBeforeTest ? finalNiazsanjiReportPerson.scoreBeforeTest : 1)  + (finalNiazsanjiReportPerson.scoreAfterTest ? finalNiazsanjiReportPerson.scoreAfterTest : 1)) / 2;

        finalNiazsanjiReportPerson.averageScore = average;

        this.finalNiazsanjiReportPersonService.update(finalNiazsanjiReportPerson).subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix>) => {
                this.loadDataByFinalNiazsanjiReportId(resp.body.finalNiazsanjiReportId);
        },(res: HttpErrorResponse) => this.onSaveError(res));

    }

}
