import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalCenterServiceMarineSuffix } from 'app/shared/model/educational-center-service-marine-suffix.model';
import { EducationalCenterServiceMarineSuffixService } from './educational-center-service-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-service-marine-suffix-delete-dialog',
    templateUrl: './educational-center-service-marine-suffix-delete-dialog.component.html'
})
export class EducationalCenterServiceMarineSuffixDeleteDialogComponent {
    educationalCenterService: IEducationalCenterServiceMarineSuffix;

    constructor(
        protected educationalCenterServiceService: EducationalCenterServiceMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalCenterServiceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalCenterServiceListModification',
                content: 'Deleted an educationalCenterService'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-center-service-marine-suffix-delete-popup',
    template: ''
})
export class EducationalCenterServiceMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterService }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalCenterServiceMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.educationalCenterService = educationalCenterService;
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
