import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { result } from 'app/plugin/upload-file/result.model';
import { map } from 'rxjs/operators';
import { ISettingsModel } from 'app/account/settings/settings.model';
const jsreport = require('jsreport-browser-client-dist/jsreport');

@Injectable({ providedIn: 'root' })
export class PersonEducationalRecordsMarineSuffixService {
    private resourceUrl = SERVER_API_URL + 'api/saveUserImage';

    constructor(private http: HttpClient) {}

    printPage(hostName, personId) {
        debugger;
        jsreport.serverUrl = `http://${hostName}:5488`;
        //const formdata = { template: { name: 'PersonEducationalRecords' }, data: { personId: personId } };

        var request = {
            template: {
                name: 'PersonEducationalRecords'
            },
            data: { personId: personId }
        };

        jsreport.renderAsync(request).then(function(res) {
            //display report in the new tab
            jsreport.render('_blank', request);

            //display report in placeholder with id reportPlaceholder

            jsreport.render('reportPlaceholder', request);

            //display report in placeholder element
            jsreport.render(document.getElementById('reportPlaceholder'), request);

            //open download dialog for report
            jsreport.download('myReport.pdf', request);
        });
        /*return this.http.post(url, formdata).subscribe(resp => {
            console.log(resp);
        });*/
    }

    pushFileToStorage(file: File, login: string): Observable<HttpResponse<ISettingsModel>> {
        let formdata: FormData = new FormData();

        formdata.append('file', file);
        formdata.append('login', login);

        return this.http
            .post(SERVER_API_URL + 'api/saveUserImage', formdata, { observe: 'response' })
            .pipe(map((res: HttpResponse<ISettingsModel>) => res)); //.pipe(map(makereturnvalue.bind(this)));

        //return this.http.post(this.resourceUrl, formdata);

        //.pipe(map(makereturnvalue.bind(this)));

        function makereturnvalue(res) {
            let returnval = new result();
            if (res.headers.get('status') == 200) {
                returnval.isOk = true;
                returnval.message = res.statusText;
            } else {
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

        return this.http
            .post(SERVER_API_URL + 'api/saveUserImage', formdata, { observe: 'response' })
            .pipe(map((res: HttpResponse<ISettingsModel>) => res)); //.pipe(map(makereturnvalue.bind(this)));

        //return this.http.post(this.resourceUrl, formdata);

        //.pipe(map(makereturnvalue.bind(this)));

        function makereturnvalue(res) {
            let returnval = new result();
            if (res.headers.get('status') == 200) {
                returnval.isOk = true;
                returnval.message = res.statusText;
            } else {
                returnval.isOk = false;
                returnval.message = res.statusText;
            }
            return returnval;
            //return this.convertItemFromServer(jsonResponse);
        }
    }
}
