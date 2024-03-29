import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuthority } from 'app/shared/model/authority.model';
import { AuthorityService } from './authority.service';

@Component({
    selector: 'mi-authority-delete-dialog',
    templateUrl: './authority-delete-dialog.component.html'
})
export class AuthorityDeleteDialogComponent {
    authority: IAuthority;

    constructor(
        private authorityService: AuthorityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(name: string) {
        this.authorityService.delete(name).subscribe(response => {
            this.eventManager.broadcast({
                name: 'authorityListModification',
                content: 'Deleted an authority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-authority-delete-popup',
    template: ''
})
export class AuthorityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ authority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AuthorityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: false
                });
                this.ngbModalRef.componentInstance.authority = authority;
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
