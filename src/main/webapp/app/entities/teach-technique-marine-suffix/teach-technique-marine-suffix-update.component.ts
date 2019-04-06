import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ITeachTechniqueMarineSuffix } from 'app/shared/model/teach-technique-marine-suffix.model';
import { TeachTechniqueMarineSuffixService } from './teach-technique-marine-suffix.service';

@Component({
    selector: 'mi-teach-technique-marine-suffix-update',
    templateUrl: './teach-technique-marine-suffix-update.component.html'
})
export class TeachTechniqueMarineSuffixUpdateComponent implements OnInit {
    private _teachTechnique: ITeachTechniqueMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private teachTechniqueService: TeachTechniqueMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ teachTechnique }) => {
            this.teachTechnique = teachTechnique;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.teachTechnique.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.teachTechnique.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.teachTechnique.id !== undefined) {
            this.subscribeToSaveResponse(this.teachTechniqueService.update(this.teachTechnique));
        } else {
            this.subscribeToSaveResponse(this.teachTechniqueService.create(this.teachTechnique));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITeachTechniqueMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ITeachTechniqueMarineSuffix>) => this.onSaveSuccess(),
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
    get teachTechnique() {
        return this._teachTechnique;
    }

    set teachTechnique(teachTechnique: ITeachTechniqueMarineSuffix) {
        this._teachTechnique = teachTechnique;
        this.createDate = moment(teachTechnique.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(teachTechnique.modifyDate).format(DATE_TIME_FORMAT);
    }
}
