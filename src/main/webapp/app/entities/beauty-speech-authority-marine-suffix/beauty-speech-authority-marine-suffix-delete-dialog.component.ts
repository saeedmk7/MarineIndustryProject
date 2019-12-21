import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBeautySpeechAuthorityMarineSuffix } from 'app/shared/model/beauty-speech-authority-marine-suffix.model';
import { BeautySpeechAuthorityMarineSuffixService } from './beauty-speech-authority-marine-suffix.service';

@Component({
    selector: 'mi-beauty-speech-authority-marine-suffix-delete-dialog',
    templateUrl: './beauty-speech-authority-marine-suffix-delete-dialog.component.html'
})
export class BeautySpeechAuthorityMarineSuffixDeleteDialogComponent {
    beautySpeechAuthority: IBeautySpeechAuthorityMarineSuffix;

    constructor(
        protected beautySpeechAuthorityService: BeautySpeechAuthorityMarineSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.beautySpeechAuthorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'beautySpeechAuthorityListModification',
                content: 'Deleted an beautySpeechAuthority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-beauty-speech-authority-marine-suffix-delete-popup',
    template: ''
})
export class BeautySpeechAuthorityMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ beautySpeechAuthority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BeautySpeechAuthorityMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.beautySpeechAuthority = beautySpeechAuthority;
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
