import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';
import { SecurityLevelMarineSuffixService } from './security-level-marine-suffix.service';

@Component({
    selector: 'mi-security-level-marine-suffix-update',
    templateUrl: './security-level-marine-suffix-update.component.html'
})
export class SecurityLevelMarineSuffixUpdateComponent implements OnInit {
    private _securityLevel: ISecurityLevelMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private securityLevelService: SecurityLevelMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ securityLevel }) => {
            this.securityLevel = securityLevel;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.securityLevel.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.securityLevel.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.securityLevel.id !== undefined) {
            this.subscribeToSaveResponse(this.securityLevelService.update(this.securityLevel));
        } else {
            this.subscribeToSaveResponse(this.securityLevelService.create(this.securityLevel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISecurityLevelMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ISecurityLevelMarineSuffix>) => this.onSaveSuccess(),
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
    get securityLevel() {
        return this._securityLevel;
    }

    set securityLevel(securityLevel: ISecurityLevelMarineSuffix) {
        this._securityLevel = securityLevel;
        this.createDate = moment(securityLevel.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(securityLevel.modifyDate).format(DATE_TIME_FORMAT);
    }
}
