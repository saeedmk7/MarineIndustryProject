import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMonitorLearningProcessMarineSuffix } from 'app/shared/model/monitor-learning-process-marine-suffix.model';
import { MonitorLearningProcessMarineSuffixService } from './monitor-learning-process-marine-suffix.service';

@Component({
    selector: 'mi-monitor-learning-process-marine-suffix-delete-dialog',
    templateUrl: './monitor-learning-process-marine-suffix-delete-dialog.component.html'
})
export class MonitorLearningProcessMarineSuffixDeleteDialogComponent {
    monitorLearningProcess: IMonitorLearningProcessMarineSuffix;

    constructor(
        protected monitorLearningProcessService: MonitorLearningProcessMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.monitorLearningProcessService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'monitorLearningProcessListModification',
                content: 'Deleted an monitorLearningProcess'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-monitor-learning-process-marine-suffix-delete-popup',
    template: ''
})
export class MonitorLearningProcessMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorLearningProcess }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MonitorLearningProcessMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.monitorLearningProcess = monitorLearningProcess;
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
