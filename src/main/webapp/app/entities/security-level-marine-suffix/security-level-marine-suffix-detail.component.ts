import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISecurityLevelMarineSuffix } from 'app/shared/model/security-level-marine-suffix.model';

@Component({
    selector: 'mi-security-level-marine-suffix-detail',
    templateUrl: './security-level-marine-suffix-detail.component.html'
})
export class SecurityLevelMarineSuffixDetailComponent implements OnInit {
    securityLevel: ISecurityLevelMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ securityLevel }) => {
            this.securityLevel = securityLevel;
        });
    }

    previousState() {
        window.history.back();
    }
}
