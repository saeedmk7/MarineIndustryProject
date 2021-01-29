import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJobChangeMarineSuffix } from 'app/shared/model/job-change-marine-suffix.model';
import { JobChangeMarineSuffixService } from './job-change-marine-suffix.service';

@Component({
    selector: 'mi-job-change-marine-suffix-delete-dialog',
    templateUrl: './job-change-marine-suffix-delete-dialog.component.html'
})
export class JobChangeMarineSuffixDeleteDialogComponent {
    jobChange: IJobChangeMarineSuffix;

    constructor(
        protected jobChangeService: JobChangeMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobChangeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jobChangeListModification',
                content: 'Deleted an jobChange'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-job-change-marine-suffix-delete-popup',
    template: ''
})
export class JobChangeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobChange }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JobChangeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.jobChange = jobChange;
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
