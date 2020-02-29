import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';
import { AssessmentMethodMarineSuffixService } from './assessment-method-marine-suffix.service';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix';

@Component({
    selector: 'mi-assessment-method-marine-suffix-update',
    templateUrl: './assessment-method-marine-suffix-update.component.html'
})
export class AssessmentMethodMarineSuffixUpdateComponent implements OnInit {
    assessmentMethod: IAssessmentMethodMarineSuffix;
    isSaving: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];

    requesteducationalmodules: IRequestEducationalModuleMarineSuffix[];
    /*createDate: string;
    modifyDate: string;*/

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected assessmentMethodService: AssessmentMethodMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ assessmentMethod }) => {
            this.assessmentMethod = assessmentMethod;
            /*this.createDate = this.assessmentMethod.createDate != null ? this.assessmentMethod.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.assessmentMethod.modifyDate != null ? this.assessmentMethod.modifyDate.format(DATE_TIME_FORMAT) : null;*/
        });
        /*this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.requestEducationalModuleService.query().subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix[]>) => {
                this.requesteducationalmodules = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        /*this.assessmentMethod.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.assessmentMethod.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;*/
        if (this.assessmentMethod.id !== undefined) {
            this.subscribeToSaveResponse(this.assessmentMethodService.update(this.assessmentMethod));
        } else {
            this.subscribeToSaveResponse(this.assessmentMethodService.create(this.assessmentMethod));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssessmentMethodMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IAssessmentMethodMarineSuffix>) => this.onSaveSuccess(),
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

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackRequestEducationalModuleById(index: number, item: IRequestEducationalModuleMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
