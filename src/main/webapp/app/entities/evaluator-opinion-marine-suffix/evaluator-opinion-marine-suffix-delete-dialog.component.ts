import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEvaluatorOpinionMarineSuffix } from 'app/shared/model/evaluator-opinion-marine-suffix.model';
import { EvaluatorOpinionMarineSuffixService } from './evaluator-opinion-marine-suffix.service';

@Component({
    selector: 'mi-evaluator-opinion-marine-suffix-delete-dialog',
    templateUrl: './evaluator-opinion-marine-suffix-delete-dialog.component.html'
})
export class EvaluatorOpinionMarineSuffixDeleteDialogComponent {
    evaluatorOpinion: IEvaluatorOpinionMarineSuffix;

    constructor(
        protected evaluatorOpinionService: EvaluatorOpinionMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.evaluatorOpinionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'evaluatorOpinionListModification',
                content: 'Deleted an evaluatorOpinion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-evaluator-opinion-marine-suffix-delete-popup',
    template: ''
})
export class EvaluatorOpinionMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluatorOpinion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EvaluatorOpinionMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.evaluatorOpinion = evaluatorOpinion;
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
