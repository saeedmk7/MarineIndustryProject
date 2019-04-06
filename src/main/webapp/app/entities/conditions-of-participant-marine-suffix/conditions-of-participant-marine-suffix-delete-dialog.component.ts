import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConditionsOfParticipantMarineSuffix } from 'app/shared/model/conditions-of-participant-marine-suffix.model';
import { ConditionsOfParticipantMarineSuffixService } from './conditions-of-participant-marine-suffix.service';

@Component({
    selector: 'mi-conditions-of-participant-marine-suffix-delete-dialog',
    templateUrl: './conditions-of-participant-marine-suffix-delete-dialog.component.html'
})
export class ConditionsOfParticipantMarineSuffixDeleteDialogComponent {
    conditionsOfParticipant: IConditionsOfParticipantMarineSuffix;

    constructor(
        private conditionsOfParticipantService: ConditionsOfParticipantMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conditionsOfParticipantService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'conditionsOfParticipantListModification',
                content: 'Deleted an conditionsOfParticipant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-conditions-of-participant-marine-suffix-delete-popup',
    template: ''
})
export class ConditionsOfParticipantMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conditionsOfParticipant }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConditionsOfParticipantMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.conditionsOfParticipant = conditionsOfParticipant;
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
