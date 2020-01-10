import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {Principal, UserService} from "app/core";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {JobNiazsanjiMarineSuffixService} from "app/entities/job-niazsanji-marine-suffix/job-niazsanji-marine-suffix.service";
import {PreJobNiazsanjiMarineSuffixService} from "app/entities/pre-job-niazsanji-marine-suffix";
import {IJobNiazsanjiMarineSuffix} from "app/shared/model/job-niazsanji-marine-suffix.model";
import {IPreJobNiazsanjiMarineSuffix} from "app/shared/model/pre-job-niazsanji-marine-suffix.model";

@Component({
    selector: 'job-niazsanji-marine-suffix-comment-dialog',
    templateUrl: './job-niazsanji-marine-suffix-comment-dialog.component.html'
})
export class JobNiazsanjiMarineSuffixCommentDialogComponent implements OnInit {
    jobNiazsanji: IJobNiazsanjiMarineSuffix;
    commentType: string;
    organizationcharts: IOrganizationChartMarineSuffix[];

    comment: string;

    currentAccount: any;
    currentPerson: IPersonMarineSuffix;
    isAdmin: boolean = false;
    isModirKolAmozesh: boolean;
    isKarshenasArshadAmozesh: boolean;

    message: string;
    commentRequired: boolean = false;
    constructor(
        protected jobNiazsanjiService: JobNiazsanjiMarineSuffixService,
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
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

    /*confirmDelete(id: number) {
        this.jobNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'preJobNiazsanjiListModification',
                content: 'Commented an preJobNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }*/
    isSaving: boolean = false;
    save(){


        this.isSaving = true;
        this.preJobNiazsanjiService.find(this.jobNiazsanji.preJobNiazsanjiId).subscribe(
            (resp: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => {
                let preJobNiazsanji = resp.body;
                this.message = "";
                let currentUserFullName = this.currentPerson.fullName;
                preJobNiazsanji.conversation += "\n ------------------------------------- \n";
                switch (this.commentType) {
                    case 'COMMENT':
                        if (!this.comment) {
                            this.message = "لطفا نظر خود را در کادر بالا وارد نمائید.";
                            this.isSaving = false;
                            return;
                        }
                        preJobNiazsanji.conversation += " ثبت نظر توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. ";
                        preJobNiazsanji.conversation += "\n";
                        preJobNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                        this.preJobNiazsanjiService.update(preJobNiazsanji).subscribe(
                            (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                            (res: HttpErrorResponse) => this.onSaveError(res)
                        );
                        return;
                    case 'REJECT':

                        if (!this.comment) {
                            this.message = "لطفا علت عدم تایید را در کادر بالا وارد نمائید.";
                            this.isSaving = false;
                            return;
                        }
                        preJobNiazsanji.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                        preJobNiazsanji.conversation += "\n";
                        preJobNiazsanji.conversation += currentUserFullName + ": " + this.comment;

                        preJobNiazsanji.requestStatus = RequestStatus.IGNORE;
                        preJobNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                        if(this.isKarshenasArshadAmozesh)
                            this.jobNiazsanji.status = 1;
                        if(this.isModirKolAmozesh)
                            this.jobNiazsanji.status = 11;
                        if(this.jobNiazsanji.status == 20)
                            this.jobNiazsanji.status = 21;
                        this.jobNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                        this.preJobNiazsanjiService.update(preJobNiazsanji).subscribe(
                            (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                            (res: HttpErrorResponse) => this.onSaveError(res)
                        );
                        break;
                    case 'ACCEPT':


                        if (this.isKarshenasArshadAmozesh && this.jobNiazsanji.status == 0) {
                            preJobNiazsanji.conversation += " تایید درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                            if (this.comment) {
                                preJobNiazsanji.conversation += "\n";
                                preJobNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                            }
                            this.jobNiazsanji.status = 10;
                            this.jobNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                            this.preJobNiazsanjiService.update(preJobNiazsanji).subscribe(
                                (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                (res: HttpErrorResponse) => this.onSaveError(res)
                            );
                        }
                        else if(this.isModirKolAmozesh && this.jobNiazsanji.status == 10) {
                            preJobNiazsanji.conversation += " تایید درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                            if (this.comment) {
                                preJobNiazsanji.conversation += "\n";
                                preJobNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                            }
                            this.jobNiazsanji.status = 20;
                            this.jobNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                            this.preJobNiazsanjiService.update(preJobNiazsanji).subscribe(
                                (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                (res: HttpErrorResponse) => this.onSaveError(res)
                            );
                        }
                        else {
                            if(this.jobNiazsanji.niazsanjiYear) {
                                preJobNiazsanji.conversation += " تایید نهایی و تصویب شوراء تربیت و آموزش سازمان توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                                if (this.comment) {
                                    preJobNiazsanji.conversation += "\n";
                                    preJobNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                                }
                                preJobNiazsanji.requestStatus = RequestStatus.ACCEPT;
                                this.preJobNiazsanjiService.update(preJobNiazsanji).subscribe(
                                    (res: HttpResponse<IPreJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                    (res: HttpErrorResponse) => this.onSaveError(res)
                                );
                                this.jobNiazsanjiService.finalize(this.jobNiazsanji).subscribe(
                                    (res: HttpResponse<IJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                    (res: HttpErrorResponse) => this.onSaveError(res)
                                );
                            }
                            else{
                                alert("لطفا قبل از اجرا کردن درخواست سال نیازسنجی آن را مشخص نمائید.");
                            }
                            return;
                        }
                        break;
                }
                this.jobNiazsanjiService.update(this.jobNiazsanji).subscribe(
                    (res: HttpResponse<IJobNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                    (res: HttpErrorResponse) => this.onSaveError(res)
                );
            },
            (res: HttpErrorResponse) => this.onSaveError(res));
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
        debugger;
        if(this.jobNiazsanji.status == 21 || this.jobNiazsanji.status == 11 || this.jobNiazsanji.status == 1 || this.jobNiazsanji.status == 30){
            this.clear();
        }
    }
    ngOnInit(): void {
        debugger;
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
        this.knowAboutStatusForContinue();
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

            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }

            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;

            }, (res: HttpErrorResponse) => this.onError(res.message));

        });
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

@Component({
    selector: 'mi-job-niazsanji-marine-suffix-comment-popup',
    template: ''
})
export class JobNiazsanjiMarineSuffixCommentPopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        debugger;
        this.activatedRoute.data.subscribe(({ jobNiazsanji }) => {
            debugger;
            this.activatedRoute.params.subscribe((params) => {
                debugger;
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

                    this.ngbModalRef = this.modalService.open(JobNiazsanjiMarineSuffixCommentDialogComponent as Component, {
                        size: 'lg',
                        backdrop: false
                    });
                    this.ngbModalRef.componentInstance.jobNiazsanji = jobNiazsanji;
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
