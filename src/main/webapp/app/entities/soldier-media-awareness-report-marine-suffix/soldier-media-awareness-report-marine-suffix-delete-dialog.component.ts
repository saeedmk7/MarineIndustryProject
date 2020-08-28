import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISoldierMediaAwarenessReportMarineSuffix } from 'app/shared/model/soldier-media-awareness-report-marine-suffix.model';
import { SoldierMediaAwarenessReportMarineSuffixService } from './soldier-media-awareness-report-marine-suffix.service';

@Component({
    selector: 'mi-soldier-media-awareness-report-marine-suffix-delete-dialog',
    templateUrl: './soldier-media-awareness-report-marine-suffix-delete-dialog.component.html'
})
export class SoldierMediaAwarenessReportMarineSuffixDeleteDialogComponent {
    soldierMediaAwarenessReport: ISoldierMediaAwarenessReportMarineSuffix;

    constructor(
        protected soldierMediaAwarenessReportService: SoldierMediaAwarenessReportMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.soldierMediaAwarenessReportService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'soldierMediaAwarenessReportListModification',
                content: 'Deleted an soldierMediaAwarenessReport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-soldier-media-awareness-report-marine-suffix-delete-popup',
    template: ''
})
export class SoldierMediaAwarenessReportMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soldierMediaAwarenessReport }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SoldierMediaAwarenessReportMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.soldierMediaAwarenessReport = soldierMediaAwarenessReport;
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
