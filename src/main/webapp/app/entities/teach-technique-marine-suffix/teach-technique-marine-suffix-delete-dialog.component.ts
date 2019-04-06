import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeachTechniqueMarineSuffix } from 'app/shared/model/teach-technique-marine-suffix.model';
import { TeachTechniqueMarineSuffixService } from './teach-technique-marine-suffix.service';

@Component({
    selector: 'mi-teach-technique-marine-suffix-delete-dialog',
    templateUrl: './teach-technique-marine-suffix-delete-dialog.component.html'
})
export class TeachTechniqueMarineSuffixDeleteDialogComponent {
    teachTechnique: ITeachTechniqueMarineSuffix;

    constructor(
        private teachTechniqueService: TeachTechniqueMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teachTechniqueService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teachTechniqueListModification',
                content: 'Deleted an teachTechnique'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teach-technique-marine-suffix-delete-popup',
    template: ''
})
export class TeachTechniqueMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachTechnique }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeachTechniqueMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.teachTechnique = teachTechnique;
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
