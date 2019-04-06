import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {IUsersRequestMarineSuffix} from 'app/shared/model/users-request-marine-suffix.model';
import {UsersRequestMarineSuffixService} from './users-request-marine-suffix.service';
import {IDocumentMarineSuffix} from 'app/shared/model/document-marine-suffix.model';
import {DocumentMarineSuffixService} from 'app/entities/document-marine-suffix';
import {Principal, User, UserService} from "app/core";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {PersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";

@Component({
    selector: 'mi-users-request-marine-suffix-update',
    templateUrl: './users-request-marine-suffix-update.component.html'
})
export class UsersRequestMarineSuffixUpdateComponent implements OnInit {
    private _usersRequest: IUsersRequestMarineSuffix;
    isSaving: boolean;

    documents: IDocumentMarineSuffix[];
    createDate: string;
    modifyDate: string;
    currentAccount: any;
    currentUserFullName: string = "";
    answer: string;
    currentRequestStatus: RequestStatus;
    isAdmin: boolean = false;
    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private usersRequestService: UsersRequestMarineSuffixService,
        private documentService: DocumentMarineSuffixService,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {}

    ngOnInit() {

        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ usersRequest }) => {
            this.usersRequest = usersRequest;
            this.currentRequestStatus = this.usersRequest.requestStatus;
        });
        this.principal.identity().then(account => {

            this.currentAccount = account;
            if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            this.currentUserFullName = document.getElementById('currenUserFullNameTopBar').textContent;
        });
    }

    save() {

        if(!this.currentUserFullName)
            this.currentUserFullName = document.getElementById('currenUserFullNameTopBar').textContent;
        this.isSaving = true;
        if (this.usersRequest.id !== undefined) {
            if(this.usersRequest.requestStatus != this.currentRequestStatus)
                this.usersRequest.changeStatusUserLogin = this.currentAccount.login;

            this.usersRequest.conversation += "\r\n" + this.currentUserFullName + ": " + this.answer;
            this.subscribeToSaveResponse(this.usersRequestService.update(this.usersRequest));
        } else {
            this.usersRequest.requestStatus = RequestStatus.NEW;
            this.usersRequest.changeStatusUserLogin = this.currentAccount.login;
            this.usersRequest.conversation = this.currentUserFullName + ": " + this.usersRequest.description;
            this.subscribeToSaveResponse(this.usersRequestService.create(this.usersRequest));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUsersRequestMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IUsersRequestMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }
    previousState() {
        window.history.back();
    }
    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackDocumentById(index: number, item: IDocumentMarineSuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get usersRequest() {
        return this._usersRequest;
    }

    set usersRequest(usersRequest: IUsersRequestMarineSuffix) {
        this._usersRequest = usersRequest;
    }
}
