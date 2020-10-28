import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelFourCriteriaMarineSuffix } from 'app/shared/model/level-four-criteria-marine-suffix.model';
import { LevelFourCriteriaMarineSuffixService } from './level-four-criteria-marine-suffix.service';

@Component({
    selector: 'mi-level-four-criteria-marine-suffix-delete-dialog',
    templateUrl: './level-four-criteria-marine-suffix-delete-dialog.component.html'
})
export class LevelFourCriteriaMarineSuffixDeleteDialogComponent {
    levelFourCriteria: ILevelFourCriteriaMarineSuffix;

    constructor(
        protected levelFourCriteriaService: LevelFourCriteriaMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.levelFourCriteriaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'levelFourCriteriaListModification',
                content: 'Deleted an levelFourCriteria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-level-four-criteria-marine-suffix-delete-popup',
    template: ''
})
export class LevelFourCriteriaMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelFourCriteria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LevelFourCriteriaMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.levelFourCriteria = levelFourCriteria;
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
