import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRestrictionMarineSuffix } from 'app/shared/model/restriction-marine-suffix.model';
import { RestrictionMarineSuffixService } from './restriction-marine-suffix.service';

@Component({
    selector: 'mi-restriction-marine-suffix-delete-dialog',
    templateUrl: './restriction-marine-suffix-delete-dialog.component.html'
})
export class RestrictionMarineSuffixDeleteDialogComponent {
    restriction: IRestrictionMarineSuffix;

    constructor(
        protected restrictionService: RestrictionMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.restrictionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'restrictionListModification',
                content: 'Deleted an restriction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-restriction-marine-suffix-delete-popup',
    template: ''
})
export class RestrictionMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ restriction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RestrictionMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.restriction = restriction;
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
