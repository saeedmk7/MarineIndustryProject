import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
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

@Component({
    selector: 'mi-job-marine-suffix-update',
    templateUrl: './job-marine-suffix-update.component.html'
})
export class JobMarineSuffixUpdateComponent implements OnInit {
    private _job: IJobMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    rastes: IRasteMarineSuffix[];

    radehs: IRadehMarineSuffix[];

    jobtypes: IJobTypeMarineSuffix[];

    scientificworkgroups: IScientificWorkGroupMarineSuffix[];

    jobs: IJobMarineSuffix[];

    maintasks: IMainTaskMarineSuffix[];

    niazsanjigroups: INiazsanjiGroupMarineSuffix[];

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
        if (this.job.id !== undefined) {
            this.subscribeToSaveResponse(this.jobService.update(this.job));
        } else {
            this.subscribeToSaveResponse(this.jobService.create(this.job));
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
