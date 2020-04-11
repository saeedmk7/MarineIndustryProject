import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalCenterGradeScoreMarineSuffix } from 'app/shared/model/educational-center-grade-score-marine-suffix.model';
import { EducationalCenterGradeScoreMarineSuffixService } from './educational-center-grade-score-marine-suffix.service';

@Component({
    selector: 'mi-educational-center-grade-score-marine-suffix-delete-dialog',
    templateUrl: './educational-center-grade-score-marine-suffix-delete-dialog.component.html'
})
export class EducationalCenterGradeScoreMarineSuffixDeleteDialogComponent {
    educationalCenterGradeScore: IEducationalCenterGradeScoreMarineSuffix;

    constructor(
        protected educationalCenterGradeScoreService: EducationalCenterGradeScoreMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalCenterGradeScoreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalCenterGradeScoreListModification',
                content: 'Deleted an educationalCenterGradeScore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-center-grade-score-marine-suffix-delete-popup',
    template: ''
})
export class EducationalCenterGradeScoreMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalCenterGradeScore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalCenterGradeScoreMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.educationalCenterGradeScore = educationalCenterGradeScore;
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
