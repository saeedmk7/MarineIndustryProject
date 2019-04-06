import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from './document-marine-suffix.service';

@Component({
    selector: 'mi-document-marine-suffix-delete-dialog',
    templateUrl: './document-marine-suffix-delete-dialog.component.html'
})
export class DocumentMarineSuffixDeleteDialogComponent {
    document: IDocumentMarineSuffix;

    constructor(
        private documentService: DocumentMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number,entityName: any) {
        this.documentService.delete(id,entityName).subscribe(response => {
            this.eventManager.broadcast({
                name: 'documentListModification',
                content: 'Deleted an document'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-document-marine-suffix-delete-popup',
    template: ''
})
export class DocumentMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;
    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ document }) => {
            this.activatedRoute.params.subscribe((params) => {
                document.entityName = params['entityName'];
            });
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DocumentMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.document = document;
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
