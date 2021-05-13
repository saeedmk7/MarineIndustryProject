import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMatchingRunningStepMarineSuffix } from 'app/shared/model/matching-running-step-marine-suffix.model';
import { MatchingRunningStepMarineSuffixService } from './matching-running-step-marine-suffix.service';

@Component({
    selector: 'mi-matching-running-step-marine-suffix-delete-dialog',
    templateUrl: './matching-running-step-marine-suffix-delete-dialog.component.html'
})
export class MatchingRunningStepMarineSuffixDeleteDialogComponent {
    matchingRunningStep: IMatchingRunningStepMarineSuffix;

    constructor(
        protected matchingRunningStepService: MatchingRunningStepMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.matchingRunningStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'matchingRunningStepListModification',
                content: 'Deleted an matchingRunningStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-matching-running-step-marine-suffix-delete-popup',
    template: ''
})
export class MatchingRunningStepMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matchingRunningStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MatchingRunningStepMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.matchingRunningStep = matchingRunningStep;
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
