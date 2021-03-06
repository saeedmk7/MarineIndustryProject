import {AfterViewInit, Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import {JhiAlertService, JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper, Principal, LoginModalService, LoginService, User} from 'app/core';
import * as moment from 'jalali-moment';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import * as $ from 'jquery';
import {NavBarItemAuthorityMarineSuffixService} from "app/entities/nav-bar-item-authority-marine-suffix";
import {NavBarItemMarineSuffixService} from "app/entities/nav-bar-item-marine-suffix";
import {INavBarItemMarineSuffix} from "app/shared/model/nav-bar-item-marine-suffix.model";
import {INavBarItemAuthorityMarineSuffix} from "app/shared/model/nav-bar-item-authority-marine-suffix.model";
import {LocalStorageService} from 'ngx-webstorage';
import {CURRENT_ALLOWED_URL_KEY} from "app/shared/constants/storage-keys.constants";
import {forEach} from "@angular/router/src/utils/collection";

/*import * as menus from './menu.json';*/

@Component({
    selector: 'mi-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['navbar.scss']
})
export class NavbarComponent implements OnInit,AfterViewInit {
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    todayDate: string;
    treeNode: any[];
    navBarItemMarineSuffix: INavBarItemMarineSuffix[];

    constructor(
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private router: Router,
        private jhiAlertService: JhiAlertService,
        private navBarItemAuthorityMarineSuffixService: NavBarItemAuthorityMarineSuffixService,
        private navBarItemMarineSuffixService: NavBarItemMarineSuffixService,
        private $localStorage: LocalStorageService
    ) {
    }
    ngOnInit() {
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
        const now : any = new Date();
        this.todayDate = moment(now).locale('fa').format('YYYY/MM/DD');
        this.loadAll();
    }
    ngAfterViewInit(){
        $(document).ready(function(){
            // Rotating side menu arrows
            $(".sidebarMenu>ul>li>a").click(function(){

                $(".sidebarMenu>ul>li>a>i.fa-angle-down").removeClass("fa-angle-down").addClass("fa-angle-left");
                if($(this).siblings().hasClass('show')) {
                    $(this).children('i.fa-angle-down').removeClass("fa-angle-down").addClass("fa-angle-left");
                }
                else {
                    $(this).children('i.fa-angle-left').removeClass("fa-angle-left").addClass("fa-angle-down");
                }
            });
            $(".nchild").click(function () {

                $(".nchild").removeClass("active");
                $(this).addClass("active");
                $(".nav-item").removeClass('active');
                $(this).parents(".nav-item").addClass('active')

            })



        });
    }
    loadAll(){
        this.principal.identity().then(account => {
            if(account) {
                let ww = account.authorities;
                let criteria = [
                    {key: 'authorityName.In', value: ww}
                ];
                this.navBarItemAuthorityMarineSuffixService.query({
                    page: 0,
                    size: 10000,
                    criteria
                }).subscribe((res: HttpResponse<INavBarItemAuthorityMarineSuffix[]>) => {
                        let navBarItemIds = res.body.map(function (v) {
                            return v.navBarItemId;
                        });
                        /*let navBarItemCriteria = [
                            {key: 'id.In', value: navBarItemIds}
                        ];*/
                        this.navBarItemMarineSuffixService.query()
                            .subscribe((res: HttpResponse<INavBarItemMarineSuffix[]>) => {

                                this.navBarItemMarineSuffix = res.body;
                                let finalNavBarItemMarineSuffix = res.body;
                                this.navBarItemMarineSuffix = this.navBarItemMarineSuffix.filter((a) => navBarItemIds.includes(a.id));
                                for (let i = 0; i < this.navBarItemMarineSuffix.length; i++) {
                                    const id = this.navBarItemMarineSuffix[i].id;
                                    const childs = finalNavBarItemMarineSuffix.filter(a => a.parentId == id);
                                    const selectedChilds = this.navBarItemMarineSuffix.filter(a => a.parentId == id);
                                    if(childs.length != 0 && selectedChilds.length == 0)
                                    {
                                        this.navBarItemMarineSuffix = this.navBarItemMarineSuffix.filter(a => a.id != id);
                                    }
                                }
                                let navBarItemAddresses: string[] = this.navBarItemMarineSuffix.map(function (v) {
                                    return v.url;
                                });

                                let additionalUrlsThatNeeds: string[] = ["#/settings","#/password","#/document-marine-suffix","#/announcement-marine-suffix"];
                                additionalUrlsThatNeeds.forEach(a => {
                                    navBarItemAddresses.push(a);
                                });
                                this.$localStorage.store(CURRENT_ALLOWED_URL_KEY, navBarItemAddresses);

                                this.list_to_tree(this.navBarItemMarineSuffix.filter(a => a.isActive == true).sort(function(a,b)
                                {
                                    return (a.displayOrder > b.displayOrder) ? 1 : ((b.displayOrder > a.displayOrder) ? -1 : 0);
                                }));
                            })
                    },
                    (res: HttpErrorResponse) => this.onError(res.message))
            }
        });

    }

    private list_to_tree(list) {
        let map = {}, node, roots = [], i;
        for (i = 0; i < list.length; i += 1) {
            map[list[i].id] = i; // initialize the map
            list[i].children = []; // initialize the children
        }
        for (i = 0; i < list.length; i += 1) {
            node = list[i];
            //node.displayOrder = node.displayOrder;
            node.parentId = node.parentId == null ? 0 : node.parentId;
            if (node.parentId !== 0) {
                // if you have dangling branches check that map[node.parentId] exists
                list[map[node.parentId]].children.push(node);
            } else {
                roots.push(node);
            }
        }
        this.treeNode = roots;
    }
    onError(str){
        this.jhiAlertService.error(str);
    }
    changeLanguage(languageKey: string) {

        this.languageService.changeLanguage(languageKey);
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {

        this.modalRef = this.loginModalService.open();
    }

    logout() {

        this.loginService.logout();
        this.router.navigate(['']).then(()=> {
            setTimeout(()=>{
                this.login();
            },1000)

        });
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {

        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }
}
