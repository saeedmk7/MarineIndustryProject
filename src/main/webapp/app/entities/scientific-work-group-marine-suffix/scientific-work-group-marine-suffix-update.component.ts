import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { ScientificWorkGroupMarineSuffixService } from './scientific-work-group-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';

@Component({
    selector: 'mi-scientific-work-group-marine-suffix-update',
    templateUrl: './scientific-work-group-marine-suffix-update.component.html'
})
export class ScientificWorkGroupMarineSuffixUpdateComponent implements OnInit {
    private _scientificWorkGroup: IScientificWorkGroupMarineSuffix;
    isSaving: boolean;

    people: IPersonMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private scientificWorkGroupService: ScientificWorkGroupMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ scientificWorkGroup }) => {
            this.scientificWorkGroup = scientificWorkGroup;
        });
        if(this.personService.people){
            this.people = this.personService.people;
        }
        else {
            this.personService.query().subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.scientificWorkGroup.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.scientificWorkGroup.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.scientificWorkGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.scientificWorkGroupService.update(this.scientificWorkGroup));
        } else {
            this.subscribeToSaveResponse(this.scientificWorkGroupService.create(this.scientificWorkGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IScientificWorkGroupMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IScientificWorkGroupMarineSuffix>) => this.onSaveSuccess(),
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

    trackPersonById(index: number, item: IPersonMarineSuffix) {
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
    get scientificWorkGroup() {
        return this._scientificWorkGroup;
    }

    set scientificWorkGroup(scientificWorkGroup: IScientificWorkGroupMarineSuffix) {
        this._scientificWorkGroup = scientificWorkGroup;
        this.createDate = moment(scientificWorkGroup.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(scientificWorkGroup.modifyDate).format(DATE_TIME_FORMAT);
    }
}
