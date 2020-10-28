import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeacherCriteriaGroupMarineSuffix } from 'app/shared/model/teacher-criteria-group-marine-suffix.model';
import { TeacherCriteriaGroupMarineSuffixService } from './teacher-criteria-group-marine-suffix.service';

@Component({
    selector: 'mi-teacher-criteria-group-marine-suffix-delete-dialog',
    templateUrl: './teacher-criteria-group-marine-suffix-delete-dialog.component.html'
})
export class TeacherCriteriaGroupMarineSuffixDeleteDialogComponent {
    teacherCriteriaGroup: ITeacherCriteriaGroupMarineSuffix;

    constructor(
        protected teacherCriteriaGroupService: TeacherCriteriaGroupMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teacherCriteriaGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teacherCriteriaGroupListModification',
                content: 'Deleted an teacherCriteriaGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teacher-criteria-group-marine-suffix-delete-popup',
    template: ''
})
export class TeacherCriteriaGroupMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacherCriteriaGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeacherCriteriaGroupMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.teacherCriteriaGroup = teacherCriteriaGroup;
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
