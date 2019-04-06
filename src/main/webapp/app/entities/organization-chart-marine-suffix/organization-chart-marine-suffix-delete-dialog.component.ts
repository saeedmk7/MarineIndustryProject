import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { OrganizationChartMarineSuffixService } from './organization-chart-marine-suffix.service';

@Component({
    selector: 'mi-organization-chart-marine-suffix-delete-dialog',
    templateUrl: './organization-chart-marine-suffix-delete-dialog.component.html'
})
export class OrganizationChartMarineSuffixDeleteDialogComponent {
    organizationChart: IOrganizationChartMarineSuffix;

    constructor(
        private organizationChartService: OrganizationChartMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager,
        private jhiAlertService : JhiAlertService
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.organizationChartService.delete(id).subscribe(response => {
                this.eventManager.broadcast({
                    name: 'organizationChartListModification',
                    content: 'Deleted an organizationChart'
                });
                this.activeModal.dismiss(true);
        },error1 => {


            this.jhiAlertService.addAlert({type: 'danger', msg:  error1.error.title}, []);
        });
    }
}

@Component({
    selector: 'mi-organization-chart-marine-suffix-delete-popup',
    template: ''
})
export class OrganizationChartMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ organizationChart }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OrganizationChartMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.organizationChart = organizationChart;
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
