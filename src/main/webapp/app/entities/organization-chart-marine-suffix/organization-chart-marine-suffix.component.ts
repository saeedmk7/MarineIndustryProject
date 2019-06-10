import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOrganizationChartMarineSuffix } from 'app/shared/model/organization-chart-marine-suffix.model';
import { Principal } from 'app/core';
import { OrganizationChartMarineSuffixService } from './organization-chart-marine-suffix.service';
import { TreeComponent } from 'angular-tree-component';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {TreeUtilities} from "app/plugin/utilities/tree-utilities";

@Component({
    selector: 'mi-organization-chart-marine-suffix',
    templateUrl: './organization-chart-marine-suffix.component.html'
})
export class OrganizationChartMarineSuffixComponent implements OnInit, OnDestroy {
    organizationCharts: IOrganizationChartMarineSuffix[];
    people: IPersonMarineSuffix[];
    selectedPeople: IPersonMarineSuffix[];
    searchPerson: IPersonMarineSuffix;
    tempSelectedPeople: IPersonMarineSuffix[];
    showPeople: boolean = false;
    disable: boolean = false;
    isSaving:boolean = false;
    myId:number=0;
    currentAccount: any;
    currentPerson: IPersonMarineSuffix;
    eventSubscriber: Subscription;
    nodes : any;
    options = {
        rtl: true
    };
    @ViewChild(TreeComponent)
    private tree: TreeComponent;
    fullOrgTitle: string;
    searchtxt: string;
    error: string;
    success: string;
    isAdmin: boolean;
    isModirKolAmozesh: boolean = false;
    isKarshenasArshadAmozeshSazman: boolean = false;
    isModirAmozesh: boolean = false;
    isSuperUsers: boolean = false;
    isTopUsers: boolean = false;
    constructor(
        private organizationChartService: OrganizationChartMarineSuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private personService : PersonMarineSuffixService,
        private treeUtilities: TreeUtilities
    ) {

    }
    searchOrg($event: IPersonMarineSuffix){


        this.personService.find($event.id).subscribe((resp: HttpResponse<IPersonMarineSuffix>) =>{
            debugger;
            this.searchtxt = resp.body.organizationChartTitle;
            this.fullOrgTitle = this.organizationCharts.find(a => a.id == resp.body.organizationChartId).fullTitle;
            this.search(this.searchtxt);
        },
            (res: HttpErrorResponse) => this.onError(res.message));

        /*let criteria = [{
            key:'id.equals',
            value: $event.id
        }];
        this.organizationChartService.query({
            page: 0,
            size: 2000,
            criteria,
            sort: ['id,asc']
        }).subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                if(res.body.length) {
                    const a = res.body[0];
                    this.searchtxt = a.title;
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );*/
    }
    callAdd(){
        if(this.myId)
            document.getElementById("add-" + this.myId).click();
        else
            document.getElementById("add").click();
    }
    callDel(){
        document.getElementById("delete-" + this.myId).click();
    }
    callEdit(){
        document.getElementById("edit-" + this.myId).click();
    }
    loadAll() {
        this.organizationChartService.query().subscribe(
            (res: HttpResponse<IOrganizationChartMarineSuffix[]>) => {

                this.organizationCharts = res.body;

                if(this.isSuperUsers)
                    this.nodes = this.treeUtilities.convertOrgChart2Tree(this.organizationCharts);
                else
                {
                    const orgIds = this.treeUtilities.getAllOfThisTreeIds(this.organizationCharts, this.currentPerson.organizationChartId);
                    const selectedOrgs = this.organizationCharts.filter(a => orgIds.includes(a.id));
                    this.nodes = this.treeUtilities.convertOrgChart2Tree(selectedOrgs);
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadPeople(){
        this.personService.query().subscribe(
            (res: HttpResponse<IPersonMarineSuffix[]>) => {
                this.people = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.setRoles(this.currentAccount);
            this.personService.find(this.currentAccount.personId).subscribe((resp: HttpResponse<IPersonMarineSuffix>) => {
                this.currentPerson = resp.body;
                this.loadAll();
            }, (res: HttpErrorResponse) => this.onError(res.message));
        });
        this.loadPeople();
        this.registerChangeInOrganizationCharts();

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
    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IOrganizationChartMarineSuffix) {
        return item.id;
    }

    registerChangeInOrganizationCharts() {
        this.eventSubscriber = this.eventManager.subscribe('organizationChartListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }


    nodeName: string;
    nodeActive(event){

        event.node.toggleExpanded();
        this.myId = event.node.id;
        this.nodeName = event.node.data.name;

        let criteria = [{
            key:'organizationChartId.equals',
            value: this.myId
        }];
        this.personService.query({
            page: 0,
            size: 2000,
            criteria,
            sort: ['id,asc']
        }).subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) => {
            this.selectedPeople = resp.body;
            this.tempSelectedPeople = this.selectedPeople;
        },
            (error1:HttpErrorResponse) =>  error1);
        //this.selectedPeople = this.people.filter(a => a.organizationChartId == this.myId);

    }



    search(event)
    {
        this.tree.treeModel.filterNodes(event, true);
    }
    save(){


        let tempSeletedPeopleIds = this.tempSelectedPeople.map(a => a.id);
        let seletedPeopleIds = this.selectedPeople.map(a => a.id);
        this.isSaving = true;
        this.error = null;
        this.success = null;
        let deletedPeopleIds = tempSeletedPeopleIds.filter(a => !seletedPeopleIds.includes(a));
        //let deletedPeopleIds = this.tempSelectedPeople.filter(a => !this.selectedPeople.includes(a));

        this.selectedPeople.forEach((a:IPersonMarineSuffix) => {
            if(a.organizationChartId) {
                if (a.organizationChartId !== this.myId) {

                    this.personService.find(a.id).subscribe((person: HttpResponse<IPersonMarineSuffix>) => {
                        let newPerson = person.body;
                        newPerson.organizationChartId = this.myId;
                        this.subscribeToSaveResponse(this.personService.update(newPerson));
                    });

                }
            }
            else{
                this.personService.find(a.id).subscribe((person: HttpResponse<IPersonMarineSuffix>) => {
                    let newPerson = person.body;
                    newPerson.organizationChartId = this.myId;
                    this.subscribeToSaveResponse(this.personService.update(newPerson));
                });
            }
        });
        deletedPeopleIds.forEach((a:number) => {
            this.personService.find(a).subscribe((person: HttpResponse<IPersonMarineSuffix>) => {
                let newPerson = person.body;
                newPerson.organizationChartId = null;
                this.subscribeToSaveResponse(this.personService.update(newPerson));
            });
        });

    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<IPersonMarineSuffix>>) {
        result.subscribe((res: HttpResponse<IPersonMarineSuffix>) => this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }
    counter:number = 0;
    private onSaveSuccess(res : IPersonMarineSuffix) {
        this.isSaving = false;
        this.counter++;
        if(this.counter == 15){
            this.counter = 0;
            this.loadPeople();
        }
        //this.error = null;
        //this.success += res.name + " " + res.family + " با موفقیت ثبت شد.";
    }

    private onSaveError() {
        this.isSaving = false;
        //this.success = null;
        //this.error = "در هنگام ثبت اطلاعات مشکلی پیشامد کرده لطفا بعدا دوباره امتحان کنید.";
    }
}
