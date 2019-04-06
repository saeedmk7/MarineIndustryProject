import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer} from '@angular/platform-browser';
import {SERVER_API_URL} from "app/app.constants";

@Pipe({ name: 'safe' })
export class SafePipe implements PipeTransform {
    constructor(private sanitizer: DomSanitizer) {}
    transform(url) {
        url = SERVER_API_URL + url;
        return this.sanitizer.bypassSecurityTrustResourceUrl(url);
    }
}
