import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeacherMarineSuffix } from 'app/shared/model/teacher-marine-suffix.model';
import { TeacherMarineSuffixService } from './teacher-marine-suffix.service';

@Component({
    selector: 'mi-teacher-marine-suffix-delete-dialog',
    templateUrl: './teacher-marine-suffix-delete-dialog.component.html'
})
export class TeacherMarineSuffixDeleteDialogComponent {
    teacher: ITeacherMarineSuffix;

    constructor(
        private teacherService: TeacherMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teacherService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teacherListModification',
                content: 'Deleted an teacher'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teacher-marine-suffix-delete-popup',
    template: ''
})
export class TeacherMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teacher }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeacherMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.teacher = teacher;
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
