import { Injectable } from '@angular/core';
import { TreeUtilities } from 'app/plugin/utilities/tree-utilities';
import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { GREGORIAN_START_END_DATE } from 'app/shared/constants/years.constants';
import { RequestStatus } from 'app/shared/model/enums/RequestStatus';
import { Grade } from 'app/shared/model/enums/Grade';
import { TranslateService } from '@ngx-translate/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class CommonSearchCheckerService {
    constructor(private treeUtilities: TreeUtilities, private translate: TranslateService) {}

    public convertEnumToSearchArray(object, enumName: string) {
        let keys = Object.keys(object);
        let title: string = 'marineindustryprojApp.' + enumName + '.';
        let resultArray: any[] = [];
        keys.forEach(w => {
            let translate = this.translate.get(title + w);
            translate.subscribe(result => {
                resultArray.push({
                    id: w,
                    title: result.toString()
                });
            });
        });
        return resultArray;
    }

    private getVariableName(variableFunction: () => any) {
        return /\s([^\s;]+);?\s*\}$/.exec(variableFunction.toString())[1];
    }

    public addOrganizationFilterToCriteria(criteria, organizationCharts: IOrganizationChartMarineSuffix[]) {
        const org = criteria.find(a => a.key == 'organizationChartId.equals');
        if (org) {
            const orgId = +org.value;
            criteria = criteria.filter(a => a.key != 'organizationChartId.equals');
            if (orgId) {
                const childIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(organizationCharts, orgId);
                criteria.push({
                    key: 'organizationChartId.in',
                    value: childIds
                });
            }
        }
        return criteria;
    }
    public addOrganizationFilterToCriteriaForFinalNiazsanjiReport(criteria, organizationCharts: IOrganizationChartMarineSuffix[]) {
        const org = criteria.find(a => a.key == 'organizationChartId.equals');

        if (org) {
            const orgId = +org.value;
            criteria = criteria.filter(a => a.key != 'organizationChartId.equals');
            if (orgId) {
                criteria = criteria.filter(a => a.key != 'organizationChartId.in');

                const childIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(organizationCharts, orgId);
                criteria.push({
                    key: 'organizationChartId.in',
                    value: childIds
                });
            }
        }
        return criteria;
    }

    public checkYear(criteria) {
        const year = criteria.find(a => a.key == 'yearId.equals');
        if (year) {
            const val = +year.value;
            //criteria.pop('yearId');
            criteria = criteria.filter(a => a.key != 'yearId.equals');
            if (val) {
                let yearDetail = GREGORIAN_START_END_DATE.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();

                criteria.push({
                    key: 'createDate.lessOrEqualThan',
                    value: endDate
                });
                criteria.push({
                    key: 'createDate.greaterOrEqualThan',
                    value: beginDate
                });
            }
        }
        return criteria;
    }

    public checkRequestStatusFilters(criteria, currentPersonOrgChartId: number) {
        const requestStatusFilters = criteria.find(a => a.key == 'requestStatusFilters.equals');
        if (requestStatusFilters) {
            const val = requestStatusFilters.value;
            criteria = criteria.filter(a => a.key != 'requestStatusFilters.equals');
            if (val) {
                if (val == RequestStatus.ACCEPT || val == RequestStatus.IGNORE) {
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: val
                    });
                } else if (val == RequestStatus.RETURNED) {
                    criteria.push({
                        key: 'hasImportantMessage.equals',
                        value: true
                    });
                } else {
                    criteria.push(
                        {
                            key: 'status.equals',
                            value: currentPersonOrgChartId
                        },
                        {
                            key: 'requestStatus.equals',
                            value: RequestStatus.NEW
                        }
                    );
                }
            }
        }
        return criteria;
    }
    public checkRequestStatusFiltersForIntegration(criteria, status: number = 0) {
        const requestStatusFilters = criteria.find(a => a.key == 'requestStatusFilters.equals');
        if (requestStatusFilters) {
            const val = requestStatusFilters.value;
            criteria = criteria.filter(a => a.key != 'requestStatusFilters.equals');
            if (val) {
                if (val == RequestStatus.ACCEPT || val == RequestStatus.IGNORE) {
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: val
                    });
                } else if (val == RequestStatus.RETURNED) {
                    criteria.push({
                        key: 'hasImportantMessage.equals',
                        value: true
                    });
                } else if (val == RequestStatus.READ) {
                    criteria = criteria.filter(w => w.key != 'status.greaterOrEqualThan');
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: RequestStatus.READ
                    });
                    criteria.push({
                        key: 'status.equals',
                        value: 20
                    });
                } else {
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: RequestStatus.READ
                    });
                    if (status) {
                        criteria = criteria.filter(w => w.key != 'status.greaterOrEqualThan');
                        criteria.push({
                            key: 'status.equals',
                            value: status
                        });
                    }
                    if (status == 0) {
                        criteria = criteria.filter(w => w.key != 'status.greaterOrEqualThan');
                        criteria.push({
                            key: 'status.equals',
                            value: '' + status
                        });
                    }
                }
            }
        }
        return criteria;
    }
    public checkRequestStatusFiltersForOrganizationChart(criteria, status: number = 0) {
        const requestStatusFilters = criteria.find(a => a.key == 'requestStatusFilters.equals');
        if (requestStatusFilters) {
            const val = requestStatusFilters.value;
            criteria = criteria.filter(a => a.key != 'requestStatusFilters.equals');
            if (val) {
                if (val == RequestStatus.ACCEPT || val == RequestStatus.IGNORE) {
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: val
                    });
                } else if (val == RequestStatus.RETURNED) {
                    criteria.push({
                        key: 'hasImportantMessage.equals',
                        value: true
                    });
                } else if (val == RequestStatus.READ) {
                    criteria = criteria.filter(w => w.key != 'status.greaterOrEqualThan');
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: RequestStatus.NEW
                    });
                    criteria.push({
                        key: 'status.equals',
                        value: 20
                    });
                } else {
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: RequestStatus.NEW
                    });
                    if (status) {
                        criteria = criteria.filter(w => w.key != 'status.greaterOrEqualThan');
                        criteria.push({
                            key: 'status.equals',
                            value: status
                        });
                    }
                    if (status == 0) {
                        criteria = criteria.filter(w => w.key != 'status.greaterOrEqualThan');
                        criteria.push({
                            key: 'status.equals',
                            value: '' + status
                        });
                    }
                }
            }
        }
        return criteria;
    }
}
