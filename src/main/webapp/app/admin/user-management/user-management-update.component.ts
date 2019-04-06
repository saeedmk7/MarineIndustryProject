import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { JhiLanguageHelper, User, UserService } from 'app/core';

import {HttpResponse} from '@angular/common/http';

import {JhiAlert, JhiAlertService} from 'ng-jhipster';
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix/person-marine-suffix.service";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {HttpErrorResponse} from "@angular/common/http/src/response";
import {AuthorityService} from "app/admin/authority";
import {IAuthority} from "app/shared/model/authority.model";
import {NgSelectConfig} from "@ng-select/ng-select";
import {EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE, PERSON_ALREADY_USED_TYPE} from "app/shared";

@Component({
    selector: 'jhi-user-mgmt-update',
    templateUrl: './user-management-update.component.html'
})
export class UserMgmtUpdateComponent implements OnInit {
    user: User;
    languages: any[];
    authorities: IAuthority[];
    isSaving: boolean;
    people: IPersonMarineSuffix[];
    error:boolean = false;


    constructor(
        private jhiAlertService: JhiAlertService,
        private languageHelper: JhiLanguageHelper,
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router,
        private personService: PersonMarineSuffixService,
        private authorityService: AuthorityService,
        private config: NgSelectConfig
    ) {
        config.notFoundText = "پیدا نشد";
    }
    change(i){
        //this.router.navigateByUrl(i);
        this.router.navigateByUrl(i);
    }
    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ user }) => {
            this.user = user.body ? user.body : user;
        });
        /*this.authorityService.authorities().subscribe(authorities => {
            this.authorities = authorities;
        });*/
        this.authorityService.authorities().subscribe(
            (res: HttpResponse<IAuthority[]>) => {
                this.authorities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
        if(this.personService.people)
        {
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
    }
    errorMessage:string;
    private onError(errorMessage: string) {
        this.errorMessage = "";
        this.error = true;
        this.errorMessage = errorMessage;
        //this.jhiAlertService.error(errorMessage,params);
        //this.jhiAlertService.addAlert({type: 'success', msg: errorMessage, timeout: 1000}, []);
    }
    previousState() {
        window.history.back();
    }

    save() {

        this.isSaving = true;

        if(this.user.personId === null)
        {
            this.onError("لطفا پرسنل مورد نظر را مشخص نمائید.");
            this.isSaving = false;
            return;
        }
        this.user.email = this.user.login + "@amoozeshMarineIndustry.com";
        this.user.langKey = 'fa';
        if (this.user.id !== null) {

            this.userService.update(this.user).subscribe(response => this.onSaveSuccess(response), (response) => this.onSaveError(response));
        } else {
            if(!this.user.password)
            {
                this.onError("لطفا رمز عبور را مشخص نمائید.");
                this.isSaving = false;
                return;
            }
            this.userService.create(this.user).subscribe(response => this.onSaveSuccess(response), (response) => this.onSaveError(response));
        }
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError(response: HttpErrorResponse) {

        this.isSaving = false;
        if (response.status === 400 && response.error.type === LOGIN_ALREADY_USED_TYPE) {
            this.error = true;
            this.errorMessage = 'نام کاربری وارد شده تکراریست. (این نام کاربری برای اکانت دیگری استفاده شده است.)';
        } else if(response.status === 400 && response.error.type === PERSON_ALREADY_USED_TYPE){
            this.error = true;
            this.errorMessage = 'پرسنل انتخاب شده تکراریست. (این فرد اکانت دیگری دارد.)';
        }
        else {
            this.error = true;
            this.errorMessage = 'خطایی در ثبت اطلاعات رخ داده است. لطفا بعدا دوباره امتحان نمائید.';
        }
    }
    trackPersonById(index: number, item: IPersonMarineSuffix) {
        return item.id;
    }
}
