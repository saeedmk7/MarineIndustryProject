import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IRequestEducationalModuleMarineSuffix } from 'app/shared/model/request-educational-module-marine-suffix.model';
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {Principal} from "app/core";
import {Observable} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {RequestEducationalModuleMarineSuffixService} from "app/entities/request-educational-module-marine-suffix/request-educational-module-marine-suffix.service";

@Component({
    selector: 'mi-request-educational-module-marine-suffix-detail',
    templateUrl: './request-educational-module-marine-suffix-detail.component.html'
})
export class RequestEducationalModuleMarineSuffixDetailComponent implements OnInit {
    requestEducationalModule: IRequestEducationalModuleMarineSuffix;

    constructor(private dataUtils: JhiDataUtils,
        private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private convertObjectDatesService: ConvertObjectDatesService,
        private requestEducationalModuleMarineSuffixService: RequestEducationalModuleMarineSuffixService) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ requestEducationalModule }) => {
            this.principal.identity().then(account => {

                this.requestEducationalModule = requestEducationalModule;
                if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                {
                    if(this.requestEducationalModule.requestStatus === 'NEW') {
                        this.requestEducationalModule.requestStatus = RequestStatus.READ;
                        this.subscribeToSaveResponse(this.requestEducationalModuleMarineSuffixService.update(this.requestEducationalModule));
                    }
                }
                this.requestEducationalModule = this.convertObjectDatesService.changeDate(requestEducationalModule);
            });

        });
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IRequestEducationalModuleMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<IRequestEducationalModuleMarineSuffix>) => this.onSaveSuccess(),
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
