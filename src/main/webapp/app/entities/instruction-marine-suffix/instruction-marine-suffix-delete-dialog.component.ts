import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';
import { InstructionMarineSuffixService } from './instruction-marine-suffix.service';

@Component({
    selector: 'mi-instruction-marine-suffix-delete-dialog',
    templateUrl: './instruction-marine-suffix-delete-dialog.component.html'
})
export class InstructionMarineSuffixDeleteDialogComponent {
    instruction: IInstructionMarineSuffix;

    constructor(
        protected instructionService: InstructionMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.instructionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'instructionListModification',
                content: 'Deleted an instruction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-instruction-marine-suffix-delete-popup',
    template: ''
})
export class InstructionMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ instruction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InstructionMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.instruction = instruction;
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
