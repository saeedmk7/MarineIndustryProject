import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelThreeEffectivenessMarineSuffix } from 'app/shared/model/level-three-effectiveness-marine-suffix.model';
import { LevelThreeEffectivenessMarineSuffixService } from './level-three-effectiveness-marine-suffix.service';

@Component({
    selector: 'mi-level-three-effectiveness-marine-suffix-delete-dialog',
    templateUrl: './level-three-effectiveness-marine-suffix-delete-dialog.component.html'
})
export class LevelThreeEffectivenessMarineSuffixDeleteDialogComponent {
    levelThreeEffectiveness: ILevelThreeEffectivenessMarineSuffix;

    constructor(
        protected levelThreeEffectivenessService: LevelThreeEffectivenessMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.levelThreeEffectivenessService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'levelThreeEffectivenessListModification',
                content: 'Deleted an levelThreeEffectiveness'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-level-three-effectiveness-marine-suffix-delete-popup',
    template: ''
})
export class LevelThreeEffectivenessMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeEffectiveness }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LevelThreeEffectivenessMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.levelThreeEffectiveness = levelThreeEffectiveness;
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
