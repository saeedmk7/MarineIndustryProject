import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAcademicRankMarineSuffix } from 'app/shared/model/academic-rank-marine-suffix.model';
import { AcademicRankMarineSuffixService } from './academic-rank-marine-suffix.service';

@Component({
    selector: 'mi-academic-rank-marine-suffix-delete-dialog',
    templateUrl: './academic-rank-marine-suffix-delete-dialog.component.html'
})
export class AcademicRankMarineSuffixDeleteDialogComponent {
    academicRank: IAcademicRankMarineSuffix;

    constructor(
        private academicRankService: AcademicRankMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.academicRankService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'academicRankListModification',
                content: 'Deleted an academicRank'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-academic-rank-marine-suffix-delete-popup',
    template: ''
})
export class AcademicRankMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ academicRank }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AcademicRankMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.academicRank = academicRank;
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
