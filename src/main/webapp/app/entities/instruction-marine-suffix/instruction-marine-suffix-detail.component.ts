import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInstructionMarineSuffix } from 'app/shared/model/instruction-marine-suffix.model';

@Component({
    selector: 'mi-instruction-marine-suffix-detail',
    templateUrl: './instruction-marine-suffix-detail.component.html'
})
export class InstructionMarineSuffixDetailComponent implements OnInit {
    instruction: IInstructionMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ instruction }) => {
            this.instruction = instruction;
        });
    }

    previousState() {
        window.history.back();
    }
}
