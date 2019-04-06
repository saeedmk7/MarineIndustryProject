import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestNiazsanjiFardiMarineSuffix } from 'app/shared/model/request-niazsanji-fardi-marine-suffix.model';
import { RequestNiazsanjiFardiMarineSuffixService } from './request-niazsanji-fardi-marine-suffix.service';

@Component({
    selector: 'mi-request-niazsanji-fardi-marine-suffix-delete-dialog',
    templateUrl: './request-niazsanji-fardi-marine-suffix-delete-dialog.component.html'
})
export class RequestNiazsanjiFardiMarineSuffixDeleteDialogComponent {
    requestNiazsanjiFardi: IRequestNiazsanjiFardiMarineSuffix;

    constructor(
        protected requestNiazsanjiFardiService: RequestNiazsanjiFardiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.requestNiazsanjiFardiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'requestNiazsanjiFardiListModification',
                content: 'Deleted an requestNiazsanjiFardi'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-request-niazsanji-fardi-marine-suffix-delete-popup',
    template: ''
})
export class RequestNiazsanjiFardiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestNiazsanjiFardi }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RequestNiazsanjiFardiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.requestNiazsanjiFardi = requestNiazsanjiFardi;
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
