import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITeachTypeMarineSuffix } from 'app/shared/model/teach-type-marine-suffix.model';
import { TeachTypeMarineSuffixService } from './teach-type-marine-suffix.service';

@Component({
    selector: 'mi-teach-type-marine-suffix-delete-dialog',
    templateUrl: './teach-type-marine-suffix-delete-dialog.component.html'
})
export class TeachTypeMarineSuffixDeleteDialogComponent {
    teachType: ITeachTypeMarineSuffix;

    constructor(
        private teachTypeService: TeachTypeMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.teachTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'teachTypeListModification',
                content: 'Deleted an teachType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-teach-type-marine-suffix-delete-popup',
    template: ''
})
export class TeachTypeMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ teachType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TeachTypeMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.teachType = teachType;
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
