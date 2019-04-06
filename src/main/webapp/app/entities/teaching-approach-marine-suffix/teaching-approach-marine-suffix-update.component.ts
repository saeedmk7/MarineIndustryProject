import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from './teaching-approach-marine-suffix.service';

@Component({
    selector: 'mi-teaching-approach-marine-suffix-update',
    templateUrl: './teaching-approach-marine-suffix-update.component.html'
})
export class TeachingApproachMarineSuffixUpdateComponent implements OnInit {
    private _teachingApproach: ITeachingApproachMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private teachingApproachService: TeachingApproachMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teachingApproach }) => {
            this.teachingApproach = teachingApproach;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.teachingApproach.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.teachingApproach.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.teachingApproach.id !== undefined) {
            this.subscribeToSaveResponse(this.teachingApproachService.update(this.teachingApproach));
        } else {
            this.subscribeToSaveResponse(this.teachingApproachService.create(this.teachingApproach));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITeachingApproachMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix>) => this.onSaveSuccess(),
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
    get teachingApproach() {
        return this._teachingApproach;
    }

    set teachingApproach(teachingApproach: ITeachingApproachMarineSuffix) {
        this._teachingApproach = teachingApproach;
        this.createDate = moment(teachingApproach.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(teachingApproach.modifyDate).format(DATE_TIME_FORMAT);
    }
}
