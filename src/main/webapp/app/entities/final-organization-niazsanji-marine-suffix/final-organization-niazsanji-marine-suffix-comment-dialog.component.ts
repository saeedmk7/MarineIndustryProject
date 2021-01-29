import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { Principal, UserService } from 'app/core';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import { RequestNiazsanjiFardiMarineSuffixService } from 'app/entities/request-niazsanji-fardi-marine-suffix';
import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { FinalOrganizationNiazsanjiMarineSuffixService } from 'app/entities/final-organization-niazsanji-marine-suffix/final-organization-niazsanji-marine-suffix.service';
import { RequestOrganizationNiazsanjiMarineSuffixService } from 'app/entities/request-organization-niazsanji-marine-suffix/request-organization-niazsanji-marine-suffix.service';
import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';

@Component({
    selector: 'mi-niazsanji-fardi-marine-suffix-comment-dialog',
    templateUrl: './final-organization-niazsanji-marine-suffix-comment-dialog.component.html'
})
export class FinalOrganizationNiazsanjiMarineSuffixCommentDialogComponent implements OnInit {
    finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix;
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
        protected finalOrganizationNiazsanjiService: FinalOrganizationNiazsanjiMarineSuffixService,
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
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    /*confirmDelete(id: number) {
        this.niazsanjiFardiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'requestNiazsanjiFardiListModification',
                content: 'Commented an requestNiazsanjiFardi'
            });
            this.activeModal.dismiss(true);
        });
    }*/
    isSaving: boolean = false;
    save() {
        this.isSaving = true;
        this.requestOrganizationNiazsanjiService.find(this.finalOrganizationNiazsanji.requestOrganizationNiazsanjiId).subscribe(
            (resp: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => {
                let requestOrganizationNiazsanji = resp.body;
                this.message = '';
                let currentUserFullName = this.currentPerson.fullName;
                requestOrganizationNiazsanji.conversation += '\n ------------------------------------- \n';
                switch (this.commentType) {
                    case 'COMMENT':
                        if (!this.comment) {
                            this.message = 'لطفا نظر خود را در کادر بالا وارد نمائید.';
                            this.isSaving = false;
                            return;
                        }
                        requestOrganizationNiazsanji.conversation +=
                            ' ثبت نظر توسط ' +
                            currentUserFullName +
                            ' در تاریخ: ' +
                            this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                            ' ثبت شد. ';
                        requestOrganizationNiazsanji.conversation += '\n';
                        requestOrganizationNiazsanji.conversation += currentUserFullName + ': ' + this.comment;
                        this.requestOrganizationNiazsanjiService
                            .update(requestOrganizationNiazsanji)
                            .subscribe(
                                (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => this.onSaveSuccess(),
                                (res: HttpErrorResponse) => this.onSaveError(res)
                            );
                        return;
                    case 'REJECT':
                        if (!this.comment) {
                            this.message = 'لطفا علت عدم تایید را در کادر بالا وارد نمائید.';
                            this.isSaving = false;
                            return;
                        }
                        requestOrganizationNiazsanji.conversation +=
                            ' رد درخواست توسط ' +
                            currentUserFullName +
                            ' در تاریخ: ' +
                            this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                            ' انجام شد. ';
                        requestOrganizationNiazsanji.conversation += '\n';
                        requestOrganizationNiazsanji.conversation += currentUserFullName + ': ' + this.comment;

                        requestOrganizationNiazsanji.requestStatus = RequestStatus.IGNORE;
                        requestOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                        this.finalOrganizationNiazsanji.requestStatus = RequestStatus.IGNORE;
                        if (this.isKarshenasArshadAmozesh) this.finalOrganizationNiazsanji.status = 1;
                        if (this.isModirKolAmozesh) this.finalOrganizationNiazsanji.status = 11;
                        if (this.finalOrganizationNiazsanji.status == 20) this.finalOrganizationNiazsanji.status = 21;
                        this.finalOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                        this.requestOrganizationNiazsanjiService
                            .update(requestOrganizationNiazsanji)
                            .subscribe(
                                (res: HttpResponse<IRequestOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                (res: HttpErrorResponse) => this.onSaveError(res)
                            );
                        break;
                    case 'ACCEPT':
                        if (this.isKarshenasArshadAmozesh && this.finalOrganizationNiazsanji.status == 0) {
                            requestOrganizationNiazsanji.conversation +=
                                ' تایید درخواست توسط ' +
                                currentUserFullName +
                                ' در تاریخ: ' +
                                this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                                ' انجام شد. ';
                            if (this.comment) {
                                requestOrganizationNiazsanji.conversation += '\n';
                                requestOrganizationNiazsanji.conversation += currentUserFullName + ': ' + this.comment;
                            }
                            this.finalOrganizationNiazsanji.status = 10;
                            this.finalOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                            this.requestOrganizationNiazsanjiService
                                .update(requestOrganizationNiazsanji)
                                .subscribe(
                                    (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => this.onSaveSuccess(),
                                    (res: HttpErrorResponse) => this.onSaveError(res)
                                );
                        } else if (this.isModirKolAmozesh && this.finalOrganizationNiazsanji.status == 10) {
                            requestOrganizationNiazsanji.conversation +=
                                ' تایید درخواست توسط ' +
                                currentUserFullName +
                                ' در تاریخ: ' +
                                this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                                ' انجام شد. ';
                            if (this.comment) {
                                requestOrganizationNiazsanji.conversation += '\n';
                                requestOrganizationNiazsanji.conversation += currentUserFullName + ': ' + this.comment;
                            }
                            this.finalOrganizationNiazsanji.status = 20;
                            this.finalOrganizationNiazsanji.changeStatusUserLogin = this.currentAccount.login;
                            this.requestOrganizationNiazsanjiService
                                .update(requestOrganizationNiazsanji)
                                .subscribe(
                                    (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => this.onSaveSuccess(),
                                    (res: HttpErrorResponse) => this.onSaveError(res)
                                );
                        } else {
                            if (this.finalOrganizationNiazsanji.niazsanjiYear) {
                                requestOrganizationNiazsanji.conversation +=
                                    ' تایید نهایی و تصویب شوراء تربیت و آموزش سازمان توسط ' +
                                    currentUserFullName +
                                    ' در تاریخ: ' +
                                    this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                                    ' انجام شد. ';
                                if (this.comment) {
                                    requestOrganizationNiazsanji.conversation += '\n';
                                    requestOrganizationNiazsanji.conversation += currentUserFullName + ': ' + this.comment;
                                }
                                requestOrganizationNiazsanji.requestStatus = RequestStatus.ACCEPT;
                                this.requestOrganizationNiazsanjiService
                                    .update(requestOrganizationNiazsanji)
                                    .subscribe(
                                        (res: HttpResponse<IRequestNiazsanjiFardiMarineSuffix>) => this.onSaveSuccess(),
                                        (res: HttpErrorResponse) => this.onSaveError(res)
                                    );
                                this.finalOrganizationNiazsanjiService
                                    .finalize(this.finalOrganizationNiazsanji)
                                    .subscribe(
                                        (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                                        (res: HttpErrorResponse) => this.onSaveError(res)
                                    );
                            } else {
                                alert('لطفا قبل از اجرا کردن درخواست سال نیازسنجی آن را مشخص نمائید.');
                            }
                            return;
                        }
                        break;
                }
                this.finalOrganizationNiazsanjiService
                    .update(this.finalOrganizationNiazsanji)
                    .subscribe(
                        (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                        (res: HttpErrorResponse) => this.onSaveError(res)
                    );
            },
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
        this.knowAboutStatusForContinue();
        if (this.organizationChartService.organizationchartsAll) {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.setPermission();
        } else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = res.body;
                    this.setPermission();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    knowAboutStatusForContinue() {
        if (
            this.finalOrganizationNiazsanji.status == 21 ||
            this.finalOrganizationNiazsanji.status == 11 ||
            this.finalOrganizationNiazsanji.status == 1 ||
            this.finalOrganizationNiazsanji.status == 30
        ) {
            this.clear();
        }
    }

    setPermission() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;

            if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) {
                this.isModirKolAmozesh = true;
            }
            if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined) {
                this.isKarshenasArshadAmozesh = true;
            }

            this.personService.find(this.currentAccount.personId).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

@Component({
    selector: 'mi-request-niazsanji-fardi-marine-suffix-comment-popup',
    template: ''
})
export class FinalOrganizationNiazsanjiMarineSuffixCommentPopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalOrganizationNiazsanji }) => {
            this.activatedRoute.params.subscribe(params => {
                let commentType = params['CommentType'] ? params['CommentType'] : '';
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
                    this.ngbModalRef = this.modalService.open(FinalOrganizationNiazsanjiMarineSuffixCommentDialogComponent as Component, {
                        size: 'lg',
                        backdrop: false
                    });
                    this.ngbModalRef.componentInstance.finalOrganizationNiazsanji = finalOrganizationNiazsanji;
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
