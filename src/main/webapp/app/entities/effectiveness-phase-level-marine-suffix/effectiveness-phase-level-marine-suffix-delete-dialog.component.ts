import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEffectivenessPhaseLevelMarineSuffix } from 'app/shared/model/effectiveness-phase-level-marine-suffix.model';
import { EffectivenessPhaseLevelMarineSuffixService } from './effectiveness-phase-level-marine-suffix.service';

@Component({
    selector: 'mi-effectiveness-phase-level-marine-suffix-delete-dialog',
    templateUrl: './effectiveness-phase-level-marine-suffix-delete-dialog.component.html'
})
export class EffectivenessPhaseLevelMarineSuffixDeleteDialogComponent {
    effectivenessPhaseLevel: IEffectivenessPhaseLevelMarineSuffix;

    constructor(
        protected effectivenessPhaseLevelService: EffectivenessPhaseLevelMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.effectivenessPhaseLevelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'effectivenessPhaseLevelListModification',
                content: 'Deleted an effectivenessPhaseLevel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-effectiveness-phase-level-marine-suffix-delete-popup',
    template: ''
})
export class EffectivenessPhaseLevelMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessPhaseLevel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EffectivenessPhaseLevelMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.effectivenessPhaseLevel = effectivenessPhaseLevel;
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
