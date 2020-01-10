import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDesignNiazsanjiMarineSuffix } from 'app/shared/model/design-niazsanji-marine-suffix.model';
import { DesignNiazsanjiMarineSuffixService } from './design-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-design-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './design-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class DesignNiazsanjiMarineSuffixDeleteDialogComponent {
    designNiazsanji: IDesignNiazsanjiMarineSuffix;

    constructor(
        protected designNiazsanjiService: DesignNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.designNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'designNiazsanjiListModification',
                content: 'Deleted an designNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-design-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class DesignNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ designNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DesignNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.designNiazsanji = designNiazsanji;
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
