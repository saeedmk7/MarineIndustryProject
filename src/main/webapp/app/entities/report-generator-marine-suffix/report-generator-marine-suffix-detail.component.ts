import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { IReportGeneratorMarineSuffix } from 'app/shared/model/report-generator-marine-suffix.model';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { IPersonMarineSuffix } from 'app/shared/model/person-marine-suffix.model';
import { IFinalNiazsanjiReportMarineSuffix } from 'app/shared/model/final-niazsanji-report-marine-suffix.model';
import { ICourseTypeMarineSuffix } from 'app/shared/model/course-type-marine-suffix.model';
import { IEducationalModuleMarineSuffix } from 'app/shared/model/educational-module-marine-suffix.model';
import { MONTHS } from 'app/shared/constants/months.constants';
import { FINALNIAZSANJISTATUSMEANING } from 'app/shared/constants/final-niazsanji-report-status-meaning.constants';
import { EFFECTIVENESSPHASELEVELS } from 'app/shared/constants/effectiveness-phase-levels.constants';
import { Subscription } from 'rxjs';
import { EducationalModuleMarineSuffixService } from 'app/entities/educational-module-marine-suffix';
import { ConvertObjectDatesService } from 'app/plugin/utilities/convert-object-dates';
import { PersonMarineSuffixService } from 'app/entities/person-marine-suffix';
import { CourseTypeMarineSuffixService } from 'app/entities/course-type-marine-suffix';
import { OrganizationChartMarineSuffixService } from 'app/entities/organization-chart-marine-suffix';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { CommonSearchCheckerService } from 'app/plugin/utilities/common-search-checkers';
import { TranslateService } from '@ngx-translate/core';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { Principal } from 'app/core';
import { FinalNiazsanjiReportMarineSuffixService } from 'app/entities/final-niazsanji-report-marine-suffix';
import { ExcelService } from 'app/plugin/export-excel/excel-service';
import { IColumnModel } from 'app/entities/report-generator-marine-suffix/column-model';
import { REPORTGENERATORFIELDS } from 'app/shared/constants/report-generator-fields';
import { IKeyValue, KeyValue } from 'app/shared/model/custom/key-value';

@Component({
    selector: 'mi-report-generator-marine-suffix-detail',
    templateUrl: './report-generator-marine-suffix-detail.component.html'
})
export class ReportGeneratorMarineSuffixDetailComponent implements OnInit {
    currentAccount: any;
    finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    reportGenerator: IReportGeneratorMarineSuffix;
    coursetypes: ICourseTypeMarineSuffix[];
    educationalModules: IEducationalModuleMarineSuffix[];
    people: IPersonMarineSuffix[];
    recommendedPeople: IPersonMarineSuffix[];
    currentPerson: IPersonMarineSuffix;
    organizationcharts: IOrganizationChartMarineSuffix[];
    recommenedOrgCharts: IOrganizationChartMarineSuffix[];
    orgsRoot: IOrganizationChartMarineSuffix[];

    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    dates: any[];
    months: any[] = MONTHS;
    statusMeaning: any[] = FINALNIAZSANJISTATUSMEANING;
    effectivenessPhaseLevels: any[] = EFFECTIVENESSPHASELEVELS;

    done: boolean = false;
    yearsCollections: any[];

    niazSanjiSource: boolean = true;

    constructor(
        protected finalNiazsanjiReportService: FinalNiazsanjiReportMarineSuffixService,
        protected activatedRoute: ActivatedRoute,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected principal: Principal,
        protected router: Router,
        protected eventManager: JhiEventManager,
        private educationalModuleService: EducationalModuleMarineSuffixService,
        private jhiTranslate: TranslateService,
        private convertObjectDatesService: ConvertObjectDatesService,
        private personService: PersonMarineSuffixService,
        private courseTypeService: CourseTypeMarineSuffixService,
        private organizationChartService: OrganizationChartMarineSuffixService,
        private treeUtilities: TreeUtilities,
        private commonSearchCheckerService: CommonSearchCheckerService
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reportGenerator }) => {
            this.reportGenerator = reportGenerator;
            this.makeCriteria(JSON.parse(this.reportGenerator.searchParams));
        });
    }
    finalResultModel: any[] = [];
    columns: IColumnModel[];
    createFinalResultModel() {
        this.columns = <IColumnModel[]>JSON.parse(this.reportGenerator.columnNames);
        this.columns.forEach(e => {
            e.displayName = REPORTGENERATORFIELDS[e.columnName];
        });
        this.columns = this.columns.sort((a, b) => (a.displayOrder > b.displayOrder ? 1 : a.displayOrder < b.displayOrder ? -1 : 0));
        this.finalNiazsanjiReports.forEach((finalNiazsanjiReport: IFinalNiazsanjiReportMarineSuffix) => {
            let keyValues: KeyValue[] = [];
            this.columns.forEach(c => {
                let keyValue: KeyValue = new KeyValue();
                keyValue.key = c.columnName;

                let splicedCol: String[] = c.columnName.split('.');
                if (splicedCol.length == 1) {
                    keyValue.value = finalNiazsanjiReport[c.columnName];
                } else if (splicedCol.length == 2) {
                } else if (splicedCol.length == 3) {
                    const col: string = splicedCol[0].toString();
                    const rel: string = splicedCol[1].toString();
                    const relCol: string = splicedCol[2].toString();
                    if (rel == 'one') {
                        const hasValue: any[] = finalNiazsanjiReport[col];
                        if (hasValue && hasValue.length > 0) {
                            keyValue.value = hasValue[0][relCol];
                        }
                    } else {
                        const hasMany: any[] = finalNiazsanjiReport[col];
                        if (hasMany && hasMany.length > 0) {
                            const val = hasMany.map(a => a[relCol]);
                            if (val.length > 0) {
                                keyValue.value = val.join(' - ').toString();
                            }
                        }
                    }
                }
                //myObj[keyValue.key] = keyValue.value;
                keyValues.push(keyValue);
            });
            let myObj: object = keyValues;
            this.finalResultModel.push(myObj);
        });
        this.finalResultModel.forEach(w => {});
    }

    makeCriteria(criteria?, excelExport: boolean = false) {
        if (criteria) {
        } else {
            criteria = [];
        }
        if (this.currentPerson) {
            if (this.organizationChartService.organizationchartsAll) {
                this.organizationcharts = this.organizationChartService.organizationchartsAll;
                let wantOrgIds = this.treeUtilities
                    .getAllOfThisTreeIds(this.organizationChartService.organizationchartsAll, this.currentPerson.organizationChartId)
                    .filter(this.treeUtilities.onlyUnique);
                return this.handleAfterChart(wantOrgIds, criteria, excelExport);
            } else {
                this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                    this.organizationcharts = resp.body;
                    let wantOrgIds = this.treeUtilities
                        .getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId)
                        .filter(this.treeUtilities.onlyUnique);
                    this.handleAfterChart(wantOrgIds, criteria, excelExport);
                });
            }
        } else {
            this.principal.identity().then(account => {
                this.currentAccount = account;
                this.setRoles(account);

                this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                    this.currentPerson = resp.body;

                    if (this.organizationChartService.organizationchartsAll) {
                        this.organizationcharts = this.organizationChartService.organizationchartsAll;
                        let wantOrgIds = this.treeUtilities
                            .getAllOfThisTreeIds(
                                this.organizationChartService.organizationchartsAll,
                                this.currentPerson.organizationChartId
                            )
                            .filter(this.treeUtilities.onlyUnique);
                        this.handleAfterChart(wantOrgIds, criteria, excelExport);
                    } else {
                        this.organizationChartService.query().subscribe((resp: HttpResponse<IOrganizationChartMarineSuffix[]>) => {
                            this.organizationcharts = resp.body;

                            let wantOrgIds = this.treeUtilities
                                .getAllOfThisTreeIds(this.organizationcharts, this.currentPerson.organizationChartId)
                                .filter(this.treeUtilities.onlyUnique);
                            this.handleAfterChart(wantOrgIds, criteria, excelExport);
                        });
                    }
                });
            });
        }
    }
    setRoles(account: any) {
        if (account.authorities.find(a => a == 'ROLE_ADMIN') !== undefined) this.isAdmin = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_AMOZESH') !== undefined) this.isModirAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_MODIR_KOL_AMOZESH') !== undefined) this.isModirKolAmozesh = true;
        if (account.authorities.find(a => a == 'ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN') !== undefined)
            this.isKarshenasArshadAmozeshSazman = true;

        if (this.isKarshenasArshadAmozeshSazman || this.isModirKolAmozesh || this.isAdmin) this.isSuperUsers = true;
    }
    handleAfterChart(wantOrgIds: number[], criteria, excelExport: boolean = false) {
        //criteria = this.commonSearchCheckerService.checkRequestStatusFilters(criteria, this.currentPerson.organizationChartId);
        if (this.isSuperUsers) {
            this.loadAll(criteria, excelExport);
            return;
        }
        if (wantOrgIds.length > 0) {
            criteria.push({
                key: 'organizationChartId.in',
                value: wantOrgIds
            });
        } else {
            criteria.push({
                key: 'createUserLogin.in',
                value: [this.currentPerson.nationalId]
            });
        }
        this.loadAll(criteria, excelExport);
    }

    loadAll(criteria?, excelExport: boolean = false) {
        criteria = this.commonSearchCheckerService.addOrganizationFilterToCriteriaForFinalNiazsanjiReport(
            criteria,
            this.organizationcharts
        );

        if (excelExport) {
            this.finalNiazsanjiReportService
                .queryWithPeople({
                    page: this.page - 1,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => this.prepareForExportExcel(res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        } else {
            this.finalNiazsanjiReportService
                .query({
                    page: this.page - 1,
                    size: 20000,
                    criteria,
                    sort: ['id', 'asc']
                })
                .subscribe(
                    (res: HttpResponse<IFinalNiazsanjiReportMarineSuffix[]>) => this.paginateFinalNiazsanjiReports(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }
    protected paginateFinalNiazsanjiReports(data: IFinalNiazsanjiReportMarineSuffix[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;

        if (this.educationalModuleService.educationalModules) {
            this.educationalModules = this.educationalModuleService.educationalModules;
            this.finalNiazsanjiReports = this.loadData(data);
            this.createFinalResultModel();
        } else {
            this.educationalModuleService.query().subscribe(
                (res: HttpResponse<IEducationalModuleMarineSuffix[]>) => {
                    this.educationalModules = res.body;
                    this.finalNiazsanjiReports = this.loadData(data);
                    this.createFinalResultModel();
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }
    prepareForExportExcel(res: IFinalNiazsanjiReportMarineSuffix[]) {
        let a = new ExcelService(this.jhiTranslate);

        let finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[] = this.loadData(res);

        let report = [];
        let index: number = 0;
        finalNiazsanjiReports.forEach(a => {
            index++;

            const peopleText =
                a.finalNiazsanjiReportPeople && a.finalNiazsanjiReportPeople.length > 0
                    ? a.finalNiazsanjiReportPeople.map(w => w.personName + ' ' + w.personFamily).join(' - ')
                    : ' ';
            let niazsanjiSource = '';
            this.jhiTranslate.get('marineindustryprojApp.NiazSanjiSource.' + a.niazSanjiSource).subscribe(w => {
                niazsanjiSource = w;
            });
            let effectivenessPhaseGrade = '';
            this.jhiTranslate.get('marineindustryprojApp.Grade.' + a.effectivenessPhaseGrade).subscribe(w => {
                effectivenessPhaseGrade = w;
            });
            let obj: Object;
            obj = {
                index: index,
                organizationChartRoot: a.organizationChartRootTitle,
                organizationChartFullTitle: a.organizationChartFullTitle,
                people: peopleText,
                educationalModuleTitle: a.educationalModuleTitle,
                educationalModuleId: a.educationalModuleCode,
                educationalModuleLevel: a.educationalModuleLevelTitle,
                educationalModuleTotalTime: a.educationalModuleTotalTime,
                courseType: a.courseTypeTitle,
                priceCost: a.priceCost,
                finalizeCost: a.finalizeCost,
                niazSanjiSource: niazsanjiSource,
                niazsanjiYear: a.niazsanjiYear,
                planningRunMonth: a.planningRunMonthPersian,
                runMonth: a.runMonthPersian,
                teacher: a.teacherFullName,
                mahiatCourse: a.mahiatCourseTitle,
                status: a.statusMeaning,
                effectivenessPhaseAverage: a.effectivenessPhaseAverage,
                effectivenessPhaseGrade: effectivenessPhaseGrade,
                selectedEffectivenessPhaseLevel: a.selectedEffectivenessPhaseLevelTitle,
                currentEffectivenessPhaseLevel: a.currentEffectivenessPhaseLevelTitle,
                lastEffectivenessPhaseFinish: a.lastEffectivenessPhaseFinishPersian,
                description: a.description,
                createDate: a.createDate,
                modifyDate: a.modifyDate,
                restriction: a.restrictions ? a.restrictions.map(a => a.title).join(' - ') : ' ',
                restrictionDescription: a.restrictionDescription,
                goalsText: a.goalsText,
                prerequisite: a.prerequisite,
                priority: a.priority,
                niazsanjiInput: a.niazsanjiInputTitle,
                teachingApproach: a.teachingApproachTitle
            };
            report.push(obj);
        });
        a.exportAsExcelFile(report, 'finalNiazsanjiReports', 'marineindustryprojApp.finalNiazsanjiReport');
    }
    loadData(data: IFinalNiazsanjiReportMarineSuffix[]): IFinalNiazsanjiReportMarineSuffix[] {
        let finalNiazsanjiReports: IFinalNiazsanjiReportMarineSuffix[] = this.convertObjectDatesService.fillFinalNiazsanjiDataArray(
            data,
            this.educationalModules,
            this.organizationcharts
        );
        return this.convertObjectDatesService.changeArrayDate(finalNiazsanjiReports);
    }
    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    previousState() {
        window.history.back();
    }
}
