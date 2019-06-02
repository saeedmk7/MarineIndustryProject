import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {Principal, UserService} from "app/core";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {IFinalOrganizationNiazsanjiMarineSuffix} from "app/shared/model/final-organization-niazsanji-marine-suffix.model";
import {IRequestEducationalModuleMarineSuffix} from "../../shared/model/request-educational-module-marine-suffix.model";
import {RequestEducationalModuleMarineSuffixService} from "./request-educational-module-marine-suffix.service";

@Component({
    selector: 'mi-request-educational-module-marine-suffix-comment-dialog',
    templateUrl: './request-educational-module-marine-suffix-comment-dialog.component.html'
})
export class RequestEducationalModuleMarineSuffixCommentDialogComponent implements OnInit {
    requestEducationalModule: IRequestEducationalModuleMarineSuffix;
    commentType: string;
    organizationcharts: IOrganizationChartMarineSuffix[];
    targetPeople: IPersonMarineSuffix[];

    comment: string;

    currentAccount: any;
    currentPerson: IPersonMarineSuffix;
    isAdmin: boolean = false;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    message: string;
    commentRequired: boolean = false;
    constructor(
        protected requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private personService: PersonMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private convertObjectDatesService: ConvertObjectDatesService
    ) {

    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        /*this.requestEducationalModuleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'requestEducationalModuleListModification',
                content: 'Commented an requestEducationalModule'
            });
            this.activeModal.dismiss(true);
        });*/
    }
    isSaving: boolean = false;
    save(){
        debugger;
        this.isSaving = true;
        this.message = "";
        let currentUserFullName = this.currentPerson.fullName;
        this.requestEducationalModule.conversation += "\n ------------------------------------- \n";
        switch (this.commentType) {
            case 'COMMENT':
                if(!this.comment)
                {
                    this.message = "لطفا نظر خود را در کادر بالا وارد نمائید.";
                    this.isSaving = false;
                    return;
                }
                this.requestEducationalModule.conversation += " ثبت نظر توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. ";
                this.requestEducationalModule.conversation += "\n";
                this.requestEducationalModule.conversation += currentUserFullName + ": " + this.comment;
                break;
            case 'REJECT':
                if(!this.comment)
                {
                    this.message = "لطفا علت عدم تایید را در کادر بالا وارد نمائید.";
                    this.isSaving = false;
                    return;
                }
                this.requestEducationalModule.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                this.requestEducationalModule.conversation += "\n";
                this.requestEducationalModule.conversation += currentUserFullName + ": " + this.comment;

                this.requestEducationalModule.requestStatus = RequestStatus.IGNORE;
                this.requestEducationalModule.changeStatusUserLogin = this.currentAccount.login;
                break;
            case 'ACCEPT':
                this.requestEducationalModule.conversation += " تایید درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                if(this.comment) {
                    this.requestEducationalModule.conversation += "\n";
                    this.requestEducationalModule.conversation += currentUserFullName + ": " + this.comment;
                }
                let organization = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                if(organization && organization.parentId && organization.parentId > 0 && this.requestEducationalModule.status != 0) {
                    this.requestEducationalModule.status = organization.parentId;
                    this.requestEducationalModule.changeStatusUserLogin = this.currentAccount.login;
                }
                else {
                    this.requestEducationalModule.status = 0;
                    this.requestEducationalModule.changeStatusUserLogin = this.currentAccount.login;
                    if(this.isSuperUsers)
                    {
                        if(!this.requestEducationalModule.code) {
                            this.message = "شناسه پودمان اجباریست لطفا قبل از تایید نهایی آن را تکمیل نمائید.";
                            return;
                        }
                        this.requestEducationalModuleService.finalize(this.requestEducationalModule).subscribe(
                            (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
                            (res: HttpErrorResponse) => this.onSaveError(res)
                        );
                        return;
                    }
                }
                break;
        }
        this.requestEducationalModuleService.update(this.requestEducationalModule).subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError(res)
        );
    }
    protected onSaveSuccess() {
        this.isSaving = false;
        this.activeModal.dismiss(true);
    }

    protected onSaveError(res) {
        this.message = res.message;
        this.isSaving = false;
    }
    ngOnInit(): void {
        switch (this.commentType) {
            case 'ACCEPT':
                this.commentRequired = false;
                break;
            case 'REJECT':
                this.commentRequired = true;
                break;
            default:
                this.commentRequired = true;
                this.commentType = 'COMMENT';
                break;
        }

        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.setPermission();
        }
        else{
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.setPermission();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }

    }
    knowAboutStatusForContinue(){

        if(this.requestEducationalModule.requestStatus == RequestStatus.ACCEPT || this.requestEducationalModule.requestStatus == RequestStatus.IGNORE){
            this.clear();
        }
        if(this.requestEducationalModule.status != 0 && this.requestEducationalModule.status != this.currentPerson.organizationChartId){
            this.clear();
        }
    }
    setPermission(){
        this.principal.identity().then(account => {

            this.currentAccount = account;
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

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;
                this.knowAboutStatusForContinue();
                if(this.commentType == 'ACCEPT')
                {
                    this.findTargetPeople(resp.body);
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));

        });
    }
    findTargetPeople(person: IPersonMarineSuffix){

        let organization = this.organizationcharts.find(a => a.id == person.organizationChartId);
        if(organization.parentId > 0) {
            let neededOrgId: number = organization.parentId;

            let criteria = [{
                key: 'organizationChartId.equals',
                value: neededOrgId
            }];
            this.personService.query({
                page: 0,
                size: 20000,
                criteria,
                sort: ["id", "asc"]
            }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    let orgPeople = resp.body;
                    if (orgPeople.length > 0) {
                        this.targetPeople = orgPeople;
                    }
                    else {
                        this.targetPeople = [];
                        this.targetPeople = [new PersonMarineSuffix(0, 'خطا', 'خطا', 'خطا: نفر دریافت کننده ای در چارت سازمانی برای شما تعریف نشده است. لطفا با مدیریت سامانه تماس بگیرید. ')]
                    }
                },
                (error) => this.onError("فردی یافت نشد."));
        }
        else{
            this.targetPeople = [];
            this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'سابقه ی آموزشی مورد نظر پس از ثبت تایید برای کارشناس ارشد آموزش سازمان برای بازبینی ارسال می شود.'));
        }
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

@Component({
    selector: 'mi-request-educational-module-marine-suffix-comment-popup',
    template: ''
})
export class RequestEducationalModuleMarineSuffixCommentPopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestEducationalModule }) => {
            this.activatedRoute.params.subscribe((params) => {

                let commentType = params['CommentType'] ? params['CommentType'] : "";
                switch (commentType) {
                    case 'ACCEPT':
                        break;
                    case 'REJECT':
                        break;
                    default:
                        commentType = 'COMMENT';
                        break;
                }
                setTimeout(() => {

                    this.ngbModalRef = this.modalService.open(RequestEducationalModuleMarineSuffixCommentDialogComponent as Component, {
                        size: 'lg',
                        backdrop: false
                    });
                    this.ngbModalRef.componentInstance.requestEducationalModule = requestEducationalModule;
                    this.ngbModalRef.componentInstance.commentType = commentType;
                        this.ngbModalRef.result.then(
                            result => {
                                this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                                this.ngbModalRef = null;
                            },
                            reason => {
                                this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                                this.ngbModalRef = null;
                            }
                        );
                }, 0);
            });

        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
