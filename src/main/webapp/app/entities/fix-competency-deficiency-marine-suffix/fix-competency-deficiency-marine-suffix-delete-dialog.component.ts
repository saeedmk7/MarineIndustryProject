import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFixCompetencyDeficiencyMarineSuffix } from 'app/shared/model/fix-competency-deficiency-marine-suffix.model';
import { FixCompetencyDeficiencyMarineSuffixService } from './fix-competency-deficiency-marine-suffix.service';

@Component({
    selector: 'mi-fix-competency-deficiency-marine-suffix-delete-dialog',
    templateUrl: './fix-competency-deficiency-marine-suffix-delete-dialog.component.html'
})
export class FixCompetencyDeficiencyMarineSuffixDeleteDialogComponent {
    fixCompetencyDeficiency: IFixCompetencyDeficiencyMarineSuffix;

    constructor(
        protected fixCompetencyDeficiencyService: FixCompetencyDeficiencyMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fixCompetencyDeficiencyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fixCompetencyDeficiencyListModification',
                content: 'Deleted an fixCompetencyDeficiency'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-fix-competency-deficiency-marine-suffix-delete-popup',
    template: ''
})
export class FixCompetencyDeficiencyMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fixCompetencyDeficiency }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FixCompetencyDeficiencyMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.fixCompetencyDeficiency = fixCompetencyDeficiency;
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
