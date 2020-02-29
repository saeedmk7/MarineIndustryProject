import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEvaluateCriteriaTrainingMarineSuffix } from 'app/shared/model/evaluate-criteria-training-marine-suffix.model';
import { EvaluateCriteriaTrainingMarineSuffixService } from './evaluate-criteria-training-marine-suffix.service';

@Component({
    selector: 'mi-evaluate-criteria-training-marine-suffix-delete-dialog',
    templateUrl: './evaluate-criteria-training-marine-suffix-delete-dialog.component.html'
})
export class EvaluateCriteriaTrainingMarineSuffixDeleteDialogComponent {
    evaluateCriteriaTraining: IEvaluateCriteriaTrainingMarineSuffix;

    constructor(
        protected evaluateCriteriaTrainingService: EvaluateCriteriaTrainingMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.evaluateCriteriaTrainingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'evaluateCriteriaTrainingListModification',
                content: 'Deleted an evaluateCriteriaTraining'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-evaluate-criteria-training-marine-suffix-delete-popup',
    template: ''
})
export class EvaluateCriteriaTrainingMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluateCriteriaTraining }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EvaluateCriteriaTrainingMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.evaluateCriteriaTraining = evaluateCriteriaTraining;
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
