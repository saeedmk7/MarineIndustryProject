import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPeopleUnderTrainingMarineSuffix } from 'app/shared/model/people-under-training-marine-suffix.model';
import { PeopleUnderTrainingMarineSuffixService } from './people-under-training-marine-suffix.service';

@Component({
    selector: 'mi-people-under-training-marine-suffix-delete-dialog',
    templateUrl: './people-under-training-marine-suffix-delete-dialog.component.html'
})
export class PeopleUnderTrainingMarineSuffixDeleteDialogComponent {
    peopleUnderTraining: IPeopleUnderTrainingMarineSuffix;

    constructor(
        protected peopleUnderTrainingService: PeopleUnderTrainingMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.peopleUnderTrainingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'peopleUnderTrainingListModification',
                content: 'Deleted an peopleUnderTraining'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-people-under-training-marine-suffix-delete-popup',
    template: ''
})
export class PeopleUnderTrainingMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ peopleUnderTraining }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PeopleUnderTrainingMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.peopleUnderTraining = peopleUnderTraining;
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
