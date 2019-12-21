import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMediaProductTypeMarineSuffix } from 'app/shared/model/media-product-type-marine-suffix.model';
import { MediaProductTypeMarineSuffixService } from './media-product-type-marine-suffix.service';

@Component({
    selector: 'mi-media-product-type-marine-suffix-delete-dialog',
    templateUrl: './media-product-type-marine-suffix-delete-dialog.component.html'
})
export class MediaProductTypeMarineSuffixDeleteDialogComponent {
    mediaProductType: IMediaProductTypeMarineSuffix;

    constructor(
        protected mediaProductTypeService: MediaProductTypeMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mediaProductTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mediaProductTypeListModification',
                content: 'Deleted an mediaProductType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-media-product-type-marine-suffix-delete-popup',
    template: ''
})
export class MediaProductTypeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mediaProductType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MediaProductTypeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.mediaProductType = mediaProductType;
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
