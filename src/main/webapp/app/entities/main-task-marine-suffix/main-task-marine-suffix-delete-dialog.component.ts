import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMainTaskMarineSuffix } from 'app/shared/model/main-task-marine-suffix.model';
import { MainTaskMarineSuffixService } from './main-task-marine-suffix.service';

@Component({
    selector: 'mi-main-task-marine-suffix-delete-dialog',
    templateUrl: './main-task-marine-suffix-delete-dialog.component.html'
})
export class MainTaskMarineSuffixDeleteDialogComponent {
    mainTask: IMainTaskMarineSuffix;

    constructor(
        private mainTaskService: MainTaskMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mainTaskService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mainTaskListModification',
                content: 'Deleted an mainTask'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-main-task-marine-suffix-delete-popup',
    template: ''
})
export class MainTaskMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mainTask }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MainTaskMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mainTask = mainTask;
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
