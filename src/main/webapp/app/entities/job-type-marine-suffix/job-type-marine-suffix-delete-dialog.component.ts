import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJobTypeMarineSuffix } from 'app/shared/model/job-type-marine-suffix.model';
import { JobTypeMarineSuffixService } from './job-type-marine-suffix.service';

@Component({
    selector: 'mi-job-type-marine-suffix-delete-dialog',
    templateUrl: './job-type-marine-suffix-delete-dialog.component.html'
})
export class JobTypeMarineSuffixDeleteDialogComponent {
    jobType: IJobTypeMarineSuffix;

    constructor(
        private jobTypeService: JobTypeMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jobTypeListModification',
                content: 'Deleted an jobType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-job-type-marine-suffix-delete-popup',
    template: ''
})
export class JobTypeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JobTypeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.jobType = jobType;
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
