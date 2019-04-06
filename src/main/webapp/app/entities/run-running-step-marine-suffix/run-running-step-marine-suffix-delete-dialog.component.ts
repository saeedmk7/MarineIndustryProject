import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRunRunningStepMarineSuffix } from 'app/shared/model/run-running-step-marine-suffix.model';
import { RunRunningStepMarineSuffixService } from './run-running-step-marine-suffix.service';

@Component({
    selector: 'mi-run-running-step-marine-suffix-delete-dialog',
    templateUrl: './run-running-step-marine-suffix-delete-dialog.component.html'
})
export class RunRunningStepMarineSuffixDeleteDialogComponent {
    runRunningStep: IRunRunningStepMarineSuffix;

    constructor(
        private runRunningStepService: RunRunningStepMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.runRunningStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'runRunningStepListModification',
                content: 'Deleted an runRunningStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-run-running-step-marine-suffix-delete-popup',
    template: ''
})
export class RunRunningStepMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ runRunningStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RunRunningStepMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.runRunningStep = runRunningStep;
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
