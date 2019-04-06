import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from './qualification-marine-suffix.service';

@Component({
    selector: 'mi-qualification-marine-suffix-update',
    templateUrl: './qualification-marine-suffix-update.component.html'
})
export class QualificationMarineSuffixUpdateComponent implements OnInit {
    private _qualification: IQualificationMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private qualificationService: QualificationMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ qualification }) => {
            this.qualification = qualification;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.qualification.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.qualification.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.qualification.id !== undefined) {
            this.subscribeToSaveResponse(this.qualificationService.update(this.qualification));
        } else {
            this.subscribeToSaveResponse(this.qualificationService.create(this.qualification));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IQualificationMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IQualificationMarineSuffix>) => this.onSaveSuccess(),
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
    get qualification() {
        return this._qualification;
    }

    set qualification(qualification: IQualificationMarineSuffix) {
        this._qualification = qualification;
        this.createDate = moment(qualification.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(qualification.modifyDate).format(DATE_TIME_FORMAT);
    }
}
