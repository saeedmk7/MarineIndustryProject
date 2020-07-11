import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReportGeneratorMarineSuffix } from 'app/shared/model/report-generator-marine-suffix.model';
import { ReportGeneratorMarineSuffixService } from './report-generator-marine-suffix.service';

@Component({
    selector: 'mi-report-generator-marine-suffix-delete-dialog',
    templateUrl: './report-generator-marine-suffix-delete-dialog.component.html'
})
export class ReportGeneratorMarineSuffixDeleteDialogComponent {
    reportGenerator: IReportGeneratorMarineSuffix;

    constructor(
        protected reportGeneratorService: ReportGeneratorMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.reportGeneratorService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'reportGeneratorListModification',
                content: 'Deleted an reportGenerator'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-report-generator-marine-suffix-delete-popup',
    template: ''
})
export class ReportGeneratorMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reportGenerator }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ReportGeneratorMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.reportGenerator = reportGenerator;
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
