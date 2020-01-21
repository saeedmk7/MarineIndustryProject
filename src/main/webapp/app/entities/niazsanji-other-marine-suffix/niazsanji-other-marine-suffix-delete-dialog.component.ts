import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiOtherMarineSuffix } from 'app/shared/model/niazsanji-other-marine-suffix.model';
import { NiazsanjiOtherMarineSuffixService } from './niazsanji-other-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-other-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-other-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiOtherMarineSuffixDeleteDialogComponent {
    niazsanjiOther: INiazsanjiOtherMarineSuffix;

    constructor(
        protected niazsanjiOtherService: NiazsanjiOtherMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiOtherService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiOtherListModification',
                content: 'Deleted an niazsanjiOther'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-other-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiOtherMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiOther }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiOtherMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.niazsanjiOther = niazsanjiOther;
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
