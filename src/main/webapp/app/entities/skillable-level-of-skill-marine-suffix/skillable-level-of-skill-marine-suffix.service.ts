import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISkillableLevelOfSkillMarineSuffix } from 'app/shared/model/skillable-level-of-skill-marine-suffix.model';

type EntityResponseType = HttpResponse<ISkillableLevelOfSkillMarineSuffix>;
type EntityArrayResponseType = HttpResponse<ISkillableLevelOfSkillMarineSuffix[]>;

@Injectable({ providedIn: 'root' })
export class SkillableLevelOfSkillMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/skillable-level-of-skills';

    constructor(private http: HttpClient) {}

    create(skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(skillableLevelOfSkill);
        return this.http
            .post<ISkillableLevelOfSkillMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(skillableLevelOfSkill);
        return this.http
            .put<ISkillableLevelOfSkillMarineSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISkillableLevelOfSkillMarineSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISkillableLevelOfSkillMarineSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix): ISkillableLevelOfSkillMarineSuffix {
        const copy: ISkillableLevelOfSkillMarineSuffix = Object.assign({}, skillableLevelOfSkill, {
            createDate:
                skillableLevelOfSkill.createDate != null && skillableLevelOfSkill.createDate.isValid()
                    ? skillableLevelOfSkill.createDate.toJSON()
                    : null,
            modifyDate:
                skillableLevelOfSkill.modifyDate != null && skillableLevelOfSkill.modifyDate.isValid()
                    ? skillableLevelOfSkill.modifyDate.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createDate = res.body.createDate != null ? moment(res.body.createDate) : null;
        res.body.modifyDate = res.body.modifyDate != null ? moment(res.body.modifyDate) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((skillableLevelOfSkill: ISkillableLevelOfSkillMarineSuffix) => {
            skillableLevelOfSkill.createDate = skillableLevelOfSkill.createDate != null ? moment(skillableLevelOfSkill.createDate) : null;
            skillableLevelOfSkill.modifyDate = skillableLevelOfSkill.modifyDate != null ? moment(skillableLevelOfSkill.modifyDate) : null;
        });
        return res;
    }
}
