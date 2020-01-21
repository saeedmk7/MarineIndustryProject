import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestOtherNiazsanjiMarineSuffix } from 'app/shared/model/request-other-niazsanji-marine-suffix.model';
import { RequestOtherNiazsanjiMarineSuffixService } from './request-other-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-request-other-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './request-other-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class RequestOtherNiazsanjiMarineSuffixDeleteDialogComponent {
    requestOtherNiazsanji: IRequestOtherNiazsanjiMarineSuffix;

    constructor(
        protected requestOtherNiazsanjiService: RequestOtherNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.requestOtherNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'requestOtherNiazsanjiListModification',
                content: 'Deleted an requestOtherNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-request-other-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class RequestOtherNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestOtherNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RequestOtherNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.requestOtherNiazsanji = requestOtherNiazsanji;
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
