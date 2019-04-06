import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalCenterMarineSuffix } from 'app/shared/model/educational-center-marine-suffix.model';
import { EducationalCenterMarineSuffixService } from './educational-center-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-marine-suffix-delete-dialog',
    templateUrl: './educational-center-marine-suffix-delete-dialog.component.html'
})
export class EducationalCenterMarineSuffixDeleteDialogComponent {
    educationalCenter: IEducationalCenterMarineSuffix;

    constructor(
        private educationalCenterService: EducationalCenterMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalCenterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalCenterListModification',
                content: 'Deleted an educationalCenter'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-center-marine-suffix-delete-popup',
    template: ''
})
export class EducationalCenterMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenter }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalCenterMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.educationalCenter = educationalCenter;
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
