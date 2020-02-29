import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEvaluateCriteriaDataMarineSuffix } from 'app/shared/model/evaluate-criteria-data-marine-suffix.model';
import { EvaluateCriteriaDataMarineSuffixService } from './evaluate-criteria-data-marine-suffix.service';

@Component({
    selector: 'mi-evaluate-criteria-data-marine-suffix-delete-dialog',
    templateUrl: './evaluate-criteria-data-marine-suffix-delete-dialog.component.html'
})
export class EvaluateCriteriaDataMarineSuffixDeleteDialogComponent {
    evaluateCriteriaData: IEvaluateCriteriaDataMarineSuffix;

    constructor(
        protected evaluateCriteriaDataService: EvaluateCriteriaDataMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.evaluateCriteriaDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'evaluateCriteriaDataListModification',
                content: 'Deleted an evaluateCriteriaData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-evaluate-criteria-data-marine-suffix-delete-popup',
    template: ''
})
export class EvaluateCriteriaDataMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluateCriteriaData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EvaluateCriteriaDataMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.evaluateCriteriaData = evaluateCriteriaData;
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
