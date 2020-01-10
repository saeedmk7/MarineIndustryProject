import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPriorityCriteriaValueMarineSuffix } from 'app/shared/model/priority-criteria-value-marine-suffix.model';
import { PriorityCriteriaValueMarineSuffixService } from './priority-criteria-value-marine-suffix.service';

@Component({
    selector: 'mi-priority-criteria-value-marine-suffix-delete-dialog',
    templateUrl: './priority-criteria-value-marine-suffix-delete-dialog.component.html'
})
export class PriorityCriteriaValueMarineSuffixDeleteDialogComponent {
    priorityCriteriaValue: IPriorityCriteriaValueMarineSuffix;

    constructor(
        protected priorityCriteriaValueService: PriorityCriteriaValueMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.priorityCriteriaValueService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'priorityCriteriaValueListModification',
                content: 'Deleted an priorityCriteriaValue'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-priority-criteria-value-marine-suffix-delete-popup',
    template: ''
})
export class PriorityCriteriaValueMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ priorityCriteriaValue }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PriorityCriteriaValueMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.priorityCriteriaValue = priorityCriteriaValue;
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
