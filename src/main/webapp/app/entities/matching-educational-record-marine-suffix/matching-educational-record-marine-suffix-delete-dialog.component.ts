import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMatchingEducationalRecordMarineSuffix } from 'app/shared/model/matching-educational-record-marine-suffix.model';
import { MatchingEducationalRecordMarineSuffixService } from './matching-educational-record-marine-suffix.service';

@Component({
    selector: 'mi-matching-educational-record-marine-suffix-delete-dialog',
    templateUrl: './matching-educational-record-marine-suffix-delete-dialog.component.html'
})
export class MatchingEducationalRecordMarineSuffixDeleteDialogComponent {
    matchingEducationalRecord: IMatchingEducationalRecordMarineSuffix;

    constructor(
        protected matchingEducationalRecordService: MatchingEducationalRecordMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.matchingEducationalRecordService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'matchingEducationalRecordListModification',
                content: 'Deleted an matchingEducationalRecord'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-matching-educational-record-marine-suffix-delete-popup',
    template: ''
})
export class MatchingEducationalRecordMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ matchingEducationalRecord }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MatchingEducationalRecordMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.matchingEducationalRecord = matchingEducationalRecord;
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
