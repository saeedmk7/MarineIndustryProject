import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeachApproachMarineSuffix } from 'app/shared/model/teach-approach-marine-suffix.model';
import { TeachApproachMarineSuffixService } from './teach-approach-marine-suffix.service';

@Component({
    selector: 'mi-teach-approach-marine-suffix-delete-dialog',
    templateUrl: './teach-approach-marine-suffix-delete-dialog.component.html'
})
export class TeachApproachMarineSuffixDeleteDialogComponent {
    teachApproach: ITeachApproachMarineSuffix;

    constructor(
        private teachApproachService: TeachApproachMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teachApproachService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teachApproachListModification',
                content: 'Deleted an teachApproach'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teach-approach-marine-suffix-delete-popup',
    template: ''
})
export class TeachApproachMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachApproach }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeachApproachMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.teachApproach = teachApproach;
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
