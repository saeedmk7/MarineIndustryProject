import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPreJobNiazsanjiCompetencyMarineSuffix } from 'app/shared/model/pre-job-niazsanji-competency-marine-suffix.model';
import { PreJobNiazsanjiCompetencyMarineSuffixService } from './pre-job-niazsanji-competency-marine-suffix.service';

@Component({
    selector: 'mi-pre-job-niazsanji-competency-marine-suffix-delete-dialog',
    templateUrl: './pre-job-niazsanji-competency-marine-suffix-delete-dialog.component.html'
})
export class PreJobNiazsanjiCompetencyMarineSuffixDeleteDialogComponent {
    preJobNiazsanjiCompetency: IPreJobNiazsanjiCompetencyMarineSuffix;

    constructor(
        protected preJobNiazsanjiCompetencyService: PreJobNiazsanjiCompetencyMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.preJobNiazsanjiCompetencyService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'preJobNiazsanjiCompetencyListModification',
                content: 'Deleted an preJobNiazsanjiCompetency'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-pre-job-niazsanji-competency-marine-suffix-delete-popup',
    template: ''
})
export class PreJobNiazsanjiCompetencyMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ preJobNiazsanjiCompetency }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PreJobNiazsanjiCompetencyMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.preJobNiazsanjiCompetency = preJobNiazsanjiCompetency;
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
