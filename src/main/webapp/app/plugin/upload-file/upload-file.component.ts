import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import {UploadFileService} from "./upload-file.service";
import {JhiAlertService} from "ng-jhipster";
import {error} from "util";
import {Observable} from "rxjs/Observable";
import {result} from './result.model';

@Component({
    selector: 'upload-file',
    templateUrl: './upload-file.component.html'
})
export class FormUploadComponent implements OnInit {

    selectedFiles: FileList
    currentFileUpload: File
    progress: { percentage: number } = { percentage: 0 }

    constructor(private uploadService: UploadFileService,private alertService: JhiAlertService) {

    }

    ngOnInit() {
    }

    selectFile(event) {

        var file = event.target.files[0];
        var type = file.name.split('.')[file.name.split('.').length - 1];
        if(type.startsWith('xls'))
        {
            this.selectedFiles = event.target.files;
        }
        else{
            this.alertService.error('error.isexcelvalid','','up');
        }
        /*if(this.selectedFiles.)*/
    }

    upload(entityName){

        this.progress.percentage = 0;

        this.currentFileUpload = this.selectedFiles.item(0);
        /*this.currentFileUpload['newname'] = entityName + '-' + this.currentFileUpload.name;*/
        this.uploadService.pushFileToStorage(this.currentFileUpload,entityName).subscribe(event => {

            this.progress.percentage = 100;
            if(event.isOk)
                this.alertService.success('error.successfileupload',event.message,'up');
            else
                this.alertService.error('error.failedfileupload',event.message,'up');
        });

        this.selectedFiles = undefined
    }
}
