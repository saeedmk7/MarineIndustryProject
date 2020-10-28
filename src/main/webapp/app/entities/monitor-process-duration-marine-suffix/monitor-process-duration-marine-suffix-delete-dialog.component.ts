import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMonitorProcessDurationMarineSuffix } from 'app/shared/model/monitor-process-duration-marine-suffix.model';
import { MonitorProcessDurationMarineSuffixService } from './monitor-process-duration-marine-suffix.service';

@Component({
    selector: 'mi-monitor-process-duration-marine-suffix-delete-dialog',
    templateUrl: './monitor-process-duration-marine-suffix-delete-dialog.component.html'
})
export class MonitorProcessDurationMarineSuffixDeleteDialogComponent {
    monitorProcessDuration: IMonitorProcessDurationMarineSuffix;

    constructor(
        protected monitorProcessDurationService: MonitorProcessDurationMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.monitorProcessDurationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'monitorProcessDurationListModification',
                content: 'Deleted an monitorProcessDuration'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-monitor-process-duration-marine-suffix-delete-popup',
    template: ''
})
export class MonitorProcessDurationMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorProcessDuration }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MonitorProcessDurationMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.monitorProcessDuration = monitorProcessDuration;
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
