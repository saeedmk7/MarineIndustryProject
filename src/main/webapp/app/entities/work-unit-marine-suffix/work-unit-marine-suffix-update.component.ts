import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IWorkUnitMarineSuffix } from 'app/shared/model/work-unit-marine-suffix.model';
import { WorkUnitMarineSuffixService } from './work-unit-marine-suffix.service';
import { IWorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';
import { WorkIndustryMarineSuffixService } from 'app/entities/work-industry-marine-suffix';
import { IWorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';
import { WorkGroupMarineSuffixService } from 'app/entities/work-group-marine-suffix';

@Component({
    selector: 'mi-work-unit-marine-suffix-update',
    templateUrl: './work-unit-marine-suffix-update.component.html'
})
export class WorkUnitMarineSuffixUpdateComponent implements OnInit {
    private _workUnit: IWorkUnitMarineSuffix;
    isSaving: boolean;

    workindustries: IWorkIndustryMarineSuffix[];

    workgroups: IWorkGroupMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private workUnitService: WorkUnitMarineSuffixService,
        private workIndustryService: WorkIndustryMarineSuffixService,
        private workGroupService: WorkGroupMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ workUnit }) => {
            this.workUnit = workUnit;
        });
        this.workIndustryService.query().subscribe(
            (res: HttpResponse<IWorkIndustryMarineSuffix[]>) => {
                this.workindustries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.workGroupService.query().subscribe(
            (res: HttpResponse<IWorkGroupMarineSuffix[]>) => {
                this.workgroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.workUnit.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.workUnit.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.workUnit.id !== undefined) {
            this.subscribeToSaveResponse(this.workUnitService.update(this.workUnit));
        } else {
            this.subscribeToSaveResponse(this.workUnitService.create(this.workUnit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IWorkUnitMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IWorkUnitMarineSuffix>) => this.onSaveSuccess(),
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

    trackWorkIndustryById(index: number, item: IWorkIndustryMarineSuffix) {
        return item.id;
    }

    trackWorkGroupById(index: number, item: IWorkGroupMarineSuffix) {
        return item.id;
    }
    get workUnit() {
        return this._workUnit;
    }

    set workUnit(workUnit: IWorkUnitMarineSuffix) {
        this._workUnit = workUnit;
        this.createDate = moment(workUnit.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(workUnit.modifyDate).format(DATE_TIME_FORMAT);
    }
}
