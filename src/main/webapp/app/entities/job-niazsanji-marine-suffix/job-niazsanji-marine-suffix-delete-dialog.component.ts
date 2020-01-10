import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJobNiazsanjiMarineSuffix } from 'app/shared/model/job-niazsanji-marine-suffix.model';
import { JobNiazsanjiMarineSuffixService } from './job-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-job-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './job-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class JobNiazsanjiMarineSuffixDeleteDialogComponent {
    jobNiazsanji: IJobNiazsanjiMarineSuffix;

    constructor(
        protected jobNiazsanjiService: JobNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jobNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jobNiazsanjiListModification',
                content: 'Deleted an jobNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-job-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class JobNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JobNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.jobNiazsanji = jobNiazsanji;
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
