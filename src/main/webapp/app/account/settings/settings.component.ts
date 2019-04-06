import { Component, OnInit } from '@angular/core';
import {JhiDataUtils, JhiLanguageService} from 'ng-jhipster';

import { Principal, AccountService, JhiLanguageHelper } from 'app/core';
import {JhiAlertService} from "ng-jhipster/src/service/alert.service";
import {SettingsService} from "./settings.service";
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {HttpResponse} from "@angular/common/http";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";

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
    picUrl: string = "";
    oldPicUrl: string = "";
    isNewImage: boolean = false;
    person: IPersonMarineSuffix = new PersonMarineSuffix();
    constructor(
        private dataUtils: JhiDataUtils,
        private account: AccountService,
        private principal: Principal,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private jhiAlertService : JhiAlertService,
        private settingService: SettingsService,
        private personMarineSuffixService: PersonMarineSuffixService
    ) {}

    ngOnInit() {

        this.principal.identity().then(account => {

            this.settingsAccount = this.copyAccount(account);
            if(this.settingsAccount.imageUrl){
                this.oldPicUrl = this.settingsAccount.imageUrl;
            }
            else {
                this.oldPicUrl = "/content/images/home/man.png";
            }
            this.myAccount = this.settingsAccount;
            if(this.settingsAccount.personId)
            {
                this.personMarineSuffixService.find(this.settingsAccount.personId).subscribe(
                    (res: HttpResponse<PersonMarineSuffix>) => this.onPersonSuccess(res.body),
                    (res: HttpResponse<any>) => this.onPersonError(res.body)
                )
            }
            else {
                this.currentUserFullName = this.settingsAccount.login;
            }
        });
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
    }
    onPersonSuccess(body) {
        this.person = body;
        if (this.person) {
            this.currentUserFullName = this.person.name + " " + this.person.family;
            this.jobTitle = this.person.jobTitle
        }
        else {
            this.currentUserFullName = this.settingsAccount.login;
        }
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
}
