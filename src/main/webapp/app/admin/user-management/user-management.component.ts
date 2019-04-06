import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE } from 'app/shared';
import {Principal, UserService, User, IUser} from 'app/core';
import { UserMgmtDeleteDialogComponent } from 'app/admin';
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import {PersonMarineSuffixService} from "app/entities/person-marine-suffix";
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Component({
    selector: 'jhi-user-mgmt',
    templateUrl: './user-management.component.html'
})
export class UserMgmtComponent implements OnInit, OnDestroy {
    currentAccount: any;
    users: User[];
    people: IPersonMarineSuffix[];
    personId: number;
    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    message: string;
    login:string;
    /*criteriaSubscriber: Subscription;
    searchbarModel: SearchPanelModel[];
    done:boolean = false;
    criteria: any;*/

    constructor(
        private userService: UserService,
        private alertService: JhiAlertService,
        private principal: Principal,
        private parseLinks: JhiParseLinks,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private modalService: NgbModal,
        private convertObjectDatesService : ConvertObjectDatesService,
        private personService: PersonMarineSuffixService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });

        /*this.criteriaSubscriber = this.eventManager.subscribe('marineindustryprojApp.criteria', (criteria) =>{
            this.criteria = criteria.content;
            this.done = true;
            this.loadAll(criteria.content);

        });*/
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;


            /*let isActiveOptions = [{id:1,title:'فعال'},{id:0,title:'غیرفعال'}];

            this.searchbarModel = new Array<SearchPanelModel>();
            this.searchbarModel.push(new SearchPanelModel('userManagement','login','text', 'contains'));
            this.searchbarModel.push(new SearchPanelModel('userManagement','activated','select', 'equals',isActiveOptions));
            if(!this.done)*/
                this.loadAll();
            this.registerChangeInUsers();
        });
    }

    ngOnDestroy() {
        this.routeData.unsubscribe();
        //this.eventManager.destroy(this.criteriaSubscriber);
    }

    registerChangeInUsers() {
        this.eventManager.subscribe('userListModification', response => this.loadAll());
    }

    setActive(user, isActivated) {
        user.activated = isActivated;

        this.userService.update(user).subscribe(response => {
            if (response.status === 200) {
                this.error = null;
                this.success = 'OK';
                this.loadAll();
            } else {
                this.success = null;
                this.error = 'ERROR';
            }
        });
    }

    loadAll() {

        this.userService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                //criteria,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<User[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
            );
    }

    trackIdentity(index, item: User) {
        return item.id;
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/admin/user-management'], {
            queryParams: {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }
    resetPassword(user: User){
        this.message = "";
        if(confirm("آیا از ریست کردن رمز کاربر مورد نظر مطمئنید؟")){
            this.userService.find(user.login).subscribe((resp: HttpResponse<IUser>) => {
                this.userService.resetPassword(resp.body).subscribe((resp: HttpResponse<IUser>) => this.onPasswordChangeSuccess());
            });
        }
    }
    search(){

        if(this.login) {
            this.userService.find(this.login).subscribe((resp: HttpResponse<IUser>) => {
                this.users = [];
                let user = this.convertObjectDatesService.changeDate(resp.body);
                if(user.personId) {
                    let person = this.people.find(w => w.id == user.personId);
                    user.personFullName = person.fullName;
                }
                this.users.push(user);
            });
        }
        else if(this.personId){
            this.userService.findByPersonId(this.personId).subscribe((resp: HttpResponse<IUser>) => {
                this.users = [];
                let user = this.convertObjectDatesService.changeDate(resp.body);
                if(user.personId) {
                    let person = this.people.find(w => w.id == user.personId);
                    user.personFullName = person.fullName;
                }
                this.users.push(user);
            });
        }
        else{
            this.loadAll();
        }
    }
    onPasswordChangeSuccess(){
        this.message = "رمز عبور با موفقیت ریست شد.";
        this.alertService.success("userManagement.passSuccess")
    }
    deleteUser(user: User) {
        this.message = "";
        const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.user = user;
        modalRef.result.then(
            result => {
                // Left blank intentionally, nothing to do here
            },
            reason => {
                // Left blank intentionally, nothing to do here
            }
        );
    }

    private onSuccess(data, headers) {
        this.message = "";
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.users = this.convertObjectDatesService.changeArrayDate(data);


        if(this.personService.people)
        {
            this.people = this.personService.people;
            this.users.forEach((a) => {
                if(a.personId) {
                    let person = this.people.find(w => w.id == a.personId);
                    if(person)
                        a.personFullName = person.fullName;
                }
            });
        }
        else{
            this.personService.query().subscribe((resp: HttpResponse<IPersonMarineSuffix[]>) =>
            {
                this.people = resp.body;
                this.users.forEach((a) => {
                    if(a.personId) {
                        let person = this.people.find(w => w.id == a.personId);
                        if(person)
                            a.personFullName = person.fullName;
                    }
                });
            });
        }
    }

    private onError(error) {
        this.message = "";
        this.alertService.error(error.error, error.message, null);
    }
}
