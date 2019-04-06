import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import {result} from "app/plugin/upload-file/result.model";
import {map} from "rxjs/operators";
import {IEffectivenessIndexMarineSuffix} from "app/shared/model/effectiveness-index-marine-suffix.model";
import {ISettingsModel} from "app/account/settings/settings.model";

@Injectable({ providedIn: 'root' })
export class SettingsService {
    constructor(private http: HttpClient) {}
    private resourceUrl = SERVER_API_URL + 'api/saveUserImage';

    pushFileToStorage(file: File, login: string): Observable<HttpResponse<ISettingsModel>> {

        let formdata: FormData = new FormData();

        formdata.append('file', file);
        formdata.append('login', login);

        return this.http.post(SERVER_API_URL + 'api/saveUserImage',formdata,{ observe: 'response' })
            .pipe(map((res: HttpResponse<ISettingsModel>) => res));//.pipe(map(makereturnvalue.bind(this)));

        //return this.http.post(this.resourceUrl, formdata);

        //.pipe(map(makereturnvalue.bind(this)));

        function makereturnvalue(res) {

            let returnval = new result();
            if (res.headers.get('status') == 200) {
                returnval.isOk = true;
                returnval.message = res.statusText;
            }
            else {
                returnval.isOk = false;
                returnval.message = res.statusText;
            }
            return returnval;
            //return this.convertItemFromServer(jsonResponse);
        }
        }

    downloadImage(file: File): Observable<HttpResponse<ISettingsModel>> {

        let formdata: FormData = new FormData();

        formdata.append('file', file);

        return this.http.post(SERVER_API_URL + 'api/saveUserImage',formdata,{ observe: 'response' })
            .pipe(map((res: HttpResponse<ISettingsModel>) => res));//.pipe(map(makereturnvalue.bind(this)));

        //return this.http.post(this.resourceUrl, formdata);

        //.pipe(map(makereturnvalue.bind(this)));

        function makereturnvalue(res) {

            let returnval = new result();
            if (res.headers.get('status') == 200) {
                returnval.isOk = true;
                returnval.message = res.statusText;
            }
            else {
                returnval.isOk = false;
                returnval.message = res.statusText;
            }
            return returnval;
            //return this.convertItemFromServer(jsonResponse);
        }
    }
}
