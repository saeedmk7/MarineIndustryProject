import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrioritizeRequestNiazsanjiMarineSuffix } from 'app/shared/model/prioritize-request-niazsanji-marine-suffix.model';
import { PrioritizeRequestNiazsanjiMarineSuffixService } from './prioritize-request-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './prioritize-request-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class PrioritizeRequestNiazsanjiMarineSuffixDeleteDialogComponent {
    prioritizeRequestNiazsanji: IPrioritizeRequestNiazsanjiMarineSuffix;

    constructor(
        protected prioritizeRequestNiazsanjiService: PrioritizeRequestNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.prioritizeRequestNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'prioritizeRequestNiazsanjiListModification',
                content: 'Deleted an prioritizeRequestNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-prioritize-request-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class PrioritizeRequestNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ prioritizeRequestNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PrioritizeRequestNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.prioritizeRequestNiazsanji = prioritizeRequestNiazsanji;
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
