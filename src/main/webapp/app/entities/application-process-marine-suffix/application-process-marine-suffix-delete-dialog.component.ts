import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApplicationProcessMarineSuffix } from 'app/shared/model/application-process-marine-suffix.model';
import { ApplicationProcessMarineSuffixService } from './application-process-marine-suffix.service';

@Component({
    selector: 'mi-application-process-marine-suffix-delete-dialog',
    templateUrl: './application-process-marine-suffix-delete-dialog.component.html'
})
export class ApplicationProcessMarineSuffixDeleteDialogComponent {
    applicationProcess: IApplicationProcessMarineSuffix;

    constructor(
        protected applicationProcessService: ApplicationProcessMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.applicationProcessService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'applicationProcessListModification',
                content: 'Deleted an applicationProcess'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-application-process-marine-suffix-delete-popup',
    template: ''
})
export class ApplicationProcessMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationProcess }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApplicationProcessMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.applicationProcess = applicationProcess;
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
