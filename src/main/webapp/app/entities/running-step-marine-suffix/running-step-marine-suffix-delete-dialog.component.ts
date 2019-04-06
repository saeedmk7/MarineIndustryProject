import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRunningStepMarineSuffix } from 'app/shared/model/running-step-marine-suffix.model';
import { RunningStepMarineSuffixService } from './running-step-marine-suffix.service';

@Component({
    selector: 'mi-running-step-marine-suffix-delete-dialog',
    templateUrl: './running-step-marine-suffix-delete-dialog.component.html'
})
export class RunningStepMarineSuffixDeleteDialogComponent {
    runningStep: IRunningStepMarineSuffix;

    constructor(
        private runningStepService: RunningStepMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.runningStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'runningStepListModification',
                content: 'Deleted an runningStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-running-step-marine-suffix-delete-popup',
    template: ''
})
export class RunningStepMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ runningStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RunningStepMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.runningStep = runningStep;
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
