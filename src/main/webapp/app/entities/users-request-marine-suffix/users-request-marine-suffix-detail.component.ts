import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IUsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {Principal} from "app/core";
import {Observable} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {UsersRequestMarineSuffixService} from "app/entities/users-request-marine-suffix/users-request-marine-suffix.service";

@Component({
    selector: 'mi-users-request-marine-suffix-detail',
    templateUrl: './users-request-marine-suffix-detail.component.html'
})
export class UsersRequestMarineSuffixDetailComponent implements OnInit {
    usersRequest: IUsersRequestMarineSuffix;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private usersRequestService: UsersRequestMarineSuffixService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ usersRequest }) => {
            this.principal.identity().then(account => {

                this.usersRequest = usersRequest;
                if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                {
                    if(this.usersRequest.requestStatus === 'NEW') {
                        this.usersRequest.requestStatus = RequestStatus.READ;
                        this.subscribeToSaveResponse(this.usersRequestService.update(this.usersRequest));
                    }
                }
                this.usersRequest = this.convertObjectDatesService.changeDate(this.usersRequest);
            });
            this.usersRequest = usersRequest;
        });
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IUsersRequestMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IUsersRequestMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    private onSaveSuccess() {
    }

    private onSaveError() {
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
