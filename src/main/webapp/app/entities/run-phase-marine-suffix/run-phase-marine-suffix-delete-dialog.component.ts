import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRunPhaseMarineSuffix } from 'app/shared/model/run-phase-marine-suffix.model';
import { RunPhaseMarineSuffixService } from './run-phase-marine-suffix.service';

@Component({
    selector: 'mi-run-phase-marine-suffix-delete-dialog',
    templateUrl: './run-phase-marine-suffix-delete-dialog.component.html'
})
export class RunPhaseMarineSuffixDeleteDialogComponent {
    runPhase: IRunPhaseMarineSuffix;

    constructor(
        private runPhaseService: RunPhaseMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.runPhaseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'runPhaseListModification',
                content: 'Deleted an runPhase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-run-phase-marine-suffix-delete-popup',
    template: ''
})
export class RunPhaseMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ runPhase }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RunPhaseMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.runPhase = runPhase;
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
