import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { MatchingEducationalRecordMarineSuffixService } from './matching-educational-record-marine-suffix.service';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { SkillableLevelOfSkillMarineSuffixService } from 'app/entities/skillable-level-of-skill-marine-suffix';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IPersonMarineSuffix, PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { IUser, Principal, UserService } from 'app/core';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { IRunPhaseTabModel, RunPhaseTabModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-tab.model';
import { IMatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';
import { MatchingRunningStepMarineSuffixService } from 'app/entities/matching-running-step-marine-suffix';
import { RunPhaseItemModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-item.model';
import { MatchingRunRunningStepMarineSuffixService } from 'app/entities/matching-run-running-step-marine-suffix';
import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model/matching-run-running-step-marine-suffix.model';
import * as $ from 'jquery';
import {
    IMatchingEducationalRecordSaveDataModel,
    MatchingEducationalRecordSaveDataModel
} from 'app/entities/matching-educational-record-marine-suffix/matching-educational-record-marine-suffix-save-data.model';
import {
    IMatchingEducationalRecordSaveDataItemModel,
    MatchingEducationalRecordSaveDataItemModel
} from 'app/entities/matching-educational-record-marine-suffix/matching-educational-record-marine-suffix-save-data-item.model';
import { IHomePagePersonEducationalModule } from 'app/shared/model/custom/home-page-person-educational-module';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { IMatchingCourseType } from 'app/shared/model/custom/matching-course-type';

@Component({
    selector: 'mi-matching-educational-record-marine-suffix-update',
    templateUrl: './matching-educational-record-marine-suffix-update.component.html',
    styleUrls: ['./matching-educational-record-marine-suffix.scss']
})
export class MatchingEducationalRecordMarineSuffixUpdateComponent implements OnInit {
    matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix;
    isSaving: boolean;

    skillablelevelofskills: ISkillableLevelOfSkillMarineSuffix[];

    documents: IDocumentMarineSuffix[];

    people: IPersonMarineSuffix[];

    organizationcharts: IOrganizationChartMarineSuffix[];

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
    isTopUsers: boolean = false;
    isUser: boolean = false;

    message: string;
    targetPeople: IPersonMarineSuffix[];

    hasNoRow: boolean = false;
    currentUserFullName: string = '';

    runPhaseTabModel: IRunPhaseTabModel[] = [];
    matchingRunningSteps: IMatchingRunningStepMarineSuffix[] = [];
    errorMessage: string;
    rows: number = 0;

    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];
    selectedItemIds: number[] = [];

    courseTypes: ICourseTypeMarineSuffix[] = [];
    aggregateTable: IMatchingCourseType[] = [];

    picUrl: string = '';
    oldPicUrl: string = '';
    isNewImage: boolean = false;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected matchingEducationalRecordService: MatchingEducationalRecordMarineSuffixService,
        protected skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        protected courseTypeService: CourseTypeMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected personService: PersonMarineSuffixService,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private principal: Principal,
        private treeUtilities: TreeUtilities,
        private userService: UserService,
        private convertObjectDatesService: ConvertObjectDatesService,
        protected router: Router,
        private matchingRunningStepService: MatchingRunningStepMarineSuffixService,
        private matchingRunRunningStepService: MatchingRunRunningStepMarineSuffixService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ matchingEducationalRecord }) => {
            this.matchingEducationalRecord = matchingEducationalRecord;
            if (this.matchingEducationalRecord.skillableLevelOfSkills && this.matchingEducationalRecord.skillableLevelOfSkills.length > 0)
                this.matchingEducationalRecord.skillableLevelOfSkill = this.matchingEducationalRecord.skillableLevelOfSkills[0];
            if (this.matchingEducationalRecord.conversation) this.rows = this.matchingEducationalRecord.conversation.split('\n').length;

            this.matchingRunningStepService.query().subscribe(
                (resp: HttpResponse<IMatchingRunningStepMarineSuffix[]>) => {
                    this.matchingRunningSteps = resp.body;

                    let stepNumbers = resp.body.filter(a => a.isHeader).sort(function(a, b) {
                        return a.stepNumber > b.stepNumber ? 1 : b.stepNumber > a.stepNumber ? -1 : 0;
                    });
                    stepNumbers.forEach(a => {
                        let tab: RunPhaseTabModel = new RunPhaseTabModel();
                        tab.id = 'tab' + a.stepNumber;
                        tab.title = a.title;
                        tab.href = '#' + tab.id;
                        tab.active = a.stepNumber == 1;
                        tab.colorText = a.colorText;
                        tab.runPhaseItems = [];

                        let runningsForThisStep = this.matchingRunningSteps.filter(
                            w => w.stepNumber == a.stepNumber && w.isHeader == false
                        );
                        runningsForThisStep.forEach(e => {
                            let runPhaseItem: RunPhaseItemModel = new RunPhaseItemModel();
                            runPhaseItem.id = e.id;
                            runPhaseItem.title = e.title; //+ (e.stepRequired ? "(اجباریست)" : "");
                            runPhaseItem.description = e.description == null ? '' : e.description;
                            runPhaseItem.required = e.stepRequired;
                            runPhaseItem.fileDocRequired = e.fileDocRequired;
                            runPhaseItem.stepNumber = a.stepNumber;
                            runPhaseItem.sampleFileDoc = e.fileDoc;
                            tab.runPhaseItems.push(runPhaseItem);
                        });

                        this.runPhaseTabModel.push(tab);
                    });

                    if (this.matchingEducationalRecord.id != undefined) {
                        const criteria1 = [
                            {
                                key: 'matchingEducationalRecordId.equals',
                                value: this.matchingEducationalRecord.id
                            }
                        ];
                        this.matchingRunRunningStepService
                            .query({
                                page: 0,
                                size: 20000,
                                criteria: criteria1,
                                sort: ['id', 'asc']
                            })
                            .subscribe(
                                (response: HttpResponse<IMatchingRunRunningStepMarineSuffix[]>) => {
                                    response.body.forEach(x => {
                                        this.runPhaseTabModel.forEach(w => {
                                            w.runPhaseItems.forEach(r => {
                                                if (r.id == x.matchingRunningStepId) {
                                                    r.descMessage = x.description == null ? '' : x.description;
                                                    r.fileDoc = x.fileDoc;
                                                    r.checked = x.done;
                                                }
                                            });
                                        });
                                    });
                                },
                                (res: HttpErrorResponse) => this.onError(res.message)
                            );
                    }
                    //this.runPhaseTabModel = resp.body.map(a => new RunPhaseTabModel(a.id))
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });

        this.skillableLevelOfSkillService.query().subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>) => {
                this.skillablelevelofskills = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.courseTypeService.query().subscribe(
            (res: HttpResponse<ICourseTypeMarineSuffix[]>) => {
                this.courseTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.convertObjectDatesService.goClone(this.organizationChartService.organizationchartsAll);

            if (this.matchingEducationalRecord.organizationChartId) {
                let org = this.organizationcharts.find(a => a.id == this.matchingEducationalRecord.organizationChartId);
                this.onOrganizationChartChange(org);
            }

            this.setPermission();
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    if (this.matchingEducationalRecord.organizationChartId) {
                        let org = this.organizationcharts.find(a => a.id == this.matchingEducationalRecord.organizationChartId);
                        this.onOrganizationChartChange(org);
                    }

                    this.setPermission();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    uploadFile(id: number) {
        let fileToUpload = $('#file-' + id).prop('files')[0];

        let formdata: FormData = new FormData();

        formdata.append('file', fileToUpload);
        this.matchingEducationalRecordService.uploadFile(formdata).subscribe(
            event => {
                if (event instanceof HttpResponse) {
                    if (event.body) {
                        //$('#fileDoc-'+ id).val(event.body);
                        this.runPhaseTabModel.filter(a =>
                            a.runPhaseItems.forEach(w => {
                                if (w.id == id) w.fileDoc = event.body.toString();
                            })
                        );
                        //this.runPhase.fileDoc = event.body;
                        //this.subscribeToSaveResponse(this.runPhaseService.create(this.runPhase));
                    }
                }
            },
            () => this.onSaveError()
        );
    }

    deleteFile(fileDoc: string, id: number) {
        this.runPhaseTabModel.filter(a =>
            a.runPhaseItems.forEach(w => {
                if (w.id == id) w.fileDoc = '';
            })
        );
    }
    homePagePersonEducationalModulesChanged(v) {
        this.homePagePersonEducationalModules = v;

        if (this.matchingEducationalRecord.selectedModuleIds) {
            this.selectedItemIds = this.matchingEducationalRecord.selectedModuleIds.split(',').map(w => +w);
        }
        this.doAggregateTable();
    }
    selectedItemIdsChanged($event) {
        this.doAggregateTable();
    }

    doAggregateTable() {
        this.aggregateTable = [];
        if (this.homePagePersonEducationalModules && this.homePagePersonEducationalModules.length > 0) {
            const completed = this.homePagePersonEducationalModules.filter(w => w.status == 100);
            if (completed && completed.length > 0) {
                this.courseTypes.forEach(w => {
                    let sum: number;
                    const sumData = completed.filter(a => a.courseTypeId == w.id);
                    if (sumData && sumData.length == 0) return;

                    sum = sumData
                        .map(
                            a =>
                                (!a.learningTimePractical ? 0 : a.learningTimePractical) +
                                (!a.learningTimeTheorical ? 0 : a.learningTimeTheorical)
                        )
                        .reduce((sum, current) => sum + current);
                    let acceptedSum = 0;
                    if (this.selectedItemIds && this.selectedItemIds.length > 0) {
                        let acceptedData: IHomePagePersonEducationalModule[] = [];
                        this.selectedItemIds.forEach(a => {
                            const findComplete = completed.find(r => r.courseTypeId == w.id && r.id == a);
                            if (findComplete) acceptedData.push(findComplete);
                        });
                        /*completed
                            .filter(a => this.selectedItemIds.includes(a.id));*/
                        if (acceptedData && acceptedData.length > 0)
                            acceptedSum = acceptedData
                                .map(
                                    a =>
                                        (!a.learningTimePractical ? 0 : a.learningTimePractical) +
                                        (!a.learningTimeTheorical ? 0 : a.learningTimeTheorical)
                                )
                                .reduce((sum, current) => sum + current);
                    }
                    this.aggregateTable.push({
                        title: w.title,
                        sum: sum,
                        acceptedSum: acceptedSum
                    });
                });
            }
        }
        return this.aggregateTable;
    }

    validateFile(event, id) {
        //file.name.split('.')[file.name.split('.').length-1] == 'rar'
        if (event && event.target.files && event.target.files[0]) {
            let file = event.target.files[0];
            if (file.size / 1024 / 1024 < 10) {
                this.successRaised(id, 'فایل معتبر است. امکان بارگذاری وجود دارد.');
                /*this.fileHasError = false;
                this.fileMessage = "فایل معتبر است.";*/
            } else {
                this.errorRaised(id, 'حجم فایل بیش از حد مجاز است.');
                /*this.fileHasError = true;
                this.fileMessage = "حجم فایل بیش از حد مجاز است.";*/
            }
        } else {
            this.errorRaised(id, 'لطفا فایل را انتخاب نمائید.');
        }
    }

    errorRaised(id, message) {
        $('#message-' + id).attr('style', '');
        $('#message-' + id).removeClass('alert-success');
        $('#message-' + id).addClass('alert-danger');
        $('#message-' + id).text(message);

        $('#uploadBtn-' + id).prop('disabled', true);
    }

    successRaised(id, message) {
        $('#message-' + id).attr('style', '');
        $('#message-' + id).removeClass('alert-danger');
        $('#message-' + id).addClass('alert-success');
        $('#message-' + id).text(message);

        $('#uploadBtn-' + id).prop('disabled', false);
    }

    setPermission() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(account);

            this.personService.find(this.currentAccount.personId).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;
                    if (!this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
                        this.matchingEducationalRecord.organizationChartId = resp.body.organizationChartId;
                        this.matchingEducationalRecord.personId = resp.body.id;
                        this.people = [];
                        this.people.push(resp.body);

                        this.findTargetPeople(this.currentPerson);
                        this.handleOrgChartView();
                        this.onPersonChange(resp.body);
                        //this.findTargetPeople(resp.body);
                    } else {
                        this.findTargetPeople(this.currentPerson);
                        this.handleOrgChartView();
                    }

                    /*    this.findTargetPeople(resp.body);
                else
                    this.handleOrgChartView();*/
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
    }

    preparePeople() {
        if (this.isSuperUsers) {
            if (this.personService.people) {
                this.allPeople = this.personService.people;
            } else {
                this.personService.query().subscribe(
                    (res: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.allPeople = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
        } else {
            let criteria = [
                {
                    key: 'organizationChartId.in',
                    value: this.recommenedOrgCharts.map(a => a.id)
                }
            ];
            this.personService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (resp: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.allPeople = resp.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;
        if (account.authorities.find(a => a == 'ROLE_USER') !== undefined) this.isUser = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh) this.isTopUsers = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }

    selectedPerson: IPersonMarineSuffix = new PersonMarineSuffix();

    onPersonChange(event: IPersonMarineSuffix) {
        if (event.id) {
            this.personService.find(event.id).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.selectedPerson = resp.body;
                    if (this.organizationcharts.find(a => a.id == this.selectedPerson.organizationChartId)) {
                        this.matchingEducationalRecord.organizationChartId = this.selectedPerson.organizationChartId;
                    } else {
                        this.organizationChartService.find(this.selectedPerson.organizationChartId).subscribe(
                            (org: HttpResponse<IOrganizationChartMarineSuffix>) => {
                                this.organizationcharts.push(org.body);
                                this.matchingEducationalRecord.organizationChartId = this.selectedPerson.organizationChartId;
                            },
                            (res: HttpErrorResponse) => this.onError(res.message)
                        );
                    }
                },
                error => this.onError('موردی یافت نشد')
            );
            this.userService.findByPersonId(event.id).subscribe(
                (res: HttpResponse<IUser>) => {
                    if (res.body.imageUrl) {
                        this.oldPicUrl = res.body.imageUrl;
                    } else {
                        this.oldPicUrl = '/content/images/home/man.png';
                    }
                },
                (res: HttpResponse<any>) => this.onError(res.body)
            );
        }
    }

    findTargetPeople(person: IPersonMarineSuffix) {
        let organization = this.organizationcharts.find(a => a.id == person.organizationChartId);
        if (organization.parentId > 0) {
            let neededOrgId: number = organization.parentId;

            let criteria = [
                {
                    key: 'organizationChartId.equals',
                    value: neededOrgId
                }
            ];
            this.personService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (resp: HttpResponse<IPersonMarineSuffix[]>) => {
                        let orgPeople = resp.body;
                        if (orgPeople.length > 0) {
                            this.targetPeople = orgPeople;
                        } else {
                            this.targetPeople = [];
                            this.targetPeople = [
                                new PersonMarineSuffix(
                                    0,
                                    'خطا',
                                    'خطا',
                                    'خطا: نفر دریافت کننده ای در چارت سازمانی برای شما تعریف نشده است. لطفا با مدیریت سامانه تماس بگیرید. '
                                )
                            ];
                        }
                    },
                    error => this.onError('فردی یافت نشد.')
                );
        } else {
            this.targetPeople = [];
            this.targetPeople.push(
                new PersonMarineSuffix(
                    0,
                    'ثبت نهایی',
                    'ثبت نهایی',
                    'درخواست تطبیق سوابق آموزشی شما پس از ثبت تایید برای تکمیل و بررسی اطلاعات توسط آموزش سازمان، به کارشناس ارشد آموزش ارسال می گردد.'
                )
            );
        }
    }

    handleOrgChartView() {
        if (this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId)) {
            let orgIds = this.treeUtilities
                .getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId)
                .filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
            this.orgChartDisabled = false;
        } else {
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
            this.orgChartDisabled = true;
        }
        this.preparePeople();
    }

    onOrganizationChartChange(event) {
        if (event.id) {
            let criteria = [
                {
                    key: 'organizationChartId.equals',
                    value: event.id
                }
            ];
            this.personService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    resp => {
                        this.people = resp.body;
                        if (this.matchingEducationalRecord.personId) {
                            this.onPersonChange(this.people.find(a => a.id == this.matchingEducationalRecord.personId));
                        }
                    },
                    error => this.onError('فردی یافت نشد.')
                );
        }
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

    validate(): boolean {
        let isValid: boolean = true;
        this.errorMessage = '';
        this.matchingRunningSteps.forEach(a => {
            if (!a.isHeader) {
                if (this.isUser && a.stepNumber > 1) return;
                if (this.isModirAmozesh && a.stepNumber > 2) return;
                if (this.isKarshenasArshadAmozeshSazman && a.stepNumber > 3) return;
                if (a.stepRequired) {
                    if (!$('#chk-' + a.id).is(':checked')) {
                        let tab = this.matchingRunningSteps.filter(e => e.isHeader && e.stepNumber == a.stepNumber)[0];
                        isValid = false;
                        this.errorMessage +=
                            '*در ' + tab.title + ' مورد ' + a.title + ' ،اجباریست لطفا قبل از تایید نهایی آن را انجام دهید.' + '<br />';
                    }
                }
                if (a.fileDocRequired) {
                    if (!$('#fileDoc-' + a.id).val()) {
                        let tab = this.matchingRunningSteps.filter(e => e.isHeader && e.stepNumber == a.stepNumber)[0];
                        isValid = false;
                        this.errorMessage +=
                            '*در ' +
                            tab.title +
                            ' مورد ' +
                            a.title +
                            ' ،بارگذاری فایل اجباریست لطفا قبل از تایید نهایی فایل مربوطه را بارگذاری نمائید.' +
                            '<br />';
                    }
                }
            }
        });
        return isValid;
    }

    save() {
        this.isSaving = true;
        this.currentUserFullName = this.currentPerson.fullName;

        if (this.validate()) {
            let matchingEducationalRecordSaveData: IMatchingEducationalRecordSaveDataModel = new MatchingEducationalRecordSaveDataModel();

            if (this.matchingEducationalRecord.id !== undefined) {
                matchingEducationalRecordSaveData.matchingEducationalRecordId = this.matchingEducationalRecord.id;

                if (this.matchingEducationalRecord.conversation)
                    matchingEducationalRecordSaveData.conversion = this.matchingEducationalRecord.conversation;
            } else {
                matchingEducationalRecordSaveData.stepNumber = 1;
                matchingEducationalRecordSaveData.bossStatus = 0;
                matchingEducationalRecordSaveData.organizationChartId = this.matchingEducationalRecord.organizationChartId;
                matchingEducationalRecordSaveData.personId = this.matchingEducationalRecord.personId;

                if (this.isKarshenasArshadAmozeshSazman) {
                    matchingEducationalRecordSaveData.chartStatus = 0;
                    matchingEducationalRecordSaveData.bossStatus = 20;
                } else if (this.isModirKolAmozesh) {
                    matchingEducationalRecordSaveData.chartStatus = 0;
                    matchingEducationalRecordSaveData.bossStatus = 30;
                } else {
                    let organization = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                    if (organization.parentId > 0) {
                        matchingEducationalRecordSaveData.chartStatus = organization.parentId;
                    } else {
                        matchingEducationalRecordSaveData.chartStatus = 0;
                        matchingEducationalRecordSaveData.bossStatus = 10;
                    }
                }
                matchingEducationalRecordSaveData.requestStatus = RequestStatus.NEW;
                matchingEducationalRecordSaveData.conversion =
                    ' درخواست توسط ' +
                    this.currentUserFullName +
                    ' در تاریخ: ' +
                    this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                    ' ثبت شد. ';
                if (this.matchingEducationalRecord.description) {
                    matchingEducationalRecordSaveData.conversion += '\n';
                    matchingEducationalRecordSaveData.conversion +=
                        this.currentUserFullName + ': ' + this.matchingEducationalRecord.description;
                }
            }

            matchingEducationalRecordSaveData.description = this.matchingEducationalRecord.description;
            matchingEducationalRecordSaveData.skillableLevelOfSkills = [];
            matchingEducationalRecordSaveData.skillableLevelOfSkills.push(this.matchingEducationalRecord.skillableLevelOfSkill);

            matchingEducationalRecordSaveData.selectedModuleIds = this.selectedItemIds.join(',');

            matchingEducationalRecordSaveData.matchingEducationalRecordSaveDataItemModels = [];

            this.matchingRunningSteps.forEach(a => {
                if (!a.isHeader) {
                    let matchingEducationalRecordSaveDataItemModel: IMatchingEducationalRecordSaveDataItemModel = new MatchingEducationalRecordSaveDataItemModel();
                    matchingEducationalRecordSaveDataItemModel.runningStepId = a.id;
                    matchingEducationalRecordSaveDataItemModel.description = $('#txt-' + a.id).val();
                    matchingEducationalRecordSaveDataItemModel.done = $('#chk-' + a.id).is(':checked');
                    matchingEducationalRecordSaveDataItemModel.fileDoc = $('#fileDoc-' + a.id).val();
                    matchingEducationalRecordSaveData.matchingEducationalRecordSaveDataItemModels.push(
                        matchingEducationalRecordSaveDataItemModel
                    );
                }
            });

            this.subscribeToSaveResponse(
                this.matchingEducationalRecordService.saveMatchingEducationalRecordModel(matchingEducationalRecordSaveData)
            );
        } else {
            this.isSaving = false;
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IMatchingEducationalRecordMarineSuffix>>, isEdit: boolean = false) {
        result.subscribe(
            (res: HttpResponse<IMatchingEducationalRecordMarineSuffix>) => this.onSaveSuccess(res.body, isEdit),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess(res: IMatchingEducationalRecordMarineSuffix, isEdit: boolean = false) {
        if (!isEdit) {
            if (res.chartStatus == 0) {
                res.conversation += '\n ------------------------------------- \n';
                res.conversation +=
                    ' تایید درخواست توسط ' +
                    this.currentUserFullName +
                    ' در تاریخ: ' +
                    this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                    ' انجام شد. ';
                /*this.requestOtherNiazsanjiService.finalize(res).subscribe(
                    (res: HttpResponse<IRequestOtherNiazsanjiMarineSuffix>) => res,
                    (res: HttpErrorResponse) => res
                );*/
            }
        }
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    change(i) {
        this.router.navigateByUrl(i);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSkillableLevelOfSkillById(index: number, item: ISkillableLevelOfSkillMarineSuffix) {
        return item.id;
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackOrganizationChartById(index: number, item: IOrganizationChartMarineSuffix) {
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
