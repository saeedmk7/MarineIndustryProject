import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestOrganizationNiazsanjiMarineSuffix } from 'app/shared/model/request-organization-niazsanji-marine-suffix.model';
import { RequestOrganizationNiazsanjiMarineSuffixService } from './request-organization-niazsanji-marine-suffix.service';

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix-delete-dialog',
    templateUrl: './request-organization-niazsanji-marine-suffix-delete-dialog.component.html'
})
export class RequestOrganizationNiazsanjiMarineSuffixDeleteDialogComponent {
    requestOrganizationNiazsanji: IRequestOrganizationNiazsanjiMarineSuffix;

    constructor(
        private requestOrganizationNiazsanjiService: RequestOrganizationNiazsanjiMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.requestOrganizationNiazsanjiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'requestOrganizationNiazsanjiListModification',
                content: 'Deleted an requestOrganizationNiazsanji'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-request-organization-niazsanji-marine-suffix-delete-popup',
    template: ''
})
export class RequestOrganizationNiazsanjiMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestOrganizationNiazsanji }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RequestOrganizationNiazsanjiMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.requestOrganizationNiazsanji = requestOrganizationNiazsanji;
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
