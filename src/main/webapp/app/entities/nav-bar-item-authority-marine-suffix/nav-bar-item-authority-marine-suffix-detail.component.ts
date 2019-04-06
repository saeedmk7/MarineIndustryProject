import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INavBarItemAuthorityMarineSuffix } from 'app/shared/model/nav-bar-item-authority-marine-suffix.model';

@Component({
    selector: 'mi-nav-bar-item-authority-marine-suffix-detail',
    templateUrl: './nav-bar-item-authority-marine-suffix-detail.component.html'
})
export class NavBarItemAuthorityMarineSuffixDetailComponent implements OnInit {
    navBarItemAuthority: INavBarItemAuthorityMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ navBarItemAuthority }) => {
            this.navBarItemAuthority = navBarItemAuthority;
        });
    }

    previousState() {
        window.history.back();
    }
}
