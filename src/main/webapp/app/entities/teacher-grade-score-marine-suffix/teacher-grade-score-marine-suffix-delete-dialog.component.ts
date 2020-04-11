import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeacherGradeScoreMarineSuffix } from 'app/shared/model/teacher-grade-score-marine-suffix.model';
import { TeacherGradeScoreMarineSuffixService } from './teacher-grade-score-marine-suffix.service';

@Component({
    selector: 'mi-teacher-grade-score-marine-suffix-delete-dialog',
    templateUrl: './teacher-grade-score-marine-suffix-delete-dialog.component.html'
})
export class TeacherGradeScoreMarineSuffixDeleteDialogComponent {
    teacherGradeScore: ITeacherGradeScoreMarineSuffix;

    constructor(
        protected teacherGradeScoreService: TeacherGradeScoreMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teacherGradeScoreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teacherGradeScoreListModification',
                content: 'Deleted an teacherGradeScore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teacher-grade-score-marine-suffix-delete-popup',
    template: ''
})
export class TeacherGradeScoreMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherGradeScore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeacherGradeScoreMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.teacherGradeScore = teacherGradeScore;
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
