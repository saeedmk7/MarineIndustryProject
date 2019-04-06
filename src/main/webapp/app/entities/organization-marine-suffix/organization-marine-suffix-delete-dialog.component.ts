import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganizationMarineSuffix } from 'app/shared/model/organization-marine-suffix.model';
import { OrganizationMarineSuffixService } from './organization-marine-suffix.service';

@Component({
    selector: 'mi-organization-marine-suffix-delete-dialog',
    templateUrl: './organization-marine-suffix-delete-dialog.component.html'
})
export class OrganizationMarineSuffixDeleteDialogComponent {
    organization: IOrganizationMarineSuffix;

    constructor(
        private organizationService: OrganizationMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.organizationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'organizationListModification',
                content: 'Deleted an organization'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-organization-marine-suffix-delete-popup',
    template: ''
})
export class OrganizationMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organization }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OrganizationMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.organization = organization;
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
