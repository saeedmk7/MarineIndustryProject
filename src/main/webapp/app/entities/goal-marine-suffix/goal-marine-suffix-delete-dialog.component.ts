import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGoalMarineSuffix } from 'app/shared/model/goal-marine-suffix.model';
import { GoalMarineSuffixService } from './goal-marine-suffix.service';

@Component({
    selector: 'mi-goal-marine-suffix-delete-dialog',
    templateUrl: './goal-marine-suffix-delete-dialog.component.html'
})
export class GoalMarineSuffixDeleteDialogComponent {
    goal: IGoalMarineSuffix;

    constructor(private goalService: GoalMarineSuffixService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.goalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'goalListModification',
                content: 'Deleted an goal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-goal-marine-suffix-delete-popup',
    template: ''
})
export class GoalMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ goal }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GoalMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.goal = goal;
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
