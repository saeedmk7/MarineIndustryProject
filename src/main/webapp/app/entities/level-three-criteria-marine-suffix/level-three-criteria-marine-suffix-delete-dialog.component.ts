import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelThreeCriteriaMarineSuffix } from 'app/shared/model/level-three-criteria-marine-suffix.model';
import { LevelThreeCriteriaMarineSuffixService } from './level-three-criteria-marine-suffix.service';

@Component({
    selector: 'mi-level-three-criteria-marine-suffix-delete-dialog',
    templateUrl: './level-three-criteria-marine-suffix-delete-dialog.component.html'
})
export class LevelThreeCriteriaMarineSuffixDeleteDialogComponent {
    levelThreeCriteria: ILevelThreeCriteriaMarineSuffix;

    constructor(
        protected levelThreeCriteriaService: LevelThreeCriteriaMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.levelThreeCriteriaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'levelThreeCriteriaListModification',
                content: 'Deleted an levelThreeCriteria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-level-three-criteria-marine-suffix-delete-popup',
    template: ''
})
export class LevelThreeCriteriaMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeCriteria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LevelThreeCriteriaMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.levelThreeCriteria = levelThreeCriteria;
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
