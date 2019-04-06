import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';

@Component({
    selector: 'mi-nav-bar-item-marine-suffix-detail',
    templateUrl: './nav-bar-item-marine-suffix-detail.component.html'
})
export class NavBarItemMarineSuffixDetailComponent implements OnInit {
    navBarItem: INavBarItemMarineSuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ navBarItem }) => {
            this.navBarItem = navBarItem;
        });
    }

    previousState() {
        window.history.back();
    }
}
