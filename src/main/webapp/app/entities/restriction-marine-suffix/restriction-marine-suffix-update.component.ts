import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from './restriction-marine-suffix.service';
import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';

@Component({
    selector: 'mi-restriction-marine-suffix-update',
    templateUrl: './restriction-marine-suffix-update.component.html'
})
export class RestrictionMarineSuffixUpdateComponent implements OnInit {
    restriction: IRestrictionMarineSuffix;
    isSaving: boolean;

    requesteducationalmodules: IRequestEducationalModuleMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ restriction }) => {
            this.restriction = restriction;
            this.createDate = this.restriction.createDate != null ? this.restriction.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.restriction.modifyDate != null ? this.restriction.modifyDate.format(DATE_TIME_FORMAT) : null;
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
        this.restriction.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.restriction.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.restriction.id !== undefined) {
            this.subscribeToSaveResponse(this.restrictionService.update(this.restriction));
        } else {
            this.subscribeToSaveResponse(this.restrictionService.create(this.restriction));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IRestrictionMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix>) => this.onSaveSuccess(),
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
