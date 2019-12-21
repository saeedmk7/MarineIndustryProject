import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMediaProductTypeMarineSuffix } from 'app/shared/model/media-product-type-marine-suffix.model';
import { MediaProductTypeMarineSuffixService } from './media-product-type-marine-suffix.service';

@Component({
    selector: 'mi-media-product-type-marine-suffix-update',
    templateUrl: './media-product-type-marine-suffix-update.component.html'
})
export class MediaProductTypeMarineSuffixUpdateComponent implements OnInit {
    mediaProductType: IMediaProductTypeMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(protected mediaProductTypeService: MediaProductTypeMarineSuffixService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mediaProductType }) => {
            this.mediaProductType = mediaProductType;
            this.createDate = this.mediaProductType.createDate != null ? this.mediaProductType.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.mediaProductType.modifyDate != null ? this.mediaProductType.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.mediaProductType.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.mediaProductType.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.mediaProductType.id !== undefined) {
            this.subscribeToSaveResponse(this.mediaProductTypeService.update(this.mediaProductType));
        } else {
            this.subscribeToSaveResponse(this.mediaProductTypeService.create(this.mediaProductType));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMediaProductTypeMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMediaProductTypeMarineSuffix>) => this.onSaveSuccess(),
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
}
