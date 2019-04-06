import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnnouncementMarineSuffix } from 'app/shared/model/announcement-marine-suffix.model';
import { AnnouncementMarineSuffixService } from './announcement-marine-suffix.service';

@Component({
    selector: 'mi-announcement-marine-suffix-delete-dialog',
    templateUrl: './announcement-marine-suffix-delete-dialog.component.html'
})
export class AnnouncementMarineSuffixDeleteDialogComponent {
    announcement: IAnnouncementMarineSuffix;

    constructor(
        private announcementService: AnnouncementMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.announcementService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'announcementListModification',
                content: 'Deleted an announcement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-announcement-marine-suffix-delete-popup',
    template: ''
})
export class AnnouncementMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ announcement }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AnnouncementMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.announcement = announcement;
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
