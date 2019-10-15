import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResearchRecordMarineSuffix } from 'app/shared/model/research-record-marine-suffix.model';
import { ResearchRecordMarineSuffixService } from './research-record-marine-suffix.service';

@Component({
    selector: 'mi-research-record-marine-suffix-delete-dialog',
    templateUrl: './research-record-marine-suffix-delete-dialog.component.html'
})
export class ResearchRecordMarineSuffixDeleteDialogComponent {
    researchRecord: IResearchRecordMarineSuffix;

    constructor(
        protected researchRecordService: ResearchRecordMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.researchRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'researchRecordListModification',
                content: 'Deleted an researchRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-research-record-marine-suffix-delete-popup',
    template: ''
})
export class ResearchRecordMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ researchRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResearchRecordMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.researchRecord = researchRecord;
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
