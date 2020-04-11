import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiPersonCriteriaMarineSuffix } from 'app/shared/model/niazsanji-person-criteria-marine-suffix.model';
import { NiazsanjiPersonCriteriaMarineSuffixService } from './niazsanji-person-criteria-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-person-criteria-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-person-criteria-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiPersonCriteriaMarineSuffixDeleteDialogComponent {
    niazsanjiPersonCriteria: INiazsanjiPersonCriteriaMarineSuffix;

    constructor(
        protected niazsanjiPersonCriteriaService: NiazsanjiPersonCriteriaMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiPersonCriteriaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiPersonCriteriaListModification',
                content: 'Deleted an niazsanjiPersonCriteria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-person-criteria-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiPersonCriteriaMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiPersonCriteria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiPersonCriteriaMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.niazsanjiPersonCriteria = niazsanjiPersonCriteria;
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
