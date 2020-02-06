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
import {
    INiazsanjiIntegrationMarineSuffix,
    NiazsanjiIntegrationMarineSuffix
} from "app/shared/model/niazsanji-integration-marine-suffix.model";
import {NiazsanjiIntegrationMarineSuffixService} from "app/entities/niazsanji-integration-marine-suffix/niazsanji-integration-marine-suffix.service";
import {PrioritizeRequestNiazsanjiMarineSuffixService} from "app/entities/prioritize-request-niazsanji-marine-suffix";
import {IPrioritizeRequestNiazsanjiMarineSuffix} from "app/shared/model/prioritize-request-niazsanji-marine-suffix.model";

@Component({
    selector: 'niazsanji-integration-marine-suffix-comment-dialog',
    templateUrl: './niazsanji-integration-marine-suffix-comment-dialog.component.html'
})
export class NiazsanjiIntegrationMarineSuffixCommentDialogComponent implements OnInit {
    niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix;
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
        protected niazsanjiIntegrationService: NiazsanjiIntegrationMarineSuffixService,
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

    /*confirmDelete(id: number) {
        this.niazsanjiIntegrationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'prioritizeRequestNiazsanjiListModification',
                content: 'Commented an prioritizeRequestNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }*/
    isSaving: boolean = false;
    save(){


        this.isSaving = true;
        this.prioritizeRequestNiazsanjiService.find(this.niazsanjiIntegration.prioritizeRequestNiazsanjiId).subscribe(
            (resp: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => {
                let prioritizeRequestNiazsanji = resp.body;
                this.message = "";
                let currentUserFullName = this.currentPerson.fullName;
                prioritizeRequestNiazsanji.conversation += "\n ------------------------------------- \n";
                switch (this.commentType) {
                    case 'COMMENT':
                        if (!this.comment) {
                            this.message = "لطفا نظر خود را در کادر بالا وارد نمائید.";
                            this.isSaving = false;
                            return;
                        }
                        prioritizeRequestNiazsanji.conversation += " ثبت نظر توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " ثبت شد. ";
                        prioritizeRequestNiazsanji.conversation += "\n";
                        prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                        this.prioritizeRequestNiazsanjiService.update(prioritizeRequestNiazsanji).subscribe(
                            (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                            (res: HttpErrorResponse) => this.onSaveError(res)
                        );
                        return;
                    case 'REJECT':

                        if (!this.comment) {
                            this.message = "لطفا علت عدم تایید را در کادر بالا وارد نمائید.";
                            this.isSaving = false;
                            return;
                        }
                        prioritizeRequestNiazsanji.conversation += " رد درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                        prioritizeRequestNiazsanji.conversation += "\n";
                        prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;

                        prioritizeRequestNiazsanji.requestStatus = RequestStatus.IGNORE;
                        prioritizeRequestNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                        if(this.isKarshenasArshadAmozesh)
                            this.niazsanjiIntegration.status = 1;
                        if(this.isModirKolAmozesh)
                            this.niazsanjiIntegration.status = 11;
                        if(this.niazsanjiIntegration.status == 20)
                            this.niazsanjiIntegration.status = 21;
                        this.niazsanjiIntegration.changeStatusUserLogin = this.currentAccount.login;
                        this.prioritizeRequestNiazsanjiService.update(prioritizeRequestNiazsanji).subscribe(
                            (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                            (res: HttpErrorResponse) => this.onSaveError(res)
                        );
                        break;
                    case 'ACCEPT':


                        if (this.isKarshenasArshadAmozesh && this.niazsanjiIntegration.status == 0) {
                            prioritizeRequestNiazsanji.conversation += " تایید درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                            if (this.comment) {
                                prioritizeRequestNiazsanji.conversation += "\n";
                                prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                            }
                            this.niazsanjiIntegration.status = 10;
                            this.niazsanjiIntegration.changeStatusUserLogin = this.currentAccount.login;
                            this.prioritizeRequestNiazsanjiService.update(prioritizeRequestNiazsanji).subscribe(
                                (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                (res: HttpErrorResponse) => this.onSaveError(res)
                            );
                        }
                        else if(this.isModirKolAmozesh && this.niazsanjiIntegration.status == 10) {
                            prioritizeRequestNiazsanji.conversation += " تایید درخواست توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                            if (this.comment) {
                                prioritizeRequestNiazsanji.conversation += "\n";
                                prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                            }
                            this.niazsanjiIntegration.status = 20;
                            this.niazsanjiIntegration.changeStatusUserLogin = this.currentAccount.login;
                            this.prioritizeRequestNiazsanjiService.update(prioritizeRequestNiazsanji).subscribe(
                                (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                (res: HttpErrorResponse) => this.onSaveError(res)
                            );
                        }
                        else {
                            if(this.niazsanjiIntegration.niazsanjiYear) {
                                prioritizeRequestNiazsanji.conversation += " تایید نهایی و تصویب شوراء تربیت و آموزش سازمان توسط " + currentUserFullName + " در تاریخ: " + this.convertObjectDatesService.miladi2Shamsi(new Date()) + " انجام شد. ";
                                if (this.comment) {
                                    prioritizeRequestNiazsanji.conversation += "\n";
                                    prioritizeRequestNiazsanji.conversation += currentUserFullName + ": " + this.comment;
                                }
                                prioritizeRequestNiazsanji.requestStatus = RequestStatus.ACCEPT;
                                this.prioritizeRequestNiazsanjiService.update(prioritizeRequestNiazsanji).subscribe(
                                    (res: HttpResponse<IPrioritizeRequestNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                    (res: HttpErrorResponse) => this.onSaveError(res)
                                );
                                this.niazsanjiIntegrationService.finalize(this.niazsanjiIntegration).subscribe(
                                    (res: HttpResponse<INiazsanjiIntegrationMarineSuffix>) => this.onSaveSuccess(),
                                    (res: HttpErrorResponse) => this.onSaveError(res)
                                );
                            }
                            else{
                                alert("لطفا قبل از تصویب کردن درخواست، سال نیازسنجی آن را مشخص نمائید.");
                            }
                            return;
                        }
                        break;
                }
                this.niazsanjiIntegrationService.update(this.niazsanjiIntegration).subscribe(
                    (res: HttpResponse<INiazsanjiIntegrationMarineSuffix>) => this.onSaveSuccess(),
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

        if(this.niazsanjiIntegration.status == 21 || this.niazsanjiIntegration.status == 11 || this.niazsanjiIntegration.status == 1 || this.niazsanjiIntegration.status == 30){
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
    selector: 'mi-niazsanji-integration-marine-suffix-comment-popup',
    template: ''
})
export class NiazsanjiIntegrationMarineSuffixCommentPopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiIntegration }) => {
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

                    this.ngbModalRef = this.modalService.open(NiazsanjiIntegrationMarineSuffixCommentDialogComponent as Component, {
                        size: 'lg',
                        backdrop: false
                    });
                    this.ngbModalRef.componentInstance.niazsanjiIntegration = niazsanjiIntegration;
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
