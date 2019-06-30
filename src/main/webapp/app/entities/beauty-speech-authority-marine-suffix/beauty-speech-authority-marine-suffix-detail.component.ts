import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBeautySpeechAuthorityMarineSuffix } from 'app/shared/model/beauty-speech-authority-marine-suffix.model';

@Component({
    selector: 'mi-beauty-speech-authority-marine-suffix-detail',
    templateUrl: './beauty-speech-authority-marine-suffix-detail.component.html'
})
export class BeautySpeechAuthorityMarineSuffixDetailComponent implements OnInit {
    beautySpeechAuthority: IBeautySpeechAuthorityMarineSuffix;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ beautySpeechAuthority }) => {
            this.beautySpeechAuthority = beautySpeechAuthority;
        });
    }

    previousState() {
        window.history.back();
    }
}
