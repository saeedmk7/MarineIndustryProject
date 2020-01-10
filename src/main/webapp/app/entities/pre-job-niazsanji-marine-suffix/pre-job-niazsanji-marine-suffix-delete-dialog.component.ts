import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPreJobNiazsanjiMarineSuffix } from 'app/shared/model/pre-job-niazsanji-marine-suffix.model';
import { PreJobNiazsanjiMarineSuffixService } from './pre-job-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-pre-job-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './pre-job-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class PreJobNiazsanjiMarineSuffixDeleteDialogComponent {
    preJobNiazsanji: IPreJobNiazsanjiMarineSuffix;

    constructor(
        protected preJobNiazsanjiService: PreJobNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.preJobNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'preJobNiazsanjiListModification',
                content: 'Deleted an preJobNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-pre-job-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class PreJobNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ preJobNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PreJobNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.preJobNiazsanji = preJobNiazsanji;
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
