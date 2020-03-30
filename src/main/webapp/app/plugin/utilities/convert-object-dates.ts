import {Injectable} from '@angular/core';
import * as moment from 'jalali-moment';
import {JhiLanguageService} from "ng-jhipster";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {MONTHS} from "app/shared/constants/months.constants";
import {PERSIAN_DATE_FORMAT} from "app/shared";
import * as gregorainMoment from 'moment';
import {Moment} from 'moment';
import {FINALNIAZSANJISTATUSMEANING} from "app/shared/constants/final-niazsanji-report-status-meaning.constants";
import {EFFECTIVENESSPHASELEVELS} from "app/shared/constants/effectiveness-phase-levels.constants";
import {IFinalNiazsanjiReportMarineSuffix} from "app/shared/model/final-niazsanji-report-marine-suffix.model";
import {IEducationalModuleMarineSuffix} from "app/shared/model/educational-module-marine-suffix.model";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {NiazSanjiSource} from "app/shared/model/enums/NiazSanjiSource";

@Injectable({ providedIn: 'root' })
export class ConvertObjectDatesService {
    isfa:boolean;

    constructor(private translate: JhiLanguageService) {
        this.isfa = translate.currentLang == 'fa';
    }
    fillFinalNiazsanjiData(finalNiazsanji: IFinalNiazsanjiReportMarineSuffix, educationalModule: IEducationalModuleMarineSuffix, orgChart: IOrganizationChartMarineSuffix) : IFinalNiazsanjiReportMarineSuffix {
        debugger;
        if(finalNiazsanji.niazSanjiSource == NiazSanjiSource.FARDI)
            finalNiazsanji.niazSanjiSource = NiazSanjiSource.FARDI2;
        finalNiazsanji.statusMeaning = this.convertFinalNiazsanjiStatus2EqualString(finalNiazsanji.status);
        finalNiazsanji.selectedEffectivenessPhaseLevelTitle = this.convertEffectivenessPhaseLevel2EqualString(finalNiazsanji.selectedEffectivenessPhaseLevel);
        finalNiazsanji.currentEffectivenessPhaseLevelTitle = this.convertEffectivenessPhaseLevel2EqualString(finalNiazsanji.currentEffectivenessPhaseLevel);
        finalNiazsanji.lastEffectivenessPhaseFinishPersian = this.miladi2ShamsiMoment(finalNiazsanji.lastEffectivenessPhaseFinish);
        finalNiazsanji.runMonthPersian = this.convertMonthsNumber2MonthName(finalNiazsanji.runMonth);
        finalNiazsanji.planningRunMonthPersian = this.convertMonthsNumber2MonthName(finalNiazsanji.planningRunMonth);
        if(educationalModule){
            finalNiazsanji.educationalModuleLevelTitle = educationalModule.skillableLevelOfSkillTitle;
            finalNiazsanji.educationalModuleTotalTime = educationalModule.totalLearningTime;
        }
        if(orgChart){
            finalNiazsanji.organizationChartFullTitle = orgChart.fullTitle;
            finalNiazsanji.organizationChartRootTitle = orgChart.fullTitle.split('>')[0];
        }
        return finalNiazsanji;
    }
    fillFinalNiazsanjiDataArray(finalNiazsanjis: IFinalNiazsanjiReportMarineSuffix[], educationalModules: IEducationalModuleMarineSuffix[], orgCharts: IOrganizationChartMarineSuffix[]) : IFinalNiazsanjiReportMarineSuffix[] {
        finalNiazsanjis.forEach((a: IFinalNiazsanjiReportMarineSuffix) => {
            const educationalModule = educationalModules.find(e => e.id == a.educationalModuleId);
            const orgChart = orgCharts.find(o => o.id == a.organizationChartId);
            a = this.fillFinalNiazsanjiData(a, educationalModule, orgChart);
        });
        return finalNiazsanjis;
    }
    public changeDate(obj,notbool = false){

        if(this.isfa) {
            const mustChangeList: string[] = ['timepassed'];
            const exceptionFields: string[] = ['dateOfStart','investDate'];
            for (let key in obj) {
                if (obj.hasOwnProperty(key)) {
                    let value = obj[key];
                    if(!exceptionFields.includes(key)) {
                        if (key.toLowerCase().includes('date')) {
                            if (value)
                                value = moment(value).format('jYYYY/jM/jD');
                        }

                        if (mustChangeList.filter(a => a === key.toLowerCase()).length) {
                            if (value)
                                value = moment(value).format('jYYYY/jM/jD');
                        }
                        if (!notbool) {
                            if (value === false)
                                value = 'خیر';
                            if (value === true)
                                value = 'بلی';
                        }
                    }
                    obj[key] = value;
                }
            }
        }
        return obj;
    }
    public changeArrayDate(objs,notbool = false){

        if(this.isfa) {
            for (let obj in objs)
            {
                objs[obj] = this.changeDate(objs[obj],notbool);
            }
        }
        return objs;
    }
    public miladi2Shamsi(date: Date): string {
        return date.getHours() + ":" + date.getMinutes() + "  " + moment(date).format(PERSIAN_DATE_FORMAT);
    }
    public miladi2ShamsiMoment(date: Moment): string {
        if(date)
            return moment(date.toISOString()).format(PERSIAN_DATE_FORMAT);
        return "";
    }
    public getYearsOfService(date: Moment): number{

        const startYear: number = date.year();
        const nowYear: number = gregorainMoment(Date()).toDate().getFullYear();
        return nowYear - startYear;
    }
    public shamsi2miladi(date: string): string {
        //return date.getHours() + ":" + date.getMinutes() + "  " + moment(date).format('jYYYY/jMM/jDD');
        if(date)
            return moment(date).toDate().toISOString();
        return "";
    }
    public shamsi2miladiMoment(date: string): Moment {
        //return date.getHours() + ":" + date.getMinutes() + "  " + moment(date).format('jYYYY/jMM/jDD');

        return gregorainMoment(moment.from(date, 'fa', 'YYYY/MM/DD').format('YYYY/MM/DD'));// .clone();
    }
    public getNowShamsiYear(): number {

        return +moment().format('jYYYY');
    }
    public getNowShamsiMonth(): number {
        debugger;
        return +moment().format('jMM');
    }
    public getNowShamsiDate(): string {
        debugger;
        return moment().format('jYYYY/jMM/jDD');
    }
    public get30YearsBeforeNow(): string {
        return moment().add(-30,'years').toISOString();
    }
    convertString2RequestStatus(newStatus: string): RequestStatus {
        switch (newStatus) {
            case 'NEW':
                return RequestStatus.NEW;
            case 'READ':
                return RequestStatus.READ;
            case 'RETURNED':
                return RequestStatus.RETURNED;
            case 'IGNORE':
                return RequestStatus.IGNORE;
            case 'ACCEPT':
                return RequestStatus.ACCEPT;
        }
    }
    convertMonthsNumber2MonthName(month: number) : string {
        if(month <= 0 || month > 12)
            return "نامشخص";
        return MONTHS.find(a => a.id == month).persianMonth;
    }
    convertStatus2EqualString(status: number): string {
        switch (status) {
            case 5:
                return "منتظر تایید مدیر قسمت";
            case 6:
                return "رد شده توسط مدیر قسمت";
            case 10:
                return "منتظر تایید مدیر آموزش";
            case 11:
                return "رد شده توسط مدیر آموزش";
            case 20:
                return "تایید نهایی درخواست توسط مدیر آموزش (منتظر تایید مدیر کل آموزش)";
            case 21:
                return "رد شده توسط مدیر کل آموزش";
            case 30:
                return "تایید نهایی درخواست توسط مدیر کل آموزش";
        }
        /*<span *ngSwitchCase="5">
            منتظر تایید مدیر قسمت
        </span>
        <span *ngSwitchCase="6">
            رد شده توسط مدیر قیمت
        </span>
        <span *ngSwitchCase="10">
            منتظر تایید مدیر آموزش
        </span>
        <span *ngSwitchCase="11">
            رد شده توسط مدیر آموزش
        </span>
        <span *ngSwitchCase="20">
            تایید نهایی درخواست توسط مدیر آموزش
        </span>*/
    }
    convertFinalNiazsanjiStatus2EqualString(status: number): string {
        const st = FINALNIAZSANJISTATUSMEANING.find(a => a.id == status);
        if(st){
            return st.mean;
        }
    }
    convertEffectivenessPhaseLevel2EqualString(effectivenessPhaseLevel: number): string {
        const ef = EFFECTIVENESSPHASELEVELS.find(a => a.id == effectivenessPhaseLevel);
        if(ef){
            return ef.title;
        }
    }
    getYearsArray() : any[]{
        let dates =[];
        let years = GREGORIAN_START_END_DATE.map(a => a.year);
        years.forEach(a=> {
            dates.push({
                id: a,
                title: a + ''
            })
        });
        return dates;
    }
    goClone(source) {
        return JSON.parse(JSON.stringify(source));
        /*if (Object.prototype.toString.call(source) === '[object Array]') {
            let clone = [];
            for (let i=0; i<source.length; i++) {
                clone[i] = this.goClone(source[i]);
            }
            return clone;
        } else if (typeof(source)=="object") {
            let clone = {};
            for (let prop in source) {
                if (source.hasOwnProperty(prop)) {
                    clone[prop] = this.goClone(source[prop]);
                }
            }
            return clone;
        } else {
            return source;
        }*/
    }
}
