import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeachingRecordMarineSuffix } from 'app/shared/model/teaching-record-marine-suffix.model';
import { TeachingRecordMarineSuffixService } from './teaching-record-marine-suffix.service';

@Component({
    selector: 'mi-teaching-record-marine-suffix-delete-dialog',
    templateUrl: './teaching-record-marine-suffix-delete-dialog.component.html'
})
export class TeachingRecordMarineSuffixDeleteDialogComponent {
    teachingRecord: ITeachingRecordMarineSuffix;

    constructor(
        protected teachingRecordService: TeachingRecordMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teachingRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teachingRecordListModification',
                content: 'Deleted an teachingRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teaching-record-marine-suffix-delete-popup',
    template: ''
})
export class TeachingRecordMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachingRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeachingRecordMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.teachingRecord = teachingRecord;
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
