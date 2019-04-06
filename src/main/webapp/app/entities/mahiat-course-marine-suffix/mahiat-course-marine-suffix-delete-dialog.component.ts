import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMahiatCourseMarineSuffix } from 'app/shared/model/mahiat-course-marine-suffix.model';
import { MahiatCourseMarineSuffixService } from './mahiat-course-marine-suffix.service';

@Component({
    selector: 'mi-mahiat-course-marine-suffix-delete-dialog',
    templateUrl: './mahiat-course-marine-suffix-delete-dialog.component.html'
})
export class MahiatCourseMarineSuffixDeleteDialogComponent {
    mahiatCourse: IMahiatCourseMarineSuffix;

    constructor(
        private mahiatCourseService: MahiatCourseMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mahiatCourseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'mahiatCourseListModification',
                content: 'Deleted an mahiatCourse'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-mahiat-course-marine-suffix-delete-popup',
    template: ''
})
export class MahiatCourseMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ mahiatCourse }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MahiatCourseMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.mahiatCourse = mahiatCourse;
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
