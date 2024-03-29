import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiLanguageService } from 'ng-jhipster';

import { VERSION } from 'app/app.constants';
import { JhiLanguageHelper, Principal, LoginModalService, LoginService, UserService, IUser, User } from 'app/core';
import { ProfileService } from '../profiles/profile.service';
import { RequestOrganizationNiazsanjiMarineSuffixService } from 'app/entities/request-organization-niazsanji-marine-suffix';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IPersonMarineSuffix, PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';
import { BeautySpeechMarineSuffixService } from 'app/entities/beauty-speech-marine-suffix';
/*import {TypedOptions} from 'typed.js';
import Typed from 'typed.js/src/typed.js';*/
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import * as $ from 'jquery';
import { UsersRequestMarineSuffixService } from 'app/entities/users-request-marine-suffix';
import { RequestEducationalModuleMarineSuffixService } from 'app/entities/request-educational-module-marine-suffix';
import { SlideInOutAnimation } from 'app/shared/animations';
import { RequestNiazsanjiFardiMarineSuffixService } from 'app/entities/request-niazsanji-fardi-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { NiazsanjiFardiMarineSuffixService } from 'app/entities/niazsanji-fardi-marine-suffix';
import { FinalOrganizationNiazsanjiMarineSuffixService } from 'app/entities/final-organization-niazsanji-marine-suffix';
import { EducationalHistoryMarineSuffixService } from 'app/entities/educational-history-marine-suffix/educational-history-marine-suffix.service';
import { RequestOtherNiazsanjiMarineSuffixService } from 'app/entities/request-other-niazsanji-marine-suffix';
import { PreJobNiazsanjiMarineSuffixService } from 'app/entities/pre-job-niazsanji-marine-suffix';
import { PrioritizeRequestNiazsanjiMarineSuffixService } from 'app/entities/prioritize-request-niazsanji-marine-suffix';
import { NiazsanjiIntegrationMarineSuffixService } from 'app/entities/niazsanji-integration-marine-suffix';
import { RunPhaseMarineSuffixService } from 'app/entities/run-phase-marine-suffix';
import { MatchingEducationalRecordMarineSuffixService } from 'app/entities/matching-educational-record-marine-suffix';
import { ApplicationProcessMarineSuffixService } from 'app/entities/application-process-marine-suffix';
import * as moment from 'jalali-moment';

@Component({
    selector: 'mi-topbar',
    templateUrl: './topbar.component.html',
    styleUrls: ['topbar.scss'],
    animations: [SlideInOutAnimation]
})
export class TopbarComponent implements OnInit, AfterViewInit, OnDestroy {
    requestNiazsanjiFardis: IRequestNiazsanjiFardiMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    organizationcharts: IOrganizationChartMarineSuffix[];
    users: IUser[];
    people: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;

    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    applicationProcessCounter: number;
    matchingEducationalRecordCounter: number;
    requestOrganizationNiazsanjiCounter: number;
    usersRequestCounter: number;
    referUsersRequestCounter: number;
    educationalModuleRequestCounter: number;
    educationalHistoryCounter: number;
    niazSanjiFardiRequestCounter: number;
    prioritizeRequestsCounter: number;
    niazsanjiOtherCounter: number;
    niazsanjiJobCounter: number;
    niazSanjiFardiCounter: number;
    niazsanjiIntegrationCounter: number;
    finalOrganizationNiazsanjiConter: number;
    runPhaseRequestCounter: number;
    forEditRunPhaseRequestCounter: number;
    imgUrl: string = '';
    currentUserFullName: string;
    jobTitle: string;
    user: User;
    speeches: string[];
    isAdmin: boolean = false;
    intervals: any;
    dailyIntervals: any;
    speechIntervals: any;
    currentSpeech: string;
    show: boolean = true;

    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;

    badError: string;
    currentAccount: any;
    todayDate: string;

    constructor(
        private userService: UserService,
        protected requestNiazsanjiFardiService: RequestNiazsanjiFardiMarineSuffixService,
        protected requestOtherNiazsanjiService: RequestOtherNiazsanjiMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        protected niazsanjiFardiService: NiazsanjiFardiMarineSuffixService,
        protected finalOrganizationNiazsanjiService: FinalOrganizationNiazsanjiMarineSuffixService,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private educationalHistoryService: EducationalHistoryMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router,
        private requestOrganizationNiazsanjiMarineSuffixService: RequestOrganizationNiazsanjiMarineSuffixService,
        private matchingEducationalRecordMarineSuffixService: MatchingEducationalRecordMarineSuffixService,
        private applicationProcessMarineSuffixService: ApplicationProcessMarineSuffixService,
        private jhiAlertService: JhiAlertService,
        private beautySpeechService: BeautySpeechMarineSuffixService,
        private usersRequestMarineSuffixService: UsersRequestMarineSuffixService,
        private requestEducationalModuleMarineSuffixService: RequestEducationalModuleMarineSuffixService,
        private runPhaseService: RunPhaseMarineSuffixService,
        private prioritizeRequestNiazsanjiService: PrioritizeRequestNiazsanjiMarineSuffixService,
        private niazsanjiIntegrationService: NiazsanjiIntegrationMarineSuffixService,
        protected treeUtilities: TreeUtilities,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.isNavbarCollapsed = true;

        this.dailyIntervals = setInterval(() => {
            let criteria = [{ key: 'isActive.equals', value: true }];

            criteria.push({
                key: 'beautySpeechAuthorityName.in',
                value: this.currentAccount.authorities
            });
            this.beautySpeechService
                .query({
                    page: 0,
                    size: 1000,
                    criteria
                })
                .subscribe(
                    (res: HttpResponse<IBeautySpeechMarineSuffix[]>) => this.showBeautySpeechResult(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }, 3600000);
    }

    ngOnInit() {
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
        const now: any = new Date();
        this.todayDate = moment(now)
            .locale('fa')
            .format('YYYY/MM/DD');
        this.profileService.getProfileInfo().then(profileInfo => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });
        this.principal.identity().then(account => {
            this.currentAccount = account;

            if (account) {
                if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
                if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
                if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
                if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
                    this.isKarshenasArshadAmozeshSazman = true;

                if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
                if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                    this.isTopUsers = true;

                if (account.imageUrl) {
                    this.imgUrl = account.imageUrl;
                } else {
                    this.imgUrl = '../../../content/images/home/man.png';
                }
                if (account.login) {
                    this.fillPerson(account);
                } else {
                    window.location.reload();
                }
            } else {
                this.imgUrl = '../../../content/images/home/man.png';
            }
            if (this.organizationChartService.organizationchartsAll) {
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
            } else {
                this.organizationChartService.query().subscribe(
                    (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                        this.organizationcharts = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            }
            this.intervals = setInterval(() => {
                if (this.isAuthenticated()) {
                    this.getNewRequestOrganization();
                    this.getNewMatchingEducationalRecord();
                    this.getNewApplicationProcess();
                    this.countAllRequestNiazSanjiFardi();
                    this.countRequestNiazsanjiOther();
                    this.countPreJob();
                    this.getNewUsersRequest();
                    this.getNewReferalUsersRequest();
                    //this.getFinalNiazSanjiFardis();
                    this.getFinalorganizationNiazsanji();
                    this.getEducationalHistories();
                    if (this.isTopUsers) {
                        this.getEducationalModuleRequests();
                        this.getPrioritizeRequests();
                        this.getForEditRunPhaseRequestCounter();
                    }
                    if (this.isSuperUsers) {
                        this.getIntegrations();
                        this.getRunPhaseRequestCounter();
                    }
                }
                //this.checkCurrentFullName();
            }, 20000);

            let criteria = [{ key: 'isActive.equals', value: true }];
            criteria.push({
                key: 'beautySpeechAuthorityName.in',
                value: this.currentAccount.authorities
            });
            this.beautySpeechService
                .query({
                    page: 0,
                    size: 1000,
                    criteria
                })
                .subscribe(
                    (res: HttpResponse<IBeautySpeechMarineSuffix[]>) => this.showBeautySpeechResult(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            /* if(this.isAdmin || this.isModirKolAmozesh) {
                 this.getNewRequestOrganization();
                 this.getNewUsersRequest();
                 this.getFinalNiazSanjiFardis();
                 this.intervals = setInterval(() => {         //replaced function() by ()=>
                     this.getNewRequestOrganization();
                     this.getNewUsersRequest();
                     this.getFinalNiazSanjiFardis();
                     this.checkCurrentFullName();
                     /!*this.getNewEducationalModuleRequest();*!/
                 }, 10000);
                 /!*setInterval(this.getNewRequestOrganization(),30000);
                 setInterval(this.getNewUsersRequest(),20000);
                 setInterval(this.getNewEducationalModuleRequest(),40000);*!/
             }
             else if (!this.isKarbar){
                 this.createCriteriaRequestNiazSanjiFardi();
                 this.intervals = setInterval(() => {         //replaced function() by ()=>
                     this.createCriteriaRequestNiazSanjiFardi();
                     this.checkCurrentFullName();
                     /!*this.getNewEducationalModuleRequest();*!/
                 }, 10000);
             }*/
            /*this.currentAccount = account;
            this.loadAll();
            this.registerChangeInUsers();*/
        });
    }

    getRunPhaseRequestCounter() {
        if (this.isSuperUsers) {
            let criteria = [];
            criteria.push({
                key: 'status.equals',
                value: 5
            });
            this.runPhaseService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.runPhaseRequestCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getForEditRunPhaseRequestCounter() {
        if (this.currentPerson.organizationChartId && this.organizationcharts) {
            const orgIds = this.treeUtilities
                .getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId)
                .filter(this.treeUtilities.onlyUnique);
            let criteria = [];
            criteria.push(
                {
                    key: 'status.equals',
                    value: 7
                },
                {
                    key: 'organizationChartId.in',
                    value: orgIds
                }
            );
            this.runPhaseService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.forEditRunPhaseRequestCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getEducationalModuleRequests() {
        if (this.currentPerson) {
            let orgId = this.currentPerson.organizationChartId;
            let criteria = [];
            if (this.isSuperUsers) {
                criteria.push({
                    key: 'status.equals',
                    value: 0
                });
                criteria.push({
                    key: 'requestStatus.equals',
                    value: 'NEW'
                });
            } else {
                criteria.push({
                    key: 'status.equals',
                    value: orgId
                });
                criteria.push({
                    key: 'requestStatus.equals',
                    value: 'NEW'
                });
            }
            this.requestEducationalModuleMarineSuffixService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.educationalModuleRequestCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getPrioritizeRequests() {
        if (this.currentPerson && this.organizationcharts) {
            let criteria = [];
            const orgIds = this.treeUtilities
                .getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId)
                .filter(this.treeUtilities.onlyUnique);
            criteria.push({
                key: 'organizationChartId.in',
                value: orgIds
            });
            criteria.push({
                key: 'requestStatus.equals',
                value: 'NEW'
            });
            this.prioritizeRequestNiazsanjiService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.prioritizeRequestsCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getIntegrations() {
        if (this.isKarshenasArshadAmozeshSazman) {
            let criteria = [{ key: 'status.equals', value: '0' }];
            this.niazsanjiIntegrationService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('usersRequestCount', res.body);
                        this.niazsanjiIntegrationCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        if (this.isModirKolAmozesh) {
            let criteria = [{ key: 'status.equals', value: 10 }];
            this.niazsanjiIntegrationService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('usersRequestCount', res.body);
                        this.niazsanjiIntegrationCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getEducationalHistories() {
        if (this.currentPerson) {
            let orgId = this.currentPerson.organizationChartId;
            let criteria = [
                {
                    key: 'status.equals',
                    value: orgId
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.educationalHistoryService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.educationalHistoryCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getFinalNiazSanjiFardis() {
        if (this.isKarshenasArshadAmozeshSazman) {
            let criteria = [{ key: 'status.equals', value: '0' }];
            this.niazsanjiFardiService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('usersRequestCount', res.body);
                        this.niazSanjiFardiCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        if (this.isModirKolAmozesh) {
            let criteria = [{ key: 'status.equals', value: 10 }];
            this.niazsanjiFardiService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('usersRequestCount', res.body);
                        this.niazSanjiFardiCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getFinalorganizationNiazsanji() {
        let status: string;
        if (this.isKarshenasArshadAmozeshSazman) {
            status = '0';
        }
        if (this.isModirKolAmozesh) {
            status = '10';
        }
        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh) {
            let criteria = [{ key: 'status.equals', value: status }];
            this.finalOrganizationNiazsanjiService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('usersRequestCount', res.body);
                        this.finalOrganizationNiazsanjiConter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    getNewRequestOrganization() {
        if (this.isTopUsers) {
            if (this.currentPerson) {
                let orgId = this.currentPerson.organizationChartId;
                let criteria = [
                    {
                        key: 'status.equals',
                        value: orgId
                    },
                    {
                        key: 'requestStatus.equals',
                        value: 'NEW'
                    }
                ];
                this.requestOrganizationNiazsanjiMarineSuffixService
                    .count({
                        page: 0,
                        size: 2000,
                        criteria,
                        sort: null
                    })
                    .subscribe(
                        (res: HttpResponse<any>) => {
                            //localStorage.setItem('requestOrganizationNiazsanjiCount', res.body);
                            this.requestOrganizationNiazsanjiCounter = res.body;
                        },
                        (res: HttpErrorResponse) => this.onError(res.message)
                    );
            }
        }
    }
    getNewMatchingEducationalRecord() {
        if (this.currentPerson) {
            let orgId = this.currentPerson.organizationChartId;
            let criteria = [
                {
                    key: 'chartStatus.equals',
                    value: orgId
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.matchingEducationalRecordMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('requestOrganizationNiazsanjiCount', res.body);
                        this.matchingEducationalRecordCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        if (this.isKarshenasArshadAmozeshSazman) {
            let criteria = [
                {
                    key: 'bossStatus.in',
                    value: [10, 30]
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.matchingEducationalRecordMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('requestOrganizationNiazsanjiCount', res.body);
                        this.matchingEducationalRecordCounter += res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        if (this.isModirKolAmozesh) {
            let criteria = [
                {
                    key: 'bossStatus.in',
                    value: [20, 30]
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.matchingEducationalRecordMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('requestOrganizationNiazsanjiCount', res.body);
                        this.matchingEducationalRecordCounter += res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    getNewApplicationProcess() {
        if (this.currentPerson) {
            let orgId = this.currentPerson.organizationChartId;
            let criteria = [
                {
                    key: 'chartStatus.equals',
                    value: orgId
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.applicationProcessMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('requestOrganizationNiazsanjiCount', res.body);
                        this.applicationProcessCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        if (this.isKarshenasArshadAmozeshSazman) {
            let criteria = [
                {
                    key: 'bossStatus.in',
                    value: [10, 30]
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.applicationProcessMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('requestOrganizationNiazsanjiCount', res.body);
                        this.applicationProcessCounter += res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
        if (this.isModirKolAmozesh) {
            let criteria = [
                {
                    key: 'bossStatus.in',
                    value: [20, 30]
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.applicationProcessMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('requestOrganizationNiazsanjiCount', res.body);
                        this.applicationProcessCounter += res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    fillPerson(account) {
        if (account.personId) {
            this.personService
                .find(account.personId)
                .subscribe(
                    (res: HttpResponse<PersonMarineSuffix>) => this.onPersonSuccess(res.body),
                    (res: HttpResponse<any>) => this.onPersonError(res.body)
                );
        } else {
            this.badError = 'برای کاربری شما فردی تخصیص داده نشده لطفا با مدیریت سامانه تماس بگیرید و مراتب را اطلاع دهید.';
            this.currentUserFullName = account.login;
        }
    }

    checkCurrentFullName() {
        let fullName = document.getElementById('currenUserFullNameTopBar').textContent;
        if (fullName != this.currentUserFullName) {
            this.fillPerson(this.currentAccount);
        }
    }

    onPersonSuccess(body) {
        this.currentPerson = body;
        if (this.currentPerson) {
            this.currentUserFullName = this.currentPerson.name + ' ' + this.currentPerson.family;
            this.jobTitle = this.currentPerson.jobTitle;
        } else {
            this.currentUserFullName = this.user.login;
        }
        this.countAllRequestNiazSanjiFardi();
        this.countPreJob();
        this.countRequestNiazsanjiOther();
        this.getNewRequestOrganization();
        this.getNewMatchingEducationalRecord();
        this.getNewApplicationProcess();
        this.getNewReferalUsersRequest();
        //this.getFinalNiazSanjiFardis();
        this.getFinalorganizationNiazsanji();
        this.getEducationalHistories();
        if (this.isTopUsers) {
            this.getEducationalModuleRequests();
            this.getPrioritizeRequests();
            this.getForEditRunPhaseRequestCounter();
        }
        if (this.isSuperUsers) {
            this.getIntegrations();
            this.getRunPhaseRequestCounter();
        }
    }

    onPersonError(body) {
        this.jhiAlertService.error(body);
    }

    ngAfterViewInit() {}

    sleep(time) {
        return new Promise(resolve => setTimeout(resolve, time));
    }

    showBeautySpeechResult(result: IBeautySpeechMarineSuffix[]) {
        this.speeches = result.map(a => a.description).filter(this.treeUtilities.onlyUnique);
        this.showSpeech(this.speeches);
        this.speechIntervals = setInterval(() => {
            if (!this.speeches) return;
            this.index++;
            if (this.index < 0) {
                this.index = this.speeches.length - 1;
            } else if (this.index > this.speeches.length - 1) {
                this.index = 0;
            }
            this.currentSpeech = this.speeches[this.index];
            this.show = false;
            this.sleep(1000).then(() => {
                this.show = true;
            });
        }, 30000);
    }

    index: number = 0;

    showSpeech(speeches: string[]) {
        if (!this.speeches) return;
        this.index++;
        if (this.index < 0) {
            this.index = this.speeches.length - 1;
        } else if (this.index > speeches.length - 1) {
            this.index = 0;
        }
        this.currentSpeech = speeches[this.index];
        this.show = false;
        this.sleep(1000).then(() => {
            this.show = true;
        });
    }

    changeSpeech(incremental: boolean) {
        if (!incremental) this.index = this.index - 2;
        this.showSpeech(this.speeches);
    }

    onError(str) {
        this.jhiAlertService.error(str);
    }

    changeLanguage(languageKey: string) {
        this.languageService.changeLanguage(languageKey);
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.loginService.logout();
        this.router.navigate(['']).then(() => {
            setTimeout(() => {
                this.login();
            }, 1000);
        });
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }

    //request niazsanji fardi
    countAllRequestNiazSanjiFardi() {
        if (this.currentPerson) {
            let orgId = this.currentPerson.organizationChartId;
            let criteria = [
                {
                    key: 'status.equals',
                    value: orgId
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.requestNiazsanjiFardiService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.niazSanjiFardiRequestCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    countRequestNiazsanjiOther() {
        if (this.currentPerson) {
            let orgId = this.currentPerson.organizationChartId;
            let criteria = [
                {
                    key: 'status.equals',
                    value: orgId
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.requestOtherNiazsanjiService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.niazsanjiOtherCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    countPreJob() {
        if (this.currentPerson) {
            let orgId = this.currentPerson.organizationChartId;
            let criteria = [
                {
                    key: 'status.equals',
                    value: orgId
                },
                {
                    key: 'requestStatus.equals',
                    value: 'NEW'
                }
            ];
            this.preJobNiazsanjiService
                .count({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.niazsanjiJobCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    ngOnDestroy() {
        clearInterval(this.intervals);
        clearInterval(this.dailyIntervals);
        clearInterval(this.speechIntervals);
    }

    //end

    /*private getNewEducationalModuleRequest() {
        if (this.isAuthenticated()) {
            let criteria = [
                {key: 'requestStatus.equals', value: RequestStatus.NEW}
            ];
            this.requestEducationalModuleMarineSuffixService.count({
                page: 0,
                size: 2000,
                criteria,
                sort: null
            }).subscribe(
                (res: HttpResponse<any>) => {
                    //localStorage.setItem('usersRequestCount', res.body);
                    this.EducationalModuleRequestCounter = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }*/

    private getNewUsersRequest() {
        if (this.isAdmin) {
            let criteria = [{ key: 'requestStatus.equals', value: RequestStatus.NEW }];
            this.usersRequestMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        //localStorage.setItem('usersRequestCount', res.body);
                        this.usersRequestCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    private getNewReferalUsersRequest() {
        if (this.currentPerson) {
            let criteria = [
                {
                    key: 'personId.equals',
                    value: this.currentPerson.id
                },
                {
                    key: 'referStatus.equals',
                    value: 'NEW'
                }
            ];
            this.usersRequestMarineSuffixService
                .count({
                    page: 0,
                    size: 2000,
                    criteria,
                    sort: null
                })
                .subscribe(
                    (res: HttpResponse<any>) => {
                        this.referUsersRequestCounter = res.body;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
}
