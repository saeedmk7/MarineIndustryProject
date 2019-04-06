import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRasteMarineSuffix } from 'app/shared/model/raste-marine-suffix.model';
import { RasteMarineSuffixService } from './raste-marine-suffix.service';

@Component({
    selector: 'mi-raste-marine-suffix-delete-dialog',
    templateUrl: './raste-marine-suffix-delete-dialog.component.html'
})
export class RasteMarineSuffixDeleteDialogComponent {
    raste: IRasteMarineSuffix;

    constructor(
        private rasteService: RasteMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.rasteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'rasteListModification',
                content: 'Deleted an raste'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-raste-marine-suffix-delete-popup',
    template: ''
})
export class RasteMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ raste }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RasteMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.raste = raste;
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
