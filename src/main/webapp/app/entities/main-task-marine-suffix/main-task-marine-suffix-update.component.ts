import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';
import { MainTaskMarineSuffixService } from './main-task-marine-suffix.service';
import { ISubTaskMarineSuffix } from 'app/shared/model/sub-task-marine-suffix.model';
import { SubTaskMarineSuffixService } from 'app/entities/sub-task-marine-suffix';
import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from 'app/entities/job-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';

@Component({
    selector: 'mi-main-task-marine-suffix-update',
    templateUrl: './main-task-marine-suffix-update.component.html'
})
export class MainTaskMarineSuffixUpdateComponent implements OnInit {
    private _mainTask: IMainTaskMarineSuffix;
    isSaving: boolean;

    subtasks: ISubTaskMarineSuffix[];

    jobs: IJobMarineSuffix[];

    people: IPersonMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private mainTaskService: MainTaskMarineSuffixService,
        private subTaskService: SubTaskMarineSuffixService,
        private jobService: JobMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ mainTask }) => {
            this.mainTask = mainTask;
        });
        this.subTaskService.query().subscribe(
            (res: HttpResponse<ISubTaskMarineSuffix[]>) => {
                this.subtasks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.jobService.query().subscribe(
            (res: HttpResponse<IJobMarineSuffix[]>) => {
                this.jobs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }
    change(i){
        this.router.navigateByUrl(i);
    }
    save() {
        this.isSaving = true;
        this.mainTask.status = 0;
        /*this.mainTask.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.mainTask.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        this.mainTask.archivedDate = moment(this.archivedDate, DATE_TIME_FORMAT);*/
        if (this.mainTask.id !== undefined) {
            this.subscribeToSaveResponse(this.mainTaskService.update(this.mainTask));
        } else {
            this.subscribeToSaveResponse(this.mainTaskService.create(this.mainTask));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMainTaskMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IMainTaskMarineSuffix>) => this.onSaveSuccess(),
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

    trackSubTaskById(index: number, item: ISubTaskMarineSuffix) {
        return item.id;
    }

    trackJobById(index: number, item: IJobMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
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
    get mainTask() {
        return this._mainTask;
    }

    set mainTask(mainTask: IMainTaskMarineSuffix) {
        this._mainTask = mainTask;
        /*this.createDate = moment(mainTask.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(mainTask.modifyDate).format(DATE_TIME_FORMAT);
        this.archivedDate = moment(mainTask.archivedDate).format(DATE_TIME_FORMAT);*/
    }
}
