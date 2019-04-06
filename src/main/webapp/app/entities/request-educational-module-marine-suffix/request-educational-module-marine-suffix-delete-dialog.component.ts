import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import { RequestEducationalModuleMarineSuffixService } from './request-educational-module-marine-suffix.service';

@Component({
    selector: 'mi-request-educational-module-marine-suffix-delete-dialog',
    templateUrl: './request-educational-module-marine-suffix-delete-dialog.component.html'
})
export class RequestEducationalModuleMarineSuffixDeleteDialogComponent {
    requestEducationalModule: IRequestEducationalModuleMarineSuffix;

    constructor(
        private requestEducationalModuleService: RequestEducationalModuleMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.requestEducationalModuleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'requestEducationalModuleListModification',
                content: 'Deleted an requestEducationalModule'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-request-educational-module-marine-suffix-delete-popup',
    template: ''
})
export class RequestEducationalModuleMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestEducationalModule }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RequestEducationalModuleMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.requestEducationalModule = requestEducationalModule;
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
