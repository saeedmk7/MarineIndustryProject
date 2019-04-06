import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiFardiMarineSuffix } from 'app/shared/model/niazsanji-fardi-marine-suffix.model';
import { NiazsanjiFardiMarineSuffixService } from './niazsanji-fardi-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-fardi-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-fardi-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiFardiMarineSuffixDeleteDialogComponent {
    niazsanjiFardi: INiazsanjiFardiMarineSuffix;

    constructor(
        protected niazsanjiFardiService: NiazsanjiFardiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiFardiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiFardiListModification',
                content: 'Deleted an niazsanjiFardi'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-fardi-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiFardiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiFardi }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiFardiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.niazsanjiFardi = niazsanjiFardi;
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
