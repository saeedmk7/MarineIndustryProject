import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiIntegrationMarineSuffix } from 'app/shared/model/niazsanji-integration-marine-suffix.model';
import { NiazsanjiIntegrationMarineSuffixService } from './niazsanji-integration-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-integration-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-integration-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiIntegrationMarineSuffixDeleteDialogComponent {
    niazsanjiIntegration: INiazsanjiIntegrationMarineSuffix;

    constructor(
        protected niazsanjiIntegrationService: NiazsanjiIntegrationMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiIntegrationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiIntegrationListModification',
                content: 'Deleted an niazsanjiIntegration'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-integration-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiIntegrationMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiIntegration }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiIntegrationMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.niazsanjiIntegration = niazsanjiIntegration;
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
