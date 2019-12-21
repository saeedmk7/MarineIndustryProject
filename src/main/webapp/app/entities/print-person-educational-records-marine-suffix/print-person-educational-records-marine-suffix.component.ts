import { Component, OnInit } from '@angular/core';
import {JhiDataUtils, JhiEventManager, JhiLanguageService} from 'ng-jhipster';

import {Principal, AccountService, JhiLanguageHelper, UserService, IUser} from 'app/core';
import {JhiAlertService} from "ng-jhipster/src/service/alert.service";
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {IJobRecordMarineSuffix} from "app/shared/model/job-record-marine-suffix.model";
import {JobRecordMarineSuffixService} from "app/entities/job-record-marine-suffix";
import {IEducationalRecordMarineSuffix} from "app/shared/model/educational-record-marine-suffix.model";
import {EducationalRecordMarineSuffixService} from "app/entities/educational-record-marine-suffix";
import {IHomePagePersonEducationalModule} from "app/shared/model/custom/home-page-person-educational-module";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IResearchRecordMarineSuffix} from "app/shared/model/research-record-marine-suffix.model";
import {ITeachingRecordMarineSuffix} from "app/shared/model/teaching-record-marine-suffix.model";
import {ResearchRecordMarineSuffixService} from "app/entities/research-record-marine-suffix";
import {TeachingRecordMarineSuffixService} from "app/entities/teaching-record-marine-suffix";
import {PersonEducationalRecordsMarineSuffixService} from "app/entities/person-educational-records-marine-suffix";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: 'print-person-educational-records-marine-suffix',
    templateUrl: './print-person-educational-records-marine-suffix.component.html',
    styleUrls: ['print-person-educational-records-marine-suffix.scss']
})
export class PrintPersonEducationalRecordsMarineSuffixComponent implements OnInit {
    error: string;
    success: string;
    settingsAccount: any;
    myAccount: any;
    jobRecords: IJobRecordMarineSuffix[] = [];
    educationalRecords: IEducationalRecordMarineSuffix[] = [];
    researchRecords: IResearchRecordMarineSuffix[] = [];
    teachingRecords: ITeachingRecordMarineSuffix[] = [];
    people: IPersonMarineSuffix[] = [];
    searchPerson: IPersonMarineSuffix;
    orgCharts: IOrganizationChartMarineSuffix[];

    currentUserFullName: string;
    jobTitle: string;
    document: any;
    file: File;
    selectedFile: File;
    picUrl: string = "";
    oldPicUrl: string = "";
    isNewImage: boolean = false;
    organizationTitle: string = "";
    person: IPersonMarineSuffix = new PersonMarineSuffix();
    currentPerson: IPersonMarineSuffix = new PersonMarineSuffix();
    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];
    sumHourPersonEducationalModules: number = 0;

    constructor(
        private dataUtils: JhiDataUtils,
        private account: AccountService,
        private principal: Principal,
        private jhiAlertService : JhiAlertService,
        private settingService: PersonEducationalRecordsMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personMarineSuffixService: PersonMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        private jobRecordService: JobRecordMarineSuffixService,
        private educationalRecordService: EducationalRecordMarineSuffixService,
        private researchRecordService: ResearchRecordMarineSuffixService,
        private teachingRecordService: TeachingRecordMarineSuffixService,
        private personService : PersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute,
        private eventManager: JhiEventManager
    ) {}

    ngOnInit() {

        this.activatedRoute.data.subscribe(({person}) => {

            this.loadPersonData(person);
        });

        document.getElementById('mi-page-ribbon').remove();
        document.getElementById('navbar').remove();
        document.getElementById('col-navbar').remove();
        document.getElementById('popup').remove();
        document.getElementById('mi-footer').remove();
        document.getElementById('mi-topbar').remove();
        setTimeout(function() {

            window.print();
        }, 5000)
    }
    onPersonSuccess(body) {

        this.person = this.convertObjectDatesService.changeDate(body);
        this.currentPerson = this.person;

        if (this.person) {
            this.prepareOrgChart(this.person.organizationChartId);
            this.finalLoad(this.person);
        }
        else {
            this.currentUserFullName = this.settingsAccount.login;
        }
    }
    finalLoad(person: IPersonMarineSuffix){

        this.currentUserFullName = person.fullName;
        this.jobTitle = person.jobTitle;
        if(this.orgCharts) {
            const org =this.orgCharts.find(a => a.id == person.organizationChartId);
            if (org)
                this.organizationTitle = org.fullTitle;
        }

        this.prepareJobRecords(person.id);
        this.prepareEducationalRecords(person.id);
        this.prepareTeachingRecords(person.id);
        this.prepareResearchRecords(person.id);
        this.prepareHomePagePersonEducationalModule(person.id);
    }
    loadPersonData($event: IPersonMarineSuffix)
    {
        if($event){
            this.personMarineSuffixService.find($event.id).subscribe(
                (res: HttpResponse<PersonMarineSuffix>) =>
                {
                    this.person = this.convertObjectDatesService.changeDate(res.body);
                    this.finalLoad(this.person);
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
            this.userService.findByPersonId($event.id).subscribe(
                (res: HttpResponse<IUser>) => {
                    if(res.body.imageUrl){
                        this.oldPicUrl = res.body.imageUrl;
                    }
                    else {
                        this.oldPicUrl = "/content/images/home/man.png";
                    }
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
            /*this.person = $event;
            this.finalLoad($event);*/
        }
    }
    prepareJobRecords(personId: number){

        let criteria = [{
            key: 'personId.equals',
            value: personId
        }];
        this.jobRecordService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["startDate", "asc"]
        }).subscribe((resp:HttpResponse<IJobRecordMarineSuffix[]>) => {
            this.jobRecords = resp.body;
        },
        (res: HttpResponse<any>) => this.onPersonError(res.body));
    }
    prepareEducationalRecords(personId: number){
        let criteria = [{
            key: 'personId.equals',
            value: personId
        }];
        this.educationalRecordService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["startDate", "asc"]
        }).subscribe((resp:HttpResponse<IEducationalRecordMarineSuffix[]>) => {
            this.educationalRecords = resp.body;
        },
        (res: HttpResponse<any>) => this.onPersonError(res.body));
    }
    prepareResearchRecords(personId: number){
        let criteria = [{
            key: 'personId.equals',
            value: personId
        }];
        this.researchRecordService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe((resp:HttpResponse<IResearchRecordMarineSuffix[]>) => {
            this.researchRecords = resp.body;
        },
        (res: HttpResponse<any>) => this.onPersonError(res.body));
    }
    prepareTeachingRecords(personId: number){
        let criteria = [{
            key: 'personId.equals',
            value: personId
        }];
        this.teachingRecordService.query({
            page: 0,
            size: 20000,
            criteria,
            sort: ["id", "asc"]
        }).subscribe((resp:HttpResponse<ITeachingRecordMarineSuffix[]>) => {
            this.teachingRecords = resp.body;
        },
        (res: HttpResponse<any>) => this.onPersonError(res.body));
    }
    prepareHomePagePersonEducationalModule(personId: number){
        this.finalNiazsanjiReportService.getHomePagePersonEducationalModule(personId).subscribe((resp: HttpResponse<IHomePagePersonEducationalModule[]>) => {

                this.homePagePersonEducationalModules = resp.body.filter(a => a.status >= 90).sort((a,b) => (a.runDate > b.runDate) ? 1 : (a.runDate < b.runDate) ? -1 : 0);
                if(this.homePagePersonEducationalModules) {
                    this.homePagePersonEducationalModules.forEach(a => {
                        a.totalLearningTime = (!a.learningTimePractical ? 0 : a.learningTimePractical) + (!a.learningTimeTheorical ? 0 : a.learningTimeTheorical);
                        switch (a.status) {
                            case 100:
                                a.statusMeaning = "خاتمه دوره";
                                break;
                            case 90:
                                a.statusMeaning = "اجرا شده";
                                break;
                            case 80:
                                a.statusMeaning = "برنامه ریزی شده";
                                break;
                            case 70:
                                a.statusMeaning = "تصویب شوراء";
                                break;
                            case 0:
                                a.statusMeaning = "شناسنامه آموزشی";
                                break;
                        }
                    });

                    this.sumHourPersonEducationalModules = this.homePagePersonEducationalModules.map(a => a.totalLearningTime ? a.totalLearningTime : 0).reduce((sum, current) => sum + current);
                }
                //this.makePersonHourPieChart(resp.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }
    prepareOrgChart(orgId: number){

        if(this.organizationChartService.organizationchartsAll)
        {
            this.orgCharts = this.organizationChartService.organizationchartsAll;
            const org = this.organizationChartService.organizationchartsAll.find(a => a.id == orgId);
            if(org)
                this.organizationTitle = org.fullTitle;
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.orgCharts = res.body;
                    const org = res.body.find(a => a.id == orgId);
                    if(org)
                        this.organizationTitle = org.fullTitle;
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }
    prepareSearchPerson(orgIds: number[]) {
        if(orgIds.length > 0)
        {
            let criteria = [{
                key: 'organizationChartId.in',
                value: orgIds
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id", "asc"]
            }).subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }
        else {
            if (this.personService.people) {
                this.people = this.personService.people;
            }
            else {
                this.personService.query().subscribe((res: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.people = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message));
            }
        }
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    onPersonError(body) {

        this.jhiAlertService.error(body);
    }
    save() {

        this.error = null;
        this.success= null;
        this.settingService.pushFileToStorage(this.selectedFile,this.settingsAccount.login).subscribe(
            (res) => {

                this.success = 'OK';
                //this.picUrl = res.body.url;
                this.myAccount.imageUrl = res.body.url;
                this.account.save(this.myAccount).subscribe(
                    () => {
                        window.location.reload();
                    },
                    () => {
                        this.error = 'خطایی در ثبت اطلاعات اتفاق افتاده است. لطفا بعدا دوباره امتحان نمائید.';
                    }
                );
            },
            (res) =>{
                this.error = 'خطایی در ثبت اطلاعات اتفاق افتاده است. لطفا بعدا دوباره امتحان نمائید.';
            }
        );
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);

    }
    show (event,picUrl){

        this.error = null;
        this.success = null;
        let file = event.target.files[0];
        if(file) {
            this.selectedFile = file;

            const allowedTypes = ["image/png","image/jpeg","image/jpg"];

            if(allowedTypes.includes(file.type))
            {
                if(file.size < 80000) {
                    this.dataUtils.toBase64(file, function (base64Data) {
                        picUrl = "data:" + file.type + ";base64, " + base64Data;
                    });
                    setTimeout(() => {
                        this.picUrl = picUrl;
                        this.isNewImage = true;
                    }, 1000);
                }
                else {
                    this.error = 'عکس انتخابی شما باید کوچکتر از 80 کیلوبایت باشد';
                    this.success = null;
                }
            }
            else {
                this.error = 'لطفا فقط از فرمت های png و jpg استفاده نمائید.';
                this.success = null;
            }

        }
        else {
            this.error = 'لطفا یک فایل انتخاب نمائید.';
            this.success = null;
        }

    }

    copyAccount(account) {
        return {
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl,
            personId: account.personId
        };
    }
}
