import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';
import { LevelThreeCriteriaMarineSuffixService } from './level-three-criteria-marine-suffix.service';
import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';
import { MahiatCourseMarineSuffixService } from 'app/entities/mahiat-course-marine-suffix';
import { ILevelThreeCriteriaGroupMarineSuffix } from 'app/shared/model/level-three-criteria-group-marine-suffix.model';
import { LevelThreeCriteriaGroupMarineSuffixService } from 'app/entities/level-three-criteria-group-marine-suffix';

@Component({
    selector: 'mi-level-three-criteria-marine-suffix-update',
    templateUrl: './level-three-criteria-marine-suffix-update.component.html'
})
export class LevelThreeCriteriaMarineSuffixUpdateComponent implements OnInit {
    levelThreeCriteria: ILevelThreeCriteriaMarineSuffix;
    isSaving: boolean;

    mahiatcourses: IMahiatCourseMarineSuffix[];

    levelthreecriteriagroups: ILevelThreeCriteriaGroupMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected levelThreeCriteriaService: LevelThreeCriteriaMarineSuffixService,
        protected mahiatCourseService: MahiatCourseMarineSuffixService,
        protected levelThreeCriteriaGroupService: LevelThreeCriteriaGroupMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ levelThreeCriteria }) => {
            this.levelThreeCriteria = levelThreeCriteria;
            this.createDate =
                this.levelThreeCriteria.createDate != null ? this.levelThreeCriteria.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.levelThreeCriteria.modifyDate != null ? this.levelThreeCriteria.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.mahiatCourseService.query().subscribe(
            (res: HttpResponse<IMahiatCourseMarineSuffix[]>) => {
                this.mahiatcourses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.levelThreeCriteriaGroupService.query().subscribe(
            (res: HttpResponse<ILevelThreeCriteriaGroupMarineSuffix[]>) => {
                this.levelthreecriteriagroups = res.body;
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
        this.levelThreeCriteria.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.levelThreeCriteria.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.levelThreeCriteria.id !== undefined) {
            this.subscribeToSaveResponse(this.levelThreeCriteriaService.update(this.levelThreeCriteria));
        } else {
            this.subscribeToSaveResponse(this.levelThreeCriteriaService.create(this.levelThreeCriteria));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILevelThreeCriteriaMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ILevelThreeCriteriaMarineSuffix>) => this.onSaveSuccess(),
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackMahiatCourseById(index: number, item: IMahiatCourseMarineSuffix) {
        return item.id;
    }

    trackLevelThreeCriteriaGroupById(index: number, item: ILevelThreeCriteriaGroupMarineSuffix) {
        return item.id;
    }
}
