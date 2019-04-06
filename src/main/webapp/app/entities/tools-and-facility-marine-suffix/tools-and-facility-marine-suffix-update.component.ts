import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IToolsAndFacilityMarineSuffix } from 'app/shared/model/tools-and-facility-marine-suffix.model';
import { ToolsAndFacilityMarineSuffixService } from './tools-and-facility-marine-suffix.service';

@Component({
    selector: 'mi-tools-and-facility-marine-suffix-update',
    templateUrl: './tools-and-facility-marine-suffix-update.component.html'
})
export class ToolsAndFacilityMarineSuffixUpdateComponent implements OnInit {
    private _toolsAndFacility: IToolsAndFacilityMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private toolsAndFacilityService: ToolsAndFacilityMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ toolsAndFacility }) => {
            this.toolsAndFacility = toolsAndFacility;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.toolsAndFacility.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.toolsAndFacility.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.toolsAndFacility.id !== undefined) {
            this.subscribeToSaveResponse(this.toolsAndFacilityService.update(this.toolsAndFacility));
        } else {
            this.subscribeToSaveResponse(this.toolsAndFacilityService.create(this.toolsAndFacility));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IToolsAndFacilityMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IToolsAndFacilityMarineSuffix>) => this.onSaveSuccess(),
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
    get toolsAndFacility() {
        return this._toolsAndFacility;
    }

    set toolsAndFacility(toolsAndFacility: IToolsAndFacilityMarineSuffix) {
        this._toolsAndFacility = toolsAndFacility;
        this.createDate = moment(toolsAndFacility.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(toolsAndFacility.modifyDate).format(DATE_TIME_FORMAT);
    }
}
