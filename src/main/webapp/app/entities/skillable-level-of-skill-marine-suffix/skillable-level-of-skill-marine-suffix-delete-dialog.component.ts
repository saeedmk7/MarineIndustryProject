import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { SkillableLevelOfSkillMarineSuffixService } from './skillable-level-of-skill-marine-suffix.service';

@Component({
    selector: 'mi-skillable-level-of-skill-marine-suffix-delete-dialog',
    templateUrl: './skillable-level-of-skill-marine-suffix-delete-dialog.component.html'
})
export class SkillableLevelOfSkillMarineSuffixDeleteDialogComponent {
    skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix;

    constructor(
        private skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.skillableLevelOfSkillService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'skillableLevelOfSkillListModification',
                content: 'Deleted an skillableLevelOfSkill'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'mi-skillable-level-of-skill-marine-suffix-delete-popup',
    template: ''
})
export class SkillableLevelOfSkillMarineSuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ skillableLevelOfSkill }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SkillableLevelOfSkillMarineSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.skillableLevelOfSkill = skillableLevelOfSkill;
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
