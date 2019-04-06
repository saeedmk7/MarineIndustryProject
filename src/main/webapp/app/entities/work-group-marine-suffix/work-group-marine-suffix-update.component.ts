import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';
import { WorkGroupMarineSuffixService } from './work-group-marine-suffix.service';

@Component({
    selector: 'mi-work-group-marine-suffix-update',
    templateUrl: './work-group-marine-suffix-update.component.html'
})
export class WorkGroupMarineSuffixUpdateComponent implements OnInit {
    private _workGroup: IWorkGroupMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private workGroupService: WorkGroupMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ workGroup }) => {
            this.workGroup = workGroup;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.workGroup.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.workGroup.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.workGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.workGroupService.update(this.workGroup));
        } else {
            this.subscribeToSaveResponse(this.workGroupService.create(this.workGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IWorkGroupMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IWorkGroupMarineSuffix>) => this.onSaveSuccess(),
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
    get workGroup() {
        return this._workGroup;
    }

    set workGroup(workGroup: IWorkGroupMarineSuffix) {
        this._workGroup = workGroup;
        this.createDate = moment(workGroup.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(workGroup.modifyDate).format(DATE_TIME_FORMAT);
    }
}
