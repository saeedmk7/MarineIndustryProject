import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMediaAwarenessReportMarineSuffix } from 'app/shared/model/media-awareness-report-marine-suffix.model';
import { MediaAwarenessReportMarineSuffixService } from './media-awareness-report-marine-suffix.service';

@Component({
    selector: 'mi-media-awareness-report-marine-suffix-delete-dialog',
    templateUrl: './media-awareness-report-marine-suffix-delete-dialog.component.html'
})
export class MediaAwarenessReportMarineSuffixDeleteDialogComponent {
    mediaAwarenessReport: IMediaAwarenessReportMarineSuffix;

    constructor(
        protected mediaAwarenessReportService: MediaAwarenessReportMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mediaAwarenessReportService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mediaAwarenessReportListModification',
                content: 'Deleted an mediaAwarenessReport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-media-awareness-report-marine-suffix-delete-popup',
    template: ''
})
export class MediaAwarenessReportMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mediaAwarenessReport }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MediaAwarenessReportMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.mediaAwarenessReport = mediaAwarenessReport;
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
