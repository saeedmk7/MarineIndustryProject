import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import Bytes = jest.Bytes;
import {IReportMarineSuffix} from "app/shared/model/report-marine-suffix.model";
import {trigger} from "@angular/animations";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";

@Component({
    selector: 'mi-planning-marine-suffix-update',
    templateUrl: './planning-marine-suffix-update.component.html'
})
export class PlanningMarineSuffixUpdateComponent implements OnInit {
    private _finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;
    _report : IReportMarineSuffix;
    isSaving: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];


    constructor(
        private jhiAlertService: JhiAlertService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private dataUtils: JhiDataUtils
    ) { }

    ngOnInit() {
        this.isSaving = false;

        this.activatedRoute.data.subscribe(({ finalNiazsanjiReport }) => {
            this.finalNiazsanjiReport = finalNiazsanjiReport;
        });
        this._report = new class implements IReportMarineSuffix {
            fileDoc: any;
            fileDocContentType: string;
        };
        if(this.educationalModuleService.educationalModules) {
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else{
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    change(i){
        this.router.navigateByUrl(i);
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.finalNiazsanjiReport.status = 0;
        if (this.finalNiazsanjiReport.id !== undefined) {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportService.update(this.finalNiazsanjiReport));
        } else {
            this.subscribeToSaveResponse(this.finalNiazsanjiReportService.create(this.finalNiazsanjiReport));
        }
    }
    makeReport(i){
        let id = i.finalNiazsanjiReport.educationalModuleId;
        this.finalNiazsanjiReportService.report(id).subscribe((res: HttpResponse<IReportMarineSuffix>)=>{
            this._report = res.body;
        })
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }
    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinalNiazsanjiReportMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => this.onSaveSuccess(),
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

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
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
    get finalNiazsanjiReport() {
        return this._finalNiazsanjiReport;
    }

    set finalNiazsanjiReport(finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix) {
        this._finalNiazsanjiReport = finalNiazsanjiReport;
    }
    /*set report(report: IReportMarineSuffix) {
        this._report = report;
    }*/
}
