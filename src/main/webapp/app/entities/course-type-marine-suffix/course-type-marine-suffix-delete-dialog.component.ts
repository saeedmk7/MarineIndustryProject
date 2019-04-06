import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { CourseTypeMarineSuffixService } from './course-type-marine-suffix.service';

@Component({
    selector: 'mi-course-type-marine-suffix-delete-dialog',
    templateUrl: './course-type-marine-suffix-delete-dialog.component.html'
})
export class CourseTypeMarineSuffixDeleteDialogComponent {
    courseType: ICourseTypeMarineSuffix;

    constructor(
        private courseTypeService: CourseTypeMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.courseTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'courseTypeListModification',
                content: 'Deleted an courseType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-course-type-marine-suffix-delete-popup',
    template: ''
})
export class CourseTypeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ courseType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CourseTypeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.courseType = courseType;
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
