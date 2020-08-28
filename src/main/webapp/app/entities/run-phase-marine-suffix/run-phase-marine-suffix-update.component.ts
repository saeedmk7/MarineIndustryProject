import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse, HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { RunPhaseMarineSuffixService } from './run-phase-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { FinalNiazsanjiReportPersonMarineSuffixService } from 'app/entities/final-niazsanji-report-person-marine-suffix';
import { Principal } from 'app/core';
import { RunningStepMarineSuffixService } from 'app/entities/running-step-marine-suffix';
import { IRunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';
import * as persianMoment from 'jalali-moment';
import { IRunPhaseTabModel, RunPhaseTabModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-tab.model';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { RunPhaseItemModel } from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-item.model';
import { RunRunningStepMarineSuffixService } from 'app/entities/run-running-step-marine-suffix';
import * as $ from 'jquery';
import {
    IRunPhaseSaveDataModel,
    RunPhaseSaveDataModel
} from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data.model';
import {
    IRunPhaseSaveDataItemModel,
    RunPhaseSaveDataItemModel
} from 'app/entities/run-phase-marine-suffix/run-phase-marine-suffix-save-data-item.model';
import { IRunRunningStepMarineSuffix } from 'app/shared/model/run-running-step-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { TeacherMarineSuffixService } from 'app/entities/teacher-marine-suffix';

@Component({
    selector: 'mi-run-phase-marine-suffix-update',
    templateUrl: './run-phase-marine-suffix-update.component.html',
    styleUrls: ['./run-phase-marine-suffix.component.scss']
})
export class RunPhaseMarineSuffixUpdateComponent implements OnInit {
    private _runPhase: IRunPhaseMarineSuffix;
    isSaving: boolean;
    runMonths: any = MONTHS.sort(function(a, b) {
        return a.id > b.id ? 1 : b.id > a.id ? -1 : 0;
    });
    teachers: ITeacherMarineSuffix[];
    educationalModule: IEducationalModuleMarineSuffix = {};
    people: IPersonMarineSuffix[];
    runningSteps: IRunningStepMarineSuffix[];
    finalniazsanjireport: IFinalNiazsanjiReportMarineSuffix;
    finalniazsanjireportPeople: IFinalNiazsanjiReportPersonMarineSuffix[];
    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];
    runPhaseTabModel: IRunPhaseTabModel[] = [];
    isAdmin: boolean = false;
    isModirKolAmozesh: boolean;
    isKarshenasArshadAmozesh: boolean;
    isSuperUser: boolean;
    currentAccount: any;
    currentPerson: IPersonMarineSuffix;

    documentUrl: string;
    fileHasError: boolean = true;
    fileMessage: string;

    finishDateValidation: number;

    constructor(
        private jhiAlertService: JhiAlertService,
        private runPhaseService: RunPhaseMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        private runningStepService: RunningStepMarineSuffixService,
        private runRunningStepMarineSuffixService: RunRunningStepMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private treeUtilities: TreeUtilities,
        private router: Router,
        private teacherService: TeacherMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {}

    checkDateValidation(event) {
        try {
            if (persianMoment(event.target.value, 'jYYYY/jMM/jDD').isValid()) {
                this.finishDateValidation = 1;
            } else {
                this.finishDateValidation = 2;
            }
        } catch (e) {
            this.finishDateValidation = 2;
        }
    }
    deleteElement(i) {
        $('#' + i).remove();
    }
    toggleColappse(i) {
        $('#' + i).collapse('toggle');
    }
    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ runPhase }) => {
            this.runPhase = runPhase;
            this.documentUrl = 'document-marine-suffix/runphase/' + this.runPhase.id;
            this.runningStepService.query().subscribe(
                (resp: HttpResponse<IRunningStepMarineSuffix[]>) => {
                    this.runningSteps = resp.body;

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

                        let runningsForThisStep = this.runningSteps.filter(w => w.stepNumber == a.stepNumber && w.isHeader == false);
                        runningsForThisStep.forEach(e => {
                            let runPhaseItem: RunPhaseItemModel = new RunPhaseItemModel();
                            runPhaseItem.id = e.id;
                            runPhaseItem.title = e.title; //+ (e.stepRequired ? "(اجباریست)" : "");
                            runPhaseItem.description = e.description == null ? '' : e.description;
                            runPhaseItem.required = e.stepRequired;
                            runPhaseItem.fileDocRequired = e.fileDocRequired;
                            runPhaseItem.stepNumber = a.stepNumber;
                            tab.runPhaseItems.push(runPhaseItem);
                        });

                        this.runPhaseTabModel.push(tab);
                    });

                    if (this.runPhase.id != undefined) {
                        const criteria1 = [
                            {
                                key: 'runPhaseId.equals',
                                value: this.runPhase.id
                            }
                        ];
                        this.runRunningStepMarineSuffixService
                            .query({
                                page: 0,
                                size: 20000,
                                criteria: criteria1,
                                sort: ['id', 'asc']
                            })
                            .subscribe(
                                (response: HttpResponse<IRunRunningStepMarineSuffix[]>) => {
                                    response.body.forEach(x => {
                                        this.runPhaseTabModel.forEach(w => {
                                            w.runPhaseItems.forEach(r => {
                                                if (r.id == x.runningStepId) {
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
            this.finalNiazsanjiReportService
                .find(this.runPhase.finalNiazsanjiReportId)
                .subscribe((resp: HttpResponse<IFinalNiazsanjiReportMarineSuffix>) => {
                    this.runPhase.teacherId = resp.body.teacherId;
                });
        });

        this.principal.identity().then(account => {
            this.currentAccount = account;
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }
            if (this.isModirKolAmozesh || this.isKarshenasArshadAmozesh) {
                this.isSuperUser = true;
            }

            this.personService.find(this.currentAccount.personId).subscribe(
                (res: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        if (this.teacherService.teachers) {
            this.teachers = this.teacherService.teachers;
        } else {
            this.teacherService.query().subscribe(
                (res: HttpResponse<ITeacherMarineSuffix[]>) => {
                    this.teachers = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }

        /*this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
        /*this.finalNiazsanjiReportService.query().subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {
                this.finalniazsanjireports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }

    uploadFile(id: number) {
        let fileToUpload = $('#file-' + id).prop('files')[0];

        let formdata: FormData = new FormData();

        formdata.append('file', fileToUpload);
        this.runPhaseService.uploadFile(formdata).subscribe(
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
        /*this.runPhaseService.deleteFile(fileDoc).subscribe(response => {

        });*/
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

    change(i) {
        this.router.navigateByUrl(i);
    }

    previousState() {
        window.history.back();
    }

    errorMessage: string;

    sendForEdit() {
        if (!this.runPhase.comment) {
            this.errorMessage = 'لطفا نظرات خود را نوشته سپس اقدام به ثبت جهت اصلاح نمائید.';
            return;
        }
        if (!this.runPhase.conversation) this.runPhase.conversation = '';
        this.runPhase.conversation +=
            ' ثبت نظر توسط ' +
            this.currentPerson.fullName +
            ' در تاریخ: ' +
            this.convertObjectDatesService.miladi2Shamsi(new Date()) +
            ' ثبت شد. ';
        this.runPhase.conversation += '\n';
        this.runPhase.conversation += this.currentPerson.fullName + ': ' + this.runPhase.comment;
        this.runPhase.conversation += '\n ------------------------------------------------------ \n';
        this.runPhase.status = 7;
        this.runPhaseService.update(this.runPhase).subscribe(
            (res: HttpResponse<IRunPhaseMarineSuffix>) => {
                this.previousState();
            },
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    finalize() {
        let isValid: boolean = true;
        this.errorMessage = '';
        this.runningSteps.forEach(a => {
            if (!a.isHeader) {
                if (a.stepRequired) {
                    if (!$('#chk-' + a.id).is(':checked')) {
                        let tab = this.runningSteps.filter(e => e.isHeader && e.stepNumber == a.stepNumber)[0];
                        isValid = false;
                        this.errorMessage +=
                            '*در ' + tab.title + ' مورد ' + a.title + ' ،اجباریست لطفا قبل از تایید نهایی آن را انجام دهید.' + '<br />';
                    }
                }
                if (a.fileDocRequired) {
                    if (!$('#fileDoc-' + a.id).val()) {
                        let tab = this.runningSteps.filter(e => e.isHeader && e.stepNumber == a.stepNumber)[0];
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
        if (isValid) {
            this.thisIsFinalize = true;
            this.runPhase.done = true;
            $('#save-entity').trigger('click');
        }
    }
    thisIsFinalize: boolean = false;
    save() {
        this.isSaving = true;

        let runPhaseSaveData: IRunPhaseSaveDataModel = new RunPhaseSaveDataModel();
        runPhaseSaveData.runPhaseId = this.runPhase.id;
        runPhaseSaveData.teacherId = this.runPhase.teacherId;
        runPhaseSaveData.runMonth = this.runPhase.runMonth;
        runPhaseSaveData.finalNiazsanjiReportId = this.runPhase.finalNiazsanjiReportId;
        runPhaseSaveData.description = this.runPhase.description;
        runPhaseSaveData.done = this.runPhase.done;
        runPhaseSaveData.stepNumber = 1;
        runPhaseSaveData.finalizeCost = this.runPhase.finalizeCost;
        runPhaseSaveData.finishDate = this.runPhase.finishDate;
        runPhaseSaveData.status = this.runPhase.status == undefined ? 0 : this.runPhase.status;
        runPhaseSaveData.runPhaseSaveDataItemModels = [];

        if (this.runPhase.conversation) runPhaseSaveData.conversion = this.runPhase.conversation;

        if (this.runPhase.comment) {
            if (!runPhaseSaveData.conversion) runPhaseSaveData.conversion = '';
            runPhaseSaveData.conversion +=
                ' ثبت نظر توسط ' +
                this.currentPerson.fullName +
                ' در تاریخ: ' +
                this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                ' ثبت شد. ';
            runPhaseSaveData.conversion += '\n';
            runPhaseSaveData.conversion += this.currentPerson.fullName + ': ' + this.runPhase.comment;
            runPhaseSaveData.conversion += '\n ------------------------------------------------------ \n';
        }

        this.runningSteps.forEach(a => {
            if (!a.isHeader) {
                let runPhaseSaveDataItemModel: IRunPhaseSaveDataItemModel = new RunPhaseSaveDataItemModel();
                runPhaseSaveDataItemModel.runningStepId = a.id;
                runPhaseSaveDataItemModel.description = $('#txt-' + a.id).val();
                runPhaseSaveDataItemModel.done = $('#chk-' + a.id).is(':checked');
                runPhaseSaveDataItemModel.fileDoc = $('#fileDoc-' + a.id).val();
                runPhaseSaveData.runPhaseSaveDataItemModels.push(runPhaseSaveDataItemModel);
            }
        });

        this.subscribeToSaveResponse(this.runPhaseService.saveRunPhaseModel(runPhaseSaveData));

        /*if (this.runPhase.id !== undefined) {
            this.subscribeToSaveResponse(this.runPhaseService.update(this.runPhase));
        } else {
            this.subscribeToSaveResponse(this.runPhaseService.create(this.runPhase));
        }*/
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRunPhaseMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRunPhaseMarineSuffix>) => this.onSaveSuccess(res.body),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess(res: IRunPhaseMarineSuffix) {
        this.isSaving = false;

        if (this.thisIsFinalize) this.previousState();
        else {
            this.runPhase = res;
            this.documentUrl = 'document-marine-suffix/runphase/' + this.runPhase.id;
        }
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

    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }

    trackFinalNiazsanjiReportById(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
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

    get runPhase() {
        return this._runPhase;
    }

    set runPhase(runPhase: IRunPhaseMarineSuffix) {
        this._runPhase = runPhase;
    }
}
