import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiInputMarineSuffix } from 'app/shared/model/niazsanji-input-marine-suffix.model';
import { NiazsanjiInputMarineSuffixService } from './niazsanji-input-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-input-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-input-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiInputMarineSuffixDeleteDialogComponent {
    niazsanjiInput: INiazsanjiInputMarineSuffix;

    constructor(
        protected niazsanjiInputService: NiazsanjiInputMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiInputService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiInputListModification',
                content: 'Deleted an niazsanjiInput'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-input-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiInputMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiInput }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiInputMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.niazsanjiInput = niazsanjiInput;
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
