import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRadehMarineSuffix } from 'app/shared/model/radeh-marine-suffix.model';
import { RadehMarineSuffixService } from './radeh-marine-suffix.service';

@Component({
    selector: 'mi-radeh-marine-suffix-update',
    templateUrl: './radeh-marine-suffix-update.component.html'
})
export class RadehMarineSuffixUpdateComponent implements OnInit {
    private _radeh: IRadehMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private radehService: RadehMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ radeh }) => {
            this.radeh = radeh;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.radeh.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.radeh.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.radeh.id !== undefined) {
            this.subscribeToSaveResponse(this.radehService.update(this.radeh));
        } else {
            this.subscribeToSaveResponse(this.radehService.create(this.radeh));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRadehMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IRadehMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get radeh() {
        return this._radeh;
    }

    set radeh(radeh: IRadehMarineSuffix) {
        this._radeh = radeh;
        this.createDate = moment(radeh.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(radeh.modifyDate).format(DATE_TIME_FORMAT);
    }
}
