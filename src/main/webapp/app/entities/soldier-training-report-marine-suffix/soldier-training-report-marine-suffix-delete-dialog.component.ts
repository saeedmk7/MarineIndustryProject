import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISoldierTrainingReportMarineSuffix } from 'app/shared/model/soldier-training-report-marine-suffix.model';
import { SoldierTrainingReportMarineSuffixService } from './soldier-training-report-marine-suffix.service';

@Component({
    selector: 'mi-soldier-training-report-marine-suffix-delete-dialog',
    templateUrl: './soldier-training-report-marine-suffix-delete-dialog.component.html'
})
export class SoldierTrainingReportMarineSuffixDeleteDialogComponent {
    soldierTrainingReport: ISoldierTrainingReportMarineSuffix;

    constructor(
        protected soldierTrainingReportService: SoldierTrainingReportMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.soldierTrainingReportService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'soldierTrainingReportListModification',
                content: 'Deleted an soldierTrainingReport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-soldier-training-report-marine-suffix-delete-popup',
    template: ''
})
export class SoldierTrainingReportMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soldierTrainingReport }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SoldierTrainingReportMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.soldierTrainingReport = soldierTrainingReport;
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
