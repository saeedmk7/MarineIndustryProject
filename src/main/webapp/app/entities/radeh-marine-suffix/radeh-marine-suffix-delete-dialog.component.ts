import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRadehMarineSuffix } from 'app/shared/model/radeh-marine-suffix.model';
import { RadehMarineSuffixService } from './radeh-marine-suffix.service';

@Component({
    selector: 'mi-radeh-marine-suffix-delete-dialog',
    templateUrl: './radeh-marine-suffix-delete-dialog.component.html'
})
export class RadehMarineSuffixDeleteDialogComponent {
    radeh: IRadehMarineSuffix;

    constructor(
        private radehService: RadehMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.radehService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'radehListModification',
                content: 'Deleted an radeh'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-radeh-marine-suffix-delete-popup',
    template: ''
})
export class RadehMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ radeh }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RadehMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.radeh = radeh;
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
