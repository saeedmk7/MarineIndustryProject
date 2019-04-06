import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFinalNiazsanjiReportPersonMarineSuffix } from 'app/shared/model/final-niazsanji-report-person-marine-suffix.model';
import { FinalNiazsanjiReportPersonMarineSuffixService } from './final-niazsanji-report-person-marine-suffix.service';

@Component({
    selector: 'mi-final-niazsanji-report-person-marine-suffix-delete-dialog',
    templateUrl: './final-niazsanji-report-person-marine-suffix-delete-dialog.component.html'
})
export class FinalNiazsanjiReportPersonMarineSuffixDeleteDialogComponent {
    finalNiazsanjiReportPerson: IFinalNiazsanjiReportPersonMarineSuffix;

    constructor(
        private finalNiazsanjiReportPersonService: FinalNiazsanjiReportPersonMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.finalNiazsanjiReportPersonService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'finalNiazsanjiReportPersonListModification',
                content: 'Deleted an finalNiazsanjiReportPerson'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-final-niazsanji-report-person-marine-suffix-delete-popup',
    template: ''
})
export class FinalNiazsanjiReportPersonMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalNiazsanjiReportPerson }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FinalNiazsanjiReportPersonMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.finalNiazsanjiReportPerson = finalNiazsanjiReportPerson;
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
