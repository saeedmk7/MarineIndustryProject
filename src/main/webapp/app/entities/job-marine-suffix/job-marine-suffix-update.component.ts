import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpResponse, HttpErrorResponse, HttpEventType} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from './job-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IRasteMarineSuffix } from 'app/shared/model/raste-marine-suffix.model';
import { RasteMarineSuffixService } from 'app/entities/raste-marine-suffix';
import { IRadehMarineSuffix } from 'app/shared/model/radeh-marine-suffix.model';
import { RadehMarineSuffixService } from 'app/entities/radeh-marine-suffix';
import { IJobTypeMarineSuffix } from 'app/shared/model/job-type-marine-suffix.model';
import { JobTypeMarineSuffixService } from 'app/entities/job-type-marine-suffix';
import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { ScientificWorkGroupMarineSuffixService } from 'app/entities/scientific-work-group-marine-suffix';
import { IMainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';
import { MainTaskMarineSuffixService } from 'app/entities/main-task-marine-suffix';
import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import { NiazsanjiGroupMarineSuffixService } from 'app/entities/niazsanji-group-marine-suffix';
import * as persianMoment from 'jalali-moment';

@Component({
    selector: 'mi-job-marine-suffix-update',
    templateUrl: './job-marine-suffix-update.component.html'
})
export class JobMarineSuffixUpdateComponent implements OnInit {
    private _job: IJobMarineSuffix;
    isSaving: boolean;

    finishDateValidation: number;

    documents: IDocumentMarineSuffix[];

    rastes: IRasteMarineSuffix[];

    radehs: IRadehMarineSuffix[];

    jobtypes: IJobTypeMarineSuffix[];

    scientificworkgroups: IScientificWorkGroupMarineSuffix[];

    jobs: IJobMarineSuffix[];

    maintasks: IMainTaskMarineSuffix[];

    niazsanjigroups: INiazsanjiGroupMarineSuffix[];

    progress: { percentage: number } = { percentage: 0 }
    file: File;
    fileHasError: boolean = true;
    fileMessage: string;
    message: string;
    isFileDirty: boolean = false;

    constructor(
        private jhiAlertService: JhiAlertService,
        private jobService: JobMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private rasteService: RasteMarineSuffixService,
        private radehService: RadehMarineSuffixService,
        private jobTypeService: JobTypeMarineSuffixService,
        private scientificWorkGroupService: ScientificWorkGroupMarineSuffixService,
        private mainTaskService: MainTaskMarineSuffixService,
        private niazsanjiGroupService: NiazsanjiGroupMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ job }) => {
            this.job = job;
        });
        /*this.documentService.query().subscribe(
            (res: HttpResponse<IDocumentMarineSuffix[]>) => {
                this.documents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
        this.rasteService.query().subscribe(
            (res: HttpResponse<IRasteMarineSuffix[]>) => {
                this.rastes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.radehService.query().subscribe(
            (res: HttpResponse<IRadehMarineSuffix[]>) => {
                this.radehs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobTypeService.query().subscribe(
            (res: HttpResponse<IJobTypeMarineSuffix[]>) => {
                this.jobtypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.scientificWorkGroupService.query().subscribe(
            (res: HttpResponse<IScientificWorkGroupMarineSuffix[]>) => {
                this.scientificworkgroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        /*this.mainTaskService.query().subscribe(
            (res: HttpResponse<IMainTaskMarineSuffix[]>) => {
                this.maintasks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.niazsanjiGroupService.query().subscribe(
            (res: HttpResponse<INiazsanjiGroupMarineSuffix[]>) => {
                this.niazsanjigroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }

    previousState() {
        window.history.back();
    }

    change(i){
        //this.router.navigateByUrl(i);
        this.router.navigateByUrl(i);
    }

    save() {

        this.isSaving = true;
        this.job.status= 0;
        if(this.isFileDirty) {
            let formdata: FormData = new FormData();
            formdata.append('file', this.file);
            this.jobService.uploadFile(formdata).subscribe(event => {
                    if (event.type === HttpEventType.UploadProgress) {
                        this.progress.percentage = Math.round(100 * event.loaded / event.total);
                    }
                    else if (event instanceof HttpResponse) {
                        if (event.body) {
                            this.job.fileDoc = event.body;
                            this.saveJob();
                        }
                    }
                },
                () => this.onSaveError());
        }
        else{
            this.saveJob();
        }
    }
    saveJob(){
        if (this.job.id !== undefined) {
            this.subscribeToSaveResponse(this.jobService.update(this.job));
        } else {
            this.subscribeToSaveResponse(this.jobService.create(this.job));
        }
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
            this.isFileDirty = true;
        }
        else{
            this.fileHasError = true;
            this.fileMessage = "حجم فایل بیش از حد مجاز است.";
        }
    }

    checkDateValidation(event) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.finishDateValidation = 1;
            }
            else {
                this.finishDateValidation = 2;
            }
        }
        catch (e) {
            this.finishDateValidation = 2;
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJobMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IJobMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRasteById(index: number, item: IRasteMarineSuffix) {
        return item.id;
    }

    trackRadehById(index: number, item: IRadehMarineSuffix) {
        return item.id;
    }

    trackJobTypeById(index: number, item: IJobTypeMarineSuffix) {
        return item.id;
    }

    trackScientificWorkGroupById(index: number, item: IScientificWorkGroupMarineSuffix) {
        return item.id;
    }

    trackJobById(index: number, item: IJobMarineSuffix) {
        return item.id;
    }

    trackMainTaskById(index: number, item: IMainTaskMarineSuffix) {
        return item.id;
    }

    trackNiazsanjiGroupById(index: number, item: INiazsanjiGroupMarineSuffix) {
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
    get job() {
        return this._job;
    }

    set job(job: IJobMarineSuffix) {
        this._job = job;
    }
}
