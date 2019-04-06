import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IAuthority } from 'app/shared/model/authority.model';
import { AuthorityService } from './authority.service';

@Component({
    selector: 'mi-authority-update',
    templateUrl: './authority-update.component.html'
})
export class AuthorityUpdateComponent implements OnInit {
    private _authority: IAuthority;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private authorityService: AuthorityService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ authority }) => {
            this.authority = authority;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.authority.name = this.authority.name.toUpperCase();
        this.subscribeToSaveResponse(this.authorityService.create(this.authority));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAuthority>>) {
        result.subscribe(
            (res: HttpResponse<IAuthority>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get authority() {
        return this._authority;
    }

    set authority(authority: IAuthority) {
        this._authority = authority;
    }
}
