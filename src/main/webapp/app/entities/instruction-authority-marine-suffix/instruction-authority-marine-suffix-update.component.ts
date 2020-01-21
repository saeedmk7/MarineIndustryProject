import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IInstructionAuthorityMarineSuffix } from 'app/shared/model/instruction-authority-marine-suffix.model';
import { InstructionAuthorityMarineSuffixService } from './instruction-authority-marine-suffix.service';
import { IInstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';
import { InstructionMarineSuffixService } from 'app/entities/instruction-marine-suffix';

@Component({
    selector: 'mi-instruction-authority-marine-suffix-update',
    templateUrl: './instruction-authority-marine-suffix-update.component.html'
})
export class InstructionAuthorityMarineSuffixUpdateComponent implements OnInit {
    instructionAuthority: IInstructionAuthorityMarineSuffix;
    isSaving: boolean;

    instructions: IInstructionMarineSuffix[];
    createDate: string;
    modifyDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected instructionAuthorityService: InstructionAuthorityMarineSuffixService,
        protected instructionService: InstructionMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ instructionAuthority }) => {
            this.instructionAuthority = instructionAuthority;
            this.createDate =
                this.instructionAuthority.createDate != null ? this.instructionAuthority.createDate.format(DATE_TIME_FORMAT) : null;
            this.modifyDate =
                this.instructionAuthority.modifyDate != null ? this.instructionAuthority.modifyDate.format(DATE_TIME_FORMAT) : null;
        });
        this.instructionService.query().subscribe(
            (res: HttpResponse<IInstructionMarineSuffix[]>) => {
                this.instructions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.instructionAuthority.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.instructionAuthority.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;
        if (this.instructionAuthority.id !== undefined) {
            this.subscribeToSaveResponse(this.instructionAuthorityService.update(this.instructionAuthority));
        } else {
            this.subscribeToSaveResponse(this.instructionAuthorityService.create(this.instructionAuthority));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstructionAuthorityMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IInstructionAuthorityMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackInstructionById(index: number, item: IInstructionMarineSuffix) {
        return item.id;
    }
}
