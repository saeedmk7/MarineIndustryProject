import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';
import { ResourceMarineSuffixService } from './resource-marine-suffix.service';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';

@Component({
    selector: 'mi-resource-marine-suffix-update',
    templateUrl: './resource-marine-suffix-update.component.html'
})
export class ResourceMarineSuffixUpdateComponent implements OnInit {
    private _resource: IResourceMarineSuffix;
    isSaving: boolean;

    educationalmodules: IEducationalModuleMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private resourceService: ResourceMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resource }) => {
            this.resource = resource;
        });
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
        if (this.resource.id !== undefined) {
            this.subscribeToSaveResponse(this.resourceService.update(this.resource));
        } else {
            this.subscribeToSaveResponse(this.resourceService.create(this.resource));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResourceMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IResourceMarineSuffix>) => this.onSaveSuccess(),
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
    get resource() {
        return this._resource;
    }

    set resource(resource: IResourceMarineSuffix) {
        this._resource = resource;
    }
}
