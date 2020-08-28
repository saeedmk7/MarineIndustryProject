import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmploymentTypeMarineSuffix } from 'app/shared/model/employment-type-marine-suffix.model';
import { IDetailFinalEffectivenessPhaseReportModel } from 'app/shared/model/custom/effectivenessPhaseModels/detail-final-effectiveness-phase-report-model';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'mi-final-effectiveness-phase-report-marine-suffix-detail-dialog',
    templateUrl: './final-effectiveness-phase-report-marine-suffix-detail-dialog.component.html'
})
export class FinalEffectivenessPhaseReportMarineSuffixDetailDialogComponent {
    detailFinalEffectivenessPhaseReportModels: IDetailFinalEffectivenessPhaseReportModel[];

    constructor(public activeModal: NgbActiveModal, private eventManager: JhiEventManager, private jhiTranslate: TranslateService) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    export() {
        let a = new ExcelService(this.jhiTranslate);
        a.exportAsExcelFile(
            this.detailFinalEffectivenessPhaseReportModels,
            'detailFinalEffectivenessPhaseReportModel',
            'marineindustryprojApp.detailFinalEffectivenessPhaseReportModel'
        );
    }

    confirmDelete(id: number) {
        this.activeModal.dismiss(true);
        /*this.employmentTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'employmentTypeListModification',
                content: 'Deleted an employmentType'
            });

        });*/
    }
}

@Component({
    selector: 'mi-final-effectiveness-phase-report-marine-suffix-detail-dialog-popup',
    template: ''
})
export class FinalEffectivenessPhaseReportMarineSuffixDetailPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ detailFinalEffectivenessPhaseReportModels }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FinalEffectivenessPhaseReportMarineSuffixDetailDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.detailFinalEffectivenessPhaseReportModels = detailFinalEffectivenessPhaseReportModels;
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
