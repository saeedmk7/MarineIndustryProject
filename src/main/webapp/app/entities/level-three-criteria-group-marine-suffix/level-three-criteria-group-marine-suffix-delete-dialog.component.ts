import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILevelThreeCriteriaGroupMarineSuffix } from 'app/shared/model/level-three-criteria-group-marine-suffix.model';
import { LevelThreeCriteriaGroupMarineSuffixService } from './level-three-criteria-group-marine-suffix.service';

@Component({
    selector: 'mi-level-three-criteria-group-marine-suffix-delete-dialog',
    templateUrl: './level-three-criteria-group-marine-suffix-delete-dialog.component.html'
})
export class LevelThreeCriteriaGroupMarineSuffixDeleteDialogComponent {
    levelThreeCriteriaGroup: ILevelThreeCriteriaGroupMarineSuffix;

    constructor(
        protected levelThreeCriteriaGroupService: LevelThreeCriteriaGroupMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.levelThreeCriteriaGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'levelThreeCriteriaGroupListModification',
                content: 'Deleted an levelThreeCriteriaGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-level-three-criteria-group-marine-suffix-delete-popup',
    template: ''
})
export class LevelThreeCriteriaGroupMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ levelThreeCriteriaGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LevelThreeCriteriaGroupMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.levelThreeCriteriaGroup = levelThreeCriteriaGroup;
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
