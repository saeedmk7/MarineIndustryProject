import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJamHelpMarineSuffix } from 'app/shared/model/jam-help-marine-suffix.model';
import { JamHelpMarineSuffixService } from './jam-help-marine-suffix.service';

@Component({
    selector: 'mi-jam-help-marine-suffix-delete-dialog',
    templateUrl: './jam-help-marine-suffix-delete-dialog.component.html'
})
export class JamHelpMarineSuffixDeleteDialogComponent {
    jamHelp: IJamHelpMarineSuffix;

    constructor(
        protected jamHelpService: JamHelpMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jamHelpService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jamHelpListModification',
                content: 'Deleted an jamHelp'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-jam-help-marine-suffix-delete-popup',
    template: ''
})
export class JamHelpMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jamHelp }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JamHelpMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.jamHelp = jamHelp;
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
