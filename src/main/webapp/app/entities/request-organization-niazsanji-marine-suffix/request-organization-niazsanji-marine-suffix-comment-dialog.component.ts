import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, ActivatedRouteSnapshot, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {IRequestNiazsanjiFardiMarineSuffix} from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IPersonMarineSuffix, PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {IUser, Principal, UserService} from "app/core";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {IFinalOrganizationNiazsanjiMarineSuffix} from "app/shared/model/final-organization-niazsanji-marine-suffix.model";
import {RequestOrganizationNiazsanjiMarineSuffixService} from "./request-organization-niazsanji-marine-suffix.service";
import {IRequestOrganizationNiazsanjiMarineSuffix} from "../../shared/model/request-organization-niazsanji-marine-suffix.model";

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix-comment-dialog',
    templateUrl: './request-organization-niazsanji-marine-suffix-comment-dialog.component.html'
})
export class RequestOrganizationNiazsanjiMarineSuffixCommentDialogComponent implements OnInit {
    requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix;
    commentType: string;
    organizationcharts: IOrganizationChartMarineSuffix[];
    targetPeople: IPersonMarineSuffix[];

    comment: string;

    currentAccount: any;
    currentPerson: IPersonMarineSuffix;
    isAdmin: boolean = false;
    message: string;
    commentRequired: boolean = false;
    constructor(
        protected requestOrganizationNiazsanjiService: RequestOrganizationNiazsanjiMarineSuffixService,
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
    isSaving: boolean = false;
    save(){
        this.isSaving = true;
        this.message = "";
        let currentUserFullName = this.currentPerson.fullName;
        this.requestOrganizationNiazsanji.conversation += "\n ------------------------------------- \n";
        switch (this.commentType) {
            case 'COMMENT':
                if(!this.comment)
                {
                    this.message = "لطفا نظر خود را در کادر بالا وارد نمائید.";
                    this.isSaving = false;
                    return;
                }
                this.requestOrganizationNiazsanji.conversation += " ثبت نظر توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. ";
                this.requestOrganizationNiazsanji.conversation += "\n";
                this.requestOrganizationNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                break;
            case 'REJECT':
                if(!this.comment)
                {
                    this.message = "لطفا علت عدم تایید را در کادر بالا وارد نمائید.";
                    this.isSaving = false;
                    return;
                }
                this.requestOrganizationNiazsanji.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                this.requestOrganizationNiazsanji.conversation += "\n";
                this.requestOrganizationNiazsanji.conversation += currentUserFullName + ": " + this.comment;

                this.requestOrganizationNiazsanji.requestStatus = RequestStatus.IGNORE;
                this.requestOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                break;
            case 'ACCEPT':
                this.requestOrganizationNiazsanji.conversation += " تایید درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                if(this.comment) {
                    this.requestOrganizationNiazsanji.conversation += "\n";
                    this.requestOrganizationNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                }
                let organization = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                if(organization.parentId > 0) {
                    this.requestOrganizationNiazsanji.status = organization.parentId;
                    this.requestOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                }
                else {
                    this.requestOrganizationNiazsanji.status = 0;
                    this.requestOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                    this.requestOrganizationNiazsanjiService.finalize(this.requestOrganizationNiazsanji).subscribe(
                        (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                        (res: HttpErrorResponse) => this.onSaveError(res)
                    );
                    return;
                }
                break;
        }
        this.requestOrganizationNiazsanjiService.update(this.requestOrganizationNiazsanji).subscribe(
            (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
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
        
        if(this.requestOrganizationNiazsanji.requestStatus == RequestStatus.ACCEPT || this.requestOrganizationNiazsanji.requestStatus == RequestStatus.IGNORE){
            this.clear();
        }
        if(this.requestOrganizationNiazsanji.status != this.currentPerson.organizationChartId){
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
        }
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix-comment-popup',
    template: ''
})
export class RequestOrganizationNiazsanjiMarineSuffixCommentPopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestOrganizationNiazsanji }) => {
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

                    this.ngbModalRef = this.modalService.open(RequestOrganizationNiazsanjiMarineSuffixCommentDialogComponent as Component, {
                        size: 'lg',
                        backdrop: false
                    });
                    this.ngbModalRef.componentInstance.requestOrganizationNiazsanji = requestOrganizationNiazsanji;
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
