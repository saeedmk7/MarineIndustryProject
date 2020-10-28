import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelFourEffectivenessMarineSuffix } from 'app/shared/model/level-four-effectiveness-marine-suffix.model';
import { LevelFourEffectivenessMarineSuffixService } from './level-four-effectiveness-marine-suffix.service';

@Component({
    selector: 'mi-level-four-effectiveness-marine-suffix-delete-dialog',
    templateUrl: './level-four-effectiveness-marine-suffix-delete-dialog.component.html'
})
export class LevelFourEffectivenessMarineSuffixDeleteDialogComponent {
    levelFourEffectiveness: ILevelFourEffectivenessMarineSuffix;

    constructor(
        protected levelFourEffectivenessService: LevelFourEffectivenessMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.levelFourEffectivenessService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'levelFourEffectivenessListModification',
                content: 'Deleted an levelFourEffectiveness'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-level-four-effectiveness-marine-suffix-delete-popup',
    template: ''
})
export class LevelFourEffectivenessMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelFourEffectiveness }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LevelFourEffectivenessMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.levelFourEffectiveness = levelFourEffectiveness;
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
