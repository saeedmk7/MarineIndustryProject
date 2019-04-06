import {Component, OnInit, ViewChild} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {HttpResponse, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import {
    INavBarItemAuthorityMarineSuffix,
    NavBarItemAuthorityMarineSuffix
} from 'app/shared/model/nav-bar-item-authority-marine-suffix.model';
import { NavBarItemAuthorityMarineSuffixService } from './nav-bar-item-authority-marine-suffix.service';
import { INavBarItemMarineSuffix } from 'app/shared/model/nav-bar-item-marine-suffix.model';
import { NavBarItemMarineSuffixService } from 'app/entities/nav-bar-item-marine-suffix';
import {TreeComponent,ITreeOptions } from "angular-tree-component";
import {IDTypeDictionary, ITreeNode} from "angular-tree-component/dist/defs/api";
import {authorityRoute} from "app/admin";
import {angularCoreEnv} from "@angular/core/src/render3/jit/environment";

@Component({
    selector: 'mi-nav-bar-item-authority-marine-suffix-update',
    templateUrl: './nav-bar-item-authority-marine-suffix-update.component.html'
})
export class NavBarItemAuthorityMarineSuffixUpdateComponent implements OnInit {
    private _navBarItemAuthority: INavBarItemAuthorityMarineSuffix;
    resultAuthorityBody: INavBarItemAuthorityMarineSuffix[];
    isSaving: boolean;
    navbaritems: INavBarItemMarineSuffix[];
    /*tempNavbaritems: INavBarItemMarineSuffix[];*/
    createDate: string;
    modifyDate: string;

    myId:number=0;
    //selectedIds: String="";

    nodes : any;
    @ViewChild(TreeComponent)
    private tree: TreeComponent;

    node: ITreeNode;
    options: ITreeOptions = {
        useCheckbox: true,
        rtl: true
    };

    optionsDisabled: ITreeOptions = {
        useCheckbox: true,
        useTriState: true
    };

    searchtxt:string;
    selectedTreeList: number[];
    selectedTreeListString: String[];
    private ng: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private navBarItemAuthorityService: NavBarItemAuthorityMarineSuffixService,
        private navBarItemService: NavBarItemMarineSuffixService,
        private activatedRoute: ActivatedRoute
    ) {

    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ navBarItemAuthority }) => {
            this.navBarItemAuthority = navBarItemAuthority;
        });
        this.loadAll().subscribe(
            (resultAuthorities: HttpResponse<INavBarItemAuthorityMarineSuffix[]>) => {
                this.navBarItemService.query().subscribe(
                    (res: HttpResponse<INavBarItemMarineSuffix[]>) => {
                        this.navbaritems = res.body;
                        this.resultAuthorityBody = resultAuthorities.body;
                        const wq = this.resultAuthorityBody;

                        let listOfObjects = [];
                        this.navbaritems.map((item) => {
                            const singleObj = {};
                            singleObj['id'] = item.id;
                            singleObj['name'] = item.title;
                            singleObj['parentId'] = item.parentId;
                            singleObj['checked'] = wq.some((a) => a.navBarItemId == item.id);
                            listOfObjects.push(singleObj);
                        });
                        this.nodes = this.list_to_tree(listOfObjects);
                        listOfObjects.forEach((nodeItem) =>{
                            if(nodeItem.checked) {
                                let _a;
                                /*this.tree.treeModel.setSelectedNode(nodeItem, true);*/
                                this.tree.treeModel.selectedLeafNodeIds = Object.assign({}, this.tree.treeModel.selectedLeafNodeIds, (_a = {}, _a[nodeItem.id] = true, _a));
                            }
                        });
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );


    }
    loadAll(): Observable<HttpResponse<INavBarItemAuthorityMarineSuffix[]>> {
        let criteria = [
            {key: 'authorityName.equals', value: this.navBarItemAuthority.authorityName}
        ];
        return this.navBarItemAuthorityService
            .query({
                page: 0,
                size: 10000,
                criteria
            });
    }
    /*private paginateNavBarItemAuthorities(data: INavBarItemAuthorityMarineSuffix[], headers: HttpHeaders) {
        this.navBarItemAuthorities = data;
    }*/
    /*onSelect(event) {
        const ee = event.treeModel;
        const ee2 = ee.selectedLeafNodeIds;
        const ww = Object.keys(ee2);
        this.navBarItemAuthority.navBarItemTitle = ww.join(',');
    }
    onDeselect(event){

        const ee = event.treeModel;
        const ee2 = ee.selectedLeafNodeIds;
        const ww = Object.keys(ee2);
        this.navBarItemAuthority.navBarItemTitle = ww.join(',');
    }*/

    private list_to_tree(list) {

        let map = {}, node, roots = [], i;
        for (i = 0; i < list.length; i += 1) {
            map[list[i].id] = i; // initialize the map
            list[i].children = []; // initialize the children
        }
        for (i = 0; i < list.length; i += 1) {
            node = list[i];
            node.parentId = node.parentId == null ? 0 : node.parentId;
            if (node.parentId !== 0) {
                // if you have dangling branches check that map[node.parentId] exists
                list[map[node.parentId]].children.push(node);
            } else {
                roots.push(node);
            }
        }
        return roots;
    }
    search(event)
    {
        this.tree.treeModel.filterNodes(event, true);
    }
    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        const authorityName = this.navBarItemAuthority.authorityName;
        const selectedLeafNodeIdsKeys = Object.keys(this.tree.treeModel.selectedLeafNodeIds);
        let selectedLeafNodeIds = [];
        for (let key of selectedLeafNodeIdsKeys) {
            const value = this.tree.treeModel.selectedLeafNodeIds[key];
            if(value)
                selectedLeafNodeIds.push(key);
        }
        //this.selectedTreeListString = this.navBarItemAuthority.navBarItemTitle.split(',');

        let ww = this.navbaritems.filter((a) => selectedLeafNodeIds.includes(""+a.id));
        ww.forEach((a) => {
            if(a.parentId){
                if(!selectedLeafNodeIds.includes(""+a.parentId)){
                    selectedLeafNodeIds.push(""+a.parentId);
                }
            }
        }) ;

        //Delete all the menus that selected for a specific authority
        /*this.navBarItemAuthorityService.deleteAuthority(authorityName);*/

        this.navBarItemAuthorityService.deleteAuthority(this.navBarItemAuthority.authorityName).subscribe(response => {
            //make a list of  navBarItemAuthority and send all of them to server and insert them all

            let navBarItemAuthorities: NavBarItemAuthorityMarineSuffix[] = [];
            selectedLeafNodeIds.forEach((a) => {
               let  navBarItemAuthorityMarineSuffix: NavBarItemAuthorityMarineSuffix = new NavBarItemAuthorityMarineSuffix();
               navBarItemAuthorityMarineSuffix.authorityName = authorityName;
               navBarItemAuthorityMarineSuffix.navBarItemId = +a;
               navBarItemAuthorityMarineSuffix.hasEditPermission = true;
               navBarItemAuthorities.push(navBarItemAuthorityMarineSuffix);
            });
            this.subscribeToSaveResponse(this.navBarItemAuthorityService.createBulk(navBarItemAuthorities));
        });
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INavBarItemAuthorityMarineSuffix>>) {
        result.subscribe(
            (res: HttpResponse<INavBarItemAuthorityMarineSuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackNavBarItemById(index: number, item: INavBarItemMarineSuffix) {
        return item.id;
    }
    get navBarItemAuthority() {
        return this._navBarItemAuthority;
    }

    set navBarItemAuthority(navBarItemAuthority: INavBarItemAuthorityMarineSuffix) {
        this._navBarItemAuthority = navBarItemAuthority;
    }
}
