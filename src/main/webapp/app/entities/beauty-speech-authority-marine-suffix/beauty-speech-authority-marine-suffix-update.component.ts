import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IBeautySpeechAuthorityMarineSuffix } from 'app/shared/model/beauty-speech-authority-marine-suffix.model';
import { BeautySpeechAuthorityMarineSuffixService } from './beauty-speech-authority-marine-suffix.service';
import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';
import { BeautySpeechMarineSuffixService } from 'app/entities/beauty-speech-marine-suffix';

@Component({
    selector: 'mi-beauty-speech-authority-marine-suffix-update',
    templateUrl: './beauty-speech-authority-marine-suffix-update.component.html'
})
export class BeautySpeechAuthorityMarineSuffixUpdateComponent implements OnInit {
    beautySpeechAuthority: IBeautySpeechAuthorityMarineSuffix;
    isSaving: boolean;

    beautyspeeches: IBeautySpeechMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected beautySpeechAuthorityService: BeautySpeechAuthorityMarineSuffixService,
        protected beautySpeechService: BeautySpeechMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ beautySpeechAuthority }) => {
            this.beautySpeechAuthority = beautySpeechAuthority;
            this.createDate =
                this.beautySpeechAuthority.createDate != null ? this.beautySpeechAuthority.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.beautySpeechAuthority.modifyDate != null ? this.beautySpeechAuthority.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.beautySpeechService.query().subscribe(
            (res: HttpResponse<IBeautySpeechMarineSuffix[]>) => {
                this.beautyspeeches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.beautySpeechAuthority.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.beautySpeechAuthority.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.beautySpeechAuthority.id !== undefined) {
            this.subscribeToSaveResponse(this.beautySpeechAuthorityService.update(this.beautySpeechAuthority));
        } else {
            this.subscribeToSaveResponse(this.beautySpeechAuthorityService.create(this.beautySpeechAuthority));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeautySpeechAuthorityMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IBeautySpeechAuthorityMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackBeautySpeechById(index: number, item: IBeautySpeechMarineSuffix) {
        return item.id;
    }
}
