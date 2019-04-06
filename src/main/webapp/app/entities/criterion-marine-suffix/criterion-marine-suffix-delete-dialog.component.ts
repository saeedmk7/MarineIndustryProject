import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICriterionMarineSuffix } from 'app/shared/model/criterion-marine-suffix.model';
import { CriterionMarineSuffixService } from './criterion-marine-suffix.service';

@Component({
    selector: 'mi-criterion-marine-suffix-delete-dialog',
    templateUrl: './criterion-marine-suffix-delete-dialog.component.html'
})
export class CriterionMarineSuffixDeleteDialogComponent {
    criterion: ICriterionMarineSuffix;

    constructor(
        private criterionService: CriterionMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.criterionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'criterionListModification',
                content: 'Deleted an criterion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-criterion-marine-suffix-delete-popup',
    template: ''
})
export class CriterionMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ criterion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CriterionMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.criterion = criterion;
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
