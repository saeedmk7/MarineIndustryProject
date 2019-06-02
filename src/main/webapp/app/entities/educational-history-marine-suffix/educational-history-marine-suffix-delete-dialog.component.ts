import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalHistoryMarineSuffix } from 'app/shared/model/educational-history-marine-suffix.model';
import { EducationalHistoryMarineSuffixService } from './educational-history-marine-suffix.service';

@Component({
    selector: 'mi-educational-history-marine-suffix-delete-dialog',
    templateUrl: './educational-history-marine-suffix-delete-dialog.component.html'
})
export class EducationalHistoryMarineSuffixDeleteDialogComponent {
    educationalHistory: IEducationalHistoryMarineSuffix;

    constructor(
        protected educationalHistoryService: EducationalHistoryMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalHistoryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalHistoryListModification',
                content: 'Deleted an educationalHistory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-history-marine-suffix-delete-popup',
    template: ''
})
export class EducationalHistoryMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalHistory }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalHistoryMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.educationalHistory = educationalHistory;
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
