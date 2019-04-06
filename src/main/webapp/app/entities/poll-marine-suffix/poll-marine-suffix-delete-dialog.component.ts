import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPollMarineSuffix } from 'app/shared/model/poll-marine-suffix.model';
import { PollMarineSuffixService } from './poll-marine-suffix.service';

@Component({
    selector: 'mi-poll-marine-suffix-delete-dialog',
    templateUrl: './poll-marine-suffix-delete-dialog.component.html'
})
export class PollMarineSuffixDeleteDialogComponent {
    poll: IPollMarineSuffix;

    constructor(private pollService: PollMarineSuffixService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pollService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pollListModification',
                content: 'Deleted an poll'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-poll-marine-suffix-delete-popup',
    template: ''
})
export class PollMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ poll }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PollMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.poll = poll;
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
