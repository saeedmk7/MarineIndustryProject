import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITeachTypeMarineSuffix } from 'app/shared/model/teach-type-marine-suffix.model';
import { TeachTypeMarineSuffixService } from './teach-type-marine-suffix.service';

@Component({
    selector: 'mi-teach-type-marine-suffix-update',
    templateUrl: './teach-type-marine-suffix-update.component.html'
})
export class TeachTypeMarineSuffixUpdateComponent implements OnInit {
    private _teachType: ITeachTypeMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private teachTypeService: TeachTypeMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teachType }) => {
            this.teachType = teachType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.teachType.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.teachType.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.teachType.id !== undefined) {
            this.subscribeToSaveResponse(this.teachTypeService.update(this.teachType));
        } else {
            this.subscribeToSaveResponse(this.teachTypeService.create(this.teachType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITeachTypeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeachTypeMarineSuffix>) => this.onSaveSuccess(),
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
    get teachType() {
        return this._teachType;
    }

    set teachType(teachType: ITeachTypeMarineSuffix) {
        this._teachType = teachType;
        this.createDate = moment(teachType.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(teachType.modifyDate).format(DATE_TIME_FORMAT);
    }
}
