import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalCenterCriteriaMarineSuffix } from 'app/shared/model/educational-center-criteria-marine-suffix.model';
import { EducationalCenterCriteriaMarineSuffixService } from './educational-center-criteria-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-criteria-marine-suffix-delete-dialog',
    templateUrl: './educational-center-criteria-marine-suffix-delete-dialog.component.html'
})
export class EducationalCenterCriteriaMarineSuffixDeleteDialogComponent {
    educationalCenterCriteria: IEducationalCenterCriteriaMarineSuffix;

    constructor(
        protected educationalCenterCriteriaService: EducationalCenterCriteriaMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalCenterCriteriaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalCenterCriteriaListModification',
                content: 'Deleted an educationalCenterCriteria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-center-criteria-marine-suffix-delete-popup',
    template: ''
})
export class EducationalCenterCriteriaMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterCriteria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalCenterCriteriaMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.educationalCenterCriteria = educationalCenterCriteria;
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
