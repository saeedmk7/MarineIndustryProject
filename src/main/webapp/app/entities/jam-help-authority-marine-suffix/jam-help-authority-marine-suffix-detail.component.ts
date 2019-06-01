import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJamHelpAuthorityMarineSuffix } from 'app/shared/model/jam-help-authority-marine-suffix.model';

@Component({
    selector: 'mi-jam-help-authority-marine-suffix-detail',
    templateUrl: './jam-help-authority-marine-suffix-detail.component.html'
})
export class JamHelpAuthorityMarineSuffixDetailComponent implements OnInit {
    jamHelpAuthority: IJamHelpAuthorityMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jamHelpAuthority }) => {
            this.jamHelpAuthority = jamHelpAuthority;
        });
    }

    previousState() {
        window.history.back();
    }
}
