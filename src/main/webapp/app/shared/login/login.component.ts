import { Component, AfterViewInit, Renderer, ElementRef } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import {JhiEventManager, JhiLanguageService} from 'ng-jhipster';

import { LoginService } from 'app/core/login/login.service';
import { StateStorageService } from 'app/core/auth/state-storage.service';
import * as $ from 'jquery';
import {Register} from "app/account";
import {HttpErrorResponse} from "@angular/common/http";
import {EMAIL_ALREADY_USED_TYPE, LOGIN_ALREADY_USED_TYPE} from "app/shared";

@Component({
    selector: 'mi-login-modal',
    templateUrl: './login.component.html',
    styleUrls:['../../../content/scss/login.scss']
})
export class JhiLoginModalComponent implements AfterViewInit {
    authenticationError: boolean;
    password: string;
    rememberMe: boolean;
    username: string;
    credentials: any;

    registerUserName: string;
    registerPassword: string;
    confirmPassword: string;

    constructor(
        private eventManager: JhiEventManager,
        private loginService: LoginService,
        private stateStorageService: StateStorageService,
        private elementRef: ElementRef,
        private renderer: Renderer,
        private router: Router,
        public activeModal: NgbActiveModal,
        public languageService: JhiLanguageService,
        public registerService: Register
    ) {

        this.credentials = {};
    }

    ngAfterViewInit() {
        setTimeout(() => this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#username'), 'focus', []), 0);
        $(document).ready(function() {
            var panelOne = $('.form-panel.two').height(),
                panelTwo = $('.form-panel.two')[0].scrollHeight;

            $('.form-panel.two').not('.form-panel.two.active').on('click', function(e) {
                e.preventDefault();

                $('.form-toggle').addClass('visible');
                $('.form-panel.one').addClass('hidden');
                $('.form-panel.two').addClass('active');
                $('.form').animate({
                    'height': panelTwo
                }, 200);
            });

            $('.form-toggle').on('click', function(e) {
                e.preventDefault();
                $(this).removeClass('visible');
                $('.form-panel.one').removeClass('hidden');
                $('.form-panel.two').removeClass('active');
                $('.form').animate({
                    'height': panelOne
                }, 200);
            });
        });
    }

    cancel() {
        this.credentials = {
            username: null,
            password: null,
            rememberMe: true
        };
        this.authenticationError = false;
        this.activeModal.dismiss('cancel');
    }

    login() {
        this.loginService
            .login({
                username: this.username,
                password: this.password,
                rememberMe: this.rememberMe
            })
            .then(() => {
                this.authenticationError = false;
                this.activeModal.dismiss('login success');
                if (this.router.url === '/register' || /^\/activate\//.test(this.router.url) || /^\/reset\//.test(this.router.url)) {
                    this.router.navigate(['']);
                }

                this.eventManager.broadcast({
                    name: 'authenticationSuccess',
                    content: 'Sending Authentication Success'
                });

                // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                // since login is succesful, go to stored previousState and clear previousState
                const redirect = this.stateStorageService.getUrl();
                if (redirect) {
                    this.stateStorageService.storeUrl(null);
                    this.router.navigate([redirect]);
                }
            })
            .catch(() => {
                this.authenticationError = true;
            });
    }
    errorMessage:string;
    hasError:boolean = false;
    success:boolean= false;
    register() {

        this.hasError = false;
        this.errorMessage = "";
        if(this.registerUserName.length < 5){
            this.errorMessage = 'نام کاربری باید بیش از 5 کاراکتر داشته باشد.';
            this.hasError = true;
        }
        if((/[^A-Za-z0-9]+/.test(this.registerUserName))){
            this.errorMessage += 'نام کاربری فقط حروف انگلیسی و عدد می تواند باشد.';
            this.hasError = true;
        }
        if (this.registerPassword !== this.confirmPassword) {
            this.errorMessage += 'تایید پسورد با پسورد متفاوت است';
            this.hasError = true;
        }
        if(!this.hasError) {
            this.languageService.getCurrent().then(key => {

                let registerAccount: any = {};
                registerAccount.langKey = key;
                registerAccount.login = this.registerUserName;
                registerAccount.email = this.registerUserName + "@amoozesh.com";
                registerAccount.password = this.registerPassword;
                this.registerService.save(registerAccount).subscribe(
                    () => {

                        this.success = true;
                        this.hasError = false;
                        /*$('.form-toggle').trigger("click");*/
                    },
                    response => this.processError(response)
                );
            });
        }
    }
    private processError(response: HttpErrorResponse) {
        this.success = false;
        this.hasError = true;
        if (response.status === 400 && response.error.type === LOGIN_ALREADY_USED_TYPE) {
            //this.errorUserExists = 'ERROR';
            this.errorMessage = "کاربری با این نام کاربری قبلا ثبت شده است در صورت فراموشی رمز عبور با ادمین سیستم تماس حاصل فرمائید."
        } else {
            this.errorMessage = 'خطایی رخ داده است.';
        }
        setTimeout(() => {
            this.hasError = false;
        },20000)
    }
    requestResetPassword() {
        this.activeModal.dismiss('to state requestReset');
        this.router.navigate(['/reset', 'request']);
    }
}
