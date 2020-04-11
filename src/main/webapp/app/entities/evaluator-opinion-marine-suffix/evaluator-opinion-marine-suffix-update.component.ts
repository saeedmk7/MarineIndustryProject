import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IEvaluatorOpinionMarineSuffix } from 'app/shared/model/evaluator-opinion-marine-suffix.model';
import { EvaluatorOpinionMarineSuffixService } from './evaluator-opinion-marine-suffix.service';
import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';
import { EducationalCenterGradeMarineSuffixService } from 'app/entities/educational-center-grade-marine-suffix';

@Component({
    selector: 'mi-evaluator-opinion-marine-suffix-update',
    templateUrl: './evaluator-opinion-marine-suffix-update.component.html'
})
export class EvaluatorOpinionMarineSuffixUpdateComponent implements OnInit {
    evaluatorOpinion: IEvaluatorOpinionMarineSuffix;
    isSaving: boolean;

    educationalcentergrades: IEducationalCenterGradeMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected evaluatorOpinionService: EvaluatorOpinionMarineSuffixService,
        protected educationalCenterGradeService: EducationalCenterGradeMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ evaluatorOpinion }) => {
            this.evaluatorOpinion = evaluatorOpinion;
            this.createDate = this.evaluatorOpinion.createDate != null ? this.evaluatorOpinion.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate = this.evaluatorOpinion.modifyDate != null ? this.evaluatorOpinion.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.educationalCenterGradeService.query().subscribe(
            (res: HttpResponse<IEducationalCenterGradeMarineSuffix[]>) => {
                this.educationalcentergrades = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.evaluatorOpinion.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.evaluatorOpinion.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.evaluatorOpinion.id !== undefined) {
            this.subscribeToSaveResponse(this.evaluatorOpinionService.update(this.evaluatorOpinion));
        } else {
            this.subscribeToSaveResponse(this.evaluatorOpinionService.create(this.evaluatorOpinion));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluatorOpinionMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEvaluatorOpinionMarineSuffix>) => this.onSaveSuccess(),
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

    trackEducationalCenterGradeById(index: number, item: IEducationalCenterGradeMarineSuffix) {
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
}
