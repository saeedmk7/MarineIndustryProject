import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompetencyMarineSuffix } from 'app/shared/model/competency-marine-suffix.model';
import { CompetencyMarineSuffixService } from './competency-marine-suffix.service';

@Component({
    selector: 'mi-competency-marine-suffix-delete-dialog',
    templateUrl: './competency-marine-suffix-delete-dialog.component.html'
})
export class CompetencyMarineSuffixDeleteDialogComponent {
    competency: ICompetencyMarineSuffix;

    constructor(
        protected competencyService: CompetencyMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.competencyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'competencyListModification',
                content: 'Deleted an competency'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-competency-marine-suffix-delete-popup',
    template: ''
})
export class CompetencyMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ competency }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CompetencyMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.competency = competency;
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
