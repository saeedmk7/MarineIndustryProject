import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICapitationMarineSuffix } from 'app/shared/model/capitation-marine-suffix.model';
import { CapitationMarineSuffixService } from './capitation-marine-suffix.service';

@Component({
    selector: 'mi-capitation-marine-suffix-delete-dialog',
    templateUrl: './capitation-marine-suffix-delete-dialog.component.html'
})
export class CapitationMarineSuffixDeleteDialogComponent {
    capitation: ICapitationMarineSuffix;

    constructor(
        protected capitationService: CapitationMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.capitationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'capitationListModification',
                content: 'Deleted an capitation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-capitation-marine-suffix-delete-popup',
    template: ''
})
export class CapitationMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ capitation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CapitationMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.capitation = capitation;
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
