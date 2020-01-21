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
import {IPrioritizeRequestNiazsanjiMarineSuffix} from "app/shared/model/prioritize-request-niazsanji-marine-suffix.model";
import {PrioritizeRequestNiazsanjiMarineSuffixService} from "app/entities/prioritize-request-niazsanji-marine-suffix/prioritize-request-niazsanji-marine-suffix.service";

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix-comment-dialog',
    templateUrl: './prioritize-request-niazsanji-marine-suffix-comment-dialog.component.html'
})
export class PrioritizeRequestNiazsanjiMarineSuffixCommentDialogComponent implements OnInit {
    prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix;
    commentType: string;
    priorityValue: number;
    organizationcharts: IOrganizationChartMarineSuffix[];
    targetPeople: IPersonMarineSuffix[];

    comment: string;

    currentAccount: any;
    currentPerson: IPersonMarineSuffix;
    isAdmin: boolean = false;
    message: string;
    commentRequired: boolean = false;
    constructor(
        protected prioritizeRequestNiazsanjiService: PrioritizeRequestNiazsanjiMarineSuffixService,
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
        this.prioritizeRequestNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'prioritizeRequestNiazsanjiListModification',
                content: 'Commented an prioritizeRequestNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
    isSaving: boolean = false;
    save(){
        this.isSaving = true;
        this.message = "";

        if(this.priorityValue)
            this.prioritizeRequestNiazsanji.priority = this.priorityValue;

        let currentUserFullName = this.currentPerson.fullName;
        this.prioritizeRequestNiazsanji.conversation += "\n ------------------------------------- \n";
        switch (this.commentType) {
            case 'COMMENT':
                if(!this.comment)
                {
                    this.message = "لطفا نظر خود را در کادر بالا وارد نمائید.";
                    this.isSaving = false;
                    return;
                }
                this.prioritizeRequestNiazsanji.conversation += " ثبت نظر توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. ";
                this.prioritizeRequestNiazsanji.conversation += "\n";
                this.prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                break;
            case 'REJECT':
                if(!this.comment)
                {
                    this.message = "لطفا علت عدم تایید را در کادر بالا وارد نمائید.";
                    this.isSaving = false;
                    return;
                }
                this.prioritizeRequestNiazsanji.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                this.prioritizeRequestNiazsanji.conversation += "\n";
                this.prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;

                this.prioritizeRequestNiazsanji.requestStatus = RequestStatus.IGNORE;
                this.prioritizeRequestNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                break;
            case 'ACCEPT':
                if(!this.priorityValue)
                {
                    this.message = "لطفا اولویت این نیازسنجی را مشخص نمائید، سپس اقدام به تایید نمائید.";
                    this.isSaving = false;
                    return;
                }
                this.prioritizeRequestNiazsanji.conversation += " تایید درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                if(this.comment) {
                    this.prioritizeRequestNiazsanji.conversation += "\n";
                    this.prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                }
                let organization = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                if(organization.parentId > 0) {
                    this.prioritizeRequestNiazsanji.status = organization.parentId;
                    this.prioritizeRequestNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                }
                else {
                    this.prioritizeRequestNiazsanji.status = 0;
                    this.prioritizeRequestNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                    this.prioritizeRequestNiazsanjiService.finalize(this.prioritizeRequestNiazsanji).subscribe(
                        (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                        (res: HttpErrorResponse) => this.onSaveError(res)
                    );
                    return;
                }
                break;
        }
        this.prioritizeRequestNiazsanjiService.update(this.prioritizeRequestNiazsanji).subscribe(
            (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
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
    knowAboutStatusForContinue(){

        if(this.prioritizeRequestNiazsanji.requestStatus == RequestStatus.ACCEPT || this.prioritizeRequestNiazsanji.requestStatus == RequestStatus.IGNORE ||
         this.prioritizeRequestNiazsanji.requestStatus == RequestStatus.READ){
            this.clear();
        }
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
    setPermission(){
        this.principal.identity().then(account => {

            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
            this.isAdmin = true;

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
            this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'درخواست شما پس از ثبت تایید برای کارشناس ارشد آموزش سازمان برای بازبینی ارسال می شود.'));
            //this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'درخواست شما پس از ثبت تایید در لیست اولویت بندی قرار میگیرد. برای ارسال نیازسنجی به کارشناس ارشد آموزش جهت بازبینی از لیست اولویت بندی استفاده نمائید.'));
        }
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix-comment-popup',
    template: ''
})
export class PrioritizeRequestNiazsanjiMarineSuffixCommentPopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ prioritizeRequestNiazsanji }) => {
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
                let priority = params['priorityValue'] ? params['priorityValue'] : "";

                setTimeout(() => {

                    this.ngbModalRef = this.modalService.open(PrioritizeRequestNiazsanjiMarineSuffixCommentDialogComponent as Component, {
                        size: 'lg',
                        backdrop: false
                    });
                    this.ngbModalRef.componentInstance.prioritizeRequestNiazsanji = prioritizeRequestNiazsanji;
                    this.ngbModalRef.componentInstance.commentType = commentType;
                    this.ngbModalRef.componentInstance.priorityValue = priority;
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
