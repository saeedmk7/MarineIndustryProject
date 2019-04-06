import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJobMarineSuffix } from 'app/shared/model/job-marine-suffix.model';
import { JobMarineSuffixService } from './job-marine-suffix.service';

@Component({
    selector: 'mi-job-marine-suffix-delete-dialog',
    templateUrl: './job-marine-suffix-delete-dialog.component.html'
})
export class JobMarineSuffixDeleteDialogComponent {
    job: IJobMarineSuffix;

    constructor(private jobService: JobMarineSuffixService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jobListModification',
                content: 'Deleted an job'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-job-marine-suffix-delete-popup',
    template: ''
})
export class JobMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ job }) => {

            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JobMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.job = job;
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
