import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IActivityAreaMarineSuffix } from 'app/shared/model/activity-area-marine-suffix.model';
import { ActivityAreaMarineSuffixService } from './activity-area-marine-suffix.service';

@Component({
    selector: 'mi-activity-area-marine-suffix-delete-dialog',
    templateUrl: './activity-area-marine-suffix-delete-dialog.component.html'
})
export class ActivityAreaMarineSuffixDeleteDialogComponent {
    activityArea: IActivityAreaMarineSuffix;

    constructor(
        private activityAreaService: ActivityAreaMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.activityAreaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'activityAreaListModification',
                content: 'Deleted an activityArea'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-activity-area-marine-suffix-delete-popup',
    template: ''
})
export class ActivityAreaMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ activityArea }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ActivityAreaMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.activityArea = activityArea;
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
