import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganizationChartAuthorityMarineSuffix } from 'app/shared/model/organization-chart-authority-marine-suffix.model';
import { OrganizationChartAuthorityMarineSuffixService } from './organization-chart-authority-marine-suffix.service';

@Component({
    selector: 'mi-organization-chart-authority-marine-suffix-delete-dialog',
    templateUrl: './organization-chart-authority-marine-suffix-delete-dialog.component.html'
})
export class OrganizationChartAuthorityMarineSuffixDeleteDialogComponent {
    organizationChartAuthority: IOrganizationChartAuthorityMarineSuffix;

    constructor(
        private organizationChartAuthorityService: OrganizationChartAuthorityMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.organizationChartAuthorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'organizationChartAuthorityListModification',
                content: 'Deleted an organizationChartAuthority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-organization-chart-authority-marine-suffix-delete-popup',
    template: ''
})
export class OrganizationChartAuthorityMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organizationChartAuthority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OrganizationChartAuthorityMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.organizationChartAuthority = organizationChartAuthority;
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
