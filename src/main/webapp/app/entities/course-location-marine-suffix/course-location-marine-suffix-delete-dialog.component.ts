import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICourseLocationMarineSuffix } from 'app/shared/model/course-location-marine-suffix.model';
import { CourseLocationMarineSuffixService } from './course-location-marine-suffix.service';

@Component({
    selector: 'mi-course-location-marine-suffix-delete-dialog',
    templateUrl: './course-location-marine-suffix-delete-dialog.component.html'
})
export class CourseLocationMarineSuffixDeleteDialogComponent {
    courseLocation: ICourseLocationMarineSuffix;

    constructor(
        private courseLocationService: CourseLocationMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.courseLocationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'courseLocationListModification',
                content: 'Deleted an courseLocation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-course-location-marine-suffix-delete-popup',
    template: ''
})
export class CourseLocationMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ courseLocation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CourseLocationMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.courseLocation = courseLocation;
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
