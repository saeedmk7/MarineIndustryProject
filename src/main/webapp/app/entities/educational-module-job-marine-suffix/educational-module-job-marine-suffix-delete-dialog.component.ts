import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEducationalModuleJobMarineSuffix } from 'app/shared/model/educational-module-job-marine-suffix.model';
import { EducationalModuleJobMarineSuffixService } from './educational-module-job-marine-suffix.service';

@Component({
    selector: 'mi-educational-module-job-marine-suffix-delete-dialog',
    templateUrl: './educational-module-job-marine-suffix-delete-dialog.component.html'
})
export class EducationalModuleJobMarineSuffixDeleteDialogComponent {
    educationalModuleJob: IEducationalModuleJobMarineSuffix;

    constructor(
        private educationalModuleJobService: EducationalModuleJobMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.educationalModuleJobService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'educationalModuleJobListModification',
                content: 'Deleted an educationalModuleJob'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-educational-module-job-marine-suffix-delete-popup',
    template: ''
})
export class EducationalModuleJobMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ educationalModuleJob }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EducationalModuleJobMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.educationalModuleJob = educationalModuleJob;
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
