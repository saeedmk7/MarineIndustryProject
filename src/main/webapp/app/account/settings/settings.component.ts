import { Component, OnInit } from '@angular/core';
import { JhiDataUtils, JhiLanguageService } from 'ng-jhipster';

import { Principal, AccountService, JhiLanguageHelper } from 'app/core';
import { JhiAlertService } from 'ng-jhipster/src/service/alert.service';
import { SettingsService } from './settings.service';
import { IPersonMarineSuffix, PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import * as moment from 'moment';

@Component({
    selector: 'jhi-settings',
    templateUrl: './settings.component.html',
    styleUrls: ['settings.scss']
})
export class SettingsComponent implements OnInit {
    error: string;
    success: string;
    settingsAccount: any;
    myAccount: any;

    currentUserFullName: string;
    jobTitle: string;
    languages: any[];
    document: any;
    file: File;
    selectedFile: File;
    picUrl: string = '';
    oldPicUrl: string = '';
    isNewImage: boolean = false;
    organizationTitle: string = '';
    mobile: string;
    person: IPersonMarineSuffix = new PersonMarineSuffix();
    constructor(
        private dataUtils: JhiDataUtils,
        private account: AccountService,
        private principal: Principal,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private jhiAlertService: JhiAlertService,
        private settingService: SettingsService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private personMarineSuffixService: PersonMarineSuffixService
    ) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.settingsAccount = this.copyAccount(account);
            if (this.settingsAccount.imageUrl) {
                this.oldPicUrl = this.settingsAccount.imageUrl;
            } else {
                this.oldPicUrl = '/content/images/home/man.png';
            }
            this.myAccount = this.settingsAccount;
            if (this.settingsAccount.personId) {
                this.personMarineSuffixService
                    .find(this.settingsAccount.personId)
                    .subscribe(
                        (res: HttpResponse<PersonMarineSuffix>) => this.onPersonSuccess(res.body),
                        (res: HttpResponse<any>) => this.onPersonError(res.body)
                    );
            } else {
                this.currentUserFullName = this.settingsAccount.login;
            }
        });
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
    }
    saveMobile() {
        this.personMarineSuffixService.find(this.person.id).subscribe((resp: HttpResponse<PersonMarineSuffix>) => {
            this.person = resp.body;
            this.person.mobile = this.mobile;
            this.personMarineSuffixService.update(this.person).subscribe((res: HttpResponse<PersonMarineSuffix>) => {
                window.location.reload();
            });
        });
    }
    onPersonSuccess(body) {
        this.person = body;
        if (this.person) {
            this.mobile = this.person.mobile;
            this.currentUserFullName = this.person.name + ' ' + this.person.family;
            this.jobTitle = this.person.jobTitle;
            this.prepareOrgChart(this.person.organizationChartId);
        } else {
            this.currentUserFullName = this.settingsAccount.login;
        }
    }
    prepareOrgChart(orgId: number) {
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationTitle = this.organizationChartService.organizationchartsAll.find(a => a.id == orgId).fullTitle;
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationTitle = res.body.find(a => a.id == orgId).fullTitle;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
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
                if (file.size < 2000000) {
                    this.dataUtils.toBase64(file, function(base64Data) {
                        picUrl = 'data:' + file.type + ';base64, ' + base64Data;
                    });
                    setTimeout(() => {
                        this.picUrl = picUrl;
                        this.isNewImage = true;
                    }, 1000);
                } else {
                    this.error = 'عکس انتخابی شما باید کوچکتر از 2 مگابایت باشد';
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
    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
        this.file = event;
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

    get yearsOfService(): string {
        if (this.person && this.person.employmentDate) {
            const result = this.getAge(new Date(this.person.employmentDate.toDate()), new Date());
            return result;
        }
        return 'تاریخ استخدام شما در سیستم ثبت نشده است.';
    }
    getAge(date_1, date_2) {
        let date2_UTC = new Date(Date.UTC(date_2.getUTCFullYear(), date_2.getUTCMonth(), date_2.getUTCDate()));
        let date1_UTC = new Date(Date.UTC(date_1.getUTCFullYear(), date_1.getUTCMonth(), date_1.getUTCDate()));

        let yAppendix, mAppendix, dAppendix;

        let days = date2_UTC.getDate() - date1_UTC.getDate();
        if (days < 0) {
            date2_UTC.setMonth(date2_UTC.getMonth() - 1);
            days += this.daysInMonth(date2_UTC);
        }

        let months = date2_UTC.getMonth() - date1_UTC.getMonth();
        if (months < 0) {
            date2_UTC.setFullYear(date2_UTC.getFullYear() - 1);
            months += 12;
        }

        let years = date2_UTC.getFullYear() - date1_UTC.getFullYear();

        if (years > 1) yAppendix = ' سال';
        else yAppendix = ' سال';
        if (months > 1) mAppendix = ' ماه';
        else mAppendix = ' ماه';
        if (days > 1) dAppendix = ' روز';
        else dAppendix = ' روز';

        return years + yAppendix + ' و ' + months + mAppendix + ' و ' + days + dAppendix + '';
    }

    daysInMonth(date2_UTC) {
        const monthStart = new Date(date2_UTC.getFullYear(), date2_UTC.getMonth(), 1);
        const monthEnd = new Date(date2_UTC.getFullYear(), date2_UTC.getMonth() + 1, 1);
        const monthLength = (monthEnd.getDate() - monthStart.getDate()) / (1000 * 60 * 60 * 24);
        return monthLength;
    }
}
