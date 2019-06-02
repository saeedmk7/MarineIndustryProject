import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {HttpResponse, HttpErrorResponse, HttpEventType} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import { IJamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';
import { JamHelpMarineSuffixService } from './jam-help-marine-suffix.service';
import {JamHelpAuthorityMarineSuffixService} from "app/entities/jam-help-authority-marine-suffix";
import {AuthorityService} from "app/admin/authority";
import {IAuthority} from "app/shared/model/authority.model";

@Component({
    selector: 'mi-jam-help-marine-suffix-update',
    templateUrl: './jam-help-marine-suffix-update.component.html'
})
export class JamHelpMarineSuffixUpdateComponent implements OnInit {
    jamHelp: IJamHelpMarineSuffix;
    isSaving: boolean;
    authorities: IAuthority[];
    selectedAuthorities: IAuthority[];

    progress: { percentage: number } = { percentage: 0 }
    file: File;
    fileHasError: boolean = true;
    fileMessage: string;
    message: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jamHelpService: JamHelpMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected jamHelpAuthorityService: JamHelpAuthorityMarineSuffixService,
        protected authorityService: AuthorityService,
        private jhiAlertService: JhiAlertService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.authorityService
            .authorities()
            .subscribe(
                (res: HttpResponse<IAuthority[]>) => this.authorities = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.activatedRoute.data.subscribe(({ jamHelp }) => {
            this.jamHelp = jamHelp;
        });
    }

    previousState() {
        window.history.back();
    }
    setFileData(event, entity, field, isImage) {

        this.fileMessage = "";
        this.fileHasError = true;
        //this.dataUtils.setFileData(event, entity, field, isImage);
        if (event && event.target.files && event.target.files[0]) {
            this.file = event.target.files[0];
            this.validateFile(this.file);
        }
        else{
            this.fileHasError = true;
            this.fileMessage = "انتخاب فایل اجباری است.";
        }
    }
    validateFile(file){
        //file.name.split('.')[file.name.split('.').length-1] == 'rar'
        if((file.size / 1024 / 1024) < 400) {
            this.fileHasError = false;
            this.fileMessage = "فایل معتبر است.";
        }
        else{
            this.fileHasError = true;
            this.fileMessage = "حجم فایل بیش از حد مجاز است.";
        }
    }

    save() {
        this.isSaving = true;
        this.message = "";
        this.jamHelp.authorityNames = this.selectedAuthorities.join(',');
        if (this.jamHelp.id !== undefined) {
            this.subscribeToSaveResponse(this.jamHelpService.update(this.jamHelp));
        } else {
            let formdata: FormData = new FormData();

            formdata.append('file', this.file);
            this.jamHelpService.uploadFile(formdata).subscribe(event =>{
                    if(event.type === HttpEventType.UploadProgress){
                        this.progress.percentage = Math.round(100 * event.loaded / event.total);
                    }
                    else if(event instanceof HttpResponse){
                        if(event.body){
                            this.jamHelp.fileDoc = event.body;
                            this.subscribeToSaveResponse(this.jamHelpService.create(this.jamHelp));
                        }
                    }
                },
                () => this.onSaveError());

        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IJamHelpMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IJamHelpMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
