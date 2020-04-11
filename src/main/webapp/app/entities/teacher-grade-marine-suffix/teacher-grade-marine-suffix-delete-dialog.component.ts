import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeacherGradeMarineSuffix } from 'app/shared/model/teacher-grade-marine-suffix.model';
import { TeacherGradeMarineSuffixService } from './teacher-grade-marine-suffix.service';

@Component({
    selector: 'mi-teacher-grade-marine-suffix-delete-dialog',
    templateUrl: './teacher-grade-marine-suffix-delete-dialog.component.html'
})
export class TeacherGradeMarineSuffixDeleteDialogComponent {
    teacherGrade: ITeacherGradeMarineSuffix;

    constructor(
        protected teacherGradeService: TeacherGradeMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teacherGradeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teacherGradeListModification',
                content: 'Deleted an teacherGrade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teacher-grade-marine-suffix-delete-popup',
    template: ''
})
export class TeacherGradeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherGrade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeacherGradeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.teacherGrade = teacherGrade;
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
