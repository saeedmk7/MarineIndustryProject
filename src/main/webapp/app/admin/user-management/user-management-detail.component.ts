import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { User } from 'app/core';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";

@Component({
    selector: 'jhi-user-mgmt-detail',
    templateUrl: './user-management-detail.component.html'
})
export class UserMgmtDetailComponent implements OnInit {
    user: User;

    constructor(private route: ActivatedRoute,private convertObjectDatesService : ConvertObjectDatesService) {}

    ngOnInit() {
        this.route.data.subscribe(({ user }) => {
            this.user = user.body ? this.convertObjectDatesService.changeDate(user.body) : this.convertObjectDatesService.changeDate(user);
        });
    }
}
