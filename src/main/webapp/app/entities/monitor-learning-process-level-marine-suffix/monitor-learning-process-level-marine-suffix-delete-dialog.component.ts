import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMonitorLearningProcessLevelMarineSuffix } from 'app/shared/model/monitor-learning-process-level-marine-suffix.model';
import { MonitorLearningProcessLevelMarineSuffixService } from './monitor-learning-process-level-marine-suffix.service';

@Component({
    selector: 'mi-monitor-learning-process-level-marine-suffix-delete-dialog',
    templateUrl: './monitor-learning-process-level-marine-suffix-delete-dialog.component.html'
})
export class MonitorLearningProcessLevelMarineSuffixDeleteDialogComponent {
    monitorLearningProcessLevel: IMonitorLearningProcessLevelMarineSuffix;

    constructor(
        protected monitorLearningProcessLevelService: MonitorLearningProcessLevelMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.monitorLearningProcessLevelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'monitorLearningProcessLevelListModification',
                content: 'Deleted an monitorLearningProcessLevel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-monitor-learning-process-level-marine-suffix-delete-popup',
    template: ''
})
export class MonitorLearningProcessLevelMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorLearningProcessLevel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MonitorLearningProcessLevelMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.monitorLearningProcessLevel = monitorLearningProcessLevel;
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
