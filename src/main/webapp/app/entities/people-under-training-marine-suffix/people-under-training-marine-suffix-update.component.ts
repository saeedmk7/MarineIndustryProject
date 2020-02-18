import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';
import { PeopleUnderTrainingMarineSuffixService } from './people-under-training-marine-suffix.service';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix';

@Component({
    selector: 'mi-people-under-training-marine-suffix-update',
    templateUrl: './people-under-training-marine-suffix-update.component.html'
})
export class PeopleUnderTrainingMarineSuffixUpdateComponent implements OnInit {
    peopleUnderTraining: IPeopleUnderTrainingMarineSuffix;
    isSaving: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];

    requesteducationalmodules: IRequestEducationalModuleMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected peopleUnderTrainingService: PeopleUnderTrainingMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ peopleUnderTraining }) => {
            this.peopleUnderTraining = peopleUnderTraining;
            this.createDate =
                this.peopleUnderTraining.createDate != null ? this.peopleUnderTraining.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.peopleUnderTraining.modifyDate != null ? this.peopleUnderTraining.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.educationalModuleService.query().subscribe(
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
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.peopleUnderTraining.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.peopleUnderTraining.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.peopleUnderTraining.id !== undefined) {
            this.subscribeToSaveResponse(this.peopleUnderTrainingService.update(this.peopleUnderTraining));
        } else {
            this.subscribeToSaveResponse(this.peopleUnderTrainingService.create(this.peopleUnderTraining));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPeopleUnderTrainingMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPeopleUnderTrainingMarineSuffix>) => this.onSaveSuccess(),
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
