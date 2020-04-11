import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalCenterGradeMarineSuffix } from 'app/shared/model/educational-center-grade-marine-suffix.model';
import { EducationalCenterGradeMarineSuffixService } from './educational-center-grade-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-grade-marine-suffix-delete-dialog',
    templateUrl: './educational-center-grade-marine-suffix-delete-dialog.component.html'
})
export class EducationalCenterGradeMarineSuffixDeleteDialogComponent {
    educationalCenterGrade: IEducationalCenterGradeMarineSuffix;

    constructor(
        protected educationalCenterGradeService: EducationalCenterGradeMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalCenterGradeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalCenterGradeListModification',
                content: 'Deleted an educationalCenterGrade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-center-grade-marine-suffix-delete-popup',
    template: ''
})
export class EducationalCenterGradeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterGrade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalCenterGradeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.educationalCenterGrade = educationalCenterGrade;
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
