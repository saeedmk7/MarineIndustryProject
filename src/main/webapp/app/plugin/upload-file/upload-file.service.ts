import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {SERVER_API_URL} from "../../app.constants";
import {Http,Response} from '@angular/http';
import {result} from "./result.model";
import {map} from "rxjs/internal/operators/map";

@Injectable()
export class UploadFileService {

    constructor(private http: Http) {}
    private resourceUrl = SERVER_API_URL + 'api/import-excel';
    pushFileToStorage(file: File,entityName: string): Observable<result> {

        let formdata: FormData = new FormData();

        formdata.append('file', file);
        formdata.append('entityName', entityName);

        /*const req = new HttpRequest('POST', '/api/importexcel', formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
*/

        return this.http.post(this.resourceUrl, formdata).pipe(map(makereturnvalue.bind(this)));

        function makereturnvalue(res) {
            let returnval = new result();
            if(res.headers.get('status') == 200)
            {
                returnval.isOk = true;
                returnval.message = res.statusText;
            }
            else
            {
                returnval.isOk = false;
                returnval.message = res.statusText;
            }
            return returnval;
            /*return this.convertItemFromServer(jsonResponse);*/
        }

    }

    /*getFiles(): Observable<string[]> {
        return this.http.get('/getallfiles')
    }*/
}
