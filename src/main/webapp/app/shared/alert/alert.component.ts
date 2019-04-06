import {Component, OnDestroy, OnInit} from '@angular/core';
import {JhiAlertService, JhiTranslateComponent} from 'ng-jhipster';
import {TranslateService} from '@ngx-translate/core';

@Component({
    selector: 'mi-alert',
    template: `
        <div class="alerts" role="alert">
            <div *ngFor="let alert of alerts" [ngClass]="setClasses(alert)">
                <ngb-alert *ngIf="alert && alert.type && alert.msg" [type]="alert.type" (close)="alert.close(alerts)">
                    <pre [innerHTML]="alert.msg"></pre>
                </ngb-alert>
            </div>
        </div>`
})
export class JhiAlertComponent implements OnInit, OnDestroy {
    alerts: any[];

    constructor(private alertService: JhiAlertService,
        private translateService: TranslateService) {
    }

    ngOnInit() {

        this.alerts = this.alertService.get();
    }

    setClasses(alert) {

        if (alert.msg.includes("504 Gateway Timeout") || alert.msg.includes("Http failure response for (unknown url)")) {
            this.translateService.get("error.ServerIsUnavailable").subscribe((resp) => {

                alert.msg = resp;
                return {
                    toast: !!alert.toast,
                    [alert.position]: true
                };
            });
        }
        else {
            return {
                toast: !!alert.toast,
                [alert.position]: true
            };
        }
    }

    ngOnDestroy() {
        this.alerts = [];
    }
}
