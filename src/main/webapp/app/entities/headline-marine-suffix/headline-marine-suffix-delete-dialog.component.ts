import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHeadlineMarineSuffix } from 'app/shared/model/headline-marine-suffix.model';
import { HeadlineMarineSuffixService } from './headline-marine-suffix.service';

@Component({
    selector: 'mi-headline-marine-suffix-delete-dialog',
    templateUrl: './headline-marine-suffix-delete-dialog.component.html'
})
export class HeadlineMarineSuffixDeleteDialogComponent {
    headline: IHeadlineMarineSuffix;

    constructor(
        protected headlineService: HeadlineMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.headlineService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'headlineListModification',
                content: 'Deleted an headline'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-headline-marine-suffix-delete-popup',
    template: ''
})
export class HeadlineMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ headline }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HeadlineMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.headline = headline;
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
