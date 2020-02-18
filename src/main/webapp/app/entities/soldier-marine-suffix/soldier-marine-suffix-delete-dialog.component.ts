import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISoldierMarineSuffix } from 'app/shared/model/soldier-marine-suffix.model';
import { SoldierMarineSuffixService } from './soldier-marine-suffix.service';

@Component({
    selector: 'mi-soldier-marine-suffix-delete-dialog',
    templateUrl: './soldier-marine-suffix-delete-dialog.component.html'
})
export class SoldierMarineSuffixDeleteDialogComponent {
    soldier: ISoldierMarineSuffix;

    constructor(
        protected soldierService: SoldierMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.soldierService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'soldierListModification',
                content: 'Deleted an soldier'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-soldier-marine-suffix-delete-popup',
    template: ''
})
export class SoldierMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ soldier }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SoldierMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.soldier = soldier;
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
