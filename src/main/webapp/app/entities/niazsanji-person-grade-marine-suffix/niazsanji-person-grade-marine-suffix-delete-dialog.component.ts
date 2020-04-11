import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INiazsanjiPersonGradeMarineSuffix } from 'app/shared/model/niazsanji-person-grade-marine-suffix.model';
import { NiazsanjiPersonGradeMarineSuffixService } from './niazsanji-person-grade-marine-suffix.service';

@Component({
    selector: 'mi-niazsanji-person-grade-marine-suffix-delete-dialog',
    templateUrl: './niazsanji-person-grade-marine-suffix-delete-dialog.component.html'
})
export class NiazsanjiPersonGradeMarineSuffixDeleteDialogComponent {
    niazsanjiPersonGrade: INiazsanjiPersonGradeMarineSuffix;

    constructor(
        protected niazsanjiPersonGradeService: NiazsanjiPersonGradeMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.niazsanjiPersonGradeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'niazsanjiPersonGradeListModification',
                content: 'Deleted an niazsanjiPersonGrade'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-niazsanji-person-grade-marine-suffix-delete-popup',
    template: ''
})
export class NiazsanjiPersonGradeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ niazsanjiPersonGrade }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NiazsanjiPersonGradeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.niazsanjiPersonGrade = niazsanjiPersonGrade;
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
