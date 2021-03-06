import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { SearchPanelModel } from 'app/shared/model/custom/searchbar.model';
import { NavigationEnd, Router } from '@angular/router';
import { JhiEventManager } from 'ng-jhipster';
import { Subscription } from 'rxjs';
import { FormGroup } from '@angular/forms';
import * as $ from 'jquery';
import { ITEMS_PER_PAGE } from 'app/shared';
@Component({
    selector: 'search-panel',
    templateUrl: './search-panel.component.html'
})
export class SearchPanelComponent implements OnInit, OnDestroy {
    @Input('searchPanelModel') searchPanelModel: SearchPanelModel[];
    @Input('returnSearchParam') returnSearchParam: boolean = false;
    @Output() searchParamResult = new EventEmitter();
    @Input('immediatelyLoad') immediatelyLoad: boolean = true;
    //@Input('size') size: number = 20;
    @Output() sizeChange = new EventEmitter();
    private _size: number = ITEMS_PER_PAGE;
    @Input('size')
    set size(value: number) {
        if (value && value != this._size) {
            this._size = +value;
            this.triggerSubmitButton();
        }
    }
    get size() {
        this.sizeChange.emit(this._size);
        return this._size;
    }
    @Output() pageChange = new EventEmitter();
    private _page: number = 1;
    @Input('page')
    set page(value: number) {
        if (value && value != this._page) {
            this._page = +value;
            this.triggerSubmitButton();
        }
    }
    get page() {
        this.pageChange.emit(this._page);
        return this._page;
    }
    private _predicate: string = '';
    @Input('predicate')
    set predicate(value: string) {
        if (value && value != this._predicate) {
            this._predicate = value;
        }
    }
    private _reverse: string = '';
    @Input('reverse')
    set reverse(value: string) {
        if (value && value != this._reverse) {
            this._reverse = value;
        }
    }
    lastUrl: string = '';
    eventSubscriber: Subscription;

    criticalArea: boolean = false;
    pagingAction: boolean = false;
    constructor(private router: Router, private eventManager: JhiEventManager) {}

    triggerSubmitButton() {
        if (!this.criticalArea) {
            this.criticalArea = true;
            setTimeout(() => {
                this.pagingAction = true;
                $('#submitBtn').trigger('click');
                this.criticalArea = false;
            }, 100);
        }
    }
    ngOnInit() {
        this.eventSubscriber = this.router.events.subscribe((val: NavigationEnd) => {
            if (val instanceof NavigationEnd) {
                if (this.lastUrl != val.urlAfterRedirects) {
                    this.lastUrl = val.urlAfterRedirects;
                    if (!this.lastUrl.includes('popup')) {
                        this.search();
                    }
                }
            }
        });

        setTimeout(() => this.search(), 1000);

        console.log('I got:', this.searchPanelModel);
    }
    search() {
        let url = window.location.href;
        let criteria = [];
        this.getPagingParameter(url);
        this.fakeLoad();
        this.searchPanelModel.forEach(a => {
            let value = this.getParameterByName(a.fieldName, url);
            if (!a.selectedValue) {
                a.selectedValue = value;
            }
            if (value) {
                a.selectedValue = value;
            }
            if (value != 'undefined') {
                criteria.push({
                    key: a.fieldName + '.' + a.searchType,
                    value: value
                });
            }
            /*if(a.type == 'radio')
           {
               a.values.forEach((w) => {
                   if(w.checked)
                   {
                       //document.getElementById(w.id).checked = true;
                   }
               })
           }*/
        });

        if (this.immediatelyLoad) {
            this.eventManager.broadcast({ name: 'marineindustryprojApp.criteria', content: criteria });
        } else {
            this.immediatelyLoad = true;
        }
    }
    fakeLoad() {
        this.size;
        this.page;
    }
    getPagingParameter(url) {
        const size = +this.getParameterByName('size', url);
        this._size = size ? size : this.size;
        const page = +this.getParameterByName('page', url);
        this._page = page ? page : this.page;
        this.predicate = this.getParameterByName('predicate', url);
        this.reverse = this.getParameterByName('reverse', url);
    }
    onSubmit(f: any) {
        let url = this.deleteQueryString();
        for (let j = 0; j < this.searchPanelModel.length; j++) {
            let value = f.value[this.searchPanelModel[j].fieldName];
            if (value) this.searchPanelModel[j].selectedValue = value;
            else {
                /*if(this.searchPanelModel[j].type == "text")
                    this.searchPanelModel[j].selectedValue = "";
                else
                    this.searchPanelModel[j].selectedValue = 0;*/
                this.searchPanelModel[j].selectedValue = '';
            }
            url = this.appendQueryString(this.searchPanelModel[j].fieldName, this.searchPanelModel[j].selectedValue, url);
        }

        url = this.appendPagingQueryString(url, !this.pagingAction);
        this.pagingAction = false;

        window.location.href = url; //.slice(0,url.length-1);
    }
    onSubmitReturnSearchParam(f: any) {
        let url = '';
        for (let j = 0; j < this.searchPanelModel.length; j++) {
            let value = f.value[this.searchPanelModel[j].fieldName];
            if (value) this.searchPanelModel[j].selectedValue = value;
            else {
                /*if(this.searchPanelModel[j].type == "text")
                    this.searchPanelModel[j].selectedValue = "";
                else
                    this.searchPanelModel[j].selectedValue = 0;*/
                this.searchPanelModel[j].selectedValue = '';
            }
            url = this.appendQueryString(this.searchPanelModel[j].fieldName, this.searchPanelModel[j].selectedValue, url);
        }

        let criteria = [];
        this.searchPanelModel.forEach(a => {
            let value = this.getParameterByName(a.fieldName, url);
            if (!a.selectedValue) {
                a.selectedValue = value;
            }
            if (value) {
                a.selectedValue = value;
            }
            if (value != 'undefined') {
                criteria.push({
                    key: a.fieldName + '.' + a.searchType,
                    value: value
                });
            }
        });
        this.searchParamResult.emit(JSON.stringify(criteria));
    }
    appendPagingQueryString(url: string, resetPage: boolean = false): string {
        url = this.appendQueryString('size', this.size, url);
        url = this.appendQueryString('page', resetPage ? 1 : this.page, url);
        url = this.appendQueryString('predicate', this._predicate, url);
        url = this.appendQueryString('reverse', this._reverse, url);

        return url;
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
    deleteQueryString(): string {
        let url = window.location.href;
        let indexOf = url.indexOf('?');
        if (indexOf > -1) {
            let extra = url.slice(indexOf, url.length);
            url = url.replace(extra, '');
        }
        return url + '?';
    }
    appendQueryString(name, value, url): string {
        /*let indexOf = url.indexOf('&');
        if (indexOf > -1) {*/
        url += name + '=' + value + '&';
        /*}
        else {
            url += name + "=" + value;
        }*/
        return url;
    }

    ngOnDestroy(): void {
        this.eventManager.destroy(this.eventSubscriber);
    }
    reset() {
        const url = this.deleteQueryString();
        window.location.href = url;
    }
}
