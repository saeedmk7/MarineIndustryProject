import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEducationalModuleJobMarineSuffix } from 'app/shared/model/educational-module-job-marine-suffix.model';
import { EducationalModuleJobMarineSuffixService } from './educational-module-job-marine-suffix.service';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from 'app/entities/job-marine-suffix';
import {NgSelectConfig} from '@ng-select/ng-select';
import {create} from "domain";
import {ifElse} from "@progress/kendo-data-query/dist/npm/funcs";

@Component({
    selector: 'mi-educational-module-job-marine-suffix-update',
    templateUrl: './educational-module-job-marine-suffix-update.component.html'
})
export class EducationalModuleJobMarineSuffixUpdateComponent implements OnInit {
    private _educationalModuleJob: IEducationalModuleJobMarineSuffix;
    isSaving: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];

    jobs: IJobMarineSuffix[];
    tempJobs: IJobMarineSuffix[];

    first3jobCode: string;
    jobCode: string;
    jobTitle: string;
    selectedJobs: any;
    selectedEducationalModules:any;

    disable = true;

    constructor(
        private jhiAlertService: JhiAlertService,
        private educationalModuleJobService: EducationalModuleJobMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private jobService: JobMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private config: NgSelectConfig
    ) {
        config.notFoundText = "پیدا نشد";
    }
    cleanPage(){
        this.first3jobCode = "";
        this.jobCode = "";
        this.jobTitle = "";
        this.selectedJobs = [];
        this.selectedEducationalModules = [];
        this.disable = true;
        document.getElementById("first3jobCode_field").focus();
    }
    isEnter(event){
        if(event.keyCode == 13) {
            this.filterJobs();
        }
    }
    filterJobs(){
        console.log(this.first3jobCode);
        console.log(this.jobs);
        if(this.jobCode)
            this.tempJobs = this.jobs.filter((a:IJobMarineSuffix) => a.jobCode == this.jobCode);
        else if(this.jobTitle)
            this.tempJobs = this.jobs.filter((a:IJobMarineSuffix) => a.title.includes(this.jobTitle));
        else if(this.first3jobCode)
            this.tempJobs = this.jobs.filter((a:IJobMarineSuffix) => a.jobCode.startsWith(this.first3jobCode));
        console.log(this.tempJobs);
        this.selectedJobs = this.tempJobs;
        this.disable = false;
    }
    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalModuleJob }) => {
            this.educationalModuleJob = educationalModuleJob;
        });
        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;

        let createEducationalModuleJobs: IEducationalModuleJobMarineSuffix[] = [];
        for (let selectedJob of this.selectedJobs) {
            for (let selectedEducationalModule of this.selectedEducationalModules) {
                let createEducationalModuleJob: IEducationalModuleJobMarineSuffix = {};
                createEducationalModuleJob.jobId = selectedJob.id;
                createEducationalModuleJob.educationalModuleId = selectedEducationalModule.id;
                createEducationalModuleJobs.push(createEducationalModuleJob);
            }
        }
        this.subscribeToSaveResponse(this.educationalModuleJobService.createBulk(createEducationalModuleJobs));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalModuleJobMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalModuleJobMarineSuffix>) => this.onSaveSuccess(),
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

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackJobById(index: number, item: IJobMarineSuffix) {
        return item.id;
    }
    get educationalModuleJob() {
        return this._educationalModuleJob;
    }

    set educationalModuleJob(educationalModuleJob: IEducationalModuleJobMarineSuffix) {
        this._educationalModuleJob = educationalModuleJob;
    }
}
