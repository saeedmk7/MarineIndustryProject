import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWorkGroupMarineSuffix } from 'app/shared/model/work-group-marine-suffix.model';
import { WorkGroupMarineSuffixService } from './work-group-marine-suffix.service';

@Component({
    selector: 'mi-work-group-marine-suffix-delete-dialog',
    templateUrl: './work-group-marine-suffix-delete-dialog.component.html'
})
export class WorkGroupMarineSuffixDeleteDialogComponent {
    workGroup: IWorkGroupMarineSuffix;

    constructor(
        private workGroupService: WorkGroupMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.workGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'workGroupListModification',
                content: 'Deleted an workGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-work-group-marine-suffix-delete-popup',
    template: ''
})
export class WorkGroupMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ workGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(WorkGroupMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.workGroup = workGroup;
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
