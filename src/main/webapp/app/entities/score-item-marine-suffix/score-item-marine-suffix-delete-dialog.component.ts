import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IScoreItemMarineSuffix } from 'app/shared/model/score-item-marine-suffix.model';
import { ScoreItemMarineSuffixService } from './score-item-marine-suffix.service';

@Component({
    selector: 'mi-score-item-marine-suffix-delete-dialog',
    templateUrl: './score-item-marine-suffix-delete-dialog.component.html'
})
export class ScoreItemMarineSuffixDeleteDialogComponent {
    scoreItem: IScoreItemMarineSuffix;

    constructor(
        private scoreItemService: ScoreItemMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.scoreItemService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'scoreItemListModification',
                content: 'Deleted an scoreItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-score-item-marine-suffix-delete-popup',
    template: ''
})
export class ScoreItemMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ scoreItem }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ScoreItemMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.scoreItem = scoreItem;
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
