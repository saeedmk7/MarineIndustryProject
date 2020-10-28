import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';
import { EducationalCenterCriteriaMarineSuffixService } from './educational-center-criteria-marine-suffix.service';
import { IEducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';
import { EducationalCenterGroupMarineSuffixService } from 'app/entities/educational-center-group-marine-suffix';

@Component({
    selector: 'mi-educational-center-criteria-marine-suffix-update',
    templateUrl: './educational-center-criteria-marine-suffix-update.component.html'
})
export class EducationalCenterCriteriaMarineSuffixUpdateComponent implements OnInit {
    educationalCenterCriteria: IEducationalCenterCriteriaMarineSuffix;
    isSaving: boolean;

    educationalcentergroups: IEducationalCenterGroupMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected educationalCenterCriteriaService: EducationalCenterCriteriaMarineSuffixService,
        protected educationalCenterGroupService: EducationalCenterGroupMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        public router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalCenterCriteria }) => {
            this.educationalCenterCriteria = educationalCenterCriteria;
            this.createDate =
                this.educationalCenterCriteria.createDate != null
                    ? this.educationalCenterCriteria.createDate.format(DATE_TIME_FORMAT)
                    : null;
            this.modifyDate =
                this.educationalCenterCriteria.modifyDate != null
                    ? this.educationalCenterCriteria.modifyDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.educationalCenterGroupService.query().subscribe(
            (res: HttpResponse<IEducationalCenterGroupMarineSuffix[]>) => {
                this.educationalcentergroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    change(i) {
        this.router.navigateByUrl(i);
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.educationalCenterCriteria.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.educationalCenterCriteria.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.educationalCenterCriteria.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalCenterCriteriaService.update(this.educationalCenterCriteria));
        } else {
            this.subscribeToSaveResponse(this.educationalCenterCriteriaService.create(this.educationalCenterCriteria));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalCenterCriteriaMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalCenterCriteriaMarineSuffix>) => this.onSaveSuccess(),
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

    trackEducationalCenterGroupById(index: number, item: IEducationalCenterGroupMarineSuffix) {
        return item.id;
    }
}
