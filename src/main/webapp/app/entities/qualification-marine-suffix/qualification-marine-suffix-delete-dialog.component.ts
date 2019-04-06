import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQualificationMarineSuffix } from 'app/shared/model/qualification-marine-suffix.model';
import { QualificationMarineSuffixService } from './qualification-marine-suffix.service';

@Component({
    selector: 'mi-qualification-marine-suffix-delete-dialog',
    templateUrl: './qualification-marine-suffix-delete-dialog.component.html'
})
export class QualificationMarineSuffixDeleteDialogComponent {
    qualification: IQualificationMarineSuffix;

    constructor(
        private qualificationService: QualificationMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.qualificationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'qualificationListModification',
                content: 'Deleted an qualification'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-qualification-marine-suffix-delete-popup',
    template: ''
})
export class QualificationMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ qualification }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QualificationMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.qualification = qualification;
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
