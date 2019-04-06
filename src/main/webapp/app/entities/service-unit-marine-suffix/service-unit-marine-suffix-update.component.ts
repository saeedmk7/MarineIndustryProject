import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';
import { ServiceUnitMarineSuffixService } from './service-unit-marine-suffix.service';

@Component({
    selector: 'mi-service-unit-marine-suffix-update',
    templateUrl: './service-unit-marine-suffix-update.component.html'
})
export class ServiceUnitMarineSuffixUpdateComponent implements OnInit {
    private _serviceUnit: IServiceUnitMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private serviceUnitService: ServiceUnitMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ serviceUnit }) => {
            this.serviceUnit = serviceUnit;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.serviceUnit.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.serviceUnit.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.serviceUnit.id !== undefined) {
            this.subscribeToSaveResponse(this.serviceUnitService.update(this.serviceUnit));
        } else {
            this.subscribeToSaveResponse(this.serviceUnitService.create(this.serviceUnit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IServiceUnitMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IServiceUnitMarineSuffix>) => this.onSaveSuccess(),
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
    get serviceUnit() {
        return this._serviceUnit;
    }

    set serviceUnit(serviceUnit: IServiceUnitMarineSuffix) {
        this._serviceUnit = serviceUnit;
        this.createDate = moment(serviceUnit.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(serviceUnit.modifyDate).format(DATE_TIME_FORMAT);
    }
}
