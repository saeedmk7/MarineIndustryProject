import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {NavigationEnd, Router} from "@angular/router";
import {JhiEventManager} from "ng-jhipster";
import {Subscription} from "rxjs";
@Component({
    selector: 'search-panel',
    templateUrl: './search-panel.component.html'
})
export class SearchPanelComponent implements OnInit, OnDestroy {
    @Input('searchPanelModel') searchPanelModel: SearchPanelModel[];
    lastUrl: string = '';
    eventSubscriber: Subscription;
    constructor(private router: Router, private eventManager: JhiEventManager) {
    }

    ngOnInit() {

        this.eventSubscriber = this.router.events.subscribe((val: NavigationEnd) => {
            if(val instanceof NavigationEnd) {
                if(this.lastUrl != val.urlAfterRedirects){
                    this.lastUrl = val.urlAfterRedirects;
                    if(!this.lastUrl.includes('popup'))
                    {
                        this.search();
                    }
                }
            }
        });

        console.log("I got:", this.searchPanelModel);
    }
    search(){
        let url = window.location.href;
        let criteria = [];
        this.searchPanelModel.forEach((a) => {
           let value = this.getParameterByName(a.fieldName,url);
           a.selectedValue = value;
           if(value != "undefined")
           {
               criteria.push({
                   key: a.fieldName + '.' + a.searchType,
                   value: value
               });
           }
        });

        this.eventManager.broadcast({ name: 'marineindustryprojApp.criteria', content: criteria });
    }
    onSubmit(f: any){


        let url = this.deleteQueryString();
        for (let j = 0; j < this.searchPanelModel.length; j++) {
            let value = f.value[this.searchPanelModel[j].fieldName];
            if(value)
                this.searchPanelModel[j].selectedValue = value;
            else {
                /*if(this.searchPanelModel[j].type == "text")
                    this.searchPanelModel[j].selectedValue = "";
                else
                    this.searchPanelModel[j].selectedValue = 0;*/
                this.searchPanelModel[j].selectedValue = "";
            }
            url = this.appendQueryString(this.searchPanelModel[j].fieldName, this.searchPanelModel[j].selectedValue, url);
        }
        window.location.href = url; //.slice(0,url.length-1);
    }
    getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
    deleteQueryString(): string{
        let url = window.location.href;
        let indexOf = url.indexOf('?');
        if (indexOf > -1) {
            let extra = url.slice(indexOf, url.length);
            url = url.replace(extra, '');
        }
        return url + '?';
    }
    appendQueryString(name, value, url): string{
        /*let indexOf = url.indexOf('&');
        if (indexOf > -1) {*/
            url += name + "=" + value + "&";
        /*}
        else {
            url += name + "=" + value;
        }*/
        return url;
    }

    ngOnDestroy(): void {
        this.eventManager.destroy(this.eventSubscriber);
    }
   /* getParameterByName(key, query) {
        if (!query)
            query = window.location.search;
        let re = new RegExp("[?|&]" + key + "=(.*?)&");
        let matches = re.exec(query + "&");
        if (!matches || matches.length < 2)
            return "";
        return decodeURIComponent(matches[1].replace("+", " "));
    }
    setUrlEncodedKey(key, value, query) {

        query = query || window.location.search;
        let q = query + "&";
        let re = new RegExp("[?|&]" + key + "=.*?&");
        if (!re.test(q))
            q += key + "=" + encodeURI(value);
        else
            q = q.replace(re, "&" + key + "=" + encodeURIComponent(value) + "&");
        q = q.trimStart("&").trimEnd("&");
        return q[0]=="?" ? q : q = "?" + q;
    }
    trimEnd(c: string) {
        if (c)
            return this.replace(new RegExp(c.escapeRegExp() + "*$"), '');
        return this.replace(/\s+$/, '');
    }
    trimStart(c: string) {
        if (c)
            return c.replace(new RegExp("^" + c.escapeRegExp() + "*"), '');
        return this.replace(/^\s+/, '');
    }

    escapeRegExp() {
        return this.replace(/[.*+?^${}()|[\]\/\\]/g, "\\$0");
    };*/
}
