import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubTaskMarineSuffix } from 'app/shared/model/sub-task-marine-suffix.model';
import { SubTaskMarineSuffixService } from './sub-task-marine-suffix.service';

@Component({
    selector: 'mi-sub-task-marine-suffix-delete-dialog',
    templateUrl: './sub-task-marine-suffix-delete-dialog.component.html'
})
export class SubTaskMarineSuffixDeleteDialogComponent {
    subTask: ISubTaskMarineSuffix;

    constructor(
        private subTaskService: SubTaskMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.subTaskService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'subTaskListModification',
                content: 'Deleted an subTask'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-sub-task-marine-suffix-delete-popup',
    template: ''
})
export class SubTaskMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ subTask }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SubTaskMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.subTask = subTask;
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
