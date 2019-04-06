import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFinalOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/final-organization-niazsanji-marine-suffix.model';
import { FinalOrganizationNiazsanjiMarineSuffixService } from './final-organization-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-final-organization-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './final-organization-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class FinalOrganizationNiazsanjiMarineSuffixDeleteDialogComponent {
    finalOrganizationNiazsanji: IFinalOrganizationNiazsanjiMarineSuffix;

    constructor(
        private finalOrganizationNiazsanjiService: FinalOrganizationNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.finalOrganizationNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'finalOrganizationNiazsanjiListModification',
                content: 'Deleted an finalOrganizationNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-final-organization-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class FinalOrganizationNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ finalOrganizationNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FinalOrganizationNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.finalOrganizationNiazsanji = finalOrganizationNiazsanji;
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
