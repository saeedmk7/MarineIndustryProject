import {Injectable} from "@angular/core";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {GREGORIAN_START_END_DATE} from "app/shared/constants/years.constants";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";

@Injectable({ providedIn: 'root' })
export class CommonSearchCheckerService {
    constructor(private treeUtilities: TreeUtilities){

    }
    public addOrganizationFilterToCriteria(criteria, organizationCharts: IOrganizationChartMarineSuffix[]){
        const org = criteria.find(a => a.key == 'organizationChartId.equals');
        if (org) {
            const orgId = +org.value;
            criteria = criteria.filter(a => a.key != 'organizationChartId.equals');
            if (orgId) {
                const childIds = this.treeUtilities.getAllOfChilderenIdsOfThisId(organizationCharts, orgId);
                criteria.push({
                    key: 'organizationChartId.in', value: childIds
                });
            }
        }
        return criteria;
    }
    public checkYear(criteria){
        const year = criteria.find(a => a.key == 'yearId.equals');
        if(year) {
            const val = +year.value;
            //criteria.pop('yearId');
            criteria = criteria.filter(a => a.key != 'yearId.equals');
            if (val) {
                let yearDetail = GREGORIAN_START_END_DATE.find(a => a.year == val);
                let beginDate = new Date(yearDetail.beginDate).toISOString();
                let endDate = new Date(yearDetail.endDate).toISOString();

                criteria.push({
                    key: 'createDate.lessOrEqualThan', value: endDate
                });
                criteria.push({
                    key: 'createDate.greaterOrEqualThan', value: beginDate
                });
            }
        }
        return criteria;
    }
    public checkRequestStatusFilters(criteria, currentPersonOrgChartId: number){
        const requestStatusFilters = criteria.find(a => a.key == 'requestStatusFilters.equals');
        if(requestStatusFilters) {
            const val = requestStatusFilters.value;
            criteria = criteria.filter(a => a.key != 'requestStatusFilters.equals');
            if (val) {
                if(val == RequestStatus.ACCEPT || val == RequestStatus.IGNORE){
                    criteria.push({
                        key: 'requestStatus.equals',
                        value: val
                    });
                }
                else if (val == RequestStatus.RETURNED){
                    criteria.push({
                        key: 'hasImportantMessage.equals',
                        value: true
                    });
                }
                else{
                    criteria.push({
                        key: 'status.equals',
                        value: currentPersonOrgChartId
                    }, {
                        key: 'requestStatus.equals',
                        value: RequestStatus.NEW
                    });
                }
            }
        }
        return criteria;
    }
}
