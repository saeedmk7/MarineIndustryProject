import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpResponse, HttpErrorResponse, HttpEventType} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model/evaluate-criteria-data-marine-suffix.model';
import { EvaluateCriteriaDataMarineSuffixService } from './evaluate-criteria-data-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';
import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model/evaluate-criteria-training-marine-suffix.model';
import { EvaluateCriteriaTrainingMarineSuffixService } from 'app/entities/evaluate-criteria-training-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {MONTHS} from "app/shared/constants/months.constants";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {Principal} from "app/core";
import {MediaProductTypeMarineSuffixService} from "app/entities/media-product-type-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";

@Component({
    selector: 'mi-evaluate-criteria-data-marine-suffix-update',
    templateUrl: './evaluate-criteria-data-marine-suffix-update.component.html'
})
export class EvaluateCriteriaDataMarineSuffixUpdateComponent implements OnInit {
    evaluateCriteriaData: IEvaluateCriteriaDataMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];

    evaluatecriteriatrainings: IEvaluateCriteriaTrainingMarineSuffix[];

    currentPerson: IPersonMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];

    recommenedOrgCharts: IOrganizationChartMarineSuffix[];

    finishDateValidation: number;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    currentAccount: any;

    runMonths: any = MONTHS.sort(function(a,b)
    {
        return (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0);
    });
    years: any = GREGORIAN_START_END_DATE.map(a => a.year);


    progress: { percentage: number } = { percentage: 0 }
    file: File;

    validFileTypes: string[] = ["image/gif","image/jpeg","image/jpg","image/png","image/TIFF","image/bmp","application/pdf","application/x-zip-compressed"];
    fileHasError: boolean = true;
    fileMessage: string;
    message: string;
    uploadingFile: boolean = false;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected evaluateCriteriaDataService: EvaluateCriteriaDataMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected evaluateCriteriaTrainingService: EvaluateCriteriaTrainingMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private principal : Principal,
        private treeUtilities: TreeUtilities,
        protected organizationChartService: OrganizationChartMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ evaluateCriteriaData }) => {
            this.evaluateCriteriaData = evaluateCriteriaData;
        });
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
                    this.currentPerson = resp.body;
                    this.loadOrgCharts();
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        });
        this.evaluateCriteriaTrainingService.query().subscribe(
            (res: HttpResponse<IEvaluateCriteriaTrainingMarineSuffix[]>) => {
                this.evaluatecriteriatrainings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        /*this.organizationChartService.query().subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                this.organizationcharts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }
    change(i){
        //this.router.navigateByUrl(i);
        this.router.navigateByUrl(i);
    }
    loadOrgCharts(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.handleOrgChartView();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.handleOrgChartView();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    handleOrgChartView(){
        if(this.isSuperUsers) {
            this.recommenedOrgCharts = this.organizationcharts;
            return;
        }
        if(this.treeUtilities.hasChild(this.organizationcharts, this.currentPerson.organizationChartId))
        {
            let orgIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, this.currentPerson.organizationChartId).filter(this.treeUtilities.onlyUnique);
            this.recommenedOrgCharts = this.organizationcharts.filter(a => orgIds.includes(a.id));
        }
        else{
            this.recommenedOrgCharts = [];
            this.recommenedOrgCharts.push(this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId));
        }
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
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    /*setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }*/

    previousState() {
        window.history.back();
    }

    setFileData(event, entity, field, isImage) {

        this.fileMessage = "";
        this.fileHasError = true;
        //this.dataUtils.setFileData(event, entity, field, isImage);
        if (event && event.target.files && event.target.files[0]) {
            this.file = event.target.files[0];
            this.validateFile(this.file);
        }
        else{
            this.fileHasError = true;
            this.fileMessage = "انتخاب فایل اجباری است.";
        }
    }

    uploadFile() {

        this.uploadingFile = true;
        let formdata: FormData = new FormData();

        formdata.append('file', this.file);
        this.evaluateCriteriaDataService.uploadFile(formdata).subscribe(event => {
                if (event instanceof HttpResponse) {
                    if (event.body) {
                        debugger;
                        this.evaluateCriteriaData.fileDoc = event.body.toString();
                        this.fileMessage = "آپلود فایل با موفقیت انجام شد.";
                    }
                }
            },
            () => {
                this.uploadingFile = false;
                this.fileMessage = "آپلود فایل با خطا روبرو شد. لطفا عملیات را بعدا دوباره تکرار کنید.";
                this.fileHasError = true;
                this.onSaveError()
            });
    }
    validateFile(file){
        //file.name.split('.')[file.name.split('.').length-1] == 'rar'
        if(this.validFileTypes.includes(file.type)){
            if((file.size / 1024 / 1024) < 10) {
                this.fileHasError = false;
                this.fileMessage = "فایل معتبر است.";
            }
            else{
                this.fileHasError = true;
                this.fileMessage = "حجم فایل بیش از حد مجاز است.";
            }
        }
        else{
            this.fileHasError = true;
            this.fileMessage = "فرمت فایل غیر مجاز است.";
        }
    }

    save() {
        this.isSaving = true;
        if (this.evaluateCriteriaData.id !== undefined) {
            this.subscribeToSaveResponse(this.evaluateCriteriaDataService.update(this.evaluateCriteriaData));
        } else {
            let formdata: FormData = new FormData();

            formdata.append('file', this.file);
            this.evaluateCriteriaDataService.uploadFile(formdata).subscribe(event =>{
                    if(event.type === HttpEventType.UploadProgress){
                        this.progress.percentage = Math.round(100 * event.loaded / event.total);
                    }
                    else if(event instanceof HttpResponse){
                        if(event.body){
                            this.evaluateCriteriaData.fileDoc = event.body;
                            this.subscribeToSaveResponse(this.evaluateCriteriaDataService.create(this.evaluateCriteriaData));
                        }
                    }
                },
                () => this.onSaveError());

        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluateCriteriaDataMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEvaluateCriteriaDataMarineSuffix>) => this.onSaveSuccess(),
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

    trackEvaluateCriteriaTrainingById(index: number, item: IEvaluateCriteriaTrainingMarineSuffix) {
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

    criteriaWeight(){
        const training = this.evaluatecriteriatrainings.find(a => a.id == this.evaluateCriteriaData.evaluateCriteriaTrainingId);
        if(training && training.criteriaWeight)
            return training.criteriaWeight;
        return 0;
    }
    calculateMeasure(){
        const measureCalc = (this.evaluateCriteriaData.firstNumber ? this.evaluateCriteriaData.firstNumber : 0) / (this.evaluateCriteriaData.secondNumber ? this.evaluateCriteriaData.secondNumber : 0);
        if(measureCalc) {
            this.evaluateCriteriaData.measureCalc = Number(measureCalc.toFixed(2));

            const result = measureCalc * this.criteriaWeight();
            if(result)
            {
                this.evaluateCriteriaData.result = Number(result.toFixed(2));

                this.evaluateCriteriaData.measureCalcPercent = Number(((measureCalc / result) * 100).toFixed(2));
            }

        }
    }
}
