import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWorkIndustryMarineSuffix } from 'app/shared/model/work-industry-marine-suffix.model';
import { WorkIndustryMarineSuffixService } from './work-industry-marine-suffix.service';

@Component({
    selector: 'mi-work-industry-marine-suffix-delete-dialog',
    templateUrl: './work-industry-marine-suffix-delete-dialog.component.html'
})
export class WorkIndustryMarineSuffixDeleteDialogComponent {
    workIndustry: IWorkIndustryMarineSuffix;

    constructor(
        private workIndustryService: WorkIndustryMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.workIndustryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'workIndustryListModification',
                content: 'Deleted an workIndustry'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-work-industry-marine-suffix-delete-popup',
    template: ''
})
export class WorkIndustryMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ workIndustry }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(WorkIndustryMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.workIndustry = workIndustry;
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
