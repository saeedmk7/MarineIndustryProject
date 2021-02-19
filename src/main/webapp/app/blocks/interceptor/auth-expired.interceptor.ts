import { Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoginService } from 'app/core/login/login.service';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { LoginModalService } from 'app/core';
import { Router } from '@angular/router';

export class AuthExpiredInterceptor implements HttpInterceptor {
    modalRef: NgbModalRef;

    constructor(private injector: Injector, private loginModalService: LoginModalService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            tap(
                (event: HttpEvent<any>) => {},
                (err: any) => {
                    if (err instanceof HttpErrorResponse) {
                        if (err.status === 401) {
                            this.logout();
                        }
                    }
                }
            )
        );
    }

    login() {
        this.loginModalService = this.injector.get(LoginModalService);
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        debugger;
        const loginService: LoginService = this.injector.get(LoginService);
        loginService.logout();

        const router: Router = this.injector.get(Router);
        router.navigate(['']).then(() => {
            setTimeout(() => {
                this.login();
            }, 1000);
        });
    }
}
