import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeachingApproachMarineSuffix } from 'app/shared/model/teaching-approach-marine-suffix.model';
import { TeachingApproachMarineSuffixService } from './teaching-approach-marine-suffix.service';

@Component({
    selector: 'mi-teaching-approach-marine-suffix-delete-dialog',
    templateUrl: './teaching-approach-marine-suffix-delete-dialog.component.html'
})
export class TeachingApproachMarineSuffixDeleteDialogComponent {
    teachingApproach: ITeachingApproachMarineSuffix;

    constructor(
        private teachingApproachService: TeachingApproachMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teachingApproachService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teachingApproachListModification',
                content: 'Deleted an teachingApproach'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teaching-approach-marine-suffix-delete-popup',
    template: ''
})
export class TeachingApproachMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachingApproach }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeachingApproachMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.teachingApproach = teachingApproach;
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
