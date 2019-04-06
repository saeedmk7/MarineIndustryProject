import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IAnnouncementMarineSuffix } from 'app/shared/model/announcement-marine-suffix.model';
import { AnnouncementMarineSuffixService } from './announcement-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';

@Component({
    selector: 'mi-announcement-marine-suffix-update',
    templateUrl: './announcement-marine-suffix-update.component.html'
})
export class AnnouncementMarineSuffixUpdateComponent implements OnInit {
    private _announcement: IAnnouncementMarineSuffix;
    isSaving: boolean;
    documents: IDocumentMarineSuffix[];
    createDate: string;
    modifyDate: string;
    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private announcementService: AnnouncementMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ announcement }) => {
            this.announcement = announcement;
        });
    }
    writeToHidden($event){

        this.announcement.compeleteText = $event;
    }
    donothing(){
        return;
    }
    previousState() {
        window.history.back();
    }
    ss(){

        this.canSave= true
    }
    canSave: boolean = false
    public value: string= "";
    save() {

        if(!this.canSave)
            return;
        this.canSave = false;
        this.isSaving = true;
        this.announcement.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.announcement.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.announcement.id !== undefined) {
            this.subscribeToSaveResponse(this.announcementService.update(this.announcement));
        } else {
            this.subscribeToSaveResponse(this.announcementService.create(this.announcement));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAnnouncementMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IAnnouncementMarineSuffix>) => this.onSaveSuccess(),
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

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
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
    get announcement() {
        return this._announcement;
    }

    set announcement(announcement: IAnnouncementMarineSuffix) {
        this._announcement = announcement;
        this.value = announcement.compeleteText;
        this.createDate = moment(announcement.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(announcement.modifyDate).format(DATE_TIME_FORMAT);
    }
}
