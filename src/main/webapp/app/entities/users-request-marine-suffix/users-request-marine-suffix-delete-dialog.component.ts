import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';
import { UsersRequestMarineSuffixService } from './users-request-marine-suffix.service';

@Component({
    selector: 'mi-users-request-marine-suffix-delete-dialog',
    templateUrl: './users-request-marine-suffix-delete-dialog.component.html'
})
export class UsersRequestMarineSuffixDeleteDialogComponent {
    usersRequest: IUsersRequestMarineSuffix;

    constructor(
        private usersRequestService: UsersRequestMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.usersRequestService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'usersRequestListModification',
                content: 'Deleted an usersRequest'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-users-request-marine-suffix-delete-popup',
    template: ''
})
export class UsersRequestMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ usersRequest }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UsersRequestMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
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
