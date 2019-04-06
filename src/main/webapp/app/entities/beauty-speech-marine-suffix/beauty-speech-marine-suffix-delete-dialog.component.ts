import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBeautySpeechMarineSuffix } from 'app/shared/model/beauty-speech-marine-suffix.model';
import { BeautySpeechMarineSuffixService } from './beauty-speech-marine-suffix.service';

@Component({
    selector: 'mi-beauty-speech-marine-suffix-delete-dialog',
    templateUrl: './beauty-speech-marine-suffix-delete-dialog.component.html'
})
export class BeautySpeechMarineSuffixDeleteDialogComponent {
    beautySpeech: IBeautySpeechMarineSuffix;

    constructor(
        private beautySpeechService: BeautySpeechMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.beautySpeechService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'beautySpeechListModification',
                content: 'Deleted an beautySpeech'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-beauty-speech-marine-suffix-delete-popup',
    template: ''
})
export class BeautySpeechMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ beautySpeech }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BeautySpeechMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.beautySpeech = beautySpeech;
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
