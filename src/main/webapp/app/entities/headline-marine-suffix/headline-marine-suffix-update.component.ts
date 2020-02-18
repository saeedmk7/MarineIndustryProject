import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IHeadlineMarineSuffix } from 'app/shared/model/headline-marine-suffix.model';
import { HeadlineMarineSuffixService } from './headline-marine-suffix.service';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';

@Component({
    selector: 'mi-headline-marine-suffix-update',
    templateUrl: './headline-marine-suffix-update.component.html'
})
export class HeadlineMarineSuffixUpdateComponent implements OnInit {
    headline: IHeadlineMarineSuffix;
    isSaving: boolean;

    requesteducationalmodules: IRequestEducationalModuleMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected headlineService: HeadlineMarineSuffixService,
        protected requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ headline }) => {
            this.headline = headline;
            this.createDate = this.headline.createDate != null ? this.headline.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.headline.modifyDate != null ? this.headline.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.requestEducationalModuleService.query().subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix[]>) => {
                this.requesteducationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.headline.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.headline.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.headline.id !== undefined) {
            this.subscribeToSaveResponse(this.headlineService.update(this.headline));
        } else {
            this.subscribeToSaveResponse(this.headlineService.create(this.headline));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IHeadlineMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IHeadlineMarineSuffix>) => this.onSaveSuccess(),
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

    trackRequestEducationalModuleById(index: number, item: IRequestEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }
}
