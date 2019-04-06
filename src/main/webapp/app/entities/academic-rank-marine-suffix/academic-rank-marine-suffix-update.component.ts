import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';
import { AcademicRankMarineSuffixService } from './academic-rank-marine-suffix.service';

@Component({
    selector: 'mi-academic-rank-marine-suffix-update',
    templateUrl: './academic-rank-marine-suffix-update.component.html'
})
export class AcademicRankMarineSuffixUpdateComponent implements OnInit {
    private _academicRank: IAcademicRankMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private academicRankService: AcademicRankMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ academicRank }) => {
            this.academicRank = academicRank;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.academicRank.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.academicRank.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.academicRank.id !== undefined) {
            this.subscribeToSaveResponse(this.academicRankService.update(this.academicRank));
        } else {
            this.subscribeToSaveResponse(this.academicRankService.create(this.academicRank));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAcademicRankMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IAcademicRankMarineSuffix>) => this.onSaveSuccess(),
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
    get academicRank() {
        return this._academicRank;
    }

    set academicRank(academicRank: IAcademicRankMarineSuffix) {
        this._academicRank = academicRank;
        this.createDate = moment(academicRank.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(academicRank.modifyDate).format(DATE_TIME_FORMAT);
    }
}
