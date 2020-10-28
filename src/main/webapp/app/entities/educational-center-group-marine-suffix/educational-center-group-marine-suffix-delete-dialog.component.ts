import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalCenterGroupMarineSuffix } from 'app/shared/model/educational-center-group-marine-suffix.model';
import { EducationalCenterGroupMarineSuffixService } from './educational-center-group-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-group-marine-suffix-delete-dialog',
    templateUrl: './educational-center-group-marine-suffix-delete-dialog.component.html'
})
export class EducationalCenterGroupMarineSuffixDeleteDialogComponent {
    educationalCenterGroup: IEducationalCenterGroupMarineSuffix;

    constructor(
        protected educationalCenterGroupService: EducationalCenterGroupMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalCenterGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalCenterGroupListModification',
                content: 'Deleted an educationalCenterGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-center-group-marine-suffix-delete-popup',
    template: ''
})
export class EducationalCenterGroupMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalCenterGroupMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.educationalCenterGroup = educationalCenterGroup;
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
