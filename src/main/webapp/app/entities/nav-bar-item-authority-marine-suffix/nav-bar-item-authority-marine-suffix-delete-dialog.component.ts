import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INavBarItemAuthorityMarineSuffix } from 'app/shared/model/nav-bar-item-authority-marine-suffix.model';
import { NavBarItemAuthorityMarineSuffixService } from './nav-bar-item-authority-marine-suffix.service';

@Component({
    selector: 'mi-nav-bar-item-authority-marine-suffix-delete-dialog',
    templateUrl: './nav-bar-item-authority-marine-suffix-delete-dialog.component.html'
})
export class NavBarItemAuthorityMarineSuffixDeleteDialogComponent {
    navBarItemAuthority: INavBarItemAuthorityMarineSuffix;

    constructor(
        private navBarItemAuthorityService: NavBarItemAuthorityMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.navBarItemAuthorityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'navBarItemAuthorityListModification',
                content: 'Deleted an navBarItemAuthority'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-nav-bar-item-authority-marine-suffix-delete-popup',
    template: ''
})
export class NavBarItemAuthorityMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ navBarItemAuthority }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NavBarItemAuthorityMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.navBarItemAuthority = navBarItemAuthority;
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
