import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPollItemMarineSuffix } from 'app/shared/model/poll-item-marine-suffix.model';
import { PollItemMarineSuffixService } from './poll-item-marine-suffix.service';

@Component({
    selector: 'mi-poll-item-marine-suffix-delete-dialog',
    templateUrl: './poll-item-marine-suffix-delete-dialog.component.html'
})
export class PollItemMarineSuffixDeleteDialogComponent {
    pollItem: IPollItemMarineSuffix;

    constructor(
        private pollItemService: PollItemMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pollItemService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pollItemListModification',
                content: 'Deleted an pollItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-poll-item-marine-suffix-delete-popup',
    template: ''
})
export class PollItemMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pollItem }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PollItemMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pollItem = pollItem;
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
