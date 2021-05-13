import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { IPersonMarineSuffix, PersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { IUser, Principal, UserService } from 'app/core';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { MatchingEducationalRecordMarineSuffixService } from 'app/entities/matching-educational-record-marine-suffix/matching-educational-record-marine-suffix.service';

@Component({
    selector: 'mi-matching-educational-record-marine-suffix-comment-dialog',
    templateUrl: './matching-educational-record-marine-suffix-comment-dialog.component.html'
})
export class MatchingEducationalRecordMarineSuffixCommentDialogComponent implements OnInit {
    matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix;
    commentType: string;
    organizationcharts: IOrganizationChartMarineSuffix[];
    targetPeople: IPersonMarineSuffix[];

    comment: string;

    currentAccount: any;
    currentPerson: IPersonMarineSuffix;
    message: string;
    commentRequired: boolean = false;

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;

    constructor(
        protected matchingEducationalRecordService: MatchingEducationalRecordMarineSuffixService,
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
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }
    confirmDelete(id: number) {
        this.matchingEducationalRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'matchingEducationalRecordListModification',
                content: 'Commented an matchingEducationalRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
    isSaving: boolean = false;
    save() {
        this.isSaving = true;
        this.message = '';
        let currentUserFullName = this.currentPerson.fullName;
        this.matchingEducationalRecord.conversation += '\n ------------------------------------- \n';
        switch (this.commentType) {
            case 'COMMENT':
                if (!this.comment) {
                    this.message = 'لطفا نظر خود را در کادر بالا وارد نمائید.';
                    this.isSaving = false;
                    return;
                }
                this.matchingEducationalRecord.conversation +=
                    ' ثبت نظر توسط ' +
                    currentUserFullName +
                    ' در تاریخ: ' +
                    this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                    ' ثبت شد. ';
                this.matchingEducationalRecord.conversation += '\n';
                this.matchingEducationalRecord.conversation += currentUserFullName + ': ' + this.comment;
                break;
            case 'REJECT':
                if (!this.comment) {
                    this.message = 'لطفا علت عدم تایید را در کادر بالا وارد نمائید.';
                    this.isSaving = false;
                    return;
                }
                this.matchingEducationalRecord.conversation +=
                    ' رد درخواست توسط ' +
                    currentUserFullName +
                    ' در تاریخ: ' +
                    this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                    ' انجام شد. ';
                this.matchingEducationalRecord.conversation += '\n';
                this.matchingEducationalRecord.conversation += currentUserFullName + ': ' + this.comment;

                this.matchingEducationalRecord.requestStatus = RequestStatus.IGNORE;
                this.matchingEducationalRecord.changeStatusUserLogin = this.currentAccount.login;
                break;
            case 'ACCEPT':
                this.matchingEducationalRecord.conversation +=
                    ' تایید درخواست توسط ' +
                    currentUserFullName +
                    ' در تاریخ: ' +
                    this.convertObjectDatesService.miladi2Shamsi(new Date()) +
                    ' انجام شد. ';
                if (this.comment) {
                    this.matchingEducationalRecord.conversation += '\n';
                    this.matchingEducationalRecord.conversation += currentUserFullName + ': ' + this.comment;
                }

                if (this.isKarshenasArshadAmozeshSazman) {
                    this.matchingEducationalRecord.chartStatus = 0;
                    this.matchingEducationalRecord.bossStatus = 20;
                    this.matchingEducationalRecord.changeStatusUserLogin = this.currentAccount.login;
                } else if (this.isModirKolAmozesh) {
                    this.matchingEducationalRecord.chartStatus = 0;
                    this.matchingEducationalRecord.bossStatus = 30;
                    this.matchingEducationalRecord.changeStatusUserLogin = this.currentAccount.login;
                } else {
                    let organization = this.organizationcharts.find(a => a.id == this.currentPerson.organizationChartId);
                    if (organization.parentId > 0) {
                        this.matchingEducationalRecord.chartStatus = organization.parentId;
                        this.matchingEducationalRecord.changeStatusUserLogin = this.currentAccount.login;
                    } else {
                        this.matchingEducationalRecord.chartStatus = 0;
                        this.matchingEducationalRecord.bossStatus = 10;
                        this.matchingEducationalRecord.changeStatusUserLogin = this.currentAccount.login;
                        /*this.matchingEducationalRecordService.finalize(this.matchingEducationalRecord).subscribe(
                            (res: HttpResponse<IFinalOrganizationNiazsanjiMarineSuffix>) => this.onSaveSuccess(),
                            (res: HttpErrorResponse) => this.onSaveError(res)
                        );*/
                        //return;
                    }
                }
                break;
        }
        this.matchingEducationalRecordService
            .update(this.matchingEducationalRecord)
            .subscribe(
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
    knowAboutStatusForContinue() {
        if (
            this.matchingEducationalRecord.requestStatus == RequestStatus.ACCEPT ||
            this.matchingEducationalRecord.requestStatus == RequestStatus.IGNORE
        ) {
            this.clear();
        }
        /*if(this.matchingEducationalRecord.status != this.currentPerson.organizationChartId){
            this.clear();
        }*/
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
    setPermission() {
        this.principal.identity().then(account => {
            this.currentAccount = account;

            this.setRoles(this.currentAccount);

            this.personService.find(this.currentAccount.personId).subscribe(
                (resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;
                    this.knowAboutStatusForContinue();
                    if (this.commentType == 'ACCEPT') {
                        this.findTargetPeople(resp.body);
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
    }
    findTargetPeople(person: IPersonMarineSuffix) {
        let organization = this.organizationcharts.find(a => a.id == person.organizationChartId);
        if (organization.parentId > 0) {
            let neededOrgId: number = organization.parentId;

            let criteria = [
                {
                    key: 'organizationChartId.equals',
                    value: neededOrgId
                }
            ];
            this.personService
                .query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (resp: HttpResponse<IPersonMarineSuffix[]>) => {
                        let orgPeople = resp.body;
                        if (orgPeople.length > 0) {
                            this.targetPeople = orgPeople;
                        } else {
                            this.targetPeople = [];
                            this.targetPeople = [
                                new PersonMarineSuffix(
                                    0,
                                    'خطا',
                                    'خطا',
                                    'خطا: نفر دریافت کننده ای در چارت سازمانی برای شما تعریف نشده است. لطفا با مدیریت سامانه تماس بگیرید. '
                                )
                            ];
                        }
                    },
                    error => this.onError('فردی یافت نشد.')
                );
        } else {
            this.targetPeople = [];
            //this.targetPeople.push(new PersonMarineSuffix(0, 'ثبت نهایی', 'ثبت نهایی', 'درخواست شما پس از ثبت تایید برای کارشناس ارشد آموزش سازمان برای بازبینی ارسال می شود.'));
            this.targetPeople.push(
                new PersonMarineSuffix(
                    0,
                    'ثبت نهایی',
                    'ثبت نهایی',
                    'درخواست تطبیق سوابق آموزشی شما پس از ثبت تایید برای تکمیل و بررسی اطلاعات توسط آموزش سازمان، به کارشناس ارشد آموزش ارسال می گردد.'
                )
            );
        }
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

@Component({
    selector: 'mi-matching-educational-record-marine-suffix-comment-popup',
    template: ''
})
export class MatchingEducationalRecordMarineSuffixCommentPopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matchingEducationalRecord }) => {
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
                    this.ngbModalRef = this.modalService.open(MatchingEducationalRecordMarineSuffixCommentDialogComponent as Component, {
                        size: 'lg',
                        backdrop: false
                    });
                    this.ngbModalRef.componentInstance.matchingEducationalRecord = matchingEducationalRecord;
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
