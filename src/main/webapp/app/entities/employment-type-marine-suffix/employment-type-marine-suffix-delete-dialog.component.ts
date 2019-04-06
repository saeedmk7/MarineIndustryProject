import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { EmploymentTypeMarineSuffixService } from './employment-type-marine-suffix.service';

@Component({
    selector: 'mi-employment-type-marine-suffix-delete-dialog',
    templateUrl: './employment-type-marine-suffix-delete-dialog.component.html'
})
export class EmploymentTypeMarineSuffixDeleteDialogComponent {
    employmentType: IEmploymentTypeMarineSuffix;

    constructor(
        private employmentTypeService: EmploymentTypeMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.employmentTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'employmentTypeListModification',
                content: 'Deleted an employmentType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-employment-type-marine-suffix-delete-popup',
    template: ''
})
export class EmploymentTypeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ employmentType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EmploymentTypeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.employmentType = employmentType;
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
