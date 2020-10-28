import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelFourScoreMarineSuffix } from 'app/shared/model/level-four-score-marine-suffix.model';
import { LevelFourScoreMarineSuffixService } from './level-four-score-marine-suffix.service';

@Component({
    selector: 'mi-level-four-score-marine-suffix-delete-dialog',
    templateUrl: './level-four-score-marine-suffix-delete-dialog.component.html'
})
export class LevelFourScoreMarineSuffixDeleteDialogComponent {
    levelFourScore: ILevelFourScoreMarineSuffix;

    constructor(
        protected levelFourScoreService: LevelFourScoreMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.levelFourScoreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'levelFourScoreListModification',
                content: 'Deleted an levelFourScore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-level-four-score-marine-suffix-delete-popup',
    template: ''
})
export class LevelFourScoreMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelFourScore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LevelFourScoreMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.levelFourScore = levelFourScore;
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
