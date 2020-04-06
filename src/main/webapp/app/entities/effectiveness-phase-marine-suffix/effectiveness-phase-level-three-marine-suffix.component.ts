import {Component, OnInit, OnDestroy} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiEventManager, JhiParseLinks, JhiAlertService} from 'ng-jhipster';

import {IFinalNiazsanjiReportPersonMarineSuffix} from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import {Principal} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {FinalNiazsanjiReportPersonMarineSuffixService} from "app/entities/final-niazsanji-report-person-marine-suffix";
import {IEffectivenessPhaseMarineSuffix} from "app/shared/model/effectiveness-phase-marine-suffix.model";
import {EffectivenessPhaseMarineSuffixService} from "app/entities/effectiveness-phase-marine-suffix/effectiveness-phase-marine-suffix.service";
import {NiazsanjiPersonGradeMarineSuffixService} from "app/entities/niazsanji-person-grade-marine-suffix";
import {Grade} from "app/shared/model/enums/Grade";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {MahiatCourseMarineSuffixService} from "app/entities/mahiat-course-marine-suffix";
import {IMahiatCourseMarineSuffix} from "app/shared/model/mahiat-course-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {LevelThreeCriteriaMarineSuffix} from "app/shared/model/level-three-criteria-marine-suffix.model";
import {LevelThreeCriteriaMarineSuffixService} from "app/entities/level-three-criteria-marine-suffix";
import {LevelThreeEffectivenessMarineSuffix} from "app/shared/model/level-three-effectiveness-marine-suffix.model";
import {LevelThreeEffectivenessMarineSuffixService} from "app/entities/level-three-effectiveness-marine-suffix";

@Component({
    selector: 'mi-effectiveness-phase-level-three-marine-suffix',
    templateUrl: './effectiveness-phase-level-three-marine-suffix.component.html'
})
export class EffectivenessPhaseLevelThreeMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    finalNiazsanjiReportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
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

    mahiatCourses: IMahiatCourseMarineSuffix[];
    mahiatCourseDisable: boolean = false;
    constructor(
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        private levelThreeEffectivenessService: LevelThreeEffectivenessMarineSuffixService,
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
        this.eventSubscriber = this.activatedRoute.params.subscribe((params) => {
            this.finalNiazsanjiReportId = params['finalNiazsanjiReportId'];
            this.finalNiazsanjiReportService.find(this.finalNiazsanjiReportId).subscribe(
                (resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => {
                    this.finalNiazsanjiReport = resp.body;
                    if(this.finalNiazsanjiReport.mahiatCourseId){
                        this.mahiatCourseDisable = true;
                        this.loadData();
                    }
                },(res: HttpErrorResponse) => this.onSaveError(res))
            this.mahiatCourseService.query().subscribe((resp: HttpResponse<IMahiatCourseMarineSuffix[]>) => {
                this.mahiatCourses = resp.body;
            },(res: HttpErrorResponse) => this.onSaveError(res))

        });
    }

    selectMahiatCourseById(mahiatId: number){
        if(mahiatId){
            if(confirm("آیا از ثبت این ماهیت برای این دوره مطمئنید؟ در صورت ثبت دیگر امکان تغییر ماهیت وجود ندارد.")){
                this.finalNiazsanjiReport.mahiatCourseId = mahiatId;
                this.finalNiazsanjiReportService.update(this.finalNiazsanjiReport).subscribe(
                    (resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => {
                        this.finalNiazsanjiReport = resp.body;
                        this.mahiatCourseDisable = true;
                        this.loadData();
                    },(res: HttpErrorResponse) => this.onSaveError(res))
            }
        }
    }
    loadData(){
        this.finalNiazsanjiReportPersonService.getLevelThreeDataByFinalNiazsanjiReportId(this.finalNiazsanjiReportId)
            .subscribe((resp: HttpResponse<IFinalNiazsanjiReportPersonMarineSuffix[]>) => {
                debugger;
                this.finalNiazsanjiReportPeople = resp.body.sort((a,b) => (a.modifyDate > b.modifyDate) ? 1 : (a.modifyDate < b.modifyDate) ? -1 : 0);

                this.fullAverage = this.finalNiazsanjiReportPeople.map(w => w.levelThreeScore).reduce((sum, current) => sum + current) / this.finalNiazsanjiReportPeople.length;
                this.fullGrade = this.convertObjectDatesService.calculateGrade(this.fullAverage);
                if(this.finalNiazsanjiReportPeople.filter(w => w.levelThreeScore > 0).length == this.finalNiazsanjiReportPeople.length){
                    this.canComplete = true;
                }
            });
    }
    completeLevel(finalNiazsanjiReportId: number){
        if(confirm("آیا اطلاعات وارد شده همگی صحیح هستند؟ و برای تایید نهایی کردن این سطح مطمئنید؟")) {
            this.levelThreeEffectivenessService.completeLevel(finalNiazsanjiReportId).subscribe((resp: HttpResponse<boolean>) => {
                debugger;
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
}
