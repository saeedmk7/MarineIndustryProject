import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDesignAndPlanningMarineSuffix } from 'app/shared/model/design-and-planning-marine-suffix.model';
import { DesignAndPlanningMarineSuffixService } from './design-and-planning-marine-suffix.service';

@Component({
    selector: 'mi-design-and-planning-marine-suffix-delete-dialog',
    templateUrl: './design-and-planning-marine-suffix-delete-dialog.component.html'
})
export class DesignAndPlanningMarineSuffixDeleteDialogComponent {
    designAndPlanning: IDesignAndPlanningMarineSuffix;

    constructor(
        private designAndPlanningService: DesignAndPlanningMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.designAndPlanningService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'designAndPlanningListModification',
                content: 'Deleted an designAndPlanning'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-design-and-planning-marine-suffix-delete-popup',
    template: ''
})
export class DesignAndPlanningMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ designAndPlanning }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DesignAndPlanningMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.designAndPlanning = designAndPlanning;
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
