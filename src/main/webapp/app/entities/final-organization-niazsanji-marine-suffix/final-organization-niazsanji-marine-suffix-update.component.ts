import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService} from 'ng-jhipster';

import {
    IFinalOrganizationNiazsanjiMarineSuffix
} from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import {FinalOrganizationNiazsanjiMarineSuffixService} from './final-organization-niazsanji-marine-suffix.service';
import {IPersonMarineSuffix} from 'app/shared/model/person-marine-suffix.model';
import {PersonMarineSuffixService} from 'app/entities/person-marine-suffix';
import {ITeacherMarineSuffix} from 'app/shared/model/teacher-marine-suffix.model';
import {TeacherMarineSuffixService} from 'app/entities/teacher-marine-suffix';
import {IEducationalModuleMarineSuffix} from 'app/shared/model/educational-module-marine-suffix.model';
import {EducationalModuleMarineSuffixService} from 'app/entities/educational-module-marine-suffix';
import {ITeachApproachMarineSuffix} from 'app/shared/model/teach-approach-marine-suffix.model';
import {TeachApproachMarineSuffixService} from 'app/entities/teach-approach-marine-suffix';
import {IRequestOrganizationNiazsanjiMarineSuffix} from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import {RequestOrganizationNiazsanjiMarineSuffixService} from 'app/entities/request-organization-niazsanji-marine-suffix';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";

@Component({
    selector: 'mi-final-organization-niazsanji-marine-suffix-update',
    templateUrl: './final-organization-niazsanji-marine-suffix-update.component.html'
})
export class FinalOrganizationNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    private _finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix;
    isSaving: boolean;

    people: IPersonMarineSuffix[];

    teachers: ITeacherMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    organizationCharts: IOrganizationChartMarineSuffix[];

    teachapproaches: ITeachApproachMarineSuffix[];

    requestorganizationniazsanjis: IRequestOrganizationNiazsanjiMarineSuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private finalOrganizationNiazsanjiService: FinalOrganizationNiazsanjiMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private teacherService: TeacherMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private teachApproachService: TeachApproachMarineSuffixService,
        private requestOrganizationNiazsanjiService: RequestOrganizationNiazsanjiMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ finalOrganizationNiazsanji }) => {
            this.finalOrganizationNiazsanji = finalOrganizationNiazsanji;
            if(this.finalOrganizationNiazsanji.people)
                this.finalOrganizationNiazsanji.people.forEach(a => a.fullName = a.name + " " + a.family);
        });
        if(this.organizationChartService.organizationchartsAll){
            this.organizationCharts = this.organizationChartService.organizationchartsAll;
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationCharts = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(this.personService.people){
            this.people = this.personService.people;
        }
        else {
            this.personService.query().subscribe(
                (res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(this.teacherService.teachers){
            this.teachers = this.teacherService.teachers;
        }
        else {
            this.teacherService.query().subscribe(
                (res: HttpResponse<ITeacherMarineSuffix[]>) => {
                    this.teachers = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        if(this.educationalModuleService.educationalModules) {
            this.educationalmodules = this.educationalModuleService.educationalModules;
        }
        else{
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalmodules = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    change(i){
        this.router.navigateByUrl(i);
    }

    previousState() {
        window.history.back();
    }

    save() {

        this.isSaving = true;
        if(this.finalOrganizationNiazsanji.organizationChartId){
            let org = this.organizationCharts.find(a => a.id == this.finalOrganizationNiazsanji.organizationChartId);
            if(org)
                this.finalOrganizationNiazsanji.recommendedByOrgchart = org.fullTitle;
        }
        if (this.finalOrganizationNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.finalOrganizationNiazsanjiService.update(this.finalOrganizationNiazsanji));
        } else {
            this.subscribeToSaveResponse(this.finalOrganizationNiazsanjiService.create(this.finalOrganizationNiazsanji));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
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

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackTeacherById(index: number, item: ITeacherMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackTeachApproachById(index: number, item: ITeachApproachMarineSuffix) {
        return item.id;
    }

    trackRequestOrganizationNiazsanjiById(index: number, item: IRequestOrganizationNiazsanjiMarineSuffix) {
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
    get finalOrganizationNiazsanji() {
        return this._finalOrganizationNiazsanji;
    }

    set finalOrganizationNiazsanji(finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix) {
        this._finalOrganizationNiazsanji = finalOrganizationNiazsanji;
    }
}
