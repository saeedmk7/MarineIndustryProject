import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEffectivenessPhaseMarineSuffix } from 'app/shared/model/effectiveness-phase-marine-suffix.model';
import { EffectivenessPhaseMarineSuffixService } from './effectiveness-phase-marine-suffix.service';

@Component({
    selector: 'mi-effectiveness-phase-marine-suffix-delete-dialog',
    templateUrl: './effectiveness-phase-marine-suffix-delete-dialog.component.html'
})
export class EffectivenessPhaseMarineSuffixDeleteDialogComponent {
    effectivenessPhase: IEffectivenessPhaseMarineSuffix;

    constructor(
        protected effectivenessPhaseService: EffectivenessPhaseMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.effectivenessPhaseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'effectivenessPhaseListModification',
                content: 'Deleted an effectivenessPhase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-effectiveness-phase-marine-suffix-delete-popup',
    template: ''
})
export class EffectivenessPhaseMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessPhase }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EffectivenessPhaseMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.effectivenessPhase = effectivenessPhase;
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
