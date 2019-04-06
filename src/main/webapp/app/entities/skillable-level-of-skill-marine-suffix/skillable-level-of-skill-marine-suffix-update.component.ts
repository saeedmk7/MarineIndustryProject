import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';
import { SkillableLevelOfSkillMarineSuffixService } from './skillable-level-of-skill-marine-suffix.service';

@Component({
    selector: 'mi-skillable-level-of-skill-marine-suffix-update',
    templateUrl: './skillable-level-of-skill-marine-suffix-update.component.html'
})
export class SkillableLevelOfSkillMarineSuffixUpdateComponent implements OnInit {
    private _skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix;
    isSaving: boolean;
    createDate: string;
    modifyDate: string;

    constructor(private skillableLevelOfSkillService: SkillableLevelOfSkillMarineSuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ skillableLevelOfSkill }) => {
            this.skillableLevelOfSkill = skillableLevelOfSkill;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.skillableLevelOfSkill.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        this.skillableLevelOfSkill.modifyDate = moment(this.modifyDate, DATE_TIME_FORMAT);
        if (this.skillableLevelOfSkill.id !== undefined) {
            this.subscribeToSaveResponse(this.skillableLevelOfSkillService.update(this.skillableLevelOfSkill));
        } else {
            this.subscribeToSaveResponse(this.skillableLevelOfSkillService.create(this.skillableLevelOfSkill));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISkillableLevelOfSkillMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<ISkillableLevelOfSkillMarineSuffix>) => this.onSaveSuccess(),
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
    get skillableLevelOfSkill() {
        return this._skillableLevelOfSkill;
    }

    set skillableLevelOfSkill(skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix) {
        this._skillableLevelOfSkill = skillableLevelOfSkill;
        this.createDate = moment(skillableLevelOfSkill.createDate).format(DATE_TIME_FORMAT);
        this.modifyDate = moment(skillableLevelOfSkill.modifyDate).format(DATE_TIME_FORMAT);
    }
}
