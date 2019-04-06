import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEffectivenessIndexMarineSuffix } from 'app/shared/model/effectiveness-index-marine-suffix.model';
import { EffectivenessIndexMarineSuffixService } from './effectiveness-index-marine-suffix.service';

@Component({
    selector: 'mi-effectiveness-index-marine-suffix-delete-dialog',
    templateUrl: './effectiveness-index-marine-suffix-delete-dialog.component.html'
})
export class EffectivenessIndexMarineSuffixDeleteDialogComponent {
    effectivenessIndex: IEffectivenessIndexMarineSuffix;

    constructor(
        private effectivenessIndexService: EffectivenessIndexMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.effectivenessIndexService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'effectivenessIndexListModification',
                content: 'Deleted an effectivenessIndex'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-effectiveness-index-marine-suffix-delete-popup',
    template: ''
})
export class EffectivenessIndexMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessIndex }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EffectivenessIndexMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.effectivenessIndex = effectivenessIndex;
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
