import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInstructionAuthorityMarineSuffix } from 'app/shared/model/instruction-authority-marine-suffix.model';

@Component({
    selector: 'mi-instruction-authority-marine-suffix-detail',
    templateUrl: './instruction-authority-marine-suffix-detail.component.html'
})
export class InstructionAuthorityMarineSuffixDetailComponent implements OnInit {
    instructionAuthority: IInstructionAuthorityMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ instructionAuthority }) => {
            this.instructionAuthority = instructionAuthority;
        });
    }

    previousState() {
        window.history.back();
    }
}
