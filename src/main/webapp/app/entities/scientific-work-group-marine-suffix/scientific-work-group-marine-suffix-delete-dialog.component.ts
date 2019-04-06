import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IScientificWorkGroupMarineSuffix } from 'app/shared/model/scientific-work-group-marine-suffix.model';
import { ScientificWorkGroupMarineSuffixService } from './scientific-work-group-marine-suffix.service';

@Component({
    selector: 'mi-scientific-work-group-marine-suffix-delete-dialog',
    templateUrl: './scientific-work-group-marine-suffix-delete-dialog.component.html'
})
export class ScientificWorkGroupMarineSuffixDeleteDialogComponent {
    scientificWorkGroup: IScientificWorkGroupMarineSuffix;

    constructor(
        private scientificWorkGroupService: ScientificWorkGroupMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.scientificWorkGroupService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'scientificWorkGroupListModification',
                content: 'Deleted an scientificWorkGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-scientific-work-group-marine-suffix-delete-popup',
    template: ''
})
export class ScientificWorkGroupMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ scientificWorkGroup }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ScientificWorkGroupMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.scientificWorkGroup = scientificWorkGroup;
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
