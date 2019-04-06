import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import {DATE_FORMAT, DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService, JhiLanguageService} from 'ng-jhipster';

import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import { NiazsanjiGroupMarineSuffixService } from './niazsanji-group-marine-suffix.service';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from 'app/entities/job-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import * as persianMoment from 'jalali-moment';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { ScientificWorkGroupMarineSuffixService } from 'app/entities/scientific-work-group-marine-suffix';

@Component({
    selector: 'mi-niazsanji-group-marine-suffix-update',
    templateUrl: './niazsanji-group-marine-suffix-update.component.html'
})
export class NiazsanjiGroupMarineSuffixUpdateComponent implements OnInit {
    private _niazsanjiGroup: INiazsanjiGroupMarineSuffix;
    isSaving: boolean;

    jobs: IJobMarineSuffix[];
    fullJobs: IJobMarineSuffix[];

    disable: boolean = true;
    educationalModulesDisable: boolean = false;
    isfa: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];

    scientificworkgroups: IScientificWorkGroupMarineSuffix[];
    reviewDate: string;
    scheduleDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private niazsanjiGroupService: NiazsanjiGroupMarineSuffixService,
        private jobService: JobMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private scientificWorkGroupService: ScientificWorkGroupMarineSuffixService,
        private languageManager: JhiLanguageService,
        private router: Router
    ) {
        this.isfa = languageManager.currentLang == 'fa';
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ niazsanjiGroup }) => {
            this.niazsanjiGroup = niazsanjiGroup;
            if(this.niazsanjiGroup.educationalModules){
                this.niazsanjiGroup.educationalModules.forEach(a => a.fullTitle = a.code + " - " + a.title);
            }
            if(this.niazsanjiGroup.jobs){
                this.niazsanjiGroup.jobs.forEach(a => a.fullTitle = a.jobCode + " - " + a.title);
            }
        });
        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {

                this.jobs = res.body;
                this.fullJobs = this.jobs;
                if(this.niazsanjiGroup.id){
                    this.jobs = this.jobs.filter(a => a.jobCode.startsWith(this.niazsanjiGroup.firstThreeJobCode));
                    this.disable = false;
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.scientificWorkGroupService.query().subscribe(
            (res: HttpResponse<IScientificWorkGroupMarineSuffix[]>) => {
                this.scientificworkgroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    showRelatedJobs(input){
        this.disable = true;
        let jobCode = input.niazsanjiGroup.firstThreeJobCode;
        if(jobCode.length === 3) {
            this.jobs = this.fullJobs.filter(entity => entity.jobCode.startsWith(jobCode));
            this.disable = false;
        }
    }

    previousState() {
        window.history.back();
    }
    change(i){
        this.router.navigateByUrl(i);
    }
    save() {

        this.isSaving = true;
        this.niazsanjiGroup.status = 0;
        if (!this.niazsanjiGroup.educationalModules.length) {
            this.onError("لطفا حداقل یک پودمان آموزشی انتخاب نمائید.");
            return;
        }
        if(!this.niazsanjiGroup.jobs.length) {
            this.onError("لطفا حداقل یک شغل انتخاب نمائید.");
            return;
        }

        if(this.isfa)
        {
            let reviewDatePersian: string = persianMoment.from(this.reviewDate, 'fa', 'YYYY-MM-DD').format('YYYY/MM/DD');
            this.niazsanjiGroup.reviewDate = moment(reviewDatePersian);

            let scheduleDatePersian: string = persianMoment.from(this.scheduleDate, 'fa', 'YYYY-MM-DD').format('YYYY/MM/DD');
            this.niazsanjiGroup.scheduleDate = moment(scheduleDatePersian);
        }
        else {
            this.niazsanjiGroup.reviewDate = moment(this.reviewDate, DATE_TIME_FORMAT);
            this.niazsanjiGroup.scheduleDate = moment(this.scheduleDate, DATE_TIME_FORMAT);
        }

        if (this.niazsanjiGroup.id !== undefined) {

            this.subscribeToSaveResponse(this.niazsanjiGroupService.update(this.niazsanjiGroup));
        } else {
            this.subscribeToSaveResponse(this.niazsanjiGroupService.create(this.niazsanjiGroup));
        }
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<INiazsanjiGroupMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INiazsanjiGroupMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackJobById(index: number, item: IJobMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackScientificWorkGroupById(index: number, item: IScientificWorkGroupMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get niazsanjiGroup() {
        return this._niazsanjiGroup;
    }

    set niazsanjiGroup(niazsanjiGroup: INiazsanjiGroupMarineSuffix) {
        this._niazsanjiGroup = niazsanjiGroup;

        if(this.isfa)
        {
            if(niazsanjiGroup.reviewDate)
                this.reviewDate = moment(niazsanjiGroup.reviewDate).format(DATE_FORMAT);
            else
                this.reviewDate = moment().format(DATE_FORMAT);

            if(niazsanjiGroup.scheduleDate)
                this.scheduleDate = moment(niazsanjiGroup.scheduleDate).format(DATE_FORMAT);
            else
                this.scheduleDate = moment().format(DATE_FORMAT);
        }
        else {
            this.reviewDate = moment(niazsanjiGroup.reviewDate).format(DATE_TIME_FORMAT);
            this.scheduleDate = moment(niazsanjiGroup.scheduleDate).format(DATE_TIME_FORMAT);
        }
    }
}
