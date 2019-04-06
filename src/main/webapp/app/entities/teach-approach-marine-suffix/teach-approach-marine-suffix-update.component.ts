import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';
import { TeachApproachMarineSuffixService } from './teach-approach-marine-suffix.service';

@Component({
    selector: 'mi-teach-approach-marine-suffix-update',
    templateUrl: './teach-approach-marine-suffix-update.component.html'
})
export class TeachApproachMarineSuffixUpdateComponent implements OnInit {
    private _teachApproach: ITeachApproachMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private teachApproachService: TeachApproachMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teachApproach }) => {
            this.teachApproach = teachApproach;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.teachApproach.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.teachApproach.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.teachApproach.id !== undefined) {
            this.subscribeToSaveResponse(this.teachApproachService.update(this.teachApproach));
        } else {
            this.subscribeToSaveResponse(this.teachApproachService.create(this.teachApproach));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITeachApproachMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeachApproachMarineSuffix>) => this.onSaveSuccess(),
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
    get teachApproach() {
        return this._teachApproach;
    }

    set teachApproach(teachApproach: ITeachApproachMarineSuffix) {
        this._teachApproach = teachApproach;
        this.createDate = moment(teachApproach.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(teachApproach.modifyDate).format(DATE_TIME_FORMAT);
    }
}
