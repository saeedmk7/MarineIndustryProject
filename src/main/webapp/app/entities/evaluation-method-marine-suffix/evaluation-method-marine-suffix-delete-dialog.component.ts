import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEvaluationMethodMarineSuffix } from 'app/shared/model/evaluation-method-marine-suffix.model';
import { EvaluationMethodMarineSuffixService } from './evaluation-method-marine-suffix.service';

@Component({
    selector: 'mi-evaluation-method-marine-suffix-delete-dialog',
    templateUrl: './evaluation-method-marine-suffix-delete-dialog.component.html'
})
export class EvaluationMethodMarineSuffixDeleteDialogComponent {
    evaluationMethod: IEvaluationMethodMarineSuffix;

    constructor(
        private evaluationMethodService: EvaluationMethodMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.evaluationMethodService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'evaluationMethodListModification',
                content: 'Deleted an evaluationMethod'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-evaluation-method-marine-suffix-delete-popup',
    template: ''
})
export class EvaluationMethodMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ evaluationMethod }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EvaluationMethodMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.evaluationMethod = evaluationMethod;
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
