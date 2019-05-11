import { Injectable, isDevMode } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

import { Principal } from '../';
import { LoginModalService } from '../login/login-modal.service';
import { StateStorageService } from './state-storage.service';
import {CURRENT_ALLOWED_URL_KEY} from "app/shared/constants/storage-keys.constants";
import {LocalStorageService} from 'ngx-webstorage';

@Injectable({ providedIn: 'root' })
export class UserRouteAccessService implements CanActivate {
    constructor(
        private router: Router,
        private loginModalService: LoginModalService,
        private principal: Principal,
        private stateStorageService: StateStorageService,
        private $localStorage: LocalStorageService
    ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Promise<boolean> {
        const authorities = route.data['authorities'];
        // We need to call the checkLogin / and so the principal.identity() function, to ensure,
        // that the client has a principal too, if they already logged in by the server.
        // This could happen on a page refresh.
        return this.checkLogin(authorities, state.url) && this.canSeePage(state.url);
    }
    canSeePage(url: string): Promise<boolean> {

        const principal = this.principal;
        return Promise.resolve(
            principal.identity().then(account => {

                if (account) {

                    if(account.authorities.find(a => a == "ROLE_ADMIN") !== undefined) {
                        return true;
                    }
                    let urls: string[] = this.$localStorage.retrieve(CURRENT_ALLOWED_URL_KEY);
                    let splitUrl = url.split("/");
                    if(splitUrl.length > 0) {
                        let searchWord = "";
                        if(splitUrl[1].includes('('))
                            searchWord = splitUrl[1].substring(0, splitUrl[1].indexOf("("));
                        else if(splitUrl[1].includes('?'))
                            searchWord = splitUrl[1].substring(0, splitUrl[1].indexOf("?"));
                        else
                            searchWord = splitUrl[1];

                        if (urls.filter(a => a.includes(searchWord)).length > 0) {
                            return true;
                        }

                        return false;
                    }
                    return true;
                }
            }));
    }
    checkLogin(authorities: string[], url: string): Promise<boolean> {
        const principal = this.principal;
        return Promise.resolve(
            principal.identity().then(account => {
                if (!authorities || authorities.length === 0) {
                    return true;
                }

                if (account) {
                    return principal.hasAnyAuthority(authorities).then(response => {
                        if (response) {
                            return true;
                        }
                        if (isDevMode()) {
                            console.error('User has not any of required authorities: ', authorities);
                        }
                        return false;
                    });
                }

                this.stateStorageService.storeUrl(url);
                this.router.navigate(['accessdenied']).then(() => {
                    // only show the login dialog, if the user hasn't logged in yet
                    if (!account) {
                        this.loginModalService.open();
                    }
                });
                return false;
            })
        );
    }
}
