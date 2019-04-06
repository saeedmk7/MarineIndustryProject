import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';
import { BeautySpeechMarineSuffixService } from './beauty-speech-marine-suffix.service';

@Component({
    selector: 'mi-beauty-speech-marine-suffix-update',
    templateUrl: './beauty-speech-marine-suffix-update.component.html'
})
export class BeautySpeechMarineSuffixUpdateComponent implements OnInit {
    private _beautySpeech: IBeautySpeechMarineSuffix;
    isSaving: boolean;
    showDate: string;


    constructor(private beautySpeechService: BeautySpeechMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ beautySpeech }) => {
            this.beautySpeech = beautySpeech;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.beautySpeech.showDate = moment(this.showDate, DATE_TIME_FORMAT);
        if (this.beautySpeech.id !== undefined) {
            this.subscribeToSaveResponse(this.beautySpeechService.update(this.beautySpeech));
        } else {
            this.subscribeToSaveResponse(this.beautySpeechService.create(this.beautySpeech));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBeautySpeechMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IBeautySpeechMarineSuffix>) => this.onSaveSuccess(),
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
    get beautySpeech() {
        return this._beautySpeech;
    }

    set beautySpeech(beautySpeech: IBeautySpeechMarineSuffix) {
        this._beautySpeech = beautySpeech;
        this.showDate = moment(beautySpeech.showDate).format(DATE_TIME_FORMAT);
    }
}
