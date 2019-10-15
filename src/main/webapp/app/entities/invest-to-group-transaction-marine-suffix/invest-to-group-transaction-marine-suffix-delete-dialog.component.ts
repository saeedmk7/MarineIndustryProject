import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvestToGroupTransactionMarineSuffix } from 'app/shared/model/invest-to-group-transaction-marine-suffix.model';
import { InvestToGroupTransactionMarineSuffixService } from './invest-to-group-transaction-marine-suffix.service';

@Component({
    selector: 'mi-invest-to-group-transaction-marine-suffix-delete-dialog',
    templateUrl: './invest-to-group-transaction-marine-suffix-delete-dialog.component.html'
})
export class InvestToGroupTransactionMarineSuffixDeleteDialogComponent {
    investToGroupTransaction: IInvestToGroupTransactionMarineSuffix;

    constructor(
        protected investToGroupTransactionService: InvestToGroupTransactionMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.investToGroupTransactionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'investToGroupTransactionListModification',
                content: 'Deleted an investToGroupTransaction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-invest-to-group-transaction-marine-suffix-delete-popup',
    template: ''
})
export class InvestToGroupTransactionMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ investToGroupTransaction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InvestToGroupTransactionMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.investToGroupTransaction = investToGroupTransaction;
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
