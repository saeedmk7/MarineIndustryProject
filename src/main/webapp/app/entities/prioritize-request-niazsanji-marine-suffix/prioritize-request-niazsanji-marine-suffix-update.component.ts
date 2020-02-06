import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import { PrioritizeRequestNiazsanjiMarineSuffixService } from './prioritize-request-niazsanji-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from 'app/entities/restriction-marine-suffix';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { RequestNiazsanjiFardiMarineSuffixService } from 'app/entities/request-niazsanji-fardi-marine-suffix';
import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { PreJobNiazsanjiMarineSuffixService } from 'app/entities/pre-job-niazsanji-marine-suffix';
import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import { RequestOtherNiazsanjiMarineSuffixService } from 'app/entities/request-other-niazsanji-marine-suffix';
import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from 'app/entities/niazsanji-input-marine-suffix';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from 'app/entities/teaching-approach-marine-suffix';
import {Principal, UserService} from "app/core";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix-update',
    templateUrl: './prioritize-request-niazsanji-marine-suffix-update.component.html'
})
export class PrioritizeRequestNiazsanjiMarineSuffixUpdateComponent implements OnInit {
    prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    restrictions: IRestrictionMarineSuffix[];

    requestniazsanjifardis: IRequestNiazsanjiFardiMarineSuffix[];

    prejobniazsanjis: IPreJobNiazsanjiMarineSuffix[];

    requestotherniazsanjis: IRequestOtherNiazsanjiMarineSuffix[];

    niazsanjiinputs: INiazsanjiInputMarineSuffix[];

    coursetypes: ICourseTypeMarineSuffix[];

    educationalmodules: IEducationalModuleMarineSuffix[];

    people: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

    teachingapproaches: ITeachingApproachMarineSuffix[];

    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgChartDisabled: boolean;

    allPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    currentAccount: any;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    message: string;
    targetPeople: IPersonMarineSuffix[];

    rowNumber:number = 10;
    currentUserFullName: string = "";

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected prioritizeRequestNiazsanjiService: PrioritizeRequestNiazsanjiMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected restrictionService: RestrictionMarineSuffixService,
        protected requestNiazsanjiFardiService: RequestNiazsanjiFardiMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected requestOtherNiazsanjiService: RequestOtherNiazsanjiMarineSuffixService,
        protected niazsanjiInputService: NiazsanjiInputMarineSuffixService,
        protected courseTypeService: CourseTypeMarineSuffixService,
        protected educationalModuleService: EducationalModuleMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected teachingApproachService: TeachingApproachMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private principal : Principal,
        private treeUtilities: TreeUtilities,
        private userService: UserService,
        private convertObjectDatesService: ConvertObjectDatesService,
        protected router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ prioritizeRequestNiazsanji }) => {
            this.prioritizeRequestNiazsanji = prioritizeRequestNiazsanji;
            this.rowNumber = this.prioritizeRequestNiazsanji.conversation.split('\n').length;
        });
        this.restrictionService.query().subscribe(
            (res: HttpResponse<IRestrictionMarineSuffix[]>) => {
                this.restrictions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.requestNiazsanjiFardiService.query().subscribe(
            (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix[]>) => {
                this.requestniazsanjifardis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.preJobNiazsanjiService.query().subscribe(
            (res: HttpResponse<IPreJobNiazsanjiMarineSuffix[]>) => {
                this.prejobniazsanjis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.requestOtherNiazsanjiService.query().subscribe(
            (res: HttpResponse<IRequestOtherNiazsanjiMarineSuffix[]>) => {
                this.requestotherniazsanjis = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.niazsanjiInputService.query().subscribe(
            (res: HttpResponse<INiazsanjiInputMarineSuffix[]>) => {
                this.niazsanjiinputs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.coursetypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.educationalModuleService.query().subscribe(
            (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                this.educationalmodules = res.body;
                if(this.prioritizeRequestNiazsanji.educationalModuleId){
                    this.onEducationalModuleChange(this.educationalmodules.find(a => a.id == this.prioritizeRequestNiazsanji.educationalModuleId));
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if(this.organizationChartService.organizationchartsAll){
            this.organizationcharts = this.convertObjectDatesService.goClone(this.organizationChartService.organizationchartsAll);

            if(this.prioritizeRequestNiazsanji.organizationChartId){
                let org = this.organizationcharts.find(a => a.id == this.prioritizeRequestNiazsanji.organizationChartId);
                //this.onOrganizationChartChange(org);
            }

            this.setPermission();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    if (this.prioritizeRequestNiazsanji.organizationChartId) {
                        let org = this.organizationcharts.find(a => a.id == this.prioritizeRequestNiazsanji.organizationChartId);
                        //this.onOrganizationChartChange(org);
                    }

                    this.setPermission();
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
    setPermission(){
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;
            }, (res: HttpErrorResponse) => this.onError(res.message));

        });
    }
    setRoles(account: any){

        if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
            this.isAdmin = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
            this.isModirAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
            this.isModirKolAmozesh = true;
        if(account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if(this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
            this.isSuperUsers = true;
    }
    onEducationalModuleChange($event: IEducationalModuleMarineSuffix){
        this.prioritizeRequestNiazsanji.skillLevelOfSkillTitle = $event.skillableLevelOfSkillTitle;
        this.prioritizeRequestNiazsanji.totalLearningTime = ($event.learningTimeTheorical ? +$event.learningTimeTheorical : 0) + ($event.learningTimePractical ? +$event.learningTimePractical : 0);
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

        if (this.prioritizeRequestNiazsanji.id !== undefined) {
            this.subscribeToSaveResponse(this.prioritizeRequestNiazsanjiService.update(this.prioritizeRequestNiazsanji));
        } else {
            this.subscribeToSaveResponse(this.prioritizeRequestNiazsanjiService.create(this.prioritizeRequestNiazsanji));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
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

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackRestrictionById(index: number, item: IRestrictionMarineSuffix) {
        return item.id;
    }

    trackRequestNiazsanjiFardiById(index: number, item: IRequestNiazsanjiFardiMarineSuffix) {
        return item.id;
    }

    trackPreJobNiazsanjiById(index: number, item: IPreJobNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackRequestOtherNiazsanjiById(index: number, item: IRequestOtherNiazsanjiMarineSuffix) {
        return item.id;
    }

    trackNiazsanjiInputById(index: number, item: INiazsanjiInputMarineSuffix) {
        return item.id;
    }

    trackCourseTypeById(index: number, item: ICourseTypeMarineSuffix) {
        return item.id;
    }

    trackEducationalModuleById(index: number, item: IEducationalModuleMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
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
}
