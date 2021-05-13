import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApplicationProcessRunStepMarineSuffix } from 'app/shared/model/application-process-run-step-marine-suffix.model';
import { ApplicationProcessRunStepMarineSuffixService } from './application-process-run-step-marine-suffix.service';

@Component({
    selector: 'mi-application-process-run-step-marine-suffix-delete-dialog',
    templateUrl: './application-process-run-step-marine-suffix-delete-dialog.component.html'
})
export class ApplicationProcessRunStepMarineSuffixDeleteDialogComponent {
    applicationProcessRunStep: IApplicationProcessRunStepMarineSuffix;

    constructor(
        protected applicationProcessRunStepService: ApplicationProcessRunStepMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.applicationProcessRunStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'applicationProcessRunStepListModification',
                content: 'Deleted an applicationProcessRunStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-application-process-run-step-marine-suffix-delete-popup',
    template: ''
})
export class ApplicationProcessRunStepMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationProcessRunStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApplicationProcessRunStepMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.applicationProcessRunStep = applicationProcessRunStep;
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
