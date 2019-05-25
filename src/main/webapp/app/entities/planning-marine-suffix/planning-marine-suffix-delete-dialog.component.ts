import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";


@Component({
    selector: 'mi-planning-marine-suffix-delete-dialog',
    templateUrl: './planning-marine-suffix-delete-dialog.component.html'
})
export class PlanningMarineSuffixDeleteDialogComponent {
    finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix;

    constructor(
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.finalNiazsanjiReportService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'finalNiazsanjiReportListModification',
                content: 'Deleted an finalNiazsanjiReport'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-planning-marine-suffix-delete-popup',
    template: ''
})
export class PlanningMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReport }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlanningMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.finalNiazsanjiReport = finalNiazsanjiReport;
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
