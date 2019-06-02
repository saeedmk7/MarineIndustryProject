import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJamHelpAuthorityMarineSuffix } from 'app/shared/model/jam-help-authority-marine-suffix.model';
import { JamHelpAuthorityMarineSuffixService } from './jam-help-authority-marine-suffix.service';

@Component({
    selector: 'mi-jam-help-authority-marine-suffix-delete-dialog',
    templateUrl: './jam-help-authority-marine-suffix-delete-dialog.component.html'
})
export class JamHelpAuthorityMarineSuffixDeleteDialogComponent {
    jamHelpAuthority: IJamHelpAuthorityMarineSuffix;

    constructor(
        protected jamHelpAuthorityService: JamHelpAuthorityMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jamHelpAuthorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jamHelpAuthorityListModification',
                content: 'Deleted an jamHelpAuthority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-jam-help-authority-marine-suffix-delete-popup',
    template: ''
})
export class JamHelpAuthorityMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jamHelpAuthority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JamHelpAuthorityMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.jamHelpAuthority = jamHelpAuthority;
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
