import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJobRecordMarineSuffix } from 'app/shared/model/job-record-marine-suffix.model';
import { JobRecordMarineSuffixService } from './job-record-marine-suffix.service';

@Component({
    selector: 'mi-job-record-marine-suffix-delete-dialog',
    templateUrl: './job-record-marine-suffix-delete-dialog.component.html'
})
export class JobRecordMarineSuffixDeleteDialogComponent {
    jobRecord: IJobRecordMarineSuffix;

    constructor(
        protected jobRecordService: JobRecordMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jobRecordListModification',
                content: 'Deleted an jobRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-job-record-marine-suffix-delete-popup',
    template: ''
})
export class JobRecordMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JobRecordMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.jobRecord = jobRecord;
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
