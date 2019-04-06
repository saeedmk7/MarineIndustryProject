import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IActivityAreaMarineSuffix } from 'app/shared/model/activity-area-marine-suffix.model';
import { ActivityAreaMarineSuffixService } from './activity-area-marine-suffix.service';
import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { EducationalCenterMarineSuffixService } from 'app/entities/educational-center-marine-suffix';

@Component({
    selector: 'mi-activity-area-marine-suffix-update',
    templateUrl: './activity-area-marine-suffix-update.component.html'
})
export class ActivityAreaMarineSuffixUpdateComponent implements OnInit {
    private _activityArea: IActivityAreaMarineSuffix;
    isSaving: boolean;

    educationalcenters: IEducationalCenterMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private activityAreaService: ActivityAreaMarineSuffixService,
        private educationalCenterService: EducationalCenterMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ activityArea }) => {
            this.activityArea = activityArea;
        });
        this.educationalCenterService.query().subscribe(
            (res: HttpResponse<IEducationalCenterMarineSuffix[]>) => {
                this.educationalcenters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.activityArea.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.activityArea.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.activityArea.id !== undefined) {
            this.subscribeToSaveResponse(this.activityAreaService.update(this.activityArea));
        } else {
            this.subscribeToSaveResponse(this.activityAreaService.create(this.activityArea));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IActivityAreaMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IActivityAreaMarineSuffix>) => this.onSaveSuccess(),
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

    trackEducationalCenterById(index: number, item: IEducationalCenterMarineSuffix) {
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
    get activityArea() {
        return this._activityArea;
    }

    set activityArea(activityArea: IActivityAreaMarineSuffix) {
        this._activityArea = activityArea;
        this.createDate = moment(activityArea.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(activityArea.modifyDate).format(DATE_TIME_FORMAT);
    }
}
