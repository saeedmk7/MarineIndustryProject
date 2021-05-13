import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApplicationProcessStepMarineSuffix } from 'app/shared/model/application-process-step-marine-suffix.model';
import { ApplicationProcessStepMarineSuffixService } from './application-process-step-marine-suffix.service';

@Component({
    selector: 'mi-application-process-step-marine-suffix-delete-dialog',
    templateUrl: './application-process-step-marine-suffix-delete-dialog.component.html'
})
export class ApplicationProcessStepMarineSuffixDeleteDialogComponent {
    applicationProcessStep: IApplicationProcessStepMarineSuffix;

    constructor(
        protected applicationProcessStepService: ApplicationProcessStepMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.applicationProcessStepService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'applicationProcessStepListModification',
                content: 'Deleted an applicationProcessStep'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-application-process-step-marine-suffix-delete-popup',
    template: ''
})
export class ApplicationProcessStepMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationProcessStep }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApplicationProcessStepMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.applicationProcessStep = applicationProcessStep;
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
