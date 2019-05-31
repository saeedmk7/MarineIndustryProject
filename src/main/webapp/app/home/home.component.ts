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
import {IHomePageNiazsanjiReport} from "app/shared/model/custom/niazsanji-chart-result";
import {IHomePagePersonHourChart} from "app/shared/model/custom/home-page-person-hour-chart";
import {IHomePagePersonEducationalModule} from "app/shared/model/custom/home-page-person-educational-module";
import {IPlanningAndRunMonthReport} from "app/shared/model/custom/planning-month-report";
import {MONTHS} from "app/shared/constants/months.constants";


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
    homePageNiazsanjiReport: IHomePageNiazsanjiReport = {};
    homePagePersonHourChart: IHomePagePersonHourChart = {};
    homePagePersonEducationalModules: IHomePagePersonEducationalModule[] = [];
    planningAndRunMonthReports: IPlanningAndRunMonthReport[] = [];
    isHomePageCharts: boolean = true;
    account: Account;
    currentPerson: IPersonMarineSuffix;
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
    isTopUsers: boolean = false;
    badError: string;
    homePagePersonHourPieChart: Chart;
    personHourChart: Chart;
    priceCostChart: Chart;

    chart: Chart;
    chartResults: IChartResult[] = [];
    priceCostSeries: any;
    personHourSeries: any;
    priceCostNewPercentSeries: any[] = [];
    priceCostFinishedPercentSeries: any[] = [];
    personHourNewPercentSeries: any[] = [];
    personHourFinishedPercentSeries: any[] = [];

    categories: any[];
    groups: any[];
    niazsanjiYear: number;
    years: any[];
    selectedNiazsanjiYear: number;

    detailMonthPriceCostSeries: any;
    detailMonthPersonHourSeries: any;
    detailMonthPersonHourChart: Chart;
    detailMonthPriceCostChart: Chart;
    piePlanningPersonHourChart: Chart;
    pieRunnningPersonHourChart: Chart;
    piePlanningPriceCostChart: Chart;
    pieRunnningPriceCostChart: Chart;
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
                    this.currentPerson = resp.body;
                    if(!resp.body.organizationChartId){
                        this.badError = "موقعیت در چارت سازمانی برای شما تنظیم نشده است، لطفا مراتب را با مدیریت سامانه در میان بگذارید.";
                    }
                    //this.prepareHomePagePersonHourChart(resp.body.id);
                    this.prepareHomePagePersonEducationalModule(resp.body.id);
                    //this.prepareHomePageNiazsanjiReport(resp.body.id);
                })
            }
            if(this.isTopUsers)
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
    prepareHomePageNiazsanjiReport(personId: number){
        this.finalNiazsanjiReportService.getHomePageNiazsanjiReport(personId).subscribe((resp: HttpResponse<IHomePageNiazsanjiReport>) => {

           this.homePageNiazsanjiReport = resp.body;
        },
            (res: HttpErrorResponse) => this.onError(res.message));
    }
    prepareHomePagePersonHourChart(personId: number){
        this.finalNiazsanjiReportService.getHomePagePersonHourChart(personId).subscribe((resp: HttpResponse<IHomePagePersonHourChart>) => {

                this.homePagePersonHourChart = resp.body;
                this.makePersonHourPieChart(resp.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }
    prepareHomePagePersonEducationalModule(personId: number){
        this.finalNiazsanjiReportService.getHomePagePersonEducationalModule(personId).subscribe((resp: HttpResponse<IHomePagePersonEducationalModule[]>) => {

                this.homePagePersonEducationalModules = resp.body;
                this.homePagePersonEducationalModules.forEach(a => {
                    a.totalLearningTime = a.learningTimePractical == undefined ? 0 : a.learningTimePractical + a.learningTimeTheorical == undefined ? 0 : a.learningTimeTheorical;
                   switch (a.status) {
                       case 100:
                           a.statusMeaning = "خاتمه دوره";
                           break;
                       case 90:
                           a.statusMeaning = "اجرا شده";
                           break;
                       case 80:
                           a.statusMeaning = "برنامه ریزی شده";
                           break;
                       case 70:
                           a.statusMeaning = "تصویب شوراء";
                           break;
                       case 0:
                           a.statusMeaning = "شناسنامه آموزشی";
                           break;
                   }
                });
                //this.makePersonHourPieChart(resp.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message));
    }

    makeChartResult(){
        this.groups = this.organizationcharts.filter(a => a.parentId == null).sort((a,b) => (a.id > b.id) ? 1 : (a.id < b.id) ? -1 : 0);
        this.categories = this.groups.map(a => a.title);
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

    makePersonHourPieChart(res: IHomePagePersonHourChart)
    {
        this.homePagePersonHourPieChart = new Chart({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie',
                style: {
                    fontFamily: 'IranSans, SansSerif, IranYekan, B Nazanin, B Badr, Tahoma, Times New Roman'
                }
            },
            title: {
                text: 'نمودار نفر ساعت به درصد'
            },
            tooltip: {
                pointFormat: '<b>درصد {point.percentage:.0f}</b>',
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer'
                }
            },
            series: [{
                name: '',
                //colorByPoint: true,
                data: [{
                    name: ' گذرانده شده شما تاکنون',
                    y: res.passed,
                    color: '#71f056'
                }, {
                    name: 'در حال برنامه ریزی برای اجرا',
                    y: res.designAndPlanning,
                    color: '#fcea63'
                }, {
                    name: 'باقیمانده',
                    y: res.remaining,
                    sliced: true,
                    selected: true,
                    color: '#f23537'
                }]
            }],
            credits: {
                enabled: false
            }
        });
    }
    redirect(): boolean {
        return false;
       //this.router.navigateByUrl(i);
    }
    makeSeries(){

        const sortedChartResults = this.chartResults.sort((a,b) => (a.groupId > b.groupId) ? 1 : (a.groupId < b.groupId) ? -1 : 0);
        this.priceCostSeries = [{
            name: "اجرا شده",
            data: sortedChartResults.map(a => a.priceCostFinished),
            color: "lightgreen"
        },{
            name: "اجرا نشده",
            data: sortedChartResults.map(a => a.priceCostNew),
            color: "red"
        },{
            name: "کل هزینه",
            data: sortedChartResults.map(a => a.totalPriceCost),
            color: "blue"
        }];
        this.groups.forEach(a => {
           sortedChartResults.filter(e => e.groupId == a.id).forEach(w => {
               debugger;
               this.personHourNewPercentSeries.push((w.educationalModuleTotalHourNew / w.totalPersonHour) * 100);
               this.personHourFinishedPercentSeries.push((w.educationalModuleTotalHourFinished / w.totalPersonHour) * 100);
               this.priceCostNewPercentSeries.push((w.priceCostNew / w.totalPriceCost) * 100);
               this.priceCostFinishedPercentSeries.push((w.priceCostFinished / w.totalPriceCost) * 100);
           });
        });
        this.personHourSeries = [{
            name: "اجرا شده",
            data: sortedChartResults.map(a => a.educationalModuleTotalHourFinished),
            color: "lightgreen",
        },{
            name: "اجرا نشده",
            data: sortedChartResults.map(a => a.educationalModuleTotalHourNew),
            color: "red"
        },{
            name: "کل نفرساعت",
            data: sortedChartResults.map(a => a.totalPersonHour),
            color: "blue",
        }];
        this.loadChart();
    }
    loadChart(){

        // @ts-ignore
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
                text: 'نمودار نفر ساعت گروه های سازمان'
            },
            xAxis: {
                categories: this.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'نفر ساعت'
                },
                opposite: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="direction: ltr; padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                style:{
                    direction: 'rtl'
                },
                useHTML: true,
                /*formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                        this.point.y + ' ' + this.point.name.toLowerCase();
                }*/
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series:{
                    cursor: 'pointer',
                    /*allowPointSelect: true,*/
                    point: {
                        events : {
                            click: event1 => {
                                this.showDetail(event1);
                            }
                        }
                    }
                }
            },
            series: this.personHourSeries,
            credits: {
                enabled: false
            }
        });
        // @ts-ignore
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
                text: 'نمودار هزینه (ریال) گروه های سازمان'
            },
            xAxis: {
                categories: this.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'میزان هزینه'
                },
                opposite: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="direction: ltr; padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                style:{
                    direction: 'rtl'
                },
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series:{
                    cursor: 'pointer',
                    point: {
                        events : {
                            click: event1 => {
                                this.showDetail(event1);
                            }
                        }
                    }
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

            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin)
                this.isSuperUsers = true;
            if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin || this.isModirAmozesh)
                this.isTopUsers = true;
        }
    }

    selectedGroup: string = "";
    //home page chart detail
    showDetail(event){

        this.selectedGroup = event.point.category;
        const org = this.organizationcharts.find(a => a.title == this.selectedGroup);

        if(org)
        {
            debugger;
            const rootId = this.treeUtilities.getRootId(this.organizationcharts, this.currentPerson.organizationChartId);
            if(this.isSuperUsers || org.id == rootId) {
                this.changePage('detail');
                this.showPlanningReport(org);
            }
        }
    }
    showPlanningReport(org: IOrganizationChartMarineSuffix){

        let niazsanjiYear = this.convertObjectDatesService.getNowShamsiYear();
        let orgRootId = org.id; //this.treeUtilities.getRootId(this.organizationcharts, this.currentPerson.organizationChartId);
        this.finalNiazsanjiReportService.getPlanningAndRunMonthReport(niazsanjiYear,3, orgRootId)
            .subscribe(
                (res: HttpResponse<IPlanningAndRunMonthReport[]>) => {

                    this.planningAndRunMonthReports = res.body;
                    this.planningAndRunMonthReports.forEach(a => {
                        a.persianMonth = this.convertObjectDatesService.convertMonthsNumber2MonthName(a.month);
                    });
                    this.makeDetailSeries();
                    this.makePiesSeries();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }
    makeDetailSeries(){

        this.detailMonthPriceCostSeries = [{
                name: "هزینه اجرا شده",
                data: this.planningAndRunMonthReports.filter(a => a.reportType == 2)
                    .sort((a,b) => (a.month > b.month) ? -1 : (a.month < b.month) ? 1 : 0)
                    .map(a => a.personCost),
                color: "#28a745"
            },{
            name: "هزینه برنامه ریزی شده",
            data: this.planningAndRunMonthReports.filter(a => a.reportType == 1)
                .sort((a,b) => (a.month > b.month) ? -1 : (a.month < b.month) ? 1 : 0)
                .map(a => a.personCost),
            color: "#ffc107"
        }];
        this.detailMonthPersonHourSeries = [{
            name: "نفر ساعت اجرا شده",
            data: this.planningAndRunMonthReports.filter(a => a.reportType == 2)
                .sort((a,b) => (a.month > b.month) ? -1 : (a.month < b.month) ? 1 : 0)
                .map(a => a.personHour),
            color: "#28a745"
        },{
            name: "نفر ساعت برنامه ریزی شده",
            data: this.planningAndRunMonthReports.filter(a => a.reportType == 1)
                .sort((a,b) => (a.month > b.month) ? -1 : (a.month < b.month) ? 1 : 0)
                .map(a => a.personHour),
            color: "#ffc107"
        }];
        this.loadDetailMonthColumnChart();
    }
    loadDetailMonthColumnChart(){
        const cats: any = MONTHS.sort((a,b) => (a.id > b.id) ? -1 : (a.id < b.id) ? 1 : 0).map(a => a.persianMonth);
        // @ts-ignore
        this.detailMonthPersonHourChart = this.showColumnChart(' نمودار نفر ساعت به تفکیک ماه برنامه ریزی و اجرا - ' + this.selectedGroup, this.detailMonthPersonHourSeries,
            'میزان نفر ساعت', cats);
        this.detailMonthPriceCostChart = this.showColumnChart(' نمودار هزینه (ریال) به تفکیک ماه برنامه ریزی و اجرا - ' + this.selectedGroup, this.detailMonthPriceCostSeries,
            'میزان هزینه', cats);
    }
    makePiesSeries()
    {
        let piePlanningPersonHourSeries: any = this.makePiePersonHourSeries(1);
        this.piePlanningPersonHourChart = this.showPieChart('نمودار درصد نفر ساعت برنامه ریزی شده گروه ' + this.selectedGroup +' به تفکیک ماه', piePlanningPersonHourSeries);
        let pieRunnningPersonHourSeries: any = this.makePiePersonHourSeries(2);
        this.pieRunnningPersonHourChart = this.showPieChart('نمودار درصد نفر ساعت اجرا شده گروه ' + this.selectedGroup +' به تفکیک ماه', pieRunnningPersonHourSeries);

        let piePlanningCostSeries: any = this.makePieCostSeries(1);
        this.piePlanningPriceCostChart = this.showPieChart('نمودار درصد هزینه برنامه ریزی شده گروه ' + this.selectedGroup +' به تفکیک ماه', piePlanningCostSeries);
        let pieRunnningCostSeries: any = this.makePieCostSeries(2);
        this.pieRunnningPriceCostChart = this.showPieChart('نمودار درصد هزینه اجرا شده گروه ' + this.selectedGroup +' به تفکیک ماه', pieRunnningCostSeries);
    }
    makePiePersonHourSeries(reportType: number){
        debugger;
        const filtered = this.planningAndRunMonthReports.filter(a => a.reportType == reportType);
        const allHour = filtered[0].totalHour;
        let array: any = [];
        MONTHS.forEach(a => {
           const monthFilter = filtered.filter(e => e.month == a.id);
           const sumMonth = monthFilter.map(a => a.personHour).reduce((sum, current) => sum + current);
           const sumMonthPercent = (sumMonth / allHour) * 100;
           array.push({
               name: a.persianMonth,
               y: sumMonthPercent,
               color: a.color
           });
        });
        return array;
    }
    makePieCostSeries(reportType: number){
        debugger;
        const filtered = this.planningAndRunMonthReports.filter(a => a.reportType == reportType);
        const allHour = filtered[0].totalPriceCost; //filtered.map(a => a.personCost).reduce((sum, current) => sum + current);
        let array: any = [];
        MONTHS.forEach(a => {
            const monthFilter = filtered.filter(e => e.month == a.id);
            const sumMonth = monthFilter.map(a => a.personCost).reduce((sum, current) => sum + current);
            const sumMonthPercent = (sumMonth / allHour) * 100;
            array.push({
                name: a.persianMonth,
                y: sumMonthPercent,
                color: a.color
            });
        });
        return array;
    }
    showPieChart(headerText: string, data: any): Chart{
        return new Chart({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie',
                style: {
                    fontFamily: 'IranSans, SansSerif, IranYekan, B Nazanin, B Badr, Tahoma, Times New Roman'
                }
            },
            title: {
                text: headerText,
                style: {
                    fontSize: '12px'
                }
            },
            tooltip: {
                pointFormat: '<b>%</b><b style="direction: ltr">{point.percentage:.0f}</b>',
                useHTML:true,
                style:{
                    direction: 'rtl'
                },
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer'
                }
            },
            series: [{
                name: '',
                //colorByPoint: true,
                data: data
            }],
            credits: {
                enabled: false
            }
        });
    }
    showColumnChart(headerText: string, series: any, title: string, categories: any): Chart{
        return new Chart({
            chart: {
                type: 'column',
                style: {
                    fontFamily: 'IranSans, SansSerif, IranYekan, B Nazanin, B Badr, Tahoma, Times New Roman'
                }
            },
            title: {
                text: headerText
            },
            lang: {
                decimalPoint: ',',
                thousandsSep: '.'
            },
            xAxis: {
                categories: categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: title
                },
                opposite: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="direction: ltr ;padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                style:{
                  direction: 'rtl'
                },
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: series,
            credits: {
                enabled: false
            }
        });
    }
}
