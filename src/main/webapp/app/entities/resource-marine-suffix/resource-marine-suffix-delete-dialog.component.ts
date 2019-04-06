import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import { IResourceMarineSuffix } from 'app/shared/model/resource-marine-suffix.model';
import { ResourceMarineSuffixService } from './resource-marine-suffix.service';

@Component({
    selector: 'mi-resource-marine-suffix-delete-dialog',
    templateUrl: './resource-marine-suffix-delete-dialog.component.html'
})
export class ResourceMarineSuffixDeleteDialogComponent {
    resource: IResourceMarineSuffix;

    constructor(
        private resourceService: ResourceMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager,
        private jhiAlertService: JhiAlertService
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resourceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resourceListModification',
                content: 'Deleted an resource'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-resource-marine-suffix-delete-popup',
    template: ''
})
export class ResourceMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resource }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResourceMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resource = resource;
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
