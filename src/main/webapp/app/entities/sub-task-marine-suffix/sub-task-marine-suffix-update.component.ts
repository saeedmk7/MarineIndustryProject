import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISubTaskMarineSuffix } from 'app/shared/model/sub-task-marine-suffix.model';
import { SubTaskMarineSuffixService } from './sub-task-marine-suffix.service';
import { IMainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';
import { MainTaskMarineSuffixService } from 'app/entities/main-task-marine-suffix';

@Component({
    selector: 'mi-sub-task-marine-suffix-update',
    templateUrl: './sub-task-marine-suffix-update.component.html'
})
export class SubTaskMarineSuffixUpdateComponent implements OnInit {
    private _subTask: ISubTaskMarineSuffix;
    isSaving: boolean;

    /*maintasks: IMainTaskMarineSuffix[];*/

    constructor(
        private jhiAlertService: JhiAlertService,
        private subTaskService: SubTaskMarineSuffixService,
        private mainTaskService: MainTaskMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ subTask }) => {
            this.subTask = subTask;
        });
        /*this.mainTaskService.query().subscribe(
            (res: HttpResponse<IMainTaskMarineSuffix[]>) => {
                this.maintasks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }

    previousState() {
        window.history.back();
    }
    change(i){
        this.router.navigateByUrl(i);
    }
    save() {
        this.isSaving = true;
        this.subTask.status = 0;
        if (this.subTask.id !== undefined) {
            this.subscribeToSaveResponse(this.subTaskService.update(this.subTask));
        } else {
            this.subscribeToSaveResponse(this.subTaskService.create(this.subTask));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISubTaskMarineSuffix>>) {
        result.subscribe((res: HttpResponse<ISubTaskMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    /*trackMainTaskById(index: number, item: IMainTaskMarineSuffix) {
        return item.id;
    }*/

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
    get subTask() {
        return this._subTask;
    }

    set subTask(subTask: ISubTaskMarineSuffix) {
        this._subTask = subTask;
    }
}
