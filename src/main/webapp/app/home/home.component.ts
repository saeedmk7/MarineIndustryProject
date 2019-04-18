import {Component, OnDestroy, OnInit} from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager, JhiDateUtils, JhiLanguageService, JhiAlertService} from 'ng-jhipster';

import { LoginModalService, Principal, Account } from 'app/core';
import {Chart} from "angular-highcharts";
import {HomeCalendarModel} from 'app/home/home-calendar.model';
import {SlideInOutAnimation} from 'app/shared/animations';

import * as moment from 'jalali-moment';
import {AnnouncementMarineSuffixService} from "app/entities/announcement-marine-suffix";
import {HttpErrorResponse, HttpHeaders, HttpResponse} from "@angular/common/http";
import {IAnnouncementMarineSuffix} from "app/shared/model/announcement-marine-suffix.model";
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {ActivatedRoute, NavigationStart, Router} from "@angular/router";
import {Subscription} from "rxjs";
import * as $ from 'jquery';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {SearchPanelModel} from "app/shared/model/custom/searchbar.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {OrganizationChartMarineSuffixService} from "app/entities/organization-chart-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {FinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {FinalNiazsanjiReportMarineSuffixService} from "app/entities/final-niazsanji-report-marine-suffix";
import {IChartResult} from "app/shared/model/custom/chart-result";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";


@Component({
    selector: 'mi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.scss'
    ],
    animations: [SlideInOutAnimation]
})
export class HomeComponent implements OnInit, OnDestroy {
    organizationcharts: IOrganizationChartMarineSuffix[];
    account: Account;
    modalRef: NgbModalRef;
   /* welcomeState = 'out';
    centerLinksState = 'out';
    state = 'leave';*/
    isfa: boolean;
    calendars : HomeCalendarModel[] = [];
    announcements: IAnnouncementMarineSuffix[];
    show: boolean = true;
    niazsanjishow: boolean = false;
    eventSubscriber: Subscription;
    pageName: string;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    badError: string;
    personHourChart: Chart;
    priceCostChart: Chart;
    chart: Chart;
    chartResults: IChartResult[] = [];
    priceCostSeries: any;
    personHourSeries: any;
    categories: any[];
    niazsanjiYear: number;
    years: any[];
    selectedNiazsanjiYear: number;
    constructor(
        private principal: Principal,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        private personService: PersonMarineSuffixService,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager,
        private languageManager: JhiLanguageService,
        private jhiAlertService: JhiAlertService,
        private announcementService: AnnouncementMarineSuffixService,
        private convertObjectDatesService : ConvertObjectDatesService,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private treeUtilities: TreeUtilities
    ) {
        this.isfa = languageManager.currentLang == 'fa';
        this.years = GREGORIAN_START_END_DATE.map(a => a.year);
        this.selectedNiazsanjiYear = +moment().format('jYYYY');

        this.eventSubscriber = router.events.subscribe((val : NavigationStart) => {
                let res = this.getParameterByName("pageName", val.url); //window.location.href;
                if (res)
                    this.pageName = res;
                else
                    this.pageName = 'home';
        });
    }
    deleteElement(i)
    {

        $('#' + i).remove();
    }
    toggleColappse(i)
    {
        $('#' + i).collapse('toggle');
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
    changePage(pageName){

        var url = window.location.href;
        var indexOf = url.indexOf('?');
        if (indexOf > -1) {
            var extra = url.slice(indexOf, url.length);
            url = url.replace(extra, '');
        }
        url += '?';
        url = url + "pageName=" + pageName;
        window.location.href = url;
    }
    ngOnInit() {

        this.principal.identity().then((account) => {

            this.account = account;
            this.setRoles(account);
            if(!this.isAuthenticated()){
                this.login();
            }
            if(!this.account.personId){
                this.badError = "برای کاربری شما فردی تخصیص داده نشده لطفا با مدیریت سامانه تماس بگیرید و مراتب را اطلاع دهید.";
            }
            else{
                this.personService.find(this.account.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    if(!resp.body.organizationChartId){
                        this.badError = "موقعیت در چارت سازمانی برای شما تنظیم نشده است، لطفا مراتب را با مدیریت سامانه در میان بگذارید.";
                    }
                })
            }
            if(this.isSuperUsers)
                this.prepareOrgChart();
        });
        this.registerAuthenticationSuccess();
        let criteria = [
            {key: 'isActive.equals', value: true}
        ];
        this.announcementService
            .query({
                page: 0,
                size: 5,
                criteria,
                sort: ['id','desc']
            })
            .subscribe(
                (res: HttpResponse<IAnnouncementMarineSuffix[]>) => this.loadNews(res.body),
                (res: HttpErrorResponse) => this.onError(res.message)
            );

        /*this.welcomeState = this.welcomeState === 'out' ? 'in' : 'out';
        this.centerLinksState = this.centerLinksState === 'out' ? 'in' : 'out';*/


        /*let now : any = new Date();
        let colors : string[] = ["#C8C8C8","#A0A0A0","#787878","#282828","#787878","#A0A0A0","#C8C8C8"]
        let offset : any[] = [this.addDays(now,-3),this.addDays(now,-2),this.addDays(now,-1),now,this.addDays(now,+1),this.addDays(now,+2),this.addDays(now,+3)];
        //this.calendars = [offset.length];
        offset.forEach((w,index) => {
            let calendar : HomeCalendarModel = new HomeCalendarModel();
            if(this.isfa) {
                calendar.date = moment(w).locale('fa').format('YYYY/MM/DD');
                calendar.title = moment(w).locale('fa').format('dddd');
            }
            else {
                calendar.date = moment(w).locale('en').format('YYYY/MM/DD');
                calendar.title = moment(w).locale('en').format('dddd');
            }
            calendar.color = colors[index];
            this.calendars.push(calendar);
        });*/
    }
    prepareOrgChart(){
        if(this.organizationChartService.organizationchartsAll)
        {
            this.organizationcharts = this.organizationChartService.organizationchartsAll;
            this.makeChartResult();
        }
        else {
            this.organizationChartService.query().subscribe(
                (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                    this.organizationcharts = res.body;
                    this.makeChartResult();
                },
                (res: HttpErrorResponse) => this.onError(res.message));
        }

    }

    makeChartResult(){

        const groups = this.organizationcharts.filter(a => a.parentId == null);
        this.categories = groups.sort((a,b) => (a.id > b.id) ? 1 : (a.id < b.id) ? -1 : 0).map(a => a.title);
        this.finalNiazsanjiReportService.getChartResult(this.selectedNiazsanjiYear).subscribe((resp: HttpResponse<IChartResult[]>) => {
            this.chartResults = resp.body;
            this.makeSeries();
        });

        /*const groupCount = groups.length;
        let index = 0;
        groups.forEach((group: IOrganizationChartMarineSuffix) => {
              let childs = this.treeUtilities.getAllOfChilderenIdsOfThisId(this.organizationcharts, group.id).filter(this.treeUtilities.onlyUnique);

              this.finalNiazsanjiReportService.getChartResult(childs).subscribe((resp: HttpResponse<IChartResult>) => {
                  resp.body.groupId = group.id;
                  this.chartResults.push(resp.body);
                  index++;
                  if(index == groupCount)
                      this.makeSeries();
              });
        });*/


    }

    makeSeries(){

        this.priceCostSeries = [{
            name: "اجرا شده",
            data: this.chartResults.sort((a,b) => (a.groupId > b.groupId) ? 1 : (a.groupId < b.groupId) ? -1 : 0).map(a => a.priceCostFinished),
            color: "lightgreen"
        },{
            name: "اجرا نشده",
            data: this.chartResults.sort((a,b) => (a.groupId > b.groupId) ? 1 : (a.groupId < b.groupId) ? -1 : 0).map(a => a.priceCostNew),
            color: "red"
        }];
        this.personHourSeries = [{
            name: "اجرا شده",
            data: this.chartResults.sort((a,b) => (a.groupId > b.groupId) ? 1 : (a.groupId < b.groupId) ? -1 : 0).map(a => a.educationalModuleTotalHourFinished),
            color: "lightgreen"
        },{
            name: "اجرا نشده",
            data: this.chartResults.sort((a,b) => (a.groupId > b.groupId) ? 1 : (a.groupId < b.groupId) ? -1 : 0).map(a => a.educationalModuleTotalHourNew),
            color: "red"
        }];
        this.loadChart();
    }
    loadChart(){

        this.personHourChart = new Chart({
            chart: {
                type: 'column',
                style: {
                    fontFamily: 'IranSans, SansSerif, IranYekan, B Nazanin, B Badr, Tahoma, Times New Roman'
                }
            },
            lang: {
                decimalPoint: ',',
                thousandsSep: '.'
            },
            title: {
                text: 'نمودار نفر/ساعت گروه های سازمان'
            },
            xAxis: {
                categories: this.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'نفر ساعت'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: this.personHourSeries,
            credits: {
                enabled: false
            }
        });
        this.priceCostChart = new Chart({
            chart: {
                type: 'column',
                style: {
                    fontFamily: 'IranSans, SansSerif, IranYekan, B Nazanin, B Badr, Tahoma, Times New Roman'
                }
            },
            lang: {
                decimalPoint: ',',
                thousandsSep: '.'
            },
            title: {
                text: 'نمودار هزینه گروه های سازمان'
            },
            xAxis: {
                categories: this.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'میزان هزینه'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: this.priceCostSeries,
            credits: {
                enabled: false
            }
        });

    }
    changeChartProps(){

        $('.highcharts-credits').textContent = '';
        $('.highcharts-root').addClass('chartFont');
    }
    private loadNews(data: IAnnouncementMarineSuffix[]) {
        this.announcements = this.convertObjectDatesService.changeArrayDate(data);
    }
    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    addDays(date, days) {
        var result = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    }

    back2Main(){
        this.niazsanjishow = false;

        setTimeout(() => {         //replaced function() by ()=>
            this.show = true;
        }, 500);
    }
    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    // add point to chart serie
    add() {
        this.chart.addPoint(Math.floor(Math.random() * 10));
    }

    ngOnDestroy(): void {
        this.eventManager.destroy(this.eventSubscriber);
    }

    private setRoles(account: any){
        if(account) {
            if (account.authorities.find(a => a == "ROLE_ADMIN") !== undefined)
                this.isAdmin = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_AMOZESH") !== undefined)
                this.isModirAmozesh = true;
            if (account.authorities.find(a => a == "ROLE_MODIR_KOL_AMOZESH") !== undefined)
                this.isModirKolAmozesh = true;
            if (account.authorities.find(a => a == "ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") !== undefined)
                this.isKarshenasArshadAmozeshSazman = true;

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                this.isSuperUsers = true;
        }
    }
}
