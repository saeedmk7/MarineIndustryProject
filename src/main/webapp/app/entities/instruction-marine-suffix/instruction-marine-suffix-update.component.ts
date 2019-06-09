import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IInstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';
import { InstructionMarineSuffixService } from './instruction-marine-suffix.service';
import { IDocumentMarineSuffix } from 'app/shared/model/document-marine-suffix.model';
import { DocumentMarineSuffixService } from 'app/entities/document-marine-suffix';

@Component({
    selector: 'mi-instruction-marine-suffix-update',
    templateUrl: './instruction-marine-suffix-update.component.html'
})
export class InstructionMarineSuffixUpdateComponent implements OnInit {
    instruction: IInstructionMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];
    /*createDate: string;
    modifyDate: string;*/

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected instructionService: InstructionMarineSuffixService,
        protected documentService: DocumentMarineSuffixService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ instruction }) => {
            this.instruction = instruction;
        });
        /*this.documentService.query().subscribe(
            (res: HttpResponse<IDocumentMarineSuffix[]>) => {
                this.documents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        /*this.instruction.createDate = this.createDate != null ? moment(this.createDate, DATE_TIME_FORMAT) : null;
        this.instruction.modifyDate = this.modifyDate != null ? moment(this.modifyDate, DATE_TIME_FORMAT) : null;*/
        if(!this.instruction.description)
            this.instruction.description = ' ';
        if (this.instruction.id !== undefined) {
            this.subscribeToSaveResponse(this.instructionService.update(this.instruction));
        } else {
            this.subscribeToSaveResponse(this.instructionService.create(this.instruction));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstructionMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IInstructionMarineSuffix>) => this.onSaveSuccess(),
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

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
