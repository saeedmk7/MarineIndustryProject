import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { AccountService, Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { EffectivenessPhaseMarineSuffixService } from './effectiveness-phase-marine-suffix.service';
import {
    EffectivenessPhaseLevelMarineSuffix,
    IEffectivenessPhaseLevelMarineSuffix
} from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';
import { EffectivenessPhaseLevelMarineSuffixService } from 'app/entities/effectiveness-phase-level-marine-suffix';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';

@Component({
    selector: 'mi-effectiveness-phase-marine-suffix',
    templateUrl: './effectiveness-phase-marine-suffix.component.html',
    styleUrls: ['./effectiveness-phase-marine-suffix.scss']
})
export class EffectivenessPhaseMarineSuffixComponent implements OnInit, OnDestroy {
    currentAccount: any;
    effectivenessPhases: IEffectivenessPhaseMarineSuffix[];
    effectivenessPhase: IEffectivenessPhaseMarineSuffix;
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
    effectivenessPhaseLevels: IEffectivenessPhaseLevelMarineSuffix[];
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
    currentEffectivenessPhase: number;
    canComplete: boolean = false;

    constructor(
        protected finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        protected effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        protected effectivenessPhaseLevelService: EffectivenessPhaseLevelMarineSuffixService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected principal: Principal,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager,
        protected convertObjectDatesService: ConvertObjectDatesService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll(criteria?) {
        this.effectivenessPhaseService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IEffectivenessPhaseMarineSuffix[]>) => this.paginateEffectivenessPhases(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        /*this.router.navigate(['/effectiveness-phase-marine-suffix'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();*/
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/effectiveness-phase-marine-suffix',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        //this.loadAll();
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessPhase }) => {
            this.effectivenessPhase = effectivenessPhase;
            if (this.effectivenessPhase.finalNiazsanjiReportId) {
                this.effectivenessPhaseService.getByFinalNiazsanjiReportId(this.effectivenessPhase.finalNiazsanjiReportId).subscribe(
                    (resp: HttpResponse<IEffectivenessPhaseMarineSuffix[]>) => {
                        debugger;
                        this.effectivenessPhases = this.convertObjectDatesService.changeArrayDate(resp.body);
                        if (this.effectivenessPhases && this.effectivenessPhases.length > 0) {
                            this.finalNiazsanjiReport = this.effectivenessPhases[0].finalNiazsanjiReport;
                            this.finalNiazsanjiReport.selectedEffectivenessPhaseLevelTitle = this.convertObjectDatesService.convertEffectivenessPhaseLevel2EqualString(
                                this.finalNiazsanjiReport.selectedEffectivenessPhaseLevel
                            );
                        }
                        this.effectivenessPhases = this.effectivenessPhases.sort(
                            (a, b) =>
                                a.effectivenessPhaseLevel.effectivenessLevel > b.effectivenessPhaseLevel.effectivenessLevel
                                    ? 1
                                    : a.effectivenessPhaseLevel.effectivenessLevel < b.effectivenessPhaseLevel.effectivenessLevel ? -1 : 0
                        );
                        this.effectivenessPhases.forEach(w => {
                            if (!w.finishPhaseDate) {
                                this.currentEffectivenessPhase = w.effectivenessPhaseLevel.effectivenessLevel;
                                return;
                            }
                        });

                        this.canComplete =
                            this.effectivenessPhases.filter(a => a.weightedPoints && a.weightedPoints > 0).length ==
                            this.effectivenessPhases.length;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            } else {
                this.previousState();
            }
        });
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        //this.registerChangeInEffectivenessPhases();
    }

    startPhase(id: number) {
        if (confirm('آیا برای شروع انجام این سطح مطمئنید؟')) {
            this.effectivenessPhaseService.startPhase(id).subscribe(
                (resp: HttpResponse<IEffectivenessPhaseMarineSuffix>) => {
                    const effectivenessPhase: IEffectivenessPhaseMarineSuffix = resp.body;
                    if (effectivenessPhase.status == 10) {
                        this.redirectToLevel(
                            effectivenessPhase.effectivenessPhaseLevel.effectivenessLevel,
                            effectivenessPhase.finalNiazsanjiReportId
                        );
                    }
                },
                (res: HttpErrorResponse) => this.onSaveError(res)
            );
        }
    }
    redirectToLevel(effectivenessLevel: number, finalNiazsanjiReportId: number) {
        switch (effectivenessLevel) {
            case 1:
                this.change('effectiveness-phase-marine-suffix/level-one/' + finalNiazsanjiReportId);
                break;
            case 2:
                this.change('effectiveness-phase-marine-suffix/level-two/' + finalNiazsanjiReportId);
                break;
            case 3:
                this.change('effectiveness-phase-marine-suffix/level-three/' + finalNiazsanjiReportId);
                break;
            case 4:
                this.change('effectiveness-phase-marine-suffix/level-four/' + finalNiazsanjiReportId);
                break;
        }
    }
    completeEffectivenessPhase(finalNiazsanjiReportId: number) {
        if (confirm('آیا اطلاعات وارد شده همگی صحیح هستند؟ و برای تایید نهایی کردن این نیازسنجی مطمئنید؟')) {
            this.effectivenessPhaseService.completeEffectivenessPhase(finalNiazsanjiReportId).subscribe(
                (resp: HttpResponse<boolean>) => {
                    if (resp.body) {
                        this.change('final-niazsanji-effectiveness-phase-marine-suffix');
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
    ngOnDestroy() {
        //this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEffectivenessPhaseMarineSuffix) {
        return item.id;
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateEffectivenessPhases(data: IEffectivenessPhaseMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;

        this.effectivenessPhases = data;
        const effectivenessPhaseLevelIds = this.effectivenessPhases.map(a => a.effectivenessPhaseLevelId);
        const criteria = [
            {
                key: 'effectivenessPhaseLevelId.in',
                value: effectivenessPhaseLevelIds
            }
        ];
        this.effectivenessPhaseLevelService.query(criteria).subscribe(
            (resp: HttpResponse<IEffectivenessPhaseLevelMarineSuffix[]>) => {
                this.effectivenessPhaseLevels = resp.body;
                this.effectivenessPhases.forEach(a => {
                    a.effectivenessPhaseLevel = this.effectivenessPhaseLevels.find(e => e.id == a.effectivenessPhaseLevelId);
                });
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
