import { Component, OnInit } from '@angular/core';
import { JhiDataUtils, JhiEventManager, JhiLanguageService } from 'ng-jhipster';

import { Principal, AccountService, JhiLanguageHelper, UserService, IUser } from 'app/core';
import { JhiAlertService } from 'ng-jhipster/src/service/alert.service';
import { IPersonMarineSuffix, PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { PersonEducationalRecordsMarineSuffixService } from './person-educational-records-marine-suffix.service';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { IJobRecordMarineSuffix } from 'app/shared/model/job-record-marine-suffix.model';
import { JobRecordMarineSuffixService } from 'app/entities/job-record-marine-suffix';
import { IEducationalRecordMarineSuffix } from 'app/shared/model/educational-record-marine-suffix.model';
import { EducationalRecordMarineSuffixService } from 'app/entities/educational-record-marine-suffix';
import { IHomePagePersonEducationalModule } from 'app/shared/model/custom/home-page-person-educational-module';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { IResearchRecordMarineSuffix } from 'app/shared/model/research-record-marine-suffix.model';
import { ITeachingRecordMarineSuffix } from 'app/shared/model/teaching-record-marine-suffix.model';
import { ResearchRecordMarineSuffixService } from 'app/entities/research-record-marine-suffix';
import { TeachingRecordMarineSuffixService } from 'app/entities/teaching-record-marine-suffix';
import { Subscription } from 'rxjs';

@Component({
    selector: 'person-educational-records-marine-suffix',
    templateUrl: './person-educational-records-marine-suffix.component.html',
    styleUrls: ['person-educational-records-marine-suffix.scss']
})
export class PersonEducationalRecordsMarineSuffixComponent implements OnInit {
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
    eventSubscriber: Subscription;
    jobTitle: string;
    document: any;
    file: File;
    selectedFile: File;
    picUrl: string = '';
    oldPicUrl: string = '';
    isNewImage: boolean = false;
    organizationTitle: string = '';
    person: IPersonMarineSuffix = new PersonMarineSuffix();
    currentPerson: IPersonMarineSuffix = new PersonMarineSuffix();
    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];
    sumHourPersonEducationalModules: number = 0;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;
    isRoleEdit: boolean = false;
    isRoleDelete: boolean = false;

    constructor(
        private dataUtils: JhiDataUtils,
        private account: AccountService,
        private principal: Principal,
        private jhiAlertService: JhiAlertService,
        private settingService: PersonEducationalRecordsMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personMarineSuffixService: PersonMarineSuffixService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private treeUtilities: TreeUtilities,
        private jobRecordService: JobRecordMarineSuffixService,
        private educationalRecordService: EducationalRecordMarineSuffixService,
        private researchRecordService: ResearchRecordMarineSuffixService,
        private teachingRecordService: TeachingRecordMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {}
    printPage() {
        this.settingService.printPage(window.location.hostname, this.person.id);
    }
    get printUrl() {
        return `http://${window.location.hostname}:3000/person-educational-record-report/${this.person.id}`;
    }
    ngOnInit() {
        this.principal.identity().then(account => {
            this.setRoles(account);
            this.settingsAccount = this.copyAccount(account);
            this.myAccount = this.settingsAccount;

            const personId = this.getParameterByName('personId', '');
            if (personId) {
                let tempPerson: IPersonMarineSuffix = new PersonMarineSuffix();
                tempPerson.id = +personId;
                this.loadPersonData(tempPerson);
            } else {
                if (this.settingsAccount.imageUrl) {
                    this.oldPicUrl = this.settingsAccount.imageUrl;
                } else {
                    this.oldPicUrl = '/content/images/home/man.png';
                }
                if (this.settingsAccount.personId) {
                    this.personMarineSuffixService
                        .find(this.settingsAccount.personId)
                        .subscribe(
                            (res: HttpResponse<PersonMarineSuffix>) => this.onPersonSuccess(res.body),
                            (res: HttpResponse<any>) => this.onPersonError(res.body)
                        );
                    this.userService.findByPersonId(this.settingsAccount.personId).subscribe(
                        (res: HttpResponse<IUser>) => {
                            if (res.body.imageUrl) {
                                this.oldPicUrl = res.body.imageUrl;
                            } else {
                                this.oldPicUrl = '/content/images/home/man.png';
                            }
                        },
                        (res: HttpResponse<any>) => this.onPersonError(res.body)
                    );
                } else {
                    this.currentUserFullName = this.settingsAccount.login;
                }
            }
        });
        this.registerChangeSubscriber();
    }
    registerChangeSubscriber() {
        this.eventSubscriber = this.eventManager.subscribe('educationalRecordListModification', response =>
            this.prepareEducationalRecords(this.currentPerson.id)
        );
        this.eventSubscriber = this.eventManager.subscribe('jobRecordListModification', response =>
            this.prepareJobRecords(this.currentPerson.id)
        );
        this.eventSubscriber = this.eventManager.subscribe('researchRecordListModification', response =>
            this.prepareResearchRecords(this.currentPerson.id)
        );
        this.eventSubscriber = this.eventManager.subscribe('teachingRecordListModification', response =>
            this.prepareTeachingRecords(this.currentPerson.id)
        );
    }
    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }
    onPersonSuccess(body) {
        this.person = this.convertObjectDatesService.changeDate(body);
        this.currentPerson = this.person;

        if (this.person) {
            this.prepareOrgChart(this.person.organizationChartId);
            this.finalLoad(this.person);
        } else {
            this.currentUserFullName = this.settingsAccount.login;
        }
    }
    finalLoad(person: IPersonMarineSuffix) {
        this.currentUserFullName = person.fullName;
        this.jobTitle = person.jobTitle;
        if (this.orgCharts) {
            const org = this.orgCharts.find(a => a.id == person.organizationChartId);
            if (org) this.organizationTitle = org.fullTitle;
        }

        this.prepareJobRecords(person.id);
        this.prepareEducationalRecords(person.id);
        this.prepareTeachingRecords(person.id);
        this.prepareResearchRecords(person.id);
        this.prepareHomePagePersonEducationalModule(person.id);
    }
    loadPersonData($event: IPersonMarineSuffix) {
        if ($event) {
            let url = this.deleteQueryString();
            url = this.appendQueryString('personId', $event.id, url);
            window.location.href = url;
            this.personMarineSuffixService.find($event.id).subscribe(
                (res: HttpResponse<PersonMarineSuffix>) => {
                    this.person = this.convertObjectDatesService.changeDate(res.body);
                    this.searchPerson = this.person;
                    this.currentPerson = this.person;
                    this.prepareOrgChart(this.person.organizationChartId);
                    this.finalLoad(this.person);
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
            this.userService.findByPersonId($event.id).subscribe(
                (res: HttpResponse<IUser>) => {
                    if (res.body.imageUrl) {
                        this.oldPicUrl = res.body.imageUrl;
                    } else {
                        this.oldPicUrl = '/content/images/home/man.png';
                    }
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
            /*this.person = $event;
            this.finalLoad($event);*/
        }
    }
    prepareJobRecords(personId: number) {
        let criteria = [
            {
                key: 'personId.equals',
                value: personId
            }
        ];
        this.jobRecordService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['startDate', 'asc']
            })
            .subscribe(
                (resp: HttpResponse<IJobRecordMarineSuffix[]>) => {
                    this.jobRecords = resp.body;
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
    }
    prepareEducationalRecords(personId: number) {
        let criteria = [
            {
                key: 'personId.equals',
                value: personId
            }
        ];
        this.educationalRecordService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['startDate', 'asc']
            })
            .subscribe(
                (resp: HttpResponse<IEducationalRecordMarineSuffix[]>) => {
                    this.educationalRecords = resp.body;
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
    }
    prepareResearchRecords(personId: number) {
        let criteria = [
            {
                key: 'personId.equals',
                value: personId
            }
        ];
        this.researchRecordService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['id', 'asc']
            })
            .subscribe(
                (resp: HttpResponse<IResearchRecordMarineSuffix[]>) => {
                    this.researchRecords = resp.body;
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
    }
    prepareTeachingRecords(personId: number) {
        let criteria = [
            {
                key: 'personId.equals',
                value: personId
            }
        ];
        this.teachingRecordService
            .query({
                page: 0,
                size: 20000,
                criteria,
                sort: ['id', 'asc']
            })
            .subscribe(
                (resp: HttpResponse<ITeachingRecordMarineSuffix[]>) => {
                    this.teachingRecords = resp.body;
                },
                (res: HttpResponse<any>) => this.onPersonError(res.body)
            );
    }
    prepareHomePagePersonEducationalModule(personId: number) {
        this.finalNiazsanjiReportService.getHomePagePersonEducationalModule(personId).subscribe(
            (resp: HttpResponse<IHomePagePersonEducationalModule[]>) => {
                //.filter(a => a.status >= 90)
                this.homePagePersonEducationalModules = resp.body.sort(
                    (a, b) => (a.runDate > b.runDate ? 1 : a.runDate < b.runDate ? -1 : 0)
                );
                if (this.homePagePersonEducationalModules) {
                    this.homePagePersonEducationalModules.forEach(a => {
                        a.totalLearningTime =
                            (!a.learningTimePractical ? 0 : a.learningTimePractical) +
                            (!a.learningTimeTheorical ? 0 : a.learningTimeTheorical);
                        switch (a.status) {
                            case 100:
                                a.statusMeaning = 'خاتمه دوره';
                                break;
                            case 90:
                                a.statusMeaning = 'اجرا شده';
                                break;
                            case 80:
                                a.statusMeaning = 'برنامه ریزی شده';
                                break;
                            case 70:
                                a.statusMeaning = 'تصویب شوراء';
                                break;
                            case 0:
                                a.statusMeaning = 'شناسنامه آموزشی';
                                break;
                        }
                    });

                    this.sumHourPersonEducationalModules = this.homePagePersonEducationalModules
                        .map(a => (a.totalLearningTime ? a.totalLearningTime : 0))
                        .reduce((sum, current) => sum + current);
                }
                //this.makePersonHourPieChart(resp.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    prepareOrgChart(orgId: number) {
        if (this.organizationChartService.organizationchartsAll) {
            this.orgCharts = this.organizationChartService.organizationchartsAll;
            const org = this.organizationChartService.organizationchartsAll.find(a => a.id == orgId);
            if (org) this.organizationTitle = org.fullTitle;
            if (this.isSuperUsers) this.prepareSearchPerson([]);
            else if (this.isTopUsers) {
                const orgIds = this.treeUtilities.getAllOfThisTreeIds(this.orgCharts, this.person.organizationChartId);
                this.prepareSearchPerson(orgIds);
            }
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.orgCharts = res.body;
                    const org = res.body.find(a => a.id == orgId);
                    if (org) this.organizationTitle = org.fullTitle;

                    if (this.isSuperUsers) this.prepareSearchPerson([]);
                    else if (this.isTopUsers) {
                        const orgIds = this.treeUtilities.getAllOfThisTreeIds(this.orgCharts, this.person.organizationChartId);
                        this.prepareSearchPerson(orgIds);
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    prepareSearchPerson(orgIds: number[]) {
        if (orgIds.length > 0) {
            let criteria = [
                {
                    key: 'organizationChartId.in',
                    value: orgIds
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
                    (res: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.people = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            if (this.personService.people) {
                this.people = this.convertObjectDatesService.goClone(this.personService.people);
            } else {
                this.personService.query().subscribe(
                    (res: HttpResponse<IPersonMarineSuffix[]>) => {
                        this.people = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
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
        this.success = null;
        this.settingService.pushFileToStorage(this.selectedFile, this.settingsAccount.login).subscribe(
            res => {
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
            res => {
                this.error = 'خطایی در ثبت اطلاعات اتفاق افتاده است. لطفا بعدا دوباره امتحان نمائید.';
            }
        );
        /*this.settingService.pushFileToStorage(this.selectedFile).subscribe(
            (res) => {

                if(res.isOk) {
                    this.error = null;
                    this.success = 'OK';
                    this.picUrl = res.message
                }
                else {
                    this.success = null;
                    this.error = 'ERROR';
                }
            }
        );*/

        /*this.account.save(this.settingsAccount).subscribe(
            () => {
                this.error = null;
                this.success = 'OK';
                this.principal.identity(true).then(account => {
                    this.settingsAccount = this.copyAccount(account);
                });
                this.languageService.getCurrent().then(current => {
                    if (this.settingsAccount.langKey !== current) {
                        this.languageService.changeLanguage(this.settingsAccount.langKey);
                    }
                });
            },
            () => {
                this.success = null;
                this.error = 'ERROR';
            }
        );*/
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    show(event, picUrl) {
        this.error = null;
        this.success = null;
        let file = event.target.files[0];
        if (file) {
            this.selectedFile = file;

            const allowedTypes = ['image/png', 'image/jpeg', 'image/jpg'];

            if (allowedTypes.includes(file.type)) {
                if (file.size < 80000) {
                    this.dataUtils.toBase64(file, function(base64Data) {
                        picUrl = 'data:' + file.type + ';base64, ' + base64Data;
                    });
                    setTimeout(() => {
                        this.picUrl = picUrl;
                        this.isNewImage = true;
                    }, 1000);
                } else {
                    this.error = 'عکس انتخابی شما باید کوچکتر از 80 کیلوبایت باشد';
                    this.success = null;
                }
            } else {
                this.error = 'لطفا فقط از فرمت های png و jpg استفاده نمائید.';
                this.success = null;
            }
        } else {
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

    private setRoles(account: any) {
        if (account) {
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;
            if (account.authorities.find(a => a == 'ROLE_EDIT') !== undefined) this.isRoleEdit = true;
            if (account.authorities.find(a => a == 'ROLE_DELETE') !== undefined) this.isRoleDelete = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                this.isTopUsers = true;
        }
    }

    getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
    deleteQueryString(): string {
        let url = window.location.href;
        let indexOf = url.indexOf('?');
        if (indexOf > -1) {
            let extra = url.slice(indexOf, url.length);
            url = url.replace(extra, '');
        }
        return url + '?';
    }
    appendQueryString(name, value, url): string {
        /*let indexOf = url.indexOf('&');
        if (indexOf > -1) {*/
        url += name + '=' + value + '&';
        /*}
        else {
            url += name + "=" + value;
        }*/
        return url;
    }
}
