import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInstructionAuthorityMarineSuffix } from 'app/shared/model/instruction-authority-marine-suffix.model';
import { InstructionAuthorityMarineSuffixService } from './instruction-authority-marine-suffix.service';

@Component({
    selector: 'mi-instruction-authority-marine-suffix-delete-dialog',
    templateUrl: './instruction-authority-marine-suffix-delete-dialog.component.html'
})
export class InstructionAuthorityMarineSuffixDeleteDialogComponent {
    instructionAuthority: IInstructionAuthorityMarineSuffix;

    constructor(
        protected instructionAuthorityService: InstructionAuthorityMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.instructionAuthorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'instructionAuthorityListModification',
                content: 'Deleted an instructionAuthority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-instruction-authority-marine-suffix-delete-popup',
    template: ''
})
export class InstructionAuthorityMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ instructionAuthority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InstructionAuthorityMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.instructionAuthority = instructionAuthority;
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
