import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReportGeneratorAuthorityMarineSuffix } from 'app/shared/model/report-generator-authority-marine-suffix.model';
import { ReportGeneratorAuthorityMarineSuffixService } from './report-generator-authority-marine-suffix.service';

@Component({
    selector: 'mi-report-generator-authority-marine-suffix-delete-dialog',
    templateUrl: './report-generator-authority-marine-suffix-delete-dialog.component.html'
})
export class ReportGeneratorAuthorityMarineSuffixDeleteDialogComponent {
    reportGeneratorAuthority: IReportGeneratorAuthorityMarineSuffix;

    constructor(
        protected reportGeneratorAuthorityService: ReportGeneratorAuthorityMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.reportGeneratorAuthorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'reportGeneratorAuthorityListModification',
                content: 'Deleted an reportGeneratorAuthority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-report-generator-authority-marine-suffix-delete-popup',
    template: ''
})
export class ReportGeneratorAuthorityMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reportGeneratorAuthority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ReportGeneratorAuthorityMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.reportGeneratorAuthority = reportGeneratorAuthority;
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
