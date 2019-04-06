import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiGroupMarineSuffix } from 'app/shared/model/niazsanji-group-marine-suffix.model';
import { NiazsanjiGroupMarineSuffixService } from './niazsanji-group-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-group-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-group-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiGroupMarineSuffixDeleteDialogComponent {
    niazsanjiGroup: INiazsanjiGroupMarineSuffix;

    constructor(
        private niazsanjiGroupService: NiazsanjiGroupMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiGroupListModification',
                content: 'Deleted an niazsanjiGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-group-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiGroupMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiGroupMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.niazsanjiGroup = niazsanjiGroup;
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
