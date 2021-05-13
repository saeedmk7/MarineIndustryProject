import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMatchingRunRunningStepMarineSuffix } from 'app/shared/model/matching-run-running-step-marine-suffix.model';
import { MatchingRunRunningStepMarineSuffixService } from './matching-run-running-step-marine-suffix.service';

@Component({
    selector: 'mi-matching-run-running-step-marine-suffix-delete-dialog',
    templateUrl: './matching-run-running-step-marine-suffix-delete-dialog.component.html'
})
export class MatchingRunRunningStepMarineSuffixDeleteDialogComponent {
    matchingRunRunningStep: IMatchingRunRunningStepMarineSuffix;

    constructor(
        protected matchingRunRunningStepService: MatchingRunRunningStepMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.matchingRunRunningStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'matchingRunRunningStepListModification',
                content: 'Deleted an matchingRunRunningStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-matching-run-running-step-marine-suffix-delete-popup',
    template: ''
})
export class MatchingRunRunningStepMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matchingRunRunningStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MatchingRunRunningStepMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.matchingRunRunningStep = matchingRunRunningStep;
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
