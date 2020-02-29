import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssessmentMethodMarineSuffix } from 'app/shared/model/assessment-method-marine-suffix.model';
import { AssessmentMethodMarineSuffixService } from './assessment-method-marine-suffix.service';

@Component({
    selector: 'mi-assessment-method-marine-suffix-delete-dialog',
    templateUrl: './assessment-method-marine-suffix-delete-dialog.component.html'
})
export class AssessmentMethodMarineSuffixDeleteDialogComponent {
    assessmentMethod: IAssessmentMethodMarineSuffix;

    constructor(
        protected assessmentMethodService: AssessmentMethodMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.assessmentMethodService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'assessmentMethodListModification',
                content: 'Deleted an assessmentMethod'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-assessment-method-marine-suffix-delete-popup',
    template: ''
})
export class AssessmentMethodMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ assessmentMethod }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AssessmentMethodMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.assessmentMethod = assessmentMethod;
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
