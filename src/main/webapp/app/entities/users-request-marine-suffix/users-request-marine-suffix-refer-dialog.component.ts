import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';
import { UsersRequestMarineSuffixService } from './users-request-marine-suffix.service';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { HttpResponse } from '@angular/common/http';

@Component({
    selector: 'mi-users-request-marine-suffix-refer-dialog',
    templateUrl: './users-request-marine-suffix-refer-dialog.component.html'
})
export class UsersRequestMarineSuffixReferDialogComponent implements OnInit, OnDestroy {
    usersRequest: IUsersRequestMarineSuffix;
    people: IPersonMarineSuffix[] = [];
    selectedPeople: IPersonMarineSuffix[] = [];
    comment: string = '';
    constructor(
        private usersRequestService: UsersRequestMarineSuffixService,
        private personService: PersonMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmRefer(id: number) {
        if (this.selectedPeople.length > 0) {
            this.usersRequestService.find(id).subscribe((resp: HttpResponse<IUsersRequestMarineSuffix>) => {
                this.usersRequest = resp.body;
                const peopleNames = this.selectedPeople.map(w => w.fullName).join(' , ');
                this.usersRequest.conversation += '\n ------------------------------------- \n';
                this.usersRequest.conversation += 'به آقا(یان) ' + peopleNames + ' ارجاع داده شد.';
                if (this.comment) {
                    this.usersRequest.conversation += '\n';
                    this.usersRequest.conversation += this.comment;
                }
                this.usersRequest.people = this.selectedPeople;
                this.usersRequestService.update(this.usersRequest).subscribe((resp: HttpResponse<IUsersRequestMarineSuffix>) => {
                    this.activeModal.dismiss(true);
                });
            });
        }
        /*this.usersRequestService.refer(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'usersRequestListModification',
                content: 'Referd an usersRequest'
            });
            this.activeModal.dismiss(true);
        });*/
    }

    ngOnDestroy(): void {}

    ngOnInit(): void {
        if (this.personService.people) {
            this.people = this.personService.people;
        } else {
            this.personService.query().subscribe(
                (resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    this.people = resp.body;
                },
                error => this.onError('فردی یافت نشد.')
            );
        }
    }
    private onError(errorMessage: string) {
        //this.jhiAlertService.error(errorMessage, null, null);
    }
}

@Component({
    selector: 'mi-users-request-marine-suffix-refer-popup',
    template: ''
})
export class UsersRequestMarineSuffixReferPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ usersRequest }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UsersRequestMarineSuffixReferDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.usersRequest = usersRequest;
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
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
