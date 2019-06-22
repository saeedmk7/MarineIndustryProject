import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import { IUsersRequestMarineSuffix } from 'app/shared/model/users-request-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {Principal} from "app/core";
import {Observable} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {UsersRequestMarineSuffixService} from "app/entities/users-request-marine-suffix/users-request-marine-suffix.service";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";

@Component({
    selector: 'mi-users-request-marine-suffix-detail',
    templateUrl: './users-request-marine-suffix-detail.component.html'
})
export class UsersRequestMarineSuffixDetailComponent implements OnInit {
    usersRequest: IUsersRequestMarineSuffix;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private personService: PersonMarineSuffixService,
        private jhiAlertService: JhiAlertService,
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
                let nationalIds = [];
                nationalIds.push(this.usersRequest.createUserLogin);
                nationalIds.push(this.usersRequest.changeStatusUserLogin);
                const criteria = [{
                    key: 'nationalId.in',
                    value: nationalIds
                }];
                this.personService.query({
                    page: 0,
                    size: 20000,
                    criteria,
                    sort: ["id", "asc"]
                }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
                    let people  =resp.body;
                        if(people)
                        {
                            let person = people.find(w => w.nationalId.includes(this.usersRequest.createUserLogin));
                            if(person)
                            {
                                this.usersRequest.createUserLogin = person.fullName;
                            }
                            person = people.find(w => w.nationalId.includes(this.usersRequest.changeStatusUserLogin));
                            if(person)
                            {
                                this.usersRequest.changeStatusUserLogin = person.fullName;
                            }
                        }
                    },
                    (error) => this.onError("فردی یافت نشد."));
            });
            this.usersRequest = usersRequest;
        });
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
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
