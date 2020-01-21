import {Injectable} from '@angular/core';
import {IOrganizationChartMarineSuffix} from "app/shared/model/organization-chart-marine-suffix.model";
import {RequestStatus} from "app/shared/model/enums/RequestStatus";

@Injectable({ providedIn: 'root' })
export class TreeUtilities {
    constructor() {

    }
    private list_to_tree(list) {


        let map = {}, node, roots = [], i;
        for (i = 0; i < list.length; i += 1) {
            map[list[i].id] = i; // initialize the map
            list[i].children = []; // initialize the children
        }
        for (i = 0; i < list.length; i += 1) {
            node = list[i];
            node.parentId = node.parentId == null ? 0 : node.parentId;
            if (node.parentId > 0) {
                // if you have dangling branches check that map[node.parentId] exists
                list[map[node.parentId]].children.push(node);
            } else {
                roots.push(node);
            }
        }
        return roots;
    }
    private getChildrenIds(data) : number[]
    {
        let ids : number[] = [];
        if(data.children.length){
            data.children.forEach((a) => {
                let returnIds = this.getChildrenIds(a);
                returnIds.forEach((a) => ids.push(a));
            });
        }
        ids.push(data.id);
        return ids;
    }
    hasChild(orgCharts: IOrganizationChartMarineSuffix[],organizationChartId: number): boolean{
        if(orgCharts.filter(a => a.parentId == organizationChartId).length > 0){
            return true;
        }
        else{
            return false;
        }
    }
    convertOrgChart2Tree(orgCharts: IOrganizationChartMarineSuffix[]){
        let listOfObjects = [];
        orgCharts.map(function (item) {
            let singleObj = {};
            singleObj['id'] = item.id;
            singleObj['name'] = item.title;
            singleObj['parentId'] = item.parentId;
            listOfObjects.push(singleObj);
        });
        return this.list_to_tree(listOfObjects);
    }
    resultParentIds: number[] = [];
    getParentIds(organizationcharts: IOrganizationChartMarineSuffix[] ,organizationChartId: number, clear: boolean = true) : number[] {
        if(clear)
            this.resultParentIds = [];
        let getOrg = organizationcharts.find(a => a.id == organizationChartId);
        if(getOrg.parentId > 0){
            this.resultParentIds.push(getOrg.id);
            return this.getParentIds(organizationcharts, getOrg.parentId, false);
        }
        this.resultParentIds.push(organizationChartId);
        return this.resultParentIds;
    }
    getRootId(organizationcharts: IOrganizationChartMarineSuffix[] ,organizationChartId: number) : number {
        let getOrg = organizationcharts.find(a => a.id == organizationChartId);
        if(getOrg.parentId > 0){
            //this.result.push(getOrg.id);
            return this.getRootId(organizationcharts, getOrg.parentId);
        }
        //this.result.push(organizationChartId);
        return getOrg.id;
    }
    isEmpty(obj) {
        for(let prop in obj) {
            if(obj.hasOwnProperty(prop))
                return false;
        }

        return true;
    }
    getAllOfThisTreeIds(organizationcharts: IOrganizationChartMarineSuffix[] ,organizationChartId: number) : number[] {

        let rootId = this.getRootId(organizationcharts, organizationChartId);
        let nodes = this.convertOrgChart2Tree(organizationcharts);
        let node = nodes.find(a => a.id == rootId);
        return this.getChildrenIds(node);
    }
    getAllOfChilderenIdsOfThisId(organizationcharts: IOrganizationChartMarineSuffix[] ,organizationChartId: number) : number[] {
        let resultChilderenIdsOfThisId = [];
        let orgs = organizationcharts.filter(a => a.parentId == organizationChartId);
        if(orgs.length){
            orgs.forEach(a => {
                let childs: number[] = this.getAllOfChilderenIdsOfThisId(organizationcharts,a.id);
                childs.forEach(e => resultChilderenIdsOfThisId.push(e));
            });
        }
        resultChilderenIdsOfThisId.push(organizationChartId);
        return resultChilderenIdsOfThisId;
    }
    getAllOfChilderenIdsOfThisIdWithoutItself(organizationcharts: IOrganizationChartMarineSuffix[] ,organizationChartId: number) : number[] {

        let orgIds = this.getAllOfChilderenIdsOfThisId(organizationcharts, organizationChartId);
        return orgIds.filter(a => a != organizationChartId);

        /*let resultChilderenIdsOfThisIdWithoutItself = [];
        let orgs = organizationcharts.filter(a => a.parentId == organizationChartId);
        if(orgs.length){
            orgs.forEach(a => {
                let childs: number[] = this.getAllOfChilderenIdsOfThisIdWithoutItself(organizationcharts,a.id);
                childs.forEach(e => resultChilderenIdsOfThisIdWithoutItself.push(e));
            });
        }
        resultChilderenIdsOfThisIdWithoutItself.push(organizationChartId);
        return resultChilderenIdsOfThisIdWithoutItself;*/
    }
    disableFathers(organizationcharts: IOrganizationChartMarineSuffix[]): IOrganizationChartMarineSuffix[] {
        organizationcharts.forEach(a => {
            let hasChild = organizationcharts.find(w => w.parentId == a.id);
            if(hasChild)
                a["disabled"] = true;
            else
                a["disabled"] = false;
        });
        return organizationcharts;
    }
    getStatusMeaning(organizationcharts: IOrganizationChartMarineSuffix[], status: number,requestStatus: RequestStatus): string {
        let organizationchart = organizationcharts.find(a => a.id == status);
        if(organizationchart) {
            if (requestStatus == RequestStatus.NEW) {
                return "منتظر تایید " + organizationchart.title;
            }
            if (requestStatus == RequestStatus.IGNORE) {
                return "رد شده توسط " + organizationchart.title;
            }
        }
        else {
            if(status == 0 && requestStatus == RequestStatus.NEW)
                return "منتظر در فهرست اولویت بندی";
            if(status == 0 && requestStatus == RequestStatus.READ)
                return "منتظر تصویب شوراء تربیت و آموزش سازمان";
            if(status == 0 && requestStatus == RequestStatus.IGNORE)
                return "رد شده توسط شوراء تربیت و آموزش سازمان";
            if(status == 0 && requestStatus == RequestStatus.ACCEPT)
                return "تایید شوراء تربیت و آموزش سازمان (تایید نهایی)";
        }
    }
    onlyUnique(value, index, self) {
        return self.indexOf(value) === index;
    }
}
