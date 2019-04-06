import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';
import { WorkIndustryMarineSuffixService } from './work-industry-marine-suffix.service';

@Component({
    selector: 'mi-work-industry-marine-suffix-update',
    templateUrl: './work-industry-marine-suffix-update.component.html'
})
export class WorkIndustryMarineSuffixUpdateComponent implements OnInit {
    private _workIndustry: IWorkIndustryMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private workIndustryService: WorkIndustryMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ workIndustry }) => {
            this.workIndustry = workIndustry;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.workIndustry.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.workIndustry.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.workIndustry.id !== undefined) {
            this.subscribeToSaveResponse(this.workIndustryService.update(this.workIndustry));
        } else {
            this.subscribeToSaveResponse(this.workIndustryService.create(this.workIndustry));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IWorkIndustryMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IWorkIndustryMarineSuffix>) => this.onSaveSuccess(),
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
    get workIndustry() {
        return this._workIndustry;
    }

    set workIndustry(workIndustry: IWorkIndustryMarineSuffix) {
        this._workIndustry = workIndustry;
        this.createDate = moment(workIndustry.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(workIndustry.modifyDate).format(DATE_TIME_FORMAT);
    }
}
