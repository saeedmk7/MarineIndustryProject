import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';
import { BeautySpeechMarineSuffixService } from './beauty-speech-marine-suffix.service';
import {IAuthority} from "app/shared/model/authority.model";
import {AuthorityService} from "app/admin/authority";
import {JamHelpAuthorityMarineSuffixService} from "app/entities/jam-help-authority-marine-suffix";
import {
    BeautySpeechAuthorityMarineSuffix,
    IBeautySpeechAuthorityMarineSuffix
} from "app/shared/model/beauty-speech-authority-marine-suffix.model";
import {BeautySpeechAuthorityMarineSuffixService} from "app/entities/beauty-speech-authority-marine-suffix";
import {IJamHelpAuthorityMarineSuffix} from "app/shared/model/jam-help-authority-marine-suffix.model";
import {JhiAlertService} from "ng-jhipster";

@Component({
    selector: 'mi-beauty-speech-marine-suffix-update',
    templateUrl: './beauty-speech-marine-suffix-update.component.html'
})
export class BeautySpeechMarineSuffixUpdateComponent implements OnInit {
    private _beautySpeech: IBeautySpeechMarineSuffix;
    isSaving: boolean;
    showDate: string;
    authorities: IAuthority[];
    selectedAuthorities: IAuthority[] = [];


    constructor(private beautySpeechService: BeautySpeechMarineSuffixService, private activatedRoute: ActivatedRoute,
                protected beautySpeechAuthorityService: BeautySpeechAuthorityMarineSuffixService,
                private jhiAlertService: JhiAlertService,
                protected authorityService: AuthorityService) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ beautySpeech }) => {
            this.beautySpeech = beautySpeech;
            this.authorityService
                .authorities()
                .subscribe(
                    (res: HttpResponse<IAuthority[]>) => {

                        this.authorities = res.body;
                        let criteria = [{
                            key: 'beautySpeechId.equals',
                            value: this.beautySpeech.id
                        }];
                        this.beautySpeechAuthorityService.query({
                            page: 0,
                            size: 20000,
                            criteria,
                            sort: ["id", "asc"]
                        }).subscribe((resp: HttpResponse<IBeautySpeechAuthorityMarineSuffix[]>) => {

                                const names = resp.body.map(a => a.authorityName);
                                this.selectedAuthorities = this.authorities.filter(a => names.includes(a.name));
                            },
                            (error) => this.onError("موردی یافت نشد."));

                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        });
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.beautySpeech.showDate = moment(this.showDate, DATE_TIME_FORMAT);
        this.beautySpeech.authorityNames = this.selectedAuthorities.join(',');
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
