import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiPersonGradeScoreMarineSuffix } from 'app/shared/model/niazsanji-person-grade-score-marine-suffix.model';
import { NiazsanjiPersonGradeScoreMarineSuffixService } from './niazsanji-person-grade-score-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-person-grade-score-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-person-grade-score-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiPersonGradeScoreMarineSuffixDeleteDialogComponent {
    niazsanjiPersonGradeScore: INiazsanjiPersonGradeScoreMarineSuffix;

    constructor(
        protected niazsanjiPersonGradeScoreService: NiazsanjiPersonGradeScoreMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiPersonGradeScoreService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiPersonGradeScoreListModification',
                content: 'Deleted an niazsanjiPersonGradeScore'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-person-grade-score-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiPersonGradeScoreMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiPersonGradeScore }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiPersonGradeScoreMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.niazsanjiPersonGradeScore = niazsanjiPersonGradeScore;
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
