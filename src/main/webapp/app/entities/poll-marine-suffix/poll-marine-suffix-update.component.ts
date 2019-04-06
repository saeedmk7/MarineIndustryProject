import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPollMarineSuffix } from 'app/shared/model/poll-marine-suffix.model';
import { PollMarineSuffixService } from './poll-marine-suffix.service';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';

@Component({
    selector: 'mi-poll-marine-suffix-update',
    templateUrl: './poll-marine-suffix-update.component.html'
})
export class PollMarineSuffixUpdateComponent implements OnInit {
    private _poll: IPollMarineSuffix;
    isSaving: boolean;

    finalniazsanjireports: IFinalNiazsanjiReportMarineSuffix[];
    createDate: string;
    modifyDate: string;
    archivedDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private pollService: PollMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ poll }) => {
            this.poll = poll;
        });
        this.finalNiazsanjiReportService.query().subscribe(
            (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => {
                this.finalniazsanjireports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.poll.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.poll.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        this.poll.archivedDate = moment(this.archivedDate, DATE_TIME_FORMAT);
        if (this.poll.id !== undefined) {
            this.subscribeToSaveResponse(this.pollService.update(this.poll));
        } else {
            this.subscribeToSaveResponse(this.pollService.create(this.poll));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPollMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IPollMarineSuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackFinalNiazsanjiReportById(index: number, item: IFinalNiazsanjiReportMarineSuffix) {
        return item.id;
    }
    get poll() {
        return this._poll;
    }

    set poll(poll: IPollMarineSuffix) {
        this._poll = poll;
        this.createDate = moment(poll.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(poll.modifyDate).format(DATE_TIME_FORMAT);
        this.archivedDate = moment(poll.archivedDate).format(DATE_TIME_FORMAT);
    }
}
