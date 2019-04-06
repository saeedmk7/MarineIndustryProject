import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IToolsAndFacilityMarineSuffix } from 'app/shared/model/tools-and-facility-marine-suffix.model';
import { ToolsAndFacilityMarineSuffixService } from './tools-and-facility-marine-suffix.service';

@Component({
    selector: 'mi-tools-and-facility-marine-suffix-delete-dialog',
    templateUrl: './tools-and-facility-marine-suffix-delete-dialog.component.html'
})
export class ToolsAndFacilityMarineSuffixDeleteDialogComponent {
    toolsAndFacility: IToolsAndFacilityMarineSuffix;

    constructor(
        private toolsAndFacilityService: ToolsAndFacilityMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.toolsAndFacilityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'toolsAndFacilityListModification',
                content: 'Deleted an toolsAndFacility'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-tools-and-facility-marine-suffix-delete-popup',
    template: ''
})
export class ToolsAndFacilityMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ toolsAndFacility }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ToolsAndFacilityMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.toolsAndFacility = toolsAndFacility;
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
