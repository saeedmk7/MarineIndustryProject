import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMonitorLearningProcessGradeMarineSuffix } from 'app/shared/model/monitor-learning-process-grade-marine-suffix.model';
import { MonitorLearningProcessGradeMarineSuffixService } from './monitor-learning-process-grade-marine-suffix.service';

@Component({
    selector: 'mi-monitor-learning-process-grade-marine-suffix-delete-dialog',
    templateUrl: './monitor-learning-process-grade-marine-suffix-delete-dialog.component.html'
})
export class MonitorLearningProcessGradeMarineSuffixDeleteDialogComponent {
    monitorLearningProcessGrade: IMonitorLearningProcessGradeMarineSuffix;

    constructor(
        protected monitorLearningProcessGradeService: MonitorLearningProcessGradeMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.monitorLearningProcessGradeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'monitorLearningProcessGradeListModification',
                content: 'Deleted an monitorLearningProcessGrade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-monitor-learning-process-grade-marine-suffix-delete-popup',
    template: ''
})
export class MonitorLearningProcessGradeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ monitorLearningProcessGrade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MonitorLearningProcessGradeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.monitorLearningProcessGrade = monitorLearningProcessGrade;
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
