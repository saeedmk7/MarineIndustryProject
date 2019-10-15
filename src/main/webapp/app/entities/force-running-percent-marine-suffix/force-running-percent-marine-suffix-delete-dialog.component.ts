import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IForceRunningPercentMarineSuffix } from 'app/shared/model/force-running-percent-marine-suffix.model';
import { ForceRunningPercentMarineSuffixService } from './force-running-percent-marine-suffix.service';

@Component({
    selector: 'mi-force-running-percent-marine-suffix-delete-dialog',
    templateUrl: './force-running-percent-marine-suffix-delete-dialog.component.html'
})
export class ForceRunningPercentMarineSuffixDeleteDialogComponent {
    forceRunningPercent: IForceRunningPercentMarineSuffix;

    constructor(
        protected forceRunningPercentService: ForceRunningPercentMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.forceRunningPercentService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'forceRunningPercentListModification',
                content: 'Deleted an forceRunningPercent'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-force-running-percent-marine-suffix-delete-popup',
    template: ''
})
export class ForceRunningPercentMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ forceRunningPercent }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ForceRunningPercentMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.forceRunningPercent = forceRunningPercent;
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
