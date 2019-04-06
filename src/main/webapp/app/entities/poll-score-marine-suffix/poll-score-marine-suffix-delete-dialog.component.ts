import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPollScoreMarineSuffix } from 'app/shared/model/poll-score-marine-suffix.model';
import { PollScoreMarineSuffixService } from './poll-score-marine-suffix.service';

@Component({
    selector: 'mi-poll-score-marine-suffix-delete-dialog',
    templateUrl: './poll-score-marine-suffix-delete-dialog.component.html'
})
export class PollScoreMarineSuffixDeleteDialogComponent {
    pollScore: IPollScoreMarineSuffix;

    constructor(
        private pollScoreService: PollScoreMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pollScoreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pollScoreListModification',
                content: 'Deleted an pollScore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-poll-score-marine-suffix-delete-popup',
    template: ''
})
export class PollScoreMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pollScore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PollScoreMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pollScore = pollScore;
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
