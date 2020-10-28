import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelThreeScoreMarineSuffix } from 'app/shared/model/level-three-score-marine-suffix.model';
import { LevelThreeScoreMarineSuffixService } from './level-three-score-marine-suffix.service';

@Component({
    selector: 'mi-level-three-score-marine-suffix-delete-dialog',
    templateUrl: './level-three-score-marine-suffix-delete-dialog.component.html'
})
export class LevelThreeScoreMarineSuffixDeleteDialogComponent {
    levelThreeScore: ILevelThreeScoreMarineSuffix;

    constructor(
        protected levelThreeScoreService: LevelThreeScoreMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.levelThreeScoreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'levelThreeScoreListModification',
                content: 'Deleted an levelThreeScore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-level-three-score-marine-suffix-delete-popup',
    template: ''
})
export class LevelThreeScoreMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeScore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LevelThreeScoreMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.levelThreeScore = levelThreeScore;
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
