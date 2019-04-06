import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { FieldOfStudyMarineSuffixService } from './field-of-study-marine-suffix.service';

@Component({
    selector: 'mi-field-of-study-marine-suffix-update',
    templateUrl: './field-of-study-marine-suffix-update.component.html'
})
export class FieldOfStudyMarineSuffixUpdateComponent implements OnInit {
    private _fieldOfStudy: IFieldOfStudyMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private fieldOfStudyService: FieldOfStudyMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fieldOfStudy }) => {
            this.fieldOfStudy = fieldOfStudy;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.fieldOfStudy.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.fieldOfStudy.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.fieldOfStudy.id !== undefined) {
            this.subscribeToSaveResponse(this.fieldOfStudyService.update(this.fieldOfStudy));
        } else {
            this.subscribeToSaveResponse(this.fieldOfStudyService.create(this.fieldOfStudy));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFieldOfStudyMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFieldOfStudyMarineSuffix>) => this.onSaveSuccess(),
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
    get fieldOfStudy() {
        return this._fieldOfStudy;
    }

    set fieldOfStudy(fieldOfStudy: IFieldOfStudyMarineSuffix) {
        this._fieldOfStudy = fieldOfStudy;
        this.createDate = moment(fieldOfStudy.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(fieldOfStudy.modifyDate).format(DATE_TIME_FORMAT);
    }
}
