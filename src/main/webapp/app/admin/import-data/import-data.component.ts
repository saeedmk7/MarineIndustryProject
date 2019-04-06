import { Component, OnInit } from '@angular/core';

import {ImportDataService} from './import-data.service';
import {ImportDataRequestModel} from "app/admin/import-data/import-data-request.model";
import {ImportDataResponseModel} from "app/admin/import-data/import-data-response.model";
import {JhiDataUtils} from "ng-jhipster";
import {HttpEventType, HttpResponse} from "@angular/common/http";

@Component({
    selector: 'import-data',
    templateUrl: './import-data.component.html'
})
export class ImportDataComponent implements OnInit {
    importDataRequest: ImportDataRequestModel= {};
    importDataResponse: ImportDataResponseModel;
    isSaving: boolean;

    file: File;
    progress: { percentage: number } = { percentage: 0 }

    warning: string;
    success: string;
    constructor(private importDataService: ImportDataService) {
    }

    ngOnInit() {
        const a = 0;
    }
    setFileData(event) {
        debugger;
        this.isSaving = false;
        this.warning = "";
        if (event && event.target.files && event.target.files[0]) {
            this.file = event.target.files[0];
            let type = this.file.name.split('.')[this.file.name.split('.').length - 1];
            if(!type.startsWith('xls'))
            {
                this.isSaving = true;
                this.warning = "فرمت فایل انتخابی اشتباه است.";
            }
            else{
                this.importDataRequest.fileDoc = this.file;
            }
        }
    }
    save() {
        this.progress.percentage = 0;
        this.isSaving = true;

        let formdata: FormData = new FormData();

        formdata.append('file', this.file);
        formdata.append('entityName', this.importDataRequest.entityType);
        this.importDataService.create(formdata).subscribe(event =>{
                if(event.type === HttpEventType.UploadProgress){
                    this.progress.percentage = Math.round(100 * event.loaded / event.total);
                }
                else if(event instanceof HttpResponse){
                    this.onSaveSuccess(event);
                }
            },
            () => this.onSaveError());

        /*this.subscribeToSaveResponse();*/
    }
    private onSaveSuccess(event) {
        debugger;
        this.isSaving = false;

        this.success = event.body;
    }

    private onSaveError() {

        this.isSaving = false;
    }
    previousState() {
        window.history.back();
    }
}
