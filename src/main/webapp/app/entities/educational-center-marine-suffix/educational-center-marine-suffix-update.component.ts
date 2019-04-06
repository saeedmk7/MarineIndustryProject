import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { EducationalCenterMarineSuffixService } from './educational-center-marine-suffix.service';
import { IActivityAreaMarineSuffix } from 'app/shared/model/activity-area-marine-suffix.model';
import { ActivityAreaMarineSuffixService } from 'app/entities/activity-area-marine-suffix';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';

@Component({
    selector: 'mi-educational-center-marine-suffix-update',
    templateUrl: './educational-center-marine-suffix-update.component.html'
})
export class EducationalCenterMarineSuffixUpdateComponent implements OnInit {
    private _educationalCenter: IEducationalCenterMarineSuffix;
    isSaving: boolean;

    activityareas: IActivityAreaMarineSuffix[];


    constructor(
        private jhiAlertService: JhiAlertService,
        private educationalCenterService: EducationalCenterMarineSuffixService,
        private activityAreaService: ActivityAreaMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        public router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ educationalCenter }) => {
            this.educationalCenter = educationalCenter;
        });
        this.activityAreaService.query().subscribe(
            (res: HttpResponse<IActivityAreaMarineSuffix[]>) => {
                this.activityareas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    change(i){
        this.router.navigateByUrl(i);
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.educationalCenter.status = 0;

        if (this.educationalCenter.id !== undefined) {
            this.subscribeToSaveResponse(this.educationalCenterService.update(this.educationalCenter));
        } else {
            this.subscribeToSaveResponse(this.educationalCenterService.create(this.educationalCenter));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEducationalCenterMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEducationalCenterMarineSuffix>) => this.onSaveSuccess(),
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackActivityAreaById(index: number, item: IActivityAreaMarineSuffix) {
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
    get educationalCenter() {
        return this._educationalCenter;
    }

    set educationalCenter(educationalCenter: IEducationalCenterMarineSuffix) {
        this._educationalCenter = educationalCenter;
    }
}
