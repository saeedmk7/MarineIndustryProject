import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs";
import {SERVER_API_URL} from "app/app.constants";

@Injectable({ providedIn: 'root' })
export class ImportDataService {
    private resourceUrl = SERVER_API_URL + 'api/import-excel';

    constructor(private http: HttpClient) {}

    create(formdata: FormData): Observable<HttpEvent<{}>> {

        /*return this.http
            .post<any>(this.resourceUrl, formdata, { observe: 'response', reportProgress:true });*/
        const req = new HttpRequest('POST', this.resourceUrl, formdata, {
            reportProgress: true,
            responseType: 'text'
        });

        return this.http.request(req);
    }
}
