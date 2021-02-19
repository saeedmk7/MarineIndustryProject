import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription, interval } from 'rxjs';
import { NavigationEnd, Router } from '@angular/router';
import * as moment from 'moment';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LoginModalService, LoginService, Principal } from 'app/core';
import { JhiEventManager } from 'ng-jhipster';

@Component({
    selector: 'app-count-down',
    templateUrl: './countdown.component.html',
    styleUrls: ['./countdown.component.scss']
})
export class CountdownComponent implements OnInit, OnDestroy {
    private subscription: Subscription;

    public endDate;
    milliSecondsInASecond = 1000;
    SecondsInAMinute = 60;
    minutesInAnHour = 60;

    public timeDifference;
    public secondsToDday;
    public minutesToDday;

    modalRef: NgbModalRef;

    constructor(
        private router: Router,
        private loginModalService: LoginModalService,
        private loginService: LoginService,
        private principal: Principal,
        private eventManager: JhiEventManager
    ) {
        router.events.subscribe(val => {
            if (val instanceof NavigationEnd) this.setNewEndDate();
        });
    }

    private getTimeDifference() {
        this.timeDifference = this.endDate.getTime() - new Date().getTime();
        if (this.timeDifference <= 0) {
            this.logout();
            this.ngOnDestroy();
        }

        this.allocateTimeUnits(this.timeDifference);
    }

    private allocateTimeUnits(timeDifference) {
        this.secondsToDday = Math.floor((timeDifference / this.milliSecondsInASecond) % this.SecondsInAMinute);
        this.minutesToDday = Math.floor((timeDifference / (this.milliSecondsInASecond * this.minutesInAnHour)) % this.SecondsInAMinute);
    }

    ngOnInit() {
        this.setNewEndDate();
        this.subscription = interval(1000).subscribe(x => {
            this.getTimeDifference();
        });
    }

    setNewEndDate() {
        let newDate = moment().add(30, 'm');
        this.endDate = newDate.toDate();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.subscription);
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.loginService.logout();
        this.router.navigate(['']).then(() => {
            setTimeout(() => {
                this.login();
            }, 1000);
        });
    }
}
