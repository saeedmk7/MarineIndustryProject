import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEffectivenessLevelMarineSuffix } from 'app/shared/model/effectiveness-level-marine-suffix.model';
import { EffectivenessLevelMarineSuffixService } from './effectiveness-level-marine-suffix.service';

@Component({
    selector: 'mi-effectiveness-level-marine-suffix-delete-dialog',
    templateUrl: './effectiveness-level-marine-suffix-delete-dialog.component.html'
})
export class EffectivenessLevelMarineSuffixDeleteDialogComponent {
    effectivenessLevel: IEffectivenessLevelMarineSuffix;

    constructor(
        private effectivenessLevelService: EffectivenessLevelMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.effectivenessLevelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'effectivenessLevelListModification',
                content: 'Deleted an effectivenessLevel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-effectiveness-level-marine-suffix-delete-popup',
    template: ''
})
export class EffectivenessLevelMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ effectivenessLevel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EffectivenessLevelMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.effectivenessLevel = effectivenessLevel;
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
