import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActivationNiazsanjiMarineSuffix } from 'app/shared/model/activation-niazsanji-marine-suffix.model';
import { ActivationNiazsanjiMarineSuffixService } from './activation-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-activation-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './activation-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class ActivationNiazsanjiMarineSuffixDeleteDialogComponent {
    activationNiazsanji: IActivationNiazsanjiMarineSuffix;

    constructor(
        protected activationNiazsanjiService: ActivationNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.activationNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'activationNiazsanjiListModification',
                content: 'Deleted an activationNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-activation-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class ActivationNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ activationNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ActivationNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.activationNiazsanji = activationNiazsanji;
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
