import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';
import { SecurityLevelMarineSuffixService } from './security-level-marine-suffix.service';

@Component({
    selector: 'mi-security-level-marine-suffix-delete-dialog',
    templateUrl: './security-level-marine-suffix-delete-dialog.component.html'
})
export class SecurityLevelMarineSuffixDeleteDialogComponent {
    securityLevel: ISecurityLevelMarineSuffix;

    constructor(
        private securityLevelService: SecurityLevelMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.securityLevelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'securityLevelListModification',
                content: 'Deleted an securityLevel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-security-level-marine-suffix-delete-popup',
    template: ''
})
export class SecurityLevelMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ securityLevel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SecurityLevelMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.securityLevel = securityLevel;
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
