import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';
import { NavBarItemMarineSuffixService } from './nav-bar-item-marine-suffix.service';

@Component({
    selector: 'mi-nav-bar-item-marine-suffix-delete-dialog',
    templateUrl: './nav-bar-item-marine-suffix-delete-dialog.component.html'
})
export class NavBarItemMarineSuffixDeleteDialogComponent {
    navBarItem: INavBarItemMarineSuffix;

    constructor(
        private navBarItemService: NavBarItemMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.navBarItemService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'navBarItemListModification',
                content: 'Deleted an navBarItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-nav-bar-item-marine-suffix-delete-popup',
    template: ''
})
export class NavBarItemMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ navBarItem }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NavBarItemMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.navBarItem = navBarItem;
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
