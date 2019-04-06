import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServiceUnitMarineSuffix } from 'app/shared/model/service-unit-marine-suffix.model';
import { ServiceUnitMarineSuffixService } from './service-unit-marine-suffix.service';

@Component({
    selector: 'mi-service-unit-marine-suffix-delete-dialog',
    templateUrl: './service-unit-marine-suffix-delete-dialog.component.html'
})
export class ServiceUnitMarineSuffixDeleteDialogComponent {
    serviceUnit: IServiceUnitMarineSuffix;

    constructor(
        private serviceUnitService: ServiceUnitMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.serviceUnitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'serviceUnitListModification',
                content: 'Deleted an serviceUnit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-service-unit-marine-suffix-delete-popup',
    template: ''
})
export class ServiceUnitMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ serviceUnit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ServiceUnitMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.serviceUnit = serviceUnit;
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
