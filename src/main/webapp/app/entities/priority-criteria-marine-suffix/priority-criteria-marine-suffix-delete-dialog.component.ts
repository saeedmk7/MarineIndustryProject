import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPriorityCriteriaMarineSuffix } from 'app/shared/model/priority-criteria-marine-suffix.model';
import { PriorityCriteriaMarineSuffixService } from './priority-criteria-marine-suffix.service';

@Component({
    selector: 'mi-priority-criteria-marine-suffix-delete-dialog',
    templateUrl: './priority-criteria-marine-suffix-delete-dialog.component.html'
})
export class PriorityCriteriaMarineSuffixDeleteDialogComponent {
    priorityCriteria: IPriorityCriteriaMarineSuffix;

    constructor(
        protected priorityCriteriaService: PriorityCriteriaMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.priorityCriteriaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'priorityCriteriaListModification',
                content: 'Deleted an priorityCriteria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-priority-criteria-marine-suffix-delete-popup',
    template: ''
})
export class PriorityCriteriaMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ priorityCriteria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PriorityCriteriaMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.priorityCriteria = priorityCriteria;
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
