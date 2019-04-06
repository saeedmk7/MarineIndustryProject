import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWorkUnitMarineSuffix } from 'app/shared/model/work-unit-marine-suffix.model';
import { WorkUnitMarineSuffixService } from './work-unit-marine-suffix.service';

@Component({
    selector: 'mi-work-unit-marine-suffix-delete-dialog',
    templateUrl: './work-unit-marine-suffix-delete-dialog.component.html'
})
export class WorkUnitMarineSuffixDeleteDialogComponent {
    workUnit: IWorkUnitMarineSuffix;

    constructor(
        private workUnitService: WorkUnitMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.workUnitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'workUnitListModification',
                content: 'Deleted an workUnit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-work-unit-marine-suffix-delete-popup',
    template: ''
})
export class WorkUnitMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ workUnit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(WorkUnitMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.workUnit = workUnit;
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
