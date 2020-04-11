import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeacherCriteriaMarineSuffix } from 'app/shared/model/teacher-criteria-marine-suffix.model';
import { TeacherCriteriaMarineSuffixService } from './teacher-criteria-marine-suffix.service';

@Component({
    selector: 'mi-teacher-criteria-marine-suffix-delete-dialog',
    templateUrl: './teacher-criteria-marine-suffix-delete-dialog.component.html'
})
export class TeacherCriteriaMarineSuffixDeleteDialogComponent {
    teacherCriteria: ITeacherCriteriaMarineSuffix;

    constructor(
        protected teacherCriteriaService: TeacherCriteriaMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teacherCriteriaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teacherCriteriaListModification',
                content: 'Deleted an teacherCriteria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teacher-criteria-marine-suffix-delete-popup',
    template: ''
})
export class TeacherCriteriaMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherCriteria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeacherCriteriaMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.teacherCriteria = teacherCriteria;
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
