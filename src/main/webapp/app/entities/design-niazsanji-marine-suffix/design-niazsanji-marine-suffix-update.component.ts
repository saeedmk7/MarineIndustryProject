import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import {
    DesignNiazsanjiMarineSuffix,
    IDesignNiazsanjiMarineSuffix
} from 'app/shared/model/design-niazsanji-marine-suffix.model';
import { DesignNiazsanjiMarineSuffixService } from './design-niazsanji-marine-suffix.service';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { PreJobNiazsanjiMarineSuffixService } from 'app/entities/pre-job-niazsanji-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import * as $ from 'jquery';

@Component({
    selector: 'mi-design-niazsanji-marine-suffix-update',
    templateUrl: './design-niazsanji-marine-suffix-update.component.html'
})
export class DesignNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    designNiazsanji: IDesignNiazsanjiMarineSuffix;
    @Input("index") index: number;
    @Input("elementIndex") elementIndex: number;
    @Input("editable") editable : boolean;
    @Input("preJobNiazsanji") preJobNiazsanji : IPreJobNiazsanjiMarineSuffix;
    @Output("designNiazsanjiDeleted") designNiazsanjiDeleted = new EventEmitter();
    isSaving: boolean;

    prejobniazsanjis: IPreJobNiazsanjiMarineSuffix[];

    coursetypes: ICourseTypeMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    teachingapproaches: ITeachingApproachMarineSuffix[];
    restrictions: IRestrictionMarineSuffix[];
    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected designNiazsanjiService: DesignNiazsanjiMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected courseTypeService: CourseTypeMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}


    ngOnInit() {
        this.isSaving = false;
        /*this.activatedRoute.data.subscribe(({ designNiazsanji }) => {
            this.designNiazsanji = designNiazsanji;
        });*/
        if(this.index || this.index == 0){
            if(this.preJobNiazsanji && this.preJobNiazsanji.designNiazsanjis && this.preJobNiazsanji.designNiazsanjis[this.index]){
                this.designNiazsanji = this.preJobNiazsanji.designNiazsanjis[this.index];
            }
            else{
                this.designNiazsanji = new DesignNiazsanjiMarineSuffix();
            }
        }

        setTimeout(() => {
            if(!this.editable)
            {
                $("#contentDesign :input").attr("disabled", true);
            }
        }, 1000);


        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if(this.educationalModuleService.educationalModules){
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        this.teachingApproachService.query().subscribe(
            (res: HttpResponse<ITeachingApproachMarineSuffix[]>) => {
                this.teachingapproaches = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {

        this.isSaving = true;
        this.designNiazsanji.preJobNiazsanjiId = this.preJobNiazsanji.id;
        this.designNiazsanji.status = 0;
        if (this.designNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.designNiazsanjiService.update(this.designNiazsanji));
        } else {
            this.subscribeToSaveResponse(this.designNiazsanjiService.create(this.designNiazsanji));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDesignNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IDesignNiazsanjiMarineSuffix>) => this.onSaveSuccess(res.body),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess(res) {
        this.designNiazsanji = res;
        this.isSaving = false;
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackRestrictionById(index: number, item: IRestrictionMarineSuffix) {
        return item.id;
    }

    trackPreJobNiazsanjiById(index: number, item: IPreJobNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackCourseTypeById(index: number, item: ICourseTypeMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackTeachingApproachById(index: number, item: ITeachingApproachMarineSuffix) {
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
    deleteEntity(){

        if (this.designNiazsanji.id !== undefined) {
            //this.subscribeToSaveResponse(this.designNiazsanjiService.update(this.designNiazsanji));
            this.designNiazsanjiService.delete(this.designNiazsanji.id).subscribe((res: IDesignNiazsanjiMarineSuffix) => {

                this.designNiazsanjiDeleted.emit(this.elementIndex);
            });
        } else {
            this.designNiazsanjiDeleted.emit(this.elementIndex);
        }
    }
}
