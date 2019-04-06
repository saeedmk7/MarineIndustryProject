import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRasteMarineSuffix } from 'app/shared/model/raste-marine-suffix.model';
import { RasteMarineSuffixService } from './raste-marine-suffix.service';

@Component({
    selector: 'mi-raste-marine-suffix-update',
    templateUrl: './raste-marine-suffix-update.component.html'
})
export class RasteMarineSuffixUpdateComponent implements OnInit {
    private _raste: IRasteMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private rasteService: RasteMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ raste }) => {
            this.raste = raste;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.raste.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.raste.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.raste.id !== undefined) {
            this.subscribeToSaveResponse(this.rasteService.update(this.raste));
        } else {
            this.subscribeToSaveResponse(this.rasteService.create(this.raste));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRasteMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IRasteMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get raste() {
        return this._raste;
    }

    set raste(raste: IRasteMarineSuffix) {
        this._raste = raste;
        this.createDate = moment(raste.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(raste.modifyDate).format(DATE_TIME_FORMAT);
    }
}
