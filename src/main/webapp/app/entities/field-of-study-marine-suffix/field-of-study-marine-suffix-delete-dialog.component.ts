import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFieldOfStudyMarineSuffix } from 'app/shared/model/field-of-study-marine-suffix.model';
import { FieldOfStudyMarineSuffixService } from './field-of-study-marine-suffix.service';

@Component({
    selector: 'mi-field-of-study-marine-suffix-delete-dialog',
    templateUrl: './field-of-study-marine-suffix-delete-dialog.component.html'
})
export class FieldOfStudyMarineSuffixDeleteDialogComponent {
    fieldOfStudy: IFieldOfStudyMarineSuffix;

    constructor(
        private fieldOfStudyService: FieldOfStudyMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fieldOfStudyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fieldOfStudyListModification',
                content: 'Deleted an fieldOfStudy'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-field-of-study-marine-suffix-delete-popup',
    template: ''
})
export class FieldOfStudyMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fieldOfStudy }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FieldOfStudyMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fieldOfStudy = fieldOfStudy;
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
